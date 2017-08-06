package com.sunrun.washer.model;

import java.math.BigDecimal;

/**
 * 文 件 名 : ModeModelUpdate.java
 * 创 建 人： 金明明
 * 日 期：2017-8-6
 * 修 改 人： 
 * 日 期： 
 * 描 述：洗衣模式 查询条件
 */
public class ModeModelUpdate {
	private Integer modeId; // 模式ID
	private String name; // 模式名称
	private BigDecimal modeMoney; // 模式金额
	private Integer modeTime; // 该模式需要的时间（单位秒）
	
	public Integer getModeId() {
		return modeId;
	}
	public void setModeId(Integer modeId) {
		this.modeId = modeId;
	}
	public String getName() {
		return name;
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
		return modeTime;
	}
	public void setModeTime(Integer modeTime) {
		this.modeTime = modeTime;
	}
	

}

