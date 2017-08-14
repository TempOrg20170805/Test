package com.sunrun.rest.dto;


import java.util.List;

import com.sunrun.washer.entity.Floor;

/**
 * 文 件 名 : FloorSaveDTO.java
 * 创 建 人： 金明明
 * 日 期：2017-8-1
 * 修 改 人： 
 * 日 期： 
 * 描 述：楼DTO 添加结果
 */
public class FloorByAddressDetailQueryDTO extends BaseDTO {
	
	
	public enum FloorSaveDTOEnum implements BaseStateDTOEnum{
		FLOOR_ADDRESS_DETAIL_NOT_EXIST(501, "该楼名称不存在");
		
		private Integer stateCode;
		private String msg;
		private FloorSaveDTOEnum(Integer stateCode, String msg) {
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
	
	private Integer floorId; // 楼ID
	private String addressDetail; // 地址详情
	
	private List<FloorLayerDTO> floorLayerDTOs; // 楼层列表
	
	public FloorByAddressDetailQueryDTO() {}
	public void initFloorByAddressDetailQueryDTO(Floor floor) {
		this.floorId = floor.getFloorId();
		this.addressDetail = floor.getAddressDetail();
	}
	
	public Integer getFloorId() {
		return floorId;
	}
	public void setFloorId(Integer floorId) {
		this.floorId = floorId;
	}
	public String getAddressDetail() {
		return addressDetail;
	}
	public void setAddressDetail(String addressDetail) {
		this.addressDetail = addressDetail;
	}
	public List<FloorLayerDTO> getFloorLayerDTOs() {
		return floorLayerDTOs;
	}
	public void setFloorLayerDTOs(List<FloorLayerDTO> floorLayerDTOs) {
		this.floorLayerDTOs = floorLayerDTOs;
	}
	
}

