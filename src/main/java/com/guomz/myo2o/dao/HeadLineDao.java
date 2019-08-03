package com.guomz.myo2o.dao;

import java.util.List;

import com.guomz.myo2o.entity.HeadLine;

public interface HeadLineDao {

	/**
	 * 查询头条
	 * @param headLineCondition
	 * @return
	 */
	public List<HeadLine> queryHeadLine(HeadLine headLineCondition);
}
