package com.guomz.myo2o.config.interceptor;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import com.guomz.myo2o.interceptor.FrontedInterceptor;
import com.guomz.myo2o.interceptor.ShopLoginInterceptor;
import com.guomz.myo2o.interceptor.ShopPermissionInterceptor;

@Configuration
public class InterceptorConfigurer implements WebMvcConfigurer{

	/**
	 * 配置拦截器路径
	 */
	@Override
	public void addInterceptors(InterceptorRegistry registry) {
		// TODO Auto-generated method stub
		String interceptPath="/shopadmin/**";
		String frontedPath="/fronted/**";
		InterceptorRegistration loginIR = registry.addInterceptor(new ShopLoginInterceptor());
		InterceptorRegistration shopIR = registry.addInterceptor(new ShopPermissionInterceptor());
		InterceptorRegistration frontIR = registry.addInterceptor(new FrontedInterceptor());
		loginIR.addPathPatterns(interceptPath);
		shopIR.addPathPatterns(interceptPath);
		frontIR.addPathPatterns(frontedPath);
		shopIR.excludePathPatterns("/shopadmin/shoplist","/shopadmin/getshoplist","/shopadmin/getshopinitinfo"
				,"/shopadmin/registershop","/shopadmin/shopoperation","/shopadmin/shopmanagement","/shopadmin/getshopmanagementinfo","/shopadmin/addshopauthmap");
		//二维码不需要校验
		frontIR.excludePathPatterns("/fronted/login","/shopadmin/addshopauthmap");
		loginIR.excludePathPatterns("/shopadmin/addshopauthmap");
	}

	
}
