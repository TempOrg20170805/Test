package com.sunrun.rest.dto;


import java.util.List;

/**
 * 文 件 名 : UserMachineQueryListDTO.java
 * 创 建 人： 金明明
 * 日 期：2017-7-30
 * 修 改 人： 
 * 日 期： 
 * 描 述：用户关联洗衣机DTO 查询结果不分页
 */
public class UserMachineQueryListDTO extends BaseDTO {
	public enum UserMachineQueryListDTOEnum implements BaseStateDTOEnum{
		;
		
		private Integer stateCode;
		private String msg;
		private UserMachineQueryListDTOEnum(Integer stateCode, String msg) {
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

	private List<UserMachineDTO> userMachineDTOs;
	
	public List<UserMachineDTO> getUserMachineDTOs() {
		return userMachineDTOs;
	}
	public void setUserMachineDTOs(
			List<UserMachineDTO> userMachineDTOs) {
		this.userMachineDTOs = userMachineDTOs;
	}
}

