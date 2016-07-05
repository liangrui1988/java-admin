package com.rui.pro1.modules.sys.web.converter.vail;

//import java.lang.annotation.Annotation;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.validation.ConstraintValidator;
import javax.validation.Validator;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;

import org.hibernate.validator.internal.constraintvalidators.bv.MinValidatorForNumber;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.MethodParameter;
import org.springframework.web.bind.support.WebDataBinderFactory;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.method.support.ModelAndViewContainer;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.rui.pro1.modules.sys.constants.SysComm;

public class MinVailResolver implements HandlerMethodArgumentResolver {
	private ObjectMapper objectMapper = new ObjectMapper().configure(
			DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
	@Autowired
	protected Validator validator;

	ConstraintValidator<Min, Number> minValidatorForNumber=new MinValidatorForNumber();

	
	@Override
	public boolean supportsParameter(MethodParameter parameter) {
		if (parameter.hasParameterAnnotation(Max.class))
			return true;
		return false;
	}

	@Override
	public Object resolveArgument(MethodParameter parameter,
			ModelAndViewContainer mavContainer, NativeWebRequest webRequest,
			WebDataBinderFactory binderFactory) throws Exception {
		final HttpServletRequest httpServletRequest = webRequest
				.getNativeRequest(HttpServletRequest.class);
		Map<String, String[]> map = httpServletRequest.getParameterMap();

		String s = objectMapper.writeValueAsString(map);
		System.out.println(s);

		String pName = parameter.getParameterName();

		// 做成对象才能 到vaildation 这是个难题?
		String[] obj = (String[]) map.get(pName);

		// 获取参数值
		Class clz = parameter.getParameterType();
		String pType = clz.toString();
		System.out.println(pType);
		Object returnObj = VailUtil.getType(pType, obj[0]);

		// 获得注解
		Max max = parameter.getParameterAnnotation(Max.class);
		long annotV = max.value();

		boolean hasError = false;
		Map<String, String> mapMess = new HashMap<String, String>();
		// 直接不通过
		if (obj == null || obj.length <= 0 || obj[0] == null) {
			hasError = true;
		}
		if (!hasError) {
			// 比较注解上面的值
			long paramValue = Long.valueOf(returnObj + "");
			if (paramValue > annotV) {
				hasError = true;
			}
			
			
		}
		// 验证不通过
		if (hasError) {
			// httpServletRequest.setAttribute(SysComm.VAIL_ERROR_MESSAGE,
			// "error");
			Object message = httpServletRequest
					.getAttribute(SysComm.VAIL_ERROR_MESSAGE);
			if (message != null) {
				mapMess = (Map<String, String>) message;
			}
			mapMess.put(pName, pName + ":不能大于" + max.value());
			httpServletRequest
					.setAttribute(SysComm.VAIL_ERROR_MESSAGE, mapMess);// pName+":不能大于"+max.value()
		}
		return returnObj;

	}

}
