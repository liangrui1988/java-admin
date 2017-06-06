package com.huiwan.gdata.modules.sys.service;

import com.huiwan.gdata.common.bean.page.QueryResult;
import com.huiwan.gdata.modules.sys.entity.Department;
import com.huiwan.gdata.modules.sys.vo.DepartmentVo;
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
