package com.huiwan.gdata.modules.gdata.util;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.Set;
import java.util.StringTokenizer;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.alibaba.fastjson.JSONObject;

/**
 * 字符串处理类
 * 
 */
public class StringUtil {

	/**
	 * 将str将多个分隔符进行切分，
	 * 
	 * 示例：StringTokenizerUtils.split("1,2;3 4"," ,;"); 返回: ["1","2","3","4"]
	 * 
	 * @param str
	 * @param seperators
	 * @return
	 */
	public static Object[] split(String str, String seperators) {
		StringTokenizer tokenlizer = new StringTokenizer(str, seperators);
		List<Object> result = new ArrayList<Object>();

		while (tokenlizer.hasMoreElements()) {
			Object s = tokenlizer.nextElement();
			if (s instanceof Integer) {
				result.add((Integer) s);
			} else if (s instanceof String) {
				result.add((String) s);
			} else if (s instanceof Double) {
				result.add((Double) s);
			} else if (s instanceof Float) {
				result.add((Float) s);
			} else if (s instanceof Long) {
				result.add((Long) s);
			} else if (s instanceof Boolean) {
				result.add((Boolean) s);
			}
		}
		return (Object[]) result.toArray(new Object[result.size()]);
	}

	/**
	 * 检查指定的字符串是否为空。
	 * <ul>
	 * <li>SysUtils.isEmpty(null) = true</li>
	 * <li>SysUtils.isEmpty("") = true</li>
	 * <li>SysUtils.isEmpty("   ") = true</li>
	 * <li>SysUtils.isEmpty("abc") = false</li>
	 * </ul>
	 * 
	 * @param value
	 *            待检查的字符串
	 * @return true/false
	 */
	public static boolean isEmpty(String value) {
		int strLen;
		if (value == null || (strLen = value.length()) == 0) {
			return true;
		}
		for (int i = 0; i < strLen; i++) {
			if ((Character.isWhitespace(value.charAt(i)) == false)) {
				return false;
			}
		}
		return true;
	}

	public static boolean isEmpty(String... strings) {
		if (strings == null) {
			return true;
		}
		for (String t : strings) {
			if (isEmpty(t)) {
				return true;
			}
		}
		return false;
	}

	public static boolean isNotEmpty(String... strings) {
		for (String t : strings) {
			if (isEmpty(t)) {
				return false;
			}
		}
		return true;
	}

	/**
	 * 非空字符串
	 * 
	 * @param value
	 * @return
	 */
	public static boolean isNotEmpty(String value) {
		if (!isEmpty(value)) {
			return true;
		} else {
			return false;
		}
	}

	/**
	 * 校验为int型
	 * 
	 * @param args
	 * @return true/flase
	 */
	public static boolean isInt(String... args) {
		for (String a : args) {
			try {
				Integer.valueOf(a);
			} catch (Exception e) {
				return false;
			}
		}
		return true;
	}

	/**
	 * 校验非int型
	 * 
	 * @param args
	 * @return true/flase
	 */
	public static boolean isNotInt(String... args) {
		if (!isInt(args)) {
			return true;
		} else {
			return false;
		}
	}

	/**
	 * 转化为int
	 * 
	 * @param value
	 * @return 0
	 */
	public static int toInt(String value) {
		int num = 0;
		if (StringUtil.isEmpty(value)) {
			return num;
		}
		try {
			num = Integer.parseInt(value);
		} catch (Exception e) {
			num = 0;
		}
		return num;
	}

	/**
	 * 转化为long
	 * 
	 * @param value
	 * @return
	 */
	public static long toLong(String value) {
		long num = 0;
		if (StringUtil.isEmpty(value)) {
			return num;
		}
		try {
			num = Long.valueOf(value);
		} catch (Exception e) {
			num = 0;
		}
		return num;
	}

