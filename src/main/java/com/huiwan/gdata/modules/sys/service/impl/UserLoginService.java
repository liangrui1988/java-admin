package com.huiwan.gdata.modules.sys.service.impl;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.huiwan.gdata.common.utils.copyo.BeanCopierUtils;
import com.huiwan.gdata.modules.sys.bean.UserBean;
import com.huiwan.gdata.modules.sys.entity.User;
import com.huiwan.gdata.modules.sys.mapper.UserMapper;
import com.huiwan.gdata.modules.sys.service.IUserLoginService;
import com.huiwan.gdata.modules.sys.utils.PassUtil;
import com.huiwan.gdata.modules.sys.vo.UserLoginVo;

@Service
public class UserLoginService implements IUserLoginService {
	protected Logger logger = LoggerFactory.getLogger(getClass());

	@Autowired
	private UserMapper userMapper;
	
	

	@Override
	public UserBean login(UserLoginVo userLoginVo) {
		if(userLoginVo==null||StringUtils.isBlank(userLoginVo.getUserName())||StringUtils.isBlank(userLoginVo.getPassword())){
			logger.warn("用户名密码为null 登陆！return");
			return null;
		}
		
		//sessionId
		
		//cokie
		
		//处理密码
		String password=PassUtil.encryptPassword(userLoginVo.getPassword(), userLoginVo.getUserName());
		userLoginVo.setPassword(password);
		
		User user=userMapper.query(userLoginVo);
		
		if(user==null||user.getId()<=0){
			return null;
		}
		
		UserBean userBean=new UserBean();
		BeanCopierUtils.copyProperties(user, userBean);
		return userBean;
	}

	@Override
	public int logout(UserLoginVo userLoginVo) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public User getUser(String username) {
		UserLoginVo userLoginVo = new UserLoginVo();
		userLoginVo.setUserName(username);
		User user = userMapper.query(userLoginVo);
		return user;
	}

}
