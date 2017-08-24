package com.sunrun.rest.dto;


import java.util.List;

/**
 * 文 件 名 : BankQueryListDTO.java
 * 创 建 人： 金明明
 * 日 期：2017-8-15
 * 修 改 人： 
 * 日 期： 
 * 描 述：银行DTO 查询结果
 */
public class BankQueryListDTO extends BaseDTO {
	public enum BankQueryListEnum implements BaseStateDTOEnum{
		;
		
		private Integer stateCode;
		private String msg;
		private BankQueryListEnum(Integer stateCode, String msg) {
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

	private List<BankDTO> bankDTOs;
	
	public List<BankDTO> getBankDTOs() {
		return bankDTOs;
	}
	public void setBankDTOs(
			List<BankDTO> bankDTOs) {
		this.bankDTOs = bankDTOs;
	}
}
