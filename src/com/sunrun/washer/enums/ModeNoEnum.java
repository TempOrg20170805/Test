package com.sunrun.washer.enums;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
/**
 * 文 件 名 : ModeNoEnum.java
 * 创 建 人： 金明明
 * 日 期：2017-8-13
 * 修 改 人： 
 * 日 期： 
 * 描 述：洗涤模式编号枚举，注意：这里编号与数据库ID一一对应
 */
public enum ModeNoEnum{
	DHY(1, "单脱水",3),
	QW(2,"快速洗",4),
	BTW(3,"大物洗",5),
	NW(4,"标准洗",6)
	;
	
	private Integer code;
    private String describe;
    private Integer modeNo;

    /**
     * 
     * @param code 数据库ID
     * @param describe 描述
     * @param modeNo 协议上的洗涤编号
     */
    private ModeNoEnum(Integer code, String describe, Integer modeNo) {
        this.code = code;
        this.describe = describe;
        this.modeNo = modeNo;
    }
    
    public static Map<Integer, Integer> getMap() {
        Map<Integer,Integer> map = new HashMap<Integer,Integer>();
        for (ModeNoEnum item : ModeNoEnum.values()) {
            map.put(item.getCode(), item.getModeNo());
        }
        return map;
    }

	public static List<ModeNoEnum> getList() {
        List<ModeNoEnum> stateList = new ArrayList<ModeNoEnum>();
        for (ModeNoEnum item : ModeNoEnum.values()) {
            stateList.add(item);
        }
        return stateList;
    }
	
	public static ModeNoEnum getContains(Integer code) {
        for (ModeNoEnum item : ModeNoEnum.values()) {
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

	public Integer getModeNo() {
		return modeNo;
	}

	public void setModeNo(Integer modeNo) {
		this.modeNo = modeNo;
	}
	
	

}

