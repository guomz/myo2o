package com.guomz.myo2o.controller.shopadmin;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.guomz.myo2o.dto.UserProductMapExecution;
import com.guomz.myo2o.entity.Product;
import com.guomz.myo2o.entity.Shop;
import com.guomz.myo2o.entity.UserProductMap;
import com.guomz.myo2o.enums.UserProductMapStateEnum;
import com.guomz.myo2o.service.UserProductMapService;

@RestController
@RequestMapping("/shopadmin")
public class UserProductMapController {

	@Autowired
	private UserProductMapService userProductMapService;
	
	/**
	 * 列出销量
	 * @return
	 */
	@RequestMapping(value="/listuserproductmapbyshop",method=RequestMethod.GET)
	public Map<String,Object> listUserProductMapByShop(HttpServletRequest req,@RequestParam(value="pageIndex",required=false)Integer pageIndex,@RequestParam(value="pageSize",required=false)Integer pageSize,@RequestParam(value="productName",required=false)String productName)
	{
		Map<String,Object> modelMap=new HashMap<String,Object>();
		Shop shop=(Shop) req.getSession().getAttribute("currentShop");
		if(shop!=null&&shop.getShopId()!=null&&pageIndex!=null&&pageSize!=null)
		{
			UserProductMap condition=new UserProductMap();
			condition.setShop(shop);
			if(productName!=null)
			{
				Product product=new Product();
				product.setProductName(productName);
				condition.setProduct(product);
			}
			try {
				UserProductMapExecution userProductMapExecution = userProductMapService.listUserProductMap(condition, pageIndex, pageSize);
				if(userProductMapExecution.getState()==UserProductMapStateEnum.SUCCESS.getState())
				{
					modelMap.put("success", true);
					modelMap.put("userProductMapList", userProductMapExecution.getUserProductMapList());
					modelMap.put("count", userProductMapExecution.getCount());
				}
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				modelMap.put("success", false);
				modelMap.put("errMsg", e.getMessage());
			}
			
		}
		else
		{
			modelMap.put("success", false);
			modelMap.put("errMsg", "信息不完整");
		}
		return modelMap;
	}
}
