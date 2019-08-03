package com.guomz.myo2o.util;

public class PathUtil {

	/**
	 * 得到上传文件的根路径
	 * @return
	 */
	public static String getImgBasePath()
	{
		String os=System.getProperty("os.name");
		String basePath="";
		if(os.toLowerCase().startsWith("win"))
		{
			basePath="d:/o2oProjectPics/";
		}
		else
		{
			basePath="/Users/image";
		}
		return basePath;
	}
	
	/**
	 * 得到店铺图片存放的相对路径
	 * @param shopId
	 * @return
	 */
	public static String getShopImgPath(long shopId)
	{
		return "/upload/item/shop/"+shopId+"/";
	}
}
