package com.guomz.myo2o.dao;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.guomz.myo2o.BaseTest;
import com.guomz.myo2o.entity.ProductImg;

public class ProductImgDaoTest extends BaseTest{

	@Autowired
	private ProductImgDao productImgDao;
	
	@Test
	public void batchInsertProductImg()
	{
		ProductImg p1=new ProductImg();
		p1.setImgAddr("test1");
		ProductImg p2=new ProductImg();
		p2.setImgAddr("test2");
		List<ProductImg> list=new ArrayList<ProductImg>();
		list.add(p1);
		list.add(p2);
		productImgDao.batchInsertProductImg(list);
	}
}
