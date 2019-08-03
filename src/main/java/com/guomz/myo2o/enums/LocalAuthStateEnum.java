package com.guomz.myo2o.enums;

public enum LocalAuthStateEnum {

	SUCCESS(1,"操作成功"),NULL_AUTH_INFO(-1001,"账号信息不完整"),ONLY_ONE_ACCOUNT(-1002,"账号已经绑定"),INNER_ERROR(-1003,"内部错误");
	
	private int state;
	private String stateInfo;
	
	private LocalAuthStateEnum(int state,String stateInfo)
	{
		this.state=state;
		this.stateInfo=stateInfo;
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
	
	public static LocalAuthStateEnum stateOf(int state)
	{
		for(LocalAuthStateEnum temp: values())
		{
			if(temp.getState()==state)
			{
				return temp;
			}
		}
		return null;
	}
}
