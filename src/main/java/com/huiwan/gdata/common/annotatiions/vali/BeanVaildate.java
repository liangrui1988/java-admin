package com.huiwan.gdata.common.annotatiions.vali;

import static java.lang.annotation.ElementType.ANNOTATION_TYPE;
import static java.lang.annotation.ElementType.CONSTRUCTOR;
import static java.lang.annotation.ElementType.FIELD;
import static java.lang.annotation.ElementType.METHOD;
import static java.lang.annotation.ElementType.PARAMETER;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

/**
 * 在参数对象上标记为需要验证的bean 通常是和方法里有单个字段验证时一起使用的，会把错误消息联合起来一起输出，
 * <p>
 * <code>Object obj = req.getAttribute(SysComm.VAIL_ERROR_MESSAGE);
		if (obj != null) {
			map = (Map<String, String>) obj;
			System.out.println(map);
			return map;
		}</code>
 * 
 * 
 * @author ruiliang
 *
 */
@Target({ METHOD, FIELD, ANNOTATION_TYPE, CONSTRUCTOR, PARAMETER })
@Retention(RUNTIME)
@Documented
public @interface BeanVaildate {
	// Class<?> clz();
}
