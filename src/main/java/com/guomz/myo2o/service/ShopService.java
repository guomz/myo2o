package com.guomz.myo2o.service;

import java.io.InputStream;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.guomz.myo2o.dao.ShopDao;
import com.guomz.myo2o.dto.ShopExecution;
import com.guomz.myo2o.entity.Shop;
import com.guomz.myo2o.enums.ShopStateEnum;
import com.guomz.myo2o.exceptions.ShopOperationException;
import com.guomz.myo2o.util.ImgUtil;
import com.guomz.myo2o.util.PageCalculator;
import com.guomz.myo2o.util.PathUtil;

@Service
public class ShopService {

	@Autowired
	private ShopDao shopDao;

	/**
	 * 添加店铺（参数改为输入流的形式更加方便测试）
	 * 
	 * @param img
	 * @param shop
	 * @param fileName
	 * @return
	 */
	@Transactional
	public ShopExecution addShop(InputStream img, Shop shop, String fileName) {
		if (shop == null) {
			return new ShopExecution(ShopStateEnum.NULL_SHOPID);
		}
		// 完善店铺基本信息
		shop.setCreateTime(new Date());
		shop.setLastEditTime(new Date());
		shop.setEnableStatus(0);

		try {
			// 先插入店铺信息
			int result = shopDao.insertShop(shop);
			if (result <= 0) {
				throw new ShopOperationException("店铺创建失败");
			} else {
				// 若上传图片则保存图片并更新店铺信息
				if (img != null) {
					try {
						addShopImg(img, shop, fileName);
						result = shopDao.updateShop(shop);
						if (result <= 0) {
							throw new ShopOperationException("店铺更新失败");
						}
					} catch (Exception e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
						throw new ShopOperationException(e.getMessage());
					}
				}
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			throw new ShopOperationException(e.getMessage());
		}

		return new ShopExecution(ShopStateEnum.CHECK, shop);
	}

	/**
	 * 按照id查询店铺
	 * @param shopId
	 * @return
	 */
	public Shop getShopById(Long shopId)
	{
		return shopDao.queryShopById(shopId);
	}
	
	/**
	 * 更新店铺
	 * 
	 * @param img
	 * @param shop
	 * @param fileName
	 * @return
	 */
	@Transactional
	public ShopExecution modifyShop(InputStream img, Shop shop, String fileName) {
		if (shop == null || shop.getShopId() == null) {
			return new ShopExecution(ShopStateEnum.NULL_SHOPID);
		}

		try {
			Shop preShop = shopDao.queryShopById(shop.getShopId());
			if (img != null) {
				if (preShop.getShopImg() != null && !preShop.getShopImg().equals("")) {
					ImgUtil.deleteImg(preShop.getShopImg());
				}
				// 更新图片之前先删除原来的图片，需要查找上一个店铺
				addShopImg(img, shop, fileName);
			}
			shop.setLastEditTime(new Date());
			int result = shopDao.updateShop(shop);
			if (result <= 0) {
				return new ShopExecution(ShopStateEnum.INNER_ERROR);
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			throw new ShopOperationException(e.getMessage());
		}
		return new ShopExecution(ShopStateEnum.SUCCESS, shop);
	}
	
	/**
	 * 分页返回店铺列表
	 * @param shopCondition
	 * @param pageIndex
	 * @param pageSize
	 * @return
	 */
	public ShopExecution getShopList(Shop shopCondition,int pageIndex,int pageSize)
	{
		int rowIndex = PageCalculator.calculateRowIndex(pageIndex, pageSize);
		try {
			List<Shop> shopList = shopDao.queryShopList(shopCondition, rowIndex, pageSize);
			int shopCount = shopDao.queryShopCount(shopCondition);
			ShopExecution shopExecution=new ShopExecution(ShopStateEnum.SUCCESS, shopList);
			shopExecution.setCount(shopCount);
			return shopExecution;
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			throw new ShopOperationException(e.getMessage());
		}
	}

	/**
	 * 完成上传图片操作，更新对象图片信息
	 * 
	 * @param shop
	 * @param thumbnail
	 */
	private void addShopImg(InputStream thumbnail, Shop shop, String fileName) {
		String shopImgPath = PathUtil.getShopImgPath(shop.getShopId());
		String shopImg = ImgUtil.generateThumbnail(thumbnail, shopImgPath, fileName);
		shop.setShopImg(shopImg);
	}

}
