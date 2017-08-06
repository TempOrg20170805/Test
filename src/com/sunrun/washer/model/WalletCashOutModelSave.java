package com.sunrun.washer.model;

import java.math.BigDecimal;

/**
 * 文 件 名 : WalletCashOutModelSave.java
 * 创 建 人： 金明明
 * 日 期：2017-8-6
 * 修 改 人： 
 * 日 期： 
 * 描 述：提现管理 查询条件
 */
public class WalletCashOutModelSave {
	private Integer userId; // 用户Id
	private BigDecimal money; // 提现金额
	private Integer cardId; // 提现卡Id
	
	public WalletCashOutModelSave() {}
	public WalletCashOutModelSave(Integer userId, BigDecimal money, Integer cardId) {
		this.userId = userId;
		this.money = money;
		this.cardId = cardId;
	}
	
	public Integer getUserId() {
		return userId;
	}
	public void setUserId(Integer userId) {
		this.userId = userId;
	}
	public BigDecimal getMoney() {
		return money;
	}
	public void setMoney(BigDecimal money) {
		this.money = money;
	}
	public Integer getCardId() {
		return cardId;
	}
	public void setCardId(Integer cardId) {
		this.cardId = cardId;
	}
	
	

}

