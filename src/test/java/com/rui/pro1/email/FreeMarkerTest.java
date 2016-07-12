package com.rui.pro1.email;

import java.io.File;
import java.io.IOException;
import java.io.Reader;
import java.io.StringWriter;
import java.util.HashMap;
import java.util.Map;

import org.apache.commons.io.IOUtils;
import org.junit.Assert;
import org.junit.Test;

import freemarker.cache.ClassTemplateLoader;
import freemarker.cache.FileTemplateLoader;
import freemarker.cache.MultiTemplateLoader;
import freemarker.cache.StringTemplateLoader;
import freemarker.cache.TemplateLoader;
import freemarker.core.ParseException;
import freemarker.template.Configuration;
import freemarker.template.MalformedTemplateNameException;
import freemarker.template.Template;
import freemarker.template.TemplateException;
import freemarker.template.TemplateNotFoundException;

public class FreeMarkerTest {

	public static void main(String[] args) {
		System.out.println( System.getProperty("user.dir"));
	}

	
	@Test
	public void test1() throws TemplateNotFoundException, MalformedTemplateNameException, ParseException, IOException, TemplateException {
		Configuration cfg = new Configuration();
		StringTemplateLoader stringLoader = new StringTemplateLoader();
		String templateContent = "欢迎：${name}";
		stringLoader.putTemplate("myTemplate", templateContent);

		cfg.setTemplateLoader(stringLoader);

		Template template = cfg.getTemplate("myTemplate", "utf-8");
		Map root = new HashMap();
		root.put("name", "hello");

		StringWriter writer = new StringWriter();

		template.process(root, writer);
		System.out.println(writer.toString());

	}
	
	
	 @Test
	  public void testMultiTL() throws IOException {
	    TemplateLoader ctl = new ClassTemplateLoader(FreeMarkerTest.class,
	        "/");
	    TemplateLoader ftl1 = new FileTemplateLoader(new File(
	        System.getProperty("user.dir")));
	    
	    System.out.println( ctl.findTemplateSource("test.ftl"));

	    MultiTemplateLoader mtl = new MultiTemplateLoader(new TemplateLoader[] {
	        ftl1 });//,ctl 

	    Object source = mtl.findTemplateSource("test.ftl");
	    Reader reader = mtl.getReader(source, "utf-8");
	    String dest = IOUtils.toString(reader);
	    
	    System.out.println(dest);
//	    Assert.assertEquals("${hello}", dest);
	  }

}
