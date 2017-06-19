package com.huiwan.gdata.modules.gdata.base.charset.conver;

import java.util.Comparator;

import org.apache.commons.lang.StringUtils;

import com.huiwan.gdata.modules.gdata.base.charset.bean.common.CharsetBeanCommon;


/**
 * 根据日期是24小时排序
 * 
 * @author ruiliang
 * @date 2016/12/15
 *
 */
public class BeanSortTimeHourObject implements Comparator<CharsetBeanCommon> {

	@Override
	public int compare(CharsetBeanCommon o1, CharsetBeanCommon o2) {
		if (StringUtils.isNumeric(o1.getxName()) && StringUtils.isNumeric(o2.getxName())) {
			Integer o1_time = Integer.valueOf(o1.getxName());
			Integer o2_time = Integer.valueOf(o2.getxName());
			if (o1_time > o2_time) {
				return 1;
			}
		}

		return -1;
	}
	
	public static void main(String[] args) {
		String s="01";
		System.out.println(StringUtils.isNumeric(s));
		System.out.println(Integer.valueOf(s));
		
	}

}
