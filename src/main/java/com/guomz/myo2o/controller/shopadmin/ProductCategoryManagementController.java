package com.guomz.myo2o.controller.shopadmin;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.guomz.myo2o.dto.ProductCategoryExecution;
import com.guomz.myo2o.entity.ProductCategory;
import com.guomz.myo2o.entity.Shop;
import com.guomz.myo2o.enums.ProductCategoryStateEnum;
import com.guomz.myo2o.service.ProductCategoryService;

@RestController
@RequestMapping("/shopadmin")
public class ProductCategoryManagementController {

	@Autowired
	private ProductCategoryService productCategoryService;
	
	/**
	 * 返回店铺下的商品类别
	 * @param req
	 * @return
	 */
	@RequestMapping(value="/getproductcategorylist" ,method=RequestMethod.GET)
	public Map<String,Object> getProductCategoryList(HttpServletRequest req)
	{
		Map<String,Object> modelMap=new HashMap<String,Object>();
		Shop currentShop = (Shop) req.getSession().getAttribute("currentShop");
		try {
			List<ProductCategory> productCategoryList = productCategoryService.getProductCategoryList(currentShop.getShopId());
			modelMap.put("productCategoryList", productCategoryList);
			modelMap.put("success", true);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			modelMap.put("success", true);
			modelMap.put("errMsg", e.getMessage());
		}
		
		return modelMap;
	}
	
	/**
	 * 接收前端传递的商品类别，由于contenttype是json格式所以使用requsetbody注解
	 * @param productCategoryList
	 * @param req
	 * @return
	 */
	@RequestMapping(value="/addproductcategorys" ,method=RequestMethod.POST)
	public Map<String,Object> addProductCategorys(@RequestBody List<ProductCategory> productCategoryList,HttpServletRequest req)
	{
		Map<String,Object> modelMap=new HashMap<String,Object>();
		if(productCategoryList==null||productCategoryList.size()==0)
		{
			modelMap.put("success", false);
			modelMap.put("errMsg", "类别为空");
			return modelMap;
		}
		
		Shop shop = (Shop) req.getSession().getAttribute("currentShop");
		for(ProductCategory temp:productCategoryList)
		{
			temp.setShopId(shop.getShopId());
		}
		
		ProductCategoryExecution productCategoryExecution = productCategoryService.batchAddProductCategory(productCategoryList);
		if(productCategoryExecution.getState()==ProductCategoryStateEnum.SUCCESS.getState())
		{
			modelMap.put("success", true);
		}
		else
		{
			modelMap.put("success", false);
			modelMap.put("errMsg", productCategoryExecution.getStateInfo());
		}
		return modelMap;
	}
	
	/**
	 * 删除商品类别
	 * @param productCategoryId
	 * @param req
	 * @return
	 */
	@RequestMapping(value="/removeproductcategory",method=RequestMethod.POST)
	public Map<String,Object> removeProductCategory(Long productCategoryId,HttpServletRequest req)
	{
		Map<String,Object> modelMap=new HashMap<String,Object>();
		if(productCategoryId==null||productCategoryId<=0)
		{
			modelMap.put("success", false);
			modelMap.put("errMsg", "类别id不合法");
		}
		else
		{
			Shop shop = (Shop) req.getSession().getAttribute("currentShop");
			try {
				ProductCategoryExecution productCategoryExecution = productCategoryService.deleteProductCategory(productCategoryId, shop.getShopId());
				if(productCategoryExecution.getState()==ProductCategoryStateEnum.SUCCESS.getState())
				{
					modelMap.put("success", true);
				}
				else
				{
					modelMap.put("success", false);
					modelMap.put("errMsg", productCategoryExecution.getStateInfo());
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
	
}
