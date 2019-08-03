package com.guomz.myo2o.interceptor;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import com.guomz.myo2o.entity.Shop;

/**
 * 用户操作店铺时进行权限管理，判断该店铺是否为当前用户所有
 * @author 12587
 *
 */
public class ShopPermissionInterceptor extends HandlerInterceptorAdapter{

	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		// TODO Auto-generated method stub
		Shop shop = (Shop) request.getSession().getAttribute("currentShop");
		@SuppressWarnings("unchecked")
		List<Shop> shopList = (List<Shop>) request.getSession().getAttribute("shopList");
		
		if(shop!=null&&shopList!=null)
		{
			for(Shop temp:shopList)
			{
				if(temp.getShopId().equals(shop.getShopId())==true)
				{
					return true;
				}
			}
			return false;
		}
		
		return true;
	}

	
}
