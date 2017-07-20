package com.huiwan.gdata.modules.gdata.combat.service.impl;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import org.apache.commons.lang.StringUtils;
import org.eclipse.jetty.util.ajax.JSON;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.huiwan.gdata.common.cache.CacheUtils;
import com.huiwan.gdata.common.utils.pagination.Paginator;
import com.huiwan.gdata.common.utils.pagination.PaginatorResult;
import com.huiwan.gdata.modules.gdata.base.GDataDao;
import com.huiwan.gdata.modules.gdata.base.charset.bean.QueryCommBean;
import com.huiwan.gdata.modules.gdata.combat.entity.CombatAttr;
import com.huiwan.gdata.modules.gdata.combat.entity.CombatLog;
import com.huiwan.gdata.modules.gdata.combat.service.CombatLogService;
import com.huiwan.gdata.modules.sys.entity.Dict;
import com.huiwan.gdata.modules.sys.service.IDictService;
import com.huiwan.gdata.modules.sys.service.IGameDictService;

@Service
public class CombatLogServiceImpl implements CombatLogService {
	Logger log = LoggerFactory.getLogger(getClass());

	@Autowired
	private GDataDao gdataDao;
	@Autowired
	private IDictService dictService;
	@Autowired
	private IGameDictService gameDictService;

	/**
	 * 通过时间获取分区表
	 * 
	 * @param dt
	 * @return
	 */
	public static String getTable(String dt) {
		String table_suffix = "2017_07";
		DateFormat df = new SimpleDateFormat("yyyy_MM");
		if (StringUtils.isBlank(dt)) {
			table_suffix = df.format(new Date());
		} else {
			DateFormat df_ssrc = new SimpleDateFormat("yyyy-MM-dd");
			try {
				table_suffix = df.format(df_ssrc.parse(dt));
			} catch (ParseException e) {
				table_suffix = df.format(new Date());
				e.printStackTrace();
			}
		}
		String t = " zl_log_info" + table_suffix + " ";
		return t;
	}

	public static void main(String[] args) {
		System.out.println(getTable("2018-08-10 12:50:30"));
	}

