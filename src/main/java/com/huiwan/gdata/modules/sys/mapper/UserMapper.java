package com.huiwan.gdata.modules.sys.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.huiwan.gdata.common.bean.page.Query;
import com.huiwan.gdata.modules.sys.entity.User;
import com.huiwan.gdata.modules.sys.vo.UserLoginVo;

public interface UserMapper {

	List<User> queryPages(Query query);

	Long getCount(Query query);

	User get(@Param("id") int id);

	int del(int userId);

	//int add(User user);
	
	int insert(User user);
	int insertSelective(User user);
	int update(User user);
	int updateByPrimaryKeySelective(User user);
	
	User query(UserLoginVo UserLoginVo);


	// -------------------------
	int addUserRole(@Param("userId") int userId, @Param("roleId") int roleId);

	int addUserDepartment(@Param("userId") int userId,
			@Param("departmentId") int departmentId);

	int delUserRole(@Param("userId") int userId);
	
	int delUserDepartment(@Param("userId") int userId);

	
	int updateUserDepartment(@Param("userId") int userId);

	User queryByUserName(@Param("userName") String username);


}
