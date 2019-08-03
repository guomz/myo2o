package com.guomz.myo2o.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.guomz.myo2o.dao.PersonInfoDao;
import com.guomz.myo2o.entity.PersonInfo;

@Service
public class PersonInfoService {

	@Autowired
	private PersonInfoDao personInfoDao;
	
	/**
	 * 查询用户信息
	 * @param userId
	 * @return
	 */
	public PersonInfo getPersonInfoById(Long userId)
	{
		return personInfoDao.queryPersonInfoById(userId);
	}
}
