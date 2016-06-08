package com.rui.pro1.modules.sys.web.interceptor;

import java.util.HashSet;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.NamedThreadLocal;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import com.rui.pro1.modules.sys.entity.User;
import com.rui.pro1.modules.sys.utils.UserUtils;

public class PermissionAnnotInterceptor implements HandlerInterceptor {
	
	
	
	
	//Spring提供的一个命名的ThreadLocal实现
	private NamedThreadLocal<Long> startTimeThreadLocal = new NamedThreadLocal<Long>(
			"StopWatch-StartTime");
	
	/**
	 * 用户util组件
	 */
	@Autowired
	public UserUtils userUtils;

	// 请求处理之前进行调用
	@Override
	public boolean preHandle(HttpServletRequest request,
			HttpServletResponse response, Object handler) throws Exception {

		long beginTime = System.currentTimeMillis();// 1、开始时间
		startTimeThreadLocal.set(beginTime);// 线程绑定变量（该数据只有当前请求的线程可见）
		System.out.println("preHandle");
		
		if(SetData.whiteList.contains(request.getRequestURI())){
			return true;
		}
		
		
	    //perm
	    User user=userUtils.getUser();
	    if(user==null||user.getId()!=null||user.getId()<=0)
	    {
	    	return false;
	    }
		
		
		return true;
	}

	// 后处理回调方法 在渲染视图之前
	@Override
	public void postHandle(HttpServletRequest request,
			HttpServletResponse response, Object handler,
			ModelAndView modelAndView) throws Exception {
		System.out.println("postHandle");

	}

	// 整个请求处理完毕回调方法
	@Override
	public void afterCompletion(HttpServletRequest request,
			HttpServletResponse response, Object handler, Exception ex)
			throws Exception {

		long endTime = System.currentTimeMillis();// 2、结束时间
		long beginTime = startTimeThreadLocal.get();// 得到线程绑定的局部变量（开始时间）
		long consumeTime = endTime - beginTime;// 3、消耗的时间
		if (consumeTime > 500) {// 此处认为处理时间超过500毫秒的请求为慢请求
			// TODO 记录到日志文件
			System.out.println(String.format("%s consume %d millis",
					request.getRequestURI(), consumeTime));
		}
		System.out.println("response");

	}

}
