package com.sunrun.rest.dto;
import com.sunrun.washer.entity.*;
/**
 * 文 件 名 : TroubleDTO.java
 * 创 建 人： 金明明
 * 日 期：2017-8-13
 * 修 改 人： 
 * 日 期： 
 * 描 述：故障DTO
 */
public class TroubleDTO {
	public TroubleDTO() {};
	
	/**
	 * 初始化故障
	 * @param trouble
	 */
	public TroubleDTO(Trouble trouble) {
		this.troubleId = trouble.getTroubleId();
		this.troubleReason = trouble.getTroubleReason();
		this.status = trouble.getStatus();
		this.machineNo = trouble.getMachine().getMachineNo();
	}
	
	private Integer troubleId; // 故障ID
	private String troubleReason; // 故障原因
	private Integer status; // 状态 0.删除 1.待维修 2.维修中 3.维修完毕
	private String machineNo; // 洗衣机序列号

	public Integer getTroubleId() {
		return troubleId;
	}

	public void setTroubleId(Integer troubleId) {
		this.troubleId = troubleId;
	}

	public String getTroubleReason() {
		return troubleReason;
	}

	public void setTroubleReason(String troubleReason) {
		this.troubleReason = troubleReason;
	}

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	public String getMachineNo() {
		return machineNo;
	}

	public void setMachineNo(String machineNo) {
		this.machineNo = machineNo;
	}
	
	
}
