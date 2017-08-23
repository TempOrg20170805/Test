package com.sunrun.rest.dto;



/**
 * 文 件 名 : UserMachineAllCountDTO.java
 * 创 建 人： 金明明
 * 日 期：2017-8-15
 * 修 改 人： 
 * 日 期： 
 * 描 述：用户洗衣机数量统计
 */
public class UserMachineAllCountDTO extends BaseDTO {
	public enum UserMachineAllCountDTOEnum implements BaseStateDTOEnum{
		;
		
		private Integer stateCode;
		private String msg;
		private UserMachineAllCountDTOEnum(Integer stateCode, String msg) {
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
	private Integer totalCount; // 所有数据量

	public Integer getTotalCount() {
		return totalCount;
	}
	public void setTotalCount(Integer totalCount) {
		this.totalCount = totalCount;
	}

}

