package com.huiwan.gdata.modules.gdata.util;

import java.util.Map;

/**
 * <p>
 * Description:自动生成SQL
 * </p>
 * 
 * @Author Chenkangming
 * @Date 2015-1-7
 * @version 1.0
 */
public class SQLUtil {
	/**
	 * 自动生成insert SQL语句
	 * 
	 * INSERT INTO
	 * patner_log(`action`,`create_time`,`role_id`,`server_id`,`lv`,`
	 * accname`,`role_desc`,`a1`,`a2`,`a3`,`a4`,`a5`) VALUES
	 * (?,?,?,?,?,?,?,?,?,?,?,?)
	 * 
	 * 
	 * @param table
	 *            写入的表
	 * @param key
	 *            字段名
	 * @return INSERT INTO table(key) VALUES (?)
	 */
	public static String insert(String table, String key) {
		StringBuffer sb = new StringBuffer("INSERT INTO ");
		sb.append(table);
		sb.append("(");
		String[] k = key.split(",");
		for (int i = 0; i < k.length; i++) {
			sb.append("`").append(k[i]).append("`");
			if (i != k.length - 1) {
				sb.append(",");
			}
		}
		sb.append(")").append(" VALUES ");
		sb.append("(");
		for (int i = 0; i < k.length; i++) {
			sb.append("?");
			if (i != k.length - 1) {
				sb.append(",");
			}
		}
		sb.append(")");
		return sb.toString();
	}

	/**
	 * 根据日期自动切换日志表
	 * 
	 * @param table
	 *            表名
	 * @param dt
	 *            yyyy-MM-dd
	 * @return table name
	 */
	public static String getTable(String table, String dt) {
		table = "`" + table + "_";
		String year = TimeUtil.getTime(dt, 1);
		String _01dt = year + "-01";
		String _02dt = year + "-02";
		String _03dt = year + "-03";
		String _04dt = year + "-04";
		String _05dt = year + "-05";
		String _06dt = year + "-06";
		String _07dt = year + "-07";
		String _08dt = year + "-08";
		String _09dt = year + "-09";
		String _10dt = year + "-10";
		String _11dt = year + "-11";
		String _12dt = year + "-12";
		String table1 = table + _12dt + "`";
		if (TimeUtil.compare2Time1(TimeUtil.getTime(dt, 2), _01dt, 4)) {
			table1 = table + _01dt + "`";
		} else if (TimeUtil.compare2Time1(TimeUtil.getTime(dt, 2), _02dt, 4)) {
			table1 = table + _02dt + "`";
		} else if (TimeUtil.compare2Time1(TimeUtil.getTime(dt, 2), _03dt, 4)) {
			table1 = table + _03dt + "`";
		} else if (TimeUtil.compare2Time1(TimeUtil.getTime(dt, 2), _04dt, 4)) {
			table1 = table + _04dt + "`";
		} else if (TimeUtil.compare2Time1(TimeUtil.getTime(dt, 2), _05dt, 4)) {
			table1 = table + _05dt + "`";
		} else if (TimeUtil.compare2Time1(TimeUtil.getTime(dt, 2), _06dt, 4)) {
			table1 = table + _06dt + "`";
		} else if (TimeUtil.compare2Time1(TimeUtil.getTime(dt, 2), _07dt, 4)) {
			table1 = table + _07dt + "`";
		} else if (TimeUtil.compare2Time1(TimeUtil.getTime(dt, 2), _08dt, 4)) {
			table1 = table + _08dt + "`";
		} else if (TimeUtil.compare2Time1(TimeUtil.getTime(dt, 2), _09dt, 4)) {
			table1 = table + _09dt + "`";
		} else if (TimeUtil.compare2Time1(TimeUtil.getTime(dt, 2), _10dt, 4)) {
			table1 = table + _10dt + "`";
		} else if (TimeUtil.compare2Time1(TimeUtil.getTime(dt, 2), _11dt, 4)) {
			table1 = table + _11dt + "`";
		} else if (TimeUtil.compare2Time1(TimeUtil.getTime(dt, 2), _12dt, 4)) {
			table1 = table + _12dt + "`";
		}
		return table1;
	}

	/**
	 * 根据日期自动切换日志表,一个季度换一次
	 * 
	 * @param table
	 *            表名
	 * @param dt
	 *            yyyy-MM-dd
	 * @return table name
	 */
	public static String getTable1(String table, String dt) {
		if (StringUtil.isEmpty(dt)) {
			dt = TimeUtil.getNow(2);
		}
		dt = TimeUtil.dateFormat(dt, 2);
		table = "`" + table + "_";
		String year = TimeUtil.getTime(dt, 1);
		String _03dt = year + "-03";
		String _06dt = year + "-06";
		String _09dt = year + "-09";
		String _12dt = year + "-12";
		String table1 = table + _12dt + "`";
		if (TimeUtil.compare2Time1(TimeUtil.getTime(dt, 2), _03dt, 4)) {
			table1 = table + _03dt + "`";
		} else if (TimeUtil.compare2Time1(TimeUtil.getTime(dt, 2), _06dt, 4)) {
			table1 = table + _06dt + "`";
		} else if (TimeUtil.compare2Time1(TimeUtil.getTime(dt, 2), _09dt, 4)) {
			table1 = table + _09dt + "`";
		}
		return table1;
	}

	/**
	 * MAP查询参数转where语句
	 * 
	 * @param pmap
	 *            查询参数map
	 * @return where语句
	 */
	public static String toWhereFromLogDB(Map<String, Object> map) {
		StringBuffer sb = new StringBuffer();
		for (Map.Entry<String, Object> entry : map.entrySet()) {
			String name = entry.getKey();
			Object value = entry.getValue();
			if (value != null) {
				if (value instanceof Integer) {
					if ((Integer) value != 0) {
						sb.append("`").append(name).append("`").append("=").append(value).append(" AND ");
					}
				} else if (value instanceof Long) {
					if ((Long) value != 0) {
						sb.append("`").append(name).append("`").append("=").append(value).append(" AND ");
					}
				} else if (value instanceof Double) {
					if ((Double) value != 0) {
						sb.append("`").append(name).append("`").append("=").append(value).append(" AND ");
					}
				} else {
					sb.append(name).append("='").append(value).append("' AND ");
				}
			}
		}
		if (sb.length() > 0) {
			return sb.append(" WHERE ").toString().substring(0, sb.length() - 4);
		} else {
			return "";
		}
	}

	public static void main(String[] args) {
		System.out.println(insert("patner_log", "action,create_time,role_id,server_id,lv,accname,role_desc,a1,a2,a3,a4,a5"));
		System.out.println(getTable("aa", TimeUtil.getNow(2)));
	}
}
