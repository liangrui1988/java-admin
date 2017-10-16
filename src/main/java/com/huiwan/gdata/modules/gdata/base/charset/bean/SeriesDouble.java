package com.huiwan.gdata.modules.gdata.base.charset.bean;

import java.util.List;

/**
 * 通用图表 Series 属性 long类型
 * 
 * @author ruiliang
 * @date 2016/10/10
 */
public class SeriesDouble {

	private String name; // leang
	private List<Double> data;// 图表数据

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public List<Double> getData() {
		return data;
	}

	public void setData(List<Double> data) {
		this.data = data;
	}

	public SeriesDouble(String name) {
		super();

		if (name == null) {
			this.name = "";
		} else {
			this.name = name;
		}

	}

	public SeriesDouble() {

	}

}
