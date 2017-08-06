package com.sunrun.washer.model;

import java.util.Date;

/**
 * 文 件 名 : 消费日志管理
 * 创 建 人： 金明明
 * 日 期：2017-8-6
 * 修 改 人： 
 * 日 期： 
 * 描 述：消费日志管理 查询条件
 */
public class WalletLogModel {
	
	// app查询
	private Integer userId; // 用户Id
	private Date startTime; // 开始时间
	private Date endTime; // 结束时间

	public Integer getUserId() {
		return userId;
	}

	public void setUserId(Integer userId) {
		this.userId = userId;
	}

	public Date getStartTime() {
		return startTime;
	}

	public void setStartTime(Date startTime) {
		this.startTime = startTime;
	}

	public Date getEndTime() {
		return endTime;
	}

	public void setEndTime(Date endTime) {
		this.endTime = endTime;
	}
	

}

