package com.guomz.myo2o.service;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.guomz.myo2o.dao.LocalAuthDao;
import com.guomz.myo2o.dto.LocalAuthExecution;
import com.guomz.myo2o.entity.LocalAuth;
import com.guomz.myo2o.enums.LocalAuthStateEnum;
import com.guomz.myo2o.exceptions.LocalAuthException;

@Service
public class LocalAuthService {

	@Autowired
	private LocalAuthDao localAuthDao;
	
	public LocalAuth getLocalAuthByUserId(Long userId)
	{
		return localAuthDao.queryLocalAuthByUserId(userId);
	}
	
	public LocalAuth getLocalAuthByUserNameAndPassword(String userName,String password)
	{
		return localAuthDao.queryLocalAuthByUserNameAndPassword(userName, password);
	}
	
	/**
	 * 将本地账号与微信号绑定
	 * @param localAuth
	 * @return
	 */
	@Transactional
	public LocalAuthExecution bindLocalAuth(LocalAuth localAuth)
	{
		//先判空
		if(localAuth==null||localAuth.getPersonInfo()==null||localAuth.getPersonInfo().getUserId()==null)
		{
			return new LocalAuthExecution(LocalAuthStateEnum.NULL_AUTH_INFO);
		}
		
		//判断是否已经绑定
		LocalAuth temp = localAuthDao.queryLocalAuthByUserId(localAuth.getPersonInfo().getUserId());
		if(temp!=null)
		{
			return new LocalAuthExecution(LocalAuthStateEnum.ONLY_ONE_ACCOUNT);
		}
		
		localAuth.setCreateTime(new Date());
		localAuth.setLastEditTime(new Date());
		
		try {
			int result = localAuthDao.insertLocalAuth(localAuth);
			if(result<=0)
			{
				return new LocalAuthExecution(LocalAuthStateEnum.INNER_ERROR);
			}
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			throw new LocalAuthException(e.getMessage());
		}
		return new LocalAuthExecution(LocalAuthStateEnum.SUCCESS,localAuth);
		
	}
	
	/**
	 * 修改密码
	 * @param userId
	 * @param userName
	 * @param password
	 * @param newPassword
	 * @return
	 */
	@Transactional
	public LocalAuthExecution modifyLocalAuth(Long userId,String userName,String password,String newPassword)
	{
		if(userId==null||userName==null||password==null||newPassword==null)
		{
			return new LocalAuthExecution(LocalAuthStateEnum.NULL_AUTH_INFO);
		}
		
		try {
			int result = localAuthDao.updateLocalAuth(userId, userName, password, newPassword, new Date());
			if(result<=0)
			{
				return new LocalAuthExecution(LocalAuthStateEnum.INNER_ERROR);
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			throw new LocalAuthException(e.getMessage());
		}
		return new LocalAuthExecution(LocalAuthStateEnum.SUCCESS);
	}
}
