package com.huiwan.gdata.modules.gdata.cat.bean;

public class EmailBean {

	private String type;
	private String key;
	private String re;
	private String to;
	private String title;
	private String body;
	private String value;

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getKey() {
		return key;
	}

	public void setKey(String key) {
		this.key = key;
	}

	public String getRe() {
		return re;
	}

	public void setRe(String re) {
		this.re = re;
	}

	public String getTo() {
		return to;
	}

	public void setTo(String to) {
		this.to = to;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getBody() {
		return body;
	}

	public void setBody(String body) {
		this.body = body;
	}

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}

	@Override
	public String toString() {
		return "EmailBean [type=" + type + ", key=" + key + ", re=" + re + ", to=" + to + ", title=" + title + ", body="
				+ body + ", value=" + value + "]";
	}

	
	
}
