package com.sunrun.common.util;

import java.util.Properties;

import javax.servlet.http.HttpServletRequest;

/**
 * 文 件 名 : PropertiesMallMsgUtil
 * 创 建 人： 金明明
 * 日 期：2017-8-13
 * 修 改 人： 
 * 日 期： 
 * 描 述：获取回调地址配置文件信息
 */
public class OrderPropertiesCallBackUtil {
	/**
	 * 获取支付宝回调接口地址
	 * @param request
	 * @return
	 */
	public static String getAlipayCallBackString(HttpServletRequest request) {
		return getCallBackValueByKey(request, "pay.alipayCallBack.Order");
	}
	/**
	 * 获取微信回调接口地址
	 * @param request
	 * @return
	 */
	public static String getWeiXinCallBackString(HttpServletRequest request) {
		return getCallBackValueByKey(request, "pay.weixinpayCallBack.Order");
	}
	/**
	 * 获取银行卡支付回调接口地址
	 * @param request
	 * @return
	 */
	public static String getBankCallBackString(HttpServletRequest request) {
		return getCallBackValueByKey(request, "pay.bankCallBack.Order");
	}
	
	/**
	 * 根据key查询回调的value
	 * @param request
	 * @param key
	 * @return
	 */
	private static String getCallBackValueByKey(HttpServletRequest request, String key) {
		Properties pro = LoadProperties.getPropertiesObj(request);
		String baseDomain = pro.getProperty("base.domain");
		String payAlipayCallBack = pro.getProperty(key);
		String callBackUrl = baseDomain + payAlipayCallBack;
		return callBackUrl;
	}
}
