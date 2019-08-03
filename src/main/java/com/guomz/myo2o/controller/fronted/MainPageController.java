package com.guomz.myo2o.controller.fronted;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.guomz.myo2o.entity.HeadLine;
import com.guomz.myo2o.entity.ShopCategory;
import com.guomz.myo2o.service.HeadLineService;
import com.guomz.myo2o.service.ShopCategoryService;

@RestController
@RequestMapping("/fronted")
public class MainPageController {

	@Autowired
	private ShopCategoryService shopCategoryService;
	@Autowired
	private HeadLineService headLineService;
	
	/**
	 * 获取一级类别与头条
	 * @return
	 */
	@RequestMapping(value="/listmainpageinfo",method=RequestMethod.GET)
	public Map<String ,Object> listMainPageInfo()
	{
		Map<String,Object> modelMap=new HashMap<String,Object>();
		List<ShopCategory> shopCategoryList=null;
		try {
			shopCategoryList = shopCategoryService.getShopCategoryList(null);
			modelMap.put("shopCategoryList", shopCategoryList);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			modelMap.put("success", false);
			modelMap.put("errMsg", e.getMessage());
		}
		
		HeadLine headLine=new HeadLine();
		headLine.setEnableStatus(1);
		try {
			List<HeadLine> headLineList = headLineService.getHeadLineList(headLine);
			if(headLineList==null||shopCategoryList==null)
			{
				modelMap.put("success", false);
				modelMap.put("errMsg", "内部错误");
			}
			else
			{
				modelMap.put("success", true);
				modelMap.put("headLineList", headLineList);
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			modelMap.put("success", false);
			modelMap.put("errMsg", e.getMessage());
		}
		
		//headLineService.getHeadLineList();
		return modelMap;
	}
}
