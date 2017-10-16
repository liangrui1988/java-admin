package com.huiwan.gdata.modules.sys.service;

import java.util.List;

import com.huiwan.gdata.common.bean.page.QueryResult;
import com.huiwan.gdata.modules.sys.entity.Menu;
import com.huiwan.gdata.modules.sys.vo.MenuVo;

public interface IMenuService {

	QueryResult<Menu> getMenuList(int page, int pagesize, MenuVo menu);

	Menu get(int id);

	int del(int id);

	int add(Menu menu);

	int update(Menu menu);

	List<Menu> getMenuListAll();

	List<Menu> getMenuListAll(String level);
}
