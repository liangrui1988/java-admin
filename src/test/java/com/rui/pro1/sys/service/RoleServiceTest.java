package com.rui.pro1.sys.service;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.rui.pro1.comm.BaseServiceTest;
import com.rui.pro1.common.bean.page.QueryResult;
import com.rui.pro1.modules.sys.entity.Role;
import com.rui.pro1.modules.sys.service.IRoleService;
import com.rui.pro1.modules.sys.vo.RoleVo;

public class RoleServiceTest extends BaseServiceTest {

	@Autowired
	private IRoleService roleService;

	@Test
	public void getPage() {
		QueryResult<Role> result = roleService
				.getRoleList(1, 20, new RoleVo());
		printJson(result);
	}

	@Test
	public void get() {
		Role user=roleService.get(1);
		printJson(user);

	}
	@Test
	public void del() {
		int count=roleService.del(1);
		System.out.println(count);
	}
	@Test
	public void add() {
		
		Role role=new Role();
		role.setName("系统管理");
		role.setRemake("remake");
		roleService.add(role);
	}

	@Test
	public void update() {
		
		Role role=new Role();
		role.setId(2);
		role.setName("update");
		role.setStatus(1);
		roleService.update(role);
	}

}
