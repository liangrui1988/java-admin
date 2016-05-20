package com.rui.pro1.modules.sys.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.rui.pro1.common.bean.page.Query;
import com.rui.pro1.common.bean.page.QueryResult;
import com.rui.pro1.common.utils.copyo.BeanCopierUtils;
import com.rui.pro1.modules.sys.bean.MenuBean;
import com.rui.pro1.modules.sys.bean.RoleBean;
import com.rui.pro1.modules.sys.entity.Menu;
import com.rui.pro1.modules.sys.entity.Role;
import com.rui.pro1.modules.sys.mapper.MenuMapper;
import com.rui.pro1.modules.sys.mapper.RoleMapper;
import com.rui.pro1.modules.sys.service.IRoleService;
import com.rui.pro1.modules.sys.vo.RoleVo;

@Service
public class RoleService implements IRoleService {
	@Autowired
	private RoleMapper roleMapper;

	@Autowired
	private MenuMapper menuMapper;

	@Override
	public QueryResult<Role> getRoleList(int page, int pagesize, RoleVo roleVo) {
		Query query = new Query();
		query.setBean(roleVo);
		query.setPageIndex(page);

		// 组合分页信息
		QueryResult<Role> queryResult = new QueryResult<Role>();
		Long count = roleMapper.getCount(query);
		List<Role> list = roleMapper.queryPages(query);

//		List<RoleBean> roleBeanList = new ArrayList<RoleBean>();
		// 角色下拥有的菜单
//		for (Role role : list) {
//			roleBeanList.add(roleToBean(role));
//
//		}
		// 总页数 和 取多少条
		queryResult.setPages(count, pagesize);
		queryResult.setItems(list);

		return queryResult;
	}

	/**
	 * 根据角色 转为bean 并查找所有菜单
	 * 
	 * @return 角色所有信息
	 */
	public RoleBean roleToBean(Role role) {
		RoleBean roleBean = new RoleBean();
		BeanCopierUtils.copyProperties(role, roleBean);
		// roleBeanList.add(roleBean);

		List<Menu> menus = menuMapper.getAllMenuByRoleId(role.getId());
		if (menus == null || menus.size() <= 0) {
			return roleBean;
		}

		// copy菜单
		List<MenuBean> menusBean = new ArrayList<MenuBean>();
		for (Menu m : menus) {
			MenuBean menuBean = new MenuBean();
			BeanCopierUtils.copyProperties(m, menuBean);
			menusBean.add(menuBean);
		}
		roleBean.setMenus(menusBean);

		return roleBean;
	}

	@Override
	public RoleBean get(int id) {
		Role role=roleMapper.get(id);
		return roleToBean(role);
	}

	@Override
	public int del(int id) {
		return roleMapper.del(id);
	}

	@Override
	public int add(Role role) {

		int count = roleMapper.insertSelective(role);
		if (count <= 0) {
			return 0;
		}

		if (role.getMenuIds() != null && role.getMenuIds().size() > 0) {
			// 关联菜单
			for (String menuId : role.getMenuIds()) {
				roleMapper.addRoleMenu(role.getId(), menuId);
			}
		}

		return count;
	}

	@Override
	public int update(Role role) {
		if(role==null||role.getId()<=0){
			return 0;
		}
		
		int count=roleMapper.updateByPrimaryKeySelective(role);
		if(count>0){
			//更改菜单
			roleMapper.delRoleMenu(role.getId());
			if(role.getMenuIds()!=null&&role.getMenuIds().size()>0)
			{
				for(String menuId:role.getMenuIds()){
					roleMapper.addRoleMenu(role.getId(), menuId);
				}
			}
		}
		return count;
	}

}
