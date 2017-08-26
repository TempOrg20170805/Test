package com.sunrun.washer.entity.base;

import java.util.Date;

/**
* @Package: com.sunrun.washer.entity.base
* @ClassName: WasherFaultBase  
* @Description: TODO(这里用一句话描述这个类的作用)  
* @author HL  
* @date 2017年8月8日 下午2:04:17  
*/
public abstract class WasherFaultBase implements java.io.Serializable{

	/**  
	* @Fields serialVersionUID : TODO(用一句话描述这个变量表示什么)  
	*/  
	private static final long serialVersionUID = 1L;
	/**
	 * 表主键ID
	 */
	private Integer id;
	/**
	 * 洗衣机序列号
	 */
	private String sn;
	/**
	 * 洗衣机日志
	 */
	private String fault;
	/**
	 * 发送时间
	 */
	private Date time;
	
	public WasherFaultBase(String sn, String fault, Date time) {
		super();
		this.sn = sn;
		this.fault = fault;
		this.time = time;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
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

	public Date getTime() {
		return time;
	}

	public void setTime(Date time) {
		this.time = time;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	
	
	
}
