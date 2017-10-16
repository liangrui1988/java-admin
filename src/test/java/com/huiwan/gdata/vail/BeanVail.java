package com.huiwan.gdata.vail;

import javax.validation.constraints.Max;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.Length;

public class BeanVail {

	@NotNull
	private String name;
	@Length(min = 2, max = 6)
	private String fullName;
	@Max(value = 100)
	private int age;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getFullName() {
		return fullName;
	}

	public void setFullName(String fullName) {
		this.fullName = fullName;
	}

	public int getAge() {
		return age;
	}

	public void setAge(int age) {
		this.age = age;
	}
	
	public void testMethod(int age,String name){
		System.out.println("xx:"+age);
	}

}
