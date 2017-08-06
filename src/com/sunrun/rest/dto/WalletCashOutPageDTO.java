package com.sunrun.rest.dto;


import java.math.BigDecimal;

/**
 * 文 件 名 : WalletCashOutPageDTO.java
 * 创 建 人： 金明明
 * 日 期：2017-8-6
 * 修 改 人： 
 * 日 期： 
 * 描 述：提现界面信息列表
 */
public class WalletCashOutPageDTO extends BaseDTO {
	public enum WalletCashOutPageDTOEnum implements BaseStateDTOEnum{
		/**
		 * 2501.用户不存在
		 */
		USER_IS_NOT_EXIST(2501, "用户不存在");
		
		private Integer stateCode;
		private String msg;
		private WalletCashOutPageDTOEnum(Integer stateCode, String msg) {
			this.stateCode = stateCode;
			this.msg = msg;
		}
		@Override
		public String getMsg() {
			return this.msg;
		}

		@Override
		public Integer getStateCode() {
			return this.stateCode;
		}
		
	}
	
	private BigDecimal money = new BigDecimal(0); // 用户现有金额

	public BigDecimal getMoney() {
		return money;
	}

	public void setMoney(BigDecimal money) {
		this.money = money;
	}
	
	
	
	
}

