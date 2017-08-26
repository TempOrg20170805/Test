package com.sunrun.washer.enums;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
/**
 * 文 件 名 : RuleParamNoEnum.java
 * 创 建 人： 金明明
 * 日 期：2017-8-7
 * 修 改 人： 
 * 日 期： 
 * 描 述：规则参数
 */
public enum RuleParamNoEnum{
	RULE_PARAM_NO10001("10001", "提现金额限制")
	;
	
	private String code;
    private String describe;

    private RuleParamNoEnum(String code, String describe) {
        this.code = code;
        this.describe = describe;
    }
    
    public static Map<String, String> getMap() {
        Map<String,String> map = new HashMap<String,String>();
        for (RuleParamNoEnum item : RuleParamNoEnum.values()) {
            map.put(item.getCode(), item.getDescribe());
        }
        return map;
    }

	public static List<RuleParamNoEnum> getList() {
        List<RuleParamNoEnum> stateList = new ArrayList<RuleParamNoEnum>();
        for (RuleParamNoEnum item : RuleParamNoEnum.values()) {
            stateList.add(item);
        }
        return stateList;
    }
	
	public static RuleParamNoEnum getContains(String code) {
        for (RuleParamNoEnum item : RuleParamNoEnum.values()) {
        	if(code.equals(item.getCode())){
        		return item;
        	}
        }
        return null;
    }

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getDescribe() {
		return describe;
	}

	public void setDescribe(String describe) {
		this.describe = describe;
	}
	

}

