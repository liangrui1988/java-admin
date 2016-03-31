package com.rui.pro1.modules.sys.service;

import com.rui.pro1.common.bean.page.QueryResult;
import com.rui.pro1.modules.sys.entity.User;
import com.rui.pro1.modules.sys.vo.UserVo;

public interface IUserService {
	
	QueryResult<User> getUserList(int page,int pagesize,UserVo user);

}
