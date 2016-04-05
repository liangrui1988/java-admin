package com.rui.pro1.modules.sys.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.rui.pro1.common.bean.page.Query;
import com.rui.pro1.common.bean.page.QueryResult;
import com.rui.pro1.modules.sys.entity.Menu;
import com.rui.pro1.modules.sys.mapper.MenuMapper;
import com.rui.pro1.modules.sys.service.IMenuService;
import com.rui.pro1.modules.sys.vo.MenuVo;
@Service
public class MenuService implements IMenuService {

	@Autowired
	private MenuMapper menuMapper;
	
	@Override
	public QueryResult<Menu> getMenuList(int page, int pagesize, MenuVo menu) {
		Query query = new Query();
		query.setBean(menu);
		query.setPageIndex(page);

		// 组合分页信息
		QueryResult<Menu> queryResult = new QueryResult<Menu>();
		Long count = menuMapper.getCount(query);
		List<Menu> list = menuMapper.queryPages(query);
		// 总页数 和 取多少条
		queryResult.setPages(count, pagesize);
		queryResult.setItems(list);

		return queryResult;
	}

	@Override
	public Menu get(int id) {
		return menuMapper.get(id);
	}

	@Override
	public int del(int id) {
		return menuMapper.del(id);
	}

	@Override
	public int add(Menu menu) {
		return menuMapper.add(menu);
	}

	@Override
	public int update(Menu menu) {
		return menuMapper.update(menu);
	}

}
