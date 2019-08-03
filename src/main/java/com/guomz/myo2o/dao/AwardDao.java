package com.guomz.myo2o.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.guomz.myo2o.entity.Award;

public interface AwardDao {

	public List<Award> queryAwardList(@Param(value="awardCondition")Award awardCondition,@Param(value="rowIndex")int rowIndex,@Param(value="pageSize")int pageSize);
	
	public int queryAwardCount(Award awardCondition);
	
	public Award queryAwardByAwardId(Long awardId);
	
	public int insertAward(Award award);
	
	public int updateAward(Award award);
	
	public int deleteAward(@Param(value="awardId")Long awardId,@Param(value="shopId")Long shopId);
}
