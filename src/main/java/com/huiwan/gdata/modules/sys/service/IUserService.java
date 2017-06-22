package com.huiwan.gdata.modules.sys.service;

import java.util.List;
import java.util.Set;

import com.huiwan.gdata.common.bean.page.QueryResult;
import com.huiwan.gdata.modules.sys.bean.UserBean;
import com.huiwan.gdata.modules.sys.entity.Menu;
import com.huiwan.gdata.modules.sys.entity.User;
import com.huiwan.gdata.modules.sys.vo.UserVo;

/**
 * 
 * @author ruiliang
 *
 */
public interface IUserService {

	QueryResult<UserBean> getUserList(int page, int pagesize, UserVo user);

	UserBean get(int userId);

	int del(int userId);

	int add(User user) throws Exception;

	int update(User user);

	UserBean getUser(String username);

	Set<String> getUserRole(String username);

	Set<String> getUserPermissions(String username);

	List<Menu> getUserMenus(String username);

	User queryByUserName(String username);

}
