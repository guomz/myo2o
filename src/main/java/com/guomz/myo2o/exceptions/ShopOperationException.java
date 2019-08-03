package com.guomz.myo2o.exceptions;

/**
 * 处理店铺操作的异常
 * @author 12587
 *
 */
public class ShopOperationException extends RuntimeException{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	public ShopOperationException(String msg)
	{
		super(msg);
	}

}
