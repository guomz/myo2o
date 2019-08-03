package com.guomz.myo2o.util;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.guomz.myo2o.dto.UrlResponse;

/**
 * 生成短链接
 * @author 12587
 *
 */
public class ShortNetAddressUtil {

	final static String CREATE_API = "https://dwz.cn/admin/v2/create";
    final static String TOKEN = "617ad22d594e6c7dcfebb0f70ca5201c"; // TODO:设置Token
    
    public static String getShortUrl(String originUrl)
    {
    	String params = "{\"url\":\""+ originUrl + "\"}";
    	BufferedReader reader = null;
    	try {
			URL url = new URL(CREATE_API);
			HttpURLConnection connection = (HttpURLConnection) url.openConnection();
			connection.setDoOutput(true);
			connection.setDoInput(true);
			connection.setUseCaches(false);
			connection.setInstanceFollowRedirects(true);
			connection.setRequestMethod("POST"); // 设置请求方式
			connection.setRequestProperty("Content-Type", "application/json"); // 设置发送数据的格式
			connection.setRequestProperty("Token", TOKEN); // 设置发送数据的格式");

			// 发起请求
			connection.connect();
			OutputStreamWriter out = new OutputStreamWriter(connection.getOutputStream(), "UTF-8"); // utf-8编码
			out.append(params);
			out.flush();
			out.close();

			// 读取响应
			reader = new BufferedReader(new InputStreamReader(connection.getInputStream(), "UTF-8"));
			String line;
			String res = "";
			while ((line = reader.readLine()) != null) {
			    res += line;
			}
			reader.close();
			ObjectMapper om=new ObjectMapper();
			UrlResponse urlResponse = om.readValue(res, UrlResponse.class);
			return urlResponse.getShortUrl();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

    	return "";
    }
    
//    public static void main(String[] args)
//    {
//    	System.out.println(getShortUrl("https://open.weixin.qq.com/connect/oauth2/authorize?appid=wx56fe220e30e6f35c&redirect_uri=http://39.105.145.179/myo2o/wechatlogin/logincheck&role_type=1&response_type=code&scope=snsapi_userinfo&state=1#wechat_redirect"));
//    }
}
