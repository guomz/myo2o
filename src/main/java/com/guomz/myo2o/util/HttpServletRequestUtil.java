package com.guomz.myo2o.util;

import javax.servlet.http.HttpServletRequest;

/**
 * request读取键值对的工具类
 * @author 12587
 *
 */
public class HttpServletRequestUtil {

	public static Integer getInt(HttpServletRequest req,String key)
	{
		try {
			String value = req.getParameter(key);
			return Integer.decode(value);
		} catch (NumberFormatException e) {
			// TODO Auto-generated catch block
			//e.printStackTrace();
			return null;
		}
	}
	
	public static Long getLong(HttpServletRequest req,String key)
	{
		try {
			String value=req.getParameter(key);
			return Long.valueOf(value);
		} catch (NumberFormatException e) {
			// TODO Auto-generated catch block
			//e.printStackTrace();
			return null;
		}
	}
	
	public static Boolean getBoolean(HttpServletRequest req,String key)
	{
		try {
			String value=req.getParameter(key);
			return Boolean.valueOf(value);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}
	}
	
	public static String getString(HttpServletRequest req,String key)
	{
		try {
			String value=req.getParameter(key).trim();
			if(value.equals(""))
			{
				value=null;
			}
			return value;
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}
	}
}
