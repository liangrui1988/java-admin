package com.huiwan.gdata.sys.controller;

import org.junit.Test;

import com.huiwan.gdata.comm.BaseConatrollerTest;

/**
 * 
 * @author ruiliang
 * @date 2016/04/05
 *
 */
public class UserControllerTest extends BaseConatrollerTest {

	String http = "http://localhost:9808/";

	@Test
	public void getUsers() {
		String uri = "sys/user/list";
		String result = super.getReq(http + uri);
		System.out.println(result);
	}
	
}
