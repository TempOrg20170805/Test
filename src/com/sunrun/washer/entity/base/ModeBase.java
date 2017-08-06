package com.sunrun.washer.entity.base;


import java.math.BigDecimal;

import com.sunrun.common.util.DateUtil;

public class ModeBase implements java.io.Serializable {
	
	private Integer modeId; // id
	private String name; // 模式名称
	private BigDecimal modeMoney = new BigDecimal(0); // 模式金额
	private Integer modeTime; // 该模式需要的时间（单位秒）
	private Integer status; // 状态 0.删除 1.使用中

	
	public ModeBase() {
	}

	public ModeBase(Integer modeId) {
		this.modeId = modeId;
	}
	
	public ModeBase(Integer modeId, String name, BigDecimal modeMoney, Integer modeTime, Integer status) {
		super();
		this.modeId = modeId;
		this.name = name;
		this.modeMoney = modeMoney;
		this.modeTime = modeTime;
		this.status = status;
		this.modeMoney = modeMoney;
	}
	public Integer getModeId() {
		return this.modeId;
	}
 
	public void setModeId(Integer modeId) {
		this.modeId = modeId;
	}
	public String getName() {
		return this.name;
	}
 
	public void setName(String name) {
		this.name = name;
	}
	
	public BigDecimal getModeMoney() {
		return modeMoney;
	}

	public void setModeMoney(BigDecimal modeMoney) {
		this.modeMoney = modeMoney;
	}

	public Integer getModeTime() {
		return this.modeTime;
	}
 
	public void setModeTime(Integer modeTime) {
		this.modeTime = modeTime;
	}
	
	public String getModeTimeStr() {
		return DateUtil.secToTime(this.modeTime);
	}
	
	public Integer getStatus() {
		return this.status;
	}
 
	public void setStatus(Integer status) {
		this.status = status;
	}

}

