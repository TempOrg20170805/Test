package com.sunrun.washer.entity;
// default package

import java.util.HashMap;
import java.util.Map;

import com.sunrun.washer.entity.WalletCashOut.WalletCashOutStateEnum;
import com.sunrun.washer.entity.base.WalletCardBase;
/**
 * 文 件 名 : WalletCard.java
 * 创 建 人： 金明明
 * 日 期：2017-8-6
 * 修 改 人： 
 * 日 期： 
 * 描 述：银行卡/支付宝
 */
public class WalletCard extends WalletCardBase implements java.io.Serializable {

    // Constructors

    /** default constructor */
    public WalletCard() {
    }
    
    public enum WalletCardTypeEnum {
    	/**
    	 * 1.银行卡
    	 */
		BANK_CARD(1,"银行卡"),
		/**
		 * 2.支付宝
		 */
		ALIPAY_CARD(2, "支付宝"),
		/**
		 * 3.微信
		 */
		WEIXIN(3, "微信"),
		/**
		 * 0.所有类型
		 */
		ALL(0,"所有类型");
    	private Integer value;
    	private String name;
    	
    	public Integer getValue() {
			return value;
		}

		public void setValue(Integer value) {
			this.value = value;
		}

		public String getName() {
			return name;
		}

		public void setName(String name) {
			this.name = name;
		}

		private WalletCardTypeEnum(Integer value, String name) {
    		this.value = value;
    		this.name = name;
    	}
    	
    	public static String getNameByValue(Integer value) {
    		for (WalletCardTypeEnum walletCardTypeEnum : WalletCardTypeEnum.values()) {
    			if (walletCardTypeEnum.getValue().equals(value)) {
    				return walletCardTypeEnum.getName();
    			}
    		}
    		return "";
    	}
    	
	    public static Map<Integer, String> getMap() {
	        Map<Integer,String> map = new HashMap<Integer,String>();
	        for (WalletCardTypeEnum item : WalletCardTypeEnum.values()) {
	            map.put(item.getValue(), item.getName());
	        }
	        return map;
	    }
    	
    }
    
    public enum WalletCardStatusEnum {
    	/**
    	 * 0.删除
    	 */
		DELETE(0,"删除"),
		/**
		 * 1.有效
		 */
		USE(1, "有效");
    	private Integer value;
    	private String name;
    	
    	public Integer getValue() {
			return value;
		}

		public void setValue(Integer value) {
			this.value = value;
		}

		public String getName() {
			return name;
		}

		public void setName(String name) {
			this.name = name;
		}

		private WalletCardStatusEnum(Integer value, String name) {
    		this.value = value;
    		this.name = name;
    	}
    	
    	public static String getNameByValue(Integer value) {
    		for (WalletCardStatusEnum walletCardStatusEnum : WalletCardStatusEnum.values()) {
    			if (walletCardStatusEnum.getValue().equals(value)) {
    				return walletCardStatusEnum.getName();
    			}
    		}
    		return "";
    	}
    	
    }
   
}
