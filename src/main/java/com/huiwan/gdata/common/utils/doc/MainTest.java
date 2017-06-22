package com.huiwan.gdata.common.utils.doc;

import java.io.FileOutputStream;
import java.io.InputStream;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.apache.poi.ss.usermodel.CellStyle;
import org.junit.Test;

import com.huiwan.gdata.modules.sys.entity.Department;
import com.huiwan.gdata.modules.sys.entity.User;

public class MainTest {

	public static void main(String[] args) throws Exception {
		List<User> users = new ArrayList<User>();
		User user = new User();
		Department department=new Department();
		department.setName("技术部");
		department.setTestXls(09.9999);
		department.setBdTest(new BigDecimal(66.666666));
		user.setUserName("林冲t");
		user.setCreateTime(new Date());
		user.setId(1);
		user.setDepartment(department);
		users.add(user);
		User user2 = new User();
		user2.setUserName("林冲t2");
		user2.setCreateTime(new Date());
		user2.setId(2);
		
		Department department2=new Department();
		department2.setName("财务部");
		department2.setTestXls(001.8820);
		department2.setBdTest(new BigDecimal(555.55555));
		user2.setDepartment(department2);
		users.add(user2);

		Map<String, FieldStyle> m = new LinkedHashMap<String, FieldStyle>();
		m.put("id", new FieldStyle("id", CellStyle.ALIGN_LEFT));
		m.put("姓名", new FieldStyle("userName",CellStyle.ALIGN_RIGHT));
		m.put("部门", new FieldStyle("department.name",CellStyle.ALIGN_CENTER));
		m.put("时间", new FieldStyle("createTime",CellStyle.ALIGN_LEFT,"YYYY-MM-dd",10000));
		DecimalFormat df = new java.text.DecimalFormat("##.0000");
		df.setRoundingMode(RoundingMode.FLOOR);
		m.put("小数测试", new FieldStyle("department.testXls",df));
		m.put("钱数字测试", new FieldStyle("department.bdTest",CellStyle.ALIGN_LEFT));

		InputStream is = ExcelDecorator.getXlsIO("2017年度", "数据报表", m, users);
		//InputStream is = excel.getXlsIO( "数据报表", m, users);
		// Write the output to a file
		FileOutputStream fileOut = new FileOutputStream("E:\\workbook2.xls");
		// ByteArrayOutputStream os=null;
		int leng = 0;
		byte[] bytes = new byte[1024]; // 缓存buffer
		// k始读取
		while ((leng = is.read(bytes)) > 0) {
			// 开始写入
			fileOut.write(bytes, 0, leng);
		}

		fileOut.close();

	}

	@Test
	public void tset2() throws Exception {
		ExcelDecorator excel = new ExcelDecorator();

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

		Map<String, FieldStyle> m = new LinkedHashMap<String, FieldStyle>();
		m.put("id", new FieldStyle("id", CellStyle.ALIGN_RIGHT, 100000));
		m.put("姓名", new FieldStyle("userName",CellStyle.ALIGN_RIGHT, 10000));
		m.put("时间", new FieldStyle("createTime",CellStyle.ALIGN_LEFT, 256));

		InputStream is = excel.getXlsIO("2017年度", "数据报表", m, users);

		// Write the output to a file
		FileOutputStream fileOut = new FileOutputStream("E:\\workbook2.xls");

		// ByteArrayOutputStream os=null;
		int leng = 0;
		byte[] bytes = new byte[1024]; // 缓存buffer
		// k始读取
		while ((leng = is.read(bytes)) > 0) {
			// 开始写入
			fileOut.write(bytes, 0, leng);
		}

		fileOut.close();
	}
}
