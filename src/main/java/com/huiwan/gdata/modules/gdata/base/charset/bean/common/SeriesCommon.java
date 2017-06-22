package com.huiwan.gdata.modules.gdata.base.charset.bean.common;

import java.util.List;

/**
 * 通用图表 Series 属性
 * 
 * @author ruiliang
 * @date 2016/10/10
 */
public class SeriesCommon {

	/**
	 * leang
	 */
	private String name;

	/**
	 * 图表数据
	 */
	private List<Object> data;
	private String type = "column";
	private Integer yAxis;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public List<Object> getData() {
		return data;
	}

	public void setData(List<Object> data) {
		this.data = data;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public Integer getyAxis() {
		return yAxis;
	}

	public void setyAxis(Integer yAxis) {
		this.yAxis = yAxis;
	}

	public SeriesCommon(String name) {
		super();

		if (name == null) {
			this.name = "";
		} else {
			this.name = name;
		}

	}

	public SeriesCommon() {

	}

}
