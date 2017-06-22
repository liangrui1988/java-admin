package com.huiwan.gdata.modules.sys.service;

import java.util.List;
import java.util.Map;

import com.huiwan.gdata.common.bean.page.QueryResult;
import com.huiwan.gdata.modules.sys.entity.Dict;
import com.huiwan.gdata.modules.sys.exception.ObjectExistException;

/**
 * 
 * @author ruiliang
 *
 */
public interface IDictService {

	QueryResult<Dict> getList(int page, int pagesize, Dict dict);

	Dict get(int id);

	int del(int id);

	int add(Dict dict) throws Exception;

	int update(Dict dict)  throws ObjectExistException;

	List<Dict> getByType(String type);

	Map<String,String> getByTypeMaps(String type);
}
