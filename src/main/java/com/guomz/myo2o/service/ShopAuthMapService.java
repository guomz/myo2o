package com.guomz.myo2o.service;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.guomz.myo2o.dao.ShopAuthMapDao;
import com.guomz.myo2o.dto.ShopAuthMapExecution;
import com.guomz.myo2o.entity.ShopAuthMap;
import com.guomz.myo2o.enums.ShopAuthMapStateEnum;
import com.guomz.myo2o.exceptions.ShopAuthMapException;
import com.guomz.myo2o.util.PageCalculator;

/**
 * 处理店铺店员授权管理业务
 * @author 12587
 *
 */
@Service
public class ShopAuthMapService {

	@Autowired
	private ShopAuthMapDao shopAuthMapDao;
	
	/**
	 * 列出某店铺下的授权信息
	 * @param shopId
	 * @param pageIndex
	 * @param pageSize
	 * @return
	 */
	public ShopAuthMapExecution listShopAuthMapByShopId(Long shopId,Integer pageIndex,Integer pageSize)
	{
		if(shopId!=null&&pageIndex!=null&&pageSize!=null)
		{
			int rowIndex=PageCalculator.calculateRowIndex(pageIndex, pageSize);
			try {
				List<ShopAuthMap> shopAuthMapList = shopAuthMapDao.queryShopAuthMapListByShopId(shopId, rowIndex, pageSize);
				int count = shopAuthMapDao.queryShopAuthMapCount(shopId);
				ShopAuthMapExecution shopAuthMapExecution=new ShopAuthMapExecution(ShopAuthMapStateEnum.SUCCESS,shopAuthMapList);
				shopAuthMapExecution.setCount(count);
				return shopAuthMapExecution;
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				return new ShopAuthMapExecution(ShopAuthMapStateEnum.INNER_ERROR);
			}
			
		}
		else
		{
			return new ShopAuthMapExecution(ShopAuthMapStateEnum.NULL_SHOPAUTHID);
		}
	}
	
	/**
	 * 精确查询授权信息
	 * @param shopAuthId
	 * @return
	 */
	public ShopAuthMapExecution getShopAuthMapById(Long shopAuthId)
	{
		ShopAuthMap shopAuthMap = shopAuthMapDao.queryShopAuthMapById(shopAuthId);
		return new ShopAuthMapExecution(ShopAuthMapStateEnum.SUCCESS,shopAuthMap);
	}
	
	/**
	 * 添加授权信息
	 * @param shopAuthMap
	 * @return
	 */
	@Transactional
	public ShopAuthMapExecution addShopAuthMap(ShopAuthMap shopAuthMap)
	{
		if(shopAuthMap!=null&&shopAuthMap.getShop()!=null&&shopAuthMap.getShop().getShopId()!=null&&shopAuthMap.getEmployee()!=null&&shopAuthMap.getEmployee().getUserId()!=null)
		{
			shopAuthMap.setCreateTime(new Date());
			shopAuthMap.setLastEditTime(new Date());
			//扫码后状态默认可用
			shopAuthMap.setEnableStatus(1);
			shopAuthMap.setTitleFlag(0);
			try {
				int result = shopAuthMapDao.insertShopAuthMap(shopAuthMap);
				if(result<=0)
				{
					throw new ShopAuthMapException("内部错误");
				}
				else
				{
					return new ShopAuthMapExecution(ShopAuthMapStateEnum.SUCCESS,shopAuthMap);
				}
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				throw new ShopAuthMapException(e.getMessage());
			}
		}
		else
		{
			return new ShopAuthMapExecution(ShopAuthMapStateEnum.NULL_SHOPAUTHID);
		}
	}
	
	/**
	 * 修改授权信息
	 * @param shopAuthMap
	 * @return
	 */
	@Transactional
	public ShopAuthMapExecution modifyShopAuthMap(ShopAuthMap shopAuthMap)
	{
		if(shopAuthMap!=null&shopAuthMap.getShopAuthId()!=null)
		{
			try {
				int result = shopAuthMapDao.updateShopAuthMap(shopAuthMap);
				if(result<=0)
				{
					throw new ShopAuthMapException("内部错误");
				}
				else
				{
					return new ShopAuthMapExecution(ShopAuthMapStateEnum.SUCCESS,shopAuthMap);
				}
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				throw new ShopAuthMapException(e.getMessage());
			}
		}
		else
		{
			return new ShopAuthMapExecution(ShopAuthMapStateEnum.NULL_SHOPAUTHID);
		}
	}
	
}
