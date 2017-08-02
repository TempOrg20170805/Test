package com.sunrun.washer.model;


/**
 * 文 件 名 : FloorLayerModelSave.java
 * 创 建 人： 金明明
 * 日 期：2017-7-30
 * 修 改 人： 
 * 日 期： 
 * 描 述：楼层 查询条件
 */
public class FloorLayerModelSave {
	private String name; // 楼层名
	private Integer floorId; // 楼Id
	private Integer layer; // 第几层
	private Integer layerX; // 楼层规格x
	private Integer layerY; // 楼层规格y
	
	public FloorLayerModelSave() {}
	public FloorLayerModelSave(String name,Integer layer,Integer layerX,Integer layerY,Integer floorId) {
		this.name = name;
		this.layer = layer;
		this.layerX = layerX;
		this.layerY = layerY;
		this.floorId = floorId;
	}
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public Integer getFloorId() {
		return floorId;
	}
	public void setFloorId(Integer floorId) {
		this.floorId = floorId;
	}
	public Integer getLayer() {
		return layer;
	}
	public void setLayer(Integer layer) {
		this.layer = layer;
	}
	public Integer getLayerX() {
		return layerX;
	}
	public void setLayerX(Integer layerX) {
		this.layerX = layerX;
	}
	public Integer getLayerY() {
		return layerY;
	}
	public void setLayerY(Integer layerY) {
		this.layerY = layerY;
	}
}

