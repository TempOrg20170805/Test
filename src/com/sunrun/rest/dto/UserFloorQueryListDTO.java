package com.sunrun.rest.dto;


import java.util.List;

/**
 * 文 件 名 : UserFloorQueryListDTO.java
 * 创 建 人： 金明明
 * 日 期：2017-7-31
 * 修 改 人： 
 * 日 期： 
 * 描 述：用户关联楼DTO 查询结果不分页
 */
public class UserFloorQueryListDTO extends BaseDTO {
	public enum UserFloorQueryListDTOEnum implements BaseStateDTOEnum{
		;
		
		private Integer stateCode;
		private String msg;
		private UserFloorQueryListDTOEnum(Integer stateCode, String msg) {
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

	private List<UserFloorDTO> userFloorDTOs;
	
	public List<UserFloorDTO> getUserFloorDTOs() {
		return userFloorDTOs;
	}
	public void setUserFloorDTOs(
			List<UserFloorDTO> userFloorDTOs) {
		this.userFloorDTOs = userFloorDTOs;
	}
}

