package com.huiwan.gdata.common.utils.web;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.mail.MessagingException;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.FileSystemXmlApplicationContext;
import org.springframework.ui.freemarker.FreeMarkerTemplateUtils;
import org.springframework.web.servlet.view.freemarker.FreeMarkerConfigurer;

import com.huiwan.gdata.common.utils.spring.SysApplicationContext;

import freemarker.core.ParseException;
import freemarker.template.MalformedTemplateNameException;
import freemarker.template.Template;
import freemarker.template.TemplateException;
import freemarker.template.TemplateNotFoundException;

//@Component
public class FreeMarkerUtil {

	private static FreeMarkerConfigurer freeMarkerConfigurer;

	/**
	 * 获取 FreeMarkerConfigurer
	 * 
	 * @return
	 */
	public static FreeMarkerConfigurer getFreeMarkerConfigurer() {
		// System.out.println("freeMarkerConfigurer:>>>>>"+freeMarkerConfigurer);
		if (freeMarkerConfigurer == null) {
			freeMarkerConfigurer = (FreeMarkerConfigurer) SysApplicationContext
					.getBean("freeMarker");
		}
		return freeMarkerConfigurer;
	}

	public static void main(String[] args) throws MessagingException,
			TemplateNotFoundException, MalformedTemplateNameException,
			ParseException, IOException, TemplateException {
		ApplicationContext ctx = new FileSystemXmlApplicationContext(
				"src/main/resources/application-pro1.xml");
		FreeMarkerConfigurer config = (FreeMarkerConfigurer) ctx
				.getBean("freeMarker");
		Template tpl = config.getConfiguration().getTemplate("test.ftl");
		// FreeMarker通过Map传递动态数据
		Map map = new HashMap();
		map.put("username", "hello world"); // 注意动态数据的key和模板标签中指定的属性相匹配
		// 解析模板并替换动态数据，最终username将替换模板文件中的${username}标签。
		String htmlText = FreeMarkerTemplateUtils.processTemplateIntoString(
				tpl, map);
		System.out.println(htmlText);

	}

}
