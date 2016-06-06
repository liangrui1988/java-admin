package com.rui.pro1.sys.service;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.rui.pro1.comm.BaseServiceTest;
import com.rui.pro1.common.bean.page.QueryResult;
import com.rui.pro1.modules.sys.bean.RoleBean;
import com.rui.pro1.modules.sys.entity.Role;
import com.rui.pro1.modules.sys.exception.ObjectExistException;
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
		RoleBean user=roleService.get(1);
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
		try {
			roleService.add(role);
		} catch (ObjectExistException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Test
	public void update() {
		
		Role role=new Role();
		role.setId(1);
		role.setName("update");
		role.setStatus(1);
		
		List<String> menuIds=new ArrayList<String>();
		menuIds.add("6");
		menuIds.add(7+"");
		menuIds.add(8+"");
		role.setMenuIds(menuIds);
		roleService.update(role);
	}

}
