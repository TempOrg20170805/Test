package com.sunrun.washer.model;

/**
 * 文 件 名 : TroubleModelUpdate.java
 * 创 建 人： 金明明
 * 日 期：2017-8-13
 * 修 改 人： 
 * 日 期： 
 * 描 述：故障 查询条件
 */
public class TroubleModelUpdate {
	private Integer troubleId; // 故障ID
	private Integer status; // 状态 1.待维修 2.维修中 3.维修完毕

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	public Integer getTroubleId() {
		return troubleId;
	}

	public void setTroubleId(Integer troubleId) {
		this.troubleId = troubleId;
	}
	
}

