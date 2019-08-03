package com.guomz.myo2o.util;

public class PageCalculator {

	/**
	 * 由页数计算行数
	 * @param pageIndex
	 * @param pageSize
	 * @return
	 */
	public static int calculateRowIndex(int pageIndex,int pageSize)
	{
		return pageIndex>1?(pageIndex-1)*pageSize:0;
	}
}
