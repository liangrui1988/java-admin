package com.rui.pro1.sys.controller;

import org.junit.Test;

import com.rui.pro1.comm.BaseConatrollerTest;

public class UserLoginControllerTest extends BaseConatrollerTest  {

	@Test
	public void simpleLoginTeset(){
		String url=http+"/demo/sys/user/login?";
		String parma="password=123456&userName=admin";
		String result=postReq(url,parma);
		printResult(result);
	}
}
