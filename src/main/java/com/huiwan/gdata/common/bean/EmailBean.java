package com.huiwan.gdata.common.bean;

import java.util.Map;

import org.apache.commons.mail.EmailAttachment;

/**
 * 发送邮件bean
 * 
 * @author ruiliang
 * @2016/7/13
 */
public class EmailBean {

	private String subject;// 主题
	private String toEmail;// 接收人
	private String toEmailName;// 收件人 名称 《xx》
	private String context;// 普通邮件文本

	private Map<String, Object> map;// 模板邮件数据
	private String ftlName;

	
	private EmailAttachment emailAttachment;
	
	public String getContext() {
		return context;
	}

	public void setContext(String context) {
		this.context = context;
	}

	public String getToEmail() {
		return toEmail;
	}

	public void setToEmail(String toEmail) {
		this.toEmail = toEmail;
	}

	public String getToEmailName() {
		return toEmailName;
	}

	public void setToEmailName(String toEmailName) {
		this.toEmailName = toEmailName;
	}

	public String getFtlName() {
		return ftlName;
	}

	public void setFtlName(String ftlName) {
		this.ftlName = ftlName;
	}

	public Map<String, Object> getMap() {
		return map;
	}

	public void setMap(Map<String, Object> map) {
		this.map = map;
	}

	public String getSubject() {
		return subject;
	}

	public void setSubject(String subject) {
		this.subject = subject;
	}

	public EmailAttachment getEmailAttachment() {
		return emailAttachment;
	}

	public void setEmailAttachment(EmailAttachment emailAttachment) {
		this.emailAttachment = emailAttachment;
	}
	
	
	

}
