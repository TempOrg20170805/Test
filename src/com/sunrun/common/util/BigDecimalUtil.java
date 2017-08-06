package com.sunrun.common.util;

import java.math.BigDecimal;

/**
 * 文 件 名 : BigDecimalUtil
 * 创 建 人： 金明明
 * 日 期：2016年7月5日
 * 修 改 人： 
 * 日 期： 
 * 描 述：BigDecimal工具，提供基本设置
 */
public class BigDecimalUtil {
	public static void main(String args[]) {
		BigDecimal bigDecimal = new BigDecimal(99.99);
		System.out.println(bigDecimal.divide(new BigDecimal(100)));
		System.out.println(roundHalfUp(new BigDecimal(12.456)));
	}
	
	public static BigDecimal roundHalfUp(BigDecimal bigDecimal) {
		return bigDecimal.setScale(2, BigDecimal.ROUND_HALF_UP);
	}

	
}
