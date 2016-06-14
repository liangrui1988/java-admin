package com.rui.pro1.modules.sys.service;

import java.util.List;

import com.rui.pro1.common.bean.page.QueryResult;
import com.rui.pro1.modules.sys.entity.Dict;
import com.rui.pro1.modules.sys.exception.ObjectExistException;

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

}
