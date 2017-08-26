package com.sunrun.washer.model;

/**
 * 文 件 名 : RuleParamModelUpdate.java
 * 创 建 人： 金明明
 * 日 期：2017-8-26
 * 修 改 人： 
 * 日 期： 
 * 描 述：规则参数 查询条件
 */
public class RuleParamModelUpdate {
	private Integer ruleParamId;
	private String des; // 说明
	private Integer ruleParamValue; // 规则参数值
	public Integer getRuleParamId() {
		return ruleParamId;
	}
	public void setRuleParamId(Integer ruleParamId) {
		this.ruleParamId = ruleParamId;
	}
	public String getDes() {
		return des;
	}
	public void setDes(String des) {
		this.des = des;
	}
	public Integer getRuleParamValue() {
		return ruleParamValue;
	}
	public void setRuleParamValue(Integer ruleParamValue) {
		this.ruleParamValue = ruleParamValue;
	}
	

}

