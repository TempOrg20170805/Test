package com.sunrun.common.util.unionpay;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.sunrun.common.util.unionpay.util.UnionpayBase;
import com.sunrun.rest.dto.BaseDTO;
import com.unionpay.acp.sdk.SDKConfig;

public class UnionpayUtil extends UnionpayBase{
	private String version="5.0.0";// 版本号
	private String encoding="UTF-8";// 字符集编码 默认"UTF-8"
	private String signMethod="01";// 签名方法 01 RSA
	/*取值：
	00	查询交易
	01：消费
	02：预授权
	03：预授权完成
	04：退货
	05: 圈存
	11：代收
	12：代付
	13：账单支付
	14： 转账（保留）
	21：批量交易
	22：批量查询
	31：消费撤销
	32：预授权撤销
	33：预授权完成撤销
	71：余额查询
	72：实名认证-建立绑定关系
	73： 账单查询
	74：解除绑定关系
	75：查询绑定关系
	77：发送短信验证码交易
	78：开通查询交易
	79：开通交易
	94：IC卡脚本通知*/
	private String txnType="01";// 交易类型 01-消费
	
	private String txnSubType="01";// 交易子类型 01:自助消费 02:订购 03:分期付款
	/*依据实际业务场景填写(目前仅使用后4位，签名2位默认为00)
	默认取值:000000
	具体取值范围：
	000101 基金业务之股票基金
	000102 基金业务之货币基金
	000201 B2C网关支付
	000301  认证支付2.0
	000302  评级支付
	000401  代付
	000501  代收
	000601  账单支付
	000801  跨行收单
	000901  绑定支付
	001001  订购
	000202  B2B*/
	private String bizType="000201";// 业务类型
	private String channelType="08";// 渠道类型，07-PC，08-手机
	//private String frontUrl="http://localhost:8080/ACPTest/acp_front_url.do";// 前台通知地址 ，控件接入方式无作用
	private String backUrl;// 后台通知地址
	private String accessType="0";// 接入类型，商户接入填0 0- 商户 ， 1： 收单， 2：平台商户
	private String merId="898441848160036";// 商户号码，请改成自己的商户号
	private String orderId;// 商户订单号，8-40位数字字母
	//private String txnTime=new SimpleDateFormat("yyyyMMddHHmmss").format(new Date());// 订单发送时间，取系统时间
	private String txnAmt;// 交易金额，单位分
	private String currencyCode="156";// 交易币种
	//private String reqReserved;// 请求方保留域，透传字段，查询、通知、对账文件中均会原样出现
	//private String orderDesc;// 订单描述，可不上送，上送时控件中会显示该信息
	private String requestAppUrl;// 交易请求url 从配置文件读取
	private SimpleDateFormat sdf=new SimpleDateFormat("yyyyMMddHHmmss");
	private BaseDTO baseDTO = new BaseDTO();
	private Map<String, String> data = new HashMap<String, String>();
	private Map<String, String> resmap = new HashMap<String, String>();
	
	
	/**
	 * 手机支付 ——手机控件支付
	 *url 路径参数格式 如：/alipayNew.do
	 * @return
	 */
	public String appConsume(String callBackUrl, HttpServletRequest request,HttpServletResponse response,Map<String, Object> map){
		Properties pro=SDKConfig.getConfig().getProperties();
		System.out.println("银联回调地址"+callBackUrl);
		backUrl=callBackUrl;
		data = new HashMap<String, String>();
		data.put("version", version);
		data.put("encoding", encoding);
		data.put("signMethod", signMethod);
		data.put("txnType", txnType);
		data.put("txnSubType", txnSubType);
		data.put("bizType", bizType);
		data.put("channelType", channelType);
		data.put("backUrl", backUrl);
		data.put("accessType", accessType);
		data.put("merId", merId);
		orderId=(String) map.get("orderId");
		data.put("orderId", orderId);
		data.put("txnTime", sdf.format(new Date()));
		txnAmt=(String) map.get("txnAmt");
		data.put("txnAmt", txnAmt);
		data.put("currencyCode", currencyCode);
		data = signData(data);
		requestAppUrl = SDKConfig.getConfig().getAppRequestUrl();
		
		resmap = submitUrl(data, requestAppUrl);
		
		System.out.println("请求报文=["+data.toString()+"]");
		System.out.println("应答报文=["+resmap.toString()+"]");
		return resmap.get("tn");
	}
}
