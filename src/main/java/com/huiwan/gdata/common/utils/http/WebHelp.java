package com.huiwan.gdata.common.utils.http;

import java.util.Iterator;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.context.request.ServletWebRequest;







import com.google.gson.Gson;
import com.huiwan.gdata.common.bean.ResultBean;

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
//			response.setCharacterEncoding("UTF-8");
			response.getOutputStream().write(callback.getBytes("UTF-8"));
		} catch (Exception e)
		{
			logger.error(e.getMessage());
		}
	}
	
	
	
	public static void responseResultBean(HttpServletRequest request, HttpServletResponse response, ResultBean rb)
	{
		if (WebHelp.isCrossDomainRequest(request))
		{
			WebHelp.handleCrossDomainRequest(request, response, rb);
		} else
		{
			try
			{
				response.getOutputStream().write(new Gson().toJson(rb).getBytes("UTF-8"));
			} catch (Exception e)
			{
				logger.error(e.getMessage());
			}
		}
	}

	
	public static String requestParametersToString(HttpServletRequest request)
	{
		StringBuffer sb = new StringBuffer();
		Map map = request.getParameterMap();
		if (map != null)
		{
			Iterator<String> it = map.keySet().iterator();
			boolean isOk = false;
			String key = null;
			Object value = null;
			while (it.hasNext())
			{
				key = it.next();
				value = request.getParameter(key);
				if (value != null && !value.equals("") && !key.startsWith("button.") && !key.startsWith("submitDateTime") && !key.equals("_"))
				{
					if (isOk)
					{
						sb.append("&");
					}
					sb.append(key + "=" + value);
					isOk = true;
				}
			}
		}
		return sb.toString();
	}
	
}
