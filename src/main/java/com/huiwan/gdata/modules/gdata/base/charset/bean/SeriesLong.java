package com.huiwan.gdata.modules.gdata.base.charset.bean;

import java.util.List;

/**
 * 通用图表 Series 属性 long类型
 * 
 * @author ruiliang
 * @date 2016/10/10
 */
public class SeriesLong {

	private String name; // leang
	private List<Long> data;// 图表数据

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public List<Long> getData() {
		return data;
	}

	public void setData(List<Long> data) {
		this.data = data;
	}

	public SeriesLong(String name) {
		super();

		if (name == null) {
			this.name = "";
		} else {
			this.name = name;
		}

	}

	public SeriesLong() {

	}

}
