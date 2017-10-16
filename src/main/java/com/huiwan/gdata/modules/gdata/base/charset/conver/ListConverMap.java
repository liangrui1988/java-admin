package com.huiwan.gdata.modules.gdata.base.charset.conver;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import org.apache.commons.lang.StringUtils;

import com.huiwan.gdata.modules.gdata.base.charset.CharsetDataUtil;
import com.huiwan.gdata.modules.gdata.base.charset.bean.CharsetBeanDobule;
import com.huiwan.gdata.modules.gdata.base.charset.bean.QueryCommBean;
import com.huiwan.gdata.modules.gdata.base.charset.bean.common.CharsetBeanCommon;

public class ListConverMap {

	public static final String TIME_TYPE_HOUR = "HOUR";// 时间处理按小时
	public static final String TIME_TYPE_DAY = "DAY";// 时间处理按天

	/**
	 * 按日期归组，再按id（type）排序
	 * 
	 * @param lists
	 * @return
	 */
	public static Map<String, List<CharsetBeanDobule>> beanConverMap(List<CharsetBeanDobule> lists) {
		if (lists == null || lists.size() <= 0) {
			return new HashMap<>();
		}
		Map<String, List<CharsetBeanDobule>> maps = new LinkedHashMap<>();
		for (CharsetBeanDobule bean : lists) {
			// 存在已有key,取出来再存进去
			if (maps.containsKey(bean.getxName())) {
				List<CharsetBeanDobule> list = maps.get(bean.getxName());
				list.add(bean);
				continue;
			}
			// 如果没有key,新加
			List<CharsetBeanDobule> list = new ArrayList<>();
			list.add(bean);
			maps.put(bean.getxName(), list);
		}

		// 对list 进行排序
		if (maps.size() > 0) {
			for (Entry<String, List<CharsetBeanDobule>> entry : maps.entrySet()) {
				if (entry.getValue().size() <= 0) {
					continue;
				}
				CharsetBeanDobule[] array = new CharsetBeanDobule[entry.getValue().size()];
				entry.getValue().toArray(array);
				// 排序后重新加入map
				java.util.Arrays.sort(array, new BeanSort());
				List<CharsetBeanDobule> new_list = new ArrayList<>();
				Collections.addAll(new_list, array);
				maps.put(entry.getKey(), new_list);
			}
		}
		return maps;
	}

	/**
	 * 按日期归组，再按id（type）排序
	 * 
	 * @param lists
	 * @return
	 */
	public static Map<String, List<CharsetBeanCommon>> beanConverMapToObject(List<CharsetBeanCommon> lists) {
		if (lists == null || lists.size() <= 0) {
			return new HashMap<>();
		}
		Map<String, List<CharsetBeanCommon>> maps = new LinkedHashMap<>();
		for (CharsetBeanCommon bean : lists) {
			// 存在已有key,取出来再存进去
			if (maps.containsKey(bean.getxName())) {
				List<CharsetBeanCommon> list = maps.get(bean.getxName());
				list.add(bean);
				continue;
			}
			// 如果没有key,新加
			List<CharsetBeanCommon> list = new ArrayList<>();
			list.add(bean);
			maps.put(bean.getxName(), list);
		}

		// 对list 进行排序
		if (maps.size() > 0) {
			for (Entry<String, List<CharsetBeanCommon>> entry : maps.entrySet()) {
				if (entry.getValue().size() <= 0) {
					continue;
				}
				CharsetBeanCommon[] array = new CharsetBeanCommon[entry.getValue().size()];
				entry.getValue().toArray(array);
				// 排序后重新加入map
				java.util.Arrays.sort(array, new BeanSortObject());
				List<CharsetBeanCommon> new_list = new ArrayList<>();
				Collections.addAll(new_list, array);
				maps.put(entry.getKey(), new_list);
			}
		}
		return maps;
	}

