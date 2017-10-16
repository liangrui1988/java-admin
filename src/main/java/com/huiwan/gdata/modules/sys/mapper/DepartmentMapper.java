package com.huiwan.gdata.modules.sys.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.huiwan.gdata.common.bean.page.Query;
import com.huiwan.gdata.modules.sys.entity.Department;
import com.huiwan.gdata.modules.sys.entity.Menu;

public interface DepartmentMapper {

	List<Department> queryPages(Query query);

	Long getCount(Query query);

	Department get(@Param("id") int id);

	int del(int id);

	//int add(Department department);

	//int update(Department department);
	
	
	int insert(Department department);
	int insertSelective(Department department);
	int update(Department department);
	int updateByPrimaryKeySelective(Department department);
	
}
