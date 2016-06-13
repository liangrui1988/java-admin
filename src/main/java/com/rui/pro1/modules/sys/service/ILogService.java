package com.rui.pro1.modules.sys.service;

import com.rui.pro1.common.bean.page.QueryResult;
import com.rui.pro1.modules.sys.entity.SysLog;

public interface ILogService {

	QueryResult<SysLog> getList(Integer page, Integer pagesize, SysLog log);
	
	int add(SysLog log);


}
