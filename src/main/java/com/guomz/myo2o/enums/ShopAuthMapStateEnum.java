package com.guomz.myo2o.enums;

public enum ShopAuthMapStateEnum {

	SUCCESS(1, "操作成功"), INNER_ERROR(-1001, "内部错误"), NULL_SHOPAUTHID(-1002, "授权id为空");
	private int state;
	private String stateInfo;
	private ShopAuthMapStateEnum(int state, String stateInfo) {
		this.state = state;
		this.stateInfo = stateInfo;
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
	
	public static ShopAuthMapStateEnum stateOf(int state)
	{
		for(ShopAuthMapStateEnum temp:values())
		{
			if(temp.getState()==state)
			{
				return temp;
			}
		}
		return null;
	}
}
