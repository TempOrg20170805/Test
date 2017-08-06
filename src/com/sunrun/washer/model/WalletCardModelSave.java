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
	private String bankName; // 银行名称
	private String bankNum; // 卡号
	private String bankBranches; // 银行网点
	private String alipayNum; // 支付宝账号
	private String collectionCode; // 收款码图片
	
	public WalletCardModelSave(){}
	/**
	 * 构造方法
	 * @param userId 用户id
	 * @param realname 真实姓名
	 * @param type 类型 银行卡/支付宝
	 * @param bankName 银行名称
	 * @param bankNum 卡号
	 * @param bankBranches 银行网点
	 * @param alipayNum 支付宝账号
	 */
	public WalletCardModelSave(Integer userId,String realname,Integer type,String bankName,String bankNum,String bankBranches,String alipayNum, String collectionCode) {
		this.userId = userId;
		this.realname = realname;
		this.type = type;
		this.bankName = bankName;
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

