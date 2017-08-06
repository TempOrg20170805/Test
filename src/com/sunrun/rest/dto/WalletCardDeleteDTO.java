package com.sunrun.rest.dto;


import java.util.List;

/**
 * 文 件 名 : WalletCardDeleteDTO.java
 * 创 建 人： 金明明
 * 日 期：2017-8-6
 * 修 改 人： 
 * 日 期： 
 * 描 述：银行卡/支付宝管理DTO 更新结果
 */
public class WalletCardDeleteDTO extends BaseDTO {
	public enum WalletCardDeleteDTOEnum implements BaseStateDTOEnum{
		/**
		 * 2501.用户不存在
		 */
		USER_IS_NOT_EXIST(2501, "用户不存在"),
		/**
		 * 2502.该卡不存在
		 */
		CARD_IS_NOT_EXIST(2502, "该卡不存在");
		
		private Integer stateCode;
		private String msg;
		private WalletCardDeleteDTOEnum(Integer stateCode, String msg) {
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

