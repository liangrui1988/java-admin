package com.huiwan.gdata.sys.service;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.huiwan.gdata.comm.BaseServiceTest;
import com.huiwan.gdata.common.bean.page.QueryResult;
import com.huiwan.gdata.modules.sys.entity.Menu;
import com.huiwan.gdata.modules.sys.service.IMenuService;
import com.huiwan.gdata.modules.sys.vo.MenuVo;

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
		menu.setSortNo(1);
		menu.setTypes("2");
		menu.setParentId("0");
		menu.setIcon("a");
		menu.setHref("/sys/menu/list");
	
		menuService.add(menu);
	}

	@Test
	public void update() {
		
		Menu menu=new Menu();
		
		menu.setId("2");
		menu.setName("update");
		menu.setPermission("xx user:*");
		menu.setSortNo(2);
		menu.setTypes("3");
		menu.setParentId("0");
		menu.setStatus(1);
		
		menuService.update(menu);
	}
	
	
//	List<MenuVo> mvo=new ArrayList<MenuVo>();
//	MenuVo menuVo=new MenuVo();
//	menuVo.setId("1");
//	menuVo.setParentId("");
//	menuVo.setName("系统管理");
//	menuVo.setIcon("glyphicon glyphicon-user");
//	mvo.add(menuVo);
//	MenuVo menuVox =new MenuVo();
//	menuVox.setId("10");
//	menuVox.setParentId("1");
//	menuVox.setName("用户管理 ");
//	menuVox.setHref("/views/modules/sys/user/userlist");
//	mvo.add(menuVox);
//	MenuVo menuVoa  =new MenuVo();
//	menuVoa.setId("11");
//	menuVoa.setParentId("1");
//	menuVoa.setName("菜单管理 ");
//	menuVoa.setHref("/views/modules/sys/menu/menulist");
//	mvo.add(menuVoa);
//	//--------------------------------
//	MenuVo menuVo2=new MenuVo();
//	menuVo2.setId("2");
//	menuVo2.setParentId("");
//	menuVo2.setName("财务管理");
//	mvo.add(menuVo2);
//	MenuVo menuVo3=new MenuVo();
//	menuVo3.setId("3");
//	menuVo3.setParentId("");
//	menuVo3.setName("采购管理");
//	mvo.add(menuVo3);

}
