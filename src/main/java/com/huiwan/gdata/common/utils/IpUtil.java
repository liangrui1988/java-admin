package com.huiwan.gdata.common.utils;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang3.StringUtils;

public class IpUtil {

	// public static String remoteIp(final HttpServletRequest request) {
	// String ip = null;
	//
	// // if(Env.isProd()){
	// final String xff = request.getHeader("X-Forwarded-For");
	// if (xff != null) {
	// for (final String s : Splitter.on(',').split(xff)) {
	// if (ip == null) {
	// ip = s;
	// break;
	// }
	// }
	// }
	// // }
	// if (Strings.isNullOrEmpty(ip)) {
	// ip = request.getRemoteAddr();
	// }
	// return ip;
	// }

	public static String getRemoteIp(HttpServletRequest request) {
		String ip = request.getHeader("X-Forwarded-For");
		if (StringUtils.isNotEmpty(ip) && !"unKnown".equalsIgnoreCase(ip)) {
			// 多次反向代理后会有多个ip值，第一个ip才是真实ip
			int index = ip.indexOf(",");
			if (index != -1) {
				return ip.substring(0, index);
			} else {
				return ip;
			}
		}
		ip = request.getHeader("X-Real-IP");
		if (StringUtils.isNotEmpty(ip) && !"unKnown".equalsIgnoreCase(ip)) {
			return ip;
		}
		return request.getRemoteAddr();
	}

	/**
	 * 把ip转为10进制长整型
	 * 
	 * @author 2013-1-21
	 * @param strip
	 * @return
	 */
	public final static long ip2Long(String strip) {
		String[] ipArr = strip.split("\\.");
		return (Long.parseLong(ipArr[0]) << 24)
				+ (Long.parseLong(ipArr[1]) << 16)
				+ (Long.parseLong(ipArr[2]) << 8) + Long.parseLong(ipArr[3]);
	}

	/**
	 * 把10进制长整型转为ip
	 * 
	 * @author 2013-1-21
	 * @param longip
	 * @return
	 */
	public final static String long2Ip(long longip) {
		StringBuilder sb = new StringBuilder();
		sb.append(String.valueOf(longip >>> 24));
		sb.append(".");
		sb.append(String.valueOf((longip & 0x00ffffff) >>> 16));
		sb.append(".");
		sb.append(String.valueOf((longip & 0x0000ffff) >>> 8));
		sb.append(".");
		sb.append(String.valueOf(longip & 0x000000ff));
		return sb.toString();
	}

}
