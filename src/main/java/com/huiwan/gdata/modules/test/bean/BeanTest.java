package com.huiwan.gdata.modules.test.bean;

import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;

public class BeanTest {
	
	@DateTimeFormat(pattern="yyyy-mm-dd")
	private Date time;
	
	private Integer id;
	
	private double money;
	public Date getTime() {
		return time;
	}
	public void setTime(Date time) {
		this.time = time;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public double getMoney() {
		return money;
	}

	public void setMoney(double money) {
		this.money = money;
	}
	
	
	
	

}
