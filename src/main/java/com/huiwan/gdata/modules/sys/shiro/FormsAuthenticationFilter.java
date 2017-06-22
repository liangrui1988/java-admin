package com.huiwan.gdata.modules.sys.shiro;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;

import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.subject.Subject;
import org.apache.shiro.web.filter.authc.FormAuthenticationFilter;
import org.apache.shiro.web.util.WebUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
/**
 * 表单验证
 * auto  进行处理
 * @author 
 *
 */
public class FormsAuthenticationFilter extends FormAuthenticationFilter {
	protected Logger logger = LoggerFactory.getLogger(getClass());

	private String successUrl = "/";

	// private String usernameParam ;
	// private String passwordParam ;
	// private String rememberMeParam;

	public void setSuccessUrl(String successUrl) {
		this.successUrl = successUrl;
	}

	/**
	 * 登陆失败
	 */
	@Override
	protected boolean onLoginFailure(AuthenticationToken token,
			AuthenticationException e, ServletRequest request,
			ServletResponse response) {
		// return super.onLoginFailure(token, e, request, response);

		String className = e.getClass().getName();
		request.setAttribute(getFailureKeyAttribute(), className);

		String message = "";
		if (IncorrectCredentialsException.class.getName().equals(className)
				|| UnknownAccountException.class.getName().equals(className)) {
			message = "用户或密码错误!";
		} else if (e.getMessage() != null && e.getMessage().startsWith("msg:")) {
			// message = e.getMessage().replace("msg:", "");
		} else {
			message = "登陆异常！";
			logger.debug("登陆异常！");
			e.printStackTrace();
		}
		// getFailureKeyAttribute
		request.setAttribute("shiroLoginFailure", className);
		request.setAttribute("message", message);

		return true;
	}

	/**
	 * 全部陆ok
	 */
	@Override
	protected boolean onLoginSuccess(AuthenticationToken token,
			Subject subject, ServletRequest request, ServletResponse response)
			throws Exception {
		issueSuccessRedirect(request, response);
		// return super.onLoginSuccess(token, subject, request, response);
		System.out.println("登陆成功");
		logger.debug("登陆成功！");

		return false;
	}

	protected void issueSuccessRedirect(ServletRequest request,
			ServletResponse response) throws Exception {
		WebUtils.redirectToSavedRequest(request, response, successUrl);
	}

	/**
	 * 生成token   这里和登登陆对应
	 */
	@Override
	public AuthenticationToken createToken(ServletRequest request,
			ServletResponse response) {
		String username = getUsername(request);
		String password = getPassword(request);
		boolean rememberMe = isRememberMe(request);
		String host = getHost(request);
		return createTokenBuild(username, password, rememberMe, host);
	}

	protected AuthenticationToken createTokenBuild(String username,
			String password, boolean rememberMe, String host) {
		return new TokenBuild(username, password, rememberMe, host);
	}
}
