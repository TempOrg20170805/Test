package com.sunrun.rest.dto;

import com.sunrun.washer.entity.WalletCard;


/**
 * 文 件 名 : WalletCardDTO.java
 * 创 建 人： 金明明
 * 日 期：2017-8-6
 * 修 改 人： 
 * 日 期： 
 * 描 述：银行卡/支付宝管理DTO
 */
public class WalletCardBankDTO {
	private Integer id; // 卡Id
	private String realname; // 真实姓名
	private String bankNum; // 银行卡号
	private String bankName; // 银行名称
	private String bankBranches; // 银行网点
	
	public WalletCardBankDTO() {}
	public WalletCardBankDTO(WalletCard walletCard) {
		this.id = walletCard.getId();
		this.realname = walletCard.getRealname();

		if (walletCard.getBankNum().length() > 4) {
			this.bankNum = "**** **** " + walletCard.getBankNum().substring(walletCard.getBankNum().length() - 4, walletCard.getBankNum().length());
		} else {
			this.bankNum = walletCard.getBankNum();
		}

		this.bankName = walletCard.getBankName();
		this.bankBranches = walletCard.getBankBranches();
	}
	
	public String getRealname() {
		return realname;
	}
	public void setRealname(String realname) {
		this.realname = realname;
	}
	public String getBankName() {
		return bankName;
	}
	public void setBankName(String bankName) {
		this.bankName = bankName;
	}
	public String getBankNum() {
		return bankNum;
	}
	public void setBankNum(String bankNum) {
		this.bankNum = bankNum;
	}
	public String getBankBranches() {
		return bankBranches;
	}
	public void setBankBranches(String bankBranches) {
		this.bankBranches = bankBranches;
	}
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	
	
	
}

