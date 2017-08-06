package com.sunrun.rest.dto;

import com.sunrun.washer.entity.WalletLog;

/**
 * 文 件 名 : WalletLogDTO
 * 创 建 人： 金明明
 * 日 期：2017-8-6
 * 修 改 人： 
 * 日 期： 
 * 描 述：消费日志管理DTO
 */
public class WalletLogDTO {
	private java.lang.Integer id;
	private java.lang.Integer type; // 交易类型 1.运费付款 2.司机服务费付款
	private Integer payPlatform; // 交易平台 1.钱包平台 2.支付宝平台 3.微信平台 4.银行卡平台
	private java.math.BigDecimal money; // 交易金额
	private java.util.Date time; // 交易时间
	private java.lang.String msg; // 交易信息
	
	public WalletLogDTO(WalletLog walletLog) {
		this.id = walletLog.getId();
		this.type = walletLog.getType();
		this.payPlatform = walletLog.getPayPlatform();
		this.money = walletLog.getMoney();
		this.time = walletLog.getTime();
		this.msg = walletLog.getMsg();
	}
	
	public java.lang.Integer getId() {
		return id;
	}
	public void setId(java.lang.Integer id) {
		this.id = id;
	}
	public java.lang.Integer getType() {
		return type;
	}
	public void setType(java.lang.Integer type) {
		this.type = type;
	}
	public java.math.BigDecimal getMoney() {
		return money;
	}

	public void setMoney(java.math.BigDecimal money) {
		this.money = money;
	}

	public java.util.Date getTime() {
		return time;
	}
	public void setTime(java.util.Date time) {
		this.time = time;
	}
	public java.lang.String getMsg() {
		return msg;
	}
	public void setMsg(java.lang.String msg) {
		this.msg = msg;
	}

	public Integer getPayPlatform() {
		return payPlatform;
	}

	public void setPayPlatform(Integer payPlatform) {
		this.payPlatform = payPlatform;
	}
	
	


}

