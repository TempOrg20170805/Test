package com.sunrun.washer.entity.base;


import java.math.BigDecimal;
import java.util.Date;

import com.jeecms.core.entity.CmsUser;
import com.sunrun.common.util.DateUtil;
import com.sunrun.washer.entity.Machine;
import com.sunrun.washer.enums.WalletLogPayPlatformEnum;
import com.sunrun.washer.enums.WasherOrderStatusEnum;

public class WasherOrderBase implements java.io.Serializable {
	
	private Integer orderId; // id
	private Integer orderState = WasherOrderStatusEnum.NOT_PAY.getCode(); // 订单状态：10(默认):未付款;20:已付款;40:已完成;90:删除
	private CmsUser seller; // 卖家
	private String sellerName; // 卖家姓名
	private CmsUser buyer; // 买家
	private String buyerName; // 买家姓名
	private Date addTime; // 订单生成时间
	private String outSn; // 订单编号，外部支付时使用，有些外部支付系统要求特定的订单编号
	private Integer payPlatform; // 交易平台 1.钱包平台 2.支付宝平台 3.微信平台 4.银行卡平台
	private Date paymentTime; // 支付(付款)时间
	private String payMessage; // 支付信息
	private Date finnshedTime; // 订单完成时间
	private BigDecimal orderAmount; // 订单总价格
	private Machine machine; // 洗衣机id
	private String machineNo; // 序列号
	private Integer floorLayerX; // 洗衣机位置x
	private Integer floorLayerY; // 洗衣机位置y
	private String addressDetail; // 地址
	private Integer layer; // 第几层
	private Integer layerX; // 楼层规格x
	private Integer layerY; // 楼层规格y
	private String floorLayerLocate; // 楼层位置(机位），格式：**层**机位
	private Integer modeNo; // 模式编号
	private String modeName; // 模式名称
	private Integer modeTime; // 该模式需要的时间（单位秒）

	
	public WasherOrderBase() {
	}

	public WasherOrderBase(Integer orderId) {
		this.orderId = orderId;
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
	
	public String getOrderStateStr() {
		return WasherOrderStatusEnum.getContains(this.orderState).getDescribe();
	}

	public CmsUser getSeller() {
		return seller;
	}

	public void setSeller(CmsUser seller) {
		this.seller = seller;
	}

	public String getSellerName() {
		return sellerName;
	}

	public void setSellerName(String sellerName) {
		this.sellerName = sellerName;
	}
	

	public CmsUser getBuyer() {
		return buyer;
	}

	public void setBuyer(CmsUser buyer) {
		this.buyer = buyer;
	}

	public String getBuyerName() {
		return buyerName;
	}

	public void setBuyerName(String buyerName) {
		this.buyerName = buyerName;
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

	public Machine getMachine() {
		return machine;
	}

	public void setMachine(Machine machine) {
		this.machine = machine;
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
	public Integer getPayPlatform() {
		return payPlatform;
	}

	public void setPayPlatform(Integer payPlatform) {
		this.payPlatform = payPlatform;
	}
	
	public String getPayPlatformStr() {
		if (this.payPlatform != null) {
			return WalletLogPayPlatformEnum.getEnumByCode(this.payPlatform).getDescribe();
		} else {
			return "";
		}
	}

	public Integer getModeNo() {
		return modeNo;
	}

	public void setModeNo(Integer modeNo) {
		this.modeNo = modeNo;
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

