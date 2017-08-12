package com.sunrun.rest.dto;


import java.util.List;

/**
 * 文 件 名 : WasherOrderUpdateDTO.java
 * 创 建 人： 金明明
 * 日 期：2017-8-7
 * 修 改 人： 
 * 日 期： 
 * 描 述：订单DTO 更新结果
 */
public class WasherOrderUpdateDTO extends BaseDTO {
	public enum WasherOrderUpdateDTOEnum implements BaseStateDTOEnum{
		;
		
		private Integer stateCode;
		private String msg;
		private WasherOrderUpdateDTOEnum(Integer stateCode, String msg) {
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

