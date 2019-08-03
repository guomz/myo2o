package com.guomz.myo2o.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.guomz.myo2o.entity.Shop;

public interface ShopDao {

	/**
	 * 插入店铺
	 * @param shop
	 * @return
	 */
	public int insertShop(Shop shop);
	
	/**
	 * 更新店铺
	 * @param shop
	 * @return
	 */
	public int updateShop(Shop shop);
	
	/**
	 * 按照id查询店铺
	 * @param shopId
	 * @return
	 */
	public Shop queryShopById(Long shopId);
	
	/**
	 * 按条件分页查询店铺
	 * @param shopCondition
	 * @param rowIndex
	 * @param pageSize
	 * @return
	 */
	public List<Shop> queryShopList(@Param(value="shopCondition")Shop shopCondition,@Param("rowIndex")int rowIndex,@Param("pageSize")int pageSize);
	
	/**
	 * 按条件查询店铺数量
	 * @param shopCondition
	 * @return
	 */
	public int queryShopCount(@Param(value="shopCondition")Shop shopCondition);
}
