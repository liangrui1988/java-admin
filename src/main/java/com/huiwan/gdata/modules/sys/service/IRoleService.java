package com.huiwan.gdata.modules.sys.service;

import java.util.List;

import com.huiwan.gdata.common.bean.page.QueryResult;
import com.huiwan.gdata.modules.sys.bean.RoleBean;
import com.huiwan.gdata.modules.sys.entity.Role;
import com.huiwan.gdata.modules.sys.entity.RoleGroup;
import com.huiwan.gdata.modules.sys.exception.ObjectExistException;
import com.huiwan.gdata.modules.sys.vo.RoleVo;

public interface IRoleService {

	QueryResult<Role> getRoleList(int page, int pagesize, RoleVo roleVo);
	
	List<Role> getRoleListAll();


	RoleBean get(int id);

	int del(int id);
	
	Role getByName(String name);


	int add(Role role)throws ObjectExistException;

	int update(Role role);

	List<RoleGroup> getGroupListAll();
}
