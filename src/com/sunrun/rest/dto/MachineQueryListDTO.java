package com.sunrun.rest.dto;


import java.util.List;

/**
 * 文 件 名 : MachineQueryListDTO.java
 * 创 建 人： 金明明
 * 日 期：2017-7-31
 * 修 改 人： 
 * 日 期： 
 * 描 述：洗衣机DTO 查询结果不分页
 */
public class MachineQueryListDTO extends BaseDTO {
	public enum MachineQueryListDTOEnum implements BaseStateDTOEnum{
		;
		
		private Integer stateCode;
		private String msg;
		private MachineQueryListDTOEnum(Integer stateCode, String msg) {
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

	private List<MachineDTO> machineDTOs;
	
	public List<MachineDTO> getMachineDTOs() {
		return machineDTOs;
	}
	public void setMachineDTOs(
			List<MachineDTO> machineDTOs) {
		this.machineDTOs = machineDTOs;
	}
}

