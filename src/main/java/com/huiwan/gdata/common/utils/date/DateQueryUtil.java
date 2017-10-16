package com.huiwan.gdata.common.utils.date;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class DateQueryUtil {

	// @Test
	public static Date[] SEDate(Date start, Date end) {

		Date[] result = new Date[2];
		// 如果日期为空 查询全部
		if ((start == null || "".equals(start))
				&& (end == null || "".equals(end))) {
			result[0] = new Date(1388505660000L);// 2014/01/01
			result[1] = new Date(1514736060000L);
			return result;
		}

		// 如果开始日期有 结束日期没有 则查询 结束日期为当天时间
		if ((start != null && !"".equals(start))
				&& (end == null || "".equals(end))) {

			// 取当天日间
			long endLong = new Date().getTime();
			result[0] = start;
			result[1] = new Date(endLong);
			return result;
		}

		// 如果结束日期有 开始日期没有 则查询 开始全部
		if ((start == null || "".equals(start))
				&& (end != null && !"".equals(end))) {

			result[0] = new Date(1388505660000L);
			result[1] = end;
			return result;
		}

		// 正常日期查询
		if (start != null && !"".equals(start) && end != null
				&& !"".equals(end)) {
			result[0] = start;
			
			int dayMis = 1000 * 60 * 60 * 24;//
			long endLong=end.getTime()+dayMis-1;
			result[1] = new Date(endLong);
			//result[1] = end;
			return result;
		}

		return result;
	}

	public static void main(String[] args) throws ParseException {
		// SEDate("", "");
		String sd = "2014/01/01";
		String sd2 = "2018/01/01";

		SimpleDateFormat s = new SimpleDateFormat("yyyy/mm/dd");
		Date d1 = s.parse(sd);
		System.out.println(d1.getTime());
		Date d2 = s.parse(sd2);
		System.out.println(d2.getTime());

	}
	
	
	// @Test
		public static Date[] SEDate2(Date start, Date end) {

			Date[] result = new Date[2];
			// 如果日期为空 查询全部
			if ((start == null || "".equals(start))
					&& (end == null || "".equals(end))) {
				//result[0] = new Date(1388505660000L);// 2014/01/01
				//result[1] = new Date(1514736060000L);
				
				return result;
			}

			// 如果开始日期有 结束日期没有 则查询 结束日期为当天时间
			if ((start != null && !"".equals(start))
					&& (end == null || "".equals(end))) {

				// 取当天日间
				long endLong = new Date().getTime();
				result[0] = start;
				result[1] = new Date(endLong);
				return result;
			}

			// 如果结束日期有 开始日期没有 则查询 开始全部
			if ((start == null || "".equals(start))
					&& (end != null && !"".equals(end))) {

				result[0] = new Date(1388505660000L);
				result[1] = end;
				return result;
			}

			// 正常日期查询
			if (start != null && !"".equals(start) && end != null
					&& !"".equals(end)) {
				result[0] = start;
				
				int dayMis = 1000 * 60 * 60 * 24;//
				long endLong=end.getTime()+dayMis-1;
				result[1] = new Date(endLong);
				return result;
			}

			return result;
		}

}
