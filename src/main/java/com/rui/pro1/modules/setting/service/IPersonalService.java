package com.rui.pro1.modules.setting.service;

import com.rui.pro1.modules.sys.entity.User;

public interface IPersonalService {

	int updatePassword(String password, int id, String userName);

	int update(User user);

}