	/**
	 * id进行分组,按日期排序，如果日期不存在，再补0.
	 * <p>
	 * 此方法只对日期=Xname生效
	 * 
	 * @param vo
	 *            条件
	 * @param lists
	 *            数据集合
	 * @param charsetType
	 *            图表类型
	 */
	public static Map<String, List<CharsetBeanCommon>> beanConverMapToObject_to_date(QueryCommBean vo,
			List<CharsetBeanCommon> lists, String charsetType, String timeType) {
		if (StringUtils.isBlank(charsetType)) {
			charsetType = "";
		}
		if (lists == null || lists.size() <= 0) {
			return new HashMap<>();
		}
		// id分组
		Map<String, List<CharsetBeanCommon>> maps = new LinkedHashMap<>();
		for (CharsetBeanCommon bean : lists) {
			// 存在已有key,取出来再存进去
			if (maps.containsKey(bean.getId())) {
				List<CharsetBeanCommon> list = maps.get(bean.getId());
				list.add(bean);
				continue;
			}
			// 如果没有key,新加
			List<CharsetBeanCommon> list = new ArrayList<>();
			list.add(bean);
			maps.put(bean.getId(), list);
		}

		// 检查日期是否齐全
		Map<String, Integer> mapNullData = null;
		// 明确按天分
		if (StringUtils.isNotBlank(timeType) && TIME_TYPE_DAY.equals(timeType)) {// 按天排序
			mapNullData = CharsetDataUtil.getDayIntervalToCommonASC(vo.getDt1(), vo.getDt2(), Integer.class);
		} else {
			// 同一天按小时分
			mapNullData = CharsetDataUtil.getDayIntervalToCommonASC_OR_time_hour24(vo.getDt1(), vo.getDt2(),
					Integer.class);
		}

		// 空的map,把真实数据找出来，补上空缺
		Map<String, List<CharsetBeanCommon>> new_map = new LinkedHashMap<String, List<CharsetBeanCommon>>();
		for (Entry<String, List<CharsetBeanCommon>> entry : maps.entrySet()) {// 每一层id为key
			List<CharsetBeanCommon> new_list = new ArrayList<>();
			// 方便对比
			Map<String, CharsetBeanCommon> id_map = new HashMap<>();
			for (CharsetBeanCommon bean : entry.getValue()) {// 每个id对应的list
				id_map.put(bean.getxName(), bean);
			}
			// 如果是空
			// if(temp_list==null||temp_list.size()<=0){
			//
			// }
			// 按vo给定的日期，检查
			for (Entry<String, Integer> entry_date : mapNullData.entrySet()) {
				// 日期是否存在,如果存在,取出真实数据来
				if (id_map.containsKey(entry_date.getKey())) {
					CharsetBeanCommon temp_bean = id_map.get(entry_date.getKey());
					temp_bean.setType(charsetType);
					new_list.add(temp_bean);
					continue;
				}
				// 不存在补0
				CharsetBeanCommon bean_vir = new CharsetBeanCommon();
				bean_vir.setCount(0);// 补0
				bean_vir.setxName(entry_date.getKey());// 日期
				bean_vir.setId(entry.getKey());// id
				bean_vir.setType(charsetType);
				new_list.add(bean_vir);
			}
			new_map.put(entry.getKey(), new_list);
		}
		// 对list 进行排序
		if (new_map.size() > 0) {
			for (Entry<String, List<CharsetBeanCommon>> entry : new_map.entrySet()) {
				if (entry.getValue().size() <= 0) {
					continue;
				}
				CharsetBeanCommon[] array = new CharsetBeanCommon[entry.getValue().size()];
				entry.getValue().toArray(array);
				// 排序后重新加入map
				if (StringUtils.isNotBlank(timeType) && TIME_TYPE_HOUR.equals(timeType)) {// 按小时排序
					java.util.Arrays.sort(array, new BeanSortTimeHourObject());
				} else if (StringUtils.isNotBlank(timeType) && TIME_TYPE_DAY.equals(timeType)) {// 按天排序
					java.util.Arrays.sort(array, new BeanSortObject());
				} else {// 按天排序
					java.util.Arrays.sort(array, new BeanSortObject());
				}
				List<CharsetBeanCommon> new_list_sort = new ArrayList<>();
				Collections.addAll(new_list_sort, array);
				new_map.put(entry.getKey(), new_list_sort);
			}
		}
		return new_map;
	}

