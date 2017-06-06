package com.huiwan.gdata.modules.gdata.util;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 时间工具类
 * 
 * @author Kenmy E-mail:6e8@163.com
 * @date 创建时间：2012-7-23 下午06:13:41
 */
public class TimeUtil {

	static Logger log = LoggerFactory.getLogger(TimeUtil.class);

	private static DateFormat DF_YYYY() {
		return new SimpleDateFormat("yyyy");
	}

	private static DateFormat DF_HH() {
		return new SimpleDateFormat("HH");
	}

	private static DateFormat DF_MM() {
		return new SimpleDateFormat("mm");
	}

	private static DateFormat DF_YYYY_MM() {
		return new SimpleDateFormat("yyyy-MM");
	}

	private static DateFormat DF_YYYY_MM_DD() {
		return new SimpleDateFormat("yyyy-MM-dd");
	}

	private static DateFormat DF_YYYY_MM_DD_HH_MM_SS() {
		return new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	}

	private static DateFormat DF_YYYY_MM_DD_HH_MM() {
		return new SimpleDateFormat("yyyy-MM-dd HH:mm");
	}

	private static DateFormat DF_YYYYMMDDHH() {
		return new SimpleDateFormat("yyyyMMddHH");
	}

	private static DateFormat DF_YYYYMMDD() {
		return new SimpleDateFormat("yyyyMMdd");
	}

	private static DateFormat DF_YYYYMMDDHHMMSS() {
		return new SimpleDateFormat("yyyyMMddHHmmss");
	}

	/**
	 * 获取系统时间
	 * <ul>
	 * <li>sign=0 yyyy</li>
	 * <li>sign=1 yyyy-MM</li>
	 * <li>sign=2 yyyy-MM-dd</li>
	 * <li>sign=3 yyyy-MM-dd HH:mm:ss</li>
	 * <li>sign=4 HH 返回小时</li>
	 * <li>sign=5 MM 返回分钟</li>
	 * <li>sign=6 yyyy-MM-dd HH:mm</li>
	 * </ul>
	 * 
	 * @param sign
	 * @return
	 */
	public static String getNow(int sign) {
		Calendar now = Calendar.getInstance();
		switch (sign) {
		case 0:
			return DF_YYYY().format(now.getTime());
		case 1:
			return DF_YYYY_MM().format(now.getTime());
		case 2:
			return DF_YYYY_MM_DD().format(now.getTime());
		case 3:
			return DF_YYYY_MM_DD_HH_MM_SS().format(now.getTime());
		case 4:
			return DF_HH().format(now.getTime());
		case 5:
			return DF_MM().format(now.getTime());
		case 6:
			return DF_YYYY_MM_DD_HH_MM().format(now.getTime());
		default:
			return DF_YYYY_MM_DD().format(now.getTime());
		}
	}

	/**
	 * 获取系统时间
	 * <ul>
	 * <li>sign=0 yyyy</li>
	 * <li>sign=1 yyyy-MM</li>
	 * <li>sign=2 yyyy-MM-dd</li>
	 * <li>sign=3 yyyy-MM-dd HH:mm:ss</li>
	 * </ul>
	 * 
	 * @param sign
	 * @return string
	 */
	public static String dateFormat(Date dt, int sign) {
		switch (sign) {
		case 0:
			return DF_YYYY().format(dt);
		case 1:
			return DF_YYYY_MM().format(dt);
		case 2:
			return DF_YYYY_MM_DD().format(dt);
		case 3:
			return DF_YYYY_MM_DD_HH_MM_SS().format(dt);
		default:
			return DF_YYYY_MM_DD().format(dt);
		}
	}

