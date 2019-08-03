package com.guomz.myo2o.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.guomz.myo2o.dao.UserProductMapDao;
import com.guomz.myo2o.dto.UserProductMapExecution;
import com.guomz.myo2o.entity.UserProductMap;
import com.guomz.myo2o.enums.UserProductMapStateEnum;
import com.guomz.myo2o.util.PageCalculator;

@Service
public class UserProductMapService {

	@Autowired
	private UserProductMapDao userProductMapDao;
	
	/**
	 * 列出店铺下的商品销量
	 * @param condition
	 * @param pageIndex
	 * @param pageSize
	 * @return
	 */
	public UserProductMapExecution listUserProductMap(UserProductMap condition,Integer pageIndex,Integer pageSize)
	{
		if(condition!=null&&pageIndex!=null&&pageSize!=null)
		{
			int rowIndex=PageCalculator.calculateRowIndex(pageIndex, pageSize);
			try {
				List<UserProductMap> userProductMapList = userProductMapDao.queryUserProductMapList(condition, rowIndex, pageSize);
				int count = userProductMapDao.queryUserProductMapCount(condition);
				UserProductMapExecution userProductMapExecution=new UserProductMapExecution(UserProductMapStateEnum.SUCCESS,userProductMapList);
				userProductMapExecution.setCount(count);
				return userProductMapExecution;
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				return new UserProductMapExecution(UserProductMapStateEnum.INNER_ERROR);
			}
		}
		else
		{
			return new UserProductMapExecution(UserProductMapStateEnum.NULL_USERID);
		}
	}
}
