package com.rui.pro1.sys.service;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.rui.pro1.comm.BaseServiceTest;
import com.rui.pro1.common.bean.page.QueryResult;
import com.rui.pro1.modules.sys.entity.Role;
import com.rui.pro1.modules.sys.entity.User;
import com.rui.pro1.modules.sys.service.IUserService;
import com.rui.pro1.modules.sys.utils.PassUtil;
import com.rui.pro1.modules.sys.vo.UserVo;

public class UserServiceTest extends BaseServiceTest {

	@Autowired
	private IUserService userService;

	@Test
	public void getPage() {
		QueryResult<User> result = userService
				.getUserList(1, 20, new UserVo());
		printJson(result);
	}

	@Test
	public void get() {
		User user=userService.get(1);
		printJson(user);

	}
	@Test
	public void del() {
		int count=userService.del(2);
		System.out.println(count);
	}
	@Test
	public void add() {
		
		User user=new User();
		user.setUserName("admin");
		user.setDepartmentId(2);
		String password=PassUtil.encryptPassword("123456", "admin");
		user.setPassword(password);
//		user.setCreateTime(new Date());
		//关联角色
		List<Role> roles=new ArrayList<Role>();
		Role role=new Role();
		role.setId(1);
		user.setRoles(roles);
		
		int userid=userService.add(user);
		System.out.println(user.getId());
	}

	@Test
	public void update() {
		
		User user=new User();
		user.setId(2);
		user.setUserName("adminUpdate");
		user.setDepartmentId(55);
		String password=PassUtil.encryptPassword("123456", "adminUpdate");
		user.setPassword(password);
		//关联角色
		List<Role> roles=new ArrayList<Role>();
		Role role=new Role();
		role.setId(1);
		user.setRoles(roles);
		user.setStatus(1);
		userService.update(user);

	}

}
