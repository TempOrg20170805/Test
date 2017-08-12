package com.sunrun.washer.enums;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


public enum FloorStatusEnum{
	DELETE(0, "删除"),
	USE(1,"使用中")
	;
	
	private Integer code;
    private String describe;

    private FloorStatusEnum(Integer code, String describe) {
        this.code = code;
        this.describe = describe;
    }
    
    public static Map<Integer, String> getMap() {
        Map<Integer,String> map = new HashMap<Integer,String>();
        for (FloorStatusEnum item : FloorStatusEnum.values()) {
            map.put(item.getCode(), item.getDescribe());
        }
        return map;
    }

	public static List<FloorStatusEnum> getList() {
        List<FloorStatusEnum> stateList = new ArrayList<FloorStatusEnum>();
        for (FloorStatusEnum item : FloorStatusEnum.values()) {
            stateList.add(item);
        }
        return stateList;
    }
	
	public static FloorStatusEnum getContains(Integer code) {
        for (FloorStatusEnum item : FloorStatusEnum.values()) {
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


