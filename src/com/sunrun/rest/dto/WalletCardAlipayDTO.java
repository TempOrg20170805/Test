package com.sunrun.rest.dto;

import com.sunrun.washer.entity.WalletCard;
import com.sunrun.washer.entity.WalletCard.WalletCardTypeEnum;


/**
 * 文 件 名 : WalletCardAlipayDTO.java
 * 创 建 人： 金明明
 * 日 期：2017-8-6
 * 修 改 人： 
 * 日 期： 
 * 描 述：支付宝DTO
 */
public class WalletCardAlipayDTO {
	private Integer id; // 卡Id
	private String realname; // 真实姓名
	private String alipayNum; // 支付宝账号
	
	public WalletCardAlipayDTO() {}
	public WalletCardAlipayDTO(WalletCard walletCard) {
		this.id = walletCard.getId();
		this.realname = walletCard.getRealname();
		this.alipayNum = walletCard.getAlipayNum();
	}
	
	public String getRealname() {
		return realname;
	}
	public void setRealname(String realname) {
		this.realname = realname;
	}
	public String getAlipayNum() {
		return alipayNum;
	}
	public void setAlipayNum(String alipayNum) {
		this.alipayNum = alipayNum;
	}
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	
	
	
}

