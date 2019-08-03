package com.guomz.myo2o.dto;

import java.util.List;

import com.guomz.myo2o.entity.UserProductMap;
import com.guomz.myo2o.enums.UserProductMapStateEnum;

public class UserProductMapExecution {

	private Integer state;
	private String stateInfo;
	private List<UserProductMap> userProductMapList;
	private Integer count;
	
	public UserProductMapExecution()
	{
		
	}
	
	public UserProductMapExecution(UserProductMapStateEnum userProductMapStateEnum)
	{
		this.state=userProductMapStateEnum.getState();
		this.stateInfo=userProductMapStateEnum.getStateInfo();
	}
	
	public UserProductMapExecution(UserProductMapStateEnum userProductMapStateEnum,List<UserProductMap> userProductMapList)
	{
		this.state=userProductMapStateEnum.getState();
		this.stateInfo=userProductMapStateEnum.getStateInfo();
		this.userProductMapList=userProductMapList;
	}

	public Integer getState() {
		return state;
	}

	public void setState(Integer state) {
		this.state = state;
	}

	public String getStateInfo() {
		return stateInfo;
	}

	public void setStateInfo(String stateInfo) {
		this.stateInfo = stateInfo;
	}

	public List<UserProductMap> getUserProductMapList() {
		return userProductMapList;
	}

	public void setUserProductMapList(List<UserProductMap> userProductMapList) {
		this.userProductMapList = userProductMapList;
	}

	public Integer getCount() {
		return count;
	}

	public void setCount(Integer count) {
		this.count = count;
	}
	
	
}
