package com.guomz.myo2o.dao;

import java.util.Date;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.guomz.myo2o.BaseTest;
import com.guomz.myo2o.entity.Product;
import com.guomz.myo2o.entity.ProductSellDaily;
import com.guomz.myo2o.entity.Shop;

public class ProductSellDailyDaoTest extends BaseTest{

	@Autowired
	private ProductSellDailyDao psdd;
	
	@Test
	public void testinsert()
	{
		psdd.insertProductSellDaily();
	}
	
	@Test
	public void testQuery()
	{
		ProductSellDaily productSellDaily=new ProductSellDaily();
		Shop shop=new Shop();
		shop.setShopId(1L);
		Product product=new Product();
		product.setProductId(1L);
		product.setProductName("热咖啡");
		productSellDaily.setShop(shop);
		productSellDaily.setProduct(product);
		System.out.println(psdd.queryProductSellDailyList(productSellDaily, null, new Date()));
	}
}
