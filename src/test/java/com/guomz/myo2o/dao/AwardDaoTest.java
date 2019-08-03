package com.guomz.myo2o.dao;

import java.util.Date;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.guomz.myo2o.BaseTest;
import com.guomz.myo2o.entity.Award;

public class AwardDaoTest extends BaseTest{
	
	@Autowired
	private AwardDao ad;

	@Test
	public void testInsert()
	{
		Award award=new Award();
		award.setAwardName("award1");
		award.setAwardDesc("testing");
		award.setAwardImg("aaa");
		award.setCreateTime(new Date());
		award.setEnableStatus(0);
		award.setLastEditTime(new Date());
		award.setPoint(11);
		award.setPriority(1);
		award.setShopId(1L);
		ad.insertAward(award);
	}
	
	@Test
	public void testUpdate()
	{
		Award award=new Award();
		award.setAwardId(1L);
		award.setShopId(1L);
		award.setAwardName("award11");
		award.setAwardDesc("testinging");
		award.setAwardImg("aaaa");
		award.setCreateTime(new Date());
		award.setEnableStatus(1);
		award.setLastEditTime(new Date());
		award.setPoint(12);
		award.setPriority(2);
		ad.updateAward(award);
	}
	
	@Test
	public void testquery()
	{
		//System.out.println(ad.queryAwardByAwardId(1L));
		Award award=new Award();
		award.setAwardName("award");
		award.setAwardDesc("testing");
		award.setAwardImg("aaa");
		award.setCreateTime(new Date());
		award.setEnableStatus(1);
		award.setLastEditTime(new Date());
		award.setPoint(11);
		award.setPriority(1);
		award.setShopId(1L);
		//System.out.println(ad.queryAwardList(award, 0, 10));
		System.out.println(ad.queryAwardCount(award));
	}
	
	@Test
	public void testDelete()
	{
		ad.deleteAward(1L, 1L);
	}
}
