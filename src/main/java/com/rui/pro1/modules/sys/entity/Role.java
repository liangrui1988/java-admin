package com.rui.pro1.modules.sys.entity;

import java.io.Serializable;
import java.util.List;


/**
 * The persistent class for the sys_role database table.
 * 
 */

public class Role implements Serializable {
	private static final long serialVersionUID = 1L;

	
	private int id;

	private String name;

	private String remake;

	private int status;
	
    private List<Integer> menuIds; //拥有的菜单列表


	public Role() {
	}

	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
	}


	public List<Integer> getMenuIds() {
		return menuIds;
	}

	public void setMenuIds(List<Integer> menuIds) {
		this.menuIds = menuIds;
	}

	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getRemake() {
		return this.remake;
	}

	public void setRemake(String remake) {
		this.remake = remake;
	}

	public int getStatus() {
		return this.status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

}