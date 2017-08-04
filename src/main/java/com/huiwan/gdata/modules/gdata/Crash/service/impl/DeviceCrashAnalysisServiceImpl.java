package com.huiwan.gdata.modules.gdata.Crash.service.impl;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Service;

import com.huiwan.gdata.common.utils.pagination.Paginator;
import com.huiwan.gdata.common.utils.pagination.PaginatorResult;
import com.huiwan.gdata.modules.gdata.Crash.entity.CrashLog;
import com.huiwan.gdata.modules.gdata.Crash.entity.DeviceCrashAny;
import com.huiwan.gdata.modules.gdata.Crash.service.DeviceCrashAnalysisService;
import com.huiwan.gdata.modules.gdata.base.GDataDao;
import com.huiwan.gdata.modules.gdata.base.XLog4Dao;
import com.huiwan.gdata.modules.gdata.base.charset.CharsetDataUtil;
import com.huiwan.gdata.modules.gdata.base.charset.bean.QueryCommBean;
import com.huiwan.gdata.modules.gdata.base.charset.bean.common.CharsetBeanCommon;
//import com.uc.db.base.dao.DBCrashLogDao;
//import com.uc.db.business.dao.XLog4Dao;
//import com.uc.db.business.service.device.DeviceCrashAnalysisService;
//import com.uc.db.business.x8.model.device.CrashLog;
//import com.uc.db.business.x8.model.device.DeviceCrashAny;
//import com.uc.db.utils.SQLUtil;
//import com.uc.db.utils.charset.CharsetDataUtil;
//import com.uc.db.utils.charset.bean.ChartsQueryVo;
//import com.uc.db.utils.charset.bean.common.CharsetBeanCommon;
//import com.uc.db.utils.pagination.Paginator;
//import com.uc.db.utils.pagination.PaginatorResult;
import com.huiwan.gdata.modules.gdata.util.SQLUtil;

@Service
public class DeviceCrashAnalysisServiceImpl implements DeviceCrashAnalysisService {

	Logger log = LoggerFactory.getLogger(getClass());

	@Autowired
	private GDataDao gdataDao;
	
	
//	@Autowired
//	private XLog4Dao mydb;

//	@Autowired
	private XLog4Dao mydb;

	@Override
	public List<CharsetBeanCommon> getData(QueryCommBean vo) {
		QueryCommBean query = (QueryCommBean) vo.clone2();
		// 相等安小时取
		// if (query.getDt1().equals(query.getDt2())) {
		// return getHourData(query);
		// }
		String table = "x8_crash_log";
		if (StringUtils.isNotBlank(vo.getGameMark())) {
			table = vo.getGameMark() + "_crash_log";
		}
		// 参数
		List<Object> paramArray = new LinkedList<Object>();
		// 条件组装
		StringBuffer sqlWhere = getSQLString(vo);// 完成
		StringBuffer sql = new StringBuffer(512);
		if (vo.getDt1().equals(vo.getDt2())) {
			sql.append("SELECT flag fla,to_char(dt,'HH') dt_x,count(*) nums FROM  ");
		} else {
			sql.append("SELECT flag fla,to_char(dt,'YYYY-MM-DD') dt_x,count(*) nums FROM  ");
		}

		sql.append(table);
		sql.append(sqlWhere);
		// 按天分组
		sql.append(" GROUP BY fla,dt_x order by fla,dt_x");

		log.info("sql:>>>\n{}\n param={}", sql.toString(), paramArray.toArray());
		List<CharsetBeanCommon> data = gdataDao.selectObjectList(sql.toString(), rowMapper);
		return data;
	}

