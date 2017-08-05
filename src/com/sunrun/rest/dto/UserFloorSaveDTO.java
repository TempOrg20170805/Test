package com.sunrun.rest.dto;


import java.util.List;

/**
 * 文 件 名 : UserFloorSaveDTO.java
 * 创 建 人： 金明明
 * 日 期：2017-7-31
 * 修 改 人： 
 * 日 期： 
 * 描 述：用户关联楼DTO 添加结果
 */
public class UserFloorSaveDTO extends BaseDTO {
	public enum UserFloorSaveDTOEnum implements BaseStateDTOEnum{
		FLOOR_NAME_EXIST(601, "该楼名称已存在");
		
		private Integer stateCode;
		private String msg;
		private UserFloorSaveDTOEnum(Integer stateCode, String msg) {
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

