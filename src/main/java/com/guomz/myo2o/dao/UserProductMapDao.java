package com.guomz.myo2o.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.guomz.myo2o.entity.UserProductMap;

public interface UserProductMapDao {

	public List<UserProductMap> queryUserProductMapList(@Param(value="condition")UserProductMap condition,@Param(value="rowIndex")int rowIndex,@Param(value="pageSize")int pageSize);
	
	public int queryUserProductMapCount(@Param(value="condition")UserProductMap condition);
	
	public int insertUserProductMapCount(UserProductMap userProductMap);
}
