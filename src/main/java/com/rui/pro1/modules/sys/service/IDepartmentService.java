package com.rui.pro1.modules.sys.service;

import com.rui.pro1.common.bean.page.QueryResult;
import com.rui.pro1.modules.sys.entity.Department;
import com.rui.pro1.modules.sys.vo.DepartmentVo;
/**
 * 
 * @author ruiliang
 *
 */
public interface IDepartmentService {
	QueryResult<Department> getDepartmentList(int page, int pagesize,DepartmentVo departmentVo);

	Department get(int id);

	int del(int id);

	int add(Department department);

	int update(Department department);
}
