package com.sunrun.washer.entity;
// default package

import com.sunrun.washer.entity.base.WalletCashOutBase;
/**
 * 文 件 名 : WalletCashOut.java
 * 创 建 人： 金明明
 * 日 期：2017-8-6
 * 修 改 人： 
 * 日 期： 
 * 描 述：钱包提现
 */
public class WalletCashOut extends WalletCashOutBase implements java.io.Serializable {
	
	public enum WalletCashOutStateEnum {
		/**
		 * 1.提现申请中
		 */
		CASH_OUT_DO(1, "提现申请中"),
		/**
		 * 2.提现成功
		 */
		SUCCESS(2, "提现成功"),
		/**
		 * 3.提现失败
		 */
		FAIL(3, "提现失败");
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
		private WalletCashOutStateEnum(Integer value, String name) {
			this.value = value;
			this.name = name;
		}
		
		public static String getNameByValue(Integer value) {
			for (WalletCashOutStateEnum walletCashOutStateEnum : WalletCashOutStateEnum.values()) {
				if (walletCashOutStateEnum.getValue().equals(value)) {
					return walletCashOutStateEnum.getName();
				}
			}
			return "";
		}
	}

    /** default constructor */
    public WalletCashOut() {
    }

   
}
