package com.huiwan.gdata.email;

import org.apache.commons.mail.DefaultAuthenticator;
import org.apache.commons.mail.Email;
import org.apache.commons.mail.EmailException;
import org.apache.commons.mail.SimpleEmail;

public class MethodEmialTest {
	
	public final static String hostName = "smtp.163.com";// 协议
	public final static String from = "rui_dev@163.com";// 发件人
	public final static String userName = "rui_dev";// 登陆名
	public final static String password = "ruidev123456";// smt协议 密码
	public final static int smtpPort = 25;// 
	public final static String sslSmtpPort = "465";// 

	
	public static void main(String[] args) throws EmailException {
		Email email = new SimpleEmail();
		email.setHostName(hostName);
		email.setSslSmtpPort(sslSmtpPort);
		
		email.setAuthenticator(new DefaultAuthenticator(userName,
				password));// @163.com
		
		email.setSSLOnConnect(true);

		// 发送人
		email.setFrom(from);// 设置字段的电子邮件使用指定的地址。电子邮件
		email.setSubject("送行-梁实秋");

		email.setMsg("method main 发邮件");
		// 接收人
		email.addTo("rui_dev@126.com");// 382453602@qq.com rui_dev@126.com
		email.send();
	}

	public String getEmailByFrom(String from) {

		return from;
	}

}
