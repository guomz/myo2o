package com.guomz.myo2o.dao;

import java.util.Date;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.guomz.myo2o.BaseTest;
import com.guomz.myo2o.entity.PersonInfo;
import com.guomz.myo2o.entity.Shop;
import com.guomz.myo2o.entity.ShopAuthMap;

public class ShopAuthDaoTest extends BaseTest{

	@Autowired
	private ShopAuthMapDao shopAuthDao;
	
	@Test
	public void testInsert()
	{
		ShopAuthMap shopAuthMap=new ShopAuthMap();
		PersonInfo employee=new PersonInfo();
		employee.setUserId(1L);
		Shop shop=new Shop();
		shop.setShopId(1L);
		shopAuthMap.setShop(shop);
		shopAuthMap.setEmployee(employee);
		shopAuthMap.setTitle("test");
		shopAuthMap.setTitleFlag(1);
		shopAuthMap.setEnableStatus(0);
		shopAuthMap.setCreateTime(new Date());
		shopAuthMap.setLastEditTime(new Date());
		shopAuthDao.insertShopAuthMap(shopAuthMap);
	}
	
	@Test
	public void testUpdate()
	{
		ShopAuthMap shopAuthMap=new ShopAuthMap();
		shopAuthMap.setShopAuthId(1L);
		PersonInfo employee=new PersonInfo();
		employee.setUserId(1L);
		Shop shop=new Shop();
		shop.setShopId(1L);
		shopAuthMap.setShop(shop);
		shopAuthMap.setEmployee(employee);
		shopAuthMap.setTitle("testing");
		shopAuthMap.setTitleFlag(2);
		shopAuthMap.setEnableStatus(1);
		shopAuthMap.setCreateTime(new Date());
		shopAuthMap.setLastEditTime(new Date());
		shopAuthDao.updateShopAuthMap(shopAuthMap);
	}
	
	@Test
	public void testQuery()
	{
		ShopAuthMap shopAuthMap=new ShopAuthMap();
		shopAuthMap.setShopAuthId(1L);
		PersonInfo employee=new PersonInfo();
		employee.setUserId(1L);
		Shop shop=new Shop();
		shop.setShopId(1L);
		shopAuthMap.setShop(shop);
		shopAuthMap.setEmployee(employee);
		shopAuthMap.setTitle("testing");
		shopAuthMap.setTitleFlag(2);
		shopAuthMap.setEnableStatus(1);
		shopAuthMap.setCreateTime(new Date());
		shopAuthMap.setLastEditTime(new Date());
		//System.out.println(shopAuthDao.queryShopAuthMapById(1L));
		//System.out.println(shopAuthDao.queryShopAuthMapListByShopId(1L, 0, 5));
		System.out.println(shopAuthDao.queryShopAuthMapCount(1L));
	}
	
	@Test
	public void testDelete()
	{
		shopAuthDao.deleteShopAuthMap(1L);
	}
}