	@Override
	public PaginatorResult getPaginatorList(Paginator paginator, QueryCommBean bean) {
		PaginatorResult result = new PaginatorResult();
		// 获取总记录数
		result.setTotal(getLogTotal(bean));
		// 参数
		List<Object> paramArray = new LinkedList<Object>();
		// 条件组装
		StringBuffer sqlWhere = getSQLString(bean);// 完成
		StringBuffer sql = new StringBuffer(512);
		// sql.append(
		// "SELECT to_char(time,'YYYY-MM-DD HH24:MI:SS') dt,cont->>'add_hp'
		// add_hp,cont->>'dungeon_id' dungeon_id ");

		sql.append("SELECT id,server_id,file,to_char(time,'YYYY-MM-DD HH24:MI:SS') dt,cont->>'uuid' uuid,cont cont ");

		sql.append(" FROM zl_log_info ");
		sql.append(sqlWhere);
		// 用id做查询条件
		// if(StringUtils.isNotBlank(paginator.getPageId())){
		// sql.append(" and id< ");
		// sql.append(Long.parseLong(paginator.getPageId()));
		// }
		// 排序
		if (StringUtils.isNotBlank(paginator.getSort())) {
			if ("logType".equals(paginator.getSort())) {
				sql.append(" order by ");
				sql.append(" flag ");
				sql.append(paginator.getOrder());
			}
			if ("dt".equals(paginator.getSort())) {
				sql.append(" order by ");
				sql.append(" id ");
				sql.append(paginator.getOrder());
			}
		} else {
			sql.append(" order by time_log desc ,id desc");
		}
		// 分页
		// if(StringUtils.isBlank(paginator.getPageId())){
		sql.append(" offset ");
		sql.append(paginator.getOffset());
		// }

		sql.append(" LIMIT ");
		sql.append(paginator.getLimit());

		log.info("sql:>>>\n{}\n param={}", sql.toString(), paramArray.toArray());
		List<CombatLog> data = gdataDao.selectObjectList(sql.toString(), rowMapper);

		Map<String, String> dicts = dictService.getByTypeMaps("_file_types");
		Map<String, String> servers_type = dictService.getByTypeMaps("servers_type");

		// 技能列表
		Map<String, String> zl_skill_type = gameDictService.getByTypeMaps("zl_skill_type");
		// 监听列表
		Map<String, Dict> zl_event_type = gameDictService.getByTypeMapsDicts("zl_event_type");
		// 效果概率
		Map<String, String> zl_effect_type = gameDictService.getByTypeMaps("zl_effect_type");
		// BUFF
		Map<String, Dict> zl_buff_types = gameDictService.getByTypeMapsDicts("zl_buff_types");

		// 属性
		Map<String, Dict> zl_attrs_types = gameDictService.getByTypeMapsDicts("zl_attrs_types");

		// 被动技能
		Map<String, Dict> zl_passive_skill_type = gameDictService.getByTypeMapsDicts("zl_passive_skill_type");
		// 独特属性
		Map<String, Dict> zl_exotics_type = gameDictService.getByTypeMapsDicts("zl_exotics_type");

		// 去掉空的。主要是属性对比
		// List<CombatLog> data_new = new LinkedList<CombatLog>();
		// System.out.println(JSONObject.toJSONString(data));
		// 转换中文
		if (data != null && data.size() > 0) {
			for (CombatLog log : data) {
				// 有些需要做判单
				// log.setFileSrc(log.getFile());
				// 转换文件名
				if (dicts.containsKey(log.getFile())) {
					log.setFileName(dicts.get(log.getFile()));
				} else {
					log.setFileName(log.getFile());
				}
				// 转换服务器
				if (servers_type.containsKey(String.valueOf(log.getServerId()))) {
					log.setServer(servers_type.get(String.valueOf(log.getServerId())));
				} else {
					log.setServer(String.valueOf(log.getServerId()));
				}
				// 技能名转换
				if (StringUtils.isBlank(log.getCont())) {
					continue;
				}
				if ("skill".equals(log.getFile())) {
					JSONObject jsonCont = (JSONObject) JSONObject.parse(log.getCont());
					if (!jsonCont.containsKey("skill_id") || StringUtils.isBlank(jsonCont.getString("skill_id"))) {
						continue;
					}
					String skill_id = jsonCont.getString("skill_id");
					String skill_name = zl_skill_type.get(skill_id);
					jsonCont.put("skill_name", skill_name);
					log.setCont(jsonCont.toJSONString());
				}
				// 监听转换
				if ("opport".equals(log.getFile())) {
					JSONObject jsonCont = (JSONObject) JSONObject.parse(log.getCont());
					if (!jsonCont.containsKey("opports") || StringUtils.isBlank(jsonCont.getString("opports"))) {
						continue;
					}
					String key = jsonCont.getString("opports");// 是一个数组，需转换
					String arrs_str = key.replace("[", "").replace("]", "");
					String[] arrs = arrs_str.split(",");
					if (arrs != null && arrs.length > 0) {
						List<Dict> d_temp = new ArrayList<>();
						for (String arr_key : arrs) {
							if (zl_event_type.containsKey(arr_key)) {
								Dict dict = zl_event_type.get(arr_key);
								d_temp.add(dict);
							} else {// 没有找到，则显示为id
								Dict dict = new Dict();
								dict.setValue(arr_key);
								dict.setName(arr_key);
								dict.setRemake(arr_key);
							}
						}
						jsonCont.put("opports_josn", d_temp);
					}
					log.setCont(jsonCont.toJSONString());
				}
				// 效果转换
				if ("effect".equals(log.getFile())) {
					JSONObject jsonCont = (JSONObject) JSONObject.parse(log.getCont());
					if (!jsonCont.containsKey("effect_id") || StringUtils.isBlank(jsonCont.getString("effect_id"))) {
						continue;
					}
					String key = jsonCont.getString("effect_id");
					if (zl_effect_type.containsKey(key)) {
						String skill_name = zl_effect_type.get(key);
						jsonCont.put("effect_Prob", skill_name);
					} else {
						jsonCont.put("effect_Prob", "末能翻译：" + key);
					}
					log.setCont(jsonCont.toJSONString());
				}
				// BUFF处理效果 把file转换 add_buf 添加Buf del_buf 删除Buf buf_expire
				// Buf过期删除
				if ("add_buf".equals(log.getFile()) || "del_buf".equals(log.getFile())
						|| "buf_expire".equals(log.getFile())) {
					log.setFileName("BUFF处理效果");
					// log.setFile("buff_all");
					// 查询buffstatus
					JSONObject jsonCont = (JSONObject) JSONObject.parse(log.getCont());
					// if(!jsonCont.containsKey("add_bufs")||StringUtils.isBlank(jsonCont.getString("add_bufs"))){
					// continue;
					// }
					String key = "";

					if (jsonCont.containsKey("add_bufs")) {
						key = jsonCont.getString("add_bufs");// 是一个数组，需转换
					}
					if (jsonCont.containsKey("del_buff_ids")) {
						key = jsonCont.getString("del_buff_ids");// 是一个数组，需转换
					}
					if (jsonCont.containsKey("del_buff_ids")) {
						key = jsonCont.getString("del_buff_ids");// 是一个数组，需转换
					}
					String arrs_str = key.replace("[", "").replace("]", "");
					String[] arrs = arrs_str.split(",");
					if (arrs != null && arrs.length > 0) {
						List<Dict> d_temp = new ArrayList<>();
						for (String arr_key : arrs) {
							if (zl_buff_types.containsKey(arr_key)) {
								Dict dict = zl_buff_types.get(arr_key);
								d_temp.add(dict);
							} else {// 没有找到，则显示为id
								Dict dict = new Dict();
								dict.setValue(arr_key);
								dict.setName(arr_key);
								dict.setRemake(arr_key);
							}
						}
						jsonCont.put("buff_all_json", d_temp);
					}
					log.setCont(jsonCont.toJSONString());
				}
				// 属性变更 最当前的一条和，最近的一条比较
				// if ("attrs".equals(log.getFile())) {
				// JSONObject jsonCont = (JSONObject)
				// JSONObject.parse(log.getCont());
				// if (!jsonCont.containsKey("uuid") ||
				// StringUtils.isBlank(jsonCont.getString("uuid"))) {
				// log.setCont("");
				// continue;
				// }
				// try {
				// log = diffAttrs(log, zl_attrs_types);
				// } catch (ParseException e) {
				// e.printStackTrace();
				// continue;
				// }
				// // log.setCont(jsonCont.toJSONString());
				// }

				// 属性变化
				if ("attrs_diff".equals(log.getFile())) {
					JSONObject jsonCont_new = new JSONObject();
					JSONObject jsonCont = (JSONObject) JSONObject.parse(log.getCont());
					if (!jsonCont.containsKey("uuid") || StringUtils.isBlank(jsonCont.getString("uuid"))) {
						continue;
					}

					for (Entry<String, Dict> entry : zl_attrs_types.entrySet()) {
						if (jsonCont.containsKey(entry.getKey())) {
							jsonCont_new.put(entry.getValue().getName(), jsonCont.getString(entry.getKey()));
						} else {
							jsonCont_new.put(entry.getKey(), jsonCont.getString(entry.getKey()));
						}

						jsonCont.put("attrs_dif_v2", jsonCont_new.toJSONString());
					}

					log.setCont(jsonCont.toJSONString());
				}

				// 添加被动技能
				if ("add_pass".equals(log.getFile())) {
					JSONObject add_pass_passive_skill_type = new JSONObject();
					JSONObject jsonCont = (JSONObject) JSONObject.parse(log.getCont());
					if (!jsonCont.containsKey("uuid") || StringUtils.isBlank(jsonCont.getString("uuid"))) {
						continue;
					}
					if (!jsonCont.containsKey("pass_ids") || StringUtils.isBlank(jsonCont.getString("pass_ids"))) {
						continue;
					}
					String pass_id = jsonCont.getString("pass_ids");
					if (zl_passive_skill_type.containsKey(pass_id)) {
						Dict pass_skill_dict = zl_passive_skill_type.get(pass_id);
						// 第一层找 被动技能
						jsonCont.put("pass_skill_name", pass_skill_dict.getName());
						// 再通过参数去找
						String exotics_ids = "";
						// 是否有等级区间
						String lv_src = pass_skill_dict.getArg1();
						if (StringUtils.isNotBlank(lv_src)) {
							jsonCont.put("lveare", lv_src);
							// 独特属性bids数组折分
							// 按等级取{1,10}= ids
							String[] lv_arr = lv_src.replace("{", "").replace("}", "").trim().split(",");
							Integer lv = jsonCont.getInteger("lv");
							// 如果在这个等级范围里
							if (lv >= Integer.parseInt(lv_arr[0]) && lv <= Integer.parseInt(lv_arr[1])) {
								exotics_ids = pass_skill_dict.getArg2();
							}
						} else {// 没有区间直接取
							exotics_ids = pass_skill_dict.getArg2();
						}
						// 没有id
						if (StringUtils.isBlank(exotics_ids)) {
							continue;
						}
						// 拆分数组，并取相应值
						String[] exotics_ids_arr = exotics_ids.replace("[", "").replace("]", "").trim().split(",");
						JSONArray exotics_array = new JSONArray();
						for (String e_id : exotics_ids_arr) {
							if (StringUtils.isBlank(e_id)) {
								continue;
							}
							if (!zl_exotics_type.containsKey(e_id)) {
								add_pass_passive_skill_type.put("pass_list", "翻译未能找到-" + e_id);
								continue;
							}
							Dict exotics_Dict = zl_exotics_type.get(e_id);
							JSONObject exotics_json = new JSONObject();
							// 独特属性
							exotics_json.put("id", exotics_Dict.getValue());
							exotics_json.put("type", exotics_Dict.getName());
							exotics_json.put("arg2", exotics_Dict.getArg2());
							exotics_json.put("arg3", exotics_Dict.getArg3());
							// 接着翻译
							if (StringUtils.isBlank(exotics_Dict.getArg1())) {
								exotics_json.put("attr", "空");
								exotics_array.add(exotics_json);
								continue;
							}
							if ("EXOTIC_RAND_ATTR".equals(exotics_Dict.getName())) {// 他才有属性
								String attrs_key = "A_" + exotics_Dict.getArg1();
								if (zl_attrs_types.containsKey(attrs_key)) {
									exotics_json.put("attr", zl_attrs_types.get(attrs_key).getName());
								} else {
									exotics_json.put("属性:配置表末能翻译-", attrs_key);
								}
							}
							exotics_array.add(exotics_json);
						}
						add_pass_passive_skill_type.put("pass_list", exotics_array);
					} else {
						add_pass_passive_skill_type.put("pass_list", pass_id + ":表中未能找到");
					}
					// 加入一层
					jsonCont.put("add_pass_passive_skill_type", add_pass_passive_skill_type.toJSONString());
					log.setCont(jsonCont.toJSONString());
				}
				// 独特属性转换
				if ("exotics_ids".equals(log.getFile())) {
					JSONObject jsonCont = (JSONObject) JSONObject.parse(log.getCont());
					if (!jsonCont.containsKey("exotics_ids")) {
						continue;
					}
					String exotics_ids = jsonCont.get("exotics_ids").toString();
					if (StringUtils.isBlank(exotics_ids)) {
						continue;
					}
					String[] ids = exotics_ids.replace("[", "").replace("]", "").split(",");
					JSONArray exotics_array = new JSONArray();

					for (String id : ids) {
						JSONObject jsonCont_new = new JSONObject();
						if (!zl_exotics_type.containsKey(id)) {
							jsonCont_new.put("msg", "配置表末能找到对应的id_" + id);
							exotics_array.add(jsonCont_new);
							continue;
						}
						Dict exotics_Dict = zl_exotics_type.get(id);
						// 独特属性
						jsonCont_new.put("id", exotics_Dict.getValue());
						jsonCont_new.put("type", exotics_Dict.getName());
						jsonCont_new.put("arg2", exotics_Dict.getArg2());
						jsonCont_new.put("arg3", exotics_Dict.getArg3());
						// 接着翻译
						if (StringUtils.isBlank(exotics_Dict.getArg1())) {
							jsonCont_new.put("attr", "空");
							exotics_array.add(jsonCont_new);
							continue;
						}
						if ("EXOTIC_RAND_ATTR".equals(exotics_Dict.getName())) {// 他才有属性
							String attrs_key = "A_" + exotics_Dict.getArg1();
							if (zl_attrs_types.containsKey(attrs_key)) {
								jsonCont_new.put("attr", zl_attrs_types.get(attrs_key).getName());
							} else {
								jsonCont_new.put("属性:配置表末能翻译-", attrs_key);
							}
						}

						exotics_array.add(jsonCont_new);
					}
					
					JSONObject add_pass_passive_skill_type = new JSONObject();
					add_pass_passive_skill_type.put("pass_list", exotics_array);
					jsonCont.put("add_pass_passive_skill_type", add_pass_passive_skill_type.toJSONString());
					log.setCont(jsonCont.toJSONString());
				}
			}

			// for (CombatLog log : data) {
			// if(StringUtils.isNotBlank(log.getCont())){
			// data_new.add(log);
			// }
			// }
		}

		if (data != null && data.size() > 0) {
			CombatLog logPage = data.get(data.size() - 1);
			logPage.setPageId(logPage.getId());
		}

		result.setRows(data);
		return result;
	}

