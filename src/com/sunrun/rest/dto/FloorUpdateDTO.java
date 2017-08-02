package com.sunrun.rest.dto;


import java.util.List;

/**
 * 文 件 名 : FloorUpdateDTO.java
 * 创 建 人： 金明明
 * 日 期：2017-8-1
 * 修 改 人： 
 * 日 期： 
 * 描 述：楼DTO 更新结果
 */
public class FloorUpdateDTO extends BaseDTO {
	public enum FloorUpdateDTOEnum implements BaseStateDTOEnum{
		;
		
		private Integer stateCode;
		private String msg;
		private FloorUpdateDTOEnum(Integer stateCode, String msg) {
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
}

