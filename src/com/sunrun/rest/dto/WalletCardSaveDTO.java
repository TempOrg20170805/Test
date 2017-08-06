package com.sunrun.rest.dto;


import java.util.List;

/**
 * 文 件 名 : WalletCardSaveDTO.java
 * 创 建 人： 金明明
 * 日 期：2017-8-6
 * 修 改 人： 
 * 日 期： 
 * 描 述：银行卡/支付宝管理DTO 添加结果
 */
public class WalletCardSaveDTO extends BaseDTO {
	public enum WalletCardSaveDTOEnum implements BaseStateDTOEnum{
		/**
		 * 2501.用户不存在
		 */
		USER_IS_NOT_EXIST(2501, "用户不存在"),
		/**
		 * 2502.卡类型错误
		 */
		CARD_TYPE_ERROR(2502, "卡类型错误");
		
		private Integer stateCode;
		private String msg;
		private WalletCardSaveDTOEnum(Integer stateCode, String msg) {
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

