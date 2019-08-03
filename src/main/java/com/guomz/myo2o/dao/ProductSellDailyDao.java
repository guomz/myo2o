package com.guomz.myo2o.dao;

import java.util.Date;
import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.guomz.myo2o.entity.ProductSellDaily;

public interface ProductSellDailyDao {

	/**
	 * 用于统计某商品在指定时间区间内的销量
	 * @param condition
	 * @param beginTime
	 * @param endTime
	 * @return
	 */
	public List<ProductSellDaily> queryProductSellDailyList(@Param(value="condition")ProductSellDaily condition,@Param(value="beginTime")Date beginTime,@Param(value="endTime")Date endTime);
	
	/**
	 * 生成商品日销量
	 * @return
	 */
	public int insertProductSellDaily();
	
	/**
	 * 该商品无销量时的插入
	 * @return
	 */
	public int insertDefaultProductSellDaily();
}
