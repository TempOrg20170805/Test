package com.sunrun.rest.dto;
import java.util.Date;

import com.sunrun.washer.entity.FloorLayer;
import com.sunrun.washer.entity.UserMachine;
/**
 * 文 件 名 : UserMachineDTO.java
 * 创 建 人： 金明明
 * 日 期：2017-7-30
 * 修 改 人： 
 * 日 期： 
 * 描 述：用户关联洗衣机DTO
 */
public class UserMachineDTO {
	
	private String name; // 洗衣机名称
	private String type; // 洗衣机型号
	private String machineNo; // 序列号
	private Integer status; // 状态 0.删除 1.空闲 2.使用中 3.停用
	private Integer floorLayerId; // 楼层Id
	private Integer floorLayerX; // 楼层位置x
	private Integer floorLayerY; // 楼层位置y
	private Date createTime; // 创建时间
	private Date useTime; // 投放时间
	private Date baseUpdateTime; // 基本数据更新时间 
	
	
	public UserMachineDTO() {};
	
	/**
	 * 初始化用户关联洗衣机
	 * @param userMachine
	 */
	public UserMachineDTO(UserMachine userMachine) {
		this.name = userMachine.getMachine().getName();
		this.type = userMachine.getMachine().getType();
		this.machineNo = userMachine.getMachine().getMachineNo();
		this.status = userMachine.getMachine().getStatus();
		FloorLayer floorLayer = userMachine.getMachine().getFloorLayer();
		if (floorLayer != null) {
			this.floorLayerId = floorLayer.getFloorLayerId();
		}
		this.floorLayerX = userMachine.getMachine().getFloorLayerX();
		this.floorLayerY = userMachine.getMachine().getFloorLayerY();
		this.createTime = userMachine.getMachine().getCreateTime();
		this.useTime = userMachine.getMachine().getUseTime();
		this.baseUpdateTime = userMachine.getMachine().getBaseUpdateTime();
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

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	public Date getUseTime() {
		return useTime;
	}

	public void setUseTime(Date useTime) {
		this.useTime = useTime;
	}

	public Date getBaseUpdateTime() {
		return baseUpdateTime;
	}

	public void setBaseUpdateTime(Date baseUpdateTime) {
		this.baseUpdateTime = baseUpdateTime;
	}
	
	
	
}

