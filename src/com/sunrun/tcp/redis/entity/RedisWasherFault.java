package com.sunrun.tcp.redis.entity;

import com.sunrun.tcp.redis.entity.base.RedisWasherFaultBase;

/** 
 * @Package: com.sunrun.tcp.redis.entity 
 * @ClassName: RedisWasherFault.java 
 * @Description:
 * @author: HL 
 * @date: 创建时间：2017年8月23日 下午4:24:39
 * @version: 1.0 
 */
public class RedisWasherFault extends RedisWasherFaultBase{

	/**  
	* @Fields serialVersionUID : TODO(用一句话描述这个变量表示什么)  
	*/  
	private static final long serialVersionUID = 1L;

	public RedisWasherFault(String sn, String fault, String time) {
		super(sn, fault, time);
		// TODO Auto-generated constructor stub
	}

}
