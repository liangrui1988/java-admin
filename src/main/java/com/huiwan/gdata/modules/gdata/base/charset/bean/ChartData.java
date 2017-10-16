package com.huiwan.gdata.modules.gdata.base.charset.bean;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.alibaba.fastjson.JSONObject;
import com.huiwan.gdata.modules.gdata.base.charset.bean.common.SeriesCommon;

/**
 * 通用图表属性
 * 
 * @date 2016、9、26
 * 
 * @author ruiliang
 *
 */
public class ChartData {

	Logger log = LoggerFactory.getLogger(getClass());

	private List<String> aisxName; // 标题 X_Aisx
	private List<Series> series; // 图表 名和数据集合 Inteter类型
	private List<SeriesLong> seriesLong; // 图表 名和数据集合 Long类型
	private List<SeriesDouble> seriesdouble; // 图表 名和数据集合 Double类型
	private List<SeriesCommon> seriesCommon; // 图表名和数据集合Obejct类型

	public List<String> getAisxName() {
		return aisxName;
	}

	public void setAisxName(List<String> aisxName) {
		this.aisxName = aisxName;
	}

	public List<Series> getSeries() {
		return series;
	}

	public void setSeries(List<Series> series) {
		this.series = series;
	}

	public List<SeriesLong> getSeriesLong() {
		return seriesLong;
	}

	public void setSeriesLong(List<SeriesLong> seriesLong) {
		this.seriesLong = seriesLong;
	}

	// 转换为字符串的json
	public ChartDataStr proxyCharDataStr() {
		if (this.getAisxName() == null || this.getAisxName().size() <= 0) {
			return new ChartDataStr();
		}
		log.info("chartData:{}", JSONObject.toJSONString(this));
		return new ChartDataStr(JSONObject.toJSONString(this.getAisxName()), JSONObject.toJSONString(this.getSeries()));
	}

	// 转换为字符串的json
	public ChartDataStr proxyCharDataStrToLong() {
		if (this.getAisxName() == null || this.getAisxName().size() <= 0) {
			return new ChartDataStr();
		}
		log.info("chartData:{}", JSONObject.toJSONString(this));
		return new ChartDataStr(JSONObject.toJSONString(this.getAisxName()),
				JSONObject.toJSONString(this.getSeriesLong()));
	}

	public List<SeriesDouble> getSeriesdouble() {
		return seriesdouble;
	}

	public void setSeriesdouble(List<SeriesDouble> seriesdouble) {
		this.seriesdouble = seriesdouble;
	}

	// 转换为字符串的json
	public ChartDataStr proxyCharDataStrToDouble() {
		if (this.getAisxName() == null || this.getAisxName().size() <= 0) {
			return new ChartDataStr();
		}
		log.info("chartData:{}", JSONObject.toJSONString(this));
		return new ChartDataStr(JSONObject.toJSONString(this.getAisxName()),
				JSONObject.toJSONString(this.getSeriesdouble()));
	}

	public List<SeriesCommon> getSeriesCommon() {
		return seriesCommon;
	}

	public void setSeriesCommon(List<SeriesCommon> seriesCommon) {
		this.seriesCommon = seriesCommon;
	}

	// 转换为字符串的json
	public ChartDataStr proxyCharDataStrToCommon() {
		if (this.getAisxName() == null || this.getAisxName().size() <= 0) {
			return new ChartDataStr(JSONObject.toJSONString(new ArrayList<String>()),
					JSONObject.toJSONString(new ArrayList<SeriesCommon>()));
			
		}
		log.info("chartData:{}", JSONObject.toJSONString(this));
		return new ChartDataStr(JSONObject.toJSONString(this.getAisxName()),
				JSONObject.toJSONString(this.getSeriesCommon()));
	}
}
