package com.guomz.myo2o.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.guomz.myo2o.dao.ShopCategoryDao;
import com.guomz.myo2o.entity.ShopCategory;

@Service
public class ShopCategoryService {

	@Autowired
	private ShopCategoryDao shopCategoryDao;
	@Autowired
	private RedisTemplate<String,Object> redisTemplate;
	private static String KEY="shopcategorylist";
	
	/**
	 * 返回商品类别列表，当传入对象为空则返回所有一级类别
	 * 对象不为空则返回所有二级类别
	 * 对象parent不为空则返回指定一级类别下的二级类别
	 * @param shopCategory
	 * @return
	 */
	@Transactional
	@SuppressWarnings("all")
	public List<ShopCategory> getShopCategoryList(ShopCategory shopCategory)
	{
		if(shopCategory==null)
		{
			KEY=KEY+"_allfirstlevel";
		}
		else if(shopCategory!=null&&shopCategory.getParent()!=null&&shopCategory.getParent().getShopCategoryId()!=null)
		{
			KEY=KEY+"_parent"+shopCategory.getParent().getShopCategoryId();
		}
		else if(shopCategory!=null&&shopCategory.getParent()==null)
		{
			KEY=KEY+"_allsecondlevel";
		}
		
		if(redisTemplate.hasKey(KEY))
		{
			List<ShopCategory> shopCategoryList = (List<ShopCategory>) redisTemplate.opsForValue().get(KEY);
			return shopCategoryList;
		}
		else
		{
			List<ShopCategory> shopCateogryList = shopCategoryDao.queryShopCategory(shopCategory);
			redisTemplate.opsForValue().set(KEY, shopCateogryList);
			return shopCateogryList;
		}
	}
}
