package com.huiwan.gdata.modules.sys.service.impl;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.apache.commons.lang3.StringUtils;
import org.apache.shiro.cache.Cache;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.huiwan.gdata.common.bean.page.Query;
import com.huiwan.gdata.common.bean.page.QueryResult;
import com.huiwan.gdata.common.cache.EhCacheKeys;
import com.huiwan.gdata.common.cache.SpringCacheManagerWrapper;
import com.huiwan.gdata.common.utils.copyo.BeanCopierUtils;
import com.huiwan.gdata.common.utils.spring.SysApplicationContext;
import com.huiwan.gdata.modules.sys.bean.UserBean;
import com.huiwan.gdata.modules.sys.entity.Menu;
import com.huiwan.gdata.modules.sys.entity.Role;
import com.huiwan.gdata.modules.sys.entity.User;
import com.huiwan.gdata.modules.sys.exception.UserExistException;
import com.huiwan.gdata.modules.sys.mapper.MenuMapper;
import com.huiwan.gdata.modules.sys.mapper.UserMapper;
import com.huiwan.gdata.modules.sys.service.IUserService;
import com.huiwan.gdata.modules.sys.utils.PassUtil;
import com.huiwan.gdata.modules.sys.vo.UserLoginVo;
import com.huiwan.gdata.modules.sys.vo.UserVo;

@Service
public class UserService implements IUserService {

	protected Logger logger = LoggerFactory.getLogger(UserService.class);

	@Autowired
	private UserMapper userMapper;

	@Autowired
	private MenuMapper menuMapper;

	@Override
	public QueryResult<UserBean> getUserList(int page, int pagesize, UserVo userVo) {
		Query query = new Query();
		query.setBean(userVo);
		query.setPageIndex(page);
		query.setPageSize(pagesize);

		// 组合分页信息
		QueryResult<UserBean> queryResult = new QueryResult<UserBean>();
		Long count = userMapper.getCount(query);
		List<User> list = userMapper.queryPages(query);
		List<UserBean> listBean = new ArrayList<UserBean>();
		if (list != null && list.size() > 0) {
			for (int i = 0; i < list.size(); i++) {
				User user = list.get(i);
				if (user != null && user.getId() > 0) {
					UserBean userBean = new UserBean();
					BeanCopierUtils.copyProperties(user, userBean);
					listBean.add(userBean);
				}
			}
		}
		// 总页数 和 取多少条
		queryResult.setPages(count, pagesize);
		queryResult.setItems(listBean);
		return queryResult;
	}

	// FIXME:cache
	@Override
	public UserBean get(int userId) {
		UserBean cacheBean = getCacheUser(userId);
		// 暂不考虑穿透 +暂位
		if (cacheBean == null || cacheBean.getId() == null || cacheBean.getId() <= 0) {
			User user = userMapper.get(userId);
			cacheBean = new UserBean();
			BeanCopierUtils.copyProperties(user, cacheBean);
			putCacheUser(userId, cacheBean);
		} else {
			logger.debug("get user cache test key:userId:{}=value:{}", userId, cacheBean);
		}
		return cacheBean;
	}

	private UserBean getCacheUser(int userId) {
		SpringCacheManagerWrapper cacheManager = (SpringCacheManagerWrapper) SysApplicationContext
				.getBean("cacheManager");
		if (cacheManager == null) {
			return null;
		}

		Cache<String, UserBean> cache = cacheManager.getCache(EhCacheKeys.SYS_USER_BEAN);
		return cache.get(String.valueOf(userId));

	}

	private void putCacheUser(int userId, UserBean cacheBean) {
		SpringCacheManagerWrapper cacheManager = (SpringCacheManagerWrapper) SysApplicationContext
				.getBean("cacheManager");
		if (cacheManager == null) {
			return;
		}
		if (cacheBean != null && cacheBean.getId() != null && cacheBean.getId() > 0) {
			Cache<String, UserBean> cache = cacheManager.getCache(EhCacheKeys.SYS_USER_BEAN);
			cache.put(String.valueOf(userId), cacheBean);
		}
	}

	private void delCacheUser(int userId) {
		SpringCacheManagerWrapper cacheManager = (SpringCacheManagerWrapper) SysApplicationContext
				.getBean("cacheManager");
		if (cacheManager == null) {
			return;
		}

		Cache<String, UserBean> cache = cacheManager.getCache(EhCacheKeys.SYS_USER_BEAN);
		cache.remove(String.valueOf(userId));

	}

	@Override
	public int del(int userId) {
		delCacheUser(userId);
		int i = userMapper.del(userId);
		if (i > 0) {
			return userMapper.delUserRole(userId);
		}
		return i;
	}

