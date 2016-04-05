package com.rui.pro1.modules.sys.entity;

import java.io.Serializable;


/**
 * The persistent class for the sys_menu database table.
 * 
 */

public class Menu implements Serializable {
	private static final long serialVersionUID = 1L;


	private int id;

	private String name;

	private int parentId;

	private String parentIds;

	private String permission;

	private int sort;

	private Integer status;

	private String types;

	public Menu() {
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

	public String getPermission() {
		return this.permission;
	}

	public void setPermission(String permission) {
		this.permission = permission;
	}

	public int getSort() {
		return this.sort;
	}

	public void setSort(int sort) {
		this.sort = sort;
	}

	public Integer getStatus() {
		return this.status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	public String getTypes() {
		return this.types;
	}

	public void setTypes(String types) {
		this.types = types;
	}

	@Override
	public String toString() {
		return "Menu [id=" + id + ", name=" + name + ", parentId=" + parentId
				+ ", parentIds=" + parentIds + ", permission=" + permission
				+ ", sort=" + sort + ", status=" + status + ", types=" + types
				+ "]";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + id;
		result = prime * result + ((name == null) ? 0 : name.hashCode());
		result = prime * result + parentId;
		result = prime * result
				+ ((parentIds == null) ? 0 : parentIds.hashCode());
		result = prime * result
				+ ((permission == null) ? 0 : permission.hashCode());
		result = prime * result + sort;
		result = prime * result + status;
		result = prime * result + ((types == null) ? 0 : types.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Menu other = (Menu) obj;
		if (id != other.id)
			return false;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		if (parentId != other.parentId)
			return false;
		if (parentIds == null) {
			if (other.parentIds != null)
				return false;
		} else if (!parentIds.equals(other.parentIds))
			return false;
		if (permission == null) {
			if (other.permission != null)
				return false;
		} else if (!permission.equals(other.permission))
			return false;
		if (sort != other.sort)
			return false;
		if (status != other.status)
			return false;
		if (types == null) {
			if (other.types != null)
				return false;
		} else if (!types.equals(other.types))
			return false;
		return true;
	}
	
	

}