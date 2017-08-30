package com.huiwan.gdata.modules.sys.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.huiwan.gdata.common.bean.page.Query;
import com.huiwan.gdata.common.bean.page.QueryResult;
import com.huiwan.gdata.common.utils.copyo.BeanCopierUtils;
import com.huiwan.gdata.modules.sys.bean.MenuBean;
import com.huiwan.gdata.modules.sys.bean.RoleBean;
import com.huiwan.gdata.modules.sys.entity.Menu;
import com.huiwan.gdata.modules.sys.entity.Role;
import com.huiwan.gdata.modules.sys.entity.RoleGroup;
import com.huiwan.gdata.modules.sys.exception.ObjectExistException;
import com.huiwan.gdata.modules.sys.mapper.MenuMapper;
import com.huiwan.gdata.modules.sys.mapper.RoleMapper;
import com.huiwan.gdata.modules.sys.service.IRoleService;
import com.huiwan.gdata.modules.sys.vo.RoleVo;

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
		query.setPageSize(pagesize);

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
		//删除角色关联
		int i= roleMapper.del(id);
		if(i>0){
			return roleMapper.delRoleMenu(id);
		}
		return i;
	}
	
	  /**
	   * 添加角色
	   */
	@Override
	public int add(Role role) throws ObjectExistException{
		if(role==null||StringUtils.isBlank(role.getName())){
			return 0;
		}
		int count =0;
		//如果id不存在则 是新增
		if(role.getId()==null||role.getId()<=0)
		{
			//角色名称是否存在
			Role roleExists=roleMapper.getByName(role.getName());
			if(roleExists!=null)
			{
				throw new ObjectExistException("角色已存在");
			}
			//role.setUpdateTime(new Date());
			//新增
			count= roleMapper.insertSelective(role);
			if (count <= 0) 
			{
				return 0;
			}
			// 关联菜单
			if (role.getMenuIds() != null && role.getMenuIds().size() > 0) 
			{
				
				for (String menuId : role.getMenuIds()) {
					roleMapper.addRoleMenu(role.getId(), menuId);
				}
			}
			
		}else{
			//id大于0  修改
			count=	roleMapper.updateByPrimaryKeySelective(role);
			if (count <= 0) 
			{
				return 0;
			}
			//先删除菜单
		    roleMapper.delRoleMenu(role.getId());
		    // 关联菜单
			if (role.getMenuIds() != null && role.getMenuIds().size() > 0) 
			{
				for (String menuId : role.getMenuIds()) {
					roleMapper.addRoleMenu(role.getId(), menuId);
				}
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

	@Override
	public List<Role> getRoleListAll() {
		return roleMapper.getRoleListAll();
	}

	@Override
	public Role getByName(String name) {
		return roleMapper.getByName(name);
		
	}

	@Override
	public List<RoleGroup> getGroupListAll() {
		return roleMapper.getGroupListAll();
	}

}
