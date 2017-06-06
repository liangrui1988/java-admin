package com.huiwan.gdata.modules.gdata.base;

import javax.annotation.Resource;
import javax.sql.DataSource;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;


/**
 * <p>
 * Title:DaoFactory
 * </p>
 * <p>
 * Description:封装常用SQL CRUD操作
 * </p>
 * <p>
 * 使用SPRING JDBC TEMPLATE
 * </p>
 * 
 * @Author Chenkangming @Date 2013-9-18
 * @version 1.5
 */
@Repository
@Transactional(rollbackFor = Exception.class)
public class XLog4Dao extends BaseDao{
	@Resource(name = "xLogDataSource")
	public void setDataSource(DataSource dataSource) {
		myjdbc = new JdbcTemplate(dataSource);
	}
	
	public DataSource getDataSource(){
		return this.myjdbc.getDataSource();
	}
	
	public JdbcTemplate getMyjdbc(){
		return this.myjdbc;
	}

}
