package com.guomz.myo2o.entity;

import java.util.Date;

/**
 * 某商品在某天的销量
 * @author 12587
 *
 */
public class ProductSellDaily {

	private Long productSellDailyId;
	//哪天的销量
	private Date createTime;
	//销量
	private Integer total;
	private Product product;
	//所属店铺
	private Shop shop;
	public Long getProductSellDailyId() {
		return productSellDailyId;
	}
	public void setProductSellDailyId(Long productSellDailyId) {
		this.productSellDailyId = productSellDailyId;
	}
	public Date getCreateTime() {
		return createTime;
	}
	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}
	public Integer getTotal() {
		return total;
	}
	public void setTotal(Integer total) {
		this.total = total;
	}
	public Product getProduct() {
		return product;
	}
	public void setProduct(Product product) {
		this.product = product;
	}
	public Shop getShop() {
		return shop;
	}
	public void setShop(Shop shop) {
		this.shop = shop;
	}
	@Override
	public String toString() {
		return "ProductSellDaily [createTime=" + createTime + ", total=" + total + ", product=" + product + ", shop="
				+ shop + "]";
	}
	
}
