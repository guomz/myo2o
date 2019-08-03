package com.guomz.myo2o.controller.fronted;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/fronted")
public class FrontedController {

	@RequestMapping("/index")
	public String index()
	{
		return "fronted/index";
	}
	
	@RequestMapping("/shoplist")
	public String shoplist()
	{
		return "fronted/shoplist";
	}
	
	@RequestMapping("/shopdetail")
	public String shopdetail()
	{
		return "fronted/shopdetail";
	}
	
	@RequestMapping("/productdetail")
	public String productdetail()
	{
		return "fronted/productdetail";
	}
}
