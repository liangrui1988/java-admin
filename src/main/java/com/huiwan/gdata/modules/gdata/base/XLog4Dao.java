package com.huiwan.gdata.modules.gdata.base;

import javax.annotation.Resource;
import javax.sql.DataSource;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

/**
 * x_log
 * @author rui
 *
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
