package com.sunrun.rest.dto;


import java.math.BigDecimal;

import com.sunrun.common.util.DateUtil;
import com.sunrun.washer.entity.WasherOrder;

/**
 * 文 件 名 : WasherOrderSaveDTO.java
 * 创 建 人： 金明明
 * 日 期：2017-8-7
 * 修 改 人： 
 * 日 期： 
 * 描 述：订单DTO 添加结果
 */
public class WasherOrderSaveDTO extends BaseDTO {
	public enum WasherOrderSaveDTOEnum implements BaseStateDTOEnum{
		;
		
		private Integer stateCode;
		private String msg;
		private WasherOrderSaveDTOEnum(Integer stateCode, String msg) {
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
	
	private Integer orderId; // 订单ID
	private Integer orderState; // 订单状态：10(默认):未付款;40:已完成;90:删除
	private String outSn; // 订单编号，外部支付时使用，有些外部支付系统要求特定的订单编号

	private BigDecimal orderAmount; // 订单总价格
	private String machineNo; // 序列号
	private String addressDetail; // 地址
	private String floorLayerLocate; // 楼层位置(机位），格式：**层**机位
	private String modeName; // 模式名称
	private Integer modeTime; // 该模式需要的时间（单位秒）
	private Integer floorLayerX; // 洗衣机位置x
	private Integer floorLayerY; // 洗衣机位置y
	private Integer layer; // 第几层
	private Integer layerX; // 楼层规格x
	private Integer layerY; // 楼层规格y
	
	public void init(WasherOrder washerOrder) {
		this.orderId = washerOrder.getOrderId();
		this.orderState = washerOrder.getOrderState();
		this.outSn = washerOrder.getOutSn();
		this.orderAmount = washerOrder.getOrderAmount();
		this.machineNo = washerOrder.getMachineNo();
		this.addressDetail = washerOrder.getAddressDetail();
		this.floorLayerLocate = washerOrder.getFloorLayerLocate();
		this.modeName = washerOrder.getModeName();
		this.modeTime = washerOrder.getModeTime();
		this.floorLayerX = washerOrder.getFloorLayerX();
		this.floorLayerY = washerOrder.getFloorLayerY();
		this.layer = washerOrder.getLayer();
		this.layerX = washerOrder.getLayerX();
		this.layerY = washerOrder.getLayerY();
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
	public String getOutSn() {
		return outSn;
	}
	public void setOutSn(String outSn) {
		this.outSn = outSn;
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

	public Integer getFloorLayerX() {
		return floorLayerX;
	}

	public void setFloorLayerX(Integer floorLayerX) {
		this.floorLayerX = floorLayerX;
	}

	public Integer getFloorLayerY() {
		return floorLayerY;
	}

	public void setFloorLayerY(Integer floorLayerY) {
		this.floorLayerY = floorLayerY;
	}

	public Integer getLayer() {
		return layer;
	}

	public void setLayer(Integer layer) {
		this.layer = layer;
	}

	public Integer getLayerX() {
		return layerX;
	}

	public void setLayerX(Integer layerX) {
		this.layerX = layerX;
	}

	public Integer getLayerY() {
		return layerY;
	}

	public void setLayerY(Integer layerY) {
		this.layerY = layerY;
	}
	
}

