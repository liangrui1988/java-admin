package com.huiwan.gdata.modules.sys.entity;

import java.math.BigDecimal;
import java.util.Date;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.huiwan.gdata.common.utils.date.DateSerializer;

public class Department {
	private Integer id;

	private String name;

	private Integer sort;

	private Integer parentId;

	private String parentIds;

	private Integer status;
	@JsonSerialize(using = DateSerializer.class)
	private Date createTime;

	@JsonSerialize(using = DateSerializer.class)
	private Date updateTime;

	private Integer createById;

	private Integer updateById;

	private String remake;

	private Double testXls;
	private BigDecimal bdTest;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name == null ? null : name.trim();
	}

	public Integer getSort() {
		return sort;
	}

	public void setSort(Integer sort) {
		this.sort = sort;
	}

	public Integer getParentId() {
		return parentId;
	}

	public void setParentId(Integer parentId) {
		this.parentId = parentId;
	}

	public String getParentIds() {
		return parentIds;
	}

	public void setParentIds(String parentIds) {
		this.parentIds = parentIds == null ? null : parentIds.trim();
	}

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	public Date getUpdateTime() {
		return updateTime;
	}

	public void setUpdateTime(Date updateTime) {
		this.updateTime = updateTime;
	}

	public Integer getCreateById() {
		return createById;
	}

	public void setCreateById(Integer createById) {
		this.createById = createById;
	}

	public Integer getUpdateById() {
		return updateById;
	}

	public void setUpdateById(Integer updateById) {
		this.updateById = updateById;
	}

	public String getRemake() {
		return remake;
	}

	public void setRemake(String remake) {
		this.remake = remake == null ? null : remake.trim();
	}

	public Double getTestXls() {
		return testXls;
	}

	public void setTestXls(Double testXls) {
		this.testXls = testXls;
	}

	public BigDecimal getBdTest() {
		return bdTest;
	}

	public void setBdTest(BigDecimal bdTest) {
		this.bdTest = bdTest;
	}

}