package com.guomz.myo2o.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.guomz.myo2o.entity.Product;

public interface ProductDao {

	/**
	 * 插入商品
	 * @param product
	 * @return
	 */
	public int insertProduct(Product product);
	
	/**
	 * 根据id查询商品
	 * @param productId
	 * @return
	 */
	public Product queryProductById(Long productId);
	
	/**
	 * 更新商品信息
	 * @param product
	 * @return
	 */
	public int updateProduct(Product product);
	
	/**
	 * 查询商品列表
	 * @param product
	 * @param rowIndex
	 * @param pageSize
	 * @return
	 */
	List<Product> queryProductList(@Param("product")Product product,@Param("rowIndex")int rowIndex,@Param("pageSize")int pageSize);
	
	/**
	 * 查询商品数量
	 * @param product
	 * @return
	 */
	int queryProductCount(Product product);
	
	/**
	 * 删除商品类别时将该类别下的商品类别置null
	 * @param productCategoryId
	 * @return
	 */
	int updateProductCategoryToNull(@Param("productCategoryId")Long productCategoryId);
}