	/**
	 * 获取系统时间
	 * <ul>
	 * <li>sign=0 yyyy</li>
	 * <li>sign=1 yyyy-MM</li>
	 * <li>sign=2 yyyy-MM-dd</li>
	 * <li>sign=3 yyyy-MM-dd HH:mm:ss</li>
	 * </ul>
	 * 
	 * @param sign
	 * @return string
	 */
	public static String dateFormat(String dt, int sign) {
		try {
			switch (sign) {
			case 0:
				return DF_YYYY().format(DF_YYYY().parse(dt).getTime());
			case 1:
				return DF_YYYY_MM().format(DF_YYYY_MM().parse(dt).getTime());
			case 2:
				return DF_YYYY_MM_DD().format(DF_YYYY_MM_DD().parse(dt).getTime());
			case 3:
				return DF_YYYY_MM_DD_HH_MM_SS().format(DF_YYYY_MM_DD_HH_MM_SS().parse(dt).getTime());
			default:
				return DF_YYYY_MM_DD().format(DF_YYYY_MM_DD().parse(dt).getTime());
			}
		} catch (Exception e) {
			log.error(e.getMessage());
		}
		return null;
	}

	/**
	 * 获取系统时间
	 * <ul>
	 * <li>sign=0 yyyy</li>
	 * <li>sign=1 yyyy-MM</li>
	 * <li>sign=2 yyyy-MM-dd</li>
	 * <li>sign=3 yyyy-MM-dd HH:mm:ss</li>
	 * </ul>
	 * 
	 * @param sign
	 * @return date
	 */
	public static Date dateFormat1(String dt, int sign) {
		try {
			switch (sign) {
			case 0:
				return DF_YYYY().parse(dt);
			case 1:
				return DF_YYYY_MM().parse(dt);
			case 2:
				return DF_YYYY_MM_DD().parse(dt);
			case 3:
				return DF_YYYY_MM_DD_HH_MM_SS().parse(dt);
			default:
				return DF_YYYY_MM_DD().parse(dt);
			}
		} catch (Exception e) {
			log.error(e.getMessage());
		}
		return null;
	}

	/**
	 * 时间格式转换
	 * 
	 * @param time
	 * @param flag
	 *            传入的时间格式：1、yyyyMMDD 2、yyyyMMddHHmmss 3、yyyy-MM-dd HH:mm:ss
	 * @return sign 1：yyyy-MM-dd 2:yyyy-MM-dd HH:mm:ss 3、yyyyMMddHHmmss
	 */
	public static String toDate(String time, int flag, int sign) {
		Calendar dt = Calendar.getInstance();
		try {
			if (flag == 1) {
				dt.setTime(DF_YYYYMMDD().parse(time));
			} else if (flag == 2) {
				dt.setTime(DF_YYYYMMDDHHMMSS().parse(time));
			} else if (flag == 3) {
				dt.setTime(DF_YYYY_MM_DD_HH_MM_SS().parse(time));
			}
		} catch (ParseException e) {
			log.error(e.getMessage());
		}
		if (sign == 2) {
			return DF_YYYY_MM_DD_HH_MM_SS().format(dt.getTime());
		} else if (sign == 3) {
			return DF_YYYYMMDDHHMMSS().format(dt.getTime());
		} else {
			return DF_YYYY_MM_DD().format(dt.getTime());
		}
	}

	/**
	 * 时间格式转换为yyyy-MM-dd
	 * 
	 * @param time
	 * @return
	 */
	public static String toDate(String time) {
		return toDate(time, 1, 1);
	}

	/**
	 * 根据传入日期获取年、月
	 * 
	 * @param time
	 *            yyyy-MM-dd
	 * @param flag
	 *            1、返回yyyy 2、返回yyyy-MM，3返回MM，4返回dd
	 * @return
	 */
	public static String getTime(String time, int flag) {
		Calendar dt = Calendar.getInstance();
		try {
			dt.setTime(DF_YYYY_MM_DD().parse(time));
		} catch (ParseException e) {
			log.error(e.getMessage());
		}
		switch (flag) {
		case 1:
			return DF_YYYY().format(dt.getTime());
		case 2:
			return DF_YYYY_MM().format(dt.getTime());
		case 3:
			return String.valueOf(dt.get(Calendar.MONTH) + 1);
		case 4:
			return String.valueOf(dt.get(Calendar.DATE));
		default:
			return DF_YYYY().format(dt.getTime());
		}
	}

