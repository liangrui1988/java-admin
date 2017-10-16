package com.huiwan.gdata.sys.service;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.huiwan.gdata.comm.BaseServiceTest;
import com.huiwan.gdata.modules.sys.entity.Department;
import com.huiwan.gdata.modules.sys.entity.Menu;
import com.huiwan.gdata.modules.sys.entity.Role;
import com.huiwan.gdata.modules.sys.entity.User;
import com.huiwan.gdata.modules.sys.mapper.SystemMapper;
import com.huiwan.gdata.modules.sys.service.IDepartmentService;
import com.huiwan.gdata.modules.sys.service.IMenuService;
import com.huiwan.gdata.modules.sys.service.IRoleService;
import com.huiwan.gdata.modules.sys.service.IUserService;
import com.huiwan.gdata.modules.sys.utils.PassUtil;

public class InitSysDataServiceTest extends BaseServiceTest {
	
	boolean isDel=false;
	
	@Autowired
	private IUserService userService;
	
	@Autowired
	private IRoleService roleService;
	
	
	@Autowired
	private IMenuService menuService;
	
	@Autowired
	private IDepartmentService departmentService;
	
	@Autowired
	private SystemMapper systemMapper;
//	@Autowired
//	private PassUtil PassUtil;
	
	
	@Test
	public void truncate(){
		systemMapper.truncateAllRBAC();

	}
	
	@Test
	public void initData() throws Exception{
		if(isDel){ //truncate table
			systemMapper.truncateAllRBAC();
		}
		//部门
		Department department = new Department();
		department.setName("技术部");
		department.setSort(1);
		department.setParentId(0);
		department.setRemake("remake");
		departmentService.add(department);
		//分置菜单 
		Menu menu=new Menu();
		menu.setName("系统管理");
		menu.setParentId("0");
		menu.setPermission("sys:*");
		menu.setSortNo(1);
		menu.setTypes("1");
		menu.setIcon("a");
		menu.setHref("/sys/menu/list");
		menuService.add(menu);
		//角色
		Role role=new Role();
		role.setName("系统管理员");
		role.setRemake("remake");
		List<String> menuIds=new ArrayList<String>();
		menuIds.add(menu.getId());
		role.setMenuIds(menuIds);
		roleService.add(role);
		//用户
		User user=new User();
		user.setUserName("admin");
		String password=PassUtil.encryptPassword("123456", "admin");
		user.setPassword(password);
		//关联角色
		List<Role> roles=new ArrayList<Role>();
		Role role2=new Role();
		role2.setId(role.getId());
		roles.add(role2);
		user.setRoles(roles);
		//部门
		user.setDepartmentId(department.getId());
		userService.add(user);
		System.out.println("userId>>>"+user.getId());
	}
	

}
