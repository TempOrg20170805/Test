package com.sunrun.washer.enums;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
/**
 * 文 件 名 : BaseStatus.java
 * 创 建 人： 金明明
 * 日 期：2017-8-6
 * 修 改 人： 
 * 日 期： 
 * 描 述：基本状态
 */
public enum BaseStatusEnum{
	DELETE(0, "删除"),
	USE(1,"有效")
	;
	
	private Integer code;
    private String describe;

    private BaseStatusEnum(Integer code, String describe) {
        this.code = code;
        this.describe = describe;
    }
    
    public static Map<Integer, String> getMap() {
        Map<Integer,String> map = new HashMap<Integer,String>();
        for (BaseStatusEnum item : BaseStatusEnum.values()) {
            map.put(item.getCode(), item.getDescribe());
        }
        return map;
    }

	public static List<BaseStatusEnum> getList() {
        List<BaseStatusEnum> stateList = new ArrayList<BaseStatusEnum>();
        for (BaseStatusEnum item : BaseStatusEnum.values()) {
            stateList.add(item);
        }
        return stateList;
    }
	
	public static BaseStatusEnum getContains(Integer code) {
        for (BaseStatusEnum item : BaseStatusEnum.values()) {
        	if(code.equals(item.getCode())){
        		return item;
        	}
        }
        return null;
    }

	public Integer getCode() {
		return code;
	}

	public void setCode(Integer code) {
		this.code = code;
	}

	public String getDescribe() {
		return describe;
	}

	public void setDescribe(String describe) {
		this.describe = describe;
	}
	

}

