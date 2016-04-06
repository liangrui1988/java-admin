package com.rui.pro1.modules.sys.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.rui.pro1.common.bean.page.Query;
import com.rui.pro1.modules.sys.entity.Menu;

public interface MenuMapper {

	List<Menu> queryPages(Query query);

	Long getCount(Query query);

	Menu get(@Param("id") int id);

	int del(int id);

	int add(Menu menu);

	int update(Menu menu);
	
	List<Menu> getAllMenuByRoleId(@Param("roleId") int roleId);
}
