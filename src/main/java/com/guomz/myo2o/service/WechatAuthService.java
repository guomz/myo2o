package com.guomz.myo2o.service;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.guomz.myo2o.dao.PersonInfoDao;
import com.guomz.myo2o.dao.WechatAuthDao;
import com.guomz.myo2o.dto.WechatAuthExecution;
import com.guomz.myo2o.entity.PersonInfo;
import com.guomz.myo2o.entity.WechatAuth;
import com.guomz.myo2o.enums.WechatAuthStateEnum;
import com.guomz.myo2o.exceptions.WechatAuthException;

@Service
public class WechatAuthService {

	@Autowired
	private WechatAuthDao wechatAuthDao;
	@Autowired
	private PersonInfoDao personInfoDao;
	
	/**
	 * 微信用户注册
	 * @param wechatAuth
	 * @return
	 */
	@Transactional
	public WechatAuthExecution register(WechatAuth wechatAuth)
	{
		if(wechatAuth==null||wechatAuth.getOpenId()==null)
		{
			return new WechatAuthExecution(WechatAuthStateEnum.NULL_AUTH_INFO);
		}
		else
		{	
			wechatAuth.setCreateTime(new Date());
			//微信用户携带的用户id为空时则代表用户第一次使用
			if(wechatAuth.getPersonInfo()!=null&&wechatAuth.getPersonInfo().getUserId()==null)
			{
				PersonInfo personInfo = wechatAuth.getPersonInfo();
				personInfo.setCreateTime(new Date());
				personInfo.setEnableStatus(1);
				
				try {
					int result=personInfoDao.insertPersonInfo(personInfo);
					if(result<=0)
					{
						return new WechatAuthExecution(WechatAuthStateEnum.INNER_ERROR);
					}
					wechatAuth.setPersonInfo(personInfo);
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
					throw new WechatAuthException(e.getMessage());
				}
			}
			else if(wechatAuth.getPersonInfo()==null)
			{
				return new WechatAuthExecution(WechatAuthStateEnum.LOGINFAIL);
			}
			
			try {
				int result = wechatAuthDao.insertWechatAuth(wechatAuth);
				if(result<=0)
				{
					return new WechatAuthExecution(WechatAuthStateEnum.INNER_ERROR);
				}
				
				return new WechatAuthExecution(WechatAuthStateEnum.SUCCESS, wechatAuth);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				throw new WechatAuthException(e.getMessage());
			}
		}
	}
	
	/**
	 * 通过openid查询用户
	 * @param openId
	 * @return
	 */
	public WechatAuth getWechatAuthByOpenId(String openId)
	{
		return wechatAuthDao.queryWechatAuthById(openId);
	}
}
