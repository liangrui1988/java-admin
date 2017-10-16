package com.huiwan.gdata.modules.sys.web.converter;

import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;
import javax.validation.ConstraintViolation;
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

import org.hibernate.validator.constraints.Length;
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

import com.huiwan.gdata.common.annotatiions.vali.BeanVaildate;
import com.huiwan.gdata.modules.sys.web.converter.vail.AnnotResolverHelp;
import com.huiwan.gdata.modules.sys.web.converter.vail.VailResolverUtils;

/**
 * @desc 验证接口参数（方法里的实体bean和单个参数）的解析器
 *       <p>
 *       大部分还是取spring默认(注解验证)的方式，如有特殊验证可以在这里自定义验证
 * @author ruiliang
 * @date 2016/07/01
 *
 */
public class ValidateMethodParamHandlerResolver implements
		HandlerMethodArgumentResolver {

	@Autowired
	protected Validator validator;

	static Logger logger = LoggerFactory
			.getLogger(ValidateMethodParamHandlerResolver.class);


	/**
	 * 是否给定 {@linkplain MethodParameter method parameter} 支持这个解析器.
	 * 
	 * @param 参数方法参数检查
	 * @return {@code true} 如果这个解析器支持提供的参; {@code false} otherwise
	 */
	@Override
	public boolean supportsParameter(MethodParameter parameter) {
		if (parameter.hasParameterAnnotation(BeanVaildate.class))
			return true;
		if (parameter.hasParameterAnnotation(Null.class))
			return true;
		if (parameter.hasParameterAnnotation(NotNull.class))
			return true;
		if (parameter.hasParameterAnnotation(Size.class))
			return true;
		if (parameter.hasParameterAnnotation(Min.class))
			return true;
		if (parameter.hasParameterAnnotation(Max.class))
			return true;
		if (parameter.hasParameterAnnotation(Length.class))
			return true;
		//...TODO 末实现
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
		if (parameter.hasParameterAnnotation(Past.class))
			return true;
		if (parameter.hasParameterAnnotation(Pattern.class))
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
		String pName = parameter.getParameterName();
		String[] obj = (String[]) map.get(pName);
		if(obj==null){
			obj=new String[]{null};
		}
		// 获取参数值
		Class clz = parameter.getParameterType();
		String pType = clz.toString();
		Object returnObj = VailResolverUtils.getType(pType, obj[0]);
		// 判断是那种注解
		if (parameter.hasParameterAnnotation(BeanVaildate.class)) 
		{
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
			Set<? extends ConstraintViolation>  constraintViolations = validator.validate(o);
			Map<String, String> errorMessages = null;
			if(constraintViolations!=null&&constraintViolations.size()>0){
				 errorMessages = new HashMap<String, String>();
				for (ConstraintViolation violation : constraintViolations) {
					errorMessages.put(violation.getPropertyPath().toString(), violation.getMessage());
				}
			}
			// 验证不通过
			if (errorMessages != null) {
				AnnotResolverHelp.errorMessageChain(httpServletRequest, errorMessages);
			}
			return o;
		}
			
		if (parameter.hasParameterAnnotation(Null.class)) {
			Null annot = parameter.getParameterAnnotation(Null.class);
			AnnotResolverHelp.nullResolver(returnObj, annot, pName,
					httpServletRequest);
			return returnObj;
		}
		if (parameter.hasParameterAnnotation(NotNull.class)) {
			NotNull annot = parameter.getParameterAnnotation(NotNull.class);
			AnnotResolverHelp.notNullResolver(returnObj, annot, pName,
					httpServletRequest);
			return returnObj;
		}
		if (parameter.hasParameterAnnotation(Max.class)) {
			// 获得注解
			Max max = parameter.getParameterAnnotation(Max.class);
			AnnotResolverHelp.maxResolver(returnObj, max, pName,
					httpServletRequest);
			return returnObj;
		}
		if (parameter.hasParameterAnnotation(Min.class)) {
			// 获得注解
			Min min = parameter.getParameterAnnotation(Min.class);
			AnnotResolverHelp.minResolver(returnObj, min, pName,
					httpServletRequest);
			return returnObj;
		}
		
		if (parameter.hasParameterAnnotation(Length.class)) {
			// 获得注解
			Length annot = parameter.getParameterAnnotation(Length.class);
			AnnotResolverHelp.lengthResolver(returnObj, annot, pName,
					httpServletRequest);
			return returnObj;
		}
		if (parameter.hasParameterAnnotation(DecimalMax.class)) {
			return returnObj;
		}
		if (parameter.hasParameterAnnotation(DecimalMin.class)) {
			return returnObj;
		}
		if (parameter.hasParameterAnnotation(AssertFalse.class)) {
			return returnObj;
		}
		if (parameter.hasParameterAnnotation(AssertTrue.class)) {
			return returnObj;
		}
	
		if (parameter.hasParameterAnnotation(Digits.class)) {
			return returnObj;
		}
		if (parameter.hasParameterAnnotation(Future.class)) {
			return returnObj;
		}
		if (parameter.hasParameterAnnotation(Past.class)) {
			return returnObj;
		}
		if (parameter.hasParameterAnnotation(Pattern.class)) {
			return returnObj;
		}
		if (parameter.hasParameterAnnotation(Size.class)) {
			return returnObj;
		}
		return returnObj;
	}
}
