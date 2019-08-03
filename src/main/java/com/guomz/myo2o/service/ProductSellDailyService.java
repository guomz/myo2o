package com.guomz.myo2o.service;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.guomz.myo2o.dao.ProductSellDailyDao;
import com.guomz.myo2o.entity.ProductSellDaily;

@Service
public class ProductSellDailyService {

	@Autowired
	private ProductSellDailyDao productSellDailyDao;
	
	/**
	 * 统计前一天的销量
	 */
	public void dailyCalculate()
	{
		productSellDailyDao.insertProductSellDaily();
	}
	
	/**
	 * 列出某店铺内的销量或者列出某店铺某商品的销量
	 * @param condition
	 * @param beginTime
	 * @param endTime
	 * @return
	 */
	public List<ProductSellDaily> listProductSellDaily(ProductSellDaily condition,Date beginTime,Date endTime)
	{
		return productSellDailyDao.queryProductSellDailyList(condition, beginTime, endTime);
	}
}
