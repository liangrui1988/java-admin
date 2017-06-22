package com.huiwan.gdata.common.utils.date;

import static java.util.Calendar.DATE;
import static java.util.Calendar.DAY_OF_MONTH;
import static java.util.Calendar.DAY_OF_WEEK;
import static java.util.Calendar.HOUR_OF_DAY;
import static java.util.Calendar.MILLISECOND;
import static java.util.Calendar.MINUTE;
import static java.util.Calendar.MONTH;
import static java.util.Calendar.SECOND;
import static java.util.Calendar.WEEK_OF_YEAR;
import static java.util.Calendar.YEAR;

import java.text.DateFormat;
import java.text.DecimalFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 日期工具类
 */
public class DateUtils {
	public static final long MILLIS_PER_SECOND = 1000L;
	private static final String DEFAULT_FORMAT = "yyyy-MM-dd HH:mm:ss";
	private static final Logger log = LoggerFactory.getLogger(DateUtils.class);
	public static final long MILLIS_PER_DAY = 24*60*60*1000L;
	public static final long MILLIS_PER_MINUTE = 60*1000L;
	

	/**
	 * 将时间转换成‘yyyy-MM-dd HH24:mm ’格式的字符串
	 * @param date
	 * @return
	 */
	public static String YMDHMToString(Date date){
		if (date == null) {
			return "";
		}
		DateFormat dateformat = new SimpleDateFormat(DEFAULT_FORMAT);
		return dateformat.format(date);
	}
	/**
	 * 将时间转化成 "yyyy-MM-dd"格式的字符串
	 * @param date
	 * @return
	 */
	public static String YMDToString(Date date){
		SimpleDateFormat dateformat = new SimpleDateFormat("yyyy-MM-dd");
		return dateformat.format(date);
	}
	
	/**
	 * 将时间转化成 "yyyy-MM-dd HH"格式的字符串
	 * @param date
	 * @return
	 */
	public static String YMDHToString(Date date){
		SimpleDateFormat dateformat = new SimpleDateFormat("yyyy-MM-dd HH");
		return dateformat.format(date);
	}
	
	/**
	 * 检查当前时间和指定时间是否同一周
	 * 
	 * @param  year 			年
	 * @param  week 			周
	 * @param  firstDayOfWeek 	周的第一天设置值，{@link Calendar#DAY_OF_WEEK}
	 * @return {@link Boolean}	true-同一周, false-不同周
	 */
	public static boolean isSameWeek(int year, int week, int firstDayOfWeek) {
		Calendar cal = Calendar.getInstance();
		cal.setFirstDayOfWeek(firstDayOfWeek);
		return year == cal.get(YEAR) && week == cal.get(WEEK_OF_YEAR);
	}
	
	/**
	 * 检查当前时间和指定时间是否同一周
	 * 
	 * @param  time 			被检查的时间
	 * @param  firstDayOfWeek 	周的第一天设置值，{@link Calendar#DAY_OF_WEEK}
	 * @return {@link Boolean}	true-同一周, false-不同周
	 */
	public static boolean isSameWeek(Date time, int firstDayOfWeek) {
		Calendar cal = Calendar.getInstance();
		cal.setTime(time);
		cal.setFirstDayOfWeek(firstDayOfWeek);
		return isSameWeek(cal.get(YEAR), cal.get(WEEK_OF_YEAR), firstDayOfWeek);
	}
	
	/**
	 * 获取周的第一天
	 * @param  firstDayOfWeek 	周的第一天设置值，{@link Calendar#DAY_OF_WEEK}
	 * @param  time 			指定时间，为 null 代表当前时间
	 * @return {@link Date}		周的第一天
	 */
	public static Date firstTimeOfWeek(int firstDayOfWeek, Date time) {
		Calendar cal = Calendar.getInstance();
		if (time != null) {
			cal.setTime(time);
		}
		cal.setFirstDayOfWeek(firstDayOfWeek);
		int day = cal.get(DAY_OF_WEEK);
		
		if (day == firstDayOfWeek) {
			day = 0;
		} else if (day < firstDayOfWeek) {
			day = day + (7 - firstDayOfWeek);
		} else if (day > firstDayOfWeek) {
			day = day - firstDayOfWeek;
		}
		
		cal.set(HOUR_OF_DAY, 0);
		cal.set(MINUTE, 0);
		cal.set(SECOND, 0);
		cal.set(MILLISECOND, 0);
		
		cal.add(DATE, -day);
		return cal.getTime();
	}
	
