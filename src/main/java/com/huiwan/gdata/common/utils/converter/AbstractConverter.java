package com.huiwan.gdata.common.utils.converter;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.MethodParameter;
import org.springframework.web.bind.support.WebDataBinderFactory;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.method.support.ModelAndViewContainer;

import com.google.common.reflect.TypeToken;

/**
 * 对Controller中的入参进行转换
 * 具体的转换逻辑由子类实现
 * Created  on 2014/4/28
 */
public abstract class AbstractConverter<T> implements HandlerMethodArgumentResolver {

	static Logger logger = LoggerFactory.getLogger(AbstractConverter.class);
	final TypeToken<T> type = new TypeToken<T>(getClass()) {
		private static final long serialVersionUID = -5448869194131285385L;
	};

	@Override
	public boolean supportsParameter(MethodParameter parameter) {
		return type.getRawType().isAssignableFrom(parameter.getParameterType());
	}

	@Override
	public Object resolveArgument(MethodParameter parameter, ModelAndViewContainer mavContainer, NativeWebRequest webRequest, WebDataBinderFactory binderFactory) throws Exception {
		return onResolve(parameter, webRequest, mavContainer, binderFactory);
	}

	protected abstract Object onResolve(MethodParameter parameter, NativeWebRequest webRequest, ModelAndViewContainer mavContainer, WebDataBinderFactory binderFactory) throws Exception;

}
