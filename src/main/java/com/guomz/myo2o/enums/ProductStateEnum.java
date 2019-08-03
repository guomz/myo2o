package com.guomz.myo2o.enums;

public enum ProductStateEnum {

	CHECK(0, "审核中"), OFFLINE(-1, "非法商品"), SUCCESS(1, "操作成功"), INNER_ERROR(-1001, "内部错误"), NULL_PRODUCT(-1002, "商品为空");

	private int state;
	private String stateInfo;

	private ProductStateEnum() {

	}

	/**
	 * 私有构造函数
	 * 
	 * @param state
	 * @param stateInfo
	 */
	private ProductStateEnum(int state, String stateInfo) {
		this.state = state;
		this.stateInfo = stateInfo;
	}

	/**
	 * 根据传入的状态值返回枚举对象
	 * 
	 * @param state
	 * @return
	 */
	public static ProductStateEnum stateOf(int state) {
		for (ProductStateEnum productStateEnum : values()) {
			if (productStateEnum.getState() == state) {
				return productStateEnum;
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
