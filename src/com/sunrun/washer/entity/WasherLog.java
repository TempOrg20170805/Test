package com.sunrun.washer.entity;

import java.util.Date;

import com.sunrun.washer.entity.base.WasherLogBase;

/** 
 * @Package: com.sunrun.washer.entity
 * @ClassName: WasherLog.java 
 * @Description:
 * @author: HL 
 * @date: 创建时间：2017年6月29日 下午2:20:09
 * @version: 1.0 
 */
public class WasherLog extends WasherLogBase{

	/**  
	* @Fields serialVersionUID : TODO(用一句话描述这个变量表示什么)  
	*/  
	private static final long serialVersionUID = 1L;

	public WasherLog(String sn, String log, Date time) {
		super(sn, log, time);
		// TODO Auto-generated constructor stub
	}
}
