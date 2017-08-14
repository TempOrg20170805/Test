package com.sunrun.washer.enums;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 文 件 名 : WalletLogTypeEnum.java
 * 创 建 人： 金明明
 * 日 期：2017-8-6
 * 修 改 人： 
 * 日 期： 
 * 描 述：钱包消费日志类型
 */
public enum WalletLogTypeEnum {
	/**
	 * 1.提现
	 */
	CASHOUT(1, "提现"),
	/**
	 * 2.收入
	 */
	INCOME(2, "收入")
	;
	private Integer code;
    private String describe;

    private WalletLogTypeEnum(Integer code, String describe) {
        this.code = code;
        this.describe = describe;
    }
    
    public static Map<Integer, String> getMap() {
        Map<Integer,String> map = new HashMap<Integer,String>();
        for (WalletLogTypeEnum item : WalletLogTypeEnum.values()) {
            map.put(item.getCode(), item.getDescribe());
        }
        return map;
    }

	public static List<WalletLogTypeEnum> getList() {
        List<WalletLogTypeEnum> stateList = new ArrayList<WalletLogTypeEnum>();
        for (WalletLogTypeEnum item : WalletLogTypeEnum.values()) {
            stateList.add(item);
        }
        return stateList;
    }
	
	public static WalletLogTypeEnum getEnumByCode(Integer code) {
        for (WalletLogTypeEnum item : WalletLogTypeEnum.values()) {
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
