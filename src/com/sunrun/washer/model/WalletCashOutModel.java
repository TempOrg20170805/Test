package com.sunrun.washer.model;

/**
 * 文 件 名 : WalletCashOutModel.java
 * 创 建 人： 金明明
 * 日 期：2017-8-6
 * 修 改 人： 
 * 日 期： 
 * 描 述：提现管理 查询条件
 */
public class WalletCashOutModel {
	private Integer userId; // 根据用户ID查询提现记录
	private String username; // 用户名
	private Integer state = 0; // 提现状态 0.所有状态 1.提现申请中 2.提现成功 3.提现失败
	
	
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public Integer getState() {
		return state;
	}
	public void setState(Integer state) {
		this.state = state;
	}
	public Integer getUserId() {
		return userId;
	}
	public void setUserId(Integer userId) {
		this.userId = userId;
	}
}

