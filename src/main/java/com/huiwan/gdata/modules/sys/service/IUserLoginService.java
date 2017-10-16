package com.huiwan.gdata.modules.sys.service;

import com.huiwan.gdata.modules.sys.bean.UserBean;
import com.huiwan.gdata.modules.sys.entity.User;
import com.huiwan.gdata.modules.sys.vo.UserLoginVo;

public interface IUserLoginService {

	UserBean login(UserLoginVo userLoginVo);
	
	
	User getUser(String username);


	int logout(UserLoginVo userLoginVo);

}
