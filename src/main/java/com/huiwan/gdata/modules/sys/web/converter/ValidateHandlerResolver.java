package com.huiwan.gdata.modules.sys.web.converter;

import java.lang.reflect.Field;
import java.util.Map;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;
import javax.validation.ConstraintViolation;
import javax.validation.Validator;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.BeanWrapper;
import org.springframework.beans.PropertyAccessorFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.MethodParameter;
import org.springframework.web.bind.support.WebDataBinderFactory;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.method.support.ModelAndViewContainer;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.common.collect.Maps;
import com.huiwan.gdata.common.annotatiions.vali.BeanVaildate;
import com.huiwan.gdata.modules.sys.web.converter.vail.AnnotResolverHelp;

/**
 * @desc 验证接口参数（方法里的实体bean和单个参数）的解析器
 *       <p>
 *       大部分还是取spring默认(注解验证)的方式，如有特殊验证可以在这里自定义验证
 * @author ruiliang
 * @date 2016/07/01
 *
 */
@Deprecated
public class ValidateHandlerResolver implements HandlerMethodArgumentResolver {

	@Autowired
	protected Validator validator;

	static Logger logger = LoggerFactory
			.getLogger(ValidateHandlerResolver.class);
	// final TypeToken<PMessage> type = new TypeToken<PMessage>() {
	// private static final long serialVersionUID = -5448869194131285385L;
	// };
	private ObjectMapper objectMapper = new ObjectMapper().configure(
			DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);

	/**
	 * 是否给定 {@linkplain MethodParameter method parameter} 支持这个解析器.
	 * 
	 * @param 参数方法参数检查
	 * @return {@code true} 如果这个解析器支持提供的参; {@code false} otherwise
	 */
	@Override
	public boolean supportsParameter(MethodParameter parameter) {

		//return true;
		return parameter.hasParameterAnnotation(BeanVaildate.class);

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

		Object o = BeanUtils.instantiate(parameter.getParameterType());
		Field[] frr = o.getClass().getDeclaredFields();
		// 给对象设置值
		BeanWrapper beanWrapper = PropertyAccessorFactory
				.forBeanPropertyAccess(o);
		for (Field f : frr) {
			if (map.containsKey(f.getName())) {// 如果参数有值
				Object[] oValue = (Object[]) map.get(f.getName());
				if (oValue != null && oValue.length > 0) {
					beanWrapper.setPropertyValue(f.getName(), oValue[0]);
				}
			}
		}

		// 验证对象
		// BeanValidators.validateWithException(validator,hello);
		@SuppressWarnings("rawtypes")
		Set constraintViolations = validator.validate(o);
//		if (!constraintViolations.isEmpty()) {
//			throw new ConstraintViolationException(constraintViolations);
//		}
		Map<String, String> errorMessages =extractPropertyAndMessage(constraintViolations);
		// 验证不通过
		if (errorMessages != null) {
			AnnotResolverHelp.errorMessageChain(httpServletRequest, errorMessages);
		}
		return o;

	}
	
	
	public static Map<String, String> extractPropertyAndMessage(Set<? extends ConstraintViolation> constraintViolations) {
		Map<String, String> errorMessages = Maps.newHashMap();
		for (ConstraintViolation violation : constraintViolations) {
			errorMessages.put(violation.getPropertyPath().toString(), violation.getMessage());
		}
		return errorMessages;
	}

}
