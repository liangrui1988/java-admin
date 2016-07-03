package com.rui.pro1.modules.sys.web.converter;

import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.Map;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;
import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;
import javax.validation.Validator;
import javax.validation.constraints.AssertFalse;
import javax.validation.constraints.AssertTrue;
import javax.validation.constraints.DecimalMax;
import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.Digits;
import javax.validation.constraints.Future;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Null;
import javax.validation.constraints.Past;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

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
import com.rui.pro1.common.annotatiions.PermissionAnnot;
import com.rui.pro1.vail.v.Car;

/**
 * @desc 验证接口参数（方法里的实体bean和单个参数）的解析器
 *       <p>
 *       大部分还是取spring默认(注解验证)的方式，如有特殊验证可以在这里自定义验证
 * @author ruiliang
 * @date 2016/07/01
 *
 */
public class ValidateSingleParamHandlerResolver implements
		HandlerMethodArgumentResolver {

	@Autowired
	protected Validator validator;

	static Logger logger = LoggerFactory
			.getLogger(ValidateSingleParamHandlerResolver.class);

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

		if (parameter.hasParameterAnnotation(Null.class))
			return true;
		if (parameter.hasParameterAnnotation(NotNull.class))
			//NotNullValidator
			return true;
		if (parameter.hasParameterAnnotation(AssertFalse.class))
			return true;
		if (parameter.hasParameterAnnotation(AssertTrue.class))
			return true;
		if (parameter.hasParameterAnnotation(DecimalMax.class))
			return true;
		if (parameter.hasParameterAnnotation(DecimalMin.class))
			return true;
		if (parameter.hasParameterAnnotation(Digits.class))
			return true;
		if (parameter.hasParameterAnnotation(Future.class))
			return true;
		if (parameter.hasParameterAnnotation(Max.class))
			return true;
		if (parameter.hasParameterAnnotation(Past.class))
			return true;
		if (parameter.hasParameterAnnotation(Pattern.class))
			return true;
		if (parameter.hasParameterAnnotation(Size.class))
			return true;
		if (parameter.hasParameterAnnotation(Min.class))
			return true;
		if (parameter.hasParameterAnnotation(Min.class))
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

//		String s = objectMapper.writeValueAsString(map);
//		System.out.println(s);
		
		
		Annotation[] as= parameter.getMethodAnnotations();
		
		Class decla=parameter.getDeclaringClass();
		int index=parameter.getParameterIndex();
		
		Method method=parameter.getMethod();
		
		String pName=parameter.getParameterName();
		
		
		
		for (Annotation a : as) {
			if (a.annotationType() == Null.class) {
				if(!map.containsKey(pName)){
					
				}
			
			}
		}
		
		
		Object parameterClz=parameter.getClass();
		System.out.println(parameterClz);
		Object containingClz=parameter.getContainingClass();
		String p=parameter.getParameterName();
		

		
	//	Method method = Car.class.getMethod("drive", int.class, String.class);

		Object[] parameterValues = { 1, "a" };
		
		Set<ConstraintViolation<Object>> violations = validator.forExecutables()
				.validateParameters(parameterClz, method, parameterValues);
		
		validator.forExecutables().validateParameters(object, method, parameterValues, groups)

		System.out.println(violations);
		for (ConstraintViolation cv : violations) {
			System.out.println(cv.getMessage());
		}
		
		// 验证对象
		// BeanValidators.validateWithException(validator,hello);
		if (!violations.isEmpty()) {
			throw new ConstraintViolationException(violations);
		}
		return null;

	}

}
