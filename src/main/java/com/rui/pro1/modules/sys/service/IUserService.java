package com.rui.pro1.modules.sys.service;

import java.util.List;
import java.util.Set;

import com.rui.pro1.common.bean.page.QueryResult;
import com.rui.pro1.modules.sys.bean.UserBean;
import com.rui.pro1.modules.sys.entity.Menu;
import com.rui.pro1.modules.sys.entity.User;
import com.rui.pro1.modules.sys.vo.UserLoginVo;
import com.rui.pro1.modules.sys.vo.UserVo;

/**
 * 
 * @author ruiliang
 *
 */
public interface IUserService {

	QueryResult<User> getUserList(int page, int pagesize, UserVo user);

	User get(int userId);

	int del(int userId);

	int add(User user);

	int update(User user);
	
	User getUser(String username);
	
	Set<String> getUserRole(String username);

	Set<String> getUserPermissions(String username);
	
	 List<Menu> getUserMenus(String username);

}
