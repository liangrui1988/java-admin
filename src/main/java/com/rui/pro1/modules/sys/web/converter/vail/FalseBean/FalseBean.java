package com.rui.pro1.modules.sys.web.converter.vail.FalseBean;

import javax.validation.constraints.Max;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.Length;

public class FalseBean {

	@NotNull
	private String notNullF;
	
	
	@Length(min = 2, max = 6)
	private String lengthF;
	
	
	@Max(100)
	private long maxF;


	public String getNotNullF() {
		return notNullF;
	}


	public void setNotNullF(String notNullF) {
		this.notNullF = notNullF;
	}


	public String getLengthF() {
		return lengthF;
	}


	public void setLengthF(String lengthF) {
		this.lengthF = lengthF;
	}


	public long getMaxF() {
		return maxF;
	}


	public void setMaxF(long maxF) {
		this.maxF = maxF;
	}


	

}
