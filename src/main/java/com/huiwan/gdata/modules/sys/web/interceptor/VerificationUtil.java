package com.huiwan.gdata.modules.sys.web.interceptor;

import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;
import java.util.TreeMap;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.huiwan.gdata.modules.sys.utils.CryptUtil;

public class VerificationUtil {
	protected static Logger logger = LoggerFactory
			.getLogger(VerificationUtil.class);

	private static String singKey = "^^^^^^^^^^^^^";

	/**
	 * 获取签名
	 * 
	 * 
	 * @param param
	 *            request.getParameterMap();
	 * @return
	 */
	public static String getCallBackServerSign(Map param) {
		String signParam = "";
		param = getParameterMap(param);
		if (param.containsKey("sign")) {
			param.remove("sign");// 不参与服务端校验
		}
		signParam = getServerSignParam(param);
		try {
			return CryptUtil.md5(signParam + singKey).toLowerCase();
		} catch (Exception e) {
			logger.error(e.getMessage());
		}
		return null;
	}

	/**
	 * 获取签名的参数 格式a=1&b=1
	 * 
	 * @param head
	 * @return
	 */
	public static String getServerSignParam(Map param) {
		return getSignParam(param);
	}

	/**
	 * map 转参数uri
	 * 
	 * @param param
	 * @return
	 */
	public static String getSignParam(Map param) {
		StringBuffer sb = new StringBuffer();
		String strBody = "";
		if (null != param && param.size() > 0) {
			for (Iterator<Entry<String, Object>> iterator = param.entrySet()
					.iterator(); iterator.hasNext();) {
				Entry<String, Object> obj = iterator.next();
				if (StringUtils.isNotBlank(String.valueOf(obj.getValue()))) {
					sb.append(obj.getKey()).append("=")
							.append(String.valueOf(obj.getValue())).append("&");
				}
			}
			if (sb.length() > 1) {
				strBody = sb.substring(0, sb.length() - 1);
			}
		}
		return strBody;
	}

	/**
	 * map 转treeMap
	 * 
	 * @param properties
	 * @return
	 */
	private static Map getParameterMap(Map properties) {
		Map returnMap = new TreeMap();
		Iterator entries = properties.entrySet().iterator();
		Map.Entry entry;
		String name = "";
		String value = "";
		while (entries.hasNext()) {
			entry = (Map.Entry) entries.next();
			name = (String) entry.getKey();
			Object valueObj = entry.getValue();
			if (null == valueObj) {
				value = "";
			} else if (valueObj instanceof String[]) {
				String[] values = (String[]) valueObj;
				for (int i = 0; i < values.length; i++) {
					value = values[i] + ",";
				}
				value = value.substring(0, value.length() - 1);
			} else {
				value = valueObj.toString();
			}
			returnMap.put(name, value);
		}
		return returnMap;
	}

	// ------------------------------client
	/**
	 * 获取签名 客户端
	 * 
	 * @param head
	 * @param body
	 * @return
	 */
	// public static String getClientSign(Object head,Object body) {
	// String signParam=getClientSignParam(head,body);
	// String pass = paramMd5(signParam);
	// return pass.substring(8, 24);
	// }

	/**
	 * 加密
	 * 
	 * @param param
	 * @return
	 */
	// public static String paramMd5(String param) {
	// String pass = "";
	// try {
	// pass = CryptUtil.md5(param + singKey).toLowerCase();
	// } catch (Exception e) {
	// logger.error(e.getMessage());
	// }
	// return pass;
	// }

	/**
	 * 获取签名的参数 格式a=1&b=1
	 * 
	 * @param head
	 * @param body
	 * @return
	 */
	// public static String getClientSignParam(Object head, Object body) {
	// return getSignParam(object2MapParam(head, body));
	// }

	// public static TreeMap object2MapParam(Object head, Object body) {
	// TreeMap bodyMap = null;
	// TreeMap headMap = null;
	// try {
	// if (null != body) {
	// String str = JSON.toJSONString(body);
	// bodyMap = JSON.parseObject(str, TreeMap.class);
	// }
	// if (null != head) {
	// // headMap=JsonUtils.stringToTreeMap(JSON.toJSONString(head));
	// String str2 = JSON.toJSONString(head);
	// headMap = JSON.parseObject(str2, TreeMap.class);
	// }
	// } catch (Exception e) {
	// logger.error("body or head content should be key-value");
	// }
	// if (headMap != null && bodyMap != null) {
	// headMap.putAll(bodyMap);
	// }
	// return headMap;
	// }

}
