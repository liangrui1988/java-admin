package com.rui.pro1.common.utils;

import javax.servlet.http.HttpServletRequest;

import com.google.common.base.Splitter;
import com.google.common.base.Strings;

public class IpUtil {
	
	
	public static String remoteIp(final HttpServletRequest request) {
		String ip = null;

		//if(Env.isProd()){
			final String xff = request.getHeader("X-Forwarded-For");
			if (xff != null) {
				for (final String s : Splitter.on(',').split(xff)) {
					if (ip == null) {
						ip = s;
						break;
					}
				}
			}
		//}
		if (Strings.isNullOrEmpty(ip)) {
			ip = request.getRemoteAddr();
		}
		return ip;
	}

}
