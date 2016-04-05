package com.rui.pro1.modules.sys.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.rui.pro1.common.bean.page.Query;
import com.rui.pro1.modules.sys.entity.Menu;
import com.rui.pro1.modules.sys.entity.Role;

public interface RoleMapper {

	List<Role> queryPages(Query query);

	Long getCount(Query query);

	Role get(@Param("id") int id);

	int del(int id);

	int add(Role role);

	int update(Role role);
}
