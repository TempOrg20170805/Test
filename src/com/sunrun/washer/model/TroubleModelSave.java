package com.sunrun.washer.model;

/**
 * 文 件 名 : TroubleModelSave.java
 * 创 建 人： 金明明
 * 日 期：2017-8-13
 * 修 改 人： 
 * 日 期： 
 * 描 述：故障 查询条件
 */
public class TroubleModelSave {

	private String troubleReason; // 故障原因
	private Integer userId; // 用户ID
	private Integer machineId; // 洗衣机ID
	
	public String getTroubleReason() {
		return troubleReason;
	}
	public void setTroubleReason(String troubleReason) {
		this.troubleReason = troubleReason;
	}
	public Integer getUserId() {
		return userId;
	}
	public void setUserId(Integer userId) {
		this.userId = userId;
	}
	public Integer getMachineId() {
		return machineId;
	}
	public void setMachineId(Integer machineId) {
		this.machineId = machineId;
	}
	
}

