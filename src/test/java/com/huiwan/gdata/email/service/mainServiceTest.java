package com.huiwan.gdata.email.service;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import org.apache.commons.mail.EmailAttachment;
import org.apache.commons.mail.EmailException;
import org.junit.Test;
import org.springframework.ui.freemarker.FreeMarkerTemplateUtils;
import org.springframework.web.servlet.view.freemarker.FreeMarkerConfigurer;

import com.huiwan.gdata.comm.BaseServiceTest;
import com.huiwan.gdata.common.bean.EmailBean;
import com.huiwan.gdata.common.utils.web.EmailUtil;
import com.huiwan.gdata.common.utils.web.FreeMarkerUtil;

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
	public void simpleSendTest() throws EmailException {
		EmailUtil.sendContextEmail("simpleSendTest rui", "工具测试subject",
				"rui_dev@126.com", "rui");

	}

	@Test
	public void ftlSendTest() throws EmailException {

		Map<String, Object> map = new HashMap<String, Object>();
		map.put("hello", "world");
		EmailUtil.sendFtlEmail(map, "test.ftl", "ftl测试邮件", "rui_dev@126.com",
				"rui");

	}

	@Test
	public void sendBeanTest() throws EmailException {
		EmailBean email = new EmailBean();
		email.setContext("world hao");
		email.setToEmail("rui_dev@126.com");
		email.setSubject("worldx");
		EmailUtil.sendEmailToBean(email);

	}

	/**
	 * 附件测试
	 * 
	 * @throws EmailException
	 */
	@Test
	public void sendBeanTest2() throws EmailException {
		EmailBean email = new EmailBean();
		email.setContext("world hao");
		email.setToEmail("rui_dev@126.com");
		email.setSubject("worldx");

		EmailAttachment emailAttachment = new EmailAttachment();
		emailAttachment
				.setPath("C:\\Users\\Public\\Pictures\\Sample Pictures\\200811241158539655.jpg");

		email.setEmailAttachment(emailAttachment);
		EmailUtil.sendEmailToBean(email);

	}

	/**
	 * ftl测试
	 * 
	 * @throws EmailException
	 */
	@Test
	public void sendBeanTest3() throws EmailException {
		EmailBean email = new EmailBean();
		email.setContext("world hao");
		email.setToEmail("rui_dev@126.com");
		email.setSubject("worldx");
		email.setFtlName("test.ftl");
		// email.setFtlName("test2.ftl"); 纯ftl内容

		Map<String, Object> map = new HashMap<String, Object>();
		map.put("hello", "world");
		email.setMap(map);

		EmailUtil.sendEmailToBean(email);

	}

	/**
	 * ftl测试 +附件
	 * 
	 * @throws EmailException
	 */
	@Test
	public void sendBeanTest4() throws EmailException {
		EmailBean email = new EmailBean();
		email.setContext("world hao");
		email.setToEmail("rui_dev@126.com");
		email.setSubject("worldx");
		email.setFtlName("test.ftl");
		// email.setFtlName("test2.ftl"); 纯ftl内容

		Map<String, Object> map = new HashMap<String, Object>();
		map.put("hello", "world");
		email.setMap(map);
		//
		EmailAttachment emailAttachment = new EmailAttachment();
		emailAttachment
				.setPath("C:\\Users\\Public\\Pictures\\Sample Pictures\\200811241158539655.jpg");

		email.setEmailAttachment(emailAttachment);
		EmailUtil.sendEmailToBean(email);

	}

}
