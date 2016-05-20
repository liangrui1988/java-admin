package com.rui.pro1.modules.sys.service.impl;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.rui.pro1.common.bean.page.Query;
import com.rui.pro1.common.bean.page.QueryResult;
import com.rui.pro1.modules.sys.entity.Menu;
import com.rui.pro1.modules.sys.entity.Role;
import com.rui.pro1.modules.sys.entity.User;
import com.rui.pro1.modules.sys.mapper.MenuMapper;
import com.rui.pro1.modules.sys.mapper.UserMapper;
import com.rui.pro1.modules.sys.service.IUserService;
import com.rui.pro1.modules.sys.vo.UserLoginVo;
import com.rui.pro1.modules.sys.vo.UserVo;

@Service
public class UserService implements IUserService {

	protected Logger logger = LoggerFactory.getLogger(getClass());

	@Autowired
	private UserMapper userMapper;

	@Autowired
	private MenuMapper menuMapper;

	@Override
	public QueryResult<User> getUserList(int page, int pagesize, UserVo user) {
		// User user=new User();
		// user.setId(1);
		// user.setName("user001");
		// user.setPassowd("pass");
		//
		// List<User> list=new ArrayList<User>();
		// list.add(user);

		Query query = new Query();
		query.setBean(user);
		query.setPageIndex(page);

		// 组合分页信息
		QueryResult<User> queryResult = new QueryResult<User>();
		Long count = userMapper.getCount(query);
		List<User> list = userMapper.queryPages(query);
		// 总页数 和 取多少条
		queryResult.setPages(count, pagesize);
		queryResult.setItems(list);

		return queryResult;
	}

	@Override
	public User get(int userId) {
		return userMapper.get(userId);
	}

	@Override
	public int del(int userId) {
		return userMapper.del(userId);
	}

	@Override
	public int add(User user) {
		// 为速度 不考虑 分离转换
		// User user=new User();
		// BeanCopier copier = BeanCopier.create(UserVo.class, User.class,
		// false);
		// copier.copy(userVo, user, null);

		if (user == null || user.getRoles() == null
				|| user.getRoles().size() <= 0) {
			return 0;
		}

		int count = userMapper.add(user);
		if (count > 0) {
			// 用户拥有的角色
			for (Role role : user.getRoles()) {
				if (role.getId() > 0) {
					userMapper.addUserRole(user.getId(), role.getId());
					// FIXME:如果不成功抛异常
				}
			}
			// 用户to部门
			if (user.getDepartmentId() > 0) {
				userMapper.addUserDepartment(user.getId(),
						user.getDepartmentId());
			}
		}
		return count;
	}

	@Override
	public int update(User user) {
		if (user == null) {
			return 0;
		}

		int count = userMapper.update(user);

		// 用户拥有的角色
		if (count > 0) {
			// 如果没有角色更新的 返回
			if (user.getRoles() == null || user.getRoles().size() <= 0) {
				return count;
			}
			// 删除用户拥有的角色
			userMapper.delUserRole(user.getId());
			// 用户拥有的角色
			for (Role role : user.getRoles()) {
				if (role.getId() > 0) {
					userMapper.addUserRole(user.getId(), role.getId());
					// FIXME:如果不成功抛异常
				}
			}
			// 用户to部门
			if (user.getDepartmentId() > 0) {
				User user2 = userMapper.get(user.getId());

				if (user2 == null || user2.getId() <= 0) {
					// FIXME:抛异常 回滚
				}
				// 如果部门有改变，才修改
				if (user2.getDepartmentId() != user.getDepartmentId()) {
					userMapper.delUserDepartment(user.getId());
					userMapper.addUserDepartment(user.getId(),
							user.getDepartmentId());
				}
			}
		}
		return count;
	}

	@Override
	public User getUser(String username) {

		UserLoginVo userLoginVo = new UserLoginVo();
		userLoginVo.setUserName(username);

		User user = userMapper.query(userLoginVo);

		return user;
	}

	@Override
	public Set<String> getUserRole(String username) {
		UserLoginVo userLoginVo = new UserLoginVo();
		userLoginVo.setUserName(username);

		User user = userMapper.query(userLoginVo);

		if (user == null) {
			return null;
		}

		if (user.getRoles() == null || user.getRoles().size() <= 0) {
			return null;
		}

		Set<String> roles = new HashSet<String>();
		for (Role role : user.getRoles()) {
			roles.add(role.getName());
		}
		return roles;
	}

	@Override
	public Set<String> getUserPermissions(String username) {

		UserLoginVo userLoginVo = new UserLoginVo();
		userLoginVo.setUserName(username);
		User user = userMapper.query(userLoginVo);
		if (user == null) {
			return null;
		}

		if (user.getRoles() == null || user.getRoles().size() <= 0) {
			return null;
		}
		Set<String> result = new HashSet<String>();
		for (Role role : user.getRoles()) {
			List<Menu> mes = menuMapper.getAllMenuByRoleId(role.getId());
			if (mes == null || mes.size() <= 0) {
				continue;
			}
			for (Menu m : mes) {
				result.add(m.getPermission());
			}
		}

		return result;
	}

	@Override
	public List<Menu> getUserMenus(String username) {

//		UserLoginVo userLoginVo = new UserLoginVo();
//		userLoginVo.setUserName(username);
		//User user = userMapper.query(userLoginVo);
		
		User user = userMapper.queryByUserName(username);
		
		if (user == null) {
			return null;
		}

		if (user.getRoles() == null || user.getRoles().size() <= 0) {
			return null;
		}

		List<Menu> menus = new ArrayList<Menu>();
		for (Role role : user.getRoles()) {
			List<Menu> menu = menuMapper.getAllMenuByRoleId(role.getId());
			if (menu == null || menu.size() <= 0) {
				continue;
			}
			
			for(Menu m:menu){
				if(m==null||m.getStatus()!=0){
					continue;
				}
				menus.add(m);
			}
			//menus.addAll(menu);

		}

		return menus;
	}

}
