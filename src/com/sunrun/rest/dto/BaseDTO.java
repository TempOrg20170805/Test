package com.sunrun.rest.dto;
/**
 * 
 * @author wangcy
 * @ClassName BaseDTO.java
 * @CreateDate  2017-6-13
 * * 描 述： 基本DTO包括状态码
 * <p> 错误码格式说明（示例：2101）：</p> 
 * <ul>
 *   <li> 2 服务级错误（1为系统级错误） 
 *   <li> 1 总模块id 
 *   <li> 01 具体错误代码
 * </ul>
 */
public class BaseDTO {
	/**
	 * 
	 * @author wangcy
	 * @ClassName BaseDTO.java
	 * @CreateDate  2017-5-3
	 * @descrintion  基本枚举
	 * @editor 
	 * @editDate
	 */
	public enum BaseDTOEnum implements BaseStateDTOEnum{
		/**
		 * 0.API接口调用状态-成功
		 */
		API_STATUS_SUCCESS(0,"成功"),
		/**
		 * 101.API接口调用状态-失败
		 */
		API_STATUS_FAIL(101,"失败"),
		/**
		 * 102.API接口消息-无数据
		 */
		API_MESSAGE_NO_DATA(102,"无数据"),
		/**
		 * 103.API接口消息-缺少参数
		 */
		API_MESSAGE_PARAM_REQUIRED(103,"缺少参数"),
		/**
		 * 104.API接口消息-参数错误
		 */
		API_MESSAGE_PARAM_ERROR(104,"参数错误"),
		/**
		 * 105.API接口消息-用户不存在
		 */
		API_MESSAGE_USER_NOT_FOUND(105,"用户不存在"),
		/**
		 * 106.API接口消息-内容未找到
		 */
		API_MESSAGE_CONTENT_NOT_FOUND(106,"内容未找到"),
		/**
		 * 107.API接口消息-用户未登录
		 */
		API_MESSAGE_USER_NOT_LOGIN(107,"用户未登陆"),
		/**
		 * 108.API接口消息-SESSION错误
		 */
		API_MESSAGE_SESSION_ERROR(108,"SESSION错误"),
		/**
		 * 109. API接口消息-密码错误
		 */
		API_MESSAGE_PASSWORD_ERROR(109,"密码错误"),
		/**
		 * 201.API接口消息-用户已存在
		 */
		API_MESSAGE_USERNAME_EXIST(201,"用户已存在"),
		/**
		 * 202.API接口消息-用户名或密码错误
		 */
		API_MESSAGE_USERNAME_PASSWORD_ERROR(202,"用户名或密码错误"),
		/**
		 * 203.API接口消息-重复请求API
		 */
		API_MESSAGE_REQUEST_REPEAT(203,"对象唯一性重复"),
		/**
		 * 204.API接口消息-用户已禁用
		 */
		API_MESSAGE_USER_NOT_ACTIVATED(204,"用户已禁用"),
		/**
		 * 205.API接口消息-无权限访问
		 */
		API_MESSAGE_NOT_ROLE(205,"无权限访问"),
		/**
		 * 206.API接口消息-验证对象不存在
		 */
		API_MESSAGE_VALIDATECODE_NOTEXIST(206,"验证对象不存在"),
		/**
		 * 207.API接口消息-验证对象错误
		 */
		API_MESSAGE_VALIDATECODE_ERROR(207,"验证对象错误"),
		/**
		 * 208.API接口消息-参数不能为NUll
		 */
		API_MESSAGE_PARAM_NOT_NULL(208,"参数不能为NULL或空"),
		/**
		 * 209.API接口消息-参数唯一性错误
		 */
		API_MESSAGE_PARAM_UNIQUE_ERROR(209,"参数唯一性错误");
		private Integer stateCode;
		private String msg;
		private BaseDTOEnum(Integer stateCode, String msg) {
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
	
	/**
	 * 状态码
	 */
	private Integer stateCode;
	/**
	 * 信息
	 */
	private String msg;

	public Integer getStateCode() {
		return stateCode;
	}

	public String getMsg() {
		return msg;
	}

	public void setState(BaseStateDTOEnum stateDTOEnum) {
		this.msg = stateDTOEnum.getMsg();
		this.stateCode = stateDTOEnum.getStateCode();
	}
}
