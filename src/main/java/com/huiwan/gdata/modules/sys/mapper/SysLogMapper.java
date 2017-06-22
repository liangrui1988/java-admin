package com.huiwan.gdata.modules.sys.mapper;

import java.util.List;

import com.huiwan.gdata.common.bean.page.Query;
import com.huiwan.gdata.modules.sys.entity.SysLog;

public interface SysLogMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(SysLog record);

    int insertSelective(SysLog record);

    SysLog selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(SysLog record);

    int updateByPrimaryKey(SysLog record);

	Long getCount(Query query);

	List<SysLog> queryPages(Query query);
}