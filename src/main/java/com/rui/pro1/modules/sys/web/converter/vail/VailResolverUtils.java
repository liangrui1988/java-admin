package com.rui.pro1.modules.sys.web.converter.vail;

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

public class VailResolverUtils {
	public static <T> Map<String, String> isValid(
			ConstraintValidator ConstraintValidator, T t, Object objValue,
			String pName) {
		// 比较注解上面的值
		boolean b = ConstraintValidator.isValid(objValue, null);

		Map<String, String> mapMess = null;

		if (!b) {
			mapMess=getMessageMap(t, pName);
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
			mapMess.put(pName, pName + ":不能大于" + max.value());
			return mapMess;
		}

		if (t instanceof Null) {
			Null salfAnnot = (Null) t;
			mapMess = new HashMap<String, String>();
			mapMess.put(pName, pName + "验证失败");
			return mapMess;
		}

		if (t instanceof NotNull) {
			NotNull salfAnnot = (NotNull) t;
			mapMess = new HashMap<String, String>();
			mapMess.put(pName, pName + "验证失败");
			return mapMess;
		}

		if (t instanceof AssertFalse) {
			AssertFalse salfAnnot = (AssertFalse) t;
			mapMess = new HashMap<String, String>();
			mapMess.put(pName, pName + "验证失败");
			return mapMess;
		}

		if (t instanceof DecimalMax) {
			DecimalMax salfAnnot = (DecimalMax) t;
			mapMess = new HashMap<String, String>();
			mapMess.put(pName, pName + "验证失败");
			return mapMess;
		}
		if (t instanceof Digits) {
			Digits salfAnnot = (Digits) t;
			mapMess = new HashMap<String, String>();
			mapMess.put(pName, pName + "验证失败");
			return mapMess;
		}
		if (t instanceof Future) {
			Future salfAnnot = (Future) t;
			mapMess = new HashMap<String, String>();
			mapMess.put(pName, pName + "验证失败");
			return mapMess;
		}
		if (t instanceof Past) {
			Past salfAnnot = (Past) t;
			mapMess = new HashMap<String, String>();
			mapMess.put(pName, pName + "验证失败");
			return mapMess;
		}
		if (t instanceof Pattern) {
			Pattern salfAnnot = (Pattern) t;
			mapMess = new HashMap<String, String>();
			mapMess.put(pName, pName + "验证失败");
			return mapMess;
		}
		if (t instanceof Size) {
			Size salfAnnot = (Size) t;
			mapMess = new HashMap<String, String>();
			mapMess.put(pName, pName + "验证失败");
			return mapMess;
		}
		if (t instanceof Min) {
			Min salfAnnot = (Min) t;
			mapMess = new HashMap<String, String>();
			mapMess.put(pName, pName + "验证失败");
			return mapMess;
		}
		return mapMess;

	}
}
