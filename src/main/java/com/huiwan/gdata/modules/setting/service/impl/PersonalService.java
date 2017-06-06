package com.huiwan.gdata.modules.setting.service.impl;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.huiwan.gdata.modules.setting.service.IPersonalService;
import com.huiwan.gdata.modules.sys.entity.User;
import com.huiwan.gdata.modules.sys.mapper.UserMapper;
import com.huiwan.gdata.modules.sys.utils.PassUtil;
@Service
public class PersonalService implements IPersonalService {

	
	@Autowired
	private UserMapper userMapper;

	@Override
	public int update(User user) {
		if (user == null) {
			return 0;
		}
		
		int count = userMapper.updateByPrimaryKeySelective(user);

		return count;
	}
	
	
	@Override
	public int updatePassword(String password,int id,String userName) {
		if (id <=0||StringUtils.isBlank(password)||StringUtils.isBlank(userName)) {
			return 0;
		}
		
		User user=new User();
		user.setId(id);		
		user.setPassword(PassUtil.encryptPassword(password, userName));
		int count = userMapper.updateByPrimaryKeySelective(user);

		return count;
	}

}
