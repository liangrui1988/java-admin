package com.huiwan.gdata.modules.setting.service;

import com.huiwan.gdata.modules.sys.entity.User;

public interface IPersonalService {

	int updatePassword(String password, int id, String userName);

	int update(User user);

}