	/**
	 * 防止sql注入
	 * 
	 * @param sql
	 * @return
	 **/
	public static String filterSql(String sql) {
		// .* 0个以上任意'或;字符
		return sql.replaceAll(".*([';]+|(--)+).*", " ");
	}

	/**
	 * 生成随机数 （激活码）
	 * 
	 * @param length
	 *            生成多少位
	 * @return
	 */
	public static String createRandom(int length) {
		String code = "";
		char[] a = new char[length];
		for (int i = 0; i < a.length; ++i) {
			int rand = getRandom();
			rand = a[i] = (char) (rand + 48);
		}
		code = new String(a).substring(0, length);
		return code;
	}

	private static int getRandom() {
		Set<Integer> filter = new HashSet<Integer>();
		for (int i = 58; i < 97; i++) {
			filter.add(i - 48);
		}
		Random r = new Random();
		int i = r.nextInt(122 - 48);
		if (filter.contains(i))
			i = getRandom();
		return i;
	}

	/**
	 * LIST 去重
	 * 
	 * @param list
	 * @return
	 */
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public static List removeDuplicateWithOrder(List list) {
		Set set = new HashSet();
		List newList = new ArrayList();
		for (Iterator iter = list.iterator(); iter.hasNext();) {
			Object element = iter.next();
			if (set.add(element))
				newList.add(element);
		}
		return newList;
	}

	/**
	 * 保留两位小数,已自带%
	 * 
	 * @param num
	 * @return
	 */
	public static String NumFormat(float num) {
		DecimalFormat df = new DecimalFormat("0.00");
		return df.format(num * 100) + "%";
	}

	/**
	 * 保留两位小数,已自带%
	 * 
	 * @param num
	 * @return
	 */
	public static String NumFormat(double num) {
		DecimalFormat df = new DecimalFormat("0.00");
		return df.format(num * 100) + "%";
	}

	/**
	 * 全角转半角
	 * 
	 * @param input
	 * @return 半角字符串
	 */
	public static String ToDBC(String input) {
		char[] c = input.toCharArray();
		for (int i = 0; i < c.length; i++) {
			if (c[i] == '\u3000') {
				c[i] = ' ';
			} else if (c[i] > '\uFF00' && c[i] < '\uFF5F') {
				c[i] = (char) (c[i] - 65248);
			}
		}
		return new String(c);
	}

	/**
	 * String转MAP工具 String格式为：file=task`t=2014-04-03
	 * 08:59:50`rid=6958`lv=23`server_id=1`activity_id=1040513`action=3
	 * 
	 * @param params
	 * @param split
	 *            分隔符
	 * @return
	 */
	public static Map<String, Object> stringToMap(String params, String split) {
		String[] rs = params.split(split);
		Map<String, Object> map = new HashMap<String, Object>(2);
		for (String ss : rs) {
			String[] s1 = ss.split("=");
			if (s1.length < 2) {
				map.put(s1[0], "");
			} else {
				map.put(s1[0], s1[1]);
			}
		}
		return map;
	}

	/**
	 * [{key,value}]转换格式
	 * 
	 * @param str
	 *            [{stren_exp,0},{stren_lv,0}]
	 * @return map
	 */
	public static Map<String, String> toMapValue(String str) {
		Map<String, String> map = new HashMap<String, String>();
		if (StringUtil.isNotEmpty(str)) {
			String regex = "\\{.*?\\}";
			Pattern pa = Pattern.compile(regex, Pattern.CANON_EQ);
			Matcher ma = pa.matcher(str);
			String[] param = null;
			while (ma.find()) {
				param = ma.group().replace("{", "").replace("}", "").split(",");
				map.put(param[0], param[1]);
			}
		}
		return map;
	}

	/**
	 * 正则表达式比较
	 * 
	 * @param regex
	 * @param value
	 * @return
	 */
	public static boolean matcher(String regex, String value) {
		Pattern pattern = Pattern.compile(regex);

		Matcher matcher = pattern.matcher(value);
		return matcher.find();
	}

