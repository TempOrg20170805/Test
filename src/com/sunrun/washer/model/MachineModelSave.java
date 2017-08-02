package com.sunrun.washer.model;

/**
 * 文 件 名 : MachineModelSave.java
 * 创 建 人： 金明明
 * 日 期：2017-7-31
 * 修 改 人： 
 * 日 期： 
 * 描 述：洗衣机 查询条件
 */
public class MachineModelSave {
	private String name; // 洗衣机名称
	private String type; // 洗衣机型号
	private String machineNo; // 序列号
	private Integer floorLayerId; // 楼层Id
	private Integer floorLayerX; // 楼层位置x
	private Integer floorLayerY; // 楼层位置y
	
	private String userName;// 用户名（渠道商的用户名，拥有该洗衣机）
	
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public String getMachineNo() {
		return machineNo;
	}
	public void setMachineNo(String machineNo) {
		this.machineNo = machineNo;
	}
	public Integer getFloorLayerId() {
		return floorLayerId;
	}
	public void setFloorLayerId(Integer floorLayerId) {
		this.floorLayerId = floorLayerId;
	}
	public Integer getFloorLayerX() {
		return floorLayerX;
	}
	public void setFloorLayerX(Integer floorLayerX) {
		this.floorLayerX = floorLayerX;
	}
	public Integer getFloorLayerY() {
		return floorLayerY;
	}
	public void setFloorLayerY(Integer floorLayerY) {
		this.floorLayerY = floorLayerY;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	
}

