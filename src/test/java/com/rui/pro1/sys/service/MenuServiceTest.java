package com.rui.pro1.sys.service;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.rui.pro1.comm.BaseServiceTest;
import com.rui.pro1.common.bean.page.QueryResult;
import com.rui.pro1.modules.sys.entity.Menu;
import com.rui.pro1.modules.sys.service.IMenuService;
import com.rui.pro1.modules.sys.vo.MenuVo;

public class MenuServiceTest extends BaseServiceTest {

	@Autowired
	private IMenuService menuService;

	@Test
	public void getPage() {
		QueryResult<Menu> result = menuService
				.getMenuList(1, 20, new MenuVo());
		printJson(result);
	}

	@Test
	public void get() {
		Menu menu=menuService.get(1);
		printJson(menu);

	}
	@Test
	public void del() {
		int count=menuService.del(3);
		System.out.println(count);
	}
	@Test
	public void add() {
		Menu menu=new Menu();
		menu.setName("系统管理");
		menu.setPermission("user:*");
		menu.setSort(1);
		menu.setTypes("2");
		menu.setParentId(0);
	
		menuService.add(menu);
	}

	@Test
	public void update() {
		
		Menu menu=new Menu();
		
		menu.setId(2);
		menu.setName("update");
		menu.setPermission("xx user:*");
		menu.setSort(2);
		menu.setTypes("3");
		menu.setParentId(0);
		menu.setStatus(1);
		
		menuService.update(menu);
	}

}
