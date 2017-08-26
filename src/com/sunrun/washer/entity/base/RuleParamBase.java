package com.sunrun.washer.entity.base;



public class RuleParamBase implements java.io.Serializable {
	
	private Integer ruleParamId; // id
	private String ruleParamNo; // 规则编号
	private String des; // 说明
	private Integer ruleParamValue; // 规则参数值

	
	public RuleParamBase() {
	}

	public RuleParamBase(Integer ruleParamId) {
		this.ruleParamId = ruleParamId;
	}
	
	public RuleParamBase(Integer ruleParamId, String ruleParamNo, String des, Integer ruleParamValue) {
		super();
		this.ruleParamId = ruleParamId;
		this.ruleParamNo = ruleParamNo;
		this.des = des;
		this.ruleParamValue = ruleParamValue;

	}
	public Integer getRuleParamId() {
		return this.ruleParamId;
	}
 
	public void setRuleParamId(Integer ruleParamId) {
		this.ruleParamId = ruleParamId;
	}
	public String getRuleParamNo() {
		return this.ruleParamNo;
	}
 
	public void setRuleParamNo(String ruleParamNo) {
		this.ruleParamNo = ruleParamNo;
	}
	public String getDes() {
		return this.des;
	}
 
	public void setDes(String des) {
		this.des = des;
	}
	public Integer getRuleParamValue() {
		return this.ruleParamValue;
	}
 
	public void setRuleParamValue(Integer ruleParamValue) {
		this.ruleParamValue = ruleParamValue;
	}

}

