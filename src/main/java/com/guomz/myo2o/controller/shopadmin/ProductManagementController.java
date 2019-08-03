package com.guomz.myo2o.controller.shopadmin;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.guomz.myo2o.dto.ProductExecution;
import com.guomz.myo2o.entity.Product;
import com.guomz.myo2o.entity.ProductCategory;
import com.guomz.myo2o.entity.Shop;
import com.guomz.myo2o.enums.ProductStateEnum;
import com.guomz.myo2o.service.ProductCategoryService;
import com.guomz.myo2o.service.ProductService;
import com.guomz.myo2o.util.HttpServletRequestUtil;

@RestController
@RequestMapping("/shopadmin")
public class ProductManagementController {

	@Autowired
	private ProductService productService;
	@Autowired
	private ProductCategoryService productCategoryService;

	@RequestMapping(value = "/addproduct", method = RequestMethod.POST)
	public Map<String, Object> addProduct(HttpServletRequest req,
			@RequestParam(value = "productImg", required = false) MultipartFile productImg,
			@RequestParam(value = "productImgList", required = false) MultipartFile[] productImgList) {
		Map<String, Object> modelMap = new HashMap<String, Object>();
		String productStr = HttpServletRequestUtil.getString(req, "productStr");
		ObjectMapper objectMapper = new ObjectMapper();
		Product product = null;
		try {
			product = objectMapper.readValue(productStr, Product.class);
			if (product == null) {
				modelMap.put("success", false);
				modelMap.put("errMsg", "店铺信息异常");
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			modelMap.put("success", false);
			modelMap.put("errMsg", e.getMessage());
		}

		// 进行验证码的验证
		String verifyCodeActual = HttpServletRequestUtil.getString(req, "verifyCodeActual");
		String realCode = (String) req.getSession().getAttribute("code");
		System.out.println("realCode: " + realCode);
		System.out.println("your input: " + verifyCodeActual);
		if (verifyCodeActual.equals(realCode) == false) {
			modelMap.put("success", false);
			modelMap.put("errMsg", "验证码不正确");
			return modelMap;
		}

		// 获得店铺信息
		Shop shop = (Shop) req.getSession().getAttribute("currentShop");
		product.setShop(shop);

		try {
			//对图片做非空判断
			if(productImg!=null&&productImgList!=null&&productImgList.length>0)
			{
				// 取得详情图名称
				List<String> productImgNameList = new ArrayList<String>();
				for (MultipartFile temp : productImgList) {
					productImgNameList.add(temp.getOriginalFilename());
				}
				// 取得输入流
				List<InputStream> pImgList = new ArrayList<InputStream>();
				for (MultipartFile temp : productImgList) {
					pImgList.add(temp.getInputStream());
				}
				
				ProductExecution productExecution = productService.addProduct(product, productImg.getInputStream(),
						productImg.getOriginalFilename(), pImgList, productImgNameList);
				if (productExecution.getState() == ProductStateEnum.SUCCESS.getState()) {
					modelMap.put("success", true);
				} else {
					modelMap.put("success", false);
					modelMap.put("errMsg", productExecution.getStateInfo());
				}
			}
			else
			{
				modelMap.put("success", false);
				modelMap.put("errMsg", "缺少图片信息");
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return modelMap;
	}

	/**
	 * 更新商品信息
	 * 
	 * @param req
	 * @param productImg
	 * @param productImgList
	 * @return
	 */
	@RequestMapping(value = "/modifyproduct", method = RequestMethod.POST)
	public Map<String, Object> modifyProduct(HttpServletRequest req,
			@RequestParam(value = "productImg", required = false) MultipartFile productImg,
			@RequestParam(value = "productImgList", required = false) MultipartFile[] productImgList,
			@RequestParam(value = "statusChange", required = false) Boolean statusChange) {
		Map<String, Object> modelMap = new HashMap<String, Object>();
		// 验证码判断
		if (statusChange == null || statusChange == false) {
			String verifyCodeActual = HttpServletRequestUtil.getString(req, "verifyCodeActual");
			String realCode = (String) req.getSession().getAttribute("code");
			if (!verifyCodeActual.equals(realCode)) {
				modelMap.put("success", false);
				modelMap.put("errMsg", "验证码错误");
				return modelMap;
			}
		}

		String productStr = HttpServletRequestUtil.getString(req, "productStr");
		ObjectMapper objectMapper = new ObjectMapper();
		Product product = null;
		try {
			product = objectMapper.readValue(productStr, Product.class);

		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			modelMap.put("success", false);
			modelMap.put("errMsg", e.getMessage());
		}

		// 获得店铺信息
		Shop shop = (Shop) req.getSession().getAttribute("currentShop");
		product.setShop(shop);
		try {
			//更新不需要强制详情图或者缩略图，但是详情图数组不会为null，会是空数组
			if(productImgList!=null&&productImgList.length>0&&productImg!=null)
			{
				// 取得详情图名称
				List<String> productImgNameList = new ArrayList<String>();
				for (MultipartFile temp : productImgList) {
					productImgNameList.add(temp.getOriginalFilename());
				}
				// 取得输入流
				List<InputStream> pImgList = new ArrayList<InputStream>();
				for (MultipartFile temp : productImgList) {
					pImgList.add(temp.getInputStream());
				}
				//更新操作
				ProductExecution productExecution = productService.modifyProduct(product, productImg.getInputStream(), productImg.getOriginalFilename(), pImgList, productImgNameList);
				if(productExecution.getState()==ProductStateEnum.SUCCESS.getState())
				{
					modelMap.put("success", true);
				}
				else
				{
					modelMap.put("success", false);
					modelMap.put("errMsg", productExecution.getStateInfo());
				}
			}
			else if(productImgList!=null&&productImgList.length==0&&productImg!=null)
			{
				//更新操作
				ProductExecution productExecution = productService.modifyProduct(product, productImg.getInputStream(), productImg.getOriginalFilename(), null, null);
				if(productExecution.getState()==ProductStateEnum.SUCCESS.getState())
				{
					modelMap.put("success", true);
				}
				else
				{
					modelMap.put("success", false);
					modelMap.put("errMsg", productExecution.getStateInfo());
				}
			}
			else if(productImgList!=null&&productImgList.length>0&&productImg==null)
			{
				// 取得详情图名称
				List<String> productImgNameList = new ArrayList<String>();
				for (MultipartFile temp : productImgList) {
					productImgNameList.add(temp.getOriginalFilename());
				}
				// 取得输入流
				List<InputStream> pImgList = new ArrayList<InputStream>();
				for (MultipartFile temp : productImgList) {
					pImgList.add(temp.getInputStream());
				}
				//更新操作
				ProductExecution productExecution = productService.modifyProduct(product, null, null, pImgList, productImgNameList);
				if(productExecution.getState()==ProductStateEnum.SUCCESS.getState())
				{
					modelMap.put("success", true);
				}
				else
				{
					modelMap.put("success", false);
					modelMap.put("errMsg", productExecution.getStateInfo());
				}
			}
			else if(productImgList!=null&&productImgList.length==0&&productImg==null)
			{
				//更新操作
				ProductExecution productExecution = productService.modifyProduct(product, null, null, null, null);
				if(productExecution.getState()==ProductStateEnum.SUCCESS.getState())
				{
					modelMap.put("success", true);
				}
				else
				{
					modelMap.put("success", false);
					modelMap.put("errMsg", productExecution.getStateInfo());
				}
			}
			//上下架更新情况，由于没有文件控件所以multipart数组为null
			else if(productImgList==null&&productImg==null)
			{
				//更新操作
				ProductExecution productExecution = productService.modifyProduct(product, null, null, null, null);
				if(productExecution.getState()==ProductStateEnum.SUCCESS.getState())
				{
					modelMap.put("success", true);
				}
				else
				{
					modelMap.put("success", false);
					modelMap.put("errMsg", productExecution.getStateInfo());
				}
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			modelMap.put("success", false);
			modelMap.put("errMsg", e.getMessage());
		}

		return modelMap;
	}

	/**
	 * 编辑商品之前使用
	 * 
	 * @param req
	 * @return
	 */
	@RequestMapping(value = "/getproductbyid", method = RequestMethod.GET)
	public Map<String, Object> getProductById(HttpServletRequest req) {
		Map<String, Object> modelMap = new HashMap<String, Object>();
		Long productId = HttpServletRequestUtil.getLong(req, "productId");
		try {
			Product product = productService.getProductById(productId);
			Shop shop = (Shop) req.getSession().getAttribute("currentShop");
			List<ProductCategory> productCategoryList = productCategoryService.getProductCategoryList(shop.getShopId());
			modelMap.put("productCategoryList", productCategoryList);
			modelMap.put("success", true);
			modelMap.put("product", product);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			modelMap.put("success", false);
			modelMap.put("errMsg", e.getMessage());
		}
		return modelMap;
	}
	
	@RequestMapping(value="/getproductlistbyshop",method=RequestMethod.GET)
	public Map<String,Object> getProductListByShop(HttpServletRequest req,Integer pageIndex,Integer pageSize,String productName,Long productCategoryId)
	{
		//获得商品列表
		Map<String,Object> modelMap=new HashMap<String,Object>();
		Shop currentShop=(Shop) req.getSession().getAttribute("currentShop");
		if((pageIndex==null||pageIndex<0)||(pageSize==null||pageSize<=0))
		{
			modelMap.put("success", false);
			modelMap.put("errMsg", "页码信息不正确");
			return modelMap;
		}
		Product product=new Product();
		product.setShop(currentShop);
		if(productName!=null&&productName.equals("")==false)
		{
			product.setProductName(productName);
		}
		if(productCategoryId!=null&&productCategoryId>0)
		{
			ProductCategory productCategory=new ProductCategory();
			productCategory.setProductCategoryId(productCategoryId);
			product.setProductCategory(productCategory);
		}
		try {
			ProductExecution result = productService.getProductList(product, pageIndex, pageSize);
			if(result.getState()==ProductStateEnum.SUCCESS.getState())
			{
				modelMap.put("success", true);
				modelMap.put("productList", result.getproductList());
			}
			else
			{
				modelMap.put("success", false);
				modelMap.put("errMsg", result.getStateInfo());
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			modelMap.put("success", false);
			modelMap.put("errMsg", e.getMessage());
		}
		return modelMap;
	}
}
