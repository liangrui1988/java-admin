package com.huiwan.gdata.modules.sys.entity;

import java.util.Date;
import java.util.List;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.huiwan.gdata.common.utils.date.DateSerializer;

public class Role {
    private Integer id;

    private String name;

    private Integer status;

    private String types;

    @JsonSerialize(using=DateSerializer.class)
    private Date createTime;
    @JsonSerialize(using=DateSerializer.class)
    private Date updateTime;

    private Integer createById;

    private Integer updateById;

    private Integer groupId;

    private String remake;
    
	private List<String> menuIds; // 拥有的菜单列表
	
	private List<Menu> menus; // 拥有的菜单列表
	

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

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public String getTypes() {
        return types;
    }

    public void setTypes(String types) {
        this.types = types == null ? null : types.trim();
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

	public List<String> getMenuIds() {
		return menuIds;
	}

	public void setMenuIds(List<String> menuIds) {
		this.menuIds = menuIds;
	}

	public List<Menu> getMenus() {
		return menus;
	}

	public void setMenus(List<Menu> menus) {
		this.menus = menus;
	}
	
	

	public Integer getGroupId() {
		return groupId;
	}

	public void setGroupId(Integer groupId) {
		this.groupId = groupId;
	}

	@Override
	public String toString() {
		return "Role [id=" + id + ", name=" + name + ", status=" + status
				+ ", types=" + types + ", createTime=" + createTime
				+ ", updateTime=" + updateTime + ", createById=" + createById
				+ ", updateById=" + updateById + ", groupId=" + groupId
				+ ", remake=" + remake + ", menuIds=" + menuIds + ", menus="
				+ menus + "]";
	}
    
    
}