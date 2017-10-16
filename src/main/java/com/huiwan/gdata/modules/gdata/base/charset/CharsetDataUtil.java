package com.huiwan.gdata.modules.gdata.base.charset;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import org.apache.commons.lang.StringUtils;

import com.huiwan.gdata.modules.gdata.base.charset.bean.CharsetBean;
import com.huiwan.gdata.modules.gdata.base.charset.bean.CharsetBeanDobule;
import com.huiwan.gdata.modules.gdata.base.charset.bean.CharsetBeanLong;
import com.huiwan.gdata.modules.gdata.base.charset.bean.ChartData;
import com.huiwan.gdata.modules.gdata.base.charset.bean.QueryCommBean;
import com.huiwan.gdata.modules.gdata.base.charset.bean.Series;
import com.huiwan.gdata.modules.gdata.base.charset.bean.SeriesDouble;
import com.huiwan.gdata.modules.gdata.base.charset.bean.SeriesLong;
import com.huiwan.gdata.modules.gdata.base.charset.bean.common.CharsetBeanCommon;
import com.huiwan.gdata.modules.gdata.base.charset.bean.common.SeriesCommon;
import com.huiwan.gdata.modules.gdata.base.charset.conver.ListConverMap;

/**
 * 图表工具
 * 
 * @author ruiliang
 * @date 2016/10/10
 *
 */
public class CharsetDataUtil {

