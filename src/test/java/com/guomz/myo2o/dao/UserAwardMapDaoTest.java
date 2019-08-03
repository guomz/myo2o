package com.guomz.myo2o.dao;

import java.util.Date;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.guomz.myo2o.BaseTest;
import com.guomz.myo2o.entity.Award;
import com.guomz.myo2o.entity.PersonInfo;
import com.guomz.myo2o.entity.Shop;
import com.guomz.myo2o.entity.UserAwardMap;

public class UserAwardMapDaoTest extends BaseTest{

	@Autowired
	private UserAwardMapDao uamd;
	
	@Test
	public void testInsert()
	{
		UserAwardMap uam=new UserAwardMap();
		Award award=new Award();
		award.setAwardId(1L);
		PersonInfo user=new PersonInfo();
		user.setUserId(1L);
		PersonInfo operator=new PersonInfo();
		operator.setUserId(1L);
		Shop s=new Shop();
		s.setShopId(1L);
		uam.setCreateTime(new Date());
		uam.setPoint(1);
		uam.setUsedStatus(0);
		uam.setAward(award);
		uam.setUser(user);
		uam.setShop(s);
		uam.setOperator(operator);
		uamd.insertUserAwardMap(uam);
	}
	
	@Test
	public void testUpdate()
	{
		UserAwardMap uam=new UserAwardMap();
		Award award=new Award();
		award.setAwardId(1L);
		PersonInfo user=new PersonInfo();
		user.setUserId(1L);
		PersonInfo operator=new PersonInfo();
		operator.setUserId(1L);
		Shop s=new Shop();
		s.setShopId(1L);
		uam.setUserAwardId(2L);
		uam.setCreateTime(new Date());
		uam.setPoint(1);
		uam.setUsedStatus(1);
		uam.setAward(award);
		uam.setUser(user);
		uam.setShop(s);
		uam.setOperator(operator);
		uamd.updateUserAwardMap(uam);
	}
	
	@Test
	public void testQuery()
	{
		//System.out.println(uamd.queryUserAwardMapById(2L));
		UserAwardMap uam=new UserAwardMap();
		Award award=new Award();
		award.setAwardId(1L);
		award.setAwardName("test");
		PersonInfo user=new PersonInfo();
		user.setUserId(1L);
		user.setUserName("guomz");
		PersonInfo operator=new PersonInfo();
		operator.setUserId(1L);
		Shop s=new Shop();
		s.setShopId(1L);
		uam.setUserAwardId(2L);
		uam.setCreateTime(new Date());
		uam.setPoint(1);
		uam.setUsedStatus(1);
		uam.setAward(award);
		uam.setUser(user);
		uam.setShop(s);
		uam.setOperator(operator);
		//System.out.println(uamd.queryUserAwardMapList(uam, 0, 5));
		System.out.println(uamd.queryUserAwardMapCount(uam));
	}
}
