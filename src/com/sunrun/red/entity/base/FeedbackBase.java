package com.sunrun.red.entity.base;

import java.util.Date;

import com.jeecms.core.entity.CmsUser;

public class FeedbackBase implements java.io.Serializable {
	
	private Integer id;
	private String info;
	private CmsUser user;
	private Date createTime;
	
	
	public FeedbackBase() {
	}

	
	public FeedbackBase(Integer id) {
		this.id = id;
	}
	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getInfo() {
		return info;
	}
	public void setInfo(String info) {
		this.info = info;
	}
	public CmsUser getUser() {
		return user;
	}
	public void setUser(CmsUser user) {
		this.user = user;
	}
	public Date getCreateTime() {
		return createTime;
	}
	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}
	public FeedbackBase(Integer id, String info, CmsUser user, Date createTime) {
		super();
		this.id = id;
		this.info = info;
		this.user = user;
		this.createTime = createTime;
	}
	
	
}
