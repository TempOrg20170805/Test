package com.sunrun.tcp.redis.manager;

import java.util.List;
import java.util.concurrent.ConcurrentMap;

import com.sunrun.tcp.redis.entity.RedisWasherLog;
import com.sunrun.washer.entity.WasherLog;

import redis.clients.jedis.ShardedJedis;

/** 
 * @Package: com.sunrun.tcp.redis.manager 
 * @ClassName: RedisMng.java 
 * @Description:Redis数据业务层
 * @author: HL 
 * @date: 创建时间：2017年4月28日 下午3:38:35
 * @version: 1.0 
 */
public interface RedisMng {

	/**
	* @author: HL
	* @date: 2017年08月08日 下午2:54:47
	* @function: pushWasherLogList  
	* @Description: 洗衣机日志数据缓存到redis
	* @param: @param redisWasherLog
	* @param: @return
	* @return: Long
	* @throws
	*/
	Long pushWasherLogList(RedisWasherLog redisWasherLog);
	
	/**
	* @author: HL
	* @date: 2017年08月08日 下午3:01:34
	* @function: popWasherLogList  
	* @Description: 从REDIS获取洗衣机日志数据和设备在线离线数据 
	* @param: @param shardedJedis
	* @param: @param washerLogs
	* @param: @param onlineMap
	* @param: @return
	* @return: Boolean
	* @throws
	*/
	Boolean popWasherLogList(ShardedJedis shardedJedis,List<WasherLog> washerLogs,ConcurrentMap<String, Integer> onlineMap);
}
