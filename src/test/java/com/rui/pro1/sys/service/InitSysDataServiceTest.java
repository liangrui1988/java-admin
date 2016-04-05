package com.rui.pro1.sys.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.rui.pro1.comm.BaseServiceTest;
import com.rui.pro1.modules.sys.entity.Department;
import com.rui.pro1.modules.sys.entity.Role;
import com.rui.pro1.modules.sys.entity.User;
import com.rui.pro1.modules.sys.service.IDepartmentService;
import com.rui.pro1.modules.sys.service.IMenuService;
import com.rui.pro1.modules.sys.service.IRoleService;
import com.rui.pro1.modules.sys.service.IUserService;
import com.rui.pro1.modules.sys.utils.PassUtil;

public class InitSysDataServiceTest extends BaseServiceTest {
	
	boolean isDel=true;
	@Autowired
	private IUserService userService;
	
	@Autowired
	private IRoleService roleService;
	
	
	@Autowired
	private IMenuService menuService;
	
	@Autowired
	private IDepartmentService departmentService;
	
	
	
	public void initData(){
//		if(isDel){ //truncate table
//		}
		
		Department department = new Department();
		department.setName("技术部");
		department.setSort(1);
		department.setParentId(0);
		department.setRemake("remake");
		departmentService.add(department);
		
		
		
		Role role=new Role();
		role.setName("系统管理");
		role.setRemake("remake");
		roleService.add(role);
		
		
		User user=new User();
		user.setUserName("admin");
		user.setDepartmentId(2);
		String password=PassUtil.encryptPassword("123456", "admin");
		user.setPassword(password);
		//关联角色
		List<Role> roles=new ArrayList<Role>();
		Role role2=new Role();
		role2.setId(1);
		user.setRoles(roles);
		
		
		
		
		
	}
	

}
