package com.sunrun.washer.model;

/**
 * 文 件 名 : UserMachineModelUpdatePutIn.java
 * 创 建 人： 金明明
 * 日 期：2017-8-3
 * 修 改 人： 
 * 日 期： 
 * 描 述：渠道商投放洗衣机
 */
public class UserMachineModelUpdatePutIn {
	private Integer machineId; // 洗衣机ID
	private Integer floorLayerId; // 楼层Id
	private Integer floorLayerX; // 楼层位置x
	private Integer floorLayerY; // 楼层位置y
	
	public UserMachineModelUpdatePutIn() {
		
	}
	
	public UserMachineModelUpdatePutIn(Integer machineId, Integer floorLayerId,Integer floorLayerX,Integer floorLayerY) {
		this.machineId = machineId;
		this.floorLayerId = floorLayerId;
		this.floorLayerX = floorLayerX;
		this.floorLayerY = floorLayerY;
	}
	public Integer getMachineId() {
		return machineId;
	}
	public void setMachineId(Integer machineId) {
		this.machineId = machineId;
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
}

