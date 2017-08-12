package com.sunrun.washer.enums;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 文 件 名 : WalletLogPayPlatformEnum.java
 * 创 建 人： 金明明
 * 日 期：2017-8-6
 * 修 改 人： 
 * 日 期： 
 * 描 述：交易平台
 */
public enum WalletLogPayPlatformEnum {
	/**
	 * 1.钱包平台支付
	 */
	WALLET(1, "钱包平台"),
	/**
	 * 2.支付宝平台
	 */
	ALIPAY(2, "支付宝平台"),
	/**
	 * 3.微信平台
	 */
	WEIXIN(3, "微信平台"),
	/**
	 * 4.银行卡
	 */
	BANK_CARD(4, "银行卡")
	;
	private Integer code;
    private String describe;

    private WalletLogPayPlatformEnum(Integer code, String describe) {
        this.code = code;
        this.describe = describe;
    }
    
    public static Map<Integer, String> getMap() {
        Map<Integer,String> map = new HashMap<Integer,String>();
        for (WalletLogPayPlatformEnum item : WalletLogPayPlatformEnum.values()) {
            map.put(item.getCode(), item.getDescribe());
        }
        return map;
    }

	public static List<WalletLogPayPlatformEnum> getList() {
        List<WalletLogPayPlatformEnum> stateList = new ArrayList<WalletLogPayPlatformEnum>();
        for (WalletLogPayPlatformEnum item : WalletLogPayPlatformEnum.values()) {
            stateList.add(item);
        }
        return stateList;
    }
	
	public static WalletLogPayPlatformEnum getEnumByCode(Integer code) {
        for (WalletLogPayPlatformEnum item : WalletLogPayPlatformEnum.values()) {
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
