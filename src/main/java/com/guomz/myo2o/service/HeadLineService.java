package com.guomz.myo2o.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.guomz.myo2o.dao.HeadLineDao;
import com.guomz.myo2o.entity.HeadLine;

@Service
public class HeadLineService {

	@Autowired
	private HeadLineDao headLineDao;
	@Autowired
	private RedisTemplate<String,Object> redisTemplate;
	private static String KEY="headlinelist";
	
	/**
	 * 返回status为1的头条列表
	 * @param headLineCondition
	 * @return
	 */
	@Transactional
	@SuppressWarnings("all")
	public List<HeadLine> getHeadLineList(HeadLine headLineCondition)
	{
		//为不同的查询条件设置不同的key
		if(headLineCondition!=null&&headLineCondition.getEnableStatus()!=null)
		{
			KEY=KEY+"_"+headLineCondition.getEnableStatus();
		}
		
		if(redisTemplate.hasKey(KEY))
		{
			List<HeadLine> headLineList = (List<HeadLine>) redisTemplate.opsForValue().get(KEY);
			return headLineList;
		}
		else
		{
			List<HeadLine> headLineList = headLineDao.queryHeadLine(headLineCondition);
			redisTemplate.opsForValue().set(KEY, headLineList);
			return headLineList;
		}
	}
}
