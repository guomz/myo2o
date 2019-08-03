package com.guomz.myo2o.dao;

import java.util.Date;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.guomz.myo2o.BaseTest;
import com.guomz.myo2o.entity.LocalAuth;
import com.guomz.myo2o.entity.PersonInfo;

public class LocalAuthDaoTest extends BaseTest{

	@Autowired
	private LocalAuthDao localAuthDao;
	
	@Test
	public void insertLocalAuth()
	{
		LocalAuth localAuth=new LocalAuth();
		PersonInfo p=new PersonInfo();
		p.setUserId(1L);
		localAuth.setPersonInfo(p);
		localAuth.setUserName("guomz");
		localAuth.setPassword("123");
		localAuth.setCreateTime(new Date());
		localAuth.setLastEditTime(new Date());
		localAuthDao.insertLocalAuth(localAuth);
	}
	
	@Test
	public void queryLocalAuthById()
	{
		System.out.println(localAuthDao.queryLocalAuthByUserId(1L));
	}
	
	@Test
	public void queryLocalAuthByNameAndPwd()
	{
		System.out.println(localAuthDao.queryLocalAuthByUserNameAndPassword("guomz9405", "123"));
	}
	
	@Test
	public void updateLocalAuth()
	{
		localAuthDao.updateLocalAuth(1L, "guomz9405", "123", "123456", new Date());
	}
}
