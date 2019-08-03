package com.guomz.myo2o.service;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.guomz.myo2o.BaseTest;

public class ProductCategoryTest extends BaseTest{

	@Autowired
	private ProductCategoryService productCategoryService;
	
	@Test
	public void getProductCategoryList()
	{
		System.out.println(productCategoryService.getProductCategoryList(1L));
	}
}
