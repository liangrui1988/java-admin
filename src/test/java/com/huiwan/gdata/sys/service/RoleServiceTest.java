package com.huiwan.gdata.sys.service;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.huiwan.gdata.comm.BaseServiceTest;
import com.huiwan.gdata.common.bean.page.QueryResult;
import com.huiwan.gdata.modules.sys.bean.RoleBean;
import com.huiwan.gdata.modules.sys.entity.Role;
import com.huiwan.gdata.modules.sys.exception.ObjectExistException;
import com.huiwan.gdata.modules.sys.service.IRoleService;
import com.huiwan.gdata.modules.sys.vo.RoleVo;

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
