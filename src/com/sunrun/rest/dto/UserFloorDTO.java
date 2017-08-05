package com.sunrun.rest.dto;
import com.sunrun.washer.entity.*;
/**
 * 文 件 名 : UserFloorDTO.java
 * 创 建 人： 金明明
 * 日 期：2017-7-31
 * 修 改 人： 
 * 日 期： 
 * 描 述：用户关联楼DTO
 */
public class UserFloorDTO {
	private Integer userFloorId; // 用户楼ID
	private Integer floorId; // 楼ID
	private String name; // 楼名
	private String addressDetail; // 地址详情
	private Integer machineCount; // 该楼拥有洗衣机数量
	
	public UserFloorDTO() {};
	
	/**
	 * 初始化用户关联楼
	 * @param userFloor
	 */
	public UserFloorDTO(UserFloor userFloor, Integer machineCount) {
		this.userFloorId = userFloor.getUserFloorId();
		this.floorId = userFloor.getFloor().getFloorId();
		this.name = userFloor.getFloor().getName();
		this.addressDetail = userFloor.getFloor().getAddressDetail();
		this.machineCount = machineCount;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getAddressDetail() {
		return addressDetail;
	}

	public void setAddressDetail(String addressDetail) {
		this.addressDetail = addressDetail;
	}

	public Integer getMachineCount() {
		return machineCount;
	}

	public void setMachineCount(Integer machineCount) {
		this.machineCount = machineCount;
	}

	public Integer getFloorId() {
		return floorId;
	}

	public void setFloorId(Integer floorId) {
		this.floorId = floorId;
	}

	public Integer getUserFloorId() {
		return userFloorId;
	}

	public void setUserFloorId(Integer userFloorId) {
		this.userFloorId = userFloorId;
	}
	
	
	
}

