package com.guomz.myo2o.dao;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.guomz.myo2o.BaseTest;
import com.guomz.myo2o.entity.Product;
import com.guomz.myo2o.entity.Shop;

public class ProductDaoTest extends BaseTest{

	@Autowired
	private ProductDao productDao;
	
	@Test
	public void insertProduct()
	{
		Product p=new Product();
		p.setPriority(1);
		p.setProductName("test");
		Shop shop=new Shop();
		shop.setShopId(1L);
		p.setShop(shop);
		p.setEnableStatus(0);
		productDao.insertProduct(p);
		System.out.println(p);
	}
	
	@Test
	public void queryProductById()
	{
		System.out.println(productDao.queryProductById(5L));
	}
	
	@Test
	public void updateProduct()
	{
		Product p=new Product();
		p.setProductId(5L);
		p.setProductName("testing");
		productDao.updateProduct(p);
	}
}
