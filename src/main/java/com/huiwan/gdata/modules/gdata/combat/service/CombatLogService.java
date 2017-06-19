package com.huiwan.gdata.modules.gdata.combat.service;

import com.huiwan.gdata.common.utils.pagination.Paginator;
import com.huiwan.gdata.common.utils.pagination.PaginatorResult;
import com.huiwan.gdata.modules.gdata.base.charset.bean.QueryCommBean;

public interface CombatLogService {

//	List<CombatLog> list(QueryCommBean bean);
	
	/**
	 * 分页请求日志列表
	 * 
	 * @param paginator
	 * @param vo
	 * @return
	 */
	PaginatorResult getPaginatorList(Paginator paginator, QueryCommBean bean);
}
