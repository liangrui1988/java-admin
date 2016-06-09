package com.rui.pro1.modules.sys.web.converter;

import java.lang.annotation.Annotation;
import java.util.Set;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.MethodParameter;
import org.springframework.web.bind.support.WebDataBinderFactory;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.method.support.ModelAndViewContainer;

import com.rui.pro1.common.annotatiions.PermissionAnnot;
import com.rui.pro1.modules.sys.utils.UserUtils;

/**
 * 
 * @author rui
 * @date 2016/6/9 今天是端午节，睡到12点，在家写代码
 *
 */
public class permissionAnnotResolver implements HandlerMethodArgumentResolver {

	static Logger logger = LoggerFactory
			.getLogger(permissionAnnotResolver.class);

	/**
	 * 用户util组件
	 */
	@Autowired
	public UserUtils userUtils;

	@Override
	public boolean supportsParameter(MethodParameter parameter) {
		Annotation[] as = parameter.getMethodAnnotations();
		if (as == null) {
			return false;

		}

		for (Annotation a : as) {
			if (a.annotationType() == PermissionAnnot.class) {
				PermissionAnnot permissionAnnot = (PermissionAnnot) a;
				Set<String> set = userUtils.getUserPermisson();
				logger.error("permissionAnnotResolver >>> resolveArgument throw new org.apache.shiro.authz.AuthorizationException(\"没有访问权限\")==id:{}",permissionAnnot.id());
				if (set.contains(permissionAnnot.id())) {
					return false;
				}
				return true;
			}
		}

		return false;
	}

	@Override
	public Object resolveArgument(MethodParameter parameter,
			ModelAndViewContainer mavContainer, NativeWebRequest webRequest,
			WebDataBinderFactory binderFactory) throws Exception {
		// final HttpServletRequest servletRequest = webRequest
		// .getNativeRequest(HttpServletRequest.class);
		// HttpInputMessage inputMessage = new ServletServerHttpRequest(
		// servletRequest);
		// return inputMessage.getBody();
//		logger.error("permissionAnnotResolver >>> resolveArgument throw new org.apache.shiro.authz.AuthorizationException(\"没有访问权限\")");
		throw new org.apache.shiro.authz.AuthorizationException("没有访问权限");

	}

	// @Override
	// public Object resolveArgument(MethodParameter parameter,
	// ModelAndViewContainer mavContainer, NativeWebRequest webRequest,
	// WebDataBinderFactory binderFactory) throws Exception {
	// mavContainer.setRequestHandled(true);
	//
	// PMessage pMessage = (PMessage) webRequest.getAttribute("PMessage",
	// RequestAttributes.SCOPE_REQUEST);
	// Object resultObj = pMessage.getBodyObj(parameter.getParameterType());
	// return resultObj;
	// }

}
