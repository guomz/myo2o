package com.guomz.myo2o.interceptor;

import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import com.guomz.myo2o.entity.PersonInfo;

public class FrontedInterceptor extends HandlerInterceptorAdapter{

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
		writer.println("window.open('"+request.getContextPath()+"/local/login?userType=1','_self')");
		writer.println("</script>");
		writer.println("</html>");
		return false;
	}

}
