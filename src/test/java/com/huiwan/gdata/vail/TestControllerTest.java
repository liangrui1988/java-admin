package com.huiwan.gdata.vail;

import org.junit.Test;

import com.huiwan.gdata.comm.BaseConatrollerTest;

/**
 * 
 * @author ruiliang
 * @date 2016/04/05
 *
 */
public class TestControllerTest extends BaseConatrollerTest {

	String http = "http://localhost:9808/";


	
	@Test
	public void getVailTest() {
		String uri = "test/testVaildate";
		
		StringBuffer sb=new StringBuffer();
		sb.append(http);
		sb.append(uri);
		sb.append("?");
		sb.append("hello=world");
		sb.append("&size=1000");
		sb.append("&name=abcdNAme");
		sb.append("&size2=1");
		sb.append("&contacterPhone=12");
		sb.append("&size=1000");
		sb.append("&sizeo=1");
		sb.append("&bd=3.66");

		String result = super.getReq(sb.toString());
		System.out.println(result);
	}
}
