package com.sunrun.washer.entity.base;


import java.util.Date;

import com.jeecms.core.entity.*;
import com.sunrun.washer.entity.Machine;
import com.sunrun.washer.enums.TroubleStatusEnum;

public class TroubleBase implements java.io.Serializable {
	
	private Integer troubleId; // id
	private String troubleReason; // 故障原因
	private CmsUser cmsUser; // 用户
	private Machine machine; // 洗衣机
	private Integer status; // 状态 0.删除 1.待维修 2.维修中 3.维修完毕
	private Date createTime; // 创建时间
	private Date startTime; // 开始维修时间
	private Date SuccessTime; // 维修成功时间

	
	public TroubleBase() {
	}


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


	public CmsUser getCmsUser() {
		return cmsUser;
	}


	public void setCmsUser(CmsUser cmsUser) {
		this.cmsUser = cmsUser;
	}


	public Machine getMachine() {
		return machine;
	}


	public void setMachine(Machine machine) {
		this.machine = machine;
	}


	public Integer getStatus() {
		return status;
	}


	public void setStatus(Integer status) {
		this.status = status;
	}
	
	public String getStatusStr() {
		return TroubleStatusEnum.getContains(this.status).getDescribe();
	}


	public Date getCreateTime() {
		return createTime;
	}


	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}


	public Date getStartTime() {
		return startTime;
	}


	public void setStartTime(Date startTime) {
		this.startTime = startTime;
	}


	public Date getSuccessTime() {
		return SuccessTime;
	}


	public void setSuccessTime(Date successTime) {
		SuccessTime = successTime;
	}


}