	/**
	 * 时间转换为long型
	 * 
	 * @param time
	 * @param flag
	 *            <li>1、YYYY-MM-DD hh:mm:ss</li>
	 *            <li>2、YYYY-MM-DD hh:mm</li>
	 *            <li>3、YYYY-MM-DD</li>
	 *            <li>4、 YYYY-MM</li>
	 *            <li>5、YYYYMMDDHH</li>
	 * @return long
	 */
	public static long toDateLong(String time, int flag) {
		Calendar dt = Calendar.getInstance();
		time = time.trim();
		try {
			switch (flag) {
			case 1:
				dt.setTime(DF_YYYY_MM_DD_HH_MM_SS().parse(time));
				break;
			case 2:
				dt.setTime(DF_YYYY_MM_DD_HH_MM().parse(time));
				break;
			case 3:
				dt.setTime(DF_YYYY_MM_DD().parse(time));
				break;
			case 4:
				dt.setTime(DF_YYYY_MM().parse(time));
				break;
			case 5:
				dt.setTime(DF_YYYYMMDDHH().parse(time));
				break;
			}
		} catch (ParseException e) {
			log.error(e.getMessage());
		}
		return dt.getTimeInMillis() / 1000;
	}

	/**
	 * 时间转换为long型
	 * 
	 * @param time
	 *            yyyy-MM-dd HH:mm:ss
	 * @return
	 */
	public static long toDateLong(String time) {
		return toDateLong(time, 1);
	}

	/**
	 * long 转时间格式 yyyy-MM-dd HH:mm:ss
	 * 
	 * @param time
	 * @return yyyy-MM-dd HH:mm:ss
	 */
	public static String long2Date(long time) {
		java.util.Date dt = new Date(time * 1000);
		return DF_YYYY_MM_DD_HH_MM_SS().format(dt);
	}

	/**
	 * long 转时间格式 yyyy-MM-dd HH:mm:ss
	 * 
	 * @param time
	 * @return yyyy-MM-dd HH:mm:ss
	 */
	public static String long2Date(String time) {
		java.util.Date dt = new Date(StringUtil.toLong(time) * 1000);
		return DF_YYYY_MM_DD_HH_MM_SS().format(dt);
	}

	/**
	 * long 转时间格式
	 * 
	 * @param time
	 *            long
	 * @param flag
	 *            int
	 * @return 1 yyyy-MM-dd HH:mm:ss 2、yyyy-MM-dd
	 */
	public static String long2Date(long time, int flag) {
		java.util.Date dt = new Date(time * 1000);
		switch (flag) {
		case 1:
			return DF_YYYY_MM_DD_HH_MM_SS().format(dt);
		case 2:
			return DF_YYYY_MM_DD().format(dt);
		default:
			return DF_YYYY_MM_DD_HH_MM_SS().format(dt);
		}
	}

	/**
	 * 当前时间相加或相减天数返回
	 * 
	 * @param day
	 *            /-day
	 * @return yyyy-MM-dd
	 */
	public static String getDay(int day) {
		Calendar time = Calendar.getInstance();
		time.add(Calendar.DATE, day); // 当前时间相减去天数返回最新日期
		return DF_YYYY_MM_DD().format(time.getTime());
	}

	/**
	 * 时间相加
	 * 
	 * @param dt
	 * @param day
	 * @return
	 */
	public static String getDay(String dt, int day) {
		Calendar time = Calendar.getInstance();
		try {
			time.setTime(DF_YYYY_MM_DD().parse(dt));
		} catch (ParseException e) {
			log.error(e.getMessage());
		}
		time.add(Calendar.DATE, day); // 当前时间相减去天数返回最新日期
		return DF_YYYY_MM_DD().format(time.getTime());
	}

