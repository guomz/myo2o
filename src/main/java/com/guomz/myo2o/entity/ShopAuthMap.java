package com.guomz.myo2o.entity;

import java.util.Date;

/**
 * 店家对店员的授权
 * @author 12587
 *
 */
public class ShopAuthMap {

	private Long shopAuthId;
	//店员的职称名
	private String title;
	//职称符号0店家1店员
	private Integer titleFlag;
	//授权状态0不可用1可用
	private Integer enableStatus;
	private Date createTime;
	private Date lastEditTime;
	//店员
	private PersonInfo employee;
	private Shop shop;
	public Long getShopAuthId() {
		return shopAuthId;
	}
	public void setShopAuthId(Long shopAuthId) {
		this.shopAuthId = shopAuthId;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public Integer getTitleFlag() {
		return titleFlag;
	}
	public void setTitleFlag(Integer titleFlag) {
		this.titleFlag = titleFlag;
	}
	public Integer getEnableStatus() {
		return enableStatus;
	}
	public void setEnableStatus(Integer enableStatus) {
		this.enableStatus = enableStatus;
	}
	public Date getCreateTime() {
		return createTime;
	}
	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}
	public Date getLastEditTime() {
		return lastEditTime;
	}
	public void setLastEditTime(Date lastEditTime) {
		this.lastEditTime = lastEditTime;
	}
	public PersonInfo getEmployee() {
		return employee;
	}
	public void setEmployee(PersonInfo employee) {
		this.employee = employee;
	}
	public Shop getShop() {
		return shop;
	}
	public void setShop(Shop shop) {
		this.shop = shop;
	}
	@Override
	public String toString() {
		return "ShopAuthMap [shopAuthId=" + shopAuthId + ", title=" + title + ", titleFlag=" + titleFlag
				+ ", enableStatus=" + enableStatus + ", createTime=" + createTime + ", lastEditTime=" + lastEditTime
				+ ", employee=" + employee + ", shop=" + shop + "]";
	}
	
}
