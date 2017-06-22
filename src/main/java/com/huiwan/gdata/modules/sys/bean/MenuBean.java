package com.huiwan.gdata.modules.sys.bean;

import java.io.Serializable;
import java.util.List;

/**
 * The persistent class for the sys_menu database table.
 * 
 */

public class MenuBean implements Serializable {
	private static final long serialVersionUID = 1L;

	private String id;

	private String name;

	private int parentId;

	private String parentIds;

	//private String permission;

	private int sortNo;

	private Integer status;

	private String types;

	private String icon;
	private String href;
	
	private List<MenuBean> list;

	public MenuBean() {
	}

	public String getId() {
		return this.id;
	}

	public void setId(String id) {
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

//	public String getPermission() {
//		return this.permission;
//	}
//
//	public void setPermission(String permission) {
//		this.permission = permission;
//	}



	public Integer getStatus() {
		return this.status;
	}

	public int getSortNo() {
		return sortNo;
	}

	public void setSortNo(int sortNo) {
		this.sortNo = sortNo;
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

	public String getIcon() {
		return icon;
	}

	public void setIcon(String icon) {
		this.icon = icon;
	}

	public String getHref() {
		return href;
	}

	public void setHref(String href) {
		this.href = href;
	}

	public List<MenuBean> getList() {
		return list;
	}

	public void setList(List<MenuBean> list) {
		this.list = list;
	}
	
	

}