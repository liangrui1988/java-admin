package com.rui.pro1.modules.sys.service;

import java.util.List;

import com.rui.pro1.common.bean.page.QueryResult;
import com.rui.pro1.modules.sys.entity.Parameter;

public interface IParameterService {
	QueryResult<Parameter> getList(int page, int pagesize, Parameter parameter);

	Parameter get(int id);

	int del(int id);

	int add(Parameter parameter);

	int update(Parameter parameter);

	List<Parameter> getByKeyx(String keyx);

	String getByKeyxToValue(String keyx);

}
