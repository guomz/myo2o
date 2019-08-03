package com.guomz.myo2o.dao;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.guomz.myo2o.BaseTest;
import com.guomz.myo2o.entity.ShopCategory;

public class ShopCategoryDaoTest extends BaseTest{

	@Autowired
	private ShopCategoryDao shopCategoryDao;
	
	@Test
	public void queryShopCategory()
	{
		System.out.println(shopCategoryDao.queryShopCategory(new ShopCategory()));
	}
}
