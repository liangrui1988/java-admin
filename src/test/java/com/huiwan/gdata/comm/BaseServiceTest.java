package com.huiwan.gdata.comm;

import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.huiwan.gdata.common.utils.json.JsonUtils;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath*:application-pro1.xml")
public class BaseServiceTest {
	protected Logger logger = LoggerFactory.getLogger(getClass());

	public void printJson(Object o) {
		System.out.println("-----------print json start ---------------\n");
		System.out.println(JsonUtils.toJsonString(o));
		System.out.println("\n-----------print json end ------------------");

	}

}
