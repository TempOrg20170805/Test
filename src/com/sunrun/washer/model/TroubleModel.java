package com.sunrun.washer.model;

/**
 * 文 件 名 : TroubleModel.java
 * 创 建 人： 金明明
 * 日 期：2017-8-13
 * 修 改 人： 
 * 日 期： 
 * 描 述：故障 查询条件
 */
public class TroubleModel {
	private Integer status; // 状态  1.待维修 2.维修中 3.维修完毕
	private Integer userId; // 用户ID
	private String userName; // 用户名
	private String machineNo; // 洗衣机序列号
	public Integer getStatus() {
		return status;
	}
	public void setStatus(Integer status) {
		this.status = status;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getMachineNo() {
		return machineNo;
	}
	public void setMachineNo(String machineNo) {
		this.machineNo = machineNo;
	}
	public Integer getUserId() {
		return userId;
	}
	public void setUserId(Integer userId) {
		this.userId = userId;
	}
	
	
}