	/**
	 * name进行分组,按日期排序，如果日期不存在，再补0.
	 * <p>
	 * 此方法只对日期=id生效
	 * 
	 * @param vo
	 *            条件
	 * @param lists
	 *            数据集合
	 * @param charsetType
	 *            图表类型
	 */
	public static Map<String, List<CharsetBeanCommon>> beanConverMapToObject_to_date_toidEqDt(QueryCommBean vo,
			List<CharsetBeanCommon> lists, String charsetType, String timeType) {
		if (StringUtils.isBlank(charsetType)) {
			charsetType = "";
		}
		if (lists == null || lists.size() <= 0) {
			return new HashMap<>();
		}
		// name分组，非日期，key=list分组
		Map<String, List<CharsetBeanCommon>> maps = new LinkedHashMap<>();
		for (CharsetBeanCommon bean : lists) {
			// 存在已有key,取出来再存进去
			if (maps.containsKey(bean.getId())) {
				List<CharsetBeanCommon> list = maps.get(bean.getId());
				list.add(bean);
				continue;
			}
			// 如果没有key,新加
			List<CharsetBeanCommon> list = new ArrayList<>();
			list.add(bean);
			maps.put(bean.getId(), list);
		}
		// 检查日期是否齐全
		Map<String, Integer> mapNullData = null;
		if (StringUtils.isNotBlank(timeType) && TIME_TYPE_DAY.equals(timeType)) {// 按天排序
			// 相等也按天显示
			mapNullData = CharsetDataUtil.getDayIntervalToCommonASC(vo.getDt1(), vo.getDt2(), Integer.class);
		} else {
			mapNullData = CharsetDataUtil.getDayIntervalToCommonASC_OR_time_hour24(vo.getDt1(), vo.getDt2(),
					Integer.class);
		}

		// 空的map,把真实数据找出来，补上空缺
		Map<String, List<CharsetBeanCommon>> new_map = new LinkedHashMap<String, List<CharsetBeanCommon>>();
		for (Entry<String, List<CharsetBeanCommon>> entry : maps.entrySet()) {// 每一层id为key，非日期
			List<CharsetBeanCommon> new_list = new ArrayList<>();
			// 小集合，k={list}= 方便对比
			Map<String, CharsetBeanCommon> namex_dt_map = new HashMap<>();// dt={list}
			for (CharsetBeanCommon bean : entry.getValue()) {// 每个id对应的list
				namex_dt_map.put(bean.getxName(), bean);
			}
			// 如果是空
			// if(temp_list==null||temp_list.size()<=0){
			//
			// }
			// 按vo给定的日期，检查
			for (Entry<String, Integer> entry_date : mapNullData.entrySet()) {
				// 日期是否存在,如果存在,取出真实数据来
				if (namex_dt_map.containsKey(entry_date.getKey())) {
					CharsetBeanCommon temp_bean = namex_dt_map.get(entry_date.getKey());
					temp_bean.setType(charsetType);
					new_list.add(temp_bean);
					continue;
				}
				// 不存在补0
				CharsetBeanCommon bean_vir = new CharsetBeanCommon();
				bean_vir.setCount(0);// 补0
				bean_vir.setxName(entry_date.getKey());// =日期
				bean_vir.setId(entry.getKey());// id,=name
				bean_vir.setType(charsetType);
				new_list.add(bean_vir);
			}
			new_map.put(entry.getKey(), new_list);
		}
		// 对list 进行排序
		if (new_map.size() > 0) {
			for (Entry<String, List<CharsetBeanCommon>> entry : new_map.entrySet()) {
				if (entry.getValue().size() <= 0) {
					continue;
				}
				CharsetBeanCommon[] array = new CharsetBeanCommon[entry.getValue().size()];
				entry.getValue().toArray(array);
				// 排序后重新加入map
				if (StringUtils.isNotBlank(timeType) && TIME_TYPE_HOUR.equals(timeType)) {// 按小时排序
					java.util.Arrays.sort(array, new BeanSortTimeHourObject());
				} else if (StringUtils.isNotBlank(timeType) && TIME_TYPE_DAY.equals(timeType)) {// 按天排序
					java.util.Arrays.sort(array, new BeanSortObject());
				} else {// 按天排序
					java.util.Arrays.sort(array, new BeanSortObject());
				}
				List<CharsetBeanCommon> new_list_sort = new ArrayList<>();
				Collections.addAll(new_list_sort, array);
				new_map.put(entry.getKey(), new_list_sort);
			}
		}
		return new_map;
	}

	public static void main(String[] args) {
		List<Object> list = new ArrayList<Object>();
		list.add(1);
		list.add(2);
		list.add(3);
		Object[] array = list.toArray();
		// Integer[] array = new Integer[list.size()];
		// list.toArray(array);
		array[0] = 10;
		System.out.println(array[0]);
		System.out.println(array[2]);
		List<Object> list2 = new ArrayList<Object>();
		Collections.addAll(list2, array);
		System.out.println(list2);

	}

}
