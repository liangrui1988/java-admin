package com.huiwan.gdata.modules.sys.vo;

public class UserLoginVo {
	private String userName;
	private String password;
	private String rememberMe;
	private String captcha;
	

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
	
	
	

	public String getCaptcha() {
		return captcha;
	}

	public void setCaptcha(String captcha) {
		this.captcha = captcha;
	}

	@Override
	public String toString() {
		return "UserLoginVo [userName=" + userName + ", password=" + password
				+ ", rememberMe=" + rememberMe + "]";
	}


	
	

}
