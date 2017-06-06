package com.huiwan.gdata.modules.sys.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.huiwan.gdata.common.bean.page.Query;
import com.huiwan.gdata.modules.sys.entity.Menu;

public interface MenuMapper {

	List<Menu> queryPages(Query query);

	Long getCount(Query query);

	Menu get(@Param("id") int id);

	int del(int id);

	//int add(Menu menu);

	//int update(Menu menu);
	
	List<Menu> getAllMenuByRoleId(@Param("roleId") int roleId);
	
	
	int insert(Menu menu);
	int insertSelective(Menu menu);
	int update(Menu menu);
	int updateByPrimaryKeySelective(Menu menu);

	List<Menu> queryAll();
	List<Menu> queryAllByLevel(@Param("level") String level);
}
