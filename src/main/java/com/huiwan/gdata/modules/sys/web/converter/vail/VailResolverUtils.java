package com.huiwan.gdata.modules.sys.web.converter.vail;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import javax.validation.ConstraintValidator;
import javax.validation.constraints.AssertFalse;
import javax.validation.constraints.DecimalMax;
import javax.validation.constraints.Digits;
import javax.validation.constraints.Future;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Null;
import javax.validation.constraints.Past;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

import org.apache.commons.lang3.StringUtils;
import org.hibernate.validator.constraints.Length;

public class VailResolverUtils {

	public static <T> Map<String, String> isValid(
			ConstraintValidator ConstraintValidator, T t, Object objValue,
			String pName) {
		// 比较注解上面的值
		boolean b = ConstraintValidator.isValid(objValue, null);

		Map<String, String> mapMess = null;

		if (!b) {
			mapMess = getMessageMap(t, pName);
		}

		return mapMess;

	}

	/**
	 * TODO 错误提示消息处理,可以结合vaildation国际化使用，这里先简单处理，后期可优化
	 * 
	 * @param t
	 * @param pName
	 * @return
	 */
	public static Map<String, String> getMessageMap(Object t, String pName) {
		Map<String, String> mapMess = null;
		if (t instanceof Max) {
			Max max = (Max) t;
			mapMess = new HashMap<String, String>();
			String message=max.message();
			if(StringUtils.isBlank(message)||message.startsWith("{javax.validation")){
				message="最大不能超过{value}".replace("{value}",String.valueOf(max.value()));
			}
			mapMess.put(pName,message);
			return mapMess;
		}

		if (t instanceof Null) {
			mapMess = new HashMap<String, String>();
			Null salfAnnot = (Null) t;
			String message=salfAnnot.message();
			if(StringUtils.isBlank(message)||message.startsWith("{javax.validation")){
				message="必须为null";
			}
			mapMess.put(pName, message);
			return mapMess;
		}
		
		if (t instanceof Length) {
			mapMess = new HashMap<String, String>();
			Length salfAnnot = (Length) t;
			String message=salfAnnot.message();
			if(StringUtils.isBlank(message)||message.startsWith("{org.hibernate")){
				message="长度需要在{min}和{max}之间";
				message=message.replace("{min}", String.valueOf(salfAnnot.min()));
				message=message.replace("{max}", String.valueOf(salfAnnot.max()));
			}
			mapMess.put(pName, message);
			return mapMess;
		}

		if (t instanceof NotNull) {
			NotNull salfAnnot = (NotNull) t;
			mapMess = new HashMap<String, String>();
			String message=salfAnnot.message();
			if(StringUtils.isBlank(message)||message.startsWith("{javax.validation")){
				message="不能为null";
			}
			mapMess.put(pName, message);
			return mapMess;
		}

		if (t instanceof AssertFalse) {
			AssertFalse salfAnnot = (AssertFalse) t;
			mapMess = new HashMap<String, String>();
			String message=salfAnnot.message();
			if(StringUtils.isBlank(message)||message.startsWith("{javax.validation")){
				message="只能为false";
			}
			mapMess.put(pName, message);
			return mapMess;
		}

		if (t instanceof DecimalMax) {
			DecimalMax salfAnnot = (DecimalMax) t;
			mapMess = new HashMap<String, String>();
			String message=salfAnnot.message();
			if(StringUtils.isBlank(message)||message.startsWith("{javax.validation")){
				message="必须小于或等于"+salfAnnot.value();
			}
			mapMess.put(pName, message);
			return mapMess;
		}
		if (t instanceof Digits) {
			Digits salfAnnot = (Digits) t;
			mapMess = new HashMap<String, String>();
			String message=salfAnnot.message();
			if(StringUtils.isBlank(message)||message.startsWith("{javax.validation")){
				message="数字的值超出了允许范围(只允许在{integer}位整数和{fraction}位小数范围内)";
				message=message.replace("{integer}", salfAnnot.integer()+"");
				message=message.replace("{fraction}",salfAnnot.fraction()+"");
			}
			mapMess.put(pName, message);
			return mapMess;
		}
		if (t instanceof Future) {
			Future salfAnnot = (Future) t;
			mapMess = new HashMap<String, String>();
			String message=salfAnnot.message();
			if(StringUtils.isBlank(message)||message.startsWith("{javax.validation")){
				message="需要是一个将来的时间";
			}
			mapMess.put(pName, message);
			return mapMess;
		}
		if (t instanceof Past) {
			Past salfAnnot = (Past) t;
			mapMess = new HashMap<String, String>();
			String message=salfAnnot.message();
			if(StringUtils.isBlank(message)||message.startsWith("{javax.validation")){
				message="需要是一个过去的时间";
			}
			mapMess.put(pName, message);
			return mapMess;
		}
		if (t instanceof Pattern) {
			Pattern salfAnnot = (Pattern) t;
			mapMess = new HashMap<String, String>();
			String message=salfAnnot.message();
			if(StringUtils.isBlank(message)||message.startsWith("{javax.validation")){
				message="需要匹配正则表达式\"{"+salfAnnot.regexp()+"}\"";
			}
			mapMess.put(pName, message);
			return mapMess;
		}
		if (t instanceof Size) {
			mapMess = new HashMap<String, String>();
			Size salfAnnot = (Size) t;
			String message=salfAnnot.message();
			if(StringUtils.isBlank(message)||message.startsWith("{javax.validation")){
				message="个数必须在{min}和{max}之间";
				message=message.replace("{min}", String.valueOf(salfAnnot.min()));
				message=message.replace("{max}", String.valueOf(salfAnnot.max()));
			}
			mapMess.put(pName, message);
			return mapMess;
		}
		if (t instanceof Min) {
			Min salfAnnot = (Min) t;
			mapMess = new HashMap<String, String>();
			String message=salfAnnot.message();
			if(StringUtils.isBlank(message)||message.startsWith("{{javax.validation")){
				message="最小不能小于{value}";
				message=message.replace("{value}", String.valueOf(salfAnnot.value()));
			}
			mapMess.put(pName, message);
			return mapMess;
		}
		return mapMess;

	}

	/**
	 * 返回给定类型的参数值
	 * 
	 * @param pType
	 * @param obj
	 * @return
	 */
	public static Object getType(String pType, String obj) {
		
	
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
