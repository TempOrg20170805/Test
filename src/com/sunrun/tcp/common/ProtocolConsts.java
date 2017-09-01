package com.sunrun.tcp.common;
/** 
 * @Package: com.sunrun.tcp.common.constants 
 * @ClassName: ProtocolConsts.java 
 * @Description:设备与服务器之间通讯消息协议字段常量类
 * @author: HL 
 * @date: 创建时间：2017年5月31日 下午1:38:47
 * @version: 1.0 
 */
public class ProtocolConsts {

	
	/*数据消息类型常量定义*/
	/**
	 * 开始洗涤
	 */
	public final static byte MSGTYPE_WASH_START=0x01;
	/**
	 * 洗涤完成
	 */
	public final static byte MSGTYPE_WASH_OVER=0x02;
	/**
	 * 洗涤状态恢复
	 */
	public final static byte MSGTYPE_WASH_STATUS_RESP=0x09;
	/**
	 * 心跳包消息类型
	 */
	public final static byte MSGTYPE_HEARTBEAT=0x0F;
	/**
	 * 故障上报
	 */
	public final static byte MSGTYPE_WASH_FAULT=0x24;
	/*数据消息类型对应数据包长度常量定义*/
	/**
	 * 数据包最小长度
	 */
	public final static byte PACKAGE_MIN_LEN=0x0C;
	/**
	 * 心跳包长度
	 */
	public final static byte PACKAGE_HEARTBEAT_LEN=0x0C;
	/**
	 *设备端洗涤响应数据包长度
	 */
	public final static byte PACKAGE_WASHANSWER_LEN=0x0E;
	/**
	 *服务端指令下发数据包长度
	 */
	public final static byte PACKAGE_WASHORDER_LEN=0x0C;
	
	 /**
     * 本地东八区北京时间时间戳格式
     */
    public static final String LOCAL_DATE_PATTEN="yyyy-MM-dd HH:mm:ss";
	
	/**
	 * 固定头
	 */
	public static final byte[] PACKET_HEADER ={(byte)0x53,(byte)0x4A};
	
	/**
	 * 设备在线
	 */
	public static final byte DEVICE_ONLINE = 0x01;
	/**
	 * 设备离线
	 */
	public static final byte DEVICE_OFFLINE = 0x00;
	
	/**
	 * 厂家ID
	 */
	public static final byte FACTORY_ID = 0x01;
	
	/**
	* @ClassName: ProtocolField  
	* @Description: 协议字段在数据包中位置常量枚举类  
	* @author HL  
	* @date 2017年05月31日 下午1:43:33  
	*/
	public static enum ProtocolField {
		
		HEADER(0,2),/*协议数据包同步头0x53 4A开始位和长度*/
		PACKAGE_LEN(2,1),/*数据包长度字节在整个数据包中开始位和长度*/
		FACTORY_ID(3,1),/*厂家ID字节在整个数据包中开始位和长度*/
		DEVICEID(4,6),/*设备ID*/
		MSGTYPE(10,1),/*消息指令字节在整个数据包中开始位和长度*/
		RESERVE(11,2),/*预留位*/
		WASHCHECKCODE(13,1),/*洗涤数据包校验码*/
		HEARTCHECKCODE(11,1);/*心跳包校验码*/
		/**
		 * 协议字段在整个数据包中的开始位置
		 */
		private int pos;
		/**
		 * 协议字段数据的长度
		 */
		private int len;

		private ProtocolField(int pos, int len) {
			this.pos = pos;
			this.len = len;
		}

		public int getPos() {
			return pos;
		}

		public int getLen() {
			return len;
		}
		
	}
	
	/**
	* @ClassName: FaultLog  
	* @Description: 报警事件常量枚举类
	* @author HL  
	* @date 2017年08月23日 上午9:47:19  
	*
    */
	public static enum FaultLog {
	
		JINSHUIOVERTIME( new byte[]{0x01,0x00},"进水超时"),
		PAISHUIOVERTIME( new byte[]{0x02,0x00},"排水超时"),
		TUOSHUIUNBALANCED( new byte[]{0x03,0x00},"脱水不平衡"),
		CAPFAULT( new byte[]{0x04,0x00},"盖子未盖"),
		WATERLEVELSENSOR( new byte[]{0x05,0x00},"水位传感器异常"),
		STORAGEDAMAGE( new byte[]{0x06,0x00},"存储区损坏");
		
		/**
		 * 故障代码
		 */
		private byte[] code;
		/**
		 * 报警事件
		 */
		private String fault;
		
		private FaultLog(byte[] code, String fault) {
			this.code = code;
			this.fault = fault;
		}

		public byte[] getCode() {
			return code;
		}

		public void setCode(byte[] code) {
			this.code = code;
		}

		public String getFault() {
			return fault;
		}

		public void setFault(String fault) {
			this.fault = fault;
		}

	}
	
}
