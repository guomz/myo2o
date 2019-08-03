package com.guomz.myo2o.dao;

import com.guomz.myo2o.entity.PersonInfo;

public interface PersonInfoDao {

	/**
	 * 精确查询用户信息
	 * @param userId
	 * @return
	 */
	public PersonInfo queryPersonInfoById(Long userId);
	
	/**
	 * 插入用户信息
	 * @param personInfo
	 * @return
	 */
	public int insertPersonInfo(PersonInfo personInfo);
}
