package com.sunrun.rest.dto;


import java.util.List;

/**
 * 文 件 名 : FloorQueryListDTO.java
 * 创 建 人： 金明明
 * 日 期：2017-8-1
 * 修 改 人： 
 * 日 期： 
 * 描 述：楼DTO 查询结果不分页
 */
public class FloorQueryListDTO extends BaseDTO {
	public enum FloorQueryListDTOEnum implements BaseStateDTOEnum{
		;
		
		private Integer stateCode;
		private String msg;
		private FloorQueryListDTOEnum(Integer stateCode, String msg) {
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

	private List<FloorDTO> floorDTOs;
	
	public List<FloorDTO> getFloorDTOs() {
		return floorDTOs;
	}
	public void setFloorDTOs(
			List<FloorDTO> floorDTOs) {
		this.floorDTOs = floorDTOs;
	}
}

