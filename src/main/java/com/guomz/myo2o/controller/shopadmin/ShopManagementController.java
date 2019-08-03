package com.guomz.myo2o.controller.shopadmin;

import java.io.IOException;
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
import com.guomz.myo2o.dto.ShopExecution;
import com.guomz.myo2o.entity.Area;
import com.guomz.myo2o.entity.PersonInfo;
import com.guomz.myo2o.entity.Shop;
import com.guomz.myo2o.entity.ShopCategory;
import com.guomz.myo2o.enums.ShopStateEnum;
import com.guomz.myo2o.service.AreaService;
import com.guomz.myo2o.service.ShopCategoryService;
import com.guomz.myo2o.service.ShopService;
import com.guomz.myo2o.util.HttpServletRequestUtil;
/**
 * 店铺管理相关操作
 * @author 12587
 *
 */
@RestController
@RequestMapping("/shopadmin")
public class ShopManagementController {

	@Autowired
	private ShopService shopService;
	@Autowired
	private ShopCategoryService shopCategoryService;
	@Autowired
	private AreaService areaService;
	
	/**
	 * 注册店铺之前获得区域列表和二级类别列表
	 * @return
	 */
	@RequestMapping(value="/getshopinitinfo",method=RequestMethod.GET)
	public Map<String,Object> getShopInitInfo()
	{
		Map<String,Object> modelMap=new HashMap<String,Object>();
		try {
			List<Area> areaList = areaService.getAreaList();
			List<ShopCategory> shopCategoryList = shopCategoryService.getShopCategoryList(new ShopCategory());
			modelMap.put("areaList", areaList);
			modelMap.put("shopCategoryList", shopCategoryList);
			modelMap.put("success", true);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			modelMap.put("success", false);
			modelMap.put("errMsg", e.getMessage());
		}
		return modelMap;
	}
	
