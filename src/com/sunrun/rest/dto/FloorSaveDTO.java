package com.sunrun.rest.dto;


import java.util.List;

/**
 * 文 件 名 : FloorSaveDTO.java
 * 创 建 人： 金明明
 * 日 期：2017-8-1
 * 修 改 人： 
 * 日 期： 
 * 描 述：楼DTO 添加结果
 */
public class FloorSaveDTO extends BaseDTO {
	public enum FloorSaveDTOEnum implements BaseStateDTOEnum{
		;
		
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
}

