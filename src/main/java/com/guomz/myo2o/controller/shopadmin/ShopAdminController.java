package com.guomz.myo2o.controller.shopadmin;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * 店铺操作路由
 * @author 12587
 *
 */
@Controller
@RequestMapping(value="/shopadmin",method=RequestMethod.GET)
public class ShopAdminController {

	@RequestMapping("/shopoperation")
	public String shopOperation()
	{
		return"shop/shopoperation";
	}
	
	@RequestMapping(value="/shoplist",method=RequestMethod.GET)
	public String shopList()
	{
		return "shop/shoplist";
	}
	
	@RequestMapping(value="/shopmanage" ,method=RequestMethod.GET)
	public String shopManage()
	{
		return "shop/shopmanage";
	}
	
	@RequestMapping(value="/productcategorymanage",method=RequestMethod.GET)
	public String productCategoryManage()
	{
		return "shop/productcategorymanage";
	}
	
	@RequestMapping(value="/productoperation",method=RequestMethod.GET)
	public String productOperation()
	{
		return "shop/productoperation";
	}
	
	@RequestMapping(value="/productmanage",method=RequestMethod.GET)
	public String productManage()
	{
		return "shop/productmanage";
	}
	
	@RequestMapping(value="/shopauthmanage",method=RequestMethod.GET)
	public String shopAuthManage()
	{
		return "shop/shopauthmanage";
	}
	
	@RequestMapping(value="/shopauthedit",method=RequestMethod.GET)
	public String shopAuthEdit()
	{
		return "shop/shopauthedit";
	}
	
	@RequestMapping(value="/operationsuccess",method=RequestMethod.GET)
	public String operationSuccess()
	{
		return "shop/operationsuccess";
	}
	
	@RequestMapping(value="/operationfail",method=RequestMethod.GET)
	public String operationFail()
	{
		return "shop/operationfail";
	}
	
	@RequestMapping(value="/productbuycheck",method=RequestMethod.GET)
	public String productBuyCheck()
	{
		return "shop/productbuycheck";
	}
}
