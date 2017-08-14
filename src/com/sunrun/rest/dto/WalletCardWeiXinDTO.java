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
public class WalletCardWeiXinDTO {
	private Integer id; // 卡Id
	private String realname; // 真实姓名
	private String collectionCode; // 收款码
	
	public WalletCardWeiXinDTO() {}
	public WalletCardWeiXinDTO(WalletCard walletCard) {
		this.id = walletCard.getId();
		this.realname = walletCard.getRealname();
		this.collectionCode = walletCard.getCollectionCode();
	}
	
	public String getRealname() {
		return realname;
	}
	public void setRealname(String realname) {
		this.realname = realname;
	}
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getCollectionCode() {
		return collectionCode;
	}
	public void setCollectionCode(String collectionCode) {
		this.collectionCode = collectionCode;
	}
}

