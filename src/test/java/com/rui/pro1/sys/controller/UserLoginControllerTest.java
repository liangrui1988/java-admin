package com.rui.pro1.sys.controller;

import org.junit.Test;

import com.rui.pro1.comm.BaseControllerTest;

public class UserLoginControllerTest extends BaseControllerTest  {

	@Test
	public void simpleLogin(){
		String url=http+"/demo/sys/user/login?";
		String parma="password=123456&userName=admin";
		String result=postReq(url,parma);
		printResult(result);
	}
}
