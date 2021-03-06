package com.sunrun.rest.dto;
import java.util.Date;

import com.sunrun.washer.entity.*;
/**
 * 文 件 名 : MachineDTO.java
 * 创 建 人： 金明明
 * 日 期：2017-7-31
 * 修 改 人： 
 * 日 期： 
 * 描 述：洗衣机DTO
 */
public class MachineDTO {
	
	private Integer machineId; // 洗衣机id
	private String name; // 洗衣机名称
	private String type; // 洗衣机型号
	private String machineNo; // 序列号
	private Integer status = 1; // 状态 0.删除 1.空闲 2.使用中 3.停用
	private Integer floorLayerX; // 楼层位置x
	private Integer floorLayerY; // 楼层位置y
	private Date createTime = new Date(); // 创建时间
	
	public MachineDTO() {};
	
	/**
	 * 初始化洗衣机
	 * @param machine
	 */
	public MachineDTO(Machine machine) {
		this.machineId = machine.getMachineId();
		this.name = machine.getName();
		this.type = machine.getType();
		this.machineNo = machine.getMachineNo();
		this.status = machine.getStatus();
		this.floorLayerX = machine.getFloorLayerX();
		this.floorLayerY = machine.getFloorLayerY();
		this.createTime = machine.getCreateTime();
	}

	public Integer getMachineId() {
		return machineId;
	}

	public void setMachineId(Integer machineId) {
		this.machineId = machineId;
	}

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

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
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

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}
	
	
}

