package com.guomz.myo2o.interceptor;

import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import com.guomz.myo2o.entity.PersonInfo;

/**
 * 对进入店铺管理的用户做拦截，若session中没有用户信息则让用户登陆
 * @author 12587
 *
 */
public class ShopLoginInterceptor extends HandlerInterceptorAdapter{

	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		// TODO Auto-generated method stub
		PersonInfo user = (PersonInfo) request.getSession().getAttribute("user");
		if(user!=null)
		{
			if(user.getUserId()!=null&&user.getUserId()>=0&&user.getEnableStatus()==1)
			{
				return true;
			}
		}
		
		PrintWriter writer = response.getWriter();
		writer.println("<html>");
		writer.println("<script>");
		writer.println("window.open('"+request.getContextPath()+"/local/login?userType=2','_self')");
		writer.println("</script>");
		writer.println("</html>");
		return false;
	}

	
}
