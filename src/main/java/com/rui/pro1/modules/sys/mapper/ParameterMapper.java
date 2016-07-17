package com.rui.pro1.modules.sys.mapper;

import com.rui.pro1.modules.sys.entity.Parameter;

public interface ParameterMapper {
	int deleteByPrimaryKey(Integer id);

	int insert(Parameter record);

	int insertSelective(Parameter record);

	Parameter selectByPrimaryKey(Integer id);

	int updateByPrimaryKeySelective(Parameter record);

	int updateByPrimaryKey(Parameter record);
}