	/**
	 * 按小时间取数据
	 * 
	 * @param vo
	 * @return
	 */
	public List<CharsetBeanCommon> getHourData(QueryCommBean vo) {
		String table = "x8_crash_log";
		if (StringUtils.isNotBlank(vo.getGameMark())) {
			table = vo.getGameMark() + "_crash_log";
		}
		// 参数
		List<Object> paramArray = new LinkedList<Object>();
		// 条件组装
		StringBuffer sqlWhere = getSQLString(vo);// 完成
		StringBuffer sql = new StringBuffer(512);
		sql.append("SELECT flag fla,to_char(dt,'HH') dt_x,count(*) nums FROM ");
		sql.append(table);
		sql.append(sqlWhere);
		sql.append(" GROUP BY fla,dt_x order by fla,dt_x");
		log.info("sql:>>>\n{}\n param={}", sql.toString(), paramArray.toArray());
		List<CharsetBeanCommon> data = gdataDao.selectObjectList(sql.toString(), rowMapper);
//		List<CharsetBeanCommon> data2 = gdataDao.selectObjectList(sql.toString(), new BeanPropertyRowMapper<CharsetBeanCommon>(CharsetBeanCommon.class));
		return data;
	}

	private RowMapper<CharsetBeanCommon> rowMapper = new RowMapper<CharsetBeanCommon>() {
		@Override
		public CharsetBeanCommon mapRow(ResultSet rs, int rowNum) throws SQLException {
			CharsetBeanCommon bean = new CharsetBeanCommon();
			bean.setId(rs.getString("fla"));
			bean.setxName(rs.getString("dt_x"));
			bean.setCount(rs.getInt("nums"));
			return bean;
		}
	};

	private StringBuffer getSQLString(QueryCommBean vo) {
		// 条件语句
		StringBuffer sbWhere = new StringBuffer();
		if (vo != null) {
			// 服务器
			// if (vo.getServer() != null && vo.getServer() > 0) {
			// sbWhere.append(" and server_id=");
			// sbWhere.append(vo.getServer());
			// }
			// 日期
			if (StringUtils.isNotBlank(vo.getDt1())) {// 如果不为空，
				sbWhere.append(" and dt>='");
				sbWhere.append(vo.getDt1());
				sbWhere.append("'");
			}

			if (StringUtils.isNotBlank(vo.getDt2())) {// 如果不为空，
				sbWhere.append(" and dt<='");
				sbWhere.append(vo.getDt2());
				sbWhere.append(" 23:59:59'");
			}
			// 平台
			if (StringUtils.isNotBlank(vo.getOs()) && !"-1".equals(vo.getOs())) {
				sbWhere.append(" and os='");
				sbWhere.append(vo.getOs().toLowerCase());
				sbWhere.append("'");
			}

			if (StringUtils.isNotBlank(vo.getType()) && !"-1".equals(vo.getType())) {
				sbWhere.append(" and app_v='");
				sbWhere.append(vo.getType());
				sbWhere.append("'");
			}
			// 1 JAVA 奔溃日志、2 C奔溃日志、3 Lua错误日志
			if (vo.getCommQueryInt() > 0) {
				sbWhere.append(" and flag=");
				sbWhere.append(vo.getCommQueryInt());
			}

		}
		if (StringUtils.isNotBlank(sbWhere.toString())) {
			// 删除 and 前4位
			sbWhere.delete(0, 5);
			sbWhere.insert(0, " where ");
		}
		// 转数组
		return sbWhere;
	}

