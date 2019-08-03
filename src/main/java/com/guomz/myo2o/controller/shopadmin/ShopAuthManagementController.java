package com.guomz.myo2o.controller.shopadmin;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.zxing.client.j2se.MatrixToImageWriter;
import com.google.zxing.common.BitMatrix;
import com.guomz.myo2o.dto.ShopAuthMapExecution;
import com.guomz.myo2o.dto.UserAccessToken;
import com.guomz.myo2o.dto.WeChatInfo;
import com.guomz.myo2o.entity.Shop;
import com.guomz.myo2o.entity.ShopAuthMap;
import com.guomz.myo2o.entity.WechatAuth;
import com.guomz.myo2o.enums.ShopAuthMapStateEnum;
import com.guomz.myo2o.service.ShopAuthMapService;
import com.guomz.myo2o.service.WechatAuthService;
import com.guomz.myo2o.util.HttpServletRequestUtil;
import com.guomz.myo2o.util.QRCodeUtil;
import com.guomz.myo2o.util.ShortNetAddressUtil;
import com.guomz.myo2o.util.weixin.WechatUtil;

@RequestMapping("/shopadmin")
@Controller
public class ShopAuthManagementController {

	@Autowired
	private ShopAuthMapService shopAuthMapService;
	@Autowired
	private WechatAuthService weChatAuthService;
	//一下四个字符串为二维码的链接，为了方便处理故分成四段
	private String urlPrefix="https://open.weixin.qq.com/connect/oauth2/authorize?appid=wx56fe220e30e6f35c&redirect_uri=";
	private String authUrl="http://39.105.145.179/myo2o/shopadmin/addshopauthmap";
	private String urlMiddle="&role_type=1&response_type=code&scope=snsapi_userinfo&state=";
	private String urlSuffix="#wechat_redirect";

	/**
	 * 列出授权列表
	 * 
	 * @param req
	 * @return
	 */
	@RequestMapping(value = "/listshopauthmapsbyshop", method = RequestMethod.GET)
	@ResponseBody
	public Map<String, Object> listShopAuthMapsByShop(HttpServletRequest req) {
		Map<String, Object> modelMap = new HashMap<String, Object>();
		Integer pageIndex = HttpServletRequestUtil.getInt(req, "pageIndex");
		Integer pageSize = HttpServletRequestUtil.getInt(req, "pageSize");
		Shop shop = (Shop) req.getSession().getAttribute("currentShop");
		if (shop == null || shop.getShopId() == null || pageIndex == null || pageSize == null) {
			modelMap.put("success", false);
			modelMap.put("errMsg", "信息不完整");
		} else {
			try {
				ShopAuthMapExecution shopAuthMapExecution = shopAuthMapService.listShopAuthMapByShopId(shop.getShopId(),
						pageIndex, pageSize);
				modelMap.put("shopAuthMapList", shopAuthMapExecution.getShopAuthMapList());
				modelMap.put("count", shopAuthMapExecution.getCount());
				modelMap.put("success", true);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				modelMap.put("success", false);
				modelMap.put("errMsg", e.getMessage());
			}

		}
		return modelMap;
	}

	/**
	 * 编辑时返回授权信息
	 * 
	 * @param req
	 * @return
	 */
	@RequestMapping(value = "/getshopauthmapbyid", method = RequestMethod.GET)
	@ResponseBody
	public Map<String, Object> getShopAuthMapById(HttpServletRequest req) {
		Map<String, Object> modelMap = new HashMap<String, Object>();
		Long shopAuthId = HttpServletRequestUtil.getLong(req, "shopAuthId");
		if (shopAuthId == null) {
			modelMap.put("success", false);
			modelMap.put("errMsg", "信息不完整");
		} else {
			try {
				ShopAuthMapExecution shopAuthMapExecution = shopAuthMapService.getShopAuthMapById(shopAuthId);
				modelMap.put("success", true);
				modelMap.put("shopAuthMap", shopAuthMapExecution.getShopAuthMap());
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				modelMap.put("success", false);
				modelMap.put("errMsg", e.getMessage());
			}

		}
		return modelMap;
	}

