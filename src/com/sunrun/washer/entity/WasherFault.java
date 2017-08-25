package com.sunrun.washer.entity;

import java.util.Date;

import com.sunrun.washer.entity.base.WasherFaultBase;

/** 
 * @Package: com.sunrun.washer.entity 
 * @ClassName: WasherFault.java 
 * @Description:
 * @author: HL 
 * @date: 创建时间：2017年8月23日 下午4:35:45
 * @version: 1.0 
 */
public class WasherFault extends WasherFaultBase {

	/**  
	* @Fields serialVersionUID : TODO(用一句话描述这个变量表示什么)  
	*/  
	private static final long serialVersionUID = 1L;

	public WasherFault(String sn, String fault, Date time) {
		super(sn, fault, time);
		// TODO Auto-generated constructor stub
	}

}
