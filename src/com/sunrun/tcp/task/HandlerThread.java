package com.sunrun.tcp.task;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.sunrun.tcp.redis.manager.RedisMng;
import com.sunrun.tcp.redis.pool.RedisDataSource;
import com.sunrun.washer.entity.WasherFault;
import com.sunrun.washer.entity.WasherLog;
import com.sunrun.washer.manager.MachineMng;
import com.sunrun.washer.manager.WasherLogMng;

import redis.clients.jedis.ShardedJedis;

/** 
 * @Package: com.sunrun.tcp.task 
 * @ClassName: HandlerThread.java 
 * @Description:数据存储线程，从redis缓存中获取RFID数据，批量插入到后台数据库
 * @author: HL 
 * @date: 创建时间：2017年08月08日 下午1:25:20
 * @version: 1.0 
 */
public class HandlerThread implements Runnable {
	
	/**
	 * logback打印日志，打印等级配置在logback.xml文件
	 */
	private static Logger logger = LoggerFactory.getLogger(HandlerThread.class); 
	/**
	 * REDIS数据源
	 */
	private RedisDataSource redisDataSource;
	/**
	 * 操作REDIS的Jedis句柄对象
	 */
	private ShardedJedis shardedJedis=null;
	/**
	 * 洗衣机表业务层
	 */
	private MachineMng machineMng;
	/**
	 * 洗衣机日志表业务层
	 */
	private WasherLogMng washerLogMng;
	/**
	 * REDIS缓存数据业务层
	 */
	private RedisMng redisMng;
	/**
	 * 洗衣机日志数据缓存列表
	 */
	private List<WasherLog> washerLogs=new ArrayList<WasherLog>();
	/**
	 * 洗衣机故障数据缓存列表
	 */
	private List<WasherFault> washerFaults=new ArrayList<WasherFault>();
	/**
	 * 设置在线离线MAP表
	 */
    private ConcurrentMap<String, Integer> onlineMap=new ConcurrentHashMap<String, Integer>(); 
	
	public HandlerThread(RedisDataSource redisDataSource, MachineMng machineMng,WasherLogMng washerLogMng, RedisMng redisMng) {
		super();
		this.redisDataSource = redisDataSource;
		this.machineMng = machineMng;
		this.washerLogMng=washerLogMng;
		this.redisMng = redisMng;
	}

	//数据存数线程核心循环体，不断从REDIS提取未插入数据库的数据，插入到后台数据库，同时将已插入数据同步缓存到REDIS，方便查询
	@Override
	public void run() {
		// TODO Auto-generated method stub
		try {
			//从REDIS数据源取一个REDIS的操作句柄，整个线程，只取一次，当线程出错关闭，避免每次循环都去线程池取，提高效率
			shardedJedis=redisDataSource.getRedisClient();
			while(true)
			{
				onlineMap.clear();//清空前一次map表
				washerLogs.clear();//清空设备在线离线日志数据列表
				washerFaults.clear();//清空设备故障数据列表
				redisMng.popWasherLogList(shardedJedis, washerLogs, onlineMap);
				redisMng.popWasherFaultList(shardedJedis, washerFaults);
				//有未插入的缓存数据，执行数据插入			
				if(onlineMap.size()>0)
				{
					//更新网关在线状态
					machineMng.updateOnline(onlineMap);
				}
				
				if(washerLogs.size()>0)
				{
					//存储设备在线离线日志数据
					washerLogMng.saveWasherLogList(washerLogs);
				}
				if(washerFaults.size()>0)
				{
					//存储设备故障日志数据
					washerLogMng.saveWasherFaultList(washerFaults);
				}
		
				Thread.sleep(200);//挂起1
			}
		} catch (Exception e) {
			// TODO: handle exception
			logger.error("HandlerThread",e);
		}finally{
			//当线程出错，释放资源，向REDIS线程池归还REDIS操作S句柄
			redisDataSource.returnResource(shardedJedis, true); 
		}
		
	}

}