	@Override
	public int add(User user) throws UserExistException {
		// 为开发速度 不考虑 分离转换
		// User user=new User();
		// BeanCopier copier = BeanCopier.create(UserVo.class, User.class,
		// false);
		// copier.copy(userVo, user, null);

		if (user == null || StringUtils.isBlank(user.getUserName()) || StringUtils.isBlank(user.getPassword())
				|| user.getRoles() == null || user.getRoles().size() <= 0) {
			return 0;
		}
		int count = 0;
		if (user.getId() != null && user.getId() > 0) {// 修改
			if (!StringUtils.isBlank(user.getPassword())) {
				user.setPassword(PassUtil.encryptPassword(user.getPassword(), user.getUserName()));
			}
			count = userMapper.updateByPrimaryKeySelective(user);
			// 用户拥有的角色
			if (count > 0) {
				// 删除用户拥有的角色
				userMapper.delUserRole(user.getId());
				// 如果没有角色更新的 返回
				if (user.getRoles() == null || user.getRoles().size() <= 0) {
					return count;
				}
				// 用户拥有的角色
				for (Role role : user.getRoles()) {
					if (role.getId() != null && role.getId() > 0) {
						userMapper.addUserRole(user.getId(), role.getId());
					}
				}
			}

		} else {// 新增
				// 登录名是否存在
			UserLoginVo vo = new UserLoginVo();
			vo.setUserName(user.getUserName());
			User isExistsUser = userMapper.query(vo);
			if (isExistsUser != null && isExistsUser.getId() > 0) {
				throw new UserExistException("用户已存在");
			}
			//
			user.setPassword(PassUtil.encryptPassword(user.getPassword(), user.getUserName()));
			count = userMapper.insertSelective(user);
			if (count > 0) {
				// 用户拥有的角色
				for (Role role : user.getRoles()) {
					if (role.getId() != null && role.getId() > 0) {
						int countx = userMapper.addUserRole(user.getId(), role.getId());
						// if(countx<=0){
						// throw new SysRuntimeException("添加角色失败");
						// }
					}
				}
				// 用户to部门
				// if (user.getDepartmentId()!=null&&user.getDepartmentId() > 0)
				// {
				// userMapper.addUserDepartment(user.getId(),
				// user.getDepartmentId());
				// }
			}
		}
		return count;
	}

	@Override
	public int update(User user) {
		if (user == null) {
			return 0;
		}

		if (!StringUtils.isBlank(user.getPassword())) {
			user.setPassword(PassUtil.encryptPassword(user.getPassword(), user.getUserName()));
		}
		int count = userMapper.updateByPrimaryKeySelective(user);
		// 用户拥有的角色
		if (count > 0) {
			// 删除用户拥有的角色
			userMapper.delUserRole(user.getId());
			// 如果没有角色更新的 返回
			if (user.getRoles() == null || user.getRoles().size() <= 0) {
				return count;
			}
			// 用户拥有的角色
			for (Role role : user.getRoles()) {
				if (role.getId() != null && role.getId() > 0) {
					userMapper.addUserRole(user.getId(), role.getId());
				}
			}
			// 用户to部门
			// if (user.getDepartmentId() > 0) {
			// User user2 = userMapper.get(user.getId());
			//
			// if (user2 == null || user2.getId() <= 0) {
			// // FIXME:抛异常 回滚
			// }
			// // 如果部门有改变，才修改
			// if (user2.getDepartmentId() != user.getDepartmentId()) {
			// userMapper.delUserDepartment(user.getId());
			// userMapper.addUserDepartment(user.getId(),
			// user.getDepartmentId());
			// }
			// }
			delCacheUser(user.getId());

		}
		return count;
	}

	@Override
	public UserBean getUser(String username) {
		UserLoginVo userLoginVo = new UserLoginVo();
		userLoginVo.setUserName(username);
		User user = userMapper.query(userLoginVo);
		UserBean userBean = new UserBean();
		BeanCopierUtils.copyProperties(user, userBean);
		return userBean;
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

	/**
	 * 获取权限
	 */
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
				result.add(m.getId());
			}
		}
		return result;
	}

	/**
	 * 获取菜单
	 */
	@Override
	public List<Menu> getUserMenus(String username) {
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
			for (Menu m : menu) {
				if (m == null || m.getStatus() != 0) {
					continue;
				}
				menus.add(m);
			}
		}
		return menus;
	}

	@Override
	public User queryByUserName(String username) {
		return userMapper.queryByUserName(username);
	}

}
