package com.sunrun.common.util.alipay;

import javax.servlet.http.HttpServletRequest;

import com.alipay.api.AlipayApiException;
import com.alipay.api.AlipayClient;
import com.alipay.api.DefaultAlipayClient;
import com.alipay.api.domain.AlipayTradeAppPayModel;
import com.alipay.api.request.AlipayTradeAppPayRequest;
import com.alipay.api.response.AlipayTradeAppPayResponse;

public class AlipayUtil {


	/**
	 * 支付宝预支付
	 * @CreateDate 2017-6-14
	 * @author wangcy
	 * @param title  标题
	 * @param body 内容描述
	 * @param outTradeNo 订单号
	 * @param totalAmount  金额
	 * @param callBackUrl 回调地址
	 * @param httpServletRequest
	 * @return 返回APP数据
	 */
	public static String app_payment_order_information(String title,
			String body, String outTradeNo, String totalAmount, String callBackUrl,
			HttpServletRequest httpServletRequest) {
		// 实例化客户端
		AlipayClient alipayClient = new DefaultAlipayClient(AlipayConfig.URL,
				AlipayConfig.APPID, AlipayConfig.RSA_PRIVATE_KEY,
				AlipayConfig.FORMAT, AlipayConfig.CHARSET,
				AlipayConfig.ALIPAY_PUBLIC_KEY, AlipayConfig.SIGNTYPE);
		// 实例化具体API对应的request类,类名称和接口名称对应,当前调用接口名称：alipay.trade.app.pay
		AlipayTradeAppPayRequest request = new AlipayTradeAppPayRequest();
		// SDK已经封装掉了公共参数，这里只需要传入业务参数。以下方法为sdk的model入参方式(model和biz_content同时存在的情况下取biz_content)。
		AlipayTradeAppPayModel model = new AlipayTradeAppPayModel();
		model.setBody(body);
		model.setSubject(title);
		model.setOutTradeNo(outTradeNo);
		model.setTimeoutExpress("30m");
		model.setTotalAmount(totalAmount);
		model.setProductCode("QUICK_MSECURITY_PAY");//销售产品码
		request.setBizModel(model);
		request.setNotifyUrl(callBackUrl);//获取回调地址
		String appStr = "";
		try {
			// 这里和普通的接口调用不同，使用的是sdkExecute
			AlipayTradeAppPayResponse response = alipayClient.sdkExecute(request);
			appStr = response.getBody();
			System.out.println(response.getBody());// 就是orderString// 可以直接给客户端请求，无需再做处理。
		} catch (AlipayApiException e) {
			e.printStackTrace();
		}
		return appStr;
	}



}
