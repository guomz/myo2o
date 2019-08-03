package com.guomz.myo2o.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.guomz.myo2o.entity.UserAwardMap;

public interface UserAwardMapDao {

	public List<UserAwardMap> queryUserAwardMapList(@Param(value="condition")UserAwardMap condition,@Param(value="rowIndex")Integer rowIndex,@Param(value="pageSize")Integer pageSize);
	
	public int queryUserAwardMapCount(@Param(value="condition")UserAwardMap condition);
	
	public UserAwardMap queryUserAwardMapById(Long userAwardId);
	
	public int insertUserAwardMap(UserAwardMap userAwardMap);
	
	public int updateUserAwardMap(UserAwardMap userAwardMap);
	
}
