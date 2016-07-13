package com.rui.pro1.email.service;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import org.apache.commons.mail.EmailException;
import org.junit.Test;
import org.springframework.ui.freemarker.FreeMarkerTemplateUtils;
import org.springframework.web.servlet.view.freemarker.FreeMarkerConfigurer;

import com.rui.pro1.comm.BaseServiceTest;
import com.rui.pro1.common.utils.web.EmailUtil;
import com.rui.pro1.common.utils.web.FreeMarkerUtil;

import freemarker.core.ParseException;
import freemarker.template.MalformedTemplateNameException;
import freemarker.template.Template;
import freemarker.template.TemplateException;
import freemarker.template.TemplateNotFoundException;

public class mainServiceTest extends BaseServiceTest {

	@Test
	public void test1() throws TemplateNotFoundException,
			MalformedTemplateNameException, ParseException, IOException,
			TemplateException {
		FreeMarkerConfigurer fc = FreeMarkerUtil.getFreeMarkerConfigurer();
		// fc = FreeMarkerUtil.getFreeMarkerConfigurer();
		// fc = FreeMarkerUtil.getFreeMarkerConfigurer();
		Template tpl = fc.getConfiguration().getTemplate("test.ftl");
		// FreeMarker通过Map传递动态数据
		Map map = new HashMap();
		map.put("hello", "world"); // 注意动态数据的key和模板标签中指定的属性相匹配
		// 解析模板并替换动态数据，最终username将替换模板文件中的${username}标签。
		String htmlText = FreeMarkerTemplateUtils.processTemplateIntoString(
				tpl, map);
		System.out.println(htmlText);
	}
	
	
	@Test
	public void simpleSendTest() throws EmailException{
		EmailUtil.sendContextEmail("simpleSendTest rui", "工具测试subject", "rui_dev@126.com", "rui");
		
	}
	
	
	@Test
	public void ftlSendTest() throws EmailException{
		
		Map<String,Object> map=new HashMap<String,Object>();
		map.put("hello", "world"); 
		EmailUtil.sendFtlEmail(map, "test.ftl", "ftl测试邮件",  "rui_dev@126.com", "rui");
		
	}
	
	
	
	
}
