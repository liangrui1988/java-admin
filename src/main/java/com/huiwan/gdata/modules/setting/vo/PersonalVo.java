package com.huiwan.gdata.modules.setting.vo;

import java.util.Date;

public class PersonalVo {
	private Integer id;
	private String userName;
	private String password;
	private String fullName;
	private String email;
	// private Integer createById;
	private Integer updateById;
	// private Date createTime;
	private Date updateTime;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
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

	public String getFullName() {
		return fullName;
	}

	public void setFullName(String fullName) {
		this.fullName = fullName;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public Integer getUpdateById() {
		return updateById;
	}

	public void setUpdateById(Integer updateById) {
		this.updateById = updateById;
	}

	public Date getUpdateTime() {
		return updateTime;
	}

	public void setUpdateTime(Date updateTime) {
		this.updateTime = updateTime;
	}

}
