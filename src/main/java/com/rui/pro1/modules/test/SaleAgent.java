package com.rui.pro1.modules.test;

import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.Length;

/**
 * 销售代理商Entity
 * @author ranyao
 * @version 2015-12-14
 */
public class SaleAgent  {
	
	private String name;		// 名称
	private Integer property;		// 性质
	private String address;		// 固定营业场所地址
	private String contacter;		// 联系人
	private String contacterPhone;		// 联系人电话
	private String salesman;		// 对接的销售
	
	public SaleAgent() {
		super();
	}

	

	@Length(min=1, max=512, message="名称长度必须介于 1 和 512 之间")
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	@NotNull(message="性质不能为空")
	public Integer getProperty() {
		return property;
	}

	public void setProperty(Integer property) {
		this.property = property;
	}
	
	@Length(min=1, max=512, message="固定营业场所地址长度必须介于 1 和 512 之间")
	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}
	
	@Length(min=1, max=256, message="联系人长度必须介于 1 和 256 之间")
	public String getContacter() {
		return contacter;
	}

	public void setContacter(String contacter) {
		this.contacter = contacter;
	}
	
	@Length(min=5, max=32, message="联系人电话长度必须介于 1 和 32 之间")
	public String getContacterPhone() {
		return contacterPhone;
	}

	public void setContacterPhone(String contacterPhone) {
		this.contacterPhone = contacterPhone;
	}
	
	@Length(min=1, max=256, message="对接的销售长度必须介于 1 和 256 之间")
	public String getSalesman() {
		return salesman;
	}

	public void setSalesman(String salesman) {
		this.salesman = salesman;
	}
	
}