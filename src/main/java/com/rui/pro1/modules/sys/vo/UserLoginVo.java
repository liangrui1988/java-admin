package com.rui.pro1.modules.sys.vo;

public class UserLoginVo {
	private String userName;
	private String password;
	private String rememberMe;
	

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

	
	
	public String getRememberMe() {
		return rememberMe;
	}

	public void setRememberMe(String rememberMe) {
		this.rememberMe = rememberMe;
	}

	@Override
	public String toString() {
		return "UserLoginVo [userName=" + userName + ", password=" + password
				+ ", rememberMe=" + rememberMe + "]";
	}


	
	

}
