package com.guomz.myo2o.dao;

import java.util.Date;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.guomz.myo2o.BaseTest;
import com.guomz.myo2o.entity.PersonInfo;
import com.guomz.myo2o.entity.Shop;
import com.guomz.myo2o.entity.UserShopMap;

public class UserShopMapDaoTest extends BaseTest{

	@Autowired
	private UserShopMapDao usmd;
	
	@Test
	public void testInsert()
	{
		UserShopMap userShopMap=new UserShopMap();
		PersonInfo user=new PersonInfo();
		user.setUserId(1L);
		Shop shop=new Shop();
		shop.setShopId(1L);
		userShopMap.setUser(user);
		userShopMap.setShop(shop);
		userShopMap.setPoint(1);
		userShopMap.setCreateTime(new Date());
		usmd.insertUserShopMap(userShopMap);
	}
	
	@Test
	public void testUpdate()
	{
		UserShopMap userShopMap=new UserShopMap();
		PersonInfo user=new PersonInfo();
		user.setUserId(1L);
		Shop shop=new Shop();
		shop.setShopId(1L);
		userShopMap.setUser(user);
		userShopMap.setShop(shop);
		userShopMap.setPoint(2);
		userShopMap.setCreateTime(new Date());
		usmd.updateUserShopMap(userShopMap);
	}
	
	@Test
	public void testQuery()
	{
		UserShopMap userShopMap=new UserShopMap();
		PersonInfo user=new PersonInfo();
		user.setUserId(1L);
		user.setUserName("guomz");
		Shop shop=new Shop();
		shop.setShopId(1L);
		shop.setShopName("二手车交易");
		userShopMap.setUser(user);
		userShopMap.setShop(shop);
		userShopMap.setPoint(2);
		//userShopMap.setCreateTime(new Date());
		//System.out.println(usmd.queryUserShopMap(1L, 1L));
		System.out.println(usmd.queryUserShopMapList(userShopMap, 0, 5));
		System.out.println(usmd.queryUserShopMapCount(userShopMap));
	}
}
