package com.sunrun.washer.model;

/**
 * 文 件 名 : WasherOrderModel.java
 * 创 建 人： 金明明
 * 日 期：2017-8-7
 * 修 改 人： 
 * 日 期： 
 * 描 述：订单 查询条件
 */
public class WasherOrderModel {

	private Integer orderState; // 订单状态：10(默认):未付款;20:已付款;30:运行中;40:已完成;90:删除
	private String outSn; // 订单编号，外部支付时使用，有些外部支付系统要求特定的订单编号
	private String machineNo; // 洗衣机序列号
	private String buyerName; //买家用户名
	private Integer buyerId; // 买家ID
	public Integer getOrderState() {
		return orderState;
	}
	public void setOrderState(Integer orderState) {
		this.orderState = orderState;
	}
	public String getOutSn() {
		return outSn;
	}
	public void setOutSn(String outSn) {
		this.outSn = outSn;
	}
	public Integer getBuyerId() {
		return buyerId;
	}
	public void setBuyerId(Integer buyerId) {
		this.buyerId = buyerId;
	}
	public String getBuyerName() {
		return buyerName;
	}
	public void setBuyerName(String buyerName) {
		this.buyerName = buyerName;
	}
	public String getMachineNo() {
		return machineNo;
	}
	public void setMachineNo(String machineNo) {
		this.machineNo = machineNo;
	}
	
	
	
	

}

