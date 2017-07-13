package com.huiwan.gdata.modules.gdata.combat.service.impl;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Service;

import com.huiwan.gdata.modules.gdata.base.SqliteDao;
import com.huiwan.gdata.modules.gdata.combat.entity.SqliteBean;
import com.huiwan.gdata.modules.gdata.combat.service.SqliteService;

@Service
public class SqliteServiceImpl implements SqliteService {
	@Autowired
	private SqliteDao sqliteDao;

	public SqliteServiceImpl() {
	}

	public List<SqliteBean> gett() {
		String sql = "SELECT act_id,act_type FROM activity";
		return sqliteDao.selectObjectList(sql, rowMapper);

	}

	private RowMapper<SqliteBean> rowMapper = new RowMapper<SqliteBean>() {
		@Override
		public SqliteBean mapRow(ResultSet rs, int rowNum) throws SQLException {
			SqliteBean bean = new SqliteBean();
			bean.setAct_id(rs.getString("act_id"));
			bean.setAct_type(rs.getString("act_type"));
			return bean;
		}
	};

}
