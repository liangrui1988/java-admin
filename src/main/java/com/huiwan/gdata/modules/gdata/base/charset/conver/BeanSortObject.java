package com.huiwan.gdata.modules.gdata.base.charset.conver;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Comparator;

import com.huiwan.gdata.modules.gdata.base.charset.bean.common.CharsetBeanCommon;


/**
 * 根据日期排序,日期小的在前面
 * 
 * @author ruiliang
 * @date 2016/12/15
 *
 */
public class BeanSortObject implements Comparator<CharsetBeanCommon> {

	@Override
	public int compare(CharsetBeanCommon o1, CharsetBeanCommon o2) {
		DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
		try {
			long o1_time = df.parse(o1.getxName()).getTime();
			long o2_time = df.parse(o2.getxName()).getTime();
			if (o1_time > o2_time) {
				return 1;
			}
		} catch (ParseException e) {
			e.printStackTrace();
		}

		return -1;
	}

}
