package com.huiwan.gdata.modules.sys.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.huiwan.gdata.common.bean.page.Query;
import com.huiwan.gdata.modules.sys.entity.Role;
import com.huiwan.gdata.modules.sys.entity.RoleGroup;

public interface RoleMapper {

	List<Role> queryPages(Query query);
	
	
	List<Role> getRoleListAll();

	Long getCount(Query query);

	Role get(@Param("id") int id);

	int del(int id);

	//int add(Role role);

	//int update(Role role);
	
	int addRoleMenu(@Param("roleId")int roleId,@Param("menuId")String menuId);
	
	int delRoleMenu(@Param("roleId")int roleId);
	
	
	
	int insert(Role role);
	int insertSelective(Role role);
	int update(Role role);
	int updateByPrimaryKeySelective(Role role);


	Role getByName(@Param("name")String name);


	List<RoleGroup> getGroupListAll();
}