	/**
	 * 检查指定日期是否今天(使用系统时间)
	 * @param date 被检查的日期
	 * @return
	 */
	public static boolean isToday(Date date) {
		Calendar cal = Calendar.getInstance();
		cal.add(DATE, 1);
		cal.set(HOUR_OF_DAY, 0);
		cal.set(MINUTE, 0);
		cal.set(SECOND, 0);
		cal.set(MILLISECOND, 0);
		Date end = cal.getTime(); // 明天的开始
		cal.add(MILLISECOND, -1);
		cal.add(DATE, -1);
		Date start = cal.getTime(); // 昨天的结束
		return date.after(start) && date.before(end);
	}

	/**
	 * 日期转换成字符串格式
	 * @param theDate 待转换的日期
	 * @param datePattern 日期格式
	 * @return 日期字符串
	 */
	public static String date2String(Date theDate, String datePattern) {
		if (theDate == null) {
			return "";
		}

		DateFormat format = new SimpleDateFormat(datePattern);
		try {
			return format.format(theDate).trim();
		} catch (Exception e) {
			return "";
		}
	}

	/**
	 * 字符串转换成日期格式
	 * 
	 * @param  dateString 		待转换的日期字符串
	 * @param  datePattern 		日期格式
	 * @return {@link Date}		转换后的日期
	 */
	public static Date string2Date(String dateString, String datePattern) {
		if (dateString == null || dateString.trim().isEmpty()) {
			return null;
		}

		DateFormat format = new SimpleDateFormat(datePattern);
		try {
			return format.parse(dateString);
		} catch (ParseException e) {
			log.error("ParseException in Converting String to date: " + e.getMessage());
		}

		return null;

	}
	
	/**
	 * 把当前时间戳转字符串
	 * 
	 * @param format
	 *            时间格式
	 */
	public static final String time2str(String format) {
		return time2str(System.currentTimeMillis(), format);
	}

	/**
	 * 时间戳转字符串
	 * 
	 * @param timestamp
	 *            时间戳
	 * @param format
	 *            时间格式
	 */
	public static final String time2str(long timestamp, String format) {
		return new SimpleDateFormat(format).format(timestamp);
	}
	
	/**
	 * 字符串转时间戳
	 * 
	 * @param str
	 *            字符串
	 * @param format
	 *            时间格式
	 * @param isMicrotime
	 *            是否输出毫秒
	 */
	public static final long str2time(String str, String format, boolean isMicrotime) {
		if (str == null || "".equals(str)) {
			return -1;
		}
		try {
			if (isMicrotime) {
				return new SimpleDateFormat(format).parse(str).getTime();
			}
			return new SimpleDateFormat(format).parse(str).getTime() / 1000;
		} catch (Exception e) {
			return -1;
		}
	}

	/**
	 * 字符串转时间戳
	 * 
	 * @param str
	 *            字符串
	 * @param format
	 *            时间格式
	 */
	public static final int str2time(String str) {
		return (int) str2time(str, DEFAULT_FORMAT, false);
	}
	
	/**
	 * 字符串转时间戳
	 * 
	 * @param str
	 *            字符串
	 * @param isMicrotime
	 *            是否输出毫秒
	 */
	public static final long str2time(String str, boolean isMicrotime) {
		return str2time(str, DEFAULT_FORMAT, isMicrotime);
	}

	/**
	 * 字符串转时间戳
	 * 
	 * @param str
	 *            字符串
	 * @param format
	 *            时间格式
	 */
	public static final int str2time(String str, String format) {
		return (int) str2time(str, format, false);
	}

	/**
	 * 把秒数转换成把毫秒数
	 * 
	 * @param  seconds		秒数的数组
	 * @return {@link Long} 毫秒数
	 */
	public static long toMillisSecond(long...seconds) {
		long millis = 0L;
		if(seconds != null && seconds.length > 0) {
			for (long time : seconds) {
				millis += (time * 1000);
			}
		}
		return millis;
	}
	
	
	

