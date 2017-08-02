package com.sunrun.washer.entity.base;


import com.jeecms.core.entity.CmsUser;
import com.sunrun.washer.entity.Machine;

public class UserMachineBase implements java.io.Serializable {
	
	private Integer userMachineId; // id
	private CmsUser cmsUser; // 用户
	private Machine machine; // 洗衣机
	private Integer useType; // 关联类型：1.用户拥有该洗衣机 2.用户收藏该洗衣机
	private Integer authLevel; // 权限等级 1.一级渠道商等级 2.二级渠道商等级 3.三级渠道商等级 99.普通用户权限

	
	public UserMachineBase() {
	}

	public Integer getUserMachineId() {
		return this.userMachineId;
	}
 
	public void setUserMachineId(Integer userMachineId) {
		this.userMachineId = userMachineId;
	}
	
	public Integer getUseType() {
		return this.useType;
	}
 
	public void setUseType(Integer useType) {
		this.useType = useType;
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

	public Integer getAuthLevel() {
		return authLevel;
	}

	public void setAuthLevel(Integer authLevel) {
		this.authLevel = authLevel;
	}

	
}

