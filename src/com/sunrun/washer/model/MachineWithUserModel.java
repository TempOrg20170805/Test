package com.sunrun.washer.model;

/**
 * 文 件 名 : MachineWithUserModel.java
 * 创 建 人： 金明明
 * 日 期：2017-8-27
 * 修 改 人： 
 * 日 期： 
 * 描 述：洗衣机 查询条件
 */
public class MachineWithUserModel {
	
	private String machineNo; // 序列号
	private Integer type=0; // 查询类型 0.所有洗衣机 1.查询未授权洗衣机 2.查询已授权洗衣机 
	
	public String getMachineNo() {
		return machineNo;
	}
	public void setMachineNo(String machineNo) {
		this.machineNo = machineNo;
	}
	public Integer getType() {
		return type;
	}
	public void setType(Integer type) {
		this.type = type;
	}
	
}

