package com.rui.pro1.modules.sys.service.impl;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.rui.pro1.common.bean.page.Query;
import com.rui.pro1.common.bean.page.QueryResult;
import com.rui.pro1.modules.sys.entity.User;
import com.rui.pro1.modules.sys.mapper.UserMapper;
import com.rui.pro1.modules.sys.service.IUserService;
import com.rui.pro1.modules.sys.vo.UserVo;

@Service
public class UserService implements IUserService {

	protected Logger logger = LoggerFactory.getLogger(getClass());

	@Autowired
	private UserMapper userMapper;

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

		int count = userMapper.add(user);
		if (count > 0) {
			// FIXME:add role
			// FIXME:add user_depment
			// add user_role_

		}

		return count;
	}

	@Override
	public int update(User user) {
		return userMapper.update(user);
	}

}