	/**
	 * 店铺注册
	 * @param shopImg
	 * @param req
	 * @return
	 */
	@RequestMapping(value="/registershop",method=RequestMethod.POST)
	public Map<String,Object> registerShop(@RequestParam(value="shopImg",required=false)MultipartFile shopImg,HttpServletRequest req)
	{
		Map<String,Object> modelMap=new HashMap<String,Object>();
		String shopStr = HttpServletRequestUtil.getString(req, "shopStr");
		ObjectMapper objectMapper=new ObjectMapper();
		Shop shop=null;
		try {
			shop = objectMapper.readValue(shopStr, Shop.class);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			modelMap.put("success", false);
			modelMap.put("errMsg", e.getMessage());
		}
		
		//进行验证码的验证
		String verifyCodeActual = HttpServletRequestUtil.getString(req, "verifyCodeActual");
		String realCode = (String) req.getSession().getAttribute("code");
		System.out.println("realCode: "+realCode);
		System.out.println("your input: "+verifyCodeActual);
		if(verifyCodeActual.equals(realCode)==false)
		{
			modelMap.put("success", false);
			modelMap.put("errMsg", "验证码不正确");
			return modelMap;
		}
		
		if(shopImg==null)
		{
			modelMap.put("success", false);
			modelMap.put("errMsg", "请上传图片");
		}
		if(shop==null)
		{
			modelMap.put("success", false);
			modelMap.put("errMsg", "店铺信息不完整");
		}
		
		//添加店主信息
		PersonInfo owner=(PersonInfo) req.getSession().getAttribute("user");
		/*
		 * if(owner==null) { owner=new PersonInfo(); owner.setUserId(1l);
		 * req.getSession().setAttribute("user", owner); }
		 */
		shop.setOwner(owner);
		
		try {
			ShopExecution shopExecution = shopService.addShop(shopImg.getInputStream(), shop,shopImg.getOriginalFilename());
			if(shopExecution.getState()==ShopStateEnum.CHECK.getState())
			{
				//更新用户的店铺列表
				@SuppressWarnings("unchecked")
				List<Shop> shopList=(List<Shop>) req.getSession().getAttribute("shopList");
				if(shopList==null)
				{
					shopList=new ArrayList<Shop>();
				}
				shopList.add(shopExecution.getShop());
				req.getSession().setAttribute("shopList", shopList);
				modelMap.put("success", true);
			}
			else
			{
				modelMap.put("success", false);
				modelMap.put("errMsg", shopExecution.getStateInfo());
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			modelMap.put("success", false);
			modelMap.put("errMsg", e.getMessage());
		}
		
		
		return modelMap;
	}
	
	/**
	 * 根据id返回店铺信息
	 * @param req
	 * @return
	 */
	@RequestMapping(value="/getshopbyid",method=RequestMethod.GET)
	public Map<String,Object> getShopById(HttpServletRequest req)
	{
		Map<String,Object> modelMap=new HashMap<String,Object>();
		try {
			Long ShopId = HttpServletRequestUtil.getLong(req, "shopId");
			Shop shop = shopService.getShopById(ShopId);
			List<Area> areaList = areaService.getAreaList();
			modelMap.put("success", true);
			modelMap.put("shop", shop);
			modelMap.put("areaList", areaList);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			modelMap.put("success", false);
			modelMap.put("errMsg", e.getMessage());
		}
		return modelMap;
	}
	
	/**
	 * 修改店铺信息
	 * @param shopImg
	 * @param req
	 * @return
	 */
	@RequestMapping(value="/modifyshop",method=RequestMethod.POST)
	public Map<String,Object> modifyShop(@RequestParam(value="shopImg",required=false)MultipartFile shopImg,HttpServletRequest req)
	{
		Map<String,Object> modelMap=new HashMap<String,Object>();
		String shopStr = HttpServletRequestUtil.getString(req, "shopStr");
		ObjectMapper objectMapper=new ObjectMapper();
		Shop shop=null;
		try {
			shop = objectMapper.readValue(shopStr, Shop.class);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			modelMap.put("success", false);
			modelMap.put("errMsg", e.getMessage());
		}
		
		//进行验证码的验证
		String verifyCodeActual = HttpServletRequestUtil.getString(req, "verifyCodeActual");
		String realCode = (String) req.getSession().getAttribute("code");
		System.out.println("realCode: "+realCode);
		System.out.println("your input: "+verifyCodeActual);
		if(verifyCodeActual.equals(realCode)==false)
		{
			modelMap.put("success", false);
			modelMap.put("errMsg", "验证码不正确");
			return modelMap;
		}
		
		if(shop==null)
		{
			modelMap.put("success", false);
			modelMap.put("errMsg", "店铺信息不完整");
		}
				
		try {
			ShopExecution shopExecution = shopService.modifyShop(shopImg.getInputStream(), shop,shopImg.getOriginalFilename());
			if(shopExecution.getState()==ShopStateEnum.SUCCESS.getState())
			{
				//更新用户的店铺列表
				@SuppressWarnings("unchecked")
				List<Shop> shopList=(List<Shop>) req.getSession().getAttribute("shopList");
				if(shopList==null)
				{
					shopList=new ArrayList<Shop>();
				}
				shopList.add(shopExecution.getShop());
				req.getSession().setAttribute("shopList", shopList);
				modelMap.put("success", true);
			}
			else
			{
				modelMap.put("success", false);
				modelMap.put("errMsg", shopExecution.getStateInfo());
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			modelMap.put("success", false);
			modelMap.put("errMsg", e.getMessage());
		}
		
		
		return modelMap;
	}
	
	/**
	 * 根据用户信息返回该用户的店铺列表
	 * @param req
	 * @return
	 */
	@RequestMapping(value="getshoplist",method=RequestMethod.GET)
	public Map<String,Object> getShopList(HttpServletRequest req)
	{
		Map<String,Object> modelMap=new HashMap<String,Object>();
		PersonInfo owner = (PersonInfo) req.getSession().getAttribute("user");
		/*
		 * if(owner==null) { owner=new PersonInfo(); owner.setUserId(1L);
		 * owner.setUserName("test"); req.getSession().setAttribute("user", owner); }
		 */
		
		Shop shopCondition=new Shop();
		shopCondition.setOwner(owner);
		try {
			ShopExecution shopExecution = shopService.getShopList(shopCondition, 0, 100);
			modelMap.put("success", true);
			modelMap.put("shopList", shopExecution.getShopList());
			modelMap.put("user", owner);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			modelMap.put("success", false);
			modelMap.put("errMsg", e.getMessage());
		}
		
		return modelMap;
	}
	
	/**
	 * 用于对店铺信息管理页面访问的拦截
	 * @param req
	 * @return
	 */
	@RequestMapping(value="/getshopmanagementinfo",method=RequestMethod.GET)
	public Map<String,Object> getShopManagementInfo(HttpServletRequest req)
	{
		Map<String,Object> modelMap=new HashMap<String,Object>();
		Long shopId = HttpServletRequestUtil.getLong(req, "shopId");
		//当店铺id存在问题时检查session是否存在之前访问过的店铺
		if(shopId==null||shopId<=0)
		{
			Shop currentShop = (Shop) req.getSession().getAttribute("currentShop");
			if(currentShop==null)
			{
				modelMap.put("redirect", true);
				modelMap.put("url", "/myo2o/shopadmin/shoplist");
			}
			else
			{
				modelMap.put("redirect", false);
				modelMap.put("shopId", currentShop.getShopId());
			}
		}
		else
		{
			modelMap.put("redirect", false);
			Shop currentShop=new Shop();
			currentShop.setShopId(shopId);
			modelMap.put("shopId", currentShop.getShopId());
			req.getSession().setAttribute("currentShop", currentShop);
		}
		return modelMap;
	}
	
}
