package com.sunrun.washer.enums;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
/**
 * 文 件 名 : UserMachineUseTypeEnum.java
 * 创 建 人： 金明明
 * 日 期：2017-8-8
 * 修 改 人： 
 * 日 期： 
 * 描 述：用户洗衣机使用类型
 */
public enum UserMachineUseTypeEnum{
	USE(1, "用户拥有该洗衣机"),
	COLLECT(2,"用户收藏该洗衣机"),
	;
	
	private Integer code;
    private String describe;

    private UserMachineUseTypeEnum(Integer code, String describe) {
        this.code = code;
        this.describe = describe;
    }
    
    public static Map<Integer, String> getMap() {
        Map<Integer,String> map = new HashMap<Integer,String>();
        for (UserMachineUseTypeEnum item : UserMachineUseTypeEnum.values()) {
            map.put(item.getCode(), item.getDescribe());
        }
        return map;
    }

	public static List<UserMachineUseTypeEnum> getList() {
        List<UserMachineUseTypeEnum> stateList = new ArrayList<UserMachineUseTypeEnum>();
        for (UserMachineUseTypeEnum item : UserMachineUseTypeEnum.values()) {
            stateList.add(item);
        }
        return stateList;
    }
	
	public static UserMachineUseTypeEnum getContains(Integer code) {
        for (UserMachineUseTypeEnum item : UserMachineUseTypeEnum.values()) {
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

