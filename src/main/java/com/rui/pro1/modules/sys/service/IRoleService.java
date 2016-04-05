package com.rui.pro1.modules.sys.service;

import com.rui.pro1.common.bean.page.QueryResult;
import com.rui.pro1.modules.sys.entity.Role;
import com.rui.pro1.modules.sys.vo.RoleVo;

public interface IRoleService {

	QueryResult<Role> getRoleList(int page, int pagesize, RoleVo roleVo);

	Role get(int id);

	int del(int id);

	int add(Role role);

	int update(Role role);
}
