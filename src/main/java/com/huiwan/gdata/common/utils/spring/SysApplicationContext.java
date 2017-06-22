package com.huiwan.gdata.common.utils.spring;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;

public class SysApplicationContext implements ApplicationContextAware {

	public static ApplicationContext applicationContext; // Spring应用上下文环境
	private Log log = LogFactory.getLog(this.getClass().getName());

	public SysApplicationContext() {
		// sysApplicationContext = this;
	}

	public void setApplicationContext(ApplicationContext appc)
			throws BeansException {
		// log.info("xxxxxxxxxxxxxxxxInitalizing ApplicationContext......");
		SysApplicationContext.applicationContext = appc;

	}

	public static ApplicationContext getApplicationContext() {
		return SysApplicationContext.applicationContext;
	}

	public static Object getBean(String name) throws BeansException {
		if(applicationContext==null){
			return null;
		}
		return applicationContext.getBean(name);
	}
}
