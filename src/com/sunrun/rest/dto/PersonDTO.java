package com.sunrun.rest.dto;

import com.jeecms.core.entity.CmsUser;

public class PersonDTO extends BaseDTO{

	private Integer id;
	private String userImg;
	private String nickname;
	private String mobile;
	private Integer realnameStatus;//实名状态:1不需要实名，2、待实名，3、实名通过，4、实名未通过 ，5、认证中
	
	public void init(CmsUser entity){
		this.id = entity.getId();
		this.userImg = entity.getUserImg();
		this.nickname = entity.getNickname();
		this.mobile = entity.getMobile();
		this.realnameStatus = entity.getRealnameStatus();
	}
	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getUserImg() {
		return userImg;
	}
	public void setUserImg(String userImg) {
		this.userImg = userImg;
	}
	public String getNickname() {
		return nickname;
	}
	public void setNickname(String nickname) {
		this.nickname = nickname;
	}
	public String getMobile() {
		return mobile;
	}
	public void setMobile(String mobile) {
		this.mobile = mobile;
	}
	public Integer getRealnameStatus() {
		return realnameStatus;
	}
	public void setRealnameStatus(Integer realnameStatus) {
		this.realnameStatus = realnameStatus;
	}
	
	
}
