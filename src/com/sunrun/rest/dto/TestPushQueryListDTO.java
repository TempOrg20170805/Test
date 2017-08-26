package com.sunrun.rest.dto;


import java.util.List;

/**
 * 文 件 名 : TestPushQueryListDTO.java
 * 创 建 人： 金明明
 * 日 期：2017-8-26
 * 修 改 人： 
 * 日 期： 
 * 描 述：测试推送DTO 查询结果不分页
 */
public class TestPushQueryListDTO extends BaseDTO {
	public enum TestPushQueryListDTOEnum implements BaseStateDTOEnum{
		;
		
		private Integer stateCode;
		private String msg;
		private TestPushQueryListDTOEnum(Integer stateCode, String msg) {
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

	private List<TestPushDTO> testPushDTOs;
	
	public List<TestPushDTO> getTestPushDTOs() {
		return testPushDTOs;
	}
	public void setTestPushDTOs(
			List<TestPushDTO> testPushDTOs) {
		this.testPushDTOs = testPushDTOs;
	}
}

