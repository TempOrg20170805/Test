package com.sunrun.washer.model;

/**
 * 文 件 名 : WalletCardModelSave.java
 * 创 建 人： 金明明
 * 日 期：2017-8-6
 * 修 改 人： 
 * 日 期： 
 * 描 述：银行卡/支付宝管理 查询条件
 */
public class WalletCardModelSave {
	private Integer userId; // 用户id
	private String realname; // 真实姓名
	private Integer type; // 类型 银行卡/支付宝
	private Integer bankId; // 银行Id
	private String bankNum; // 卡号
	private String bankBranches; // 银行网点
	private String alipayNum; // 支付宝账号
	private String collectionCode; // 收款码图片
	
	public WalletCardModelSave(){}

	public WalletCardModelSave(Integer userId,String realname,Integer type,Integer bankId,String bankNum,String bankBranches,String alipayNum, String collectionCode) {
		this.userId = userId;
		this.realname = realname;
		this.type = type;
		this.bankId = bankId;
		this.bankNum = bankNum;
		this.bankBranches = bankBranches;
		this.alipayNum = alipayNum;
		this.collectionCode = collectionCode;
	}
	
	public Integer getUserId() {
		return userId;
	}
	public void setUserId(Integer userId) {
		this.userId = userId;
	}
	public String getRealname() {
		return realname;
	}
	public void setRealname(String realname) {
		this.realname = realname;
	}
	public Integer getType() {
		return type;
	}
	public void setType(Integer type) {
		this.type = type;
	}
	public Integer getBankId() {
		return bankId;
	}

	public void setBankId(Integer bankId) {
		this.bankId = bankId;
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
	public String getAlipayNum() {
		return alipayNum;
	}
	public void setAlipayNum(String alipayNum) {
		this.alipayNum = alipayNum;
	}
	public String getCollectionCode() {
		return collectionCode;
	}
	public void setCollectionCode(String collectionCode) {
		this.collectionCode = collectionCode;
	}
	
	
	
}