	/**
	 * 比较属性变化，并显示
	 * 
	 * @param src
	 * @return
	 * @throws ParseException
	 */
	public CombatLog diffAttrs(CombatLog log_src, Map<String, Dict> zl_attrs_types) throws ParseException {

		if (zl_attrs_types == null || zl_attrs_types.size() <= 0) {
			log_src.setCont("{\"msg\":\"字典为空\"}");
			return log_src;
		}

		String uuid = log_src.getUuid();
		String dt = log_src.getDt();

		String diff_dt = "";
		if (StringUtils.isNotBlank(dt)) {
			DateFormat df = (DateFormat) new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			long d_long = df.parse(dt).getTime() - 120000;// 二分钟类的日志
			diff_dt = df.format(d_long);
		}
		if (StringUtils.isBlank(diff_dt)) {
			log_src.setCont("");
			return log_src;
		}
		StringBuffer sql = new StringBuffer();
		sql.append(
				"SELECT id,server_id,file,to_char(time,'YYYY-MM-DD HH24:MI:SS') dt,cont->>'uuid' uuid,cont cont FROM zl_log_info_attrs where file='attrs' ");
		sql.append(" and cont->>'uuid'='");
		sql.append(uuid);
		sql.append("'");
		// 取二分钟内的
		sql.append(" and time<'");
		sql.append(dt);
		sql.append("'");

		sql.append(" and time>'");
		sql.append(diff_dt);
		sql.append("'");
		sql.append(" order by id desc limit 1");
		log.info("sql:>>>\n{}\n", sql.toString());

		CombatLog datax = gdataDao.selectObject(sql.toString(), rowMapper2);
		if (datax == null) {
			log_src.setCont("{\"msg\":\"末能找到下一条做比较\"}");
			return log_src;

		}

		JSONObject jsonCont_dif = (JSONObject) JSONObject.parse(datax.getCont());
		JSONObject jsonCont_src = (JSONObject) JSONObject.parse(log_src.getCont());
		// 比较
		JSONObject cont = new JSONObject();

		for (Entry<String, Dict> entry : zl_attrs_types.entrySet()) {
			String dif = jsonCont_dif.getString(entry.getKey());
			String src = jsonCont_src.getString(entry.getKey());

			if (StringUtils.isBlank(src)) {
				continue;
			}
			if (StringUtils.isBlank(dif)) {
				cont.put(entry.getValue().getName(), "当前值：" + src + "｜变更前值：空");
			} else if (!dif.equals(src)) {
				cont.put(entry.getValue().getName(), "当前值：" + src + "｜变更前值：" + dif);
			}
		}

		if (StringUtils.isBlank(cont.toJSONString()) || "{}".equals(cont.toJSONString())) {
			// conts="{\"msg\":\"无变化\"}";
			cont.put("msg", "无变化");
		}
		// 必要信息
		cont.put("file", log_src.getFile());
		JSONObject contJSON = (JSONObject) JSONObject.parse(log_src.getCont());
		cont.put("name", contJSON.getString("name"));

		log_src.setCont(cont.toJSONString());
		return log_src;
	}

