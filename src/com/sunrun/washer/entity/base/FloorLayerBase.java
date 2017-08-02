package com.sunrun.washer.entity.base;


import java.util.Date;

import com.sunrun.washer.entity.Floor;

public class FloorLayerBase implements java.io.Serializable {
	
	private Integer floorLayerId; // id
	private String name; // 楼层名
	private Floor floor; // 楼
	private Integer layer; // 第几层
	private Integer layerX; // 楼层坐标x
	private Integer layerY; // 楼层坐标y
	private Date createTime; // 创建时间
	private Date baseUpdateTime; // 基本数据更新时间 

	
	public FloorLayerBase() {
	}

	public FloorLayerBase(Integer floorLayerId) {
		this.floorLayerId = floorLayerId;
	}
	
	public Integer getFloorLayerId() {
		return this.floorLayerId;
	}
 
	public void setFloorLayerId(Integer floorLayerId) {
		this.floorLayerId = floorLayerId;
	}
	public String getName() {
		return this.name;
	}
 
	public void setName(String name) {
		this.name = name;
	}

	public Integer getLayer() {
		return this.layer;
	}
 
	public void setLayer(Integer layer) {
		this.layer = layer;
	}
	public Integer getLayerX() {
		return this.layerX;
	}
 
	public void setLayerX(Integer layerX) {
		this.layerX = layerX;
	}
	public Integer getLayerY() {
		return this.layerY;
	}
 
	public void setLayerY(Integer layerY) {
		this.layerY = layerY;
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

	public Floor getFloor() {
		return floor;
	}

	public void setFloor(Floor floor) {
		this.floor = floor;
	}
	

}

