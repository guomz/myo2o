package com.guomz.myo2o.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.guomz.myo2o.entity.ProductCategory;

public interface ProductCategoryDao {

	/**
	 * 根据店铺id返回其下的商品类别
	 * @param shopId
	 * @return
	 */
	public List<ProductCategory> queryProductCategoryList(Long shopId);
	
	/**
	 * 批量添加商品类别
	 * @param productCategoryList
	 * @return
	 */
	public int batchInsertProductCategoryList(List<ProductCategory> productCategoryList);
	
	/**
	 * 删除商品类别
	 * @param productCategoryId
	 * @param shopId
	 * @return
	 */
	public int deleteProductCategory(@Param("productCategoryId")Long productCategoryId,@Param("shopId")Long shopId);
}
