package com.guomz.myo2o.util;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.EncodeHintType;
import com.google.zxing.MultiFormatWriter;
import com.google.zxing.WriterException;
import com.google.zxing.common.BitMatrix;

/**
 * 用于生成二维码
 * @author 12587
 *
 */
public class QRCodeUtil {

	/**
	 * 生成二维码，content为二维码携带的信息
	 * @param content
	 * @param resp
	 * @return
	 */
	public static BitMatrix generateQRCodeStream(String content,HttpServletResponse resp)
	{
		//设置响应类型为图片，并告知浏览器不缓存
		resp.setHeader("Cache-Control", "no-store");
		resp.setHeader("Pragma", "no-cache");
		resp.setDateHeader("Expires", 0);
		resp.setContentType("image/png");
		//设置二维码内文字的编码类型以及内边距
		Map<EncodeHintType,Object> hints=new HashMap<EncodeHintType,Object>();
		hints.put(EncodeHintType.CHARACTER_SET, "UTF-8");
		hints.put(EncodeHintType.MARGIN, 0);
		BitMatrix bitMatrix;
		try {
			bitMatrix=new MultiFormatWriter().encode(content, BarcodeFormat.QR_CODE, 300, 300,hints);
		} catch (WriterException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}
		return bitMatrix;
	}
}
