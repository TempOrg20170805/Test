package com.sunrun.rest.dto;

import com.jeecms.core.entity.CmsUser;

/**
 * 文 件 名 : RegisterDTO
 * 创 建 人： 汪长义
 * 日 期：2017年6月1日
 * 修 改 人： 
 * 日 期： 
 * 描 述：注册DTO
 */
public class RegisterDTO extends BaseDTO {
	
	
	private int userId;
	private String username; // 用户名
	private String realname; // 真实姓名
	private String groupname; // 分组分名称 
	private Integer groupId; // 分组Id 1.管理员 2.消费者 3.业务员 4.VIP
	public void initRegisterCmsUserDTO(CmsUser cmsUser) {
		this.userId = cmsUser.getId();
		this.username = cmsUser.getUsername();
		this.realname = cmsUser.getUserExt().getRealname();
		this.groupId = cmsUser.getGroup().getId();
		this.groupname = cmsUser.getGroup().getName();
	}

	public int getUserId() {
		return userId;
	}

	public void setUserId(int userId) {
		this.userId = userId;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getRealname() {
		return realname;
	}

	public void setRealname(String realname) {
		this.realname = realname;
	}

	public String getGroupname() {
		return groupname;
	}

	public void setGroupname(String groupname) {
		this.groupname = groupname;
	}

	public Integer getGroupId() {
		return groupId;
	}

	public void setGroupId(Integer groupId) {
		this.groupId = groupId;
	}
	
	

}