	private RowMapper<CombatLog> rowMapper = new RowMapper<CombatLog>() {
		@Override
		public CombatLog mapRow(ResultSet rs, int rowNum) throws SQLException {
			CombatLog bean = new CombatLog();
			bean.setId(rs.getLong("id"));
			bean.setServerId(rs.getInt("server_id"));
			bean.setDt(rs.getString("dt"));
			bean.setUuid(rs.getString("uuid"));
			bean.setFile(rs.getString("file"));
			bean.setCont(rs.getString("cont"));
			return bean;
		}
	};

	private RowMapper<CombatLog> rowMapper2 = new RowMapper<CombatLog>() {
		@Override
		public CombatLog mapRow(ResultSet rs, int rowNum) throws SQLException {
			CombatLog bean = new CombatLog();
			bean.setId(rs.getLong("id"));
			bean.setServerId(rs.getInt("server_id"));
			bean.setDt(rs.getString("dt"));
			bean.setUuid(rs.getString("uuid"));
			bean.setFile(rs.getString("file"));
			bean.setCont(rs.getString("cont"));
			return bean;
		}
	};

	/**
	 * 获取总记录数
	 * 
	 * @param vo
	 * @return
	 */
	public int getLogTotal(QueryCommBean vo) {
		// 参数
		List<Object> paramArray = new LinkedList<Object>();
		// 条件组装
		StringBuffer sqlWhere = getSQLString(vo);// 完成
		StringBuffer sql = new StringBuffer(512);
		sql.append("select  count(*) ");
		sql.append(" FROM zl_log_info ");
		sql.append(sqlWhere);

		Object totCache = CacheUtils.get("selectCount", sql.toString());
		if (totCache != null && StringUtils.isNotBlank(totCache.toString())) {
			int tot = Integer.parseInt(totCache.toString());
			return tot;
		}
		log.info("sql:>>>\n{}\n param={}", sql.toString(), paramArray.toArray());
		int total = gdataDao.selectForRows(sql.toString());
		// 做缓存
		CacheUtils.put("selectCount", sql.toString(), total);
		return total;
	}

