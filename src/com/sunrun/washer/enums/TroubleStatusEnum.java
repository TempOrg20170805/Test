package com.sunrun.washer.enums;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
/**
 * 故障状态枚举
 *
 */
public enum TroubleStatusEnum{
	DELETE(0, "删除"),
	WAITING(1,"待维修"),
	UNDER_REPAIR(2,"维修中"),
	SUCCESS(3,"维修完毕")
	;
	
	private Integer code;
    private String describe;

    private TroubleStatusEnum(Integer code, String describe) {
        this.code = code;
        this.describe = describe;
    }
    
    public static Map<Integer, String> getMap() {
        Map<Integer,String> map = new HashMap<Integer,String>();
        for (TroubleStatusEnum item : TroubleStatusEnum.values()) {
            map.put(item.getCode(), item.getDescribe());
        }
        return map;
    }

	public static List<TroubleStatusEnum> getList() {
        List<TroubleStatusEnum> stateList = new ArrayList<TroubleStatusEnum>();
        for (TroubleStatusEnum item : TroubleStatusEnum.values()) {
            stateList.add(item);
        }
        return stateList;
    }
	
	public static TroubleStatusEnum getContains(Integer code) {
        for (TroubleStatusEnum item : TroubleStatusEnum.values()) {
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


