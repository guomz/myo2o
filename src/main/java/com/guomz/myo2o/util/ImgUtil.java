package com.guomz.myo2o.util;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Random;

import javax.imageio.ImageIO;

import net.coobird.thumbnailator.Thumbnails;
import net.coobird.thumbnailator.geometry.Positions;

public class ImgUtil {

	//水印图片根路径，默认windows路径
	private static String basePath="d:/o2oProjectPics/watermarks/";
	//用于随机生成时间
	private static SimpleDateFormat simpleDateFormat=new SimpleDateFormat("yyyyMMddHHmmss");
	//配合时间生成随机文件名
	private static Random r=new Random();
	
	
	/**
	 * 接受图片添加水印，存储后返回相对路径
	 * @param thumbnail
	 * @param targetAddr
	 * @param prefileName
	 * @return
	 */
	public static String generateThumbnail(InputStream thumbnail,String targetAddr,String prefileName)
	{
		String os=System.getProperty("os.name");
		if(os.toLowerCase().startsWith("win")==false)
		{
			basePath="/Users/image/watermarks/";
		}
		String fileName=getRandomFilename();//666xxxxxxxxxxxx
		String fileExtention = getFileExtention(prefileName);//.jpg
		mkDirs(targetAddr);
		String relativeAddr=targetAddr+fileName+fileExtention;
		try {
			Thumbnails.of(thumbnail).size(200, 200)
					.watermark(Positions.BOTTOM_RIGHT, ImageIO.read(new File(basePath+"watermark.jpg")), 0.2f).outputQuality(0.8f).toFile(new File(PathUtil.getImgBasePath()+relativeAddr));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return relativeAddr;
	}
	
	
	/**
	 * 生成随机文件名
	 * @return
	 */
	private static String getRandomFilename()
	{
		int num=r.nextInt(89999)+100000;
		String nowDate=simpleDateFormat.format(new Date());
		return nowDate+num;
	}
	
	/**
	 * 返回文件扩展名
	 * @param prefileName
	 * @return
	 */
	private static String getFileExtention(String prefileName)
	{
		return prefileName.substring(prefileName.lastIndexOf("."));
	}
	
	/**
	 * 生成文件目录
	 * @param relativeAddr
	 */
	private static void mkDirs(String relativeAddr)
	{
		File f=new File(PathUtil.getImgBasePath()+relativeAddr);
		if(!f.exists())
		{
			f.mkdirs();
		}
	}
	
	/**
	 * 删除图片
	 * @param relativeAddr
	 */
	public static void deleteImg(String relativeAddr)
	{
		File f=new File(PathUtil.getImgBasePath()+relativeAddr);
		if(f.isDirectory())
		{
			for(File temp:f.listFiles())
			{
				deleteChildFile(temp);
			}
		}
		f.delete();
	}
	
	/**
	 * 用于递归删除子文件夹的文件
	 */
	private static void deleteChildFile(File f)
	{
		if(f.isDirectory())
		{
			for(File temp:f.listFiles())
			{
				deleteChildFile(temp);
			}
		}
		f.delete();
	}
}
