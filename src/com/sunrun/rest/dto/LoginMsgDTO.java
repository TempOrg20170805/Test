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
	public enum LoginMsgDTOEnum implements BaseStateDTOEnum{
		GROUP_NULL(701, "该用户未分组,请联系管理员更改会员组"),
		GROUP_ERROR(702, "该用户不能在该app上登录，请联系管理员更改会员组，或更换其它端登录");
		
		private Integer stateCode;
		private String msg;
		private LoginMsgDTOEnum(Integer stateCode, String msg) {
			this.stateCode = stateCode;
			this.msg = msg;
		}
		@Override
		public String getMsg() {
			return this.msg;
		}

		@Override
		public Integer getStateCode() {
			return this.stateCode;
		}
		
	}
	
	
	private Integer userId; // 用户id
	private String username; // 用户名
	private String nickname;//昵称
	private String realname; // 真实姓名
	private String groupname; // 分组分名称 
	private Integer groupId; // 分组Id 1.渠道端 2.普通端
	
	public void initCmsUserDTO(CmsUser cmsUser) {
		this.userId = cmsUser.getId();
		this.username = cmsUser.getUsername();
		this.nickname = cmsUser.getNickname();
		this.realname = cmsUser.getUserExt().getRealname();
		this.groupId = cmsUser.getGroup().getId();
		this.groupname = cmsUser.getGroup().getName();
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
	public String getNickname() {
		return nickname;
	}
	public void setNickname(String nickname) {
		this.nickname = nickname;
	}

	
}
