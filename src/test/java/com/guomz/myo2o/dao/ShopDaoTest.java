package com.guomz.myo2o.dao;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.guomz.myo2o.entity.Area;
import com.guomz.myo2o.entity.PersonInfo;
import com.guomz.myo2o.entity.Shop;
import com.guomz.myo2o.entity.ShopCategory;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ShopDaoTest {

	@Autowired
	private ShopDao shopDao;
	
	@Test
	public void insertShop()
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
		shopDao.insertShop(shop);
		System.err.println(shop);
	}
	
	@Test
	public void updateShop()
	{
		Shop shop=new Shop();
		shop.setShopId(13L);
		shop.setShopName("testa");
		shop.setShopImg("/upload/items/shop/13/20190309203726167345.jpg");
		shopDao.updateShop(shop);
	}
	
	@Test
	public void queryShopById()
	{
		System.out.println(shopDao.queryShopById(1L));
	}
	
	@Test
	public void queryShopCount()
	{
		PersonInfo owner=new PersonInfo();
		owner.setUserId(1L);
		/*
		 * Area area=new Area(); area.setAreaId(4);
		 */
		ShopCategory shopCategory=new ShopCategory();
		shopCategory.setShopCategoryId(14L);
		Shop shop=new Shop();
		shop.setShopCategory(shopCategory);
		shop.setOwner(owner);
		//shop.setArea(area);
		System.out.println(shopDao.queryShopCount(shop));
	}
	
	@Test
	public void queryShopList()
	{
		PersonInfo owner=new PersonInfo();
		owner.setUserId(1L);
		ShopCategory shopCategory=new ShopCategory();
		shopCategory.setShopCategoryId(14L);
		Shop shop=new Shop();
		shop.setShopCategory(shopCategory);
		shop.setOwner(owner);
		System.out.println(shopDao.queryShopList(shop, 0, 15));
	}
}
