package com.guomz.myo2o.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * 接收百度短链接接口的回送数据
 * @author 12587
 *
 */
public class UrlResponse {

	@JsonProperty("Code")
	private Integer Code;
	@JsonProperty("ErrMsg")
	private String ErrMsg;
	@JsonProperty("LongUrl")
	private String LongUrl;
	@JsonProperty("ShortUrl")
	private String ShortUrl;
	@JsonProperty("IsNew")
	private Boolean IsNew;
	public Boolean getIsNew() {
		return IsNew;
	}
	public void setIsNew(Boolean isNew) {
		IsNew = isNew;
	}
	public Integer getCode() {
		return Code;
	}
	public void setCode(Integer code) {
		Code = code;
	}
	public String getErrMsg() {
		return ErrMsg;
	}
	public void setErrMsg(String errMsg) {
		ErrMsg = errMsg;
	}
	public String getLongUrl() {
		return LongUrl;
	}
	public void setLongUrl(String longUrl) {
		LongUrl = longUrl;
	}
	public String getShortUrl() {
		return ShortUrl;
	}
	public void setShortUrl(String shortUrl) {
		ShortUrl = shortUrl;
	}
	@Override
	public String toString() {
		return "UrlResponse [Code=" + Code + ", ErrMsg=" + ErrMsg + ", LongUrl=" + LongUrl + ", ShortUrl=" + ShortUrl
				+ "]";
	}
	
}
