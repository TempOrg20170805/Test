package com.sunrun.rest.dto;
import java.math.BigDecimal;

import com.sunrun.washer.entity.Mode;
/**
 * 文 件 名 : ModeDTO.java
 * 创 建 人： 金明明
 * 日 期：2017-8-6
 * 修 改 人： 
 * 日 期： 
 * 描 述：洗衣模式DTO
 */
public class ModeDTO {
	private Integer modeId; // 模式ID
	private String name; // 模式名称
	private BigDecimal modeMoney; // 模式金额
	private Integer modeTime; // 该模式需要的时间（单位秒）
	private String modeTimeStr;// 模式时间字符串
	
	public ModeDTO() {};
	
	/**
	 * 初始化洗衣模式
	 * @param mode
	 */
	public ModeDTO(Mode mode) {
		this.modeId = mode.getModeId();
		this.name = mode.getName();
		this.modeMoney = mode.getModeMoney();
		this.modeTime = mode.getModeTime();
		this.modeTimeStr = mode.getModeTimeStr();
	}

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

	public String getModeTimeStr() {
		return modeTimeStr;
	}

	public void setModeTimeStr(String modeTimeStr) {
		this.modeTimeStr = modeTimeStr;
	}
	
	
}

