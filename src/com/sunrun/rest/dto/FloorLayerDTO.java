package com.sunrun.rest.dto;
import com.sunrun.washer.entity.*;
/**
 * 文 件 名 : FloorLayerDTO.java
 * 创 建 人： 金明明
 * 日 期：2017-7-30
 * 修 改 人： 
 * 日 期： 
 * 描 述：楼层DTO
 */
public class FloorLayerDTO {
	private Integer floorLayerId; // 楼层ID
	private String name; // 楼层名
	private Integer layer; // 第几层
	private Integer layerX; // 楼层坐标x
	private Integer layerY; // 楼层坐标y
	private Integer machineCount = 0; // 该楼层洗衣机数量
	public FloorLayerDTO() {};
	
	/**
	 * 初始化楼层
	 * @param floorLayer
	 */
	public FloorLayerDTO(FloorLayer floorLayer) {
		this.floorLayerId = floorLayer.getFloorLayerId();
		this.name = floorLayer.getName();
		this.layerX = floorLayer.getLayerX();
		this.layerY = floorLayer.getLayerY();
		this.layer = floorLayer.getLayer();
	}

	public Integer getFloorLayerId() {
		return floorLayerId;
	}

	public void setFloorLayerId(Integer floorLayerId) {
		this.floorLayerId = floorLayerId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
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

	public Integer getMachineCount() {
		return machineCount;
	}

	public void setMachineCount(Integer machineCount) {
		this.machineCount = machineCount;
	}
}

