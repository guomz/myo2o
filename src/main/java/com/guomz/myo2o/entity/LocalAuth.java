package com.guomz.myo2o.entity;

import java.util.Date;

/**
 * 用户本地账号信息
 * @author 12587
 *
 */
public class LocalAuth {

	private Long localAuthId;
	//本地账号用户名，不同于personinfo的
	private String userName;
	private String password;
	private Date createTime;
	private Date lastEditTime;
	private PersonInfo personInfo;
	public Long getLocalAuthId() {
		return localAuthId;
	}
	public void setLocalAuthId(Long localAuthId) {
		this.localAuthId = localAuthId;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public Date getCreateTime() {
		return createTime;
	}
	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}
	public Date getLastEditTime() {
		return lastEditTime;
	}
	public void setLastEditTime(Date lastEditTime) {
		this.lastEditTime = lastEditTime;
	}
	public PersonInfo getPersonInfo() {
		return personInfo;
	}
	public void setPersonInfo(PersonInfo personInfo) {
		this.personInfo = personInfo;
	}
	@Override
	public String toString() {
		return "LocalAuth [localAuthId=" + localAuthId + ", userName=" + userName + ", password=" + password
				+ ", createTime=" + createTime + ", lastEditTime=" + lastEditTime + ", personInfo=" + personInfo + "]";
	}
	
}
