package com.sunrun.rest.dto;


import java.util.List;

/**
 * 文 件 名 : FloorLayerSaveDTO.java
 * 创 建 人： 金明明
 * 日 期：2017-7-30
 * 修 改 人： 
 * 日 期： 
 * 描 述：楼层DTO 添加结果
 */
public class FloorLayerSaveDTO extends BaseDTO {
	public enum FloorLayerSaveDTOEnum implements BaseStateDTOEnum{
		;
		
		private Integer stateCode;
		private String msg;
		private FloorLayerSaveDTOEnum(Integer stateCode, String msg) {
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
	private Integer floorLayerId; // 楼层列表
	public Integer getFloorLayerId() {
		return floorLayerId;
	}
	public void setFloorLayerId(Integer floorLayerId) {
		this.floorLayerId = floorLayerId;
	}
	
}