	private StringBuffer getSQLString(QueryCommBean vo) {
		// 条件语句
		StringBuffer sbWhere = new StringBuffer();

		if (vo != null) {
			if (StringUtils.isNotBlank(vo.getFile())) {
				sbWhere.append(" and file='");
				sbWhere.append(vo.getFile());
				sbWhere.append("'");
			}
			// not exists (select 1 from table2 tbl2 where tbl1.id = tbl2.id);
			// sbWhere.append(" and file not
			// in('attrs','target_temp_eff','attack_temp_eff','temp_targetor','temp_attacker')
			// ");
			// sbWhere.append(" and not exists(select 1 from zl_log_info where
			// file='attrs' and file='target_temp_eff' and
			// file='attack_temp_eff' and file='temp_targetor' and
			// file='temp_attacker') ");
			// 服务器
			if (vo.getServer() != null && vo.getServer() > 0&&!"-1".equals(vo.getServer())) {
				sbWhere.append(" and server_id=");
				sbWhere.append(vo.getServer());
			}
			// 日期
			if (StringUtils.isBlank(vo.getDt1())) {
				DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
				vo.setDt1(df.format(new Date()));
			}
			if (StringUtils.isNotBlank(vo.getDt1())) {// 如果不为空，
				sbWhere.append(" and time>='");
				sbWhere.append(vo.getDt1());
				sbWhere.append("'");

				sbWhere.append(" and time<='");
				sbWhere.append(vo.getDt1());
				sbWhere.append(" 23:59:59'");
			}

			// if (StringUtils.isNotBlank(vo.getDt2())) {// 如果不为空，
			// sbWhere.append(" and time<='");
			// sbWhere.append(vo.getDt2());
			// // sbWhere.append(" 23:59:59'");
			// sbWhere.append("'");
			// }

			// 副本
			if (StringUtils.isNotBlank(vo.getCopyId())) {// 如果不为空，
				sbWhere.append(" and cont->>'copyId'='");
				sbWhere.append(vo.getCopyId());
				sbWhere.append("'");
			}

			// uuid
			if (StringUtils.isNotBlank(vo.getType())) {// 如果不为空，
				sbWhere.append(" and uuid=");
				sbWhere.append(vo.getType());
			}

			if (StringUtils.isNotBlank(vo.getName())) {// 如果不为空，
				sbWhere.append(" and cont->>'name'='");
				sbWhere.append(vo.getName());
				sbWhere.append("'");
			}
			//
			// if (StringUtils.isNotBlank(vo.getCommQueryString())) {// 见听对象
			// sbWhere.append(" and cont->>copyId='");
			// sbWhere.append(vo.getCommQueryString());
			// sbWhere.append("'");
			// }

		}
		if (StringUtils.isNotBlank(sbWhere.toString())) {
			// 删除 and 前4位
			sbWhere.delete(0, 5);
			sbWhere.insert(0, " where ");
		}
		// 转数组
		return sbWhere;
	}

