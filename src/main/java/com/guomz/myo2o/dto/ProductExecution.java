package com.guomz.myo2o.dto;

import java.util.List;

import com.guomz.myo2o.entity.Product;
import com.guomz.myo2o.enums.ProductStateEnum;

public class ProductExecution {

	private int state;
	private String stateInfo;
	private Product product;
	private List<Product> productList;
	private int count;
	
	public ProductExecution() {
		
	}
	
	/**
	 * 失败构造器
	 * @param productStateEnum
	 */
	public ProductExecution(ProductStateEnum productStateEnum)
	{
		this.state=productStateEnum.getState();
		this.stateInfo=productStateEnum.getStateInfo();
	}
	
	/**
	 * 成功构造器
	 * @param productStateEnum
	 * @param product
	 */
	public ProductExecution(ProductStateEnum productStateEnum,Product product)
	{
		this.state=productStateEnum.getState();
		this.stateInfo=productStateEnum.getStateInfo();
		this.product=product;
	}
	
	/**
	 * 成功构造器
	 * @param productStateEnum
	 * @param productList
	 */
	public ProductExecution(ProductStateEnum productStateEnum,List<Product> productList)
	{
		this.state=productStateEnum.getState();
		this.stateInfo=productStateEnum.getStateInfo();
		this.productList=productList;
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

	public Product getproduct() {
		return product;
	}

	public void setproduct(Product product) {
		this.product = product;
	}

	public List<Product> getproductList() {
		return productList;
	}

	public void setproductList(List<Product> productList) {
		this.productList = productList;
	}

	public int getCount() {
		return count;
	}

	public void setCount(int count) {
		this.count = count;
	}
}
