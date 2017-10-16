package com.huiwan.gdata.common.utils.http;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.net.URLEncoder;

import org.apache.commons.lang3.StringUtils;


public class UrlDocode {

	/**
	 * 转码
	 * 
	 * @param value
	 * @return
	 * @throws UnsupportedEncodingException
	 */
	public static String urlEncode(String value) throws UnsupportedEncodingException {
		if (StringUtils.isBlank(value)) {
			return "";
		}
		return URLEncoder.encode(value, "UTF-8");
	}

	/**
	 * 解码
	 * 
	 * @param value
	 * @return
	 * @throws UnsupportedEncodingException
	 */
	public static String urlDecode(String value) throws UnsupportedEncodingException {
		if (StringUtils.isBlank(value)) {
			return "";
		}
		return URLDecoder.decode(value, "UTF-8");
	}

}
