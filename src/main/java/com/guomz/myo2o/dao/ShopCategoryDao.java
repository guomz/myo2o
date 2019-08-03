package com.guomz.myo2o.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.guomz.myo2o.entity.ShopCategory;

public interface ShopCategoryDao {

	public List<ShopCategory> queryShopCategory(@Param(value="shopCategory")ShopCategory shopCategory);
}
