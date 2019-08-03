package com.guomz.myo2o.service;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.guomz.myo2o.BaseTest;
import com.guomz.myo2o.entity.Area;
import com.guomz.myo2o.entity.PersonInfo;
import com.guomz.myo2o.entity.Shop;
import com.guomz.myo2o.entity.ShopCategory;

public class ShopServiceTest extends BaseTest{

	@Autowired
	private ShopService shopService;
	
	@Test
	public void addShopReal()
	{
		Shop shop=new Shop();
		Area area=new Area();
		PersonInfo owner=new PersonInfo();
		ShopCategory shopCategory=new ShopCategory();
		shopCategory.setShopCategoryId(10L);
		area.setAreaId(10);
		owner.setUserId(1L);
		shop.setShopName("test");
		shop.setEnableStatus(0);
		shop.setOwner(owner);
		shop.setShopCategory(shopCategory);
		shop.setArea(area);
		File f=new File("d:/o2oProjectPics/test.jpg");
		try {
			shopService.addShop(new FileInputStream(f), shop, f.getName());
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	@Test
	public void modifyShop()
	{
		Shop shop=new Shop();
		shop.setShopId(13L);
		shop.setShopName("testchange");
		File f=new File("d:/o2oProjectPics/test2.jpg");
		try {
			shopService.modifyShop(new FileInputStream(f), shop, f.getName());
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	@Test
	public void getShopList()
	{

		PersonInfo owner=new PersonInfo();
		owner.setUserId(1L);
		ShopCategory shopCategory=new ShopCategory();
		shopCategory.setShopCategoryId(14L);
		Shop shop=new Shop();
		shop.setShopCategory(shopCategory);
		shop.setOwner(owner);
		shopService.getShopList(shop, 1, 6);
	}
}
