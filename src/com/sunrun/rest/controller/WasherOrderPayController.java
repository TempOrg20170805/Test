package com.sunrun.rest.controller;
import java.io.IOException;
import java.io.PrintStream;
import java.io.UnsupportedEncodingException;
import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.RandomStringUtils;
import org.apache.commons.lang.StringUtils;
import org.jdom.Element;
import org.jdom.JDOMException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alipay.api.internal.util.AlipaySignature;
import com.jeecms.common.page.Pagination;
import com.jeecms.common.util.DateFormatUtils;
import com.jeecms.common.util.Num62;
import com.jeecms.common.web.RequestUtils;
import com.jeecms.core.manager.CmsUserMng;
import com.sunrun.common.util.OrderPropertiesCallBackUtil;
import com.sunrun.common.util.alipay.AlipayConfig;
import com.sunrun.common.util.alipay.AlipayUtil;
import com.sunrun.common.util.alipay.PayDTO;
import com.sunrun.common.util.unionpay.UnionpayUtil;
import com.sunrun.common.util.weixinpay.ConstantUtil;
import com.sunrun.common.util.weixinpay.MD5;
import com.sunrun.common.util.weixinpay.XMLMap;
import com.sunrun.common.util.weixinpay.XMLPost;
import com.sunrun.rest.dto.BaseDTO;
import com.sunrun.rest.dto.BaseDTO.BaseDTOEnum;
import com.sunrun.rest.dto.WasherOrderPayDTO;
import com.sunrun.rest.dto.WasherOrderPayDTO.WasherOrderPayDTOEnum;
import com.sunrun.washer.entity.Machine;
import com.sunrun.washer.entity.WasherOrder;
import com.sunrun.washer.enums.MachineStatusEnum;
import com.sunrun.washer.enums.WalletLogPayPlatformEnum;
import com.sunrun.washer.enums.WasherOrderStatusEnum;
import com.sunrun.washer.manager.MachineMng;
import com.sunrun.washer.manager.ModeMng;
import com.sunrun.washer.manager.WasherOrderMng;
import com.sunrun.washer.model.MachineModel;
import com.unionpay.acp.sdk.LogUtil;
import com.unionpay.acp.sdk.SDKConstants;
import com.unionpay.acp.sdk.SDKUtil;

/**
 * 文 件 名 : WasherOrderPayController.java
 * 创 建 人： 金明明
 * 日 期：2017-8-13
 * 修 改 人： 
 * 日 期： 
 * 描 述：订单 Controller层
 */
@Controller
public class WasherOrderPayController extends BaseController{

