package com.sunrun.rest.dto;

/**
 * 
 * @author wangcy
 * @ClassName BaseStateDTOEnum.java
 * @CreateDate  2017-5-22
 * @descrintion  状态码枚举接口
 * @editor 
 * @editDate
 */
public interface BaseStateDTOEnum {

	/**
	 * 信息
	 * @return
	 */
	public String getMsg();
	
	/**
	 * 状态码
	 * 
	 * @return
	 */
	public Integer getStateCode();
}
