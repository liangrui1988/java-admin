package com.huiwan.gdata.sys.service;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.huiwan.gdata.comm.BaseServiceTest;
import com.huiwan.gdata.common.bean.page.QueryResult;
import com.huiwan.gdata.modules.sys.bean.UserBean;
import com.huiwan.gdata.modules.sys.entity.Role;
import com.huiwan.gdata.modules.sys.entity.User;
import com.huiwan.gdata.modules.sys.service.IUserService;
import com.huiwan.gdata.modules.sys.utils.PassUtil;
import com.huiwan.gdata.modules.sys.vo.UserVo;

public class UserServiceTest extends BaseServiceTest {

	@Autowired
	private IUserService userService;
	


	@Test
	public void getPage() {
		QueryResult<UserBean> result = userService
				.getUserList(1, 20, new UserVo());
		printJson(result);
	}

	@Test
	public void get() {
		UserBean user=userService.get(1);
		printJson(user);

	}
	@Test
	public void del() {
		int count=userService.del(2);
		System.out.println(count);
	}
	@Test
	public void add() throws Exception {
		
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
		user.setId(1);
		user.setUserName("adminUpdate");
		user.setDepartmentId(55);
		String password=PassUtil.encryptPassword("123456", "adminUpdate");
		user.setPassword(password);
		//关联角色
		List<Role> roles=new ArrayList<Role>();
		Role role=new Role();
		role.setId(2);
		roles.add(role);
		
		user.setRoles(roles);
		user.setStatus(1);
		userService.update(user);

	}

}
