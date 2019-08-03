package com.guomz.myo2o.dao;

import java.util.Date;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.guomz.myo2o.BaseTest;
import com.guomz.myo2o.entity.PersonInfo;
import com.guomz.myo2o.entity.Product;
import com.guomz.myo2o.entity.Shop;
import com.guomz.myo2o.entity.UserProductMap;

public class UserProductMapDaoTest extends BaseTest{

	@Autowired
	private UserProductMapDao upmd;
	
	@Test
	public void testInsert()
	{
		UserProductMap userProductMap=new UserProductMap();
		userProductMap.setCreateTime(new Date());
		userProductMap.setPoint(1);
		Product product=new Product();
		product.setProductId(1L);
		PersonInfo user=new PersonInfo();
		user.setUserId(1L);
		Shop shop=new Shop();
		shop.setShopId(1L);
		PersonInfo operator=new PersonInfo();
		operator.setUserId(1L);
		userProductMap.setOperator(operator);
		userProductMap.setProduct(product);
		userProductMap.setShop(shop);
		userProductMap.setUser(user);
		upmd.insertUserProductMapCount(userProductMap);
	}
	
	@Test
	public void testQuery()
	{
		UserProductMap userProductMap=new UserProductMap();
		//userProductMap.setCreateTime(new Date());
		userProductMap.setPoint(1);
		Product product=new Product();
		product.setProductId(1L);
		product.setProductName("热咖啡");
		PersonInfo user=new PersonInfo();
		user.setUserId(1L);
		user.setUserName("guomz");
		Shop shop=new Shop();
		shop.setShopId(1L);
		PersonInfo operator=new PersonInfo();
		operator.setUserId(1L);
		userProductMap.setOperator(operator);
		userProductMap.setProduct(product);
		userProductMap.setShop(shop);
		userProductMap.setUser(user);
		//System.out.println(upmd.queryUserProductMapList(userProductMap, 0, 5));
		System.out.println(upmd.queryUserProductMapCount(userProductMap));
	}
}
