package com.sunrun.washer.entity;
// default package

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.sunrun.washer.entity.base.WalletLogBase;


/**
 * WalletLog entity. @author MyEclipse Persistence Tools
 */
public class WalletLog extends WalletLogBase implements java.io.Serializable {
	
	
	/**
	 * 文 件 名 : Wallet.java
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
		CASHOUT(1, "提现")
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
		
		public static WalletLogTypeEnum getContains(Integer code) {
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
	
	/**
	 * 文 件 名 : WalletLog.java
	 * 创 建 人： 金明明
	 * 日 期：2017-8-6
	 * 修 改 人： 
	 * 日 期： 
	 * 描 述：交易平台(仅用于运费支付)
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
		private Integer value;
		private String name;
		
		public Integer getValue() {
			return value;
		}
		public String getName() {
			return name;
		}
		private WalletLogPayPlatformEnum(Integer value, String name) {
			this.value = value;
			this.name = name;
		}
		public static String getNameByValue(Integer value) {
			for (WalletLogPayPlatformEnum orderStatusEnum : WalletLogPayPlatformEnum.values()) {
				if (orderStatusEnum.getValue().equals(value)) {
					return orderStatusEnum.getName();
				}
			}
			return "";
		}
	}
	
	

    // Constructors

    /** default constructor */
    public WalletLog() {
    }

    
}
