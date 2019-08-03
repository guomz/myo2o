package com.guomz.myo2o.controller.fronted;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.guomz.myo2o.entity.Product;
import com.guomz.myo2o.service.ProductService;
import com.guomz.myo2o.util.HttpServletRequestUtil;

@RestController
@RequestMapping("/fronted")
public class ProductDetailController {

	@Autowired
	private ProductService productService;
	
	/**
	 * 返回商品详情信息
	 * @param req
	 * @return
	 */
	@RequestMapping(value="/listproductdetailpageinfo",method=RequestMethod.GET)
	public Map<String,Object> listProductDetailPageInfo(HttpServletRequest req)
	{
		Map<String,Object> modelMap=new HashMap<String,Object>();
		Long productId = HttpServletRequestUtil.getLong(req, "productId");
		if(productId==null||productId<=0)
		{
			modelMap.put("success",false);
			modelMap.put("errMsg", "商品Id不合法");
		}
		else
		{
			try {
				Product product = productService.getProductById(productId);
				modelMap.put("success", true);
				modelMap.put("product", product);
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
