package com.huiwan.gdata.sys.service;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.huiwan.gdata.comm.BaseServiceTest;
import com.huiwan.gdata.modules.sys.bean.UserBean;
import com.huiwan.gdata.modules.sys.service.impl.UserLoginService;
import com.huiwan.gdata.modules.sys.vo.UserLoginVo;

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
