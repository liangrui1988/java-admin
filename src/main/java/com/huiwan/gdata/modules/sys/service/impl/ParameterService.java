package com.huiwan.gdata.modules.sys.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.huiwan.gdata.common.bean.page.Query;
import com.huiwan.gdata.common.bean.page.QueryResult;
import com.huiwan.gdata.modules.sys.entity.Parameter;
import com.huiwan.gdata.modules.sys.mapper.ParameterMapper;
import com.huiwan.gdata.modules.sys.service.IParameterService;

@Service
public class ParameterService implements IParameterService {

	@Autowired
	private ParameterMapper ParameterMapper;

	@Override
	public QueryResult<Parameter> getList(int page, int pagesize,
			Parameter parameter) {
		Query query = new Query();
		query.setBean(parameter);
		query.setPageIndex(page);
		query.setPageSize(pagesize);

		// 组合分页信息
		QueryResult<Parameter> queryResult = new QueryResult<Parameter>();
		Long count = ParameterMapper.getCount(query);
		List<Parameter> list = ParameterMapper.queryPages(query);

		// 总页数 和 取多少条
		queryResult.setCurrentPage(page);
		queryResult.setPages(count, pagesize);
		queryResult.setItems(list);
		return queryResult;
	}

	@Override
	public Parameter get(int id) {
		return ParameterMapper.selectByPrimaryKey(id);
	}

	@Override
	public int del(int id) {
		return ParameterMapper.deleteByPrimaryKey(id);
	}

	@Override
	public int add(Parameter parameter) {
		return ParameterMapper.insertSelective(parameter);
	}

	@Override
	public int update(Parameter parameter) {
		return ParameterMapper.updateByPrimaryKeySelective(parameter);
	}

	@Override
	public List<Parameter> getByKeyx(String keyx) {
		List<Parameter> list = ParameterMapper.selectByKeyX(keyx);
		return list;
	}

	@Override
	public String getByKeyxToValue(String keyx) {
		List<Parameter> list = ParameterMapper.selectByKeyX(keyx);

		if (list == null || list.size() <= 0) {

			return list.get(0).getValue();
		}
		return null;
	}

}
