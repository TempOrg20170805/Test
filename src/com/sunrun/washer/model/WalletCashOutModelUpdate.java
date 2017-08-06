package com.sunrun.washer.model;

/**
 * 文 件 名 : WalletCashOutModelUpdate.java
 * 创 建 人： 金明明
 * 日 期：2017-8-6
 * 修 改 人： 
 * 日 期： 
 * 描 述：提现管理 查询条件
 */
public class WalletCashOutModelUpdate {
	private Integer walletCashOutId; // 提现Id
	private Integer state; // 提现结果状态： 1.提现申请中 2.提现成功 3.提现失败
	public Integer getWalletCashOutId() {
		return walletCashOutId;
	}
	public void setWalletCashOutId(Integer walletCashOutId) {
		this.walletCashOutId = walletCashOutId;
	}
	public Integer getState() {
		return state;
	}
	public void setState(Integer state) {
		this.state = state;
	}
	
	

}

