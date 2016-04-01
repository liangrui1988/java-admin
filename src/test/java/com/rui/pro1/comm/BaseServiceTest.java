package com.rui.pro1.comm;

import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.rui.pro1.common.utils.JsonUtils;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath*:application-pro1.xml")
public class BaseServiceTest {

	public void printJson(Object o) {
		System.out.println("-----------print json start ---------------\n");
		System.out.println(JsonUtils.toJsonString(o));
		System.out.println("\n-----------print json end ------------------");

	}

}
