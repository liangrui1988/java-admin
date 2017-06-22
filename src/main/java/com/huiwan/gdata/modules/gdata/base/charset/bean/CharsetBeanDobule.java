package com.huiwan.gdata.modules.gdata.base.charset.bean;

/**
 * 通用bean long类型
 * <p>
 * 方便统一转换图表的对象
 * 
 * @author ruiliang
 * @date 2016/10/14
 *
 */
public class CharsetBeanDobule {

	private String id; // 能常用于图表的serier名称
	private String xName; // 通常用于图表的X轴名称
	private Double count; // 通常用于图表的数据

	public CharsetBeanDobule(String id, String xName, Double count) {
		super();
		this.id = id;
		this.xName = xName;
		this.count = count;
	}

	public CharsetBeanDobule(String xName, Double count) {
		super();
		this.xName = xName;
		this.count = count;
	}

	public CharsetBeanDobule() {
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

	public Double getCount() {
		return count;
	}

	public void setCount(Double count) {
		this.count = count;
	}

}
