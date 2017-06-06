package com.huiwan.gdata.modules.sys.threads;

import org.apache.commons.lang3.StringUtils;

import com.huiwan.gdata.common.utils.spring.SysApplicationContext;
import com.huiwan.gdata.modules.sys.entity.SysLog;
import com.huiwan.gdata.modules.sys.service.ILogService;

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
