package com.guomz.myo2o.dao;

import java.util.List;

import com.guomz.myo2o.entity.ProductImg;

public interface ProductImgDao {

	/**
	 * 批量插入商品详情图
	 * @param productImgList
	 * @return
	 */
	public int batchInsertProductImg(List<ProductImg> productImgList);
	
	/**
	 * 删除商品详情图
	 * @param productId
	 * @return
	 */
	public int deleteProductImgByProductId(Long productId);
	
	/**
	 * 查询商品详情图
	 * @param productId
	 * @return
	 */
	public List<ProductImg> queryProductImgList(Long productId);
}
