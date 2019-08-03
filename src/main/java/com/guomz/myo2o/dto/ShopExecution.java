package com.guomz.myo2o.dto;

import java.util.List;

import com.guomz.myo2o.entity.Shop;
import com.guomz.myo2o.enums.ShopStateEnum;

public class ShopExecution {

	private int state;
	private String stateInfo;
	private Shop shop;
	private List<Shop> shopList;
	private int count;
	
	public ShopExecution() {
		
	}
	
	/**
	 * 失败构造器
	 * @param shopStateEnum
	 */
	public ShopExecution(ShopStateEnum shopStateEnum)
	{
		this.state=shopStateEnum.getState();
		this.stateInfo=shopStateEnum.getStateInfo();
	}
	
	/**
	 * 成功构造器
	 * @param shopStateEnum
	 * @param shop
	 */
	public ShopExecution(ShopStateEnum shopStateEnum,Shop shop)
	{
		this.state=shopStateEnum.getState();
		this.stateInfo=shopStateEnum.getStateInfo();
		this.shop=shop;
	}
	
	/**
	 * 成功构造器
	 * @param shopStateEnum
	 * @param shopList
	 */
	public ShopExecution(ShopStateEnum shopStateEnum,List<Shop> shopList)
	{
		this.state=shopStateEnum.getState();
		this.stateInfo=shopStateEnum.getStateInfo();
		this.shopList=shopList;
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

	public Shop getShop() {
		return shop;
	}

	public void setShop(Shop shop) {
		this.shop = shop;
	}

	public List<Shop> getShopList() {
		return shopList;
	}

	public void setShopList(List<Shop> shopList) {
		this.shopList = shopList;
	}

	public int getCount() {
		return count;
	}

	public void setCount(int count) {
		this.count = count;
	}
	
}
