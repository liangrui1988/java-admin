package com.rui.pro1.modules.sys.web.converter.vail;

import org.springframework.core.MethodParameter;
import org.springframework.web.bind.support.WebDataBinderFactory;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.method.support.ModelAndViewContainer;

import com.rui.pro1.common.annotatiions.vali.MethodVailAnnot;

public class MethodVailAnnotResolver implements HandlerMethodArgumentResolver {

	@Override
	public boolean supportsParameter(MethodParameter parameter) {
		
		if( parameter.hasParameterAnnotation(MethodVailAnnot.class)){
			
		}
		
		
		return false;
	}

	@Override
	public Object resolveArgument(MethodParameter parameter,
			ModelAndViewContainer mavContainer, NativeWebRequest webRequest,
			WebDataBinderFactory binderFactory) throws Exception {
		
		return null;
	}

}
