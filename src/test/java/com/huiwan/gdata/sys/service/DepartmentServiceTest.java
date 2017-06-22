package com.huiwan.gdata.sys.service;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.huiwan.gdata.comm.BaseServiceTest;
import com.huiwan.gdata.common.bean.page.QueryResult;
import com.huiwan.gdata.modules.sys.entity.Department;
import com.huiwan.gdata.modules.sys.service.IDepartmentService;
import com.huiwan.gdata.modules.sys.vo.DepartmentVo;

public class DepartmentServiceTest extends BaseServiceTest {

	@Autowired
	private IDepartmentService departmentService;

	@Test
	public void getPage() {
		QueryResult<Department> result = departmentService.getDepartmentList(1,
				20, new DepartmentVo());
		printJson(result);
	}

	@Test
	public void get() {
		Department department = departmentService.get(1);
		printJson(department);

	}

	@Test
	public void del() {
		int count = departmentService.del(2);
		System.out.println(count);
	}

	@Test
	public void add() {
		Department department = new Department();
		department.setName("技术部");
		department.setSort(1);
		department.setParentId(0);
		department.setRemake("remake");
		departmentService.add(department);
	}

	@Test
	
	public void update() {

		Department department = new Department();
		department.setId(2);
		department.setName("update");
		department.setSort(2);
		department.setParentId(0);
		department.setStatus(1);
		department.setRemake("remake update");


		departmentService.update(department);
	}

}