	/**
	 * 获取总数据
	 */
	@Override
	public DeviceCrashAny getAny(QueryCommBean vo) {
		// 影响用户数-----------------------------------------------------
		String table_1 = "x8_crash_log";
		if (StringUtils.isNotBlank(vo.getGameMark())) {
			table_1 = vo.getGameMark() + "_crash_log";
		}

		// 参数
		List<Object> paramArray_3 = new LinkedList<Object>();
		// 条件组装
		StringBuffer sqlWhere_3 = getSQLString(vo);// 完成
		StringBuffer sql_3 = new StringBuffer(512);
		sql_3.append("SELECT flag fla, 0 dt_x, count(DISTINCT device_mark) nums FROM ");
		sql_3.append(table_1);
		sql_3.append(sqlWhere_3);
		sql_3.append(" GROUP BY fla");
		log.info("sql:>>>\n{}\n param={}", sql_3.toString(), paramArray_3.toArray());
		List<CharsetBeanCommon> beans = gdataDao.selectObjectList(sql_3.toString(), rowMapper);
		DeviceCrashAny bean = new DeviceCrashAny();
		if (beans != null && beans.size() > 0) {
			for (CharsetBeanCommon common : beans) {
				if ("1".equals(common.getId())) {
					bean.setJavaEffectNum(Integer.parseInt(common.getCount().toString()));
					continue;
				}
				if ("2".equals(common.getId())) {
					bean.setCcEffectNum(Integer.parseInt(common.getCount().toString()));
					continue;
				}
				if ("3".equals(common.getId())) {
					bean.setLuaEffectNum(Integer.parseInt(common.getCount().toString()));
				}
			}
		}
		// mydb---------------------------------------------------------
//		String table = SQLUtil.getTable1("log_client_init_v2", vo.getDt1());
//		String table2 = SQLUtil.getTable1("log_client_init_v2", vo.getDt2());
		
//		String table="log_client_init_v2_02";
//		// 参数
//		List<Object> paramArray = new LinkedList<Object>();
//		// 条件组装
//		StringBuffer sqlWhere = getAnySQLString(vo);// 完成
//		StringBuffer sql = new StringBuffer(512);
//		// 1小时取
//		sql.append("SELECT count(*) FROM ");
//		sql.append(table);
//		sql.append(sqlWhere);
//		log.info("sql:>>>\n{}\n param={}", sql.toString(), paramArray.toArray());
//		int data = mydb.selectForRows(sql.toString());
//		bean.setInitNum(data);
//		if (!table.equals(table2)) {// 2张表查询
//			StringBuffer sql_2 = new StringBuffer(512);
//			sql_2.append("SELECT count(*) FROM ");
//			sql_2.append(table2);
//			sql_2.append(sqlWhere);
//			log.info("sql:>>>\n{}\n param={}", sql_2.toString(), paramArray.toArray());
//			int data_2 = mydb.selectForRows(sql_2.toString());
//			bean.setInitNum(data + data_2);
//		}

//		return bean;
		return null;
	}

	private StringBuffer getAnySQLString(QueryCommBean vo) {
		// 条件语句
		StringBuffer sbWhere = new StringBuffer();
		if (vo != null) {
			// 服务器
			// if (vo.getServer() != null && vo.getServer() > 0) {
			// sbWhere.append(" and server_id=");
			// sbWhere.append(vo.getServer());
			// }
			// 日期
			if (StringUtils.isNotBlank(vo.getDt1())) {// 如果不为空，
				sbWhere.append(" and dt>='");
				sbWhere.append(vo.getDt1());
				sbWhere.append("'");
			}

			if (StringUtils.isNotBlank(vo.getDt2())) {// 如果不为空，
				sbWhere.append(" and dt<='");
				sbWhere.append(vo.getDt2());
				sbWhere.append(" 23:59:59'");
			}
			// 平台
			if (StringUtils.isNotBlank(vo.getOs()) && !"-1".equals(vo.getOs())) {
				sbWhere.append(" and os='");
				sbWhere.append(vo.getOs().toLowerCase());
				sbWhere.append("'");
			}
			// 1 JAVA 奔溃日志、2 C奔溃日志、3 Lua错误日志
			// if (StringUtils.isNotBlank(vo.getType())) {
			// sbWhere.append(" and flag=");
			// sbWhere.append(vo.getType());
			// }
			if (StringUtils.isNotBlank(vo.getGameMark())) {
				sbWhere.append(" and game_mark='");
				sbWhere.append(vo.getGameMark());
				sbWhere.append("'");
			}

			if (StringUtils.isNotBlank(vo.getType()) && !"-1".equals(vo.getType())) {
				sbWhere.append(" and app_ver='");
				sbWhere.append(vo.getType());
				sbWhere.append("'");
			}

		}
		if (StringUtils.isNotBlank(sbWhere.toString())) {
			// 删除 and 前4位
			sbWhere.delete(0, 5);
			sbWhere.insert(0, " where ");
		}
		// 转数组
		return sbWhere;
	}

