package com.rui.pro1.modules.sys.web.converter.vail;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;
import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;
import javax.validation.Validator;
import javax.validation.constraints.NotNull;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.MethodParameter;
import org.springframework.web.bind.support.WebDataBinderFactory;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.method.support.ModelAndViewContainer;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.rui.pro1.modules.sys.constants.SysComm;
import com.rui.pro1.modules.sys.web.converter.vail.FalseBean.FalseBean;

public class NotNallVailResolver implements HandlerMethodArgumentResolver {
	private ObjectMapper objectMapper = new ObjectMapper().configure(
			DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
	@Autowired
	protected Validator validator;
	
	@Override
	public boolean supportsParameter(MethodParameter parameter) {
		if (parameter.hasParameterAnnotation(NotNull.class))
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

		// for(Entry<String,Object> set:map.entrySet()){
		// System.out.println(set.getKey());
		// System.out.println(map.get(set.getKey()));
		// }

		String s = objectMapper.writeValueAsString(map);
		System.out.println(s);
		String pName = parameter.getParameterName();

		// 做成对象才能vaildation 这是个难题?
		Object[] obj = (Object[]) map.get(pName);
		
		if(obj==null||obj.length<=0||obj[0]==null){
			//模拟异常
			Set<ConstraintViolation<FalseBean>> constraintViolations = validator
					.validateValue(FalseBean.class, "notNullF", null);
			if (!constraintViolations.isEmpty()) {
				throw new ConstraintViolationException(constraintViolations);
			}
		}
		
		
		//Map<String,String> mapMess=new HashMap<String,String>();
		//Object message= httpServletRequest.getAttribute(SysComm.VAIL_ERROR_MESSAGE);
		//mapMess.put(pName, "x");
		//httpServletRequest.setAttribute(SysComm.VAIL_ERROR_MESSAGE, mapMess);

		return obj[0];

	}

}
