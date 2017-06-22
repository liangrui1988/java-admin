package com.huiwan.gdata.modules.gdata.base;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.jdbc.support.rowset.SqlRowSet;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;



public abstract class BaseDao {
	protected Logger log = LoggerFactory.getLogger(getClass());

	protected JdbcTemplate myjdbc;

	/**
	 * 写入
	 * 
	 * @param sql
	 * @param params
	 * @return int
	 * @throws Exception
	 */
	@Transactional(rollbackFor = Exception.class)
	public int insert(String sql, Object[] params) {
		int result = 0;
		if (StringUtils.isEmpty(params)) {
			result = myjdbc.update(sql);
		} else {
			result = myjdbc.update(sql, params);
		}
		return result;
	}

	/**
	 * 写入
	 * 
	 * @param sql
	 * @return int
	 */
	@Transactional(rollbackFor = Exception.class)
	public int insert(String sql) {
		return myjdbc.update(sql);
	}

	/**
	 * 批量写入
	 * 
	 * @param sql
	 * @return
	 */
	@Transactional(rollbackFor = Exception.class)
	public int[] doBatch(String... sql) {
		return myjdbc.batchUpdate(sql);
	}

	/**
	 * 批量写入
	 * 
	 * @param sql
	 * @param params
	 * @return
	 */
	public int[] doBatch(String sql, List<Object[]> params) {
		return myjdbc.batchUpdate(sql, params);
	}

	@Transactional(rollbackFor = Exception.class)
	public void excute(String sql) {
		myjdbc.execute(sql);
	}

	/**
	 * 写入返回主键
	 * 
	 * @param sql
	 * @param params
	 * @return long keyid
	 */
	@Transactional(rollbackFor = Exception.class)
	public long insertForId(final String sql, final Object[] params) {
		long keyId = -1;
		try {
			KeyHolder holder = new GeneratedKeyHolder();
			myjdbc.update(new PreparedStatementCreator() {
				public PreparedStatement createPreparedStatement(Connection conn) throws SQLException {
					PreparedStatement ps = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
					for (int i = 0; i < params.length; i++) {
						ps.setObject(i + 1, params[i]);
					}
					return ps;
				}
			}, holder);
			keyId = holder.getKey().longValue();
		} catch (Exception e) {
			log.error(e.getMessage());
		}
		return keyId;
	}

	/**
	 * 更新
	 * 
	 * @param sql
	 * @param params
	 * @return int
	 */
	@Transactional(rollbackFor = Exception.class)
	public int update(String sql, Object[] params) {
		int result = 0;
		if (StringUtils.isEmpty(params)) {
			result = myjdbc.update(sql);
		} else {
			result = myjdbc.update(sql, params);
		}
		return result;
	}

	/**
	 * 更新
	 * 
	 * @param sql
	 * @param params
	 * @return int
	 */
	@Transactional(rollbackFor = Exception.class)
	public int update(String sql) {
		return update(sql, null);
	}

	/**
	 * 删除
	 * 
	 * @param sql
	 * @return int
	 */
	@Transactional(rollbackFor = Exception.class)
	public int delete(String sql) {
		return myjdbc.update(sql);
	}

	/**
	 * 删除
	 * 
	 * @param sql
	 * @param params
	 * @return int
	 */
	@Transactional(rollbackFor = Exception.class)
	public int delete(String sql, Object[] params) {
		int result = 0;
		if (StringUtils.isEmpty(params)) {
			result = myjdbc.update(sql);
		} else {
			result = myjdbc.update(sql, params);
		}
		return result;
	}

	/**
	 * 查找
	 * 
	 * @param sql
	 * @param params
	 * @return rows
	 */
	public SqlRowSet select(String sql, Object[] params) {
		SqlRowSet srs = null;
		try {
			if (StringUtils.isEmpty(params)) {
				srs = (SqlRowSet) myjdbc.queryForRowSet(sql);
			} else {
				srs = (SqlRowSet) myjdbc.queryForRowSet(sql, params);
			}
		} catch (Exception e) {
			log.error(e.getMessage(), e);
		}
		return srs;
	}

	/**
	 * 查找
	 * 
	 * @param sql
	 * @return rows
	 */
	public SqlRowSet select(String sql) {
		SqlRowSet srs = null;
		try {
			srs = (SqlRowSet) myjdbc.queryForRowSet(sql);
		} catch (Exception e) {
			log.error(e.getMessage(), e);
		}
		return srs;
	}

	/**
	 * 返回List
	 * 
	 * @param <T>
	 * @param sql
	 * @param params
	 * @param rowMapper
	 * @return
	 */
	public <T> List<T> selectObjectList(String sql, Object[] params, RowMapper<T> rowMapper) {
		if (params == null) {
			return selectObjectList(sql, rowMapper);
		} else {
			return myjdbc.query(sql, params, rowMapper);
		}
	}

	/**
	 * 返回List
	 * 
	 * @param <T>
	 * @param sql
	 * @param params
	 * @param rowMapper
	 * @return
	 */
	public <T> List<T> selectObjectList(String sql, RowMapper<T> rowMapper) {
		return myjdbc.query(sql, rowMapper);
	}

	/**
	 * 返回model
	 * 
	 * @param <T>
	 * @param sql
	 * @param params
	 * @param rowMapper
	 * @return
	 */
	public <T> T selectObject(String sql, Object[] params, RowMapper<T> rowMapper) {
		try {
			if (params == null) {
				return selectObject(sql, rowMapper);
			} else {
				return myjdbc.queryForObject(sql, params, rowMapper);
			}
		} catch (Exception e) {
			return null;
		}
	}

	/**
	 * 返回model
	 * 
	 * @param <T>
	 * @param sql
	 * @param params
	 * @param rowMapper
	 * @return
	 */
	public <T> T selectObject(String sql, RowMapper<T> rowMapper) {
		try {
			return myjdbc.queryForObject(sql, rowMapper);
		} catch (Exception e) {
			return null;
		}
	}

	/**
	 * 查询返回条数，常用于分页
	 * 
	 * @param sql
	 * @param params
	 * @return long
	 */
	public int selectForRows(String sql, Object[] params) {
		Integer result = 0;
		try {
			if (StringUtils.isEmpty(params)) {
				result = myjdbc.queryForObject(sql, Integer.class);
			} else {
				result = myjdbc.queryForObject(sql, params, Integer.class);
			}
		} catch (Exception e) {
			log.error(e.getMessage(), e);
		}
		if (result == null) {
			result = 0;
		}
		return result;
	}

	/**
	 * 查询返回条数，常用于分页
	 * 
	 * @param sql
	 * @param params
	 * @return int
	 */
	public int selectForRows(String sql) {
		int result = 0;
		try {
			result = myjdbc.queryForObject(sql, Integer.class);
		} catch (Exception e) {
			log.error(e.getMessage(), e);
		}
		return result;
	}
}
