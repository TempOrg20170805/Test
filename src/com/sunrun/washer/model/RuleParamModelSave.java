package com.sunrun.washer.model;

/**
 * 文 件 名 : RuleParamModelSave.java
 * 创 建 人： 金明明
 * 日 期：2017-8-26
 * 修 改 人： 
 * 日 期： 
 * 描 述：规则参数 查询条件
 */
public class RuleParamModelSave {
	
	private String ruleParamNo;
	private String des;
	private Integer ruleParamValue;
	public String getRuleParamNo() {
		return ruleParamNo;
	}
	public void setRuleParamNo(String ruleParamNo) {
		this.ruleParamNo = ruleParamNo;
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

