package com.rui.pro1.modules.sys.web.converter.vail;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.apache.commons.lang3.StringUtils;

public class VailUtil {

	/**
	 * 返回给定类型的参数值
	 * 
	 * @param pType
	 * @param obj
	 * @return
	 */
	public static Object getType(String pType, String obj) {
		Object returnObj = null;
		if (pType.endsWith("int")) {
			if (StringUtils.isBlank(obj)) {
				return 0;
			}
			int value = Integer.valueOf(obj);
			return value;
		}

		if (pType.endsWith("Integer")) {
			if (StringUtils.isBlank(obj)) {
				return obj;
			}
			Integer value = Integer.valueOf(obj);
			return value;
		}
		
		if (pType.endsWith("String")) {
			return obj;
		}

		if (pType.endsWith("long")) {
			if (!StringUtils.isBlank(obj)) {
				long value = Long.valueOf((String) obj);
				return value;
			}
			
		}

		if (pType.endsWith("Long")) {
			return Long.valueOf((String) obj);
		}

		if (pType.endsWith("short")) {
			if (StringUtils.isBlank(obj)) {
				return 0;
			}
			short value = Short.valueOf(obj);
			return value;
		}
		if (pType.endsWith("Short")) {
			return Short.valueOf(obj);
		}

		if (pType.endsWith("double")) {
			if (StringUtils.isBlank(obj)) {
				return 0;
			}
			return Double.valueOf(obj);

		}
		if (pType.endsWith("Double")) {
			return Double.valueOf(obj);
		}

		if (pType.endsWith("Date")) {
			if (StringUtils.isBlank(obj)) {
				return null;
			}

			DateFormat df = new SimpleDateFormat();
			Date date = null;
			try {
				date = df.parse(obj);
			} catch (ParseException e) {
				e.printStackTrace();
			}
			return date;

		}

		if (pType.endsWith("boolean")) {
			if (StringUtils.isBlank(obj)) {
				return false;
			}
			return Boolean.valueOf(obj);
		}
		if (pType.endsWith("Boolean")) {
			return Boolean.valueOf(obj);
		} // .......
		return null;
	}
}