	/**
	 * 去掉换行符
	 * 
	 * @param str
	 * @return
	 */
	public static String replaceBlank(String str) {
		String dest = "";
		if (str != null) {
			Pattern p = Pattern.compile("\\s*|\t|\r|\n");
			Matcher m = p.matcher(str);
			dest = m.replaceAll("");
		}
		return dest;
	}

	/**
	 * a ÷ b保留两位小数
	 * 
	 * @param a
	 * @param b
	 * @return
	 */
	public static String division(Object a, Object b) {
		if (b instanceof Integer) {
			b = Double.valueOf((Integer) b);
		} else if (b instanceof Long) {
			b = Double.valueOf((Long) b);
		} else if (b instanceof Double) {
			b = (Double) b;
		} else if (b instanceof String) {
			b = Double.valueOf((String) b);
		}
		if ((Double) b == 0) {
			return "0.00";
		}

		if (a instanceof Integer) {
			a = Double.valueOf((Integer) a);
		} else if (a instanceof Long) {
			a = Double.valueOf((Long) a);
		} else if (a instanceof Double) {
			a = (Double) a;
		} else if (a instanceof String) {
			a = Double.valueOf((String) a);
		}
		DecimalFormat df = new DecimalFormat("0.00");
		return df.format(((double) a / (double) b));
	}

	/**
	 * a ÷ b
	 * 
	 * @param a
	 * @param b
	 * @param length
	 *            保留多少位小数点后数字
	 * @return
	 */
	public static String division(Object a, Object b, int length) {
		if (b instanceof Integer) {
			b = Double.valueOf((Integer) b);
		} else if (b instanceof Long) {
			b = Double.valueOf((Long) b);
		} else if (b instanceof Double) {
			b = (Double) b;
		} else if (b instanceof String) {
			b = Double.valueOf((String) b);
		}
		if ((Double) b == 0) {
			return "0.00";
		}

		if (a instanceof Integer) {
			a = Double.valueOf((Integer) a);
		} else if (a instanceof Long) {
			a = Double.valueOf((Long) a);
		} else if (a instanceof Double) {
			a = (Double) a;
		} else if (a instanceof String) {
			a = Double.valueOf((String) a);
		}
		String pointZ = "";
		for (int i = 0; i < length; i++) {
			pointZ += "0";
		}
		if (!pointZ.equals("")) {
			pointZ = "." + pointZ;
		}
		DecimalFormat df = new DecimalFormat("0" + pointZ);
		return df.format(((double) a / (double) b));
	}

	/**
	 * a ÷ b保留两位小数
	 * 
	 * @param a
	 * @param b
	 * @return string
	 */
	public static String division(Object a, int b) {
		if (b == 0) {
			return "0.00";
		}
		if (a instanceof Integer) {
			a = Double.valueOf((Integer) a);
		} else if (a instanceof Long) {
			a = Double.valueOf((Long) a);
		} else if (a instanceof Double) {
			a = (Double) a;
		}
		DecimalFormat df = new DecimalFormat("0.00");
		return df.format((double) a / b);
	}

	/**
	 * 数值转字符串
	 * 
	 * @param a
	 * @param b
	 * @return string
	 */
	public static String numToString(Object a) {
		return String.valueOf(a);
	}

	/**
	 * a ÷ b保留两位小数
	 * 
	 * @param a
	 * @param b
	 * @return string
	 */
	public static String division(long a, long b) {
		if (b == 0) {
			return "0.00";
		}
		DecimalFormat df = new DecimalFormat("0.00");
		return df.format((double) a / b);
	}