	@Autowired
	private WasherOrderMng washerOrderMng;
	@Autowired
	private CmsUserMng cmsUserMng;
	@Autowired
	private ModeMng modeMng;
	@Autowired
	private MachineMng machineMng;
	// 银联支付相关
	private UnionpayUtil uutil=new UnionpayUtil();
	/**
	 * 预支付
	 * @param outSn 订单号
	 * @param payPlatform 交易平台 2.支付宝平台 3.微信平台 4.银行卡平台
	 * @return
	 */
	@RequestMapping(value = "/walletRecharge/rechargeOrderPay.json")
	@ResponseBody
	public WasherOrderPayDTO rechargeOrderPay(String outSn, Integer payPlatform, HttpServletRequest request) {
		WasherOrderPayDTO washerOrderPayDTO = new  WasherOrderPayDTO();
		WasherOrder washerOrder = washerOrderMng.findByOutSn(outSn);
		if (validateOrderPay(washerOrderPayDTO,getUserId(), washerOrder, payPlatform)) {
			washerOrderPayDTO.setOutSn(washerOrder.getOutSn());
			washerOrderPayDTO.setPayPrice(washerOrder.getOrderAmount());
			
			if(payPlatform.equals(WalletLogPayPlatformEnum.ALIPAY.getCode())) {
				// 跳转到支付宝支付
				washerOrderPayDTO.setCallBackUrl(OrderPropertiesCallBackUtil.getAlipayCallBackString(request));
				
				// 支付宝预支付
				DecimalFormat format = new DecimalFormat("0.00");
				String str = format.format(washerOrder.getOrderAmount());//订单总金额，单位为元，精确到小数点后两位，String类型 
				String title = "智能洗衣机";
				String goods_body = "";
				String number= washerOrder.getOutSn(); 
				String alipayData= AlipayUtil.app_payment_order_information(title, goods_body, number, str, OrderPropertiesCallBackUtil.getAlipayCallBackString(request), request);
				washerOrderPayDTO.setAliPayString(alipayData);
				
				
				washerOrderPayDTO.setState(BaseDTO.BaseDTOEnum.API_STATUS_SUCCESS);
				washerOrderMng.updatePayMsg(washerOrder.getOrderId(), WalletLogPayPlatformEnum.ALIPAY.getCode(), WalletLogPayPlatformEnum.ALIPAY.getDescribe() + "支付");
			}
			else if (payPlatform.equals(WalletLogPayPlatformEnum.WEIXIN.getCode())) {
				washerOrderPayDTO.setCallBackUrl(OrderPropertiesCallBackUtil.getWeiXinCallBackString(request));
				Map<String, Object> weixResMap = new HashMap<>();
				try {
					weixResMap = weiXinResPay(outSn, washerOrder.getOrderAmount(), request, response);
				} catch (Exception e) {
					e.printStackTrace();
					washerOrderPayDTO.setState(WasherOrderPayDTOEnum.FAIL);
					return washerOrderPayDTO;
				}
				washerOrderPayDTO.setWeixResMap(weixResMap);
				washerOrderMng.updatePayMsg(washerOrder.getOrderId(), WalletLogPayPlatformEnum.WEIXIN.getCode(), WalletLogPayPlatformEnum.WEIXIN.getDescribe() + "支付");
			}
			else if (payPlatform.equals(WalletLogPayPlatformEnum.BANK_CARD.getCode())) {
				washerOrderPayDTO.setCallBackUrl(OrderPropertiesCallBackUtil.getBankCallBackString(request));
				washerOrderMng.updatePayMsg(washerOrder.getOrderId(), WalletLogPayPlatformEnum.BANK_CARD.getCode(), WalletLogPayPlatformEnum.BANK_CARD.getDescribe() + "支付");
				Map<String, Object> bankResMap = new HashMap<>();
				try {
					bankResMap = useUnionpay(OrderPropertiesCallBackUtil.getBankCallBackString(request), washerOrder.getOrderAmount(), washerOrder.getOutSn(), request, response);
				} catch (Exception e) {
					e.printStackTrace();
					washerOrderPayDTO.setState(WasherOrderPayDTOEnum.FAIL);
					return washerOrderPayDTO;
				}
			}
			washerOrderPayDTO.setState(BaseDTOEnum.API_STATUS_SUCCESS);
		}
		return washerOrderPayDTO;
	}
	
