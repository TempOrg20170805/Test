package com.sunrun.washer.entity.base;


import java.util.Date;

import com.sunrun.washer.entity.FloorLayer;
import com.sunrun.washer.enums.MachineStatusEnum;

public class MachineBase implements java.io.Serializable {
	
	private Integer machineId; // id
	private String name; // 洗衣机名称
	private String type; // 洗衣机型号
	private String machineNo; // 序列号
	private Integer online = 0; // 0-离线 1-在线
	private Integer status = 1; // 状态 0.删除 1.空闲 2.使用中 3.停用
	private FloorLayer floorLayer; // 楼层
	private Integer floorLayerX; // 楼层位置x
	private Integer floorLayerY; // 楼层位置y
	private Date createTime = new Date(); // 创建时间
	private Date useTime = new Date(); // 投放时间
	private Date baseUpdateTime = new Date(); // 基本数据更新时间 
	private Integer isTrouble = 0;// 是否故障 1故障 0正常 默认0
	
	public MachineBase() {
	}

	public MachineBase(Integer machineId) {
		this.machineId = machineId;
	}

	public Integer getMachineId() {
		return this.machineId;
	}
 
	public void setMachineId(Integer machineId) {
		this.machineId = machineId;
	}
	public String getName() {
		return this.name;
	}
 
	public void setName(String name) {
		this.name = name;
	}
	public String getType() {
		return this.type;
	}
 
	public void setType(String type) {
		this.type = type;
	}
	public String getMachineNo() {
		return this.machineNo;
	}
 
	public void setMachineNo(String machineNo) {
		this.machineNo = machineNo;
	}
	public Integer getStatus() {
		return this.status;
	}
 
	public void setStatus(Integer status) {
		this.status = status;
	}
	
	public String getStatusStr() {
		return MachineStatusEnum.getMap().get(this.status);
	}
	
	public FloorLayer getFloorLayer() {
		return floorLayer;
	}

	public void setFloorLayer(FloorLayer floorLayer) {
		this.floorLayer = floorLayer;
	}

	public Integer getFloorLayerX() {
		return this.floorLayerX;
	}
 
	public void setFloorLayerX(Integer floorLayerX) {
		this.floorLayerX = floorLayerX;
	}
	public Integer getFloorLayerY() {
		return this.floorLayerY;
	}
 
	public void setFloorLayerY(Integer floorLayerY) {
		this.floorLayerY = floorLayerY;
	}
	public Date getCreateTime() {
		return this.createTime;
	}
 
	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}
	public Date getUseTime() {
		return this.useTime;
	}
 
	public void setUseTime(Date useTime) {
		this.useTime = useTime;
	}
	public Date getBaseUpdateTime() {
		return this.baseUpdateTime;
	}
 
	public void setBaseUpdateTime(Date baseUpdateTime) {
		this.baseUpdateTime = baseUpdateTime;
	}

	public Integer getOnline() {
		return online;
	}

	public void setOnline(Integer online) {
		this.online = online;
	}

	public Integer getIsTrouble() {
		return isTrouble;
	}

	public void setIsTrouble(Integer isTrouble) {
		this.isTrouble = isTrouble;
	}

}

