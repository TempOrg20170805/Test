package com.sunrun.washer.model;

import java.math.BigDecimal;

/**
 * 文 件 名 : 消费日志管理ModelSave
 * 创 建 人： 金明明
 * 日 期：2017-8-6
 * 修 改 人： 
 * 日 期： 
 * 描 述：消费日志管理 查询条件
 */
public class WalletLogModelSave {
	private Integer userId;
	private Integer type; // 交易类型 
	private Integer payPlatform; // 交易平台
	private BigDecimal money; // 交易金额
	private String msg; // 交易信息
	private BigDecimal moneyBefore; // 变更前钱包金额（不包含第三方支付）
	private BigDecimal moneyAfter; // 变更后钱包金额（不包含第三方支付）
	
	public WalletLogModelSave() {}
	
	/**
	 * 保存构造
	 * @param type 交易类型
	 * @param payWay 交易方式 
	 * @param payPlatform 交易平台
	 * @param money 交易金额
	 * @param msg 交易信息
	 * @param moneyBefore 变更前钱包金额（不包含第三方支付）
	 * @param moneyAfter 变更后钱包金额（不包含第三方支付）
	 */
	public WalletLogModelSave(Integer userId,Integer type, Integer payPlatform,BigDecimal money,String msg,BigDecimal moneyBefore,BigDecimal moneyAfter) {
		this.userId = userId;
		this.type = type;
		this.payPlatform = payPlatform;
		this.money = money;
		this.msg = msg;
		this.moneyBefore = moneyBefore;
		this.moneyAfter = moneyAfter;
	}
	
	public Integer getUserId() {
		return userId;
	}
	public void setUserId(Integer userId) {
		this.userId = userId;
	}
	public Integer getType() {
		return type;
	}
	public void setType(Integer type) {
		this.type = type;
	}
	public java.math.BigDecimal getMoney() {
		return money;
	}
	public void setMoney(java.math.BigDecimal money) {
		this.money = money;
	}
	public String getMsg() {
		return msg;
	}
	public void setMsg(String msg) {
		this.msg = msg;
	}

	public Integer getPayPlatform() {
		return payPlatform;
	}

	public void setPayPlatform(Integer payPlatform) {
		this.payPlatform = payPlatform;
	}

	public BigDecimal getMoneyBefore() {
		return moneyBefore;
	}

	public void setMoneyBefore(BigDecimal moneyBefore) {
		this.moneyBefore = moneyBefore;
	}

	public BigDecimal getMoneyAfter() {
		return moneyAfter;
	}

	public void setMoneyAfter(BigDecimal moneyAfter) {
		this.moneyAfter = moneyAfter;
	}
	
	
}