	/**
	 * 变更授权信息可用状态或者编辑授权信息
	 * 
	 * @param req
	 * @return
	 */
	@RequestMapping(value = "/modifyshopauthmap", method = RequestMethod.POST)
	@ResponseBody
	public Map<String, Object> modifyShopAuthMap(
			@RequestParam(value = "shopAuthMapStr", required = false) String shopAuthMapStr,
			@RequestParam(value = "statusChange", required = false) Boolean statusChange, HttpServletRequest req) {
		Map<String, Object> modelMap = new HashMap<String, Object>();
		// 验证码判断
		if (statusChange == null || statusChange == false) {
			String verifyCodeActual = HttpServletRequestUtil.getString(req, "verifyCodeActual");
			String realCode = (String) req.getSession().getAttribute("code");
			if (!verifyCodeActual.equals(realCode)) {
				modelMap.put("success", false);
				modelMap.put("errMsg", "验证码错误");
				return modelMap;
			}
		}
		
		ObjectMapper om=new ObjectMapper();
		try {
			ShopAuthMap shopAuthMap=om.readValue(shopAuthMapStr, ShopAuthMap.class);
			if(shopAuthMap==null||shopAuthMap.getShopAuthId()==null)
			{
				modelMap.put("success", false);
				modelMap.put("errMsg", "信息不完整");
				return modelMap;
			}
			ShopAuthMapExecution shopAuthMapExecution = shopAuthMapService.modifyShopAuthMap(shopAuthMap);
			if(shopAuthMapExecution.getState()==ShopAuthMapStateEnum.SUCCESS.getState())
			{
				modelMap.put("success", true);
			}
			else
			{
				modelMap.put("success", false);
				modelMap.put("errMsg", shopAuthMapExecution.getStateInfo());
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			modelMap.put("success", false);
			modelMap.put("errMsg", e.getMessage());
		}
		return modelMap;
	}
	
	/**
	 * 生成二维码图片
	 * @param req
	 * @param resp
	 */
	@RequestMapping(value="/generateqrcode4shopauth",method=RequestMethod.GET)
	public void generateQRCode4ShopAuth(HttpServletRequest req,HttpServletResponse resp)
	{
		//取出当前店铺信息
		Shop shop=(Shop) req.getSession().getAttribute("currentShop");
		if(shop!=null&&shop.getShopId()!=null)
		{
			//获取当前时间，给二维码当作有效时间
			long timeStamp = System.currentTimeMillis();
			String content="{aaashopIdaaa:"+shop.getShopId()+",aaacreateTimeaaa:"+timeStamp+"}";
			try {
				//最终的原url地址，由于url过长会导致二维码出错故需截短
				String longUrl=urlPrefix+authUrl+urlMiddle+URLEncoder.encode(content, "UTF-8")+urlSuffix;
				String shortUrl = ShortNetAddressUtil.getShortUrl(longUrl);
				BitMatrix qrCodeStream = QRCodeUtil.generateQRCodeStream(shortUrl, resp);
				MatrixToImageWriter.writeToStream(qrCodeStream, "png", resp.getOutputStream());
			} catch (UnsupportedEncodingException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		}
	}
	
	/**
	 * 添加授权信息
	 * @param req
	 * @return
	 */
	@RequestMapping(value="/addshopauthmap",method=RequestMethod.GET)
	public String addShopAuthMap(HttpServletRequest request)
	{
		String code = request.getParameter("code");
		// 这个state可以用来传我们自定义的信息，方便程序调用，这里也可以不用
		System.out.println("getting roleType...");
		String stateFlag="state";
		String content = request.getParameter(stateFlag);
		while(content==null)
		{
			stateFlag="amp;"+stateFlag;
			content=request.getParameter(stateFlag);
		}
		String openId = null;
		WechatAuth wechatAuth = null;
		if (null != code) {
			UserAccessToken token;
			try {
				// 通过code获取access_token
				token = WechatUtil.getUserAccessToken(code);
				// 通过token获取openId
				openId = token.getOpenId();
				wechatAuth = weChatAuthService.getWechatAuthByOpenId(openId);
			} catch (IOException e) {
				//log.error("error in getUserAccessToken or getUserInfo or findByOpenId: " + e.toString());
				e.printStackTrace();
			}
		}
		else
		{
			return "shop/operationfail";
		}
		
		//通过微信进行过登陆的用户有资格被命名为店员
		if(wechatAuth!=null)
		{
			try {
				String qrCodeInfo=new String(URLDecoder.decode(content, "UTF-8"));
				ObjectMapper om=new ObjectMapper();
				//替换aaa变为json格式，得到二维码信息
				WeChatInfo wechatInfo=om.readValue(qrCodeInfo.replaceAll("aaa", "\""), WeChatInfo.class);
				//进行防止重复操作的判断
				ShopAuthMapExecution shopAuthMapExecution = shopAuthMapService.listShopAuthMapByShopId(wechatInfo.getShopId(), 1, 999);
				for(ShopAuthMap temp:shopAuthMapExecution.getShopAuthMapList())
				{
					if(temp.getEmployee().getUserId()==wechatAuth.getPersonInfo().getUserId())
					{
						return "shop/operationfail";
					}
				}
				
				//验证二维码有效时间10分钟
				long currentTime = System.currentTimeMillis();
				if(currentTime-wechatInfo.getCreateTime()>60000)
				{
					return "shop/operationfail";
				}
				
				//添加授权信息
				ShopAuthMap shopAuthMap=new ShopAuthMap();
				Shop shop=new Shop();
				shop.setShopId(wechatInfo.getShopId());
				shopAuthMap.setCreateTime(new Date());
				shopAuthMap.setTitle("店员");
				shopAuthMap.setTitleFlag(1);
				shopAuthMap.setEmployee(wechatAuth.getPersonInfo());
				shopAuthMap.setShop(shop);
				ShopAuthMapExecution shopAuthMapExecution2 = shopAuthMapService.addShopAuthMap(shopAuthMap);
				if(shopAuthMapExecution2.getState()==ShopAuthMapStateEnum.SUCCESS.getState())
				{
					return "shop/operationsuccess";
				}
				else
				{
					return "shop/operationfail";
				}
			} catch (UnsupportedEncodingException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				return "shop/operationfail";
			} catch (JsonParseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				return "shop/operationfail";
			} catch (JsonMappingException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				return "shop/operationfail";
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				return "shop/operationfail";
			}
		}
		
		
		return "shop/operationfail";
	}
}
