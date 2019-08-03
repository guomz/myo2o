package com.guomz.myo2o.dao;

import java.util.Date;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.guomz.myo2o.BaseTest;
import com.guomz.myo2o.entity.PersonInfo;

public class PersonInfoDaoTest extends BaseTest{

	@Autowired
	private PersonInfoDao personInfoDao;
	
	@Test
	public void insertPersonInfo()
	{
		PersonInfo p=new PersonInfo();
		p.setUserName("guomz");
		p.setGender("男");
		p.setEmail("123");
		p.setEnableStatus(1);
		p.setUserType(1);
		p.setCreateTime(new Date());
		p.setLastEditTime(new Date());
		personInfoDao.insertPersonInfo(p);
		System.out.println(p);
	}
	
	@Test
	public void queryPersonInfoById()
	{
		System.out.println(personInfoDao.queryPersonInfoById(1L));
	}
}
