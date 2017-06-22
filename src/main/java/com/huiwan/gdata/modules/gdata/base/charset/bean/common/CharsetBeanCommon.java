package com.huiwan.gdata.modules.gdata.base.charset.bean.common;

/**
 * 通用bean Object类型
 * <p>
 * 方便统一转换图表的对象
 * 
 * @author ruiliang
 * @date 2016/10/14
 *
 */
public class CharsetBeanCommon {

	/**
	 * 能常用于图表的serier名称
	 */
	private String id;

	/**
	 * 通常用于图表的X轴名称
	 */
	private String xName;

	/**
	 * 通常用于图表的数据,int、long、double...
	 */
	private Object count;

	/**
	 * 图标类型，柱，线等
	 */
	private String type = "column";

	/**
	 * 相当于一个组的显示数据 y标标量，多种数据一起显示时用
	 */
	private Integer yAxis;

	public CharsetBeanCommon(String id, String xName, Object count, Integer yAxis) {
		super();
		this.id = id;
		this.xName = xName;
		this.count = count;
		this.yAxis = yAxis;
	}

	public CharsetBeanCommon(String id, String xName, Object count, String type, Integer yAxis) {
		super();
		this.id = id;
		this.xName = xName;
		this.count = count;
		this.type = type;
		this.yAxis = yAxis;
	}

	public CharsetBeanCommon(String id, String xName, Object count, String type) {
		super();
		this.id = id;
		this.xName = xName;
		this.count = count;
		this.type = type;
	}

	public CharsetBeanCommon(String id, String xName, Object count) {
		super();
		this.id = id;
		this.xName = xName;
		this.count = count;
	}

	public CharsetBeanCommon(String xName, Object count) {
		super();
		this.xName = xName;
		this.count = count;
	}

	public CharsetBeanCommon() {
		super();
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getxName() {
		return xName;
	}

	public void setxName(String xName) {
		this.xName = xName;
	}

	public Object getCount() {
		return count;
	}

	public void setCount(Object count) {
		this.count = count;
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

	@Override
	public String toString() {
		return "CharsetBeanCommon [id=" + id + ", xName=" + xName + ", count=" + count + ", type=" + type + ", yAxis="
				+ yAxis + "]";
	}
	
	
	

}
