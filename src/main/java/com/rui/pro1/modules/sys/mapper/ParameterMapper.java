package com.rui.pro1.modules.sys.mapper;

import java.util.List;

import com.rui.pro1.common.bean.page.Query;
import com.rui.pro1.modules.sys.entity.Parameter;

public interface ParameterMapper {
	int deleteByPrimaryKey(Integer id);

	int insert(Parameter record);

	int insertSelective(Parameter record);

	Parameter selectByPrimaryKey(Integer id);

	int updateByPrimaryKeySelective(Parameter record);

	int updateByPrimaryKey(Parameter record);

	Long getCount(Query query);

	List<Parameter> queryPages(Query query);

	List<Parameter> selectByKeyX(String keyx);
}