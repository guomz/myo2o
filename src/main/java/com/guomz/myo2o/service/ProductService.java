package com.guomz.myo2o.service;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.guomz.myo2o.dao.ProductDao;
import com.guomz.myo2o.dao.ProductImgDao;
import com.guomz.myo2o.dto.ProductExecution;
import com.guomz.myo2o.entity.Product;
import com.guomz.myo2o.entity.ProductImg;
import com.guomz.myo2o.enums.ProductStateEnum;
import com.guomz.myo2o.exceptions.ProductException;
import com.guomz.myo2o.util.ImgUtil;
import com.guomz.myo2o.util.PageCalculator;
import com.guomz.myo2o.util.PathUtil;

@Service
public class ProductService {
	
	@Autowired
	private ProductDao productDao;
	@Autowired
	private ProductImgDao productImgDao;
	
	/**
	 * 添加商品
	 * @param product
	 * @param productImg
	 * @param productImgName
	 * @param productImgList
	 * @return
	 */
	@Transactional
	public ProductExecution addProduct(Product product,InputStream productImg,String productImgName,List<InputStream> productImgList,List<String> productImgListName)
	{
		if(product==null)
		{
			return new ProductExecution(ProductStateEnum.NULL_PRODUCT);
		}
		
		product.setCreateTime(new Date());
		product.setLastEditTime(new Date());
		product.setEnableStatus(1);
		
		//添加商品缩略图
		if(productImg!=null)
		{
			addProductImg(product,productImg,productImgName);
		}
		
		try {
			int result = productDao.insertProduct(product);
			if(result<=0)
			{
				return new ProductExecution(ProductStateEnum.INNER_ERROR);
			}
			else
			{
				//添加详情图
				if(productImgList!=null&&productImgList.size()>0&&productImgListName!=null&&productImgListName.size()==productImgList.size())
				{
					addProductImgList(product,productImgList,productImgListName);
				}
				return new ProductExecution(ProductStateEnum.SUCCESS, product);
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			throw new ProductException(e.getMessage());
		}
	}

	/**
	 * 更新商品信息
	 * @param product
	 * @param productImg
	 * @param productImgName
	 * @param productImgList
	 * @param productImgListName
	 * @return
	 */
	@Transactional
	public ProductExecution modifyProduct(Product product,InputStream productImg,String productImgName,List<InputStream> productImgList,List<String> productImgListName)
	{
		if(product!=null&&product.getShop()!=null&&product.getShop().getShopId()!=null)
		{
			product.setLastEditTime(new Date());
			//更新缩略图
			if(productImg!=null)
			{
				Product preproduct = productDao.queryProductById(product.getProductId());
				//清除之前的缩略图
				if(preproduct.getImgAddr()!=null)
				{
					ImgUtil.deleteImg(preproduct.getImgAddr());
				}
				addProductImg(product, productImg, productImgName);
			}
			
			//更新详情图
			if(productImgList!=null&&productImgList.size()>0)
			{
				deleteProductImg(product);
				addProductImgList(product, productImgList, productImgListName);
			}
			
			try {
				int result = productDao.updateProduct(product);
				if(result<=0)
				{
					return new ProductExecution(ProductStateEnum.INNER_ERROR);
				}
				
				
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				throw new ProductException(e.getMessage());
			}
			return new ProductExecution(ProductStateEnum.SUCCESS, product);
			
		}
		else
		{
			return new ProductExecution(ProductStateEnum.NULL_PRODUCT);
		}
	}
	
	/*
	 * 精确查询商品
	 */
	public Product getProductById(Long productId)
	{
		return productDao.queryProductById(productId);
	}
	
	/**
	 * 分页查询商品
	 * @param product
	 * @param pageIndex
	 * @param pageSize
	 * @return
	 */
	public ProductExecution getProductList(Product product,int pageIndex,int pageSize)
	{
		ProductExecution result=new ProductExecution();
		int rowIndex = PageCalculator.calculateRowIndex(pageIndex, pageSize);
		try {
			List<Product> productList = productDao.queryProductList(product, rowIndex, pageSize);
			int count=productDao.queryProductCount(product);
			result.setCount(count);
			result.setproductList(productList);
			result.setState(ProductStateEnum.SUCCESS.getState());
			return result;
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return new ProductExecution(ProductStateEnum.INNER_ERROR);
		}
		
	}
	
	/**
	 * 删除商品详情图
	 * @param product
	 */
	private void deleteProductImg(Product product) {
		// TODO Auto-generated method stub
		
		List<ProductImg> productImgList = productImgDao.queryProductImgList(product.getProductId());
		for(ProductImg temp:productImgList)
		{
			ImgUtil.deleteImg(temp.getImgAddr());
		}
		productImgDao.deleteProductImgByProductId(product.getProductId());
	}

	/**
	 * 添加商品详情图
	 * @param product
	 * @param productImgList
	 * @param productImgListName 
	 */
	private void addProductImgList(Product product, List<InputStream> productImgList, List<String> productImgListName) {
		// TODO Auto-generated method stub
		String targetAddr = PathUtil.getShopImgPath(product.getShop().getShopId());
		List<ProductImg> pimglist=new ArrayList<ProductImg>();
		for(int i=0;i<productImgList.size();i++)
		{
			String relativeAddr = ImgUtil.generateThumbnail(productImgList.get(i), targetAddr, productImgListName.get(i));
			ProductImg productImg=new ProductImg();
			productImg.setImgAddr(relativeAddr);
			productImg.setProductId(product.getProductId());
			productImg.setCreateTime(new Date());
			pimglist.add(productImg);
		}
		try {
			int result = productImgDao.batchInsertProductImg(pimglist);
			if(result<=0)
			{
				throw new ProductException("添加失败");
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			throw new ProductException(e.getMessage());
		}
	}

	/**
	 * 添加商品缩略图
	 * @param product
	 * @param productImg
	 * @param productImgName
	 */
	private void addProductImg(Product product, InputStream productImg, String productImgName) {
		// TODO Auto-generated method stub
		String targetAddr = PathUtil.getShopImgPath(product.getShop().getShopId());
		String relativeAddr = ImgUtil.generateThumbnail(productImg, targetAddr, productImgName);
		product.setImgAddr(relativeAddr);
	}
}
