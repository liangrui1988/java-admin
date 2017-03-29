package com.rui.pro1.common.utils.charset.conver;

import java.util.Comparator;

import com.rui.pro1.common.utils.charset.bean.CharsetBeanDobule;


/**
 * 根据id数字排序
 * 
 * @author ruiliang
 * @date 2016/12/15
 *
 */
public class BeanSort implements Comparator<CharsetBeanDobule> {

	@Override
	public int compare(CharsetBeanDobule o1, CharsetBeanDobule o2) {
		if (Integer.valueOf(o1.getId()) > Integer.valueOf(o2.getId())) {
			return 1;
		}
		return -1;
	}

}
