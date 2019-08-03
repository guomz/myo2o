package com.guomz.myo2o.dao;

import java.util.Date;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.guomz.myo2o.BaseTest;
import com.guomz.myo2o.entity.PersonInfo;
import com.guomz.myo2o.entity.WechatAuth;

public class WechatAuthDaoTest extends BaseTest{

	@Autowired
	private WechatAuthDao wechatAuthDao;
	
	@Test
	public void insertWechatAuth()
	{
		WechatAuth w=new WechatAuth();
		PersonInfo p=new PersonInfo();
		p.setUserId(1L);
		w.setCreateTime(new Date());
		w.setOpenId("123");
		w.setPersonInfo(p);
		wechatAuthDao.insertWechatAuth(w);
	}
	
	@Test
	public void queryWechatAuthById()
	{
		System.out.println(wechatAuthDao.queryWechatAuthById("123"));
	}
}