	/**
	 * a ÷ b保留两位小数 并返回带%
	 * 
	 * @param a
	 * @param b
	 * @return
	 */
	public static String divisionWhitPercent(Object a, Object b) {
		if (a == null) {
			a = 0;
		}
		if (b == null) {
			b = 0;
		}
		if (b instanceof Integer) {
			b = Double.valueOf((Integer) b);
		} else if (b instanceof Long) {
			b = Double.valueOf((Long) b);
		} else if (b instanceof Double) {
			b = (Double) b;
		}
		if ((Double) b == 0) {
			return "0.00%";
		}

		if (a instanceof Integer) {
			a = Double.valueOf((Integer) a);
		} else if (a instanceof Long) {
			a = Double.valueOf((Long) a);
		} else if (a instanceof Double) {
			a = (Double) a;
		}
		DecimalFormat df = new DecimalFormat("0.00");
		return df.format(((double) a / (double) b) * 100) + "%";
	}

	/**
	 * a ÷ b保留两位小数 不带%号
	 * 
	 * @param a
	 * @param b
	 * @return
	 */
	public static String divisionWhitOutPercent(Object a, Object b) {
		if (a == null) {
			a = 0;
		}
		if (b == null) {
			b = 0;
		}
		if (b instanceof Integer) {
			b = Double.valueOf((Integer) b);
		} else if (b instanceof Long) {
			b = Double.valueOf((Long) b);
		} else if (b instanceof Double) {
			b = (Double) b;
		}
		if ((Double) b == 0) {
			return "0.00";
		}

		if (a instanceof Integer) {
			a = Double.valueOf((Integer) a);
		} else if (a instanceof Long) {
			a = Double.valueOf((Long) a);
		} else if (a instanceof Double) {
			a = (Double) a;
		}
		DecimalFormat df = new DecimalFormat("0.00");
		return df.format(((double) a / (double) b) * 100);
	}

	/**
	 * 求平均值
	 * 
	 * @param array
	 * @return
	 */
	public static double average(List<Long> array) {
		long sum = 0;
		for (int i = 0, size = array.size(); i < size; i++) {
			sum += array.get(i);
		}
		double result = 0;
		if (array.size() != 0) {
			result = sum / array.size();
		}
		return result;
	}

	/**
	 * 转list数组
	 * 
	 * @param str
	 *            "1,2,3,4,5"
	 * @param seperators
	 * @return list[1,2,3,4,5]
	 */
	public static List<Integer> toList(String str, String seperators) {
		List<Integer> list = new ArrayList<Integer>();
		String[] strs = str.split(",");
		for (String s : strs) {
			list.add(toInt(s));
		}
		return list;
	}

	/**
	 * 生成24小时，间隔5分钟的分组信息
	 * 
	 * @param dt
	 *            日期
	 * @return map<String,Integer>
	 */
	public static Map<String, Integer> to24Hour5MinMap(String dt) {
		boolean isToday = false;
		if (dt.contains(TimeUtil.getNow(2))) {
			isToday = true;
		}
		Map<String, Integer> numMap = new LinkedHashMap<>();
		boolean isOut = false;

		String today = TimeUtil.getNow(6);
		for (int h = 0; h < 24; h++) {
			if (isOut) {
				break;
			}

			for (int m = 0; m < 60; m = m + 5) {
				if (isToday) {
					String _mytoday = TimeUtil.getNow(2) + " " + h + ":" + m;
					if (TimeUtil.compare2Time(today, _mytoday)) {
						isOut = true;
						break;
					}
				}
				numMap.put(h + ":" + m, 0);
			}
		}
		return numMap;
	}

	public static void main(String[] args) {
		// Map<String,Object>
		// my=stringToMap("file=task`t=2014-04-03
		// 08:59:50`rid=6958`lv=23`server_id=1`activity_id=1040513`action=3",
		// "`");
		// System.out.println(my.get("action"));

		// String sql="SELECT a,b,c,d from a";
		// System.out.println(sql.replaceAll("(?i)select.*?from",
		// "select count(*) from"));

		// System.out.println(division(10, 3));
		JSONObject js = new JSONObject();
		js.put("a", toList("10002,10003,10004,110000", ","));
		System.out.println(js.toJSONString());

		System.out.println(divisionWhitPercent(2L, 1));
	}
}
