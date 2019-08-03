package com.guomz.myo2o.controller.wechat;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.guomz.myo2o.dto.UserAccessToken;
import com.guomz.myo2o.dto.WechatAuthExecution;
import com.guomz.myo2o.dto.WechatUser;
import com.guomz.myo2o.entity.PersonInfo;
import com.guomz.myo2o.entity.WechatAuth;
import com.guomz.myo2o.enums.WechatAuthStateEnum;
import com.guomz.myo2o.service.PersonInfoService;
import com.guomz.myo2o.service.WechatAuthService;
import com.guomz.myo2o.util.weixin.WechatUtil;

@Controller
@RequestMapping("wechatlogin")
/**
 * 获取关注公众号之后的微信用户信息的接口，如果在微信浏览器里访问
 * https://open.weixin.qq.com/connect/oauth2/authorize?appid=您的appId&redirect_uri=http://o2o.yitiaojieinfo.com/o2o/wechatlogin/logincheck&role_type=1&response_type=code&scope=snsapi_userinfo&state=1#wechat_redirect
 * 则这里将会获取到code,之后再可以通过code获取到access_token 进而获取到用户信息
 * 
 * @author xiangze
 *
 */
public class WechatLoginController {

	private static Logger log = LoggerFactory.getLogger(WechatLoginController.class);

	@Autowired
	private PersonInfoService personInfoService;
	@Autowired
	private WechatAuthService wechatAuthService;
	// 判断用户通过哪个入口进入应用
	private static String FRONTED = "1";
	private static String SHOPADMIN = "2";

	@RequestMapping(value = "/logincheck", method = { RequestMethod.GET })
	public String loginCheck(HttpServletRequest request, HttpServletResponse response) {
		log.debug("weixin login get...");
		// 获取微信公众号传输过来的code,通过code可获取access_token,进而获取用户信息
		String code = request.getParameter("code");
		// 这个state可以用来传我们自定义的信息，方便程序调用，这里也可以不用
		System.out.println("getting roleType...");
		String stateFlag="state";
		String roleType = request.getParameter(stateFlag);
		while(roleType==null)
		{
			stateFlag="amp;"+stateFlag;
			roleType=request.getParameter(stateFlag);
		}
		System.out.println("roleTpye: "+roleType);
		log.debug("weixin login code:" + code);
		WechatUser user = null;
		String openId = null;
		WechatAuth wechatAuth = null;
		if (null != code) {
			UserAccessToken token;
			try {
				// 通过code获取access_token
				token = WechatUtil.getUserAccessToken(code);
				log.debug("weixin login token:" + token.toString());
				// 通过token获取accessToken
				String accessToken = token.getAccessToken();
				// 通过token获取openId
				openId = token.getOpenId();
				// 通过access_token和openId获取用户昵称等信息
				user = WechatUtil.getUserInfo(accessToken, openId);
				wechatAuth = wechatAuthService.getWechatAuthByOpenId(openId);
				log.debug("weixin login user:" + user.toString());
				request.getSession().setAttribute("openId", openId);
			} catch (IOException e) {
				log.error("error in getUserAccessToken or getUserInfo or findByOpenId: " + e.toString());
				e.printStackTrace();
			}
		}

		// 代表用户第一次使用
		if (wechatAuth == null) {
			PersonInfo personInfo = WechatUtil.getPersonInfoFromRequest(user);
			wechatAuth = new WechatAuth();
			wechatAuth.setOpenId(openId);
			if (roleType.equals(FRONTED)) {
				personInfo.setUserType(1);
			} else if (roleType.equals(SHOPADMIN)){
				personInfo.setUserType(2);
			}
			// 此时personinfo无id，会执行service的第一次注册方法
			wechatAuth.setPersonInfo(personInfo);
			WechatAuthExecution wechatAuthExecution = wechatAuthService.register(wechatAuth);
			if (wechatAuthExecution.getState() != WechatAuthStateEnum.SUCCESS.getState()) {
				return null;
			} else{
				personInfo = personInfoService.getPersonInfoById(wechatAuth.getPersonInfo().getUserId());
				request.getSession().setAttribute("user", personInfo);
			}
		} else {
			PersonInfo personInfo = personInfoService.getPersonInfoById(wechatAuth.getPersonInfo().getUserId());
			request.getSession().setAttribute("user", personInfo);
		}

		if (roleType.equals(FRONTED)) {
			return "fronted/index";
		} else if (roleType.equals(SHOPADMIN)) {
			return "shopadmin/shoplist";
		}
		return null;
	}
}
