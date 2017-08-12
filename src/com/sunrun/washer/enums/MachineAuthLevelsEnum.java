package com.sunrun.washer.enums;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


public enum MachineAuthLevelsEnum{
	ONE(1,"一级渠道商"),
	TWO(2,"二级渠道商"),
	THREE(3,"三级渠道商"),
	USER(99,"普通用户")
	;
	
	private Integer code;
    private String describe;

    private MachineAuthLevelsEnum(Integer code, String describe) {
        this.code = code;
        this.describe = describe;
    }
    
    public static Map<Integer, String> getMap() {
        Map<Integer,String> map = new HashMap<Integer,String>();
        for (MachineAuthLevelsEnum item : MachineAuthLevelsEnum.values()) {
            map.put(item.getCode(), item.getDescribe());
        }
        return map;
    }

	public static List<MachineAuthLevelsEnum> getList() {
        List<MachineAuthLevelsEnum> stateList = new ArrayList<MachineAuthLevelsEnum>();
        for (MachineAuthLevelsEnum item : MachineAuthLevelsEnum.values()) {
            stateList.add(item);
        }
        return stateList;
    }
	
	public static MachineAuthLevelsEnum getContains(Integer code) {
        for (MachineAuthLevelsEnum item : MachineAuthLevelsEnum.values()) {
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