	/**
	 * 系统时间相减得到新的时间
	 * 
	 * @param min
	 * @return yyyy-MM-dd HH:mm:ss
	 */
	public static String getLowerTime(int min) {
		long l = new Date().getTime();// 当前时间的 毫秒数
		long s = min * 60 * 1000;// 300秒的 毫秒数
		Date date = new Date(l - s);// 减去300秒后 的 时间
		return DF_YYYY_MM_DD_HH_MM_SS().format(date.getTime());
	}

	/**
	 * 时间减去分钟得到新的时间
	 * 
	 * @param dt
	 * @param min
	 *            -为减，+为加
	 * @return
	 */
	public static String getLowerTime(String dt, int min) {
		Calendar time = Calendar.getInstance();
		try {
			time.setTime(DF_YYYY_MM_DD_HH_MM_SS().parse(dt));
		} catch (Exception e) {
			log.error(e.getMessage());
		}
		long l = time.getTimeInMillis();
		long s = min * 60 * 1000;
		Date date = new Date(l + s);
		return DF_YYYY_MM_DD_HH_MM_SS().format(date.getTime());
	}

	/**
	 * 两个日期相减得到相隔天数
	 * 
	 * @param dt1
	 * @param dt2
	 * @return dt2-dt1
	 */
	public static long getDayNum(String dt1, String dt2) {
		long nums = 0;
		try {
			Date _dt1 = DF_YYYY_MM_DD().parse(dt1);
			Date _dt2 = DF_YYYY_MM_DD().parse(dt2);
			nums = _dt2.getTime() - _dt1.getTime();
			nums = nums / 1000 / 60 / 60 / 24;
		} catch (Exception e) {
			log.error(e.getMessage());
		}
		if (nums < 0) {
			nums = -1;
		}
		return nums + 1;
	}

	/**
	 * 当前时间相加或相减小时返回
	 * 
	 * @param hour
	 *            /-hour
	 * @return yyyyMMddHH
	 */
	public static String getHourOfDay(int hour) {
		Calendar time = Calendar.getInstance();
		time.add(Calendar.HOUR_OF_DAY, hour); // 当前时间相减去天数返回最新日期
		return DF_YYYYMMDDHH().format(time.getTime());
	}

	/**
	 * 当前时间相加或相减小时返回
	 * 
	 * @param hour
	 *            /-hour
	 * @return yyyy-MM-dd HH
	 */
	public static String getHourOfDay(String dt, int hour) {
		Calendar time = Calendar.getInstance();
		try {
			time.setTime(DF_YYYY_MM_DD_HH_MM_SS().parse(dt));
		} catch (ParseException e) {
			log.error(e.getMessage());
		}
		time.add(Calendar.HOUR_OF_DAY, hour); // 当前时间相减去天数返回最新日期
		return DF_YYYY_MM_DD_HH_MM_SS().format(time.getTime());
	}

	/**
	 * 两个时间相减
	 * 
	 * @param dt1
	 *            yyyy-MM-dd HH:mm:ss
	 * @param dt2
	 *            yyyy-MM-dd HH:mm:ss
	 * @return dt2-dt1 秒
	 */
	public static long getLowerTime(String dt1, String dt2) {
		long time1 = toDateLong(dt1);
		long time2 = toDateLong(dt2);
		return time2 - time1;
	}

	/**
	 * 两个时间比较
	 * 
	 * @param dt1 YYYY-MM-DD hh:mm
	 * @param dt2 YYYY-MM-DD hh:mm
	 * @return dt2>dt1 true
	 */
	public static boolean compare2Time(String dt1, String dt2) {
		long time1 = toDateLong(dt1, 2);
		long time2 = toDateLong(dt2, 2);
		if (time2 - time1 >= 0) {
			return true;
		}
		return false;
	}

