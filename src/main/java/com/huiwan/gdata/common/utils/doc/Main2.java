package com.huiwan.gdata.common.utils.doc;

import java.math.BigDecimal;

public class Main2 {

	public static void main(String[] args) {
		float v = 00000.9599f;

		String s = "";

		BigDecimal b=	new BigDecimal(555.55555);
		s = new BigDecimal(555.55955) + "";
		
		double dou= b.setScale(2,BigDecimal.ROUND_DOWN).doubleValue();
		System.out.println(String.valueOf(dou));
	}

}
