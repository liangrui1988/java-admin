package com.huiwan.gdata.modules.gdata.base.charset.bean;

/**
 * 图表数据Pie饼图
 * 
 * @author ruiliang
 *
 */
public class PieData {

	private String name; // 名称
	private Integer y;// 数据

	public PieData() {
		super();

	}

	public PieData(String name, Integer y) {
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

	public Integer getY() {
		return y;
	}

	public void setY(Integer y) {
		this.y = y;
	}

}
