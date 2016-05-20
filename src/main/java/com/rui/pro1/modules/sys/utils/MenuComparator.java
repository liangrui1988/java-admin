package com.rui.pro1.modules.sys.utils;

import java.util.Comparator;

import com.rui.pro1.modules.sys.entity.Menu;

public class MenuComparator implements Comparator<Menu> {

	// @Override
	// public int compare(Object o1, Object o2) {
	// return ((OpusComment) o1).getCreateTime().getTime() <= ((OpusComment) o2)
	// .getCreateTime().getTime() ? 0 : 1;
	// }

	public int compare(Menu o1, Menu o2) {

		try {
			return o1.getSortNo() <= o2.getSortNo() ? -1 : 1;
		} catch (Exception e) {
			e.printStackTrace();
			return 0;
		}
	}

}
