package com.sunrun.washer.model;

import com.jeecms.core.entity.CmsUser;
import com.sunrun.washer.entity.Floor;

/**
 * 文 件 名 : UserFloorModelSave.java
 * 创 建 人： 金明明
 * 日 期：2017-7-31
 * 修 改 人： 
 * 日 期： 
 * 描 述：用户关联楼 查询条件
 */
public class UserFloorModelSave {
	private Integer userId;
	private String addressDetail;
	public Integer getUserId() {
		return userId;
	}
	public void setUserId(Integer userId) {
		this.userId = userId;
	}
	public String getAddressDetail() {
		return addressDetail;
	}
	public void setAddressDetail(String addressDetail) {
		this.addressDetail = addressDetail;
	}
	
	
	

}

