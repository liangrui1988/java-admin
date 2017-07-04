package com.huiwan.gdata.modules.sys.service.impl;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.huiwan.gdata.common.bean.page.Query;
import com.huiwan.gdata.common.bean.page.QueryResult;
import com.huiwan.gdata.modules.sys.entity.Dict;
import com.huiwan.gdata.modules.sys.exception.ObjectExistException;
import com.huiwan.gdata.modules.sys.mapper.GameDictMapper;
import com.huiwan.gdata.modules.sys.service.IGameDictService;

@Service
public class GameDictService implements IGameDictService {
	@Autowired
	private GameDictMapper dictMapper;

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
		queryResult.setCurrentPage(page);
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
		
		if(dict==null||StringUtils.isBlank(dict.getType())||StringUtils.isBlank(dict.getName())||StringUtils.isBlank(dict.getValue())){
			return 0;
		}
		List<Dict> dicts=dictMapper.getByType(dict.getType());
		if(dicts!=null&&dicts.size()>0)
		{
			for(Dict d:dicts){
				if(dict.getName().equals(d.getName())||dict.getValue().equals(d.getValue())){
					throw new ObjectExistException(dict.getType()+"名称和值已存在！");
				}
			}
		}
		
		
		
		
		return dictMapper.insertSelective(dict);
	}

	@Override
	public int update(Dict dict) throws ObjectExistException {
		
		if(dict==null||StringUtils.isBlank(dict.getType())||StringUtils.isBlank(dict.getName())||StringUtils.isBlank(dict.getValue())){
			return 0;
		}
		List<Dict> dicts=dictMapper.getByType(dict.getType());
		if(dicts!=null&&dicts.size()>0)
		{
			for(Dict d:dicts){
				if(dict.getId()==d.getId()){
					continue;
				}
				if(dict.getName().equals(d.getName())||dict.getValue().equals(d.getValue())){
					throw new ObjectExistException(dict.getType()+"名称和值已存在！");
				}
			}
		}
		
		return dictMapper.updateByPrimaryKeySelective(dict);
	}

	@Override
	public List<Dict> getByType(String type) {
		return dictMapper.getByType(type);
	}

	@Override
	public Map<String, String> getByTypeMaps(String type) {
		Map<String, String> mpas=new LinkedHashMap<String, String>();
		List<Dict>  dicts= dictMapper.getByType(type);
		if(dicts!=null&&dicts.size()>0){
			for(Dict dict:dicts){
				mpas.put(dict.getValue(), dict.getName());
			}
		}

		return mpas;
	}

	
	
	public Map<String, Dict> getByTypeMapsDicts(String type) {
		Map<String, Dict> mpas=new LinkedHashMap<String, Dict>();
		List<Dict>  dicts= dictMapper.getByType(type);
		if(dicts!=null&&dicts.size()>0){
			for(Dict dict:dicts){
				mpas.put(dict.getValue(), dict);
			}
		}

		return mpas;
	}
	
}
