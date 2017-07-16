package com.sunrun.rest.dto;

import com.jeecms.core.entity.CmsUser;

/**
 * 
 * @author wangcy
 * @ClassName LoginMsgDTO.java
 * @CreateDate  2017-6-13
 * @descrintion  用户登录返回信息
 * @editor 
 * @editDate
 */
public class LoginMsgDTO extends BaseDTO{
	
	
	
	private Integer userId; // 用户id
	private String username; // 用户名
	private String nickname;//昵称
	private String realname; // 真实姓名
	private String groupname; // 分组分名称 
	private Integer groupId; // 分组Id 1.民用端会员 2.安装端会员 3.警用端会员4.管理端会员
	private java.lang.String userImg;//头像
	private Integer realnameStatus;//实名状态:1不需要实名，2、待实名，3、实名通过，4、实名未通过
	private Integer membership;//会员状态 0表示不是会员
	private String icon1Url;//会员图标
	private String imgUrl;//会员背景图
	private Long dueDate;//到期日期
	private String plateNumber;//靓号
	
	public void initCmsUserDTO(CmsUser cmsUser) {
		this.userId = cmsUser.getId();
		this.username = cmsUser.getUsername();
		this.nickname = cmsUser.getNickname();
		this.realname = cmsUser.getUserExt().getRealname();
		this.groupId = cmsUser.getGroup().getId();
		this.groupname = cmsUser.getGroup().getName();
		if(cmsUser.getUserImg()==null){
			this.userImg = "";
		}else{
			this.userImg = cmsUser.getUserImg();
		}
		this.realnameStatus=cmsUser.getRealnameStatus();
	}
	public Integer getUserId() {
		return userId;
	}
	public String getUsername() {
		return username;
	}
	public String getRealname() {
		return realname;
	}
	public String getGroupname() {
		return groupname;
	}
	public Integer getGroupId() {
		return groupId;
	}
	public java.lang.String getUserImg() {
		return userImg;
	}
	public void setUserImg(java.lang.String userImg) {
		this.userImg = userImg;
	}
	public void setUserId(Integer userId) {
		this.userId = userId;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public void setRealname(String realname) {
		this.realname = realname;
	}
	public void setGroupname(String groupname) {
		this.groupname = groupname;
	}
	public void setGroupId(Integer groupId) {
		this.groupId = groupId;
	}
	public Integer getRealnameStatus() {
		return realnameStatus;
	}
	public void setRealnameStatus(Integer realnameStatus) {
		this.realnameStatus = realnameStatus;
	}
	public String getNickname() {
		return nickname;
	}
	public void setNickname(String nickname) {
		this.nickname = nickname;
	}
	public Integer getMembership() {
		return membership;
	}
	public void setMembership(Integer membership) {
		this.membership = membership;
	}
	public String getIcon1Url() {
		return icon1Url;
	}
	public void setIcon1Url(String icon1Url) {
		this.icon1Url = icon1Url;
	}
	public String getImgUrl() {
		return imgUrl;
	}
	public void setImgUrl(String imgUrl) {
		this.imgUrl = imgUrl;
	}
	public Long getDueDate() {
		return dueDate;
	}
	public void setDueDate(Long dueDate) {
		this.dueDate = dueDate;
	}
	public String getPlateNumber() {
		return plateNumber;
	}
	public void setPlateNumber(String plateNumber) {
		this.plateNumber = plateNumber;
	}
	
}