	/**
	 * 距离现在的月数，
	 * @param len 0：当前月，-1 上一个月，1下一个月 其它类推
	 * @param datePattern 格式化样式
	 * @return 计算后的格式化串
	 */
	
	public static String changeDateToString( int len,String datePattern ){
		Date month=new Date();
		if(len!=0){
			month=changeDateOfMonth(month, len);
		} 
		return DateUtils.date2String(month, datePattern);
	}
	

	/**
	 * 修改日期
	 * @param theDate 待修改的日期
	 * @param addYeas 加减的年数
	 * @return 修改后的日期
	 */
	
	public static Date changeDateOfYear(Date theDate,int addYeas){
		return changeDateOfMonth(theDate,addYeas*12);
	}
	
	/**
	 * 修改日期
	 * @param theDate 待修改的日期
	 * @param addMonth 加减的月数
	 * @return 修改后的日期
	 */
	public static Date changeDateOfMonth(Date theDate,int addMonth){
		if (theDate == null) {
			return null;
		}

		Calendar cal = Calendar.getInstance();
		cal.setTime(theDate);
		
		cal.add(MONTH, addMonth);
		return cal.getTime();
	}
	
	/**
	 * 修改日期
	 * @param theDate 待修改的日期
	 * @param addDays 加减的天数
	 * @param hour 设置的小时
	 * @param minute 设置的分
	 * @param second 设置的秒
	 * @return 修改后的日期
	 */
	public static Date changeDateTime(Date theDate, int addDays, int hour, int minute, int second) {
		if (theDate == null) {
			return null;
		}

		Calendar cal = Calendar.getInstance();
		cal.setTime(theDate);

		cal.add(DAY_OF_MONTH, addDays);

		if (hour >= 0 && hour <= 24) {
			cal.set(HOUR_OF_DAY, hour);
		}
		if (minute >= 0 && minute <= 60) {
			cal.set(MINUTE, minute);
		}
		if (second >= 0 && second <= 60) {
			cal.set(SECOND, second);
		}

		return cal.getTime();
	}
	
	public static Date add(Date theDate, int addHours, int addMinutes, int addSecond) {
		if (theDate == null) {
			return null;
		}

		Calendar cal = Calendar.getInstance();
		cal.setTime(theDate);

		cal.add(HOUR_OF_DAY, addHours);
		cal.add(MINUTE, addMinutes);
		cal.add(SECOND, addSecond);

		return cal.getTime();
	}

	/**
	 * 取得几
	 * @param theDate
	 * @return
	 */
	public static int dayOfWeek(Date theDate) {
		if (theDate == null) {
			return -1;
		}

		Calendar cal = Calendar.getInstance();
		cal.setTime(theDate);

		return cal.get(DAY_OF_WEEK);
	}
	
	public static int getDayOfMonth(Date date) {
		if (date == null) {
			return -1;
		}
		Calendar cal = Calendar.getInstance();
		cal.setTime(date);
		return cal.get(DAY_OF_MONTH);
	}

	/**
	 * 获得某一时间的0点
	 * @param theDate 需要计算的时间
	 */
	public static Date getDate0AM(Date theDate) {
		if (theDate == null) {
			return null;
		}

		Calendar calendar = Calendar.getInstance();
		calendar.setTime(theDate);
		calendar.set(HOUR_OF_DAY, 0);
		calendar.set(MINUTE, 0);
		calendar.set(SECOND, 0);
		calendar.set(MILLISECOND, 0);
		return calendar.getTime();
	}
	
	

	/**
	 * 获得指定日期的23点59分59秒的时间
	 * @param theDate 需要计算的时间
	 */
	public static Date getThisDay2359PM(Date theDate) {
		if (theDate == null) {
			return null;
		}

		Calendar calendar = Calendar.getInstance();
		calendar.setTime(theDate);
		calendar.set(HOUR_OF_DAY, 23);
		calendar.set(MINUTE, 59);
		calendar.set(SECOND, 59);
		calendar.set(MILLISECOND, 0);
		return calendar.getTime();
	}

