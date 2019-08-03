package com.guomz.myo2o.controller.superadmin;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.guomz.myo2o.entity.Area;
import com.guomz.myo2o.service.AreaService;

@Controller
@RequestMapping("/superadmin")
public class AreaController {

	@Autowired
	private AreaService areaService;

	@RequestMapping(value = "/listarea", method = RequestMethod.GET)
	@ResponseBody
	public Map<String, Object> listArea() {
		Map<String, Object> modelMap = new HashMap<String, Object>();
		try {
			List<Area> areaList = areaService.getAreaList();
			modelMap.put("total", areaList.size());
			modelMap.put("row", areaList);
			modelMap.put("success", true);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			modelMap.put("success", false);
		}
		return modelMap;

	}
}
