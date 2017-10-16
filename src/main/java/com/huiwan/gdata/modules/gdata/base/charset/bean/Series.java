package com.huiwan.gdata.modules.gdata.base.charset.bean;

import java.util.List;

/**
 * 通用图表 Series 属性
 * 
 * @author ruiliang
 * @date 2016/10/10
 */
public class Series {

	private String name; // leang
	private List<Integer> data;// 图表数据

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public List<Integer> getData() {
		return data;
	}

	public void setData(List<Integer> data) {
		this.data = data;
	}

	public Series(String name) {
		super();

		if (name == null) {
			this.name = "";
		} else {
			this.name = name;
		}

	}

	public Series() {

	}

}
