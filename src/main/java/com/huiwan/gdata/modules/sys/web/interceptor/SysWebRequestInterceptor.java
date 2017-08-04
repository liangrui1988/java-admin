package com.huiwan.gdata.modules.sys.web.interceptor;

import java.util.Date;
import java.util.Set;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.NamedThreadLocal;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import com.huiwan.gdata.common.annotatiions.PermissionAnnot;
import com.huiwan.gdata.common.bean.ResultBean;
import com.huiwan.gdata.common.constants.RespHeaderConstans;
import com.huiwan.gdata.common.constants.enums.MenuReadWrite;
import com.huiwan.gdata.common.exception.MessageCode;
import com.huiwan.gdata.common.utils.IpUtil;
import com.huiwan.gdata.common.utils.http.WebHelp;
import com.huiwan.gdata.modules.sys.bean.UserBean;
import com.huiwan.gdata.modules.sys.entity.SysLog;
import com.huiwan.gdata.modules.sys.threads.SysLogRunnable;
import com.huiwan.gdata.modules.sys.utils.UserUtils;
import com.huiwan.gdata.modules.sys.web.converter.permissionAnnotResolver;

public class SysWebRequestInterceptor implements HandlerInterceptor {
	static Logger logger = LoggerFactory.getLogger(permissionAnnotResolver.class);
	// Spring提供的一个命名的ThreadLocal实现
	private NamedThreadLocal<Long> startTimeThreadLocal = new NamedThreadLocal<Long>("StopWatch-StartTime");

	/**
	 * 用户util组件
	 */
	@Autowired
	public UserUtils userUtils;

	// 请求处理之前进行调用
	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		long beginTime = System.currentTimeMillis();// 1、开始时间
		startTimeThreadLocal.set(beginTime);// 线程绑定变量（该数据只有当前请求的线程可见）
		// if(SetData.whiteList.contains(request.getRequestURI())){
		// return true;
		// }
		// perm
		// if (!isUserLogin(request, response)) {
		// return false;
		// }
		// 要限处理
		// 只有当GET请求是请求静态文件时(在spring配置文件里会配置静态文件的URI)，handler的实际类型会是DefaultServletHttpRequestHandler
		if (handler instanceof HandlerMethod) {
			HandlerMethod method = (HandlerMethod) handler;
			PermissionAnnot permissionAnnot = method.getMethod().getAnnotation(PermissionAnnot.class);
			if (permissionAnnot != null) {
				// 如果这个权限标记为只写，就不会做为一个权限拦截了
				if (permissionAnnot.readWrite().getValue() != MenuReadWrite.Write.getValue()) {
					// 先判断用户是否登陆
					if (!isUserLogin(request, response)) {
						return false;
					}

					Set<String> set = userUtils.getUserPermisson();
					if (!set.contains(permissionAnnot.id())) {// 如果没有权限
						logger.error(
								"permissionAnnotResolver >>> resolveArgument throw new org.apache.shiro.authz.AuthorizationException(\"没有访问权限\")==id:{}",
								permissionAnnot.id());
						throw new org.apache.shiro.authz.AuthorizationException("没有访问权限");
						// return false;
					}
				}
			}
		}

		try {
			// 日志处理
			UserBean user = userUtils.getUserBean();
			SysLog log = new SysLog();
			log.setCreateById(user.getId());
			log.setCreateTime(new Date());
			// String host = request.getRemoteHost();
			String ip = IpUtil.getRemoteIp(request);
			log.setIp(ip);
			log.setTitle("");
			log.setUri(request.getRequestURI());
			String userAgent = request.getHeader("user-agent");
			log.setAgent(userAgent);
			String parameters = WebHelp.requestParametersToString(request);
			log.setParameters(parameters);
			log.setMethod(request.getMethod());
			ExecutorService cachedThreadPool = Executors.newCachedThreadPool();
			cachedThreadPool.execute(new SysLogRunnable(log));
			// new Thread(new SysLogRunnable(log)).start();
		} catch (Exception e) {
			logger.error("写入系统日志异常!");
			e.printStackTrace();
		}
		// logger.info("SysWebRequestInterceptor》》preHandle");
		return true;
	}

	// 后处理回调方法 在渲染视图之前
	@Override
	public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler,
			ModelAndView modelAndView) throws Exception {
		// logger.info("postHandle");
	}

	// 整个请求处理完毕回调方法
	@Override
	public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex)
			throws Exception {
		long endTime = System.currentTimeMillis();// 2、结束时间
		long beginTime = startTimeThreadLocal.get();// 得到线程绑定的局部变量（开始时间）
		long consumeTime = endTime - beginTime;// 3、消耗的时间
		if (consumeTime > 500) {// 此处认为处理时间超过500毫秒的请求为慢请求
			// TODO 记录到日志文件
			logger.warn(String.format("%s consume %d millis", request.getRequestURI(), consumeTime));
		}
		// logger.info("SysWebRequestInterceptor>>afterCompletion");
	}

	private boolean isUserLogin(HttpServletRequest request, HttpServletResponse response) {
		UserBean user = userUtils.getUserBean();
		if (user == null || user.getId() == null || user.getId() <= 0) {
			ResultBean rb = new ResultBean();
			response.setHeader(RespHeaderConstans.AJAX_REQUEST_HEADER, RespHeaderConstans.Code.AJAX_REQUEST_HEADER_002);
			rb.setSuccess(false);
			rb.setMessageCode(MessageCode.SYS_NO_PERMISSE);
			rb.setMessage("没有权限");
			WebHelp.responseResultBean(request, response, rb);
			return false;
		}
		return true;
	}

}
