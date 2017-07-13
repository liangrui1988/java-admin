package com.huiwan.gdata.modules.gdata.base.charset.bean;

import java.util.List;

/**
 * 通用查询vo
 * 
 * @author ruiliang
 * @date 2016/09/23
 */
public class QueryCommBean implements Cloneable {

	private String dt1;
	private String dt2;
	private Integer server;
	private String channel;
	private String os;
	private String type;// 类型，
	private String chartType;// 查询图表类型
	private String regDt1;// 注册日期
	private String regDt2;// 注册日期
	private Integer id;
	private String gameMark;
	private Integer startNum;// 起始数
	private Integer endNum;// 结束数
	private int commQueryInt;// 通用int
	private String commQueryString;// 通用String
	private String honor;// 荣誉值
	private List<String> honors;// 荣誉值
	private String sign;
	// 多选框
	private List<String> dans;// 段位
	// 多选框
	private List<String> jobs;// 职业
	
	public String copyId;//副本id
	public String file;//文件类型
	public String name;
	


	public String getDt1() {
		return dt1;
	}

	public void setDt1(String dt1) {
		this.dt1 = dt1;
	}

	public String getDt2() {
		return dt2;
	}

	public void setDt2(String dt2) {
		this.dt2 = dt2;
	}

	public Integer getServer() {
		return server;
	}

	public void setServer(Integer server) {
		this.server = server;
	}

	public String getChannel() {
		return channel;
	}

	public void setChannel(String channel) {
		this.channel = channel;
	}

	public String getOs() {
		return os;
	}

	public void setOs(String os) {
		this.os = os;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getGameMark() {
		return gameMark;
	}

	public void setGameMark(String gameMark) {
		this.gameMark = gameMark;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getChartType() {
		return chartType;
	}

	public void setChartType(String chartType) {
		this.chartType = chartType;
	}

	public String getRegDt1() {
		return regDt1;
	}

	public void setRegDt1(String regDt1) {
		this.regDt1 = regDt1;
	}

	public String getRegDt2() {
		return regDt2;
	}

	public void setRegDt2(String regDt2) {
		this.regDt2 = regDt2;
	}

	public Integer getStartNum() {
		return startNum;
	}

	public void setStartNum(Integer startNum) {
		this.startNum = startNum;
	}

	public Integer getEndNum() {
		return endNum;
	}

	public void setEndNum(Integer endNum) {
		this.endNum = endNum;
	}

	public int getCommQueryInt() {
		return commQueryInt;
	}

	public void setCommQueryInt(int commQueryInt) {
		this.commQueryInt = commQueryInt;
	}

	public List<String> getHonors() {
		return honors;
	}

	public void setHonors(List<String> honors) {
		this.honors = honors;
	}

	public String getHonor() {
		return honor;
	}

	public void setHonor(String honor) {
		this.honor = honor;
	}

	@Override
	public Object clone() throws CloneNotSupportedException {
		return super.clone();
	}

	public Object clone2() {
		try {
			return super.clone();
		} catch (CloneNotSupportedException e) {
			e.printStackTrace();
			return this;

		}
	}

	public String getSign() {
		return sign;
	}

	public void setSign(String sign) {
		this.sign = sign;
	}

	public List<String> getDans() {
		return dans;
	}

	public void setDans(List<String> dans) {
		this.dans = dans;
	}
	
	public List<String> getJobs() {
		return jobs;
	}

	public void setJobs(List<String> jobs) {
		this.jobs = jobs;
	}

	
	
	public String getCopyId() {
		return copyId;
	}

	public void setCopyId(String copyId) {
		this.copyId = copyId;
	}
	
	


	public String getCommQueryString() {
		return commQueryString;
	}

	public void setCommQueryString(String commQueryString) {
		this.commQueryString = commQueryString;
	}
	
	
	

	public String getFile() {
		return file;
	}

	public void setFile(String file) {
		this.file = file;
	}
	
	
	

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Override
	public String toString() {
		return "ChartsQueryVo [dt1=" + dt1 + ", dt2=" + dt2 + ", server=" + server + ", channel=" + channel + ", os="
				+ os + ", type=" + type + ", chartType=" + chartType + ", regDt1=" + regDt1 + ", regDt2=" + regDt2
				+ ", id=" + id + ", gameMark=" + gameMark + ", startNum=" + startNum + ", endNum=" + endNum
				+ ", commQueryInt=" + commQueryInt + ", honor=" + honor + ", honors=" + honors + "]";
	}

}
