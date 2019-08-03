package com.guomz.myo2o.entity;

import java.util.Date;

/**
 * 存放用户信息的实体类
 * 
 * @author 12587
 *
 */
public class PersonInfo {

	private Long userId;
	//微信昵称
	private String userName;
	// 用户头像存储位置
	private String profileImg;
	private String email;
	// 性别
	private String gender;
	//0禁止使用商城 1可以使用商城
	private Integer enableStatus;
	// 用户类型：1顾客 2店家 3管理员
	private Integer userType;
	private Date createTime;
	private Date lastEditTime;

	public Long getUserId() {
		return userId;
	}

	public void setUserId(Long userId) {
		this.userId = userId;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getProfileImg() {
		return profileImg;
	}

	public void setProfileImg(String profileImg) {
		this.profileImg = profileImg;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	public Integer getEnableStatus() {
		return enableStatus;
	}

	public void setEnableStatus(Integer enableStatus) {
		this.enableStatus = enableStatus;
	}

	public Integer getUserType() {
		return userType;
	}

	public void setUserType(Integer userType) {
		this.userType = userType;
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

	@Override
	public String toString() {
		return "PersonInfo [userId=" + userId + ", userName=" + userName + ", profileImg=" + profileImg + ", email="
				+ email + ", gender=" + gender + ", enableStatus=" + enableStatus + ", userType=" + userType
				+ ", createTime=" + createTime + ", lastEditTime=" + lastEditTime + "]";
	}

}
