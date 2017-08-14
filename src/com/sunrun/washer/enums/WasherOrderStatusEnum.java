package com.sunrun.washer.enums;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
/**
 * 文 件 名 : WasherOrderStatusEnum.java
 * 创 建 人： 金明明
 * 日 期：2017-8-7
 * 修 改 人： 
 * 日 期： 
 * 描 述：订单状态状态
 */
public enum WasherOrderStatusEnum{
	NOT_PAY(10, "未付款"),
	PAY(20,"已付款"),
	USE(30,"运行中"),
	FINISH(40,"已完成"),
	DELETE(90,"删除"),
	;
	
	private Integer code;
    private String describe;

    private WasherOrderStatusEnum(Integer code, String describe) {
        this.code = code;
        this.describe = describe;
    }
    
    public static Map<Integer, String> getMap() {
        Map<Integer,String> map = new HashMap<Integer,String>();
        for (WasherOrderStatusEnum item : WasherOrderStatusEnum.values()) {
            map.put(item.getCode(), item.getDescribe());
        }
        return map;
    }

	public static List<WasherOrderStatusEnum> getList() {
        List<WasherOrderStatusEnum> stateList = new ArrayList<WasherOrderStatusEnum>();
        for (WasherOrderStatusEnum item : WasherOrderStatusEnum.values()) {
            stateList.add(item);
        }
        return stateList;
    }
	
	public static WasherOrderStatusEnum getContains(Integer code) {
        for (WasherOrderStatusEnum item : WasherOrderStatusEnum.values()) {
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

