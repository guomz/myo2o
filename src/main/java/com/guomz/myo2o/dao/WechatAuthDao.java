package com.guomz.myo2o.dao;

import com.guomz.myo2o.entity.WechatAuth;

public interface WechatAuthDao {

	/**
	 * 精确查询微信用户信息
	 * @param openId
	 * @return
	 */
	public WechatAuth queryWechatAuthById(String openId);
	
	/**
	 * 插入微信用户信息
	 * @param wechatAuth
	 * @return
	 */
	public int insertWechatAuth(WechatAuth wechatAuth);
}
