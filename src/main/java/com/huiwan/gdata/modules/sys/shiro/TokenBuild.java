package com.huiwan.gdata.modules.sys.shiro;

/**
 * token 生成
 * 
 * @author ruiliang
 *
 */
public class TokenBuild extends org.apache.shiro.authc.UsernamePasswordToken {

	private static final long serialVersionUID = 1L;

	/**
	 * The username
	 */
	private String username;

	/**
	 * The password, in char[] format
	 */
	private char[] password;

	/**
	 * Whether or not 'rememberMe' should be enabled for the corresponding login
	 * attempt; default is <code>false</code>
	 */
	private boolean rememberMe = false;

	/**
	 * The location from where the login attempt occurs, or <code>null</code> if
	 * not known or explicitly omitted.
	 */
	private String host;

	private String verification;

	// private boolean mobileLogin;

	public TokenBuild(final String username, final String password,
			final boolean rememberMe, final String host) {
		this(username, password != null ? password.toCharArray() : null,
				rememberMe, host);
	}

	public TokenBuild(final String username, final char[] password,
			final boolean rememberMe, final String host) {

		this.username = username;
		this.password = password;
		this.rememberMe = rememberMe;
		this.host = host;
	}

	public TokenBuild(final String username, final char[] password,
			final boolean rememberMe, final String host, String verification) {

		this.username = username;
		this.password = password;
		this.rememberMe = rememberMe;
		this.host = host;
		this.verification = verification;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public char[] getPassword() {
		return password;
	}

	public void setPassword(char[] password) {
		this.password = password;
	}

	public boolean isRememberMe() {
		return rememberMe;
	}

	public void setRememberMe(boolean rememberMe) {
		this.rememberMe = rememberMe;
	}

	public String getHost() {
		return host;
	}

	public void setHost(String host) {
		this.host = host;
	}

	public String getVerification() {
		return verification;
	}

	public void setVerification(String verification) {
		this.verification = verification;
	}

}