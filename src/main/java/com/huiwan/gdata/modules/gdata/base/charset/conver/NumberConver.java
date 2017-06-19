package com.huiwan.gdata.modules.gdata.base.charset.conver;

import java.math.BigDecimal;

/**
 * 数字转换
 * 
 * @author ruiliang
 * @date 2016/12/12
 */
public class NumberConver {

	/**
	 * ROUND_HALF_UP获取百分比0位小数
	 * 
	 * @param divisor
	 * @param dividend
	 * @return
	 */
	public static double getPercentageHalfUp_0(int divisor, int dividend) {
		if (dividend == 0) {
			return 0;
		}
		return new BigDecimal(divisor).divide(new BigDecimal(dividend), 2, BigDecimal.ROUND_HALF_UP)
				.multiply(new BigDecimal(100)).doubleValue();

	}

	/**
	 * ROUND_HALF_UP获取百分比2位小数
	 * 
	 * @param divisor
	 * @param dividend
	 * @return
	 */
	public static double getPercentageHalfUp_2(int divisor, int dividend) {
		if (dividend == 0) {
			return 0;
		}
		return new BigDecimal(divisor).divide(new BigDecimal(dividend), 4, BigDecimal.ROUND_HALF_UP)
				.multiply(new BigDecimal(100)).doubleValue();

	}

	/**
	 * ROUND_HALF_UP获取百分比4位小数
	 * 
	 * @param divisor
	 * @param dividend
	 * @return
	 */
	public static double getPercentageHalfUp_4(int divisor, int dividend) {
		if (dividend == 0) {
			return 0;
		}
		return new BigDecimal(divisor).divide(new BigDecimal(dividend), 6, BigDecimal.ROUND_HALF_UP)
				.multiply(new BigDecimal(100)).doubleValue();

	}
}
