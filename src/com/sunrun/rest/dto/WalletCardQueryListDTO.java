package com.sunrun.rest.dto;


import java.util.List;

/**
 * 文 件 名 : WalletCardQueryListDTO.java
 * 创 建 人： 金明明
 * 日 期：2017-8-6
 * 修 改 人： 
 * 日 期： 
 * 描 述：银行卡/支付宝管理DTO 查询结果不分页
 */
public class WalletCardQueryListDTO extends BaseDTO {
	public enum WalletCardQueryListDTOEnum implements BaseStateDTOEnum{
		;
		
		private Integer stateCode;
		private String msg;
		private WalletCardQueryListDTOEnum(Integer stateCode, String msg) {
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

	private List<WalletCardAlipayDTO> walletCardAlipayDTOs;
	private List<WalletCardBankDTO> walletCardBankDTOs;
	private List<WalletCardWeiXinDTO> walletCardWeiXinDTOs;
	public List<WalletCardAlipayDTO> getWalletCardAlipayDTOs() {
		return walletCardAlipayDTOs;
	}
	public void setWalletCardAlipayDTOs(
			List<WalletCardAlipayDTO> walletCardAlipayDTOs) {
		this.walletCardAlipayDTOs = walletCardAlipayDTOs;
	}
	public List<WalletCardBankDTO> getWalletCardBankDTOs() {
		return walletCardBankDTOs;
	}
	public void setWalletCardBankDTOs(List<WalletCardBankDTO> walletCardBankDTOs) {
		this.walletCardBankDTOs = walletCardBankDTOs;
	}
	public List<WalletCardWeiXinDTO> getWalletCardWeiXinDTOs() {
		return walletCardWeiXinDTOs;
	}
	public void setWalletCardWeiXinDTOs(
			List<WalletCardWeiXinDTO> walletCardWeiXinDTOs) {
		this.walletCardWeiXinDTOs = walletCardWeiXinDTOs;
	}
	
	
	

}

