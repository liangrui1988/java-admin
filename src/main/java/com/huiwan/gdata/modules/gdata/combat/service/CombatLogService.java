package com.huiwan.gdata.modules.gdata.combat.service;

import java.util.List;

import com.huiwan.gdata.common.utils.pagination.Paginator;
import com.huiwan.gdata.common.utils.pagination.PaginatorResult;
import com.huiwan.gdata.modules.gdata.base.charset.bean.QueryCommBean;
import com.huiwan.gdata.modules.gdata.combat.entity.CombatAttr;
import com.huiwan.gdata.modules.gdata.combat.entity.CombatLog;
import com.huiwan.gdata.modules.sys.entity.Dict;

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

	CombatLog getDetail(Integer id);

	List<Dict> getObjTypes(int type,String severId);
	
	CombatAttr getAttrs(QueryCommBean bean);
}
