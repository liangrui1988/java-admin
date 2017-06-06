package com.huiwan.gdata.common.utils.converter;

import java.io.IOException;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.io.Charsets;
import org.json.JSONException;
import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.alibaba.druid.wall.violation.ErrorCode;
import com.ning.http.util.DateUtil;

import freemarker.template.utility.StringUtil;

public class Message {

	protected static Logger logger = LoggerFactory.getLogger(Message.class);

	/**
	 * 发送消息
	 * @author ruan
	 * @param response
	 * @param data
	 * @param jsonpCallbackFunction
	 */
	public final static void writeMsg(HttpServletResponse response, JSONObject data, String jsonpCallbackFunction) {
		try {
			JSONObject json = new JSONObject();
			json.put("servertime", System.currentTimeMillis());
			json.put("status", 1);
			json.put("errorcode", "");
			json.put("data", data);

			ServletOutputStream output = response.getOutputStream();
			response.setHeader("charset", "UTF-8");
			String str;
			if (jsonpCallbackFunction != null && !jsonpCallbackFunction.isEmpty()) {
				str = jsonpCallbackFunction + "(" + json.toString() + ")";
			} else {
				str = json.toString();
			}
			output.write(str.getBytes(Charsets.UTF_8));
			output.flush();
			output.close();
			if (logger.isInfoEnabled()) {
				logger.info("write: {}", str);
			}
		} catch (IOException | JSONException e) {
			logger.warn("", e);
		}
	}

	
	/**
	 * 发送消息
	 * @author ruan
	 * @param response
	 * @param data
	 * @param jsonpCallbackFunction
	 */
	public final static void writeMsg(HttpServletResponse response, Object data, String jsonpCallbackFunction) {
		try {
			JSONObject json = new JSONObject();
			json.put("servertime", System.currentTimeMillis());
			json.put("status", 1);
			json.put("errorcode", "");
			json.put("data", data);

			ServletOutputStream output = response.getOutputStream();
			response.setHeader("charset", "UTF-8");
			String str;
			if (jsonpCallbackFunction != null && !jsonpCallbackFunction.isEmpty()) {
				str = jsonpCallbackFunction + "(" + json.toString() + ")";
			} else {
				str = json.toString();
			}
			output.write(str.getBytes(Charsets.UTF_8));
			output.flush();
			output.close();
			if (logger.isInfoEnabled()) {
				logger.info("write: {}", str);
			}
		} catch (Exception e) {
			logger.warn("", e);
		}
	}
	
	/**
	 * 发送错误消息
	 * @author ruan
	 * @param response
	 * @param errorCode
	 * @param jsonpCallbackFunction
	 */
	public final static void writeError(HttpServletResponse response, ErrorCode errorCode, String jsonpCallbackFunction) {
		try {
			JSONObject json = new JSONObject();
			json.put("servertime", System.currentTimeMillis());
			json.put("status", 0);
			json.put("errorcode", "");

			ServletOutputStream output = response.getOutputStream();
			response.setHeader("charset", "UTF-8");
			String str;
			if (jsonpCallbackFunction != null && !jsonpCallbackFunction.isEmpty()) {
				str = jsonpCallbackFunction + "(" + json.toString() + ")";
			} else {
				str = json.toString();
			}
			output.write(str.getBytes(Charsets.UTF_8));
			output.flush();
			output.close();
			if (logger.isInfoEnabled()) {
				logger.info("write: {}", str);
			}
		} catch (IOException | JSONException e) {
			logger.warn("", e);
		}
	}
	/**
	 * 发送错误消息
	 * @author ruan
	 * @param response
	 * @param errorCode
	 * @param jsonpCallbackFunction
	 */
	public final static void writeError(HttpServletResponse response, String errorMessage, String jsonpCallbackFunction) {
		try {
			JSONObject json = new JSONObject();
			json.put("servertime", System.currentTimeMillis());
			json.put("status", 0);
			json.put("errorcode", errorMessage);

			ServletOutputStream output = response.getOutputStream();
			response.setHeader("charset", "UTF-8");
			String str;
			if (jsonpCallbackFunction != null && !jsonpCallbackFunction.isEmpty()) {
				str = jsonpCallbackFunction + "(" + json.toString() + ")";
			} else {
				str = json.toString();
			}
			output.write(str.getBytes(Charsets.UTF_8));
			output.flush();
			output.close();
			if (logger.isInfoEnabled()) {
				logger.info("write: {}", str);
			}
		} catch (IOException | JSONException e) {
			logger.warn("", e);
		}
	}
	/**
	 * 向cdn添加缓存
	 * @author ruan
	 * @param res
	 */
	public final static void cdnCache(HttpServletResponse res) {
		long now = System.currentTimeMillis();
//		String url = StringUtil.getString(res.getHeader("url"));
//		int cdnTime = 0;
//		if (isWebResources(url)) {
//			cdnTime = 3600;// 针对网页资源文件，缓存1小时
//		} else {
//			cdnTime = Cdn.get(url);
//		}
//		if (cdnTime <= 0) {
//			return;
//		}
//		if (logger.isInfoEnabled()) {
//			logger.info("cdnCache url:{},cdnTime:{}", url, cdnTime);
//		}
//		res.addHeader("Cache-Control", "max-age=" + cdnTime);
//		res.addDateHeader("Last-Modified", now);
//		res.addDateHeader("Date", now);
//		res.addDateHeader("Expires", now + (cdnTime * 1000));
	}

	
	/**
	 * 发送自定义消息格式
	 * @author lwx
	 * @param response
	 * @param json  自定义格式
	 * @param jsonpCallbackFunction
	 */
	public final static void writeCustomMsg(HttpServletResponse response, JSONObject json, String jsonpCallbackFunction) {
		try {
			ServletOutputStream output = response.getOutputStream();
			response.setHeader("charset", "UTF-8");
			String str;
			if (jsonpCallbackFunction != null && !jsonpCallbackFunction.isEmpty()) {
				str = jsonpCallbackFunction + "(" + json.toString() + ")";
			} else {
				str = json.toString();
			}
			output.write(str.getBytes(Charsets.UTF_8));
			output.flush();
			output.close();
			if (logger.isInfoEnabled()) {
				logger.info("write: {}", str);
			}
		} catch (IOException e) {
			logger.warn("", e);
		}
	}
	
	/**
	 * 判断是否为网页资源文件
	 * @param url
	 * @return
	 */
	private final static boolean isWebResources(String url) {
		return url.endsWith(".js") || url.endsWith(".css") || url.endsWith(".html") || url.endsWith(".swf") || url.endsWith(".png") || url.endsWith(".jpg");
	}
	
}
