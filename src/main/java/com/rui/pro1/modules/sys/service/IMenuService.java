package com.rui.pro1.modules.sys.service;

import java.util.List;

import com.rui.pro1.common.bean.page.QueryResult;
import com.rui.pro1.modules.sys.entity.Menu;
import com.rui.pro1.modules.sys.vo.MenuVo;

public interface IMenuService {

	QueryResult<Menu> getMenuList(int page, int pagesize, MenuVo menu);

	Menu get(int id);

	int del(int id);

	int add(Menu menu);

	int update(Menu menu);

	List<Menu> getMenuListAll();

	List<Menu> getMenuListAll(String level);
}
