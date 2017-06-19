package com.huiwan.gdata.modules.gdata.base.charset.bean;

/**
 * 用于json图表对象
 * <p>
 * 
 * @author ruiliang
 * 
 * @date 2016/10/9
 *
 */
public class ChartDataStr {

	private String aisxName; // 标题 X_Aisx
	private String series; // 图表 名和数据集合

	public ChartDataStr() {
		super();

	}

	public ChartDataStr(String aisxName, String series) {
		super();
		this.aisxName = aisxName;
		this.series = series;
	}

	public String getAisxName() {
		return aisxName;
	}

	public void setAisxName(String aisxName) {
		this.aisxName = aisxName;
	}

	public String getSeries() {
		return series;
	}

	public void setSeries(String series) {
		this.series = series;
	}

	@Override
	public String toString() {
		return "ChartDataStr [aisxName=" + aisxName + ", series=" + series + "]";
	}
	
	
	

}
