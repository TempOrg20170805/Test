package com.sunrun.common.util;

import java.util.Date;

import org.apache.commons.lang.RandomStringUtils;

import com.jeecms.common.util.Num62;

/**
 * 文 件 名 : OrderUtils 
 * 创 建 人： 金明明 
 * 日 期： 2017年8月8日
 * 修 改 人： 
 * 日 期： 
 * 描 述：订单工具类
 */
public class OrderUtils {

	public static void main(String args[]) {
		System.out.println(Integer.toHexString((int)(new Date().getTime())).toUpperCase());
	}
	/**
	 * 生成订单号
	 * @return
	 */
	public static String createOrder() {
		Long time = System.currentTimeMillis();
		String orderNo = String.valueOf(time) + RandomStringUtils.random(4, Num62.N36_CHARS).toUpperCase();
		return orderNo;
	}
	
	/**
	 * 生成退款退货编号
	 * @return
	 */
	public static String createRefund() {
		Long time = System.currentTimeMillis();
		String refundNo = String.valueOf(time) + RandomStringUtils.random(4, Num62.N36_CHARS).toUpperCase();
		return refundNo;
	}

}
