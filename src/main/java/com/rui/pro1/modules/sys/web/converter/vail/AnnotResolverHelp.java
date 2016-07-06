package com.rui.pro1.modules.sys.web.converter.vail;

import java.lang.reflect.Field;
import java.util.Map;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;
import javax.validation.ConstraintValidator;
import javax.validation.Validator;
import javax.validation.constraints.DecimalMax;
import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Null;

import org.hibernate.validator.internal.constraintvalidators.bv.DecimalMaxValidatorForNumber;
import org.hibernate.validator.internal.constraintvalidators.bv.DecimalMinValidatorForNumber;
import org.hibernate.validator.internal.constraintvalidators.bv.MaxValidatorForNumber;
import org.hibernate.validator.internal.constraintvalidators.bv.MinValidatorForNumber;
import org.hibernate.validator.internal.constraintvalidators.bv.NotNullValidator;
import org.hibernate.validator.internal.constraintvalidators.bv.NullValidator;
import org.hibernate.validator.internal.engine.ValidatorImpl;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.BeanWrapper;
import org.springframework.beans.PropertyAccessorFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.MethodParameter;

import com.rui.pro1.modules.sys.constants.SysComm;

public class AnnotResolverHelp {


	static ConstraintValidator<Max, Number> maxValidatorForNumber = new MaxValidatorForNumber();
	static ConstraintValidator<Min, Number> minValidatorForNumber = new MinValidatorForNumber();
	static ConstraintValidator<NotNull, Object> notNullValidatorForNumber = new NotNullValidator();
	static ConstraintValidator<Null, Object> nullValidatorForNumber = new NullValidator();
	static ConstraintValidator<DecimalMax, Number> decimalMaxValidatorForNumber = new DecimalMaxValidatorForNumber();
	static ConstraintValidator<DecimalMin, Number> decimalMinValidatorForNumber = new DecimalMinValidatorForNumber();

	/***
	 * max 验证处理
	 * 
	 * @param returnObj
	 * @param max
	 * @param pName
	 * @param httpServletRequest
	 * @return
	 */
	public static boolean maxResolver(Object returnObj, Max max, String pName,
			HttpServletRequest httpServletRequest) {
		Map<String, String> mapMess = null;
		boolean hasError = false;
		// 直接不通过
		if (returnObj == null || "".equals(returnObj)) {
			hasError = true;
		}
		if (!hasError) {
			maxValidatorForNumber.initialize(max);
			// 比较注解上面的值
			long paramValue = Long.valueOf(returnObj + "");
			mapMess = VailResolverUtils.isValid(maxValidatorForNumber, max,
					paramValue, pName);
		}

		// 验证不通过
		if (mapMess != null) {
			mapMess = errorMessageChain(httpServletRequest, mapMess);
		}
		return mapMess == null ? false : true;

	}

	public static boolean minResolver(Object returnObj, Min min, String pName,
			HttpServletRequest httpServletRequest) {
		Map<String, String> mapMess = null;
		boolean hasError = false;
		// 直接不通过
		if (returnObj == null || "".equals(returnObj)) {
			hasError = true;
		}
		if (!hasError) {
			minValidatorForNumber.initialize(min);
			// 比较注解上面的值
			long paramValue = Long.valueOf(returnObj + "");
			mapMess = VailResolverUtils.isValid(minValidatorForNumber, min,
					paramValue, pName);
		}

		// 验证不通过
		if (mapMess != null) {
			mapMess = errorMessageChain(httpServletRequest, mapMess);
		}
		return mapMess == null ? false : true;

	}

	public static boolean notNullResolver(Object returnObj, NotNull annot,
			String pName, HttpServletRequest httpServletRequest) {
		Map<String, String> mapMess = null;
		boolean hasError = false;
		// 直接不通过
		if (returnObj == null || "".equals(returnObj)) {
			hasError = true;
		}
		if (!hasError) {
			notNullValidatorForNumber.initialize(annot);
			// 比较注解上面的值
			mapMess = VailResolverUtils.isValid(notNullValidatorForNumber,
					annot, returnObj, pName);
		}

		// 验证不通过
		if (mapMess != null) {
			mapMess = errorMessageChain(httpServletRequest, mapMess);
		}
		return mapMess == null ? false : true;

	}

	public static boolean nullResolver(Object returnObj, Null annot,
			String pName, HttpServletRequest httpServletRequest) {
		Map<String, String> mapMess = null;
		boolean hasError = false;
		// 直接不通过
		if (returnObj == null || "".equals(returnObj)) {
			hasError = true;
		}
		if (!hasError) {
			nullValidatorForNumber.initialize(annot);
			// 比较注解上面的值
			mapMess = VailResolverUtils.isValid(nullValidatorForNumber, annot,
					returnObj, pName);
		}

		// 验证不通过
		if (mapMess != null) {
			mapMess = errorMessageChain(httpServletRequest, mapMess);
		}
		return mapMess == null ? false : true;

	}


	/**
	 * error handler
	 * 
	 * @param httpServletRequest
	 * @param mapMess
	 * @return
	 */
	public static Map<String, String> errorMessageChain(
			HttpServletRequest httpServletRequest, Map<String, String> mapMess) {
		Object message = httpServletRequest
				.getAttribute(SysComm.VAIL_ERROR_MESSAGE);
		if (message != null) {
			Map<String, String> srcmapMess = (Map<String, String>) message;
			mapMess.putAll(srcmapMess);
		}
		httpServletRequest.setAttribute(SysComm.VAIL_ERROR_MESSAGE, mapMess);
		return mapMess;
	}
}
