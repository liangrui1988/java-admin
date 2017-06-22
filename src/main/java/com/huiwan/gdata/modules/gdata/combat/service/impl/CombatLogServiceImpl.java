package com.huiwan.gdata.modules.gdata.combat.service.impl;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Service;

import com.huiwan.gdata.common.utils.pagination.Paginator;
import com.huiwan.gdata.common.utils.pagination.PaginatorResult;
import com.huiwan.gdata.modules.gdata.base.GDataDao;
import com.huiwan.gdata.modules.gdata.base.charset.bean.QueryCommBean;
import com.huiwan.gdata.modules.gdata.combat.entity.CombatLog;
import com.huiwan.gdata.modules.gdata.combat.service.CombatLogService;
import com.huiwan.gdata.modules.sys.entity.Dict;
import com.huiwan.gdata.modules.sys.service.IDictService;

@Service
public class CombatLogServiceImpl implements CombatLogService {
	Logger log = LoggerFactory.getLogger(getClass());

	@Autowired
	private GDataDao gdataDao;
	@Autowired
	private IDictService dictService;

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
//		sql.append(
//				"SELECT to_char(time,'YYYY-MM-DD HH24:MI:SS') dt,cont->>'add_hp' add_hp,cont->>'dungeon_id' dungeon_id ");
		
		sql.append(
				"SELECT id,server_id,file,to_char(time,'YYYY-MM-DD HH24:MI:SS') dt,cont->>'uuid' uuid,cont cont ");
		
		sql.append(" FROM zl_log ");
		sql.append(sqlWhere);
		// 排序
		if (StringUtils.isNotBlank(paginator.getSort())) {
			if ("logType".equals(paginator.getSort())) {
				sql.append(" order by ");
				sql.append(" flag ");
				sql.append(paginator.getOrder());
			}
			if ("dt".equals(paginator.getSort())) {
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
		List<CombatLog> data = gdataDao.selectObjectList(sql.toString(), rowMapper);
		
		Map<String,String> dicts=dictService.getByTypeMaps("_file_types");
		
		//转换中文
		if(data!=null&&data.size()>0){
			for(CombatLog log:data){
				if(dicts.containsKey(log.getFile())){
					log.setFile(dicts.get(log.getFile()));
				}
			}
		}
		
		
		result.setRows(data);
		return result;
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
		sql.append(" FROM zl_log ");
		sql.append(sqlWhere);
		log.info("sql:>>>\n{}\n param={}", sql.toString(), paramArray.toArray());
		int total = gdataDao.selectForRows(sql.toString());
		return total;
	}

	private StringBuffer getSQLString(QueryCommBean vo) {
		// 条件语句
		StringBuffer sbWhere = new StringBuffer();


		if (vo != null) {
			if(StringUtils.isNotBlank(vo.getFile())){
				sbWhere.append(" and file='");
				sbWhere.append(vo.getFile());
				sbWhere.append("'");
			}
			
			// 服务器
			if (vo.getServer() != null && vo.getServer() > 0) {
				sbWhere.append(" and server_id=");
				sbWhere.append(vo.getServer());
			}
			// 日期
			if (StringUtils.isNotBlank(vo.getDt1())) {// 如果不为空，
				sbWhere.append(" and time>='");
				sbWhere.append(vo.getDt1());
				sbWhere.append("'");
			}

			if (StringUtils.isNotBlank(vo.getDt2())) {// 如果不为空，
				sbWhere.append(" and time<='");
				sbWhere.append(vo.getDt2());
//				sbWhere.append(" 23:59:59'");
				sbWhere.append("'");
			}

			// 副本
			if (StringUtils.isNotBlank(vo.getCopyId())) {// 如果不为空，
				sbWhere.append(" and cont->>'copyId'='");
				sbWhere.append(vo.getCopyId());
				sbWhere.append("'");
			}
			
			//uuid
			if (StringUtils.isNotBlank(vo.getType())) {// 如果不为空，
				sbWhere.append(" and cont->>'uuid'='");
				sbWhere.append(vo.getType());
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
		String sql="select  id,server_id,file,to_char(time,'YYYY-MM-DD HH24:MI:SS') dt,cont->>'uuid' uuid,cont cont from zl_log where id="+id;
		log.info("sql:>>>\n{}", sql.toString() );
		CombatLog data = gdataDao.selectObject(sql.toString(), rowMapper);
		return data;
	}

	@Override
	public List<Dict> getObjTypes(int type) {
		if(type==1){
//			String sql="SELECT DISTINCT(cont->>'uuid') uuid,time FROM zl_log order by time desc LIMIT 10";
			String sql="SELECT DISTINCT (cont->>'uuid') arg1 FROM(select * from zl_log order by time)  as t1 LIMIT 10";
			log.info("sql:>>>\n{}", sql.toString() );
			List<Dict> data = gdataDao.selectObjectList(sql.toString(), type_rowMapper);
			return data;
		}
		if(type==2)
		{
			String sql="SELECT DISTINCT (cont->>'dungeon_id') arg1 FROM(select * from zl_log order by time)  as t1 LIMIT 10";
			log.info("sql:>>>\n{}", sql.toString() );
			List<Dict> data = gdataDao.selectObjectList(sql.toString(), type_rowMapper);
			return data;
		}
		return null;
	}
	
	
	private RowMapper<Dict> type_rowMapper = new RowMapper<Dict>() {
		@Override
		public Dict mapRow(ResultSet rs, int rowNum) throws SQLException {
			Dict bean = new Dict();
			bean.setValue(rs.getString("arg1"));
			return bean;
		}
	};
}