	@Override
	public CombatLog getDetail(Integer id) {
		String sql = "select  id,server_id,file,to_char(time,'YYYY-MM-DD HH24:MI:SS') dt,cont->>'uuid' uuid,cont cont from zl_log_info where id="
				+ id;
		log.info("sql:>>>\n{}", sql.toString());
		CombatLog data = gdataDao.selectObject(sql.toString(), rowMapper);
		return data;
	}

	@Override
	public List<Dict> getObjTypes(int type, String severId) {
		if (type == 1) {
			// String sql="SELECT DISTINCT(cont->>'uuid') uuid,time FROM
			// zl_log_info order by time desc LIMIT 10";
			// String sql = "select cont->>'uuid' uuid,cont->>'name'
			// namea,cont->>'actor_type' actory from zl_log_info ";

			String sql = "	select max(id) maxid,uuid,namea,actory from ( ";
			sql += " select id,cont ->> 'uuid' uuid, cont ->> 'name' namea,	cont ->> 'actor_type' actory  from zl_log_info ";
			

			if (StringUtils.isNotBlank(severId)&&!"-1".equals(severId)) {
				sql += " where server_id='" + severId + "'";
			}
			sql += " order by id desc limit 3000 ";
			sql += " )as t1 GROUP BY uuid,namea,actory   order by maxid limit 20";
			log.info("sql:>>>\n{}", sql.toString());
			List<Dict> data = gdataDao.selectObjectList(sql.toString(), type_rowMapper);
			// 排序去重

			// Map<String, Dict> data_new = new LinkedHashMap<String, Dict>();
			//
			// if (data != null && data.size() > 0) {
			// for (Dict dict : data) {
			// data_new.put(dict.getValue(), dict);
			// if (data_new.size() >= 20) {
			// break;
			// }
			// }
			// }
			// List<Dict> data_result = new ArrayList<Dict>();
			// for (Entry<String, Dict> entry : data.entrySet()) {
			// data_result.add(entry.getValue());
			// }
			return data;
		}
		if (type == 2) {
			String sql = "SELECT DISTINCT (cont->>'dungeon_id') arg1 FROM(select * from zl_log_info ";
			if (StringUtils.isNotBlank(severId)&&!"-1".equals(severId)) {
				sql += " where server_id='" + severId + "'";
			}
			sql += " order by id desc  LIMIT 5000 )  as t1 LIMIT 10";
			log.info("sql:>>>\n{}", sql.toString());
			List<Dict> data = gdataDao.selectObjectList(sql.toString(), type_rowMapper_cpoy);
			return data;
		}
		return null;
	}

