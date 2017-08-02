package com.sunrun.rest.dto;


import java.util.List;

/**
 * 文 件 名 : UserMachineSaveDTO.java
 * 创 建 人： 金明明
 * 日 期：2017-7-30
 * 修 改 人： 
 * 日 期： 
 * 描 述：用户关联洗衣机DTO 添加结果
 */
public class UserMachineSaveDTO extends BaseDTO {
	public enum UserMachineSaveDTOEnum implements BaseStateDTOEnum{
		;
		
		private Integer stateCode;
		private String msg;
		private UserMachineSaveDTOEnum(Integer stateCode, String msg) {
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

