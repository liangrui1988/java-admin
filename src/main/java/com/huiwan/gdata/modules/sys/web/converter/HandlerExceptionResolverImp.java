package com.huiwan.gdata.modules.sys.web.converter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.servlet.HandlerExceptionResolver;
import org.springframework.web.servlet.ModelAndView;

/**
 * 异常解析
 * 
 * @author ruiliang
 *
 */
public class HandlerExceptionResolverImp implements HandlerExceptionResolver {

	static Logger logger = LoggerFactory.getLogger(HandlerExceptionResolverImp.class);

	@Override
	public ModelAndView resolveException(HttpServletRequest request, HttpServletResponse response, Object handler,
			Exception ex) {
		// Map<String, Object> model = new HashMap<String, Object>();
		// model.put("ex", ex);
		ex.printStackTrace();
		logger.error(ex.getMessage());
		// 根据不同错误转向不同页面
		if (ex instanceof org.apache.shiro.authz.AuthorizationException) {
			return new ModelAndView("/unauthorized");
		} else if (ex instanceof org.apache.shiro.authz.AuthorizationException) {
			return new ModelAndView("/unauthorized");
		} else {
			return new ModelAndView("/exception");
		}
	}

}
