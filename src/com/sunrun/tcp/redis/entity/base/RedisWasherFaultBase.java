package com.sunrun.tcp.redis.entity.base;

import java.io.Serializable;

/** 
 * @Package: com.sunrun.tcp.redis.entity.base 
 * @ClassName: RedisWasherFaultBase.java 
 * @Description:
 * @author: HL 
 * @date: 创建时间：2017年8月23日 下午4:20:29
 * @version: 1.0 
 */
public abstract class RedisWasherFaultBase implements Serializable{

	/**  
	* @Fields serialVersionUID : TODO(用一句话描述这个变量表示什么)  
	*/  
	private static final long serialVersionUID = 1L;
	/**
	 * 设备ID
	 */
	private String sn;
	/**
	 * 日志:1-进水超时 2-排水超时 3-脱水不平衡 4-盖子未盖 5-水位传感器异常 6-存储区损坏
	 */
	private String fault;
	/**
	 * 日志时间
	 */
	private String time;
	
	public RedisWasherFaultBase(String sn, String fault, String time) {
		super();
		this.sn = sn;
		this.fault = fault;
		this.time = time;
	}

	public String getSn() {
		return sn;
	}

	public void setSn(String sn) {
		this.sn = sn;
	}

	public String getFault() {
		return fault;
	}

	public void setFault(String fault) {
		this.fault = fault;
	}

	public String getTime() {
		return time;
	}

	public void setTime(String time) {
		this.time = time;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	
}