	/**
	 * 微信支付预支付
	 * @param outSn
	 * @param totalFee
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	private Map<String, Object> weiXinResPay(String outSn, BigDecimal totalFee,HttpServletRequest request,HttpServletResponse response)  throws Exception {
		Map<String,Object> resInfo = new HashMap<String, Object>();
		
		
		// 支付所有参数预览start
		String appid = ConstantUtil.APPID;// not null 公众账号ID
		String mch_id = ConstantUtil.MCH_ID;// not null 商户号
		String device_info = "";//设备号
		String nonce_str = RandomStringUtils.random(32, Num62.N36_CHARS);// not null 随机字符串
		// sign 由于是所有的加密方式所以应该要放在最后
		String body = "";// not null 商品描述
		String detail = "";// 商品详情
		String attach = "";// 附加数据
		String out_trade_no = outSn + "_" + (new Date()).getTime();// not null 商户订单号 使订单号不唯一
		String fee_type = "";// 货币类型
		String total_fee = totalFee.multiply(new BigDecimal(100)).setScale(0, BigDecimal.ROUND_HALF_UP).toString();// not null 总金额
		String spbill_create_ip = RequestUtils.getIpAddr(request);// not null 终端IP
		String time_start = "";// 交易起始时间
		String time_expire = "";// 交易结束时间
		String goods_tag = "";// 商品标记

		String notify_url = OrderPropertiesCallBackUtil.getWeiXinCallBackString(request);// not null 通知地址
		System.out.println(notify_url);
		// http://hhxcustomer.sunruncn.com:8282/customer/rest/weixinpayNotice
		String trade_type = "APP";// not null 交易类型  APP--app支付
		String product_id = "";// 商品ID
		String limit_pay = "";// 指定支付方式
		String openid = "";// 用户标识
		String sign = "";// not null 签名
		// 支付所有参数预览end
		
		// 支付所有参数赋值start
		
		// body商品描述
		body = body.trim();
		if(StringUtils.isBlank(body)) {
			body = "智能洗衣机";
		}

		// 防止字符串超标
		if(body.length() > 32) {
			body = body.substring(0, 30);
			body = body + "……";
		}
		
		
		// sign签名
		Map<String,Object> allParam = new HashMap<String, Object>();
		allParam.put("appid", appid);
		allParam.put("body",body);
		allParam.put("mch_id", mch_id);
		allParam.put("nonce_str", nonce_str);
		allParam.put("notify_url", notify_url);
		allParam.put("out_trade_no", out_trade_no);
		allParam.put("spbill_create_ip", spbill_create_ip);
		allParam.put("total_fee", total_fee);
		allParam.put("trade_type", trade_type);
		// 1.参数按照key=value的格式
		//这里将map.entrySet()转换成list
        List<Map.Entry<String,Object>> list = new ArrayList<Map.Entry<String,Object>>(allParam.entrySet());
        //然后通过比较器来实现key排序
        Collections.sort(list,new Comparator<Map.Entry<String,Object>>() {
            //升序排序
            public int compare(Entry<String,Object> o1,

                    Entry<String,Object> o2) {
                return o1.getKey().compareTo(o2.getKey());
            }
        });
        String stringA = "";
        for(Map.Entry<String,Object> mapping: list){
        	if (mapping == null) {  
        		stringA = stringA + "";  
            }  
            
        	stringA = stringA + mapping.getKey() + "=" + mapping.getValue();  
        	stringA = stringA + "&";  
        }
        if (stringA.endsWith("&")) {
        	stringA = org.apache.commons.lang.StringUtils.substringBeforeLast(stringA, "&");  
        }
		// 2.拼接API密钥 并 MD5运算
		sign = MD5.sign(stringA + "&key=", ConstantUtil.APIKEY, "UTF-8").toUpperCase();
		// 3.sign放入到参数中
		allParam.put("sign", sign);
		// 支付所有参数赋值end
		
		
		
		WasherOrder washerOrder = washerOrderMng.findByOutSn(outSn);
		if(washerOrder != null) {
			// 支付请求start
			Element element = new Element("element"); 
			XMLMap.parseToXML(allParam, element);
			String strpostxml = "<xml>" + XMLMap.getChildrenText(element.getChildren()) + "</xml>";
			
			strpostxml = new String(strpostxml.getBytes("UTF-8"), "ISO8859-1");
			System.out.println("strpostxml" + strpostxml);
			
			String strxml = XMLPost.postpay(ConstantUtil.GATEURL, strpostxml);
			
			
			resInfo = XMLMap.doXMLParse(strxml);
			resInfo.put("timestamp", (new Date()).getTime()/1000);
			// 支付请求end
		} else {
			resInfo.put("return_code", "FAIL");
			resInfo.put("return_msg", "订单号不存在");
		}
		return resInfo;
	}
	

	/**
	 * 银联支付预支付
	 * @param callBackUrl 回调地址
	 * @param price 订单价格
	 * @param orderId 订单号
	 * @param request
	 * @param response
	 * @return
	 * @throws IOException
	 */
	private Map<String, Object> useUnionpay(String callBackUrl, BigDecimal price, String orderId,HttpServletRequest request,
			HttpServletResponse response) throws IOException {
		Map<String, Object> map=new HashMap<String, Object>();
		map.put("orderId", orderId);
		map.put("txnAmt", price.multiply(new BigDecimal(100)).setScale(0, BigDecimal.ROUND_HALF_UP).toString());
		map.put("data", uutil.appConsume(callBackUrl, request, response, map));
		return map;
	}
	
