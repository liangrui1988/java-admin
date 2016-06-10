package com.rui.pro1.modules.sys.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.rui.pro1.common.bean.page.Query;
import com.rui.pro1.common.bean.page.QueryResult;
import com.rui.pro1.modules.sys.entity.Dict;
import com.rui.pro1.modules.sys.mapper.DictMapper;
import com.rui.pro1.modules.sys.service.IDictService;

@Service
public class DictService implements IDictService {
	@Autowired
	private DictMapper dictMapper;

	@Override
	public QueryResult<Dict> getList(int page, int pagesize, Dict dict) {
		Query query = new Query();
		query.setBean(dict);
		query.setPageIndex(page);
		query.setPageSize(pagesize);

		// 组合分页信息
		QueryResult<Dict> queryResult = new QueryResult<Dict>();
		Long count = dictMapper.getCount(query);
		List<Dict> list = dictMapper.queryPages(query);
		
		// 总页数 和 取多少条
		queryResult.setPages(count, pagesize);
		queryResult.setItems(list);
		return queryResult;
	}

	@Override
	public Dict get(int id) {
		return dictMapper.selectByPrimaryKey(id);
	}

	@Override
	public int del(int id) {
		return dictMapper.deleteByPrimaryKey(id);
	}

	@Override
	public int add(Dict dict) throws Exception {
		return dictMapper.insertSelective(dict);
	}

	@Override
	public int update(Dict dict) {
		return dictMapper.updateByPrimaryKeySelective(dict);
	}

	@Override
	public List<Dict> getByType(String type) {
		return dictMapper.getByType(type);
	}

}
