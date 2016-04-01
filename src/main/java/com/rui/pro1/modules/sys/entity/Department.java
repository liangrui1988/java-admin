package com.rui.pro1.modules.sys.entity;

import java.io.Serializable;
import java.util.List;

/**
 * The persistent class for the sys_department database table.
 * 
 */

public class Department implements Serializable {
	private static final long serialVersionUID = 1L;

	private int id;

	private String name;

	private int parentId;

	private String parentIds;

	private String remake;

	private int sort;

	private int status;
	


	public Department() {
	}

	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getParentId() {
		return this.parentId;
	}

	public void setParentId(int parentId) {
		this.parentId = parentId;
	}

	public String getParentIds() {
		return this.parentIds;
	}

	public void setParentIds(String parentIds) {
		this.parentIds = parentIds;
	}

	public String getRemake() {
		return this.remake;
	}

	public void setRemake(String remake) {
		this.remake = remake;
	}

	public int getSort() {
		return this.sort;
	}

	public void setSort(int sort) {
		this.sort = sort;
	}

	public int getStatus() {
		return this.status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

}