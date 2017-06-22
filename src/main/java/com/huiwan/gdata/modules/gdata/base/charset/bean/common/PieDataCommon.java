package com.huiwan.gdata.modules.gdata.base.charset.bean.common;

/**
 * 图表数据Pie饼图
 * 
 * @author ruiliang
 *
 */
public class PieDataCommon {

	private String name; // 名称
	private Object y;// 数据

	public PieDataCommon() {
		super();

	}

	public PieDataCommon(String name, Object y) {
		super();
		this.name = name;
		this.y = y;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Object getY() {
		return y;
	}

	public void setY(Object y) {
		this.y = y;
	}

}
