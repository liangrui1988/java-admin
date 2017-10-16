package com.huiwan.gdata.Ecap;

public class A {

	public A() {
		// TODO Auto-generated constructor stub
	}

public static void main(String[] args) {
	String s=org.apache.commons.lang3.StringEscapeUtils.escapeXml("<root>中文测试</root>");
	System.out.println(s);
	String s2=org.apache.commons.lang.StringEscapeUtils.escapeXml("<root>中文测试</root>");
	System.out.println(s2);
	

}
}
