package com.sunrun.rest.dto;
import java.math.BigDecimal;
import java.util.Date;

import com.sunrun.common.util.DateUtil;
import com.sunrun.washer.entity.WasherOrder;
/**
 * 文 件 名 : WasherOrderDTO.java
 * 创 建 人： 金明明
 * 日 期：2017-8-7
 * 修 改 人： 
 * 日 期： 
 * 描 述：订单DTO
 */
public class WasherOrderDTO {
	
	private Integer orderId; // 订单ID
	private Integer orderState; // 订单状态：10(默认):未付款;40:已完成;90:删除
	private Date addTime; // 订单生成时间
	private String outSn; // 订单编号，外部支付时使用，有些外部支付系统要求特定的订单编号
	private Integer payPlatform; // 交易平台 1.钱包平台 2.支付宝平台 3.微信平台 4.银行卡平台
	private Date paymentTime; // 支付(付款)时间
	private String payMessage; // 支付信息
	private Date finnshedTime; // 订单完成时间
	private BigDecimal orderAmount; // 订单总价格
	private String machineNo; // 序列号
	private String addressDetail; // 地址
	private String floorLayerLocate; // 楼层位置(机位），格式：**层**机位
	private String modeName; // 模式名称
	private Integer modeTime; // 该模式需要的时间（单位秒）

	public WasherOrderDTO() {};
	
	/**
	 * 初始化订单
	 * @param washerOrder
	 */
	public WasherOrderDTO(WasherOrder washerOrder) {
		this.orderId = washerOrder.getOrderId();
		this.orderState = washerOrder.getOrderState();
		this.addTime = washerOrder.getAddTime();
		this.outSn = washerOrder.getOutSn();
		this.payPlatform = washerOrder.getPayPlatform();
		this.paymentTime = washerOrder.getPaymentTime();
		this.payMessage = washerOrder.getPayMessage();
		this.finnshedTime = washerOrder.getFinnshedTime();
		this.orderAmount = washerOrder.getOrderAmount();
		this.machineNo = washerOrder.getMachineNo();
		this.addressDetail = washerOrder.getAddressDetail();
		this.floorLayerLocate = washerOrder.getFloorLayerLocate();
		this.modeName = washerOrder.getModeName();
		this.modeTime = washerOrder.getModeTime();
	}

	public Integer getOrderId() {
		return orderId;
	}

	public void setOrderId(Integer orderId) {
		this.orderId = orderId;
	}

	public Integer getOrderState() {
		return orderState;
	}

	public void setOrderState(Integer orderState) {
		this.orderState = orderState;
	}

	public Date getAddTime() {
		return addTime;
	}

	public void setAddTime(Date addTime) {
		this.addTime = addTime;
	}

	public String getOutSn() {
		return outSn;
	}

	public void setOutSn(String outSn) {
		this.outSn = outSn;
	}

	public Integer getPayPlatform() {
		return payPlatform;
	}

	public void setPayPlatform(Integer payPlatform) {
		this.payPlatform = payPlatform;
	}

	public Date getPaymentTime() {
		return paymentTime;
	}

	public void setPaymentTime(Date paymentTime) {
		this.paymentTime = paymentTime;
	}

	public String getPayMessage() {
		return payMessage;
	}

	public void setPayMessage(String payMessage) {
		this.payMessage = payMessage;
	}

	public Date getFinnshedTime() {
		return finnshedTime;
	}

	public void setFinnshedTime(Date finnshedTime) {
		this.finnshedTime = finnshedTime;
	}

	public BigDecimal getOrderAmount() {
		return orderAmount;
	}

	public void setOrderAmount(BigDecimal orderAmount) {
		this.orderAmount = orderAmount;
	}

	public String getMachineNo() {
		return machineNo;
	}

	public void setMachineNo(String machineNo) {
		this.machineNo = machineNo;
	}

	public String getAddressDetail() {
		return addressDetail;
	}

	public void setAddressDetail(String addressDetail) {
		this.addressDetail = addressDetail;
	}

	public String getFloorLayerLocate() {
		return floorLayerLocate;
	}

	public void setFloorLayerLocate(String floorLayerLocate) {
		this.floorLayerLocate = floorLayerLocate;
	}

	public String getModeName() {
		return modeName;
	}

	public void setModeName(String modeName) {
		this.modeName = modeName;
	}

	public Integer getModeTime() {
		return modeTime;
	}

	public void setModeTime(Integer modeTime) {
		this.modeTime = modeTime;
	}
	
	public String getModeTimeStr() {
		return DateUtil.secToTime(this.modeTime);
	}
}