	/**
	 * 启动数数据
	 */
	@Override
	public List<CharsetBeanCommon> getInitData(QueryCommBean vo) {
		// 如果是同一天
		if (vo.getDt1().equals(vo.getDt2())) {
			Map<String, Integer> mapNullData = CharsetDataUtil.getDayIntervalToCommonASC_OR_time_hour24(vo.getDt1(),
					vo.getDt2(), Integer.class);
			List<CharsetBeanCommon> lists = new ArrayList<>();
			QueryCommBean queryVo = (QueryCommBean) vo.clone2();
			for (Entry<String, Integer> entry : mapNullData.entrySet()) {
				// 取1小时之内的数据
				queryVo.setDt1(vo.getDt1() + " " + entry.getKey() + ":00:00");
				queryVo.setDt2(vo.getDt1() + " " + entry.getKey() + ":59:59");
				CharsetBeanCommon comm = new CharsetBeanCommon();
				comm.setxName(entry.getKey());
				comm.setType("line");
				comm.setId("启动");
				comm.setCount(singleDayInitData(queryVo));
				lists.add(comm);
			}
			return lists;
		}
		// ----------------------------华丽的分隔线--------------------------------------------------------
		Map<String, Integer> mapNullData = CharsetDataUtil.getDayIntervalToCommonASC(vo.getDt1(), vo.getDt2(),
				Integer.class);
		List<CharsetBeanCommon> lists = new ArrayList<>();
		QueryCommBean queryVo = (QueryCommBean) vo.clone2();
		for (Entry<String, Integer> entry : mapNullData.entrySet()) {
			queryVo.setDt1(entry.getKey());
			queryVo.setDt2(entry.getKey());
			CharsetBeanCommon comm = new CharsetBeanCommon();
			comm.setxName(entry.getKey());
			comm.setType("line");
			comm.setId("启动");
			comm.setCount(singleDayInitData(queryVo));
			lists.add(comm);
		}
		return lists;
	}

	/**
	 * 获取单天启动次数
	 * 
	 * @param vo
	 * @return
	 */
	public int singleDayInitData(QueryCommBean vo) {
		String table = SQLUtil.getTable1("log_client_init_v2", vo.getDt1());
		// 参数
		List<Object> paramArray = new LinkedList<Object>();
		// 条件组装
		StringBuffer sqlWhere = getAnySQLString(vo);// 完成
		StringBuffer sql = new StringBuffer(512);
		// 1小时取
		sql.append("SELECT count(*) FROM ");
		sql.append(table);
		sql.append(sqlWhere);
		log.info("sql:>>>\n{}\n param={}", sql.toString(), paramArray.toArray());
		int data = mydb.selectForRows(sql.toString());
		// if (!table.equals(table2)) {// 2张表查询
		// StringBuffer sql_2 = new StringBuffer(512);
		// sql_2.append("SELECT count(*) FROM ");
		// sql_2.append(table2);
		// sql_2.append(sqlWhere);
		// log.info("sql:>>>\n{}\n param={}", sql_2.toString(),
		// paramArray.toArray());
		// int data_2 = mydb.selectForRows(sql_2.toString());
		// data += data_2;
		// }

		return data;
	}

	/**
	 * 影响用户数,只是按设备标识去重
	 * 
	 * @param vo
	 * @return
	 */
	@Override
	public List<CharsetBeanCommon> getEffectData(QueryCommBean vo) {
		QueryCommBean query = (QueryCommBean) vo.clone2();
		// 相等安小时取
		// if (query.getDt1().equals(query.getDt2())) {
		// return getHourData(query);
		// }
		String table = "x8_crash_log";
		if (StringUtils.isNotBlank(vo.getGameMark())) {
			table = vo.getGameMark() + "_crash_log";
		}
		// 参数
		List<Object> paramArray = new LinkedList<Object>();
		// 条件组装
		StringBuffer sqlWhere = getSQLString(vo);// 完成
		StringBuffer sql = new StringBuffer(512);
		if (vo.getDt1().equals(vo.getDt2())) {
			sql.append("SELECT flag fla,to_char(dt,'HH') dt_x,count(DISTINCT device_mark) nums FROM  ");
		} else {
			sql.append("SELECT flag fla,to_char(dt,'YYYY-MM-DD') dt_x,count(DISTINCT device_mark) nums FROM  ");
		}
		sql.append(table);
		sql.append(sqlWhere);
		// 按天分组
		sql.append(" GROUP BY fla,dt_x order by fla,dt_x");
		log.info("sql:>>>\n{}\n param={}", sql.toString(), paramArray.toArray());
		List<CharsetBeanCommon> data = gdataDao.selectObjectList(sql.toString(), rowMapper);
		return data;
	}

