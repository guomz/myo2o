package com.guomz.myo2o.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.guomz.myo2o.entity.UserShopMap;

public interface UserShopMapDao {

	public List<UserShopMap> queryUserShopMapList(@Param(value="condition")UserShopMap condition,@Param(value="rowIndex")int rowIndex,@Param(value="pageSize")int pageSize);
	
	public int queryUserShopMapCount(@Param(value="condition")UserShopMap condition);
	
	public UserShopMap queryUserShopMap(@Param(value="userId")Long userId,@Param(value="shopId")Long shopId);
	
	public int insertUserShopMap(UserShopMap condition);
	
	public int updateUserShopMap(UserShopMap condition);
}
