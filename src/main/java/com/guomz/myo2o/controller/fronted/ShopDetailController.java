package com.guomz.myo2o.controller.fronted;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.guomz.myo2o.dto.ProductExecution;
import com.guomz.myo2o.entity.Product;
import com.guomz.myo2o.entity.ProductCategory;
import com.guomz.myo2o.entity.Shop;
import com.guomz.myo2o.enums.ProductCategoryStateEnum;
import com.guomz.myo2o.service.ProductCategoryService;
import com.guomz.myo2o.service.ProductService;
import com.guomz.myo2o.service.ShopService;
import com.guomz.myo2o.util.HttpServletRequestUtil;

/**
 * 显示某店铺下的店铺类别与商品
 * @author 12587
 *
 */
@RestController
@RequestMapping("/fronted")
public class ShopDetailController {

	@Autowired
	private ShopService shopService;
	@Autowired
	private ProductCategoryService productCategoryService;
	@Autowired
	private ProductService productService;
	
	/**
	 * 返回店铺信息与店铺下的商品类别
	 * @param req
	 * @return
	 */
	@RequestMapping(value="/listshopdetailpageinfo",method=RequestMethod.GET)
	public Map<String,Object> listShopDetailPageInfo(HttpServletRequest req)
	{
		Map<String,Object> modelMap=new HashMap<String,Object>();
		Long shopId = HttpServletRequestUtil.getLong(req, "shopId");
		if(shopId==null||shopId<=0)
		{
			modelMap.put("success", false);
			modelMap.put("errMsg", "店铺id不合法");
		}
		else
		{
			try {
				Shop shop = shopService.getShopById(shopId);
				List<ProductCategory> productCategoryList = productCategoryService.getProductCategoryList(shopId);
				modelMap.put("shop", shop);
				modelMap.put("productCategoryList", productCategoryList);
				modelMap.put("success", true);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				modelMap.put("success", false);
				modelMap.put("errMsg", e.getMessage());
			}
			
		}
		return modelMap;
	}
	
	/**
	 * 返回店铺下的商品列表
	 * @param req
	 * @return
	 */
	@RequestMapping(value="/listproductsbyshop",method=RequestMethod.GET)
	public Map<String,Object> listProductsByShop(HttpServletRequest req)
	{
		Map<String,Object> modelMap=new HashMap<String,Object>();
		Integer pageIndex = HttpServletRequestUtil.getInt(req, "pageIndex");
		Integer pageSize = HttpServletRequestUtil.getInt(req, "pageSize");
		Long shopId = HttpServletRequestUtil.getLong(req, "shopId");
		
		if(pageIndex==null||pageIndex<=0||pageSize==null||pageSize<=0||shopId==null||shopId<=0)
		{
			modelMap.put("success", false);
			modelMap.put("errMsg", "页码或店铺id不合法");
		}
		else
		{
			String productName = HttpServletRequestUtil.getString(req, "productName");
			Long productCategoryId = HttpServletRequestUtil.getLong(req, "productCategoryId");
			Product productCondition=generateProductCondition(shopId, productCategoryId, productName);
			try {
				ProductExecution productExecution = productService.getProductList(productCondition, pageIndex, pageSize);
				if(productExecution.getState()==ProductCategoryStateEnum.SUCCESS.getState())
				{
					modelMap.put("success", true);
					modelMap.put("productList", productExecution.getproductList());
					modelMap.put("count", productExecution.getCount());
				}
				else
				{
					modelMap.put("success", false);
					modelMap.put("errMsg", productExecution.getStateInfo());
				}
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				modelMap.put("success", false);
				modelMap.put("errMsg", e.getMessage());
			}
			
		}
		
		return modelMap;
	}
	
	/**
	 * 组合店铺查询条件
	 * @param shopId
	 * @param pageIndex
	 * @param pageSize
	 * @param productCategoryId
	 * @param productName
	 * @return
	 */
	private Product generateProductCondition(Long shopId,Long productCategoryId,String productName)
	{
		Product productCondition=new Product();
		Shop shop=new Shop();
		shop.setShopId(shopId);
		productCondition.setShop(shop);
		
		if(productCategoryId!=null&&productCategoryId>0)
		{
			ProductCategory productCategory=new ProductCategory();
			productCategory.setProductCategoryId(productCategoryId);
			productCondition.setProductCategory(productCategory);
		}
		if(productName!=null)
		{
			productCondition.setProductName(productName);
		}
		
		return productCondition;
	}
}
