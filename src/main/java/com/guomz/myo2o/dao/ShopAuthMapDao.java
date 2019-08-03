package com.guomz.myo2o.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.guomz.myo2o.entity.ShopAuthMap;

public interface ShopAuthMapDao {

	/**
	 * 返回某店铺下的授权职称列表
	 * @param shopId
	 * @return
	 */
	public List<ShopAuthMap> queryShopAuthMapListByShopId(@Param(value="shopId")Long shopId,@Param(value="rowIndex")Integer rowIndex,@Param(value="pageSize")Integer pageSize);
	
	public int queryShopAuthMapCount(@Param(value="shopId")Long shopId);
	
	public int insertShopAuthMap(ShopAuthMap shopAuthMap);
	
	public int updateShopAuthMap(ShopAuthMap shopAuthMap);
	
	public int deleteShopAuthMap(Long shopAuthId);
	
	public ShopAuthMap queryShopAuthMapById(Long shopAuthId);
}
