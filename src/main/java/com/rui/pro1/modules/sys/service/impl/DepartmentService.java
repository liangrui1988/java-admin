package com.rui.pro1.modules.sys.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.rui.pro1.common.bean.page.Query;
import com.rui.pro1.common.bean.page.QueryResult;
import com.rui.pro1.modules.sys.entity.Department;
import com.rui.pro1.modules.sys.mapper.DepartmentMapper;
import com.rui.pro1.modules.sys.service.IDepartmentService;
import com.rui.pro1.modules.sys.vo.DepartmentVo;
@Service
public class DepartmentService implements IDepartmentService {

	@Autowired
	private DepartmentMapper departmentMapper;

	@Override
	public QueryResult<Department> getDepartmentList(int page, int pagesize,
			DepartmentVo departmentVo) {
		Query query = new Query();
		query.setBean(departmentVo);
		query.setPageIndex(page);

		// 组合分页信息
		QueryResult<Department> queryResult = new QueryResult<Department>();
		Long count = departmentMapper.getCount(query);
		List<Department> list = departmentMapper.queryPages(query);
		// 总页数 和 取多少条
		queryResult.setPages(count, pagesize);
		queryResult.setItems(list);

		return queryResult;
	}

	@Override
	public Department get(int id) {
		return departmentMapper.get(id);
	}

	@Override
	public int del(int id) {
		return departmentMapper.del(id);
	}

	@Override
	public int add(Department department) {
		return departmentMapper.insertSelective(department);
	}

	@Override
	public int update(Department department) {
		return departmentMapper.updateByPrimaryKeySelective(department);
	}

}
