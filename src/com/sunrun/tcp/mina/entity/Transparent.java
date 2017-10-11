package com.sunrun.tcp.mina.entity;

import java.io.Serializable;

/** 
 * @Package: com.sunrun.tcp.mina.entity 
 * @ClassName: Transparent.java 
 * @Description:
 * @author: HL 
 * @date: 创建时间：2017年10月11日 上午10:21:09
 * @version: 1.0 
 */
public class Transparent implements Serializable{

	/**  
	* @Fields serialVersionUID : TODO(用一句话描述这个变量表示什么)  
	*/  
	private static final long serialVersionUID = 1L;
	
	/**
	 * 数据
	 */
	private byte[] data;
	/**
	 * 校验码
	 */
	private byte chkCode;
	
	public Transparent(byte[] data, byte chkCode) {
		super();
		this.data = data;
		this.chkCode = chkCode;
	}

	public byte[] getData() {
		return data;
	}

	public void setData(byte[] data) {
		this.data = data;
	}

	public byte getChkCode() {
		return chkCode;
	}

	public void setChkCode(byte chkCode) {
		this.chkCode = chkCode;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	
	

}
