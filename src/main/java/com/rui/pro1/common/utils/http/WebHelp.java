package com.rui.pro1.common.utils.http;

import javax.servlet.http.HttpServletRequest;

public class WebHelp
{
	public static boolean isAjAxRequest(HttpServletRequest request)
	{
		boolean isAjaxRequest = false;
		String requestType = request.getHeader("X-Requested-With");
		String accept = request.getHeader("Accept");
		String contentType = request.getHeader("Content-Type");
		if ((requestType != null && "XMLHttpRequest".equalsIgnoreCase(requestType))
				&& ((accept != null && accept.indexOf("application/json") != -1) || (contentType != null && contentType.indexOf("application/json") != -1)))
		{
			isAjaxRequest = true;
		}
		return isAjaxRequest;
	}

}
