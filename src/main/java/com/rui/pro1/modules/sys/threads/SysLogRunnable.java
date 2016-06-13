package com.rui.pro1.modules.sys.threads;

import org.apache.commons.lang3.StringUtils;

import com.rui.pro1.common.utils.spring.SysApplicationContext;
import com.rui.pro1.modules.sys.entity.SysLog;
import com.rui.pro1.modules.sys.service.ILogService;

public class SysLogRunnable implements Runnable {

	private SysLog log;
	
//	@Autowired
//	private ILogService logService;
	
	public SysLogRunnable(){
		
	}
	
	public SysLogRunnable(SysLog log){
		this.log=log;
	}
	
	
	@Override
	public void run() {
		ILogService	logService = (ILogService) SysApplicationContext
					.getBean("logService");
		if(log==null){
			return;
		}
		
		if(!StringUtils.isBlank(log.getUri())){
			
		}
		
		
		logService.add(log);
		
	}

}