	private RowMapper<Dict> type_rowMapper_cpoy = new RowMapper<Dict>() {
		@Override
		public Dict mapRow(ResultSet rs, int rowNum) throws SQLException {
			Dict bean = new Dict();
			bean.setValue(rs.getString("arg1"));
			return bean;
		}
	};

	private RowMapper<Dict> type_rowMapper = new RowMapper<Dict>() {
		@Override
		public Dict mapRow(ResultSet rs, int rowNum) throws SQLException {
			Dict bean = new Dict();
			bean.setValue(rs.getString("uuid"));
			// actor_type 角色类型(human=玩家, mon=怪物, partner=人物的招唤物)
			String type = rs.getString("actory");
			String name = rs.getString("namea");
			if (StringUtils.isBlank(type)) {
				type = "";
			}
			if (StringUtils.isBlank(name)) {
				name = "";
			}
			if (type.equals("human")) {
				type = "玩家";
			}
			if (type.equals("mon")) {
				type = "怪物";
			}
			if (type.equals("partner")) {
				type = "人物的招唤物";
			}

			String var = type + "-" + name + "-" + rs.getString("uuid");
			bean.setName(var);
			// bean.setType(rs.getString("actory"));
			return bean;
		}
	};

	/**
	 * 获取最大时间
	 * 
	 * @param bean
	 * @return
	 */
	public String getMaxTime(QueryCommBean bean) {
		// 参数
		List<Object> paramArray = new LinkedList<Object>();
		// 条件组装
		StringBuffer sqlWhere = getSQLString(bean);// 完成
		StringBuffer sql = new StringBuffer(512);
		sql.append("SELECT to_char(MAX(time),'YYYY-MM-DD HH24:MI:SS') tdata FROM zl_log_info_attrs ");
		sql.append(sqlWhere);
		log.info("sql:>>>\n{}\n param={}", sql.toString(), paramArray.toArray());
		String data = gdataDao.selectObject(sql.toString(), str_rowMapper);
		return data;
	}

