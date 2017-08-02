package com.sunrun.washer.entity.base;


import com.jeecms.core.entity.CmsUser;
import com.sunrun.washer.entity.Floor;

public class UserFloorBase implements java.io.Serializable {
	
	private Integer userFloorId; // id
	private CmsUser cmsUser; // 用户
	private Floor floor; // 楼

	
	public UserFloorBase() {
	}


	public Integer getUserFloorId() {
		return userFloorId;
	}


	public void setUserFloorId(Integer userFloorId) {
		this.userFloorId = userFloorId;
	}


	public CmsUser getCmsUser() {
		return cmsUser;
	}


	public void setCmsUser(CmsUser cmsUser) {
		this.cmsUser = cmsUser;
	}


	public Floor getFloor() {
		return floor;
	}


	public void setFloor(Floor floor) {
		this.floor = floor;
	}
	
}

