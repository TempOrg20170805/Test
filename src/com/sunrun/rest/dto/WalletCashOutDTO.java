package com.sunrun.rest.dto;

import java.math.BigDecimal;
import java.util.Date;

import com.sunrun.washer.entity.WalletCashOut;

/**
 * 文 件 名 : WalletCashOutDTO
 * 创 建 人： 金明明
 * 日 期：2017-8-6
 * 修 改 人： 
 * 日 期： 
 * 描 述：消费日志管理DTO
 */
public class WalletCashOutDTO {
	private Integer id;
	private Integer state; // 交易状态 1.运费付款 2.司机服务费付款
	private BigDecimal money; // 交易金额
	private Date time; // 交易时间
	
	public WalletCashOutDTO(WalletCashOut walletCashOut) {
		this.id = walletCashOut.getCashOutId();
		this.state = walletCashOut.getState();
		this.money = walletCashOut.getMoney();
		this.time = walletCashOut.getCreateTime();
	}
	
	public java.lang.Integer getId() {
		return id;
	}
	public void setId(java.lang.Integer id) {
		this.id = id;
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

	public Integer getState() {
		return state;
	}

	public void setState(Integer state) {
		this.state = state;
	}

}

