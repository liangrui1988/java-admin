package com.rui.pro1.sys.service;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.rui.pro1.comm.BaseServiceTest;
import com.rui.pro1.modules.sys.bean.UserBean;
import com.rui.pro1.modules.sys.service.impl.UserLoginService;
import com.rui.pro1.modules.sys.vo.UserLoginVo;

public class UserLoginServiceTest extends BaseServiceTest {

	@Autowired
	private UserLoginService userLoginService;

	@Test
	public void login() {
		UserLoginVo userLoginVo=new UserLoginVo();
		userLoginVo.setUserName("admin");
		userLoginVo.setPassword("123456");
		UserBean bean=userLoginService.login(userLoginVo);
		
		printJson(bean);
		
	}

}
