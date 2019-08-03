package com.guomz.myo2o.controller.local;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/local")
public class LocalController {

	@RequestMapping("/accountbind")
	public String accountbind()
	{
		return "local/accountbind";
	}
	
	@RequestMapping("/login")
	public String login()
	{
		return "local/login";
	}
	
	@RequestMapping("/changepsw")
	public String changePws()
	{
		return "local/changepsw";
	}
}
