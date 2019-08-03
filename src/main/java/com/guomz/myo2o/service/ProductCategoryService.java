package com.guomz.myo2o.service;

import java.awt.color.ProfileDataException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.guomz.myo2o.dao.ProductCategoryDao;
import com.guomz.myo2o.dao.ProductDao;
import com.guomz.myo2o.dto.ProductCategoryExecution;
import com.guomz.myo2o.entity.ProductCategory;
import com.guomz.myo2o.enums.ProductCategoryStateEnum;
import com.guomz.myo2o.exceptions.ProductCategoryException;

@Service
public class ProductCategoryService {

	@Autowired
	private ProductCategoryDao productCategoryDao;
	@Autowired
	private ProductDao productDao;
	
	/**
	 * 根据店铺id返回该店铺的商品类别
	 * @param shopId
	 * @return
	 */
	public List<ProductCategory> getProductCategoryList(Long shopId)
	{
		return productCategoryDao.queryProductCategoryList(shopId);
	}
	
	/**
	 * 批量添加商品类别
	 * @param productCategoryList
	 * @return
	 */
	@Transactional
	public ProductCategoryExecution batchAddProductCategory(List<ProductCategory> productCategoryList)
	{
		if(productCategoryList==null||productCategoryList.size()==0)
		{
			return new ProductCategoryExecution(ProductCategoryStateEnum.EMPTY_LIST);
		}
		
		try {
			int result = productCategoryDao.batchInsertProductCategoryList(productCategoryList);
			if(result<=0)
			{
				return new ProductCategoryExecution(ProductCategoryStateEnum.INNER_ERROR);
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			throw new ProductCategoryException(e.getMessage());
		}
		
		return new ProductCategoryExecution(ProductCategoryStateEnum.SUCCESS, productCategoryList);
	}
	
	/**
	 * 删除商品类别
	 * @param productCategoryId
	 * @param shopId
	 * @return
	 */
	@Transactional
	public ProductCategoryExecution deleteProductCategory(Long productCategoryId,Long shopId)
	{
		try {
			//消除关联的商品的类别信息，置为空
			productDao.updateProductCategoryToNull(productCategoryId);
			int result = productCategoryDao.deleteProductCategory(productCategoryId, shopId);
			if(result<=0)
			{
				return new ProductCategoryExecution(ProductCategoryStateEnum.INNER_ERROR);
			}
			else
			{
				return new ProductCategoryExecution(ProductCategoryStateEnum.SUCCESS);
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			throw new ProfileDataException(e.getMessage());
		}
	}
	
}