	/**
	 * 根据开始时间和结束时间，给一个map默认0 数据,倒序排，大的在前面
	 * 
	 * @param startTimeStr
	 *            开始日期
	 * @param endTimeStr
	 *            结束日期
	 * @param clz,map
	 *            里面的默认类型
	 * @return 从大到小的日期排序的空map
	 */
	public static <T> Map<String, T> getDayIntervalToLongOrderByDesc(String startTimeStr, String endTimeStr,
			Class<T> clz) {
		DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
		String dataStr = df.format(new Date());
		// 取今天
		if (StringUtils.isBlank(startTimeStr)) {
			startTimeStr = dataStr;
		}
		if (StringUtils.isBlank(endTimeStr)) {
			endTimeStr = dataStr;
		}
		Map<String, T> map = new LinkedHashMap<String, T>();
		// 天处理
		Date statTime = null;
		Date endTime = null;
		try {
			statTime = df.parse(startTimeStr);
			endTime = df.parse(endTimeStr);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		Long startDateTimeS = statTime.getTime();
		Long EndDateTimeS = endTime.getTime();
		// 如果结束时间 大于到位的时间
		Long temp = EndDateTimeS;
		// T obj = clz.newInstance();
		T t = null;
		if (clz == Long.class || clz == long.class) {
			t = (T) new Long(0L);
		} else if (clz == Integer.class || clz == int.class) {
			t = (T) new Integer(0);
		} else if (clz == Double.class || clz == double.class) {
			t = (T) new Double(0);
		} else if (clz == Float.class || clz == float.class) {
			t = (T) new Float(0);
		}
		while (temp >= startDateTimeS) {
			String xname = df.format(temp);
			map.put(xname, t);
			// 加一天的时间
			temp = temp - 86400 * 1000;
		}
		return map;
	}

	/**
	 * 根据开始时间和结束时间，给一个map默认0 数据，正常排序从小大到
	 * 
	 * @param startTimeStr
	 *            开始时间字符串
	 * @param endTimeStr
	 *            结束时间字符串
	 * @param clz
	 *            map指定 class
	 * @return
	 */
	public static <T> Map<String, T> getDayIntervalToCommonASC(String startTimeStr, String endTimeStr, Class<T> clz) {
		DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
		String dataStr = df.format(new Date());
		// 取今天
		if (StringUtils.isBlank(startTimeStr)) {
			startTimeStr = dataStr;
		}
		if (StringUtils.isBlank(endTimeStr)) {
			endTimeStr = dataStr;
		}
		Map<String, T> map = new LinkedHashMap<String, T>();
		// 天处理
		Date statTime = new Date();
		Date endTime = new Date();
		try {
			statTime = df.parse(startTimeStr);
			endTime = df.parse(endTimeStr);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		Long startDateTimeS = statTime.getTime();
		Long EndDateTimeS = endTime.getTime();
		// T obj = clz.newInstance();
		T t = null;
		if (clz == Long.class || clz == long.class) {
			t = (T) new Long(0L);
		} else if (clz == Integer.class || clz == int.class) {
			t = (T) new Integer(0);
		} else if (clz == Double.class || clz == double.class) {
			t = (T) new Double(0);
		} else if (clz == Float.class || clz == float.class) {
			t = (T) new Float(0);
		}
		// 如果结束时间 大于到位的时间
		Long temp = startDateTimeS;
		while (temp <= EndDateTimeS) {
			String xname = df.format(temp);
			map.put(xname, t);
			// 加一天的时间
			temp = temp + 86400 * 1000;
		}
		return map;
	}

	/**
	 * 根据开始时间和结束时间，给一个map默认0 数据，正常排序从小大到
	 * <p>
	 * 如果日期相同，则返回24小时数字
	 * 
	 * @param startTimeStr
	 *            开始时间字符串
	 * @param endTimeStr
	 *            结束时间字符串
	 * @param clz
	 *            map指定 class
	 * @return
	 */
	public static <T> Map<String, T> getDayIntervalToCommonASC_OR_time_hour24(String startTimeStr, String endTimeStr,
			Class<T> clz) {
		DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
		String dataStr = df.format(new Date());
		// 取今天
		if (StringUtils.isBlank(startTimeStr)) {
			startTimeStr = dataStr;
		}
		if (StringUtils.isBlank(endTimeStr)) {
			endTimeStr = dataStr;
		}
		// T obj = clz.newInstance();
		T t = null;
		if (clz == Long.class || clz == long.class) {
			t = (T) new Long(0L);
		} else if (clz == Integer.class || clz == int.class) {
			t = (T) new Integer(0);
		} else if (clz == Double.class || clz == double.class) {
			t = (T) new Double(0);
		} else if (clz == Float.class || clz == float.class) {
			t = (T) new Float(0);
		}
		Map<String, T> map = new LinkedHashMap<String, T>();
		// 如果时间相同,小时处理
		if (startTimeStr.equals(endTimeStr)) {
			// for (int i = 0; i <= 23; i++) {
			// map.put(String.valueOf(i), t);
			// }
			map.put("0", t);
			map.put("01", t);
			map.put("02", t);
			map.put("03", t);
			map.put("04", t);
			map.put("05", t);
			map.put("06", t);
			map.put("07", t);
			map.put("08", t);
			map.put("09", t);
			map.put("10", t);
			map.put("11", t);
			map.put("12", t);
			map.put("13", t);
			map.put("14", t);
			map.put("15", t);
			map.put("16", t);
			map.put("17", t);
			map.put("18", t);
			map.put("19", t);
			map.put("20", t);
			map.put("21", t);
			map.put("22", t);
			map.put("23", t);
			return map;
		}
		// 天处理
		Date statTime = new Date();
		Date endTime = new Date();
		try {
			statTime = df.parse(startTimeStr);
			endTime = df.parse(endTimeStr);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		Long startDateTimeS = statTime.getTime();
		Long EndDateTimeS = endTime.getTime();

		// 如果结束时间 大于到位的时间
		Long temp = startDateTimeS;
		while (temp <= EndDateTimeS) {
			String xname = df.format(temp);
			map.put(xname, t);
			// 加一天的时间
			temp = temp + 86400 * 1000;
		}
		return map;
	}

	/**
	 * 根据开始时间和结束时间，给一个map默认0 数据
	 * 
	 * @param startTimeStr
	 * @param endTimeStr
	 * @return
	 * @throws ParseException
	 * @Deprecated 新方法 {@link CharsetDataUtil.getDayIntervalToCommonASC() }
	 * 
	 */
	@Deprecated
	public static Map<String, Integer> getDayInterval(String startTimeStr, String endTimeStr) throws ParseException {
		DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
		String dataStr = df.format(new Date());
		// 取今天
		if (StringUtils.isBlank(startTimeStr)) {
			startTimeStr = dataStr;
		}
		if (StringUtils.isBlank(endTimeStr)) {
			endTimeStr = dataStr;
		}
		Map<String, Integer> map = new LinkedHashMap<String, Integer>();
		// 天处理
		Date statTime = df.parse(startTimeStr);
		Date endTime = df.parse(endTimeStr);
		Long startDateTimeS = statTime.getTime();
		Long EndDateTimeS = endTime.getTime();
		// 如果结束时间 大于到位的时间
		Long temp = startDateTimeS;
		while (temp <= EndDateTimeS) {
			String xname = df.format(temp);
			map.put(xname, 0);
			// 加一天的时间
			temp = temp + 86400 * 1000;
		}
		return map;
	}

	/**
	 * 根据开始时间和结束时间，给一个map默认0 数据
	 * 
	 * @param startTimeStr
	 * @param endTimeStr
	 * @return
	 * @throws ParseException
	 * @Deprecated 新方法 {@link CharsetDataUtil.getDayIntervalToCommonASC() }
	 * 
	 */
	@Deprecated
	public static Map<String, Long> getDayIntervalToLong(String startTimeStr, String endTimeStr) throws ParseException {
		DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
		String dataStr = df.format(new Date());
		// 取今天
		if (StringUtils.isBlank(startTimeStr)) {
			startTimeStr = dataStr;
		}
		if (StringUtils.isBlank(endTimeStr)) {
			endTimeStr = dataStr;
		}
		Map<String, Long> map = new LinkedHashMap<String, Long>();
		// 天处理
		Date statTime = df.parse(startTimeStr);
		Date endTime = df.parse(endTimeStr);
		Long startDateTimeS = statTime.getTime();
		Long EndDateTimeS = endTime.getTime();
		// 如果结束时间 大于到位的时间
		Long temp = startDateTimeS;
		while (temp <= EndDateTimeS) {
			String xname = df.format(temp);
			map.put(xname, 0L);
			// 加一天的时间
			temp = temp + 86400 * 1000;
		}
		return map;
	}

	/**
	 * 根据开始时间和结束时间，给一个map默认0 数据
	 * 
	 * @param startTimeStr
	 * @param endTimeStr
	 * @return
	 * @throws ParseException
	 * @Deprecated 新方法 {@link CharsetDataUtil.getDayIntervalToCommonASC() }
	 */
	@Deprecated
	public static Map<String, Double> getDayIntervalToDouble(String startTimeStr, String endTimeStr)
			throws ParseException {
		DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
		String dataStr = df.format(new Date());
		// 取今天
		if (StringUtils.isBlank(startTimeStr)) {
			startTimeStr = dataStr;
		}
		if (StringUtils.isBlank(endTimeStr)) {
			endTimeStr = dataStr;
		}
		Map<String, Double> map = new LinkedHashMap<String, Double>();
		// 天处理
		Date statTime = df.parse(startTimeStr);
		Date endTime = df.parse(endTimeStr);
		Long startDateTimeS = statTime.getTime();
		Long EndDateTimeS = endTime.getTime();
		// 如果结束时间 大于到位的时间
		Long temp = startDateTimeS;
		while (temp <= EndDateTimeS) {
			String xname = df.format(temp);
			map.put(xname, 0D);
			// 加一天的时间
			temp = temp + 86400 * 1000;
		}

		return map;
	}

	/**
	 * 根据数据组装图表数据->Integer
	 * 
	 * @param vo
	 * @param result
	 * @return
	 * @throws ParseException
	 */
	public static ChartData getChartDataInComBean(QueryCommBean vo, List<CharsetBean> result) throws ParseException {
		// 对数据进行归类
		// id={日期=数据}
		Map<Integer, Map<String, Integer>> groupMapList = new LinkedHashMap<Integer, Map<String, Integer>>();
		if (result != null && result.size() > 0) {
			// 补0数据
			for (CharsetBean bean : result) {
				// 日期=数据
				Map<String, Integer> mapNullData = CharsetDataUtil.getDayInterval(vo.getDt1(), vo.getDt2());
				groupMapList.put(bean.getId(), mapNullData);
			}
			// 补真实数据
			for (CharsetBean bean : result) {
				if (groupMapList.containsKey(bean.getId())) {// 如果存在，取出来再累积
					Map<String, Integer> tmap = groupMapList.get(bean.getId());
					tmap.put(bean.getTimex(), bean.getCountx());// 如2016-10-10=80
																// ,再追加2016-10-11=90,
					groupMapList.put(bean.getId(), tmap);// 然后再放入
				} else {
					Map<String, Integer> tmap = new LinkedHashMap<String, Integer>();
					tmap.put(bean.getTimex(), bean.getCountx());
					groupMapList.put(bean.getId(), tmap);
				}
			}
		}
		// 填充图表
		List<String> aisxName = new ArrayList<String>(); // 标题 X_Aisx
		List<Series> seriess = new ArrayList<Series>();// 图表 名和数据集合
		if (groupMapList != null && groupMapList.size() > 0) {
			for (Entry<Integer, Map<String, Integer>> entry : groupMapList.entrySet()) {
				Series series = new Series();
				List<Integer> data = new ArrayList<>();// 图表数据
				if (entry.getKey() != null) {
					series.setName(String.valueOf(entry.getKey()));// 转换名称x
				}
				for (Entry<String, Integer> tmap : entry.getValue().entrySet()) {
					aisxName.add(tmap.getKey());
					data.add(tmap.getValue());
				}
				series.setData(data);
				seriess.add(series);// 加一个数据组
			}
		}
		ChartData charData = new ChartData();
		charData.setAisxName(aisxName);
		charData.setSeries(seriess);
		return charData;
	}

	/**
	 * 根据数据组装图表数据 2 ->long
	 * <p>
	 * 方便统一转换图表
	 * 
	 * @param vo
	 *            查询条件
	 * @param result
	 *            原始数据
	 * @return 图表对象
	 * @throws ParseException
	 */
	public static ChartData getChartDataInComBean2(QueryCommBean vo, List<CharsetBeanLong> result)
			throws ParseException {
		// 对数据进行归类
		// id={日期=数据}
		Map<String, Map<String, Long>> groupMapList = new LinkedHashMap<String, Map<String, Long>>();
		if (result != null && result.size() > 0) {
			// 补0数据
			for (CharsetBeanLong bean : result) {
				// 日期=数据
				Map<String, Long> mapNullData = CharsetDataUtil.getDayIntervalToLong(vo.getDt1(), vo.getDt2());
				groupMapList.put(String.valueOf(bean.getId()), mapNullData);
			}
			// 补真实数据
			for (CharsetBeanLong bean : result) {
				if (groupMapList.containsKey(bean.getId())) {// 如果存在，取出来再累积
					Map<String, Long> tmap = groupMapList.get(bean.getId());
					tmap.put(bean.getxName(), bean.getCount());// 如2016-10-10=80
																// ,再追加2016-10-11=90,
					groupMapList.put(bean.getId(), tmap);// 然后再放入
				} else {
					Map<String, Long> tmap = new LinkedHashMap<String, Long>();
					tmap.put(bean.getxName(), bean.getCount());
					groupMapList.put(bean.getId(), tmap);
				}
			}
		}
		// 填充图表
		List<String> aisxName = new ArrayList<String>(); // 标题 X_Aisx
		List<SeriesLong> seriess = new ArrayList<SeriesLong>();// 图表 名和数据集合
		if (groupMapList != null && groupMapList.size() > 0) {
			for (Entry<String, Map<String, Long>> entry : groupMapList.entrySet()) {
				SeriesLong series = new SeriesLong();
				List<Long> data = new ArrayList<>();// 图表数据
				if (entry.getKey() != null) {
					series.setName(String.valueOf(entry.getKey()));// 转换名称x
				}
				for (Entry<String, Long> tmap : entry.getValue().entrySet()) {
					aisxName.add(tmap.getKey());
					data.add(tmap.getValue());
				}
				series.setData(data);
				seriess.add(series);// 加一个数据组
			}
		}
		ChartData charData = new ChartData();
		charData.setAisxName(aisxName);
		charData.setSeriesLong(seriess);
		return charData;
	}

	/**
	 * 根据数据组装图表数据 T类型
	 * <p>
	 * 方便统一转换图表,
	 * 
	 * @param vo
	 *            查询条件，会根据开始日期和结束日期来遍历出每一天的 日期，如果没有当天数据，则显示0
	 * @param result
	 *            原始数据
	 * @return 图表对象
	 * @throws ParseException
	 */
	public static <T> ChartData getChartDataInComBeanDouble(QueryCommBean vo, List<T> result) throws ParseException {
		// 对数据进行归类
		// id={日期=数据}
		Map<String, Map<String, Double>> groupMapList = new LinkedHashMap<String, Map<String, Double>>();

		if (result != null && result.size() > 0) {
			// 补0数据
			for (T beanx : result) {
				CharsetBeanDobule bean = (CharsetBeanDobule) beanx;
				// 日期=数据
				Map<String, Double> mapNullData = CharsetDataUtil.getDayIntervalToDouble(vo.getDt1(), vo.getDt2());
				if (StringUtils.isBlank(bean.getId())) {
					bean.setId("series");
				}
				groupMapList.put(String.valueOf(bean.getId()), mapNullData);
			}
			// 补真实数据
			for (T beanx : result) {
				CharsetBeanDobule bean = (CharsetBeanDobule) beanx;
				if (groupMapList.containsKey(bean.getId())) {// 如果存在，取出来再累积
					Map<String, Double> tmap = groupMapList.get(bean.getId());
					if (bean.getCount() == null) {
						bean.setCount(0.0);
					}
					tmap.put(bean.getxName(), bean.getCount());// 如2016-10-10=80
																// ,再追加2016-10-11=90,
					groupMapList.put(bean.getId(), tmap);// 然后再放入
				} else {
					Map<String, Double> tmap = new LinkedHashMap<String, Double>();

					if (bean.getCount() == null) {
						bean.setCount(0.0);
					}
					tmap.put(bean.getxName(), bean.getCount());
					groupMapList.put(bean.getId(), tmap);
				}
			}
		}
		// 填充图表
		List<String> aisxName = new ArrayList<String>(); // 标题 X_Aisx
		List<SeriesDouble> seriess = new ArrayList<SeriesDouble>();// 图表 名和数据集合
		if (groupMapList != null && groupMapList.size() > 0) {
			for (Entry<String, Map<String, Double>> entry : groupMapList.entrySet()) {
				SeriesDouble series = new SeriesDouble();
				List<Double> data = new ArrayList<>();// 图表数据
				if (entry.getKey() != null) {
					series.setName(String.valueOf(entry.getKey()));// 转换名称x
				}
				for (Entry<String, Double> tmap : entry.getValue().entrySet()) {
					aisxName.add(tmap.getKey());
					data.add(tmap.getValue());
				}
				series.setData(data);
				seriess.add(series);// 加一个数据组
			}
		}
		ChartData charData = new ChartData();
		charData.setAisxName(aisxName);
		charData.setSeriesdouble(seriess);
		return charData;

	}

	/**
	 * 单个Series的报表
	 * 
	 * @param comBeans
	 * @return
	 */
	@Deprecated
	public static ChartData singleSeriesToCharset(List<CharsetBeanLong> comBeans, String sName) {
		if (comBeans == null || comBeans.size() <= 0) {
			return new ChartData();
		}
		List<String> aisxName = new ArrayList<String>();
		List<SeriesLong> series = new ArrayList<SeriesLong>();
		List<Long> data = new ArrayList<Long>();
		for (CharsetBeanLong c : comBeans) {
			if (c.getId() != null) {
				aisxName.add(c.getId());
			} else {
				aisxName.add("id为空");
			}

			if (c.getCount() == null) {
				c.setCount(0L);
			}
			data.add(c.getCount());
		}
		SeriesLong s = new SeriesLong();
		s.setName(sName);
		s.setData(data);
		series.add(s);
		// 根据数据组装图表数据
		ChartData chartData = new ChartData();
		chartData.setAisxName(aisxName);
		chartData.setSeriesLong(series);

		return chartData;
	}

	/**
	 * 常用报表bean转换为图表功能
	 * <p>
	 * 根据通用bean组件定义，来组合图表对象
	 * <p>
	 * demo {@link } com.uc.db.business.x8.action.social.AttentionFansAction
	 * 
	 * @date 2016/10/26
	 * @param comBeans
	 * @return
	 */
	public static ChartData commonBeanToCharset(List<CharsetBeanLong> comBeans) {
		if (comBeans == null || comBeans.size() <= 0) {
			return new ChartData();
		}
		// 按照id去分组=SeriesLong
		// 转map
		Map<String, List<CharsetBeanLong>> map = new LinkedHashMap<String, List<CharsetBeanLong>>();
		for (CharsetBeanLong c : comBeans) {
			// 如果有，则取出来，累加
			if (map.containsKey(c.getId())) {
				List<CharsetBeanLong> listbean = map.get(c.getId());
				listbean.add(c);
				// ? t
			} else {// 没有新加
				List<CharsetBeanLong> listbean = new ArrayList<>();
				listbean.add(c);
				map.put(c.getId(), listbean);
			}
		}
		if (map.size() <= 0) {
			return new ChartData();
		}
		List<String> aisxName = new ArrayList<String>();
		List<SeriesLong> series = new ArrayList<SeriesLong>();
		// 有多少个map 就有多少个SeriesLong
		for (Entry<String, List<CharsetBeanLong>> entry : map.entrySet()) {
			List<Long> data = new ArrayList<Long>();
			// 组合图表 id=Series 名称组,xname=下面要显示的名称__x_x
			for (CharsetBeanLong c : entry.getValue()) {
				if (StringUtils.isNotBlank(c.getxName())) {
					aisxName.add(c.getxName());
				} else {
					aisxName.add("x_name为空");
				}
				if (c.getCount() == null) {
					c.setCount(0L);
				}
				data.add(c.getCount());
			}
			SeriesLong s = new SeriesLong();
			s.setName(entry.getKey());
			s.setData(data);
			series.add(s);
		}
		// 根据数据组装图表数据
		ChartData chartData = new ChartData();
		chartData.setAisxName(aisxName);
		chartData.setSeriesLong(series);
		return chartData;
	}

	/**
	 * 按vo条件中的日期，和数据结合，日期中没有数据并补0处理,
	 * <p>
	 * 会按照CharsetBeanCommon中定义的id和xName进行归类分组并按日期排序(从小到大)处理， 最后转换成图表对象
	 * 
	 * @date 2017/03/07
	 * 
	 * @param vo
	 *            条件
	 * @param lists
	 *            数据集合
	 * @param charsetType
	 *            图表类型
	 * @return
	 */
	public static ChartData commonBeanToCharsetCommonToDateHandler(QueryCommBean vo, List<CharsetBeanCommon> comBeans,
			String charsetType, String timeType) {
		if (comBeans == null || comBeans.size() <= 0) {
			return new ChartData();
		}
		Map<String, List<CharsetBeanCommon>> map = ListConverMap.beanConverMapToObject_to_date(vo, comBeans,
				charsetType, timeType);
		List<String> aisxName = new ArrayList<String>();
		List<SeriesCommon> series = new ArrayList<SeriesCommon>();
		// 有多少个map 就有多少个Series
		int i = 0;
		for (Entry<String, List<CharsetBeanCommon>> entry : map.entrySet()) {
			List<Object> data = new ArrayList<>();
			// 组合图表 id=Series 名称组,xname=下面要显示的名称__x_x
			String tempType = "";
			Integer tempYType = null;
			for (CharsetBeanCommon c : entry.getValue()) {
				// TODO:横轴不一至的情况？
				if (i == 0) {// 只取第一个日期，因为上面已经处理了不齐日期的情况，和排序的情误解，此方法只针对日期生效
					if (StringUtils.isNotBlank(c.getxName())) {
						aisxName.add(c.getxName());
					} else {
						aisxName.add("x_name为空");
					}
				}
				if (c.getCount() == null) {
					c.setCount(0L);
				}
				data.add(c.getCount());
				// 以最后一个为准
				tempYType = c.getyAxis();
				tempType = c.getType();
			}
			i++;
			SeriesCommon s = new SeriesCommon();
			s.setName(entry.getKey());// key为id分组标识
			s.setData(data);
			s.setType(tempType);
			s.setyAxis(tempYType);
			series.add(s);
		}
		// 根据数据组装图表数据
		ChartData chartData = new ChartData();
		chartData.setAisxName(aisxName);
		chartData.setSeriesCommon(series);
		return chartData;
	}

	/**
	 * 按vo条件中的日期，和数据结合，日期中没有数据并补0处理,
	 * <p>
	 * 会按照CharsetBeanCommon中定义的id和xName进行归类分组并按日期排序(从小到大)处理， 最后转换成图表对象
	 * <p>
	 * id=日期的情况，日期不为xname,而作为底图标识时
	 * <p>
	 * 日期在下面显示的时候用些方法
	 * 
	 * @date 2017/09/26
	 * 
	 * @param vo
	 *            条件
	 * @param lists
	 *            数据集合
	 * @param charsetType
	 *            图表类型
	 * @return
	 */
	public static ChartData commonBeanToCharsetCommonToDateHandlerV2ToId(QueryCommBean vo,
			List<CharsetBeanCommon> comBeans, String charsetType, String timeType) {
		if (comBeans == null || comBeans.size() <= 0) {
			return new ChartData();
		}
		// 按日期排序，补0
		Map<String, List<CharsetBeanCommon>> map = ListConverMap.beanConverMapToObject_to_date_toidEqDt(vo, comBeans,
				charsetType, timeType);
		List<String> aisxName = new ArrayList<String>();
		List<SeriesCommon> series = new ArrayList<SeriesCommon>();

		// dt={list=[1,2,3,4]}
		Map<String, List<Object>> dt_dt_array = new LinkedHashMap<>();
		// 先来一次硬转换
		for (Entry<String, List<CharsetBeanCommon>> entry : map.entrySet()) {
			aisxName.add(entry.getKey());// 非日期，
			// 转 list
			for (CharsetBeanCommon c : entry.getValue()) {
				if (dt_dt_array.containsKey(c.getxName())) {// 如果日期相同，则保存为一个数组
					List<Object> list_t = dt_dt_array.get(c.getxName());
					list_t.add(c.getCount());
					continue;
				}
				// 不存在,新加
				List<Object> list_t = new ArrayList<>();
				list_t.add(c.getCount());
				dt_dt_array.put(c.getxName(), list_t);
			}
		}
		// 组合SeriesCommon
		for (Entry<String, List<Object>> entry : dt_dt_array.entrySet()) {
			SeriesCommon s = new SeriesCommon();
			s.setName(entry.getKey());// 日期
			s.setData(entry.getValue());// 数据
			series.add(s);
		}
		// 根据数据组装图表数据
		ChartData chartData = new ChartData();
		chartData.setAisxName(aisxName);
		chartData.setSeriesCommon(series);
		return chartData;
	}

	/**
	 * 常用报表bean转换为图表功能,支持所有类型
	 * <p>
	 * 根据通用bean组件定义，来组合图表对象
	 * <p>
	 * demo {@link } com.uc.db.business.x8.action.social.AttentionFansAction
	 * 
	 * @date 2016/10/26
	 * @param comBeans
	 * @return
	 */
	public static ChartData commonBeanToCharsetCommon(List<CharsetBeanCommon> comBeans) {
		if (comBeans == null || comBeans.size() <= 0) {
			return new ChartData();
		}
		// 按照id去分组=SeriesLong
		// 转map
		Map<String, List<CharsetBeanCommon>> map = new LinkedHashMap<String, List<CharsetBeanCommon>>();
		for (CharsetBeanCommon c : comBeans) {
			// 如果有，则取出来，累加
			if (map.containsKey(c.getId())) {
				List<CharsetBeanCommon> listbean = map.get(c.getId());
				listbean.add(c);
				// ? t
			} else {// 没有新加
				List<CharsetBeanCommon> listbean = new ArrayList<>();
				listbean.add(c);
				map.put(c.getId(), listbean);
			}
		}
		if (map.size() <= 0) {
			return new ChartData();
		}
		List<String> aisxName = new ArrayList<String>();
		List<SeriesCommon> series = new ArrayList<SeriesCommon>();
		// 有多少个map 就有多少个SeriesLong
		for (Entry<String, List<CharsetBeanCommon>> entry : map.entrySet()) {
			List<Object> data = new ArrayList<>();
			// 组合图表 id=Series 名称组,xname=下面要显示的名称__x_x
			String tempType = "";
			Integer tempYType = null;
			for (CharsetBeanCommon c : entry.getValue()) {
				// TODO:横轴不一至的情况？
				if (StringUtils.isNotBlank(c.getxName())) {
					aisxName.add(c.getxName());
				} else {
					aisxName.add("x_name为空");
				}
				if (c.getCount() == null) {
					c.setCount(0L);
				}
				data.add(c.getCount());
				// 以最后一个为准
				tempYType = c.getyAxis();
				tempType = c.getType();
			}
			SeriesCommon s = new SeriesCommon();
			s.setName(entry.getKey());// key为id分组标识
			s.setData(data);
			s.setType(tempType);
			s.setyAxis(tempYType);
			series.add(s);
		}
		// 根据数据组装图表数据
		ChartData chartData = new ChartData();
		chartData.setAisxName(aisxName);
		chartData.setSeriesCommon(series);
		return chartData;
	}

	/**
	 * 常用报表bean转换为图表功能 doble
	 * <p>
	 * 根据通用bean组件定义，来组合图表对象
	 * <p>
	 * demo {@link } com.uc.db.business.x8.action.social.AttentionFansAction
	 * 
	 * @date 2016/10/26
	 * @param comBeans
	 * @return
	 */
	public static ChartData commonBeanToCharsetDouble(List<CharsetBeanDobule> comBeans) {
		if (comBeans == null || comBeans.size() <= 0) {
			return new ChartData();
		}
		// 按照id去分组=SeriesLong
		// 转map
		Map<String, List<CharsetBeanDobule>> map = new LinkedHashMap<String, List<CharsetBeanDobule>>();
		for (CharsetBeanDobule c : comBeans) {
			// 如果有，则取出来，累加
			if (map.containsKey(c.getId())) {
				List<CharsetBeanDobule> listbean = map.get(c.getId());
				listbean.add(c);
				// ? t
			} else {// 没有新加
				List<CharsetBeanDobule> listbean = new ArrayList<CharsetBeanDobule>();
				listbean.add(c);
				map.put(c.getId(), listbean);
			}
		}
		if (map.size() <= 0) {
			return new ChartData();
		}
		List<String> aisxName = new ArrayList<String>();
		List<SeriesDouble> series = new ArrayList<SeriesDouble>();
		// 有多少个map 就有多少个SeriesLong
		for (Entry<String, List<CharsetBeanDobule>> entry : map.entrySet()) {
			List<Double> data = new ArrayList<Double>();
			// 组合图表 id=Series 名称组,xname=下面要显示的名称__x_x
			for (CharsetBeanDobule c : entry.getValue()) {
				if (StringUtils.isNotBlank(c.getxName())) {
					aisxName.add(c.getxName());
				} else {
					aisxName.add("x_name为空");
				}
				if (c.getCount() == null) {
					c.setCount(0D);
				}
				data.add(c.getCount());
			}
			SeriesDouble s = new SeriesDouble();
			s.setName(entry.getKey());
			s.setData(data);
			series.add(s);
		}
		// 根据数据组装图表数据
		ChartData chartData = new ChartData();
		chartData.setAisxName(aisxName);
		chartData.setSeriesdouble(series);
		return chartData;
	}

	public static void main(String[] args) throws ParseException, InstantiationException, IllegalAccessException {
		Map<String, Long> map = getDayIntervalToLongOrderByDesc("2016-11-1", "2016-11-10", long.class);
		System.out.println(map);
		// System.out.println(Double.class.isInstance(Double.class));
		// System.out.println(Double.class == Double.class);
		// System.out.println(Double.class == double.class);
		// System.out.println(Double.class.equals(double.class));
		//
		// System.out.println(Double.class);
		// System.out.println(double.class);

		// System.out.println(CharsetDataUtil.class.newInstance());

	}
}