	@Override
	public Map<String, String> getAppVersion(QueryCommBean vo) {
		String table = SQLUtil.getTable1("log_client_init_v2", vo.getDt1());
		String table2 = SQLUtil.getTable1("log_client_init_v2", vo.getDt2());
		// 参数
		List<Object> paramArray = new LinkedList<Object>();
		// 条件组装
		StringBuffer sqlWhere = getAppVersionSQLString(vo);// 完成
		StringBuffer sql = new StringBuffer(512);
		// 1小时取
		sql.append("select DISTINCT(app_ver) app_ver from ");
		sql.append(table);
		sql.append(sqlWhere);
		log.info("sql:>>>\n{}\n param={}", sql.toString(), paramArray.toArray());
		List<CharsetBeanCommon> list = mydb.selectObjectList(sql.toString(), rowMapperAppVersion);
		if (!table.equals(table2)) {// 2张表查询
			StringBuffer sql_2 = new StringBuffer(512);
			sql_2.append("SELECT count(*) FROM ");
			sql_2.append(table2);
			sql_2.append(sqlWhere);
			log.info("sql:>>>\n{}\n param={}", sql_2.toString(), paramArray.toArray());
			List<CharsetBeanCommon> list_2 = mydb.selectObjectList(sql_2.toString(), rowMapperAppVersion);
			list.addAll(list_2);
		}
		Map<String, String> map = new HashMap<String, String>();
		// 转map
		if (list != null && list.size() > 0) {
			for (CharsetBeanCommon c : list) {
				map.put(c.getxName(), c.getxName());
			}
		}
		return map;
	}

	private StringBuffer getAppVersionSQLString(QueryCommBean vo) {
		// 条件语句
		StringBuffer sbWhere = new StringBuffer();
		if (vo != null) {
			// 日期
			// if (StringUtils.isNotBlank(vo.getDt1())) {// 如果不为空，
			// sbWhere.append(" and dt>='");
			// sbWhere.append(vo.getDt1());
			// sbWhere.append("'");
			// }
			//
			// if (StringUtils.isNotBlank(vo.getDt2())) {// 如果不为空，
			// sbWhere.append(" and dt<='");
			// sbWhere.append(vo.getDt2());
			// sbWhere.append(" 23:59:59'");
			// }
			// 平台
			if (StringUtils.isNotBlank(vo.getOs()) && !"-1".equals(vo.getOs())) {
				sbWhere.append(" and os='");
				sbWhere.append(vo.getOs().toLowerCase());
				sbWhere.append("'");
			}

			if (StringUtils.isNotBlank(vo.getGameMark())) {
				sbWhere.append(" and game_mark='");
				sbWhere.append(vo.getGameMark());
				sbWhere.append("'");
			}

		}
		if (StringUtils.isNotBlank(sbWhere.toString())) {
			// 删除 and 前4位
			sbWhere.delete(0, 5);
			sbWhere.insert(0, " where ");
		}
		// 转数组
		return sbWhere;
	}

	// 借用
	private RowMapper<CharsetBeanCommon> rowMapperAppVersion = new RowMapper<CharsetBeanCommon>() {
		@Override
		public CharsetBeanCommon mapRow(ResultSet rs, int rowNum) throws SQLException {
			CharsetBeanCommon bean = new CharsetBeanCommon();
			bean.setxName(rs.getString("app_ver"));
			return bean;
		}
	};

