package com.rui.pro1.modules.sys.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.rui.pro1.common.bean.page.Query;
import com.rui.pro1.modules.sys.entity.User;

public interface UserMapper {

	User get(@Param("id") int id);

	List<User> queryPages(Query query);
	
	Long getCount(Query query);
}
