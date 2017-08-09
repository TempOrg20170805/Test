package com.sunrun.washer.entity.base;

import java.util.Date;

/**
* @Package: com.sunrun.washer.entity.base
* @ClassName: WasherLogBase  
* @Description: TODO(这里用一句话描述这个类的作用)  
* @author HL  
* @date 2017年8月8日 下午2:04:17  
*/
public abstract class WasherLogBase implements java.io.Serializable{

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
	private String log;
	/**
	 * 发送时间
	 */
	private Date time;
	
	public WasherLogBase(String sn, String log, Date time) {
		super();
		this.sn = sn;
		this.log = log;
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

	public String getLog() {
		return log;
	}

	public void setLog(String log) {
		this.log = log;
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
