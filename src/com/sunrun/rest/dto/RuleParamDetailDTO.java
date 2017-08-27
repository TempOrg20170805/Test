package com.sunrun.rest.dto;
import com.sunrun.washer.entity.*;
/**
 * 文 件 名 : RuleParamDTO.java
 * 创 建 人： 金明明
 * 日 期：2017-8-26
 * 修 改 人： 
 * 日 期： 
 * 描 述：规则参数DTO
 */
public class RuleParamDetailDTO extends BaseDTO {
	public enum RuleParamDetailDTOEnum implements BaseStateDTOEnum{
		/**
		 * 2202.规则参数不存在
		 */
		IS_NOT_EXIST(2202, "规则参数不存在");
		
		private Integer stateCode;
		private String msg;
		private RuleParamDetailDTOEnum(Integer stateCode, String msg) {
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
	
	private Integer ruleParamValue;
	private String des;

	public Integer getRuleParamValue() {
		return ruleParamValue;
	}

	public void setRuleParamValue(Integer ruleParamValue) {
		this.ruleParamValue = ruleParamValue;
	}

	public String getDes() {
		return des;
	}

	public void setDes(String des) {
		this.des = des;
	}
	
	
	
		
}

