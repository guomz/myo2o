package com.guomz.myo2o.enums;

public enum UserProductMapStateEnum {

	SUCCESS(1, "操作成功"), INNER_ERROR(-1001, "内部错误"), NULL_USERID(-1002, "id为空");
	
	private Integer state;
	private String stateInfo;
	
	private UserProductMapStateEnum(Integer state,String stateInfo)
	{
		this.state=state;
		this.stateInfo=stateInfo;
	}
	
	public static UserProductMapStateEnum stateOf(int state)
	{
		for(UserProductMapStateEnum temp:values())
		{
			if(temp.getState()==state)
			{
				return temp;
			}
		}
		return null;
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
	
	
}
