package com.sunrun.washer.manager;

import java.util.List;

import com.sunrun.washer.entity.WasherLog;

/** 
 * @Package:com.sunrun.washer.manager 
 * @ClassName: WasherLogMng.java 
 * @Description:洗衣机日志业务层接口
 * @author: HL 
 * @date: 创建时间：2017年08月08日 下午2:28:12
 * @version: 1.0 
 */
public interface WasherLogMng {

	/**
	* @author: HL
	* @date: 2017年08月08日 下午2:29:33
	* @function: saveList  
	* @Description: 批量存储洗衣机日志列表
	* @param: @param washerLogs
	* @param: @return
	* @return: Integer
	* @throws
	 */
	Integer saveList(List<WasherLog> washerLogs);
}
