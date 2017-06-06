package com.huiwan.gdata.modules.sys.utils;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;

import org.springframework.cglib.core.Converter;
/**
 * 测试
 * @author Administrator
 *
 */
class UserConverter implements Converter {

	SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

	@Override
	public Object convert(Object value, Class target, Object context) {
		if (value instanceof Integer) {
			return (Integer) value;
		} else if (value instanceof Timestamp) {
			Timestamp date = (Timestamp) value;
			return sdf.format(date);
		} else if (value instanceof BigDecimal) {
			BigDecimal bd = (BigDecimal) value;
			return bd.toPlainString();
		}
		return null;
	}
}