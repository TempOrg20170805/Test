package com.sunrun.rest.dto;


import java.util.List;

/**
 * 文 件 名 : ModeQueryListDTO.java
 * 创 建 人： 金明明
 * 日 期：2017-8-6
 * 修 改 人： 
 * 日 期： 
 * 描 述：洗衣模式DTO 查询结果不分页
 */
public class ModeQueryListDTO extends BaseDTO {
	public enum ModeQueryListDTOEnum implements BaseStateDTOEnum{
		;
		
		private Integer stateCode;
		private String msg;
		private ModeQueryListDTOEnum(Integer stateCode, String msg) {
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

	private List<ModeDTO> modeDTOs;
	
	public List<ModeDTO> getModeDTOs() {
		return modeDTOs;
	}
	public void setModeDTOs(
			List<ModeDTO> modeDTOs) {
		this.modeDTOs = modeDTOs;
	}
}

