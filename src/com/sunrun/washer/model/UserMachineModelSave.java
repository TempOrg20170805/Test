package com.sunrun.washer.model;

/**
 * 文 件 名 : UserMachineModelSave.java
 * 创 建 人： 金明明
 * 日 期：2017-7-30
 * 修 改 人： 
 * 日 期： 
 * 描 述：用户关联洗衣机 查询条件
 */
public class UserMachineModelSave {
	private Integer machineId; // 洗衣机ID
	private String userName; // 用户名
	private Integer useType; // 使用类型
	private Integer authLevel; // 权限等级
	public Integer getMachineId() {
		return machineId;
	}
	public void setMachineId(Integer machineId) {
		this.machineId = machineId;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public Integer getUseType() {
		return useType;
	}
	public void setUseType(Integer useType) {
		this.useType = useType;
	}
	public Integer getAuthLevel() {
		return authLevel;
	}
	public void setAuthLevel(Integer authLevel) {
		this.authLevel = authLevel;
	}
	
	

}

