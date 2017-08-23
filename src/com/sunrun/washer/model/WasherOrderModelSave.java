package com.sunrun.washer.model;

/**
 * 文 件 名 : WasherOrderModelSave.java
 * 创 建 人： 金明明
 * 日 期：2017-8-7
 * 修 改 人： 
 * 日 期： 
 * 描 述：订单 查询条件
 */
public class WasherOrderModelSave {

	private Integer userId; // 买家ID
	private Integer modeId; // 洗衣模式ID
	private Integer machineId; // 洗衣机ID
	public Integer getUserId() {
		return userId;
	}
	public void setUserId(Integer userId) {
		this.userId = userId;
	}
	public Integer getModeId() {
		return modeId;
	}
	public void setModeId(Integer modeId) {
		this.modeId = modeId;
	}
	public Integer getMechineId() {
		return machineId;
	}
	public void setMechineId(Integer machineId) {
		this.machineId = machineId;
	}

	
}

