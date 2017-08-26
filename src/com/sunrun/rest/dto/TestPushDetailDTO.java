package com.sunrun.rest.dto;
import com.sunrun.washer.entity.*;
/**
 * 文 件 名 : TestPushDTO.java
 * 创 建 人： 金明明
 * 日 期：2017-8-26
 * 修 改 人： 
 * 日 期： 
 * 描 述：测试推送DTO
 */
public class TestPushDetailDTO extends BaseDTO {
	public enum TestPushDetailDTOEnum implements BaseStateDTOEnum{
		/**
		 * 2202.测试推送不存在
		 */
		IS_NOT_EXIST(2202, "测试推送不存在");
		
		private Integer stateCode;
		private String msg;
		private TestPushDetailDTOEnum(Integer stateCode, String msg) {
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

