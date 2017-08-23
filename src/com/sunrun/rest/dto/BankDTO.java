package com.sunrun.rest.dto;
import com.sunrun.washer.entity.*;
/**
 * 文 件 名 : BankDTO.java
 * 创 建 人： 金明明
 * 日 期：2017-8-15
 * 修 改 人： 
 * 日 期： 
 * 描 述：银行DTO
 */
public class BankDTO {
	public BankDTO() {};
	
	/**
	 * 初始化银行
	 * @param bank
	 */
	public BankDTO(Bank bank) {
		this.bankId = bank.getBankId();
		this.name = bank.getName();
		this.bankIconUrl = bank.getBankIconUrl();
	}
	
	private Integer bankId; // id
	private String name; // 银行名称
	private String bankIconUrl; // 图标url
	public Integer getBankId() {
		return bankId;
	}

	public void setBankId(Integer bankId) {
		this.bankId = bankId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getBankIconUrl() {
		return bankIconUrl;
	}

	public void setBankIconUrl(String bankIconUrl) {
		this.bankIconUrl = bankIconUrl;
	}
	
	
}

