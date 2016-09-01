package com.rui.pro1.common.utils.doc;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.Date;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.junit.Test;

import com.rui.pro1.modules.sys.entity.User;
import com.univocity.parsers.common.processor.BeanWriterProcessor;
import com.univocity.parsers.csv.CsvWriter;
import com.univocity.parsers.csv.CsvWriterSettings;

public class CVSMain {

	public static void main(String[] args) throws UnsupportedEncodingException, FileNotFoundException {

		CsvWriterSettings settings = new CsvWriterSettings();
		settings.setNullValue("?");
		settings.setRowWriterProcessor(new BeanWriterProcessor<User>(User.class));
		settings.setHeaders("标题", "password", "email");

		settings.setIgnoreLeadingWhitespaces(false);
		settings.setIgnoreTrailingWhitespaces(false);

		File file = new File("d:/user.csv");
		CsvWriter writer = new CsvWriter(new OutputStreamWriter(new FileOutputStream(file), "gbk"), settings);
		writer.writeHeaders();

		User u = new User();
		u.setUserName("林冲");
		u.setPassword("123456");
		u.setEmail("中文测试");

		 writer.writeRow(u.getUserName());
		 writer.writeRow(u.getPassword());
		 writer.writeRow(u.getEmail());

		// writer.processRecord(u);

		writer.close();

		System.out.println("output....");

	}

	@Test
	public void test2() throws Exception {

		List<User> users = new ArrayList<User>();

		User user = new User();
		user.setUserName("林冲t");
		user.setCreateTime(new Date());
		user.setId(1);
		users.add(user);

		User user2 = new User();
		user2.setUserName("林冲t2");
		user2.setCreateTime(new Date());
		user2.setId(2);
		users.add(user2);

		Map<String, CSVFieldStyle> m = new LinkedHashMap<String, CSVFieldStyle>();
		m.put("id", new CSVFieldStyle("id"));
		m.put("姓名", new CSVFieldStyle("userName"));
		m.put("时间", new CSVFieldStyle("createTime"));


		// Write the output to a file
		FileOutputStream fileOut = new FileOutputStream("E:\\csvTest.csv");
		
		OutputStream os = CSVExport.getCSVIO(m, users,fileOut);



		os.close();
	}

}
