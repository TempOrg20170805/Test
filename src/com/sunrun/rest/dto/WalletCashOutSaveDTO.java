package com.sunrun.rest.dto;


import java.util.List;

/**
 * 文 件 名 : WalletCashOutSaveDTO.java
 * 创 建 人： 金明明
 * 日 期：2017-8-6
 * 修 改 人： 
 * 日 期： 
 * 描 述：提现管理DTO 添加结果
 */
public class WalletCashOutSaveDTO extends BaseDTO {
	public enum WalletCashOutSaveDTOEnum implements BaseStateDTOEnum{
		/**
		 * 2501.用户不存在
		 */
		USER_IS_NOT_EXIST(2501, "用户不存在"),
		/**
		 * 2502.该卡不存在
		 */
		CARD_IS_NOT_EXIST(2502, "该卡不存在"),
		/**
		 * 2503.提现金额要大于0
		 */
		CASHOUT_CAN_NOT_ZERO(2503, "提现金额要大于0"),
		/**
		 * 2504.金额不足
		 */
		MONEY_IS_NOT_ENGOUH(2504, "金额不足");
		
		private Integer stateCode;
		private String msg;
		private WalletCashOutSaveDTOEnum(Integer stateCode, String msg) {
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
}

