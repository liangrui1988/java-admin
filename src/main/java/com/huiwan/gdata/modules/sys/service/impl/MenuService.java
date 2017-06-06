package com.huiwan.gdata.modules.sys.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.huiwan.gdata.common.bean.page.Query;
import com.huiwan.gdata.common.bean.page.QueryResult;
import com.huiwan.gdata.modules.sys.entity.Menu;
import com.huiwan.gdata.modules.sys.mapper.MenuMapper;
import com.huiwan.gdata.modules.sys.service.IMenuService;
import com.huiwan.gdata.modules.sys.vo.MenuVo;

@Service
public class MenuService implements IMenuService {

	@Autowired
	private MenuMapper menuMapper;

	@Override
	public QueryResult<Menu> getMenuList(int page, int pagesize, MenuVo menu) {
		Query query = new Query();
		query.setBean(menu);
		query.setPageIndex(page);
		query.setPageSize(pagesize);

		// 组合分页信息
		QueryResult<Menu> queryResult = new QueryResult<Menu>();
		List<Menu> list = menuMapper.queryPages(query);
		
		
		// 总页数 和 取多少条
		queryResult.setCurrentPage(page);
		Long count = menuMapper.getCount(query);
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
		int count=menuMapper.insertSelective(menu);
		if(count>0){
			//清除权限
			//增加权限
		}
		return count;
	}

	@Override
	public int update(Menu menu) {
		return menuMapper.update(menu);
	}

	@Override
	public List<Menu> getMenuListAll(String level) {
		List<Menu> list = menuMapper.queryAllByLevel(level);
		return list;
	}

	@Override
	public List<Menu> getMenuListAll() {
		List<Menu> list = menuMapper.queryAll();
		return list;
	}

}
