package com.rui.pro1.common.utils.http;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.context.request.ServletWebRequest;





import com.google.gson.Gson;
import com.rui.pro1.common.bean.ResultBean;

public class WebHelp
{
	
	 protected static Logger logger = LoggerFactory.getLogger(WebHelp.class);

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
	
	
	public static boolean isCrossDomainRequest(HttpServletRequest request)
	{
		boolean isCrossDomainRequest = false;
		String callback = request.getParameter("callback");
		if (!StringUtils.isEmpty(callback))
		{
			isCrossDomainRequest = true;
		}
		return isCrossDomainRequest;
	}

	public static boolean isCrossDomainRequest(ServletWebRequest request)
	{
		boolean isCrossDomainRequest = false;
		String callback = request.getParameter("callback");
		if (!StringUtils.isEmpty(callback))
		{
			isCrossDomainRequest = true;
		}
		return isCrossDomainRequest;
	}
	
	
	
	public static void handleCrossDomainRequest(HttpServletRequest request, HttpServletResponse response, ResultBean rb)
	{
		try
		{
			String callback = request.getParameter("callback");
			callback += "(" + new Gson().toJson(rb) + ")";
			response.setContentType("text/script");
			response.setCharacterEncoding("UTF-8");
			response.getOutputStream().write(callback.getBytes("UTF-8"));
		} catch (Exception e)
		{
			logger.error(e.getMessage());
		}
	}

}
