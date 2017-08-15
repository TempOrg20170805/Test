package com.sunrun.washer.entity.base;



public class BankBase implements java.io.Serializable {
	
	private Integer bankId; // id
	private String name; // 银行名称
	private Integer status; // 状态 0.删除 1.使用中
	private String bankStr; // 银行拼音
	private String bankIconUrl; // 图标url

	
	public BankBase() {
	}

	public BankBase(Integer bankId) {
		this.bankId = bankId;
	}
	
	public BankBase(Integer bankId, String name, Integer status, String bankStr, String bankIconUrl) {
		super();
		this.bankId = bankId;
		this.name = name;
		this.status = status;
		this.bankStr = bankStr;
		this.bankIconUrl = bankIconUrl;

	}
	public Integer getBankId() {
		return this.bankId;
	}
 
	public void setBankId(Integer bankId) {
		this.bankId = bankId;
	}
	public String getName() {
		return this.name;
	}
 
	public void setName(String name) {
		this.name = name;
	}
	public Integer getStatus() {
		return this.status;
	}
 
	public void setStatus(Integer status) {
		this.status = status;
	}
	public String getBankStr() {
		return this.bankStr;
	}
 
	public void setBankStr(String bankStr) {
		this.bankStr = bankStr;
	}
	public String getBankIconUrl() {
		return this.bankIconUrl;
	}
 
	public void setBankIconUrl(String bankIconUrl) {
		this.bankIconUrl = bankIconUrl;
	}

}

