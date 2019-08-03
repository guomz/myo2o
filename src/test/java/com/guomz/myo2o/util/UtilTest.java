package com.guomz.myo2o.util;

import java.io.File;

import org.junit.Ignore;
import org.junit.Test;

import com.guomz.myo2o.BaseTest;

public class UtilTest extends BaseTest{

	@Test
	public void testListFile()
	{
		File f=new File("d:/o2oProjectPics");
		if(f.isDirectory())
		{
			for(File temp:f.listFiles())
			{
				System.out.println(temp.getName());
			}
		}
	}
	
	@Test
	@Ignore
	public void testChileFiles()
	{
		ImgUtil.deleteImg("");
	}
}
