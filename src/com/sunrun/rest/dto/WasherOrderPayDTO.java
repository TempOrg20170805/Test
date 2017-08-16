package com.sunrun.rest.dto;

import java.math.BigDecimal;
import java.util.Map;

/**
 * 文 件 名 : WasherOrderPayDTO.java
 * 创 建 人： 金明明
 * 日 期：2017-8-13
 * 修 改 人： 
 * 日 期： 
 * 描 述：订单预支付DTO
 */
public class WasherOrderPayDTO extends BaseDTO {
	public enum WasherOrderPayDTOEnum implements BaseStateDTOEnum{
		/**
		 * 801.支付平台不存在
		 */
		PAY_PLATFORM_ERROR(801, "支付平台不存在"),
		/**
		 * 802.预支付失败
		 */
		FAIL(802, "预支付失败");
		
		private Integer stateCode;
		private String msg;
		private WasherOrderPayDTOEnum(Integer stateCode, String msg) {
			this.stateCode = stateCode;
			this.msg = msg;
		}
		@Override
		public String getMsg() {
			return this.msg;
		}

		@Override
		public Integer getStateCode() {
			return this.stateCode;
		}
	}
	private String outSn; // 订单号
	private String callBackUrl; // 回调接口地址
	private BigDecimal payPrice; // 订单支付金额
	
	private String aliPayString; // 支付宝预支付字符串
	private Map<String, Object> weixResMap; // 微信支付预支付结果
	private Map<String, Object> bankResMap; // 银行卡支付预支付结果
	
	public String getOutSn() {
		return outSn;
	}
	public void setOutSn(String outSn) {
		this.outSn = outSn;
	}
	public String getCallBackUrl() {
		return callBackUrl;
	}
	public void setCallBackUrl(String callBackUrl) {
		this.callBackUrl = callBackUrl;
	}
	public BigDecimal getPayPrice() {
		return payPrice;
	}
	public void setPayPrice(BigDecimal payPrice) {
		this.payPrice = payPrice;
	}
	public Map<String, Object> getWeixResMap() {
		return weixResMap;
	}
	public void setWeixResMap(Map<String, Object> weixResMap) {
		this.weixResMap = weixResMap;
	}
	public Map<String, Object> getBankResMap() {
		return bankResMap;
	}
	public void setBankResMap(Map<String, Object> bankResMap) {
		this.bankResMap = bankResMap;
	}
	public String getAliPayString() {
		return aliPayString;
	}
	public void setAliPayString(String aliPayString) {
		this.aliPayString = aliPayString;
	}
	
}

