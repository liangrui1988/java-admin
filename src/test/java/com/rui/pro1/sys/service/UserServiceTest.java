package com.rui.pro1.sys.service;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.rui.pro1.comm.BaseServiceTest;
import com.rui.pro1.common.bean.page.QueryResult;
import com.rui.pro1.modules.sys.entity.User;
import com.rui.pro1.modules.sys.service.IUserService;
import com.rui.pro1.modules.sys.vo.UserVo;

public class UserServiceTest extends BaseServiceTest {

	@Autowired
	private IUserService iUserService;

	@Test
	public void getPage() {
		QueryResult<User> result = iUserService
				.getUserList(1, 20, new UserVo());
		printJson(result);
	}

}
