package com.rui.pro1.email;

import org.apache.commons.mail.DefaultAuthenticator;
import org.apache.commons.mail.Email;
import org.apache.commons.mail.EmailException;
import org.apache.commons.mail.SimpleEmail;
import org.junit.Test;

public class SimpleEmailTest {

	public static void main(String[] args) throws EmailException {
		Email email = new SimpleEmail();
		email.setHostName("smtp.126.com");
		email.setSmtpPort(25);

//		email.setHostName("smtp.googlemail.com");
//		email.setSmtpPort(465);@126.com
		email.setAuthenticator(new DefaultAuthenticator("rui_dev", "ruidev123456"));
		email.setSSLOnConnect(true);
		//ruidev123456
		
		email.setFrom("rui_dev@126.com");
		email.setSubject("TestMail");
	
		email.setMsg("This is a test mail ... :-)");
		email.addTo("liangrui_1988@126.com");//1067165280@qq.com
		email.send();
		System.out.println("xxx");
	}
	
	@Test
	public void test1() throws EmailException{
		Email email = new SimpleEmail();
		email.setHostName("pop.126.com	");
		email.setSmtpPort(110);
		email.setSslSmtpPort("110");

//		email.setHostName("smtp.googlemail.com");
//		email.setSmtpPort(465);
		email.setAuthenticator(new DefaultAuthenticator("rui_dev@126.com", "rui_dev123456"));
		email.setSSLOnConnect(true);
		
		
		email.setFrom("1067165280@qq.com");
		email.setSubject("TestMail");
	
		email.setMsg("This is a test mail ... :-)");
		email.addTo("382453602@qq.com");
		email.send();
		
	}

}
