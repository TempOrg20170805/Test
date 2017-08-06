package com.sunrun.washer.model;

/**
 * 文 件 名 : WalletCardModel.java
 * 创 建 人： 金明明
 * 日 期：2017-8-6
 * 修 改 人： 
 * 日 期： 
 * 描 述：银行卡/支付宝管理 查询条件
 */
public class WalletCardModel {

	private Integer userId; // 用户Id
	private Integer type; // 类型 0.所有银行卡 1.银行卡 2.支付宝 3.微信

	
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
	
}