	/**
	 * 校验时间合法性
	 * 
	 * @param dt
	 * @return true
	 */
	public static boolean validTime(String dt) {
		String eL = "^((\\d{2}(([02468][048])|([13579][26]))[\\-\\/\\s]?((((0?[13578])|(1[02]))[\\-\\/\\s]?((0?[1-9])|([1-2][0-9])|(3[01])))|(((0?[469])|(11))[\\-\\/\\s]?((0?[1-9])|([1-2][0-9])|(30)))|(0?2[\\-\\/\\s]?((0?[1-9])|([1-2][0-9])))))|(\\d{2}(([02468][1235679])|([13579][01345789]))[\\-\\/\\s]?((((0?[13578])|(1[02]))[\\-\\/\\s]?((0?[1-9])|([1-2][0-9])|(3[01])))|(((0?[469])|(11))[\\-\\/\\s]?((0?[1-9])|([1-2][0-9])|(30)))|(0?2[\\-\\/\\s]?((0?[1-9])|(1[0-9])|(2[0-8]))))))(\\s((([01][0-9])|(2[0-3]))\\:([0-5]?[0-9])((\\s)|(\\:([0-5]?[0-9])))))?$";
		Pattern p = Pattern.compile(eL);
		Matcher m = p.matcher(dt);
		return m.matches();
	}

	/**
	 * 校验时间合法性
	 * 
	 * @param dt
	 * @return true
	 */
	public static boolean validTimes(String... dts) {
		for (String dt : dts) {
			if (validTime(dt)) {
				continue;
			} else {
				return false;
			}
		}
		return true;
	}

	/**
	 * 两个时间比较，含等于
	 * 
	 * @param dt1
	 * @param dt2
	 * @param flag
	 *            1 yyyy-MM-dd HH:mm:ss 2、yyyy-MM-dd HH:mm 3、yyyy-MM-dd
	 *            4、yyyy-MM
	 * @return dt2>dt1 true
	 */
	public static boolean compare2Time1(String dt1, String dt2, int flag) {
		long time1 = toDateLong(dt1, flag);
		long time2 = toDateLong(dt2, flag);
		if (time2 - time1 >= 0) {
			return true;
		}
		return false;
	}

	/**
	 * 根据时间，获取周一的日期
	 * 
	 * @param curDate
	 *            字符串形式
	 * @return
	 */
	public static String getMondayDayStr(Date curDate) {
		SimpleDateFormat sdf2 = new SimpleDateFormat("yyyy-MM-dd");
		Calendar cal = Calendar.getInstance();
		cal.setTime(curDate);

		// System.out.println(sdf2.format(cal.getTime()));

		int a = cal.get(Calendar.DAY_OF_WEEK) - 1; // 得到今天是周几
		// 周几,如果是周日 变为7
		// System.out.println(a);
		if (a == 0) {
			a = 7;
		}
		// 当前时间减去 几天得到周一的时间
		long resDateTime = curDate.getTime() - (a * 86400000);
		// 再加上1天的时间
		resDateTime += 86400000;
		// System.out.println(resDateTime);
		Date resDate = new Date(resDateTime);

		return sdf2.format(resDate);
	}

	public static void main(String[] args) {
		// System.out.println(TimeUtil.toDateLong("2013-11-11 19:31:50"));
		// System.out.println(TimeUtil.dateFormat1("2013-11-11 19:31:50.0", 3));
		// System.out.println(TimeUtil.getDay("2013-01-01", -1));
		// System.out.println(getDayNum("2014-03-08", "2014-03-20"));
		// System.out.println(long2Date(System.currentTimeMillis() / 1000));
		// System.out.println(TimeUtil.getLowerTime("2014-05-22 00:00:00", 5));
		// System.out.println("2014-05-22 00:00:00".substring(0, 16));
		// System.out.println(compare2Time("2014-06-01 11:20",
		// "2014-06-01 15:30"));
		//
		// System.out.println(validTimes("2014-05-08", "2018-05-12"));
		//
		// System.out.println(TimeUtil.toDateLong("9999-12-31 23:59:59"));

		// System.out.println(getLowerTime(60));

		// System.out.println(getTime("2015-05-08", 4));

		System.out.println(toDateLong("2015-09-22 00:00:00"));

		System.out.println(toDateLong("2015-09-23 23:59:59"));

		System.out.println(getLowerTime("2015-09-23 23:54:00", "2015-09-23 23:54:15"));

	}
}
