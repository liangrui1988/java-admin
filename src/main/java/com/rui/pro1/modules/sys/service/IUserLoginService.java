package com.rui.pro1.modules.sys.service;

import com.rui.pro1.modules.sys.bean.UserBean;
import com.rui.pro1.modules.sys.vo.UserLoginVo;

public interface IUserLoginService {

	UserBean login(UserLoginVo userLoginVo);

	int logout(UserLoginVo userLoginVo);

}
