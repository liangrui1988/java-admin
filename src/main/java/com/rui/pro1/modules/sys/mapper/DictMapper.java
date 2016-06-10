package com.rui.pro1.modules.sys.mapper;

import java.util.List;

import com.rui.pro1.common.bean.page.Query;
import com.rui.pro1.modules.sys.entity.Dict;

public interface DictMapper {
	int deleteByPrimaryKey(Integer id);

	int insert(Dict record);

	int insertSelective(Dict record);

	Dict selectByPrimaryKey(Integer id);

	int updateByPrimaryKeySelective(Dict record);

	int updateByPrimaryKey(Dict record);

	Long getCount(Query query);

	List<Dict> queryPages(Query query);
	
	
	List<Dict> 	getByType(String type);
}