	private RowMapper<String> str_rowMapper = new RowMapper<String>() {
		@Override
		public String mapRow(ResultSet rs, int rowNum) throws SQLException {
			return rs.getString("tdata");
		}
	};

	@Override
	public CombatAttr getAttrs(QueryCommBean bean) {
		bean.setFile("attrs");
		// 获取最大的时间
		String maxDate = getMaxTime(bean);
		if (StringUtils.isBlank(maxDate)) {
			return new CombatAttr();
		}

		// 参数
		List<Object> paramArray = new LinkedList<Object>();
		// 条件组装
		StringBuffer sqlWhere = getSQLString(bean);// 完成
		StringBuffer sql = new StringBuffer(512);
		sql.append("select * from zl_log_info_attrs ");
		sql.append(sqlWhere);
		sql.append(" and time='");
		sql.append(maxDate);
		sql.append("'");
		sql.append(" LIMIT 1");
		log.info("sql:>>>\n{}\n param={}", sql.toString(), paramArray.toArray());
		CombatAttr data = gdataDao.selectObject(sql.toString(), rowMapper_attrs);

		// 取下一条
		// select * from zl_log_info where file='attrs' and
		// cont->>'uuid'='10000114' and time<'2017-06-29 17:32:13' order by time
		// desc LIMIT 1
		CombatAttr dif = getDifAttrs(bean, maxDate);
		if (dif != null && StringUtils.isNotBlank(dif.getCont())) {
			// 对比数据
			data.setContDif(dif.getCont());
		}
		data.setTime(maxDate);
		return data;
	}

	// 取下一条
	// select * from zl_log_info where file='attrs' and cont->>'uuid'='10000114'
	// and time<'2017-06-29 17:32:13' order by time desc LIMIT 1
	public CombatAttr getDifAttrs(QueryCommBean bean, String time) {
		bean.setFile("attrs");
		// 获取最大的时间
		String maxDate = getMaxTime(bean);
		if (StringUtils.isBlank(maxDate)) {
			return new CombatAttr();
		}

		// 参数
		List<Object> paramArray = new LinkedList<Object>();
		// 条件组装
		StringBuffer sqlWhere = getSQLString(bean);// 完成
		StringBuffer sql = new StringBuffer(512);
		sql.append("select * from zl_log_info_attrs ");
		sql.append(sqlWhere);
		sql.append(" and time<'");
		sql.append(maxDate);
		sql.append("'");
		sql.append(" order by id desc LIMIT 1");
		log.info("sql:>>>\n{}\n param={}", sql.toString(), paramArray.toArray());
		CombatAttr data = gdataDao.selectObject(sql.toString(), rowMapper_attrs);
		if(data==null){
			return new CombatAttr();
		}
		data.setTime(maxDate);
		return data;
	}

	private RowMapper<CombatAttr> rowMapper_attrs = new RowMapper<CombatAttr>() {
		@Override
		public CombatAttr mapRow(ResultSet rs, int rowNum) throws SQLException {
			CombatAttr bean = new CombatAttr();
			bean.setCont(rs.getString("cont"));
			// bean.setUuid(rs.getString("uuid"));
			// bean.setName(rs.getString(columnIndex));
			return bean;
		}
	};

}
