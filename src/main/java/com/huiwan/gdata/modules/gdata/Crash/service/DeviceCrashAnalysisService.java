package com.huiwan.gdata.modules.gdata.Crash.service;

import java.util.List;
import java.util.Map;

import com.huiwan.gdata.common.utils.pagination.Paginator;
import com.huiwan.gdata.common.utils.pagination.PaginatorResult;
import com.huiwan.gdata.modules.gdata.Crash.entity.CrashLog;
import com.huiwan.gdata.modules.gdata.Crash.entity.DeviceCrashAny;
import com.huiwan.gdata.modules.gdata.base.charset.bean.QueryCommBean;
import com.huiwan.gdata.modules.gdata.base.charset.bean.common.CharsetBeanCommon;

//import com.uc.db.business.x8.model.device.CrashLog;
//import com.uc.db.business.x8.model.device.DeviceCrashAny;
//import com.uc.db.utils.charset.bean.ChartsQueryVo;
//import com.uc.db.utils.charset.bean.common.CharsetBeanCommon;
//import com.uc.db.utils.pagination.Paginator;
//import com.uc.db.utils.pagination.PaginatorResult;

/**
 * 设备崩溃统计
 * 
 * @author ruiliang
 * @date 2017/03/06
 *
 */
public interface DeviceCrashAnalysisService {

	List<CharsetBeanCommon> getData(QueryCommBean vo);

	DeviceCrashAny getAny(QueryCommBean vo);

	/**
	 * 启动
	 * 
	 * @param vo
	 * @return
	 */
	List<CharsetBeanCommon> getInitData(QueryCommBean vo);

	/**
	 * 影响用户数,只是按设备标识去重
	 * 
	 * @param vo
	 * @return
	 */
	List<CharsetBeanCommon> getEffectData(QueryCommBean vo);

	Map<String, String> getAppVersion(QueryCommBean vo);

	/**
	 * 分页请求日志列表
	 * 
	 * @param paginator
	 * @param vo
	 * @return
	 */
	PaginatorResult getPaginatorList(Paginator paginator, QueryCommBean vo);

	CrashLog getLogDetail(Long id,String gamemark);

}
