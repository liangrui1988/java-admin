package com.huiwan.gdata.vail;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

import org.junit.Test;

public class RejectTest {
	
	@Test
	public void test1() throws NoSuchMethodException, SecurityException, IllegalAccessException, IllegalArgumentException, InvocationTargetException{
		
		BeanVail v=new BeanVail();
		v.testMethod(20, "abcd");
		
		
		Class clz=v.getClass();
		
		Method m=v.getClass().getMethod("testMethod", int.class,String.class);
		
		System.out.println(m.getDeclaringClass());
		
		System.out.println(m.getDefaultValue());
		
		
		Object o=m.invoke(v,20,"abcd");
		System.out.println(o);
	}

}
