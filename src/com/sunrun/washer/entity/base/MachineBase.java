package com.sunrun.washer.entity.base;


import java.util.Date;

import com.jeecms.core.entity.*;
import com.sunrun.washer.enums.MachineStatus.MachineStatusEnum;

public class MachineBase implements java.io.Serializable {
	
	private Integer machineId; // id
	private String name; // 洗衣机名称
	private String type; // 洗衣机型号
	private String machineNo; // 序列号
	private Integer status = 1; // 状态 0.删除 1.空闲 2.使用中 3.停用
	private Integer floorLayerId; // 楼层id
	private Integer floorLayerX; // 楼层位置x
	private Integer floorLayerY; // 楼层位置y
	private Date createTime = new Date(); // 创建时间
	private Date useTime = new Date(); // 投放时间
	private Date baseUpdateTime = new Date(); // 基本数据更新时间 

	
	public MachineBase() {
	}

	public MachineBase(Integer machineId) {
		this.machineId = machineId;
	}
	
	public MachineBase(Integer machineId, String name, String type, String machineNo, Integer status, Integer floorLayerId, Integer floorLayerX, Integer floorLayerY, Date createTime, Date useTime, Date baseUpdateTime) {
		super();
		this.machineId = machineId;
		this.name = name;
		this.type = type;
		this.machineNo = machineNo;
		this.status = status;
		this.floorLayerId = floorLayerId;
		this.floorLayerX = floorLayerX;
		this.floorLayerY = floorLayerY;
		this.createTime = createTime;
		this.useTime = useTime;
		this.baseUpdateTime = baseUpdateTime;

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
	public Integer getFloorLayerId() {
		return this.floorLayerId;
	}
 
	public void setFloorLayerId(Integer floorLayerId) {
		this.floorLayerId = floorLayerId;
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

}

