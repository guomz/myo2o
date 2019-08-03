package com.guomz.myo2o.dao;

import java.util.Date;

import org.apache.ibatis.annotations.Param;

import com.guomz.myo2o.entity.LocalAuth;

public interface LocalAuthDao {

	/**
	 * 根据用户名与密码查询本地账号信息
	 * @param userName
	 * @param Password
	 * @return
	 */
	public LocalAuth queryLocalAuthByUserNameAndPassword(@Param("userName")String userName,@Param("password")String password);
	
	/**
	 * 根据用户id查询本地账号信息
	 * @param userId
	 * @return
	 */
	public LocalAuth queryLocalAuthByUserId(Long userId);
	
	/**
	 * 插入本地账号信息
	 * @param localAuth
	 * @return
	 */
	public int insertLocalAuth(LocalAuth localAuth);
	
	/**
	 * 修改密码使用
	 * @param userId
	 * @param userName
	 * @param password
	 * @param newPassword
	 * @return
	 */
	public int updateLocalAuth(@Param("userId")Long userId,@Param("userName")String userName,@Param("password")String password,@Param("newPassword")String newPassword,@Param("lastEditTime")Date lastEditTime);
}
