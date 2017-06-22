package com.huiwan.gdata.modules.sys.service;

import com.huiwan.gdata.common.bean.page.QueryResult;
import com.huiwan.gdata.modules.sys.entity.SysLog;

public interface ILogService {

	QueryResult<SysLog> getList(Integer page, Integer pagesize, SysLog log);
	
	int add(SysLog log);


}