	/**
	 * 获得当天的指定时间点
	 * @param theDate 需要计算的时间
	 */
	public static Date getSelectTime(Date theDate,int hour,int minute,int second) {
		if (theDate == null) {
			return null;
		}

		if(hour < 0 || minute < 0 || second < 0){
			return null;
		}
		
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(theDate);
		calendar.set(HOUR_OF_DAY, hour);
		calendar.set(MINUTE, minute);
		calendar.set(SECOND, second);
		calendar.set(MILLISECOND, 0);
		return calendar.getTime();
	}
	/**
	 * 获得当天的0点0分0秒
	 * @param theDate 需要计算的时间
	 */
	public static long getSelectTime(Date theDate) {
		if (theDate == null) {
			return System.currentTimeMillis();
		}
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(theDate);
		calendar.set(HOUR_OF_DAY, 0);
		calendar.set(MINUTE, 0);
		calendar.set(SECOND, 0);
		calendar.set(MILLISECOND, 0);
		return calendar.getTime().getTime();
	}


	/**
	 * 返回两个时间之间的秒差数
	 * @param startDate
	 * @param endDate
	 * @return
	 */
	public static int cal2DateTDOAHours(Date startDate,Date endDate){
		if (startDate == null || endDate == null) {
			return 0;
		}
		long startTime = startDate.getTime()/1000;
		long endTime = endDate.getTime()/1000;
		int v1 =(int) (endTime - startTime);
		return v1;
	}

	
	/**
	 * 获得获得改变后的时间
	 * 
	 * @param 	addDay			增加的天数(减少天数, 则传负数)
	 * @param   to0AM			是否取0点时间
	 * @return 
	 */
	public static Date add(int addDay, boolean to0AM) {
		Calendar calendar = Calendar.getInstance();
		calendar.add(DATE, addDay);
		Date time = calendar.getTime();
		return to0AM ? getDate0AM(time) : time;
	}
	

	
	/**
	 * 获取该日期在当前年中的第几周
	 * @param date
	 * @return int 
	 */
	public static int getWeekOfYear(Date date) {
		Calendar c = Calendar.getInstance();
        c.setFirstDayOfWeek(Calendar.MONDAY);
        c.setMinimalDaysInFirstWeek(7);
        c.setTime(date);
        return c.get(Calendar.WEEK_OF_YEAR);
	}
	
	/**
	 * 获取该日期所在年份的最后一周
	 * @param date
	 * @return int 
	 */
	public static int getLastWeekOfYear(Date date){
		Calendar c = Calendar.getInstance();
		c.setTime(date);
		int year = c.get(Calendar.YEAR);
        c.set(year, Calendar.DECEMBER, 31, 23, 59, 59);
		return getWeekOfYear(c.getTime());
	}
	/**
	 * 对天数进行加减运算
	 * 
	 * @param date
	 *            原来的时间
	 * @param days
	 *            正数为加,负数为减
	 * @return 返回运算后的时间
	 */
	public static Date addDay(final Date date, final Integer days) {
		final Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		calendar.add(Calendar.DATE, days);
		return calendar.getTime();
	}
	/**
	 * 获取日期, 以参考日期date为标准进行日期增减。
	 * @param offset 日期偏移量，昨天是－1
	 * @return  yyyy-MM-dd
	 */
	public static String yyyy_MM_dd(final int offset) {
		final Calendar cal = Calendar.getInstance();
		cal.add(Calendar.DAY_OF_MONTH, offset);
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
		return format.format(cal.getTime());
	}
	/**
	 * 毫秒转换成日期对象
	 * @param Millis
	 * @return Date
	 */
	public static Date toDate(final long Millis) {
		final Calendar clendar = Calendar.getInstance();
		clendar.setTimeInMillis(Millis);

		return clendar.getTime();
	}
	
	/**
	 * 将指定的天数日期(2014-7025)转
	 * @param date
	 * @return
	 */
	public static int toIntDate(Date date){
		Date d  = getSelectTime(date, 0, 0, 0);
		int time  = 1000 * 60 * 60 * 24;
		long ints =  d.getTime()/time;
		return (int)ints;
	}
	
