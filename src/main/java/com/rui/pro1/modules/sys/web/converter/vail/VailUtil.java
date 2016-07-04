package com.rui.pro1.modules.sys.web.converter.vail;

public class VailUtil {

	/**
	 * 返回给定类型的参数值
	 * 
	 * @param pType
	 * @param obj
	 * @return
	 */
	public static  Object getType(String pType, Object obj) {
		Object returnObj = null;
		if (pType.endsWith("int") || pType.endsWith("Integer")) {
			Integer value = Integer.valueOf((String) obj);
			returnObj = value;
		} else if (pType.endsWith("long") || pType.endsWith("Long")) {
			Long value = Long.valueOf((String) obj);
			returnObj = value;
		} else if (pType.endsWith("short") || pType.endsWith("java.lang.Short")) {
			Short value = Short.valueOf((String) obj);
			returnObj = value;
		} else if (pType.endsWith("java.util.Date")) {
			returnObj = (java.util.Date) obj;
		} else if (pType.endsWith("boolean")
				|| pType.endsWith("java.lang.Boolean")) {
			returnObj = (Boolean) obj;
		} else if (pType.endsWith("double")
				|| pType.endsWith("java.lang.Double")) {
			Double value = Double.valueOf((String) obj);
			returnObj = value;
		} else {
			returnObj = obj + "";
		}// .......
		return returnObj;
	}
	
	
	

}
