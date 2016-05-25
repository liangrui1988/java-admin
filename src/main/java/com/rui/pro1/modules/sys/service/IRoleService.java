package com.rui.pro1.modules.sys.service;

import java.util.List;

import com.rui.pro1.common.bean.page.QueryResult;
import com.rui.pro1.modules.sys.bean.RoleBean;
import com.rui.pro1.modules.sys.entity.Role;
import com.rui.pro1.modules.sys.vo.RoleVo;

public interface IRoleService {

	QueryResult<Role> getRoleList(int page, int pagesize, RoleVo roleVo);
	
	List<Role> getRoleListAll();


	RoleBean get(int id);

	int del(int id);

	int add(Role role);

	int update(Role role);
}
