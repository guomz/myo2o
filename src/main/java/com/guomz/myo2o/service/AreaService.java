package com.guomz.myo2o.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.guomz.myo2o.dao.AreaDao;
import com.guomz.myo2o.entity.Area;

@Service
public class AreaService {

	@Autowired
	private AreaDao areaDao;
	
	@Autowired
	private RedisTemplate<String,Object> redisTemplate;
	
	private static String KEY="arealist";
	
	/**
	 * 返回地区列表
	 * @return
	 */
	@Transactional
	@SuppressWarnings("all")
	public List<Area> getAreaList()
	{
		if(redisTemplate.hasKey(KEY))
		{
			List<Area> areaList = (List<Area>) redisTemplate.opsForValue().get(KEY);
			return areaList;
		}
		else
		{
			List<Area> areaList = areaDao.queryArea();
			redisTemplate.opsForValue().set(KEY, areaList);
			return areaDao.queryArea(); 
		}
	}
}
