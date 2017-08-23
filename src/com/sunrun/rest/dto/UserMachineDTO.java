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
	private Integer machineId; // 洗衣机ID
	private String name; // 洗衣机名称
	private String type; // 洗衣机型号
	private String machineNo; // 序列号
	private Integer status; // 状态 0.删除 1.空闲 2.使用中 3.停用
	private Date createTime; // 创建时间
	
	
	public UserMachineDTO() {};
	
	/**
	 * 初始化用户关联洗衣机
	 * @param userMachine
	 */
	public UserMachineDTO(UserMachine userMachine) {
		this.machineId = userMachine.getMachine().getMachineId();
		this.name = userMachine.getMachine().getName();
		this.type = userMachine.getMachine().getType();
		this.machineNo = userMachine.getMachine().getMachineNo();
		this.status = userMachine.getMachine().getStatus();
		this.createTime = userMachine.getMachine().getCreateTime();
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

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

}

