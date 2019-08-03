package com.guomz.myo2o.dto;

import java.util.List;

import com.guomz.myo2o.entity.WechatAuth;
import com.guomz.myo2o.enums.WechatAuthStateEnum;

public class WechatAuthExecution {

	private int state;
	private String stateInfo;
	private WechatAuth wechatAuth;
	private int count;
	private List<WechatAuth> wechatAuthList;
	
	public WechatAuthExecution()
	{
		
	}
	
	public WechatAuthExecution(WechatAuthStateEnum wechatAuthStateEnum)
	{
		this.state=wechatAuthStateEnum.getState();
		this.stateInfo=wechatAuthStateEnum.getStateInfo();
	}
	
	public WechatAuthExecution(WechatAuthStateEnum wechatAuthStateEnum,WechatAuth wechatAuth)
	{
		this.state=wechatAuthStateEnum.getState();
		this.stateInfo=wechatAuthStateEnum.getStateInfo();
		this.wechatAuth=wechatAuth;
	}
	
	public WechatAuthExecution(WechatAuthStateEnum wechatAuthStateEnum,List<WechatAuth> wechatAuthList)
	{
		this.state=wechatAuthStateEnum.getState();
		this.stateInfo=wechatAuthStateEnum.getStateInfo();
		this.wechatAuthList=wechatAuthList;
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
	public WechatAuth getWechatAuth() {
		return wechatAuth;
	}
	public void setWechatAuth(WechatAuth wechatAuth) {
		this.wechatAuth = wechatAuth;
	}
	public int getCount() {
		return count;
	}
	public void setCount(int count) {
		this.count = count;
	}
	public List<WechatAuth> getWechatAuthList() {
		return wechatAuthList;
	}
	public void setWechatAuthList(List<WechatAuth> wechatAuthList) {
		this.wechatAuthList = wechatAuthList;
	}
	
	
}
