package com.huiwan.gdata.modules.sys.web.converter;

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

import com.huiwan.gdata.common.annotatiions.PermissionAnnot;
import com.huiwan.gdata.common.constants.enums.MenuReadWrite;
import com.huiwan.gdata.modules.sys.service.ILogService;
import com.huiwan.gdata.modules.sys.utils.UserUtils;

/**
 * 
 * @author rui
 * @date 2016/6/9 今天是端午节，睡到12点，在家写代码
 *
 */
public class permissionAnnotResolver implements HandlerMethodArgumentResolver {

	static Logger logger = LoggerFactory
			.getLogger(permissionAnnotResolver.class);

	@Autowired
	private ILogService logService;
	
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
		
//		SetData.whiteList.contains(request.getRequestURI())

		for (Annotation a : as) {
			if (a.annotationType() == PermissionAnnot.class) {
				PermissionAnnot permissionAnnot = (PermissionAnnot) a;
				//如果这个权限标记为只写，就不会做为一个权限拦截了
				if(permissionAnnot.readWrite().getValue()==MenuReadWrite.Write.getValue()){
					return false;
				}
				
				Set<String> set = userUtils.getUserPermisson();
				if (set.contains(permissionAnnot.id())) {
					return false;
				}
				logger.error("permissionAnnotResolver >>> resolveArgument throw new org.apache.shiro.authz.AuthorizationException(\"没有访问权限\")==id:{}",permissionAnnot.id());
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
