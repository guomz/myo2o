package com.guomz.myo2o.dao;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.guomz.myo2o.BaseTest;
import com.guomz.myo2o.entity.ProductCategory;

public class ProductCategoryDaoTest extends BaseTest{

	@Autowired
	private ProductCategoryDao productCategoryDao;
	
	@Test
	public void batchInsertProductCategory()
	{
		ProductCategory p1=new ProductCategory();
		p1.setProductCategoryName("test1");
		p1.setShopId(1L);
		p1.setPriority(3);
		p1.setCreateTime(new Date());
		ProductCategory p2=new ProductCategory();
		p2.setProductCategoryName("test2");
		p2.setShopId(1L);
		p2.setPriority(3);
		p2.setCreateTime(new Date());
		List<ProductCategory> plist=new ArrayList<ProductCategory>();
		plist.add(p1);
		plist.add(p2);
		productCategoryDao.batchInsertProductCategoryList(plist);
	}
}
