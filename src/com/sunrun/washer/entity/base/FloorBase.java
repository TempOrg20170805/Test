package com.sunrun.washer.entity.base;


import java.util.Date;

public class FloorBase implements java.io.Serializable {
	
	private Integer floorId; // id
	private String name; // 楼名
	private Integer status; // 状态 0.删除 1.使用中
	private String addressDetail; // 地址详情
	private Date createTime; // 创建时间
	private Date baseUpdateTime; // 基本数据更新时间 

	
	public FloorBase() {
	}

	public FloorBase(Integer floorId) {
		this.floorId = floorId;
	}
	
	public Integer getFloorId() {
		return this.floorId;
	}
 
	public void setFloorId(Integer floorId) {
		this.floorId = floorId;
	}
	public String getName() {
		return this.name;
	}
 
	public void setName(String name) {
		this.name = name;
	}
	public Integer getStatus() {
		return this.status;
	}
 
	public void setStatus(Integer status) {
		this.status = status;
	}
	
	public String getAddressDetail() {
		return this.addressDetail;
	}
 
	public void setAddressDetail(String addressDetail) {
		this.addressDetail = addressDetail;
	}
	public Date getCreateTime() {
		return this.createTime;
	}
 
	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}
	public Date getBaseUpdateTime() {
		return this.baseUpdateTime;
	}
 
	public void setBaseUpdateTime(Date baseUpdateTime) {
		this.baseUpdateTime = baseUpdateTime;
	}

}

