package com.huiwan.gdata.modules.sys.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.huiwan.gdata.common.bean.page.Query;
import com.huiwan.gdata.common.bean.page.QueryResult;
import com.huiwan.gdata.modules.sys.bean.UserBean;
import com.huiwan.gdata.modules.sys.entity.SysLog;
import com.huiwan.gdata.modules.sys.mapper.SysLogMapper;
import com.huiwan.gdata.modules.sys.service.ILogService;
import com.huiwan.gdata.modules.sys.service.IUserService;

@Service
public class LogService implements ILogService {

	@Autowired
	SysLogMapper sysLogMapper; 
	
	@Autowired
	private IUserService userService;
	
	@Override
	public QueryResult<SysLog> getList(Integer page, Integer pagesize, SysLog log) {
		Query query = new Query();
		query.setBean(log);
		query.setPageIndex(page);
		query.setPageSize(pagesize);
		// 组合分页信息
		QueryResult<SysLog> queryResult = new QueryResult<SysLog>();
		Long count = sysLogMapper.getCount(query);
		List<SysLog> list = sysLogMapper.queryPages(query);
		if(list!=null&&list.size()>0)
		{
			for(SysLog sysLog:list){
				if(sysLog.getCreateById()!=null&&sysLog.getCreateById()>0){
					UserBean user=userService.get(sysLog.getCreateById());
					sysLog.setCreateByName(user.getUserName());
				}
			}
		}
		// 总页数 和 取多少条
		queryResult.setCurrentPage(page);
		queryResult.setPages(count, pagesize);
		queryResult.setItems(list);
		return queryResult;
	}

	@Override
	public int add(SysLog log) {
		if(log==null){
			return 0;
		}
//		log.setCreateTime(new Date());
		sysLogMapper.insertSelective(log);
		return 0;
	}


}
