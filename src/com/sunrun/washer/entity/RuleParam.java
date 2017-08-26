package com.sunrun.washer.entity;
import com.sunrun.washer.entity.base.RuleParamBase;

public class RuleParam extends RuleParamBase implements java.io.Serializable {
	
	public RuleParam() {
	}

	
	public RuleParam(Integer ruleParamId) {
		super(ruleParamId);
	}

	public RuleParam(Integer ruleParamId, String ruleParamNo, String des, Integer ruleParamValue){
		super(ruleParamId, ruleParamNo, des, ruleParamValue);
	}
}

