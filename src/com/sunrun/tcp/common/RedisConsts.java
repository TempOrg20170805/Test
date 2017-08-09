package com.sunrun.tcp.common;
/** 
 * @Package: com.sunrun.tcp.common.constants 
 * @ClassName: RedisConsts.java 
 * @Description:REDIS层使用的常量类
 * @author: HL 
 * @date: 创建时间：2017年06月16日 下午3:53:32
 * @version: 1.0 
 */
public class RedisConsts {

	 /**
     * 本地东八区北京时间时间戳格式
     */
    public static final String LOCAL_DATE_PATTEN="yyyy-MM-dd HH:mm:ss";
    /**
     * GPSID格式正则表达式验证
     */
    public static final String REGEX_GATEAWAYSN="^[0-9A-F]{2}[0-9A-F]{2}[0-9A-F]{2}[0-9A-F]{2}$";
    
    /**
     * RFID格式正则表达式验证
     */
    public static final String REGEX_RFID="^[0-9A-F]{2}[0-9A-F]{2}[0-9A-F]{2}[0-9A-F]{2}[0-9A-F]{2}[0-9A-F]{2}$";
	
	/**
	* @ClassName: RedisListKeys  
	* @Description: REDIS LIST队里KEY值常量枚举类
	* @author HL  
	* @date 2017年06月02日 下午4:21:44  
	*
	 */
	public static enum RedisListKeys {
		
		RFIDSLIST("rfids"),/*rfid数据*/
		ONLINELIST("onlines"),/*设备在线数据*/
		LOGLIST("logs");/*网关日志*/
		private String key;

		private RedisListKeys(String key) {
			this.key = key;
		}

		public String getKey() {
			return key;
		}

		public void setKey(String key) {
			this.key = key;
		}
	}
	
	/**
	* @ClassName: RedisListJsonKeys  
	* @Description: Redis LIST 中JSON数据key值常量枚举类
	* @author HL  
	* @date 2017年06月016日 下午4:22:11  
	*
	 */
	public static enum RedisListJsonKeys {
		
		SN("sn"),
		RFIDS("rfids"),
		TYPE("type"),
		LOG("log"),
		TIME("time"),
		ONLINE("online");
		
		private String key;

		private RedisListJsonKeys(String key) {
			this.key = key;
		}

		public String getKey() {
			return key;
		}

		public void setKey(String key) {
			this.key = key;
		}

	}
	
	/**
	* @ClassName: RedisListDealSize  
	* @Description: 后台线程每次处理的缓存数据最大记录数常量枚举类
	* @author HL  
	* @date 2017年06月16日 下午4:27:08  
	*
	 */
	public static enum RedisListDealSize {
		
		RFIDLISTSIZE(100),
		ONLINELISTSIZE(500),
		LOGLISTSIZE(500);
		private int size;

		private RedisListDealSize(int size) {
			this.size = size;
		}

		public int getSize() {
			return size;
		}

		public void setSize(int size) {
			this.size = size;
		}
	}
}
