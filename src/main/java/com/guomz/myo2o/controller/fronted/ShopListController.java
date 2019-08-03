package com.guomz.myo2o.controller.fronted;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.guomz.myo2o.dto.ShopExecution;
import com.guomz.myo2o.entity.Area;
import com.guomz.myo2o.entity.Shop;
import com.guomz.myo2o.entity.ShopCategory;
import com.guomz.myo2o.enums.ShopStateEnum;
import com.guomz.myo2o.service.AreaService;
import com.guomz.myo2o.service.ShopCategoryService;
import com.guomz.myo2o.service.ShopService;
import com.guomz.myo2o.util.HttpServletRequestUtil;

/**
 * 用于返回商铺查询结果
 * @author 12587
 *
 */
@RestController
@RequestMapping("/fronted")
public class ShopListController {

	@Autowired
	private ShopCategoryService shopCategoryService;
	@Autowired
	private ShopService shopService;
	@Autowired
	private AreaService areaService;
	
	/**
	 * 返回区域列表与一级类别或一级类别下的二级类别
	 * @param req
	 * @return
	 */
	@RequestMapping(value="/listshopspageinfo",method=RequestMethod.GET)
	public Map<String,Object> listShopsPageInfo(HttpServletRequest req)
	{
		Map<String,Object> modelMap=new HashMap<String,Object>();
		Long parentId = HttpServletRequestUtil.getLong(req, "parentId");
		ShopCategory shopCategoryCondition=new ShopCategory();
		//如果指定一级类别则返回一级类别下的二级类别列表，否则只返回一级类别
		if(parentId!=null&&parentId>=1)
		{
			ShopCategory parent= new ShopCategory();
			parent.setShopCategoryId(parentId);
			shopCategoryCondition.setParent(parent);
		}
		else
		{
			shopCategoryCondition=null;
		}
		
		try {
			List<ShopCategory> shopCategoryList = shopCategoryService.getShopCategoryList(shopCategoryCondition);
			List<Area> areaList = areaService.getAreaList();
			modelMap.put("shopCategoryList", shopCategoryList);
			modelMap.put("areaList", areaList);
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
	 * 返回指定查询条件下的店铺列表，parentId与shopCategoryId不会同时存在
	 * @param req
	 * @return
	 */
	@RequestMapping(value="/listshops",method=RequestMethod.GET)
	public Map<String,Object> listShops(HttpServletRequest req)
	{
		Map<String,Object> modelMap=new HashMap<String,Object>();
		
		Integer pageSize = HttpServletRequestUtil.getInt(req, "pageSize");
		Integer pageIndex = HttpServletRequestUtil.getInt(req, "pageIndex");
		
		if(pageSize==null||pageIndex==null||pageSize<=0||pageIndex<1)
		{
			modelMap.put("success", false);
			modelMap.put("errMsg", "页码信息有误");
		}
		else
		{
			Long shopCategoryId = HttpServletRequestUtil.getLong(req, "shopCategoryId");
			Long parentId = HttpServletRequestUtil.getLong(req, "parentId");
			Integer areaId = HttpServletRequestUtil.getInt(req, "areaId");
			String shopName = HttpServletRequestUtil.getString(req, "shopName");
			
			try {
				Shop shopCondition=generateShopCondition(shopCategoryId, parentId, areaId, shopName);
				ShopExecution shopExecution = shopService.getShopList(shopCondition, pageIndex, pageSize);
				if(shopExecution.getState()==ShopStateEnum.SUCCESS.getState())
				{
					modelMap.put("success", true);
					modelMap.put("shopList", shopExecution.getShopList());
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
		}
		return modelMap;
	}
	
	/**
	 * 包装查询的shop条件
	 * @param shopCategoryId
	 * @param parentId
	 * @param areaId
	 * @param shopName
	 * @return
	 */
	private Shop generateShopCondition(Long shopCategoryId,Long parentId,Integer areaId,String shopName)
	{
		Shop shopCondition=new Shop();
		if(shopName!=null)
		{
			shopCondition.setShopName(shopName);
		}
		if(areaId!=null&&areaId>=1)
		{
			Area area=new Area();
			area.setAreaId(areaId);
			shopCondition.setArea(area);
		}
		if(shopCategoryId!=null)
		{
			ShopCategory shopCategory=new ShopCategory();
			shopCategory.setShopCategoryId(shopCategoryId);
			shopCondition.setShopCategory(shopCategory);
		}
		if(parentId!=null)
		{
			ShopCategory shopCategory=new ShopCategory();
			ShopCategory parent=new ShopCategory();
			parent.setShopCategoryId(parentId);
			shopCategory.setParent(parent);
			shopCondition.setShopCategory(shopCategory);
		}
		return shopCondition;
	}
}
