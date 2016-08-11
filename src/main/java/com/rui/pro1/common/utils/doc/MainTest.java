package com.rui.pro1.common.utils.doc;

import java.io.FileOutputStream;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Date;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import com.rui.pro1.modules.sys.entity.User;


public class MainTest {

	public static void main(String[] args) throws Exception {
		
		ExcelDecorator excel=new ExcelDecorator();
		
		List<User> users=new ArrayList<User>();
		
		User user=new User();
		user.setUserName("林冲");
		user.setCreateTime(new Date());
		user.setId(1);
		users.add(user);
		

		User user2=new User();
		user2.setUserName("林冲2");
		user2.setCreateTime(new Date());
		user2.setId(2);
		users.add(user2);
		
		
		 Map<String, FieldStyle> m = new LinkedHashMap<String, FieldStyle>(); 
		 
		 
        
         m.put("姓名", new FieldStyle("userName"));  
         m.put("时间", new FieldStyle("createTime"));  
         m.put("id", new FieldStyle("id"));  
		
		 InputStream is = excel.getXlsIO("2017年度","数据报表", m, users, User.class); 
		
		 // Write the output to a file
	    FileOutputStream fileOut = new FileOutputStream("E:\\workbook2.xls");
	   
	    
	    // ByteArrayOutputStream os=null;  
        int leng = 0;  
        byte[] bytes = new byte[1024]; // 缓存buffer  
	    // k始读取  
        while ((leng = is.read(bytes)) > 0)  
        {  
            // 开始写入  
        	fileOut.write(bytes, 0, leng);  
        }  
        
	    fileOut.close();
		
	}
}