	/**
	 * 校验支付接口
	 * @param baseDTO
	 * @param outSn 订单号
	 * @param payPlatform 
	 * @return
	 */
	private Boolean validateOrderPay(BaseDTO baseDTO,Integer userId, WasherOrder washerOrder, Integer payPlatform) {
		if (cmsUserMng.findById(userId)  == null) {
			baseDTO.setState(BaseDTOEnum.API_MESSAGE_USER_NOT_FOUND);
			return false;			
		} 
		else if (washerOrder == null) {
			baseDTO.setState(BaseDTOEnum.API_MESSAGE_VALIDATECODE_NOTEXIST);
			return false;			
		} 
		else {
			if (WalletLogPayPlatformEnum.getEnumByCode(payPlatform) == null) {
				baseDTO.setState(WasherOrderPayDTOEnum.PAY_PLATFORM_ERROR);
				return false;
			}
		}
		return true;
	}
	
	/**
	 * 支付宝回调（alipay）
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping("/alipayOrderCallBack.json")
	public void alipayScoreOrderCallBack(HttpServletRequest request, HttpServletResponse response)  throws Exception {
		System.out.println("---订单:支付宝回调start 时间：" + DateFormatUtils.formatDate(new Date())+"---");
		// 获取支付宝POST过来反馈信息
		Map<String, String> params = new HashMap<String, String>();
		Map requestParams = request.getParameterMap();
		for (Iterator iter = requestParams.keySet().iterator(); iter.hasNext();) {
			String name = (String) iter.next();
			String[] values = (String[]) requestParams.get(name);
			String valueStr = "";
			for (int i = 0; i < values.length; i++) {
				valueStr = (i == values.length - 1) ? valueStr + values[i]
						: valueStr + values[i] + ",";
			}
			// 乱码解决，这段代码在出现乱码时使用。如果mysign和sign不相等也可以使用这段代码转化
			// valueStr = new String(valueStr.getBytes("ISO-8859-1"), "gbk");
			params.put(name, valueStr);
		}
		// 获取支付宝的通知返回参数，可参考技术文档中页面跳转同步通知参数列表(以下仅供参考)//
		// 商户订单号

		String out_trade_no = new String(request.getParameter("out_trade_no")
				.getBytes("ISO-8859-1"), "UTF-8");
		// 支付宝交易号

		String trade_no = new String(request.getParameter("trade_no").getBytes(
				"ISO-8859-1"), "UTF-8");

		// 交易状态
		String trade_status = new String(request.getParameter("trade_status")
				.getBytes("ISO-8859-1"), "UTF-8");

		// 获取支付宝的通知返回参数，可参考技术文档中页面跳转同步通知参数列表(以上仅供参考)//
		// 计算得出通知验证结果
		// boolean AlipaySignature.rsaCheckV1(Map<String, String> params, String
		// publicKey, String charset, String sign_type)
		boolean verify_result = AlipaySignature.rsaCheckV1(params,
				AlipayConfig.ALIPAY_PUBLIC_KEY, AlipayConfig.CHARSET, "RSA2");
		if (verify_result) {// 验证成功
			// ////////////////////////////////////////////////////////////////////////////////////////
			// 请在这里加上商户的业务逻辑程序代码

			// ——请根据您的业务逻辑来编写程序（以下代码仅作参考）——

			if (trade_status.equals("TRADE_FINISHED")) {
				// 判断该笔订单是否在商户网站中已经做过处理
				// 如果没有做过处理，根据订单号（out_trade_no）在商户网站的订单系统中查到该笔订单的详细，并执行商户的业务程序
				// 请务必判断请求时的total_fee、seller_id与通知时获取的total_fee、seller_id为一致的
				// 如果有做过处理，不执行商户的业务程序
				WasherOrder washerOrder = washerOrderMng.findByOutSn(out_trade_no);
				boolean isNotPay = true;
				// 判断之前是否已经支付
				if(washerOrder != null && !washerOrder.getOrderState().equals(WasherOrderStatusEnum.NOT_PAY.getCode())) {
					isNotPay = false;
				}
				if (isNotPay) {
					washerOrderMng.paySuccess(out_trade_no);
				} else {
					System.out.println("错误调用：app订单号不是未付款状态/订单被删除：" + out_trade_no +" 时间："+(new Date()));
				}
				// 注意：
				// 如果签约的是可退款协议，退款日期超过可退款期限后（如三个月可退款），支付宝系统发送该交易状态通知
				// 如果没有签约可退款协议，那么付款完成后，支付宝系统发送该交易状态通知。
			} else if (trade_status.equals("TRADE_SUCCESS")) {
				// 判断该笔订单是否在商户网站中已经做过处理
				// 如果没有做过处理，根据订单号（out_trade_no）在商户网站的订单系统中查到该笔订单的详细，并执行商户的业务程序
				// 请务必判断请求时的total_fee、seller_id与通知时获取的total_fee、seller_id为一致的
				// 如果有做过处理，不执行商户的业务程序
				
				// ------------------------------
				// 注意：该状态支付成功但是金额在支付宝内，未成功转入到账号。
				// ------------------------------
				
				// 注意：
				// 如果签约的是可退款协议，那么付款完成后，支付宝系统发送该交易状态通知。
			}
			// ——请根据您的业务逻辑来编写程序（以上代码仅作参考）——
			response.getWriter().write("success"); // 请不要修改或删除

			// ////////////////////////////////////////////////////////////////////////////////////////
		} else {// 验证失败
			response.getWriter().write("fail");
		}
		System.out.println("---订单:支付宝回调end 时间：" + DateFormatUtils.formatDate(new Date())+"---");

	}
	
	

	
	/**
	 * 微信支付回调接口
	 * @param strxml
	 * @param request
	 * @param response
	 * @throws IOException
	 */
	@RequestMapping(value = "/weixinpayOrderCallBack.json")
	public void weixinpayNotice(@RequestBody String strxml, HttpServletRequest request,
			HttpServletResponse response) throws IOException {
		System.out.println("---订单:微信回调start 时间：" + DateFormatUtils.formatDate(new Date())+"---");
		Map<String,Object> resultMap = new HashMap<String, Object>();
		
		if(StringUtils.isNotBlank(strxml)) {
			@SuppressWarnings("unchecked")
			Map<String, Object> resInfo = new HashMap<String, Object>();
			try {
				resInfo = XMLMap.doXMLParse(strxml);
			} catch (JDOMException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			String retrun_code = (String) resInfo.get("return_code");
			if(StringUtils.isNotBlank(retrun_code) && retrun_code.equals("SUCCESS")) {
				// 获取订单号
				String out_trade_no = (String) resInfo.get("out_trade_no");
				if(StringUtils.isNotBlank(out_trade_no) && out_trade_no.contains("_")){
					out_trade_no = out_trade_no.substring(0, out_trade_no.indexOf("_"));
				}
				
				//------------------------------
				//处理业务开始
				//------------------------------
				
				WasherOrder washerOrder = washerOrderMng.findByOutSn(out_trade_no);
				boolean isNotPay = true;
				// 判断之前是否已经支付
				if(washerOrder != null && !washerOrder.getOrderState().equals(WasherOrderStatusEnum.NOT_PAY.getCode())) {
					isNotPay = false;
				}
				if (isNotPay) {
					washerOrderMng.paySuccess(out_trade_no);
				} else {
					System.out.println("错误调用：app订单号不是未付款状态/订单被删除：" + out_trade_no +" 时间："+(new Date()));
				}
				
				
				//------------------------------
				//处理业务完毕
				//------------------------------
				
				
				resultMap.put("return_code", "SUCCESS");
				//response.getWriter().write("SUCCESS");

			} else {
				System.out.println(resInfo.get("return_msg").toString());
				resultMap.put("return_code", "FAIL");
				resultMap.put("return_msg", "OK");
				//response.getWriter().write("FAIL");
			}
			System.out.println("---订单:微信回调end 时间：" + DateFormatUtils.formatDate(new Date())+"---");
			Element element = new Element("element"); 
			XMLMap.parseToXML(resultMap, element);
			response.getWriter().write("<xml>" + XMLMap.getChildrenText(element.getChildren()) + "</xml>");
		}	
	}
	

	/**
	 * 银行卡支付回调
	 * @param request
	 * @param response
	 * @throws IOException
	 */
	@RequestMapping(value = "/bankOrderCallBack.json")
	public void bankOrderCallBack(HttpServletRequest request,
			HttpServletResponse response) throws IOException {
		System.out.println("---订单:银行卡回调start 时间：" + DateFormatUtils.formatDate(new Date())+"---");
		request.setCharacterEncoding("ISO-8859-1");
		String encoding = request.getParameter(SDKConstants.param_encoding);
		// 获取请求参数中所有的信息
		Map<String, String> reqParam = getAllRequestParam(request);
		// 打印请求报文
		System.out.println(reqParam);

		Map<String, String> valideData = null;
		if (null != reqParam && !reqParam.isEmpty()) {
			Iterator<Entry<String, String>> it = reqParam.entrySet().iterator();
			valideData = new HashMap<String, String>(reqParam.size());
			while (it.hasNext()) {
				Entry<String, String> e = it.next();
				String key = (String) e.getKey();
				String value = (String) e.getValue();
				value = new String(value.getBytes("ISO-8859-1"), encoding);
				valideData.put(key, value);
			}
		}

		// 验证签名
		if (!SDKUtil.validate(valideData, encoding)) {
			LogUtil.writeLog("验证签名结果[失败].");
		} else {
			System.out.println(valideData.get("orderId")); //其他字段也可用类似方式获取
			LogUtil.writeLog("验证签名结果[成功].");
			
			
			String orderNO=valideData.get("orderId");
			WasherOrder washerOrder = washerOrderMng.findByOutSn(orderNO);
			boolean isNotPay = true;
			// 判断之前是否已经支付
			if(washerOrder != null && !washerOrder.getOrderState().equals(WasherOrderStatusEnum.NOT_PAY.getCode())) {
				isNotPay = false;
			}

			if(isNotPay) {
				washerOrderMng.paySuccess(orderNO);
			} else {
				System.out.println("错误调用：app订单号不是未付款状态/订单被删除：" + orderNO +" 时间："+(new Date()));
			}
		}

		LogUtil.writeLog("BackRcvResponse接收后台通知结束");
		System.out.println("---订单:银行卡回调end 时间：" + DateFormatUtils.formatDate(new Date())+"---");
	}
	
	public static Map<String, String> getAllRequestParam(final HttpServletRequest request) {
		Map<String, String> res = new HashMap<String, String>();
		Enumeration<?> temp = request.getParameterNames();
		if (null != temp) {
			while (temp.hasMoreElements()) {
				String en = (String) temp.nextElement();
				String value = request.getParameter(en);
				res.put(en, value);
				//在报文上送时，如果字段的值为空，则不上送<下面的处理为在获取所有参数数据时，判断若值为空，则删除这个字段>
				//System.out.println("ServletUtil类247行  temp数据的键=="+en+"     值==="+value);
				if (null == res.get(en) || "".equals(res.get(en))) {
					res.remove(en);
				}
			}
		}
		return res;
	}
	
	
	/**
	 * 订单支付成功接口（仅供测试使用）
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "/orderCallBack.json")
	@ResponseBody	
	public BaseDTO online(String orderNo, HttpServletRequest request){
		BaseDTO baseDTO = new BaseDTO();
		WasherOrder washerOrder = washerOrderMng.findByOutSn(orderNo);
		if (washerOrder.getPayPlatform() == null) {
			// 默认支付宝支付
			washerOrderMng.updatePayMsg(washerOrder.getOrderId(), WalletLogPayPlatformEnum.ALIPAY.getCode(), WalletLogPayPlatformEnum.ALIPAY.getDescribe() + "支付");
		}
		boolean isNotPay = true;
		// 判断之前是否已经支付
		if(washerOrder != null && !washerOrder.getOrderState().equals(WasherOrderStatusEnum.NOT_PAY.getCode())) {
			isNotPay = false;
		}

		if(isNotPay) {
			washerOrderMng.paySuccess(orderNo);
		} else {
			System.out.println("错误调用：app订单号不是未付款状态/订单被删除：" + orderNo +" 时间："+(new Date()));
		}
		baseDTO.setState(BaseDTO.BaseDTOEnum.API_STATUS_SUCCESS);
		
		return baseDTO;
	}
	
}

