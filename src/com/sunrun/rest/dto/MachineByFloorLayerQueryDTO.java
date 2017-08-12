package com.sunrun.rest.dto;


import java.util.List;

import javax.mail.Flags.Flag;

import com.sunrun.washer.entity.FloorLayer;

/**
 * 文 件 名 : MachineByFloorLayerQueryDTO.java
 * 创 建 人： 金明明
 * 日 期：2017-08-05
 * 修 改 人： 
 * 日 期： 
 * 描 述：根据楼层查询洗衣机DTO 查询结果
 */
public class MachineByFloorLayerQueryDTO extends BaseDTO {
	public enum MachineByFloorLayerQueryDTOEnum implements BaseStateDTOEnum{
		;
		
		private Integer stateCode;
		private String msg;
		private MachineByFloorLayerQueryDTOEnum(Integer stateCode, String msg) {
			this.stateCode = stateCode;
			this.msg = msg;
		}
		@Override
		public String getMsg() {
			return this.msg;
		}

		@Override
		public Integer getStateCode() {
			return this.stateCode;
		}
		
	}
	private Integer totalCount; // 所有数据量
	private Integer floorLayerId; // 楼层ID
	private String name; // 楼层名
	private Integer layer; // 第几层
	private Integer layerX; // 楼层坐标x
	private Integer layerY; // 楼层坐标y

	private List<MachineByFloorLayerDTO> machineDTOs;
	
	public Integer getTotalCount() {
		return totalCount;
	}
	public void setTotalCount(Integer totalCount) {
		this.totalCount = totalCount;
	}
	public List<MachineByFloorLayerDTO> getMachineDTOs() {
		return machineDTOs;
	}
	public void setMachineDTOs(
			List<MachineByFloorLayerDTO> machineDTOs) {
		this.machineDTOs = machineDTOs;
	}
	
	public void setFloorLayer(FloorLayer floorLayer) {
		this.floorLayerId = floorLayer.getFloorLayerId();
		this.name = floorLayer.getName();
		this.layerX = floorLayer.getLayerX();
		this.layerY = floorLayer.getLayerY();
		this.layer = floorLayer.getLayer();
	}
	public Integer getFloorLayerId() {
		return floorLayerId;
	}
	public String getName() {
		return name;
	}
	public Integer getLayer() {
		return layer;
	}
	public Integer getLayerX() {
		return layerX;
	}
	public Integer getLayerY() {
		return layerY;
	}
	
	
	
}