	/**
	 * 只有在热门男女中用到的时间运行;
	 * 数据是零辰2点开始统计;所以获取当天数据时,只能获取大于当天3点的数据,否则取昨天的数据
	 * @return
	 */
	public static int intSexDate(){
		final int OPUS_STARE_TIME = 2; 
		final int OPUS_YESRTERDAY = -1;
		final int time  = 1000 * 60 * 60 * 24;
		
		Calendar calendar = Calendar.getInstance();
		//数据是零辰2点开跑;当天只能,大于3点才可以取当天的数据,偶尔取昨天的数据
		if( calendar.get(Calendar.HOUR_OF_DAY) < OPUS_STARE_TIME){ //取昨天的数据
			calendar.add(Calendar.DAY_OF_MONTH, OPUS_YESRTERDAY);
		}
		calendar.set(HOUR_OF_DAY, 0);
		calendar.set(MINUTE, 0);
		calendar.set(SECOND, 0);
		calendar.set(MILLISECOND, 0);
		long ints =  calendar.getTimeInMillis()/time;
		return (int)ints;
	}
	/**
	 * 根据时间字符串返回Date对象
	 * 
	 * @param dateStr
	 *            ,可以接受3种格式分别是:yyyy-MM-dd,yyyy-MM-dd HH:mm,yyyy-MM-dd HH:mm:ss,yyyy
	 * @return
	 */
	public static Date getDateByStr(final String dateStr) {
		SimpleDateFormat formatter = null;
		if (dateStr.length() == 10) {
			formatter = new SimpleDateFormat("yyyy-MM-dd");
		} else if (dateStr.length() == 8) {
			formatter = new SimpleDateFormat("HH:mm:ss");
		} else if (dateStr.length() == 16) {
			formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm");
		} else if (dateStr.length() == 19) {
			formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		} else if (dateStr.indexOf(".") > 0) {
			formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS");
		} else if(dateStr.length() == 4){
			formatter = new SimpleDateFormat("yyyy");
		}else{
			System.out.println("日期字符串格式错误!");
			return null;
		}
		try {
			return formatter.parse(dateStr);
		} catch (final ParseException e) {
			return null;
		}
	}
	/**
	 * 根据字符串格式返回相应日期
	 * 
	 * @param dateStr
	 *            dateStr字符串对象
	 * @param format
	 *            例如:yyyy-MM-dd HH:mm:ss
	 * @return
	 */
	public static Date getDateByStr(final String dateStr, final String format) {
		final SimpleDateFormat formatter = new SimpleDateFormat(format);
		try {
			return formatter.parse(dateStr);
		} catch (final ParseException e) {
			return null;
		}
	}
	
	/**
	 * 寻找最合适的单位来显示时间
	 * 
	 * @author ruan 2013-7-21
	 * @param time
	 * @return
	 */
	public static final String showTime(long time) {
		String str = "";
		if (time > 0 && time <= 1000) {
			str = time + " ns";
		} else if (time > 1000 && time <= 1000000) {
			str = new DecimalFormat("0.00").format(time / 1000.0) + " μs";
		} else if (time > 1000000 && time <= 1000000000) {
			str = new DecimalFormat("0.00").format(time / 1000000.0) + " ms";
		} else {
			str = new DecimalFormat("0.00").format(time / 1000000000.0) + " s";
		}
		return str;
	}
	
	/**
	 * 把时间转成 n小时前 n天前 那种形式
	 * @author ruan
	 * @param time
	 * @return
	 */
	public static final String timeBeforeStr(int time) {
		int now = (int) (System.currentTimeMillis() / 1000);
		int rest = now - time;
		if(rest < 60) {
			return "刚刚";
		}
		if(rest >= 60 && rest < 3600) {
			return ((int)(rest / 60)) + "分钟前";
		}
		if(rest >= 3600 && rest < 86400) {
			return ((int)(rest / 3600)) + "小时前";
		}
		if(rest >= 86400 && rest < 172800) {
			String dateString = date2String(new Date(time * 1000L), "HH:mm");
			return "昨天" + (dateString);
		}
		if(rest >= 172800) {
			String thisYear = date2String(new Date(), "YYYY");
			String date = date2String(new Date(time * 1000L), "YYYY-MM-dd HH:mm");
			if(!thisYear.equals(date.split("-")[0])) {
				return date;
			}
			return date.substring(date.indexOf("-") + 1);
		}
		return null;
	}
	
	/**
	 * 把当前时间格式化成yyyy-MM-dd HH:mm:ss
	 * 
	 * @return String
	 */
	public static final String date() {
		return date(DEFAULT_FORMAT);
	}

