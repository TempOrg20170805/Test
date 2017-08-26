package com.sunrun.tcp.task;

import org.quartz.JobExecutionException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;
import com.sunrun.tcp.redis.manager.RedisMng;
import com.sunrun.tcp.redis.pool.RedisDataSource;
import com.sunrun.washer.manager.MachineMng;
import com.sunrun.washer.manager.WasherLogMng;

/**
* @ClassName: RedisToDbTask  
* @Description: 定时检测数据库线程是否挂掉
* @author HL  
* @date 2017年6月16日 下午3:51:13  
*
 */
public class DatabaseThreadCheck  {

	/**
	 * logback打印日志，打印等级配置在logback.xml文件
	 */
	private static Logger logger = LoggerFactory.getLogger(DatabaseThreadCheck.class); 
	/**
	 * 洗衣机表业务层
	 */
	@Autowired
	private MachineMng machineMng;
	/**
	 * 洗衣机日志表业务层
	 */
	@Autowired
	private WasherLogMng washerLogMng;
	/**
	 * REDIS缓存数据业务层
	 */
	@Autowired
	private RedisMng redisMng;
	/**
	 * 线程池任务管理
	 */
	@Autowired
	private ThreadPoolTaskExecutor threadPoolTaskExecutor;
	/**
	 * redis数据源句柄
	 */
	@Autowired
	private RedisDataSource redisDataSource;
	
	public int timeout;
	// 调度工厂实例化后，经过timeout时间开始执行调度
	public void setTime(int timeout) {
		this.timeout = timeout;
	}
	
	public void execute()
			throws JobExecutionException {
		// TODO Auto-generated method stub
		try 
		{
			//当前定时器也是在线程池中，所以若线程数为1，则表示数据库线程已挂掉，否则线程数应该是2
			if(1==threadPoolTaskExecutor.getActiveCount())
			{
				HandlerThread handlerThread=new HandlerThread(redisDataSource,machineMng,washerLogMng, redisMng);
		        threadPoolTaskExecutor.execute(handlerThread);
			}
		} catch (Exception e) {
			// TODO: handle exception
			logger.error("executeInternal", e);
		}finally{


		}
		
	}
}
