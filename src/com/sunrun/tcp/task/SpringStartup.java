package com.sunrun.tcp.task;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;
import org.springframework.stereotype.Component;
import com.sunrun.tcp.redis.manager.RedisMng;
import com.sunrun.tcp.redis.pool.RedisDataSource;
import com.sunrun.washer.manager.MachineMng;
import com.sunrun.washer.manager.WasherLogMng;

/**
* @ClassName: SpringStartup  
* @Description: 系统起来时自动运行一次，创建数据库存储线程
* @author HL  
* @date 2017年08月08日 下午1:04:45  
*
 */
@Component
public class SpringStartup implements ApplicationListener<ApplicationEvent>{

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
	/**
	 * spring启动标示
	 */
	private static boolean isStart = false;
	
	@Override
	public void onApplicationEvent(ApplicationEvent arg0) {
		// TODO Auto-generated method stub
		//防止多次运行
		if (!isStart)
		{
	        isStart = true;
	        HandlerThread handlerThread=new HandlerThread(redisDataSource,machineMng,washerLogMng, redisMng);
	        threadPoolTaskExecutor.execute(handlerThread);
	    }
	}

}