	/**
	 * 分页请求日志列表
	 * 
	 * @param paginator
	 * @param vo
	 * @return
	 */
	@Override
	public PaginatorResult getPaginatorList(Paginator paginator, QueryCommBean vo) {
		PaginatorResult result = new PaginatorResult();
		String table = "x8_crash_log";
		if (StringUtils.isNotBlank(vo.getGameMark())) {
			table = vo.getGameMark() + "_crash_log";
		}
		// 获取总记录数
		result.setTotal(getLogTotal(vo));
		// 参数
		List<Object> paramArray = new LinkedList<Object>();
		// 条件组装
		StringBuffer sqlWhere = getSQLString(vo);// 完成
		StringBuffer sql = new StringBuffer(512);
//		sql.append("SELECT id,flag fla,dt,log FROM  ");
		sql.append("select  max(id) id,log_md5,count(*) num,max(dt) dt,max(flag) fla,max(log) log  FROM ");
		sql.append(table);
		sql.append(sqlWhere);
		//分组
		sql.append("GROUP BY log_md5");

		// 排序
		if (StringUtils.isNotBlank(paginator.getSort())) {
			if ("logType".equals(paginator.getSort())) {
				sql.append(" order by ");
				sql.append(" flag ");
				sql.append(paginator.getOrder());
			}
			if ("time".equals(paginator.getSort())) {
				sql.append(" order by ");
				sql.append(" dt ");
				sql.append(paginator.getOrder());
			}
		} else {
			sql.append(" order by dt desc");
		}
		// 分页
		sql.append(" LIMIT ");
		sql.append(paginator.getLimit());
		sql.append(" offset ");
		sql.append(paginator.getOffset());
		log.info("sql:>>>\n{}\n param={}", sql.toString(), paramArray.toArray());
		List<CrashLog> data = gdataDao.selectObjectList(sql.toString(), rowMapper_log);
		// 处理长文本的
		if (data != null && data.size() > 0) {
			for (CrashLog log : data) {
				if (StringUtils.isNotBlank(log.getLog()) && log.getLog().length() > 200) {
					String log_text = log.getLog().substring(0, 200);
					String href = "  <a href='business/device/device_crash/log_detail".intern();
					href += "?id=";
					href += log.getId();
					href += "' target='blank' style='color: blue; cursor: pointer;'>详情</a>".intern();
					log.setLog(log_text + href);
				}

				if (StringUtils.isNotBlank(log.getLogType())) {
					if ("1".equals(log.getLogType())) {
						log.setLogType("JAVA崩溃");
					} else if ("2".equals(log.getLogType())) {
						log.setLogType("C崩溃");
					} else if ("3".equals(log.getLogType())) {
						log.setLogType("Lua错误");
					}
				}
			}
		}
		result.setRows(data);
		return result;
	}

	/**
	 * 获取总记录数
	 * 
	 * @param vo
	 * @return
	 */
	public int getLogTotal(QueryCommBean vo) {
		String table = "x8_crash_log";
		if (StringUtils.isNotBlank(vo.getGameMark())) {
			table = vo.getGameMark() + "_crash_log";
		}
		// 参数
		List<Object> paramArray = new LinkedList<Object>();
		// 条件组装
		StringBuffer sqlWhere = getSQLString(vo);// 完成
		StringBuffer sql = new StringBuffer(512);
//		sql.append("SELECT count(*) FROM  ");
		sql.append("select   count(DISTINCT log_md5)  FROM ");
		sql.append(table);
		sql.append(sqlWhere);
		log.info("sql:>>>\n{}\n param={}", sql.toString(), paramArray.toArray());
		int total = gdataDao.selectForRows(sql.toString());
		return total;
	}

	private RowMapper<CrashLog> rowMapper_log = new RowMapper<CrashLog>() {
		@Override
		public CrashLog mapRow(ResultSet rs, int rowNum) throws SQLException {
			CrashLog bean = new CrashLog();
			bean.setId(rs.getLong(("id")));
			bean.setTime(rs.getString("dt"));
			bean.setLogType(rs.getString("fla"));
			bean.setLog(rs.getString("log"));
			bean.setNum(rs.getInt("num"));
			return bean;
		}
	};

	@Override
	public CrashLog getLogDetail(Long id, String gameMark) {
		String table = "x8_crash_log";
		if (StringUtils.isNotBlank(gameMark)) {
			table = gameMark + "_crash_log";
		}
		StringBuffer sql = new StringBuffer(512);
		sql.append("SELECT id,flag fla,dt,log FROM  ");
		sql.append(table);
		sql.append(" where id=");
		sql.append(id);
		log.info("sql:>>>\n{}", sql.toString());
		CrashLog data = gdataDao.selectObject(sql.toString(), rowMapper_log);
		return data;
	}
}