	/**
	 * 把当前时间格式化
	 * 
	 * @param format
	 * @return String
	 */
	public static final String date(String format) {
		return date(format, System.currentTimeMillis());
	}

	/**
	 * 把时间戳（秒）格式化成yyyy-MM-dd HH:mm:ss
	 * 
	 * @param timestamp
	 * @return String
	 */
	public static final String date(int timestamp) {
		return date(DEFAULT_FORMAT, timestamp);
	}

	/**
	 * 把时间戳（毫秒）格式化成yyyy-MM-dd HH:mm:ss
	 * 
	 * @param timestamp
	 * @return String
	 */
	public static final String date(long timestamp) {
		return date(DEFAULT_FORMAT, timestamp);
	}

	/**
	 * 把时间戳格式化
	 * 
	 * @param format
	 * @param timestamp
	 *            秒
	 * @return String
	 */
	public static final String date(String format, int timestamp) {
		return date(format, timestamp * 1000l);
	}

	/**
	 * 把时间戳格式化
	 * 
	 * @param format
	 * @param timestamp
	 *            毫秒
	 * @return String
	 */
	public static final String date(String format, long timestamp) {
		return new SimpleDateFormat(format).format(timestamp);
	}
	
	/**
	 * 获取当月最后一天是多少号
	 * @author ruan
	 */
	public static int getLastDayOfMonth() {
		Calendar cDay1 = Calendar.getInstance();
		cDay1.setTimeInMillis(System.currentTimeMillis());
		return cDay1.getActualMaximum(Calendar.DAY_OF_MONTH);
	}
	
	/**
	 * 获取指定月份最后一天 
	 * @author ruan
	 */
	public static int  getLastDayOfMonth(int year,int month,int date) {
		Calendar calendar = Calendar.getInstance();
		calendar.set(year, month-1, date);
		return calendar.getActualMaximum(Calendar.DAY_OF_MONTH);
	}
	
	/**
	 * 获取指定月份最后一天 
	 * @author ruan
	 */
	public static int  getLastDayOfMonth(Date date) {
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		return calendar.getActualMaximum(Calendar.DAY_OF_MONTH);
	}
	
	public static Date getFistDateOfWeek(Date date){
		int i=DateUtils.dayOfWeek(date)-1;//星期一算第一天
		if(i==0)i=7;//星期天算最后一天，第7天
		return DateUtils.addDay(date, 1-i);
	}
	
	public static Date getLastDateOfWeek(Date date){
		int i=DateUtils.dayOfWeek(date)-1;//星期一算第一天
		if(i==0)i=7;//星期天算最后一天，第7天
		return DateUtils.addDay(date, 7-i);
	}
	
	
	/**
	 * author:nickluo
	 * @param month
	 * @return
	 */
	public static String getFirstDateStrOfMonth(int month){
		
		
		Calendar calendar = Calendar.getInstance();
		
		calendar.set(MONTH,month);
		Date nowDate = calendar.getTime();
		Date firstDate = getFistDateOfMonth(nowDate);
		
		String firstDateStr = YMDToString(firstDate);
		
		return firstDateStr+" 00:00:00";
	}
	
	/**
	 * author:nickluo
	 * @param month
	 * @return
	 */
	public static String getLastDateStrOfMonth(int month){
		
		
		Calendar calendar = Calendar.getInstance();
		
		calendar.set(MONTH,month);
		Date nowDate = calendar.getTime();
		Date lastDate = getLastDateOfMonth(nowDate);
		
		String lastDateStr = YMDToString(lastDate);
		
		return lastDateStr+" 23:59:59";
	}
	
	
	
	public static Date getFistDateOfMonth(Date date){
		int i=DateUtils.getLastDayOfMonth(date);
		int j=DateUtils.getDayOfMonth(date);
		return DateUtils.addDay(date, 1-j);
	}
   public static Date getLastDateOfMonth(Date date){
	   int i=DateUtils.getLastDayOfMonth(date);
	   int j=DateUtils.getDayOfMonth(date);
	   return DateUtils.addDay(date, i-j);
	}
   
