package com.guomz.myo2o.controller.local;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.guomz.myo2o.dto.LocalAuthExecution;
import com.guomz.myo2o.entity.LocalAuth;
import com.guomz.myo2o.entity.PersonInfo;
import com.guomz.myo2o.enums.LocalAuthStateEnum;
import com.guomz.myo2o.service.LocalAuthService;
import com.guomz.myo2o.util.HttpServletRequestUtil;

@RestController
@RequestMapping("/local")
public class LocalAuthController {

	@Autowired
	private LocalAuthService localAuthService;
	
	/**
	 * 绑定微信
	 * @param req
	 * @return
	 */
	@RequestMapping(value="/bindlocalauth",method=RequestMethod.POST)
	public Map<String,Object> bindLocalAuth(HttpServletRequest req)
	{
		Map<String,Object> modelMap=new HashMap<String,Object>();
		String userName = HttpServletRequestUtil.getString(req, "userName");
		String password = HttpServletRequestUtil.getString(req, "password");
		String verifyCode = HttpServletRequestUtil.getString(req, "verifyCode");
		
		String realCode = (String) req.getSession().getAttribute("code");
		if(verifyCode.equals(realCode)==false)
		{
			modelMap.put("success", false);
			modelMap.put("errMsg", "验证码错误");
			return modelMap;
		}
		
		if(userName!=null&&!userName.equals("")&&password!=null&&!password.equals(""))
		{
			PersonInfo psersonInfo = (PersonInfo) req.getSession().getAttribute("user");
			LocalAuth localAuth=new LocalAuth();
			localAuth.setPersonInfo(psersonInfo);
			localAuth.setUserName(userName);
			localAuth.setPassword(password);
			localAuth.setPersonInfo(psersonInfo);
			try {
				LocalAuthExecution localAuthExecution = localAuthService.bindLocalAuth(localAuth);
				if(localAuthExecution.getState()!=LocalAuthStateEnum.SUCCESS.getState())
				{
					modelMap.put("success", false);
					modelMap.put("errMsg", localAuthExecution.getStateInfo());
				}
				else
				{
					modelMap.put("success", true);
				}
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				modelMap.put("success", false);
				modelMap.put("errMsg", e.getMessage());
			}
			
		}
		else
		{
			modelMap.put("success", false);
			modelMap.put("errMsg", "用户名或密码不能为空");
		}
		return modelMap;
	}
	
	/**
	 * 修改密码
	 * @param req
	 * @return
	 */
	@RequestMapping(value="/changelocalpassword",method=RequestMethod.POST)
	public Map<String,Object> changeLocalPassword(HttpServletRequest req)
	{
		Map<String,Object> modelMap=new HashMap<String,Object>();
		String userName = HttpServletRequestUtil.getString(req, "userName");
		String password = HttpServletRequestUtil.getString(req, "password");
		String newPassword = HttpServletRequestUtil.getString(req, "newPassword");
		String verifyCode = HttpServletRequestUtil.getString(req, "verifyCode");
		
		String realCode = (String) req.getSession().getAttribute("code");
		if(realCode.equals(verifyCode)==false)
		{
			modelMap.put("success", false);
			modelMap.put("errMsg", "验证码错误");
			return modelMap;
		}
		
		if(userName!=null&&password!=null&&newPassword!=null)
		{
			PersonInfo user = (PersonInfo) req.getSession().getAttribute("user");
			LocalAuth temp = localAuthService.getLocalAuthByUserId(user.getUserId());
			//用户名或密码与原来不一致
			if(!temp.getUserName().equals(userName)||!temp.getPassword().equals(password))
			{
				modelMap.put("success", false);
				modelMap.put("errMsg", "用户名或密码不正确");
			}
			else
			{
				try {
					LocalAuthExecution localAuthExecution = localAuthService.modifyLocalAuth(user.getUserId(), userName, password, newPassword);
					if(localAuthExecution.getState()!=LocalAuthStateEnum.SUCCESS.getState())
					{
						modelMap.put("success", false);
						modelMap.put("errMsg", localAuthExecution.getStateInfo());
					}
					else
					{
						modelMap.put("success", true);
					}
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
					modelMap.put("success", false);
					modelMap.put("errMsg", e.getMessage());
				}
				
			}
		}
		else
		{
			modelMap.put("success", false);
			modelMap.put("errMsg", "用户名或密码为空");
		}
		return modelMap;
	}
	
	/**
	 * 登陆判断
	 * @param req
	 * @return
	 */
	@RequestMapping(value="/logincheck" ,method=RequestMethod.POST)
	public Map<String,Object> loginCheck(HttpServletRequest req)
	{
		Map<String,Object> modelMap=new HashMap<String,Object>();
		String userName = HttpServletRequestUtil.getString(req, "userName");
		String password = HttpServletRequestUtil.getString(req, "password");
		String verifyCode = HttpServletRequestUtil.getString(req, "verifyCode");
		
		String realCode = (String) req.getSession().getAttribute("code");
		if(realCode.equals(verifyCode)==false)
		{
			modelMap.put("success", false);
			modelMap.put("errMsg", "验证码错误");
			return modelMap;
		}
		
		LocalAuth localAuth = localAuthService.getLocalAuthByUserNameAndPassword(userName, password);
		if(localAuth==null)
		{
			modelMap.put("success", false);
			modelMap.put("errMsg", "用户名或密码不正确");
		}
		else
		{
			modelMap.put("success", true);
			req.getSession().setAttribute("user", localAuth.getPersonInfo());
		}
		return modelMap;
	}
	
	/**
	 * 用户登出
	 * @param req
	 * @return
	 */
	@RequestMapping(value="/logout",method=RequestMethod.GET)
	public Map<String ,Object> logOut(HttpServletRequest req)
	{
		Map<String,Object> modelMap=new HashMap<String,Object>();
		try {
			req.getSession().setAttribute("user", null);
			modelMap.put("success", true);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			modelMap.put("success", false);
			modelMap.put("errMsg", false);
		}
		return modelMap;
	}
}
