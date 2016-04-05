package com.rui.pro1.modules.sys.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.rui.pro1.common.bean.page.Query;
import com.rui.pro1.common.bean.page.QueryResult;
import com.rui.pro1.modules.sys.entity.Role;
import com.rui.pro1.modules.sys.mapper.RoleMapper;
import com.rui.pro1.modules.sys.service.IRoleService;
import com.rui.pro1.modules.sys.vo.RoleVo;
@Service
public class RoleService implements IRoleService {
	@Autowired
	private RoleMapper roleMapper;

	@Override
	public QueryResult<Role> getRoleList(int page, int pagesize, RoleVo roleVo) {
		Query query = new Query();
		query.setBean(roleVo);
		query.setPageIndex(page);

		// 组合分页信息
		QueryResult<Role> queryResult = new QueryResult<Role>();
		Long count = roleMapper.getCount(query);
		List<Role> list = roleMapper.queryPages(query);
		// 总页数 和 取多少条
		queryResult.setPages(count, pagesize);
		queryResult.setItems(list);

		return queryResult;
	}

	@Override
	public Role get(int id) {
		return roleMapper.get(id);
	}

	@Override
	public int del(int id) {
		return roleMapper.del(id);
	}

	@Override
	public int add(Role role) {
		return roleMapper.add(role);
	}

	@Override
	public int update(Role role) {
		return roleMapper.update(role);
	}

}