	/**
	 * 获取指定月份前后一个月
	 * @author ruan
	 */
	public static String  getLastMonth(int year,int month,int date,int len) {
		Calendar calendar = Calendar.getInstance();
		calendar.set(year, month-1, date);
		Date dateTime=calendar.getTime();
		dateTime=changeDateOfMonth(dateTime, len);
		return DateUtils.date2String(dateTime, "yyyy-MM");
	}
	
	/**
	 * 把描述转换为  xx天xx小时xx分
	 * @param second
	 */
	public static String secondToStr(int second) {
		StringBuilder str = new StringBuilder();
		if (second <= 60) {
			str.append("1分钟");
		} else {
			if (second > 86400) {
				str.append(second / 86400);
				str.append("天");
				second = second % 86400;
			}
			else if (second > 3600) {
				str.append(second / 3600);
				str.append("小时");
				second = second % 3600;
			}
			else if (second > 60) {
				str.append(second / 60);
				str.append("分钟");
				second = second % 60;
			}
		}
		return str.toString();
	}	
	/**
	 * 判断当前时间是否为高峰期.
	 * @return
	 */
	public static boolean isHighTime(){
		  Date currentDate = new Date();
		  if (currentDate.getHours()>=20) 
	    	    return true;
		  return false;
	}

	/**
	 * 根据当前时间 获取 开始时间 到结束时间
	 * 
	 * @param day
	 *            获取几天之间的时间
	 * 
	 * @param otherDay
	 *            从前几天 开始
	 * 
	 * @return 开始时间 and 结束时间
	 */
	public static Date[] getDayBetween(Integer day, Integer otherDay) {

		// 今天整天日期
		Date startDt = new Date();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		String s = sdf.format(startDt.getTime());
		try {
			startDt = sdf.parse(s);
		} catch (ParseException e) {
			e.printStackTrace();
		}

		Calendar calendarStart = new GregorianCalendar();
		calendarStart.setTime(startDt);
		calendarStart.add(calendarStart.DATE, -7);
		startDt = calendarStart.getTime();

		Calendar calendar = new GregorianCalendar();
		calendar.setTime(new Date(startDt.getTime() - 1));
		calendar.add(calendar.DATE, 7);
		return new Date[] { startDt, calendar.getTime() };
	}
	

	/**
	 * 获娶近几周 周一的日期
	 * @param weekj
	 * @return
	 * @throws ParseException
	 */
	public static long getWeekDayTime(int weekj) throws ParseException{
		SimpleDateFormat sdf2=new SimpleDateFormat("yyyy-MM-dd");
		long now=System.currentTimeMillis();
		Calendar cal = Calendar.getInstance();
		int a = cal.get(Calendar.DAY_OF_WEEK) - 2;//-1
		int today = weekj * 7 + a;// 前三周的时间点
		long xx=today*86400000L;
	
		String strDate=sdf2.format(new Date(now-xx));
		Date date=sdf2.parse(strDate);
		return date.getTime();
		//return getHoruTime(weekj);
	}
	
	/**
	 * 当前小时
	 * 获娶近几小时  测试用    
	 * @param weekj
	 * @return
	 * @throws ParseException
	 */
	public static long getHoruTime(int hour) throws ParseException{
		SimpleDateFormat sdf2=new SimpleDateFormat("yyyy-MM-dd HH");
		long now=System.currentTimeMillis();
		String strDate=sdf2.format(new Date(now));
		Date date=sdf2.parse(strDate);
		long chour= date.getTime();
		hour=hour*60*60*1000;
		return chour-hour;
	}
	
	public static void main(String[] args) throws ParseException {
		
//		long cuh=getWeekDayTime(1);
//		SimpleDateFormat sdf2=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
//        System.out.println(cuh);
//        
//        sdf2.format(cuh);
//        System.out.println(sdf2.format(cuh));
        System.out.println(getLastDateOfMonth(string2Date("2016-01-20", "yyyy-MM-dd")));
        System.out.println(getLastDateOfMonth(string2Date("2016-01-01", "yyyy-MM-dd")));
        System.out.println(getLastDateOfMonth(string2Date("2015-12-20", "yyyy-MM-dd")));
        System.out.println(getLastDateOfMonth(string2Date("2015-12-31", "yyyy-MM-dd")));
	}
	
	
}
