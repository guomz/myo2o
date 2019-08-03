package com.guomz.myo2o.dto;

import java.util.List;

import com.guomz.myo2o.entity.LocalAuth;
import com.guomz.myo2o.enums.LocalAuthStateEnum;

public class LocalAuthExecution {

	private int state;
	private String stateInfo;
	private LocalAuth localAuth;
	private List<LocalAuth> localAuthList;
	
	public LocalAuthExecution()
	{
		
	}
	
	public LocalAuthExecution(LocalAuthStateEnum localAuthStateEnum)
	{
		this.state=localAuthStateEnum.getState();
		this.stateInfo=localAuthStateEnum.getStateInfo();
	}
	
	public LocalAuthExecution(LocalAuthStateEnum localAuthStateEnum,LocalAuth localAuth)
	{
		this.state=localAuthStateEnum.getState();
		this.stateInfo=localAuthStateEnum.getStateInfo();
		this.localAuth=localAuth;
	}
	
	public LocalAuthExecution(LocalAuthStateEnum localAuthStateEnum,List<LocalAuth> localAuthList)
	{
		this.state=localAuthStateEnum.getState();
		this.stateInfo=localAuthStateEnum.getStateInfo();
		this.localAuthList=localAuthList;
	}

	public int getState() {
		return state;
	}

	public void setState(int state) {
		this.state = state;
	}

	public String getStateInfo() {
		return stateInfo;
	}

	public void setStateInfo(String stateInfo) {
		this.stateInfo = stateInfo;
	}

	public LocalAuth getLocalAuth() {
		return localAuth;
	}

	public void setLocalAuth(LocalAuth localAuth) {
		this.localAuth = localAuth;
	}

	public List<LocalAuth> getLocalAuthList() {
		return localAuthList;
	}

	public void setLocalAuthList(List<LocalAuth> localAuthList) {
		this.localAuthList = localAuthList;
	}
	
	
}
