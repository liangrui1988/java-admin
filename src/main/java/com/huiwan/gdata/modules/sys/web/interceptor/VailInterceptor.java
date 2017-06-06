package com.huiwan.gdata.modules.sys.web.interceptor;

import java.lang.reflect.Method;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;
import javax.validation.executable.ExecutableValidator;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import com.huiwan.gdata.common.annotatiions.vali.MethodVailAnnot;
import com.huiwan.gdata.modules.sys.web.converter.vail.VailResolverUtils;

/**
 * 拦截器处理验证
 * 
 * @author rui
 *
 */
public class VailInterceptor implements HandlerInterceptor {

	static Logger logger = LoggerFactory.getLogger(VailInterceptor.class);

	@Autowired
	protected Validator validator;
	
	
	static ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
	static ExecutableValidator  executableValidator = factory.getValidator().forExecutables();
	
	
	

	// 请求处理之前进行调用
	@Override
	public boolean preHandle(HttpServletRequest request,
			HttpServletResponse response, Object handler) throws Exception {

		HandlerMethod d = (HandlerMethod) handler;
		MethodVailAnnot mva = d.getMethodAnnotation(MethodVailAnnot.class);
		if (mva == null) {
			return true;
		}

//		System.out.println(mva);

		String[] pNameArray = mva.methodParamName();
		if (pNameArray == null || pNameArray.length <= 0) {
			return true;
		}

		Method m = d.getMethod();
		Class[] clzType = m.getParameterTypes();

		for (Class clz : clzType) {
			System.out.println(clz.getSimpleName());
		}
		// TO_DO .....
		// Annotation[][]pannot= m.getParameterAnnotations();

		// 需要验证的值
		Object[] parameterValues = new Object[pNameArray.length];

		for (int i = 0; i < pNameArray.length; i++) {

			String pn = pNameArray[i];
			if (StringUtils.isBlank(pn)) {
				parameterValues[i] = "";
				continue;
			}

			if ("null".equals(pn.trim())) {
				parameterValues[i] = null;
				continue;
			}
			String pValue = request.getParameter(pNameArray[i].trim());
			Object obj = VailResolverUtils.getType(clzType[i].getSimpleName(), pValue);
			parameterValues[i] = obj;
		}

		System.out.println(parameterValues);

		
		
		
		
//		Class clzcar=Car.class;
//		
//		Object objectcar=clzcar.newInstance();
//		Method methodx = Car.class.getMethod("drive", int.class, String.class,String.class,String.class,BeanVail.class);
//		
//		BeanVail b =new BeanVail();
//		b.setAge(10000);
//		Object[] parameterValuestest = { 1, "a" ,"","",b};
//		// Object[] parameterValues = { 100,"abcdef"};
//		Set<ConstraintViolation<Object>> violations = executableValidator.validateParameters(objectcar, methodx, parameterValuestest);
//		
		
		
		
		
		Class clz = m.getDeclaringClass();
		Object o = clz.newInstance();
		
		Method method = clz.getMethod(m.getName(), clzType);
		

		Set<ConstraintViolation<Object>> violations = vailMethod(o, m,
				parameterValues);

		if (violations != null && violations.size() > 0) {
			System.out.println(violations);
			for (ConstraintViolation cv : violations) {
				System.out.println(cv.getMessage());
			}
		}

		return true;
	}

	@Override
	public void postHandle(HttpServletRequest request,
			HttpServletResponse response, Object handler,
			ModelAndView modelAndView) throws Exception {

	}

	@Override
	public void afterCompletion(HttpServletRequest request,
			HttpServletResponse response, Object handler, Exception ex)
			throws Exception {

	}

	public Set<ConstraintViolation<Object>> vailMethod(Object o, Method m,
			Object[] parameterValues) {
		Set<ConstraintViolation<Object>> violations = executableValidator.validateParameters(o, m, parameterValues);

		// toDO......

		return violations;
	}

	/**
	 * 返回给定类型的参数值,只对以下类型做验证，如果需要就在这里加入判断
	 * 
	 * @param pType
	 * @param obj
	 * @return
	 */
//	public static Object getType(String pType, Object obj) {
//		Object returnObj = null;
//
//		if (pType.endsWith("String")) {
//			returnObj = obj.toString();
//		} else if (pType.endsWith("int") || pType.endsWith("Integer")) {
//			Integer value = Integer.valueOf(obj.toString());
//			returnObj = value;
//		} else if (pType.endsWith("long") || pType.endsWith("Long")) {
//			Long value = Long.valueOf(obj.toString());
//			returnObj = value;
//		} else if (pType.endsWith("short") || pType.endsWith("Short")) {
//			Short value = Short.valueOf((String) obj);
//			returnObj = value;
//		} else if (pType.endsWith("Date")) {
//			returnObj = (java.util.Date) obj;
//		} else if (pType.endsWith("boolean") || pType.endsWith("Boolean")) {
//			returnObj = (Boolean) obj;
//		} else if (pType.endsWith("double") || pType.endsWith("Double")) {
//			Double value = Double.valueOf((String) obj);
//			returnObj = value;
//		} else {
//			returnObj = null;
//		}// .......
//		return returnObj;
//	}

}
