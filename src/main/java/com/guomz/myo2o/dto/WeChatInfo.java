package com.guomz.myo2o.dto;

/**
 * 用来接收二维码信息
 * @author 12587
 *
 */
public class WeChatInfo {

	private Long customerId;
	private Long shopId;
	private Long productId;
	private Long userAwardId;
	private Long createTime;
	public Long getCustomerId() {
		return customerId;
	}
	public void setCustomerId(Long customerId) {
		this.customerId = customerId;
	}
	public Long getShopId() {
		return shopId;
	}
	public void setShopId(Long shopId) {
		this.shopId = shopId;
	}
	public Long getProductId() {
		return productId;
	}
	public void setProductId(Long productId) {
		this.productId = productId;
	}
	public Long getUserAwardId() {
		return userAwardId;
	}
	public void setUserAwardId(Long userAwardId) {
		this.userAwardId = userAwardId;
	}
	public Long getCreateTime() {
		return createTime;
	}
	public void setCreateTime(Long createTime) {
		this.createTime = createTime;
	}
	@Override
	public String toString() {
		return "WeChatInfo [customerId=" + customerId + ", shopId=" + shopId + ", productId=" + productId
				+ ", userAwardId=" + userAwardId + ", createTime=" + createTime + ", getCustomerId()=" + getCustomerId()
				+ ", getShopId()=" + getShopId() + ", getProductId()=" + getProductId() + ", getUserAwardId()="
				+ getUserAwardId() + ", getCreateTime()=" + getCreateTime() + ", getClass()=" + getClass()
				+ ", hashCode()=" + hashCode() + ", toString()=" + super.toString() + "]";
	}
	
}
