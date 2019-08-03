package com.guomz.myo2o.enums;

public enum ShopStateEnum {

	CHECK(0, "审核中"), OFFLINE(-1, "非法店铺"), SUCCESS(1, "操作成功"), INNER_ERROR(-1001, "内部错误"), NULL_SHOPID(-1002, "店铺id为空");

	private int state;
	private String stateInfo;

	private ShopStateEnum() {

	}

	/**
	 * 私有构造函数
	 * 
	 * @param state
	 * @param stateInfo
	 */
	private ShopStateEnum(int state, String stateInfo) {
		this.state = state;
		this.stateInfo = stateInfo;
	}

	/**
	 * 根据传入的状态值返回枚举对象
	 * 
	 * @param state
	 * @return
	 */
	public static ShopStateEnum stateOf(int state) {
		for (ShopStateEnum shopStateEnum : values()) {
			if (shopStateEnum.getState() == state) {
				return shopStateEnum;
			}
		}
		return null;
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

}
