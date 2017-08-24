package com.sunrun.washer.dao;

import com.sunrun.washer.entity.WasherFault;
import com.sunrun.washer.entity.WasherLog;

/** 
 * @Package: com.sunrun.washer.dao
 * @ClassName: WasherLogDao.java 
 * @Description:洗衣机日志表dao层接口
 * @author: HL 
 * @date: 创建时间：2017年08月08日 下午2:24:35
 * @version: 1.0 
 */
public interface WasherLogDao {

	/**
	* @author: HL
	* @date: 2017年08月08日 下午2:26:07
	* @function: saveWasherLog  
	* @Description: 保存洗衣机日志实体类
	* @param: @param washerLog
	* @param: @return
	* @return: Integer
	* @throws
	 */
	Integer saveWasherLog(WasherLog washerLog);
	
	/**
	* @author: HL
	* @date: 2017年08月08日 下午2:26:07
	* @function: saveWasherFault  
	* @Description: 保存洗衣机日志实体类
	* @param: @param washerLog
	* @param: @return
	* @return: Integer
	* @throws
	 */
	Integer saveWasherFault(WasherFault washerFault);
}
