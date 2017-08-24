package com.sunrun.tcp.redis.manager.impl;

import java.text.SimpleDateFormat;
import java.util.List;
import java.util.concurrent.ConcurrentMap;

import net.sf.json.JSONObject;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import redis.clients.jedis.ShardedJedis;

import com.sunrun.tcp.common.RedisConsts;
import com.sunrun.tcp.redis.dao.RedisDao;
import com.sunrun.tcp.redis.entity.RedisWasherFault;
import com.sunrun.tcp.redis.entity.RedisWasherLog;
import com.sunrun.tcp.redis.manager.RedisMng;
import com.sunrun.washer.entity.WasherFault;
import com.sunrun.washer.entity.WasherLog;

/** 
 * @Package: com.sunrun.tcp.redis.manager.impl 
 * @ClassName: RedisMngImpl.java 
 * @Description:Redis数据业务层，从Redis提取数据存到db
 * @author: HL 
 * @date: 创建时间：2017年4月28日 下午3:39:42
 * @version: 1.0 
 */
@Service
public class RedisMngImpl implements RedisMng {

	//logback打印日志，打印等级配置在logback.xml文件
	private static Logger logger = LoggerFactory.getLogger(RedisMngImpl.class); 
	
	//redis dao层
	@Autowired
	private RedisDao redisdao;

	@Override
	public Long pushWasherLogList(RedisWasherLog redisWasherLog) {
		// TODO Auto-generated method stub
		Long result=(long) 0;
		try 
		{
			JSONObject jsonObject=JSONObject.fromObject(redisWasherLog);
			result=redisdao.rpush(RedisConsts.RedisListKeys.LOGLIST.getKey(), jsonObject.toString());
			logger.info(jsonObject.toString());
		} catch (Exception e) {
			// TODO: handle exception
			logger.error("RedisMngImpl-pushWasherLogList",e);
		}
		return result;
	}

	@Override
	public Boolean popWasherLogList(ShardedJedis shardedJedis,
			List<WasherLog> washerLogs,
			ConcurrentMap<String, Integer> onlineMap) {
		// TODO Auto-generated method stub
		Boolean result=true;
		try {
			//获取当前REDIS中未插入数据库报警事件列表的缓存长度， RedisConsts.RedisListDealSize.LOGLISTSIZE.getSize()为默认配置最大缓存数据处理长度
			long catchSize=redisdao.llen(shardedJedis,RedisConsts.RedisListKeys.LOGLIST.getKey());				
			if(catchSize > RedisConsts.RedisListDealSize.LOGLISTSIZE.getSize())
					catchSize=RedisConsts.RedisListDealSize.LOGLISTSIZE.getSize();

			//从REDIS中提取catchSize个未插入数据库的缓存数据，进行列表填充，后续插入到数据库
			while(catchSize-->0){
				String data=redisdao.lpop(RedisConsts.RedisListKeys.LOGLIST.getKey());
				if(null==data)
					break;
				JSONObject jsonObject=JSONObject.fromObject(data);
				String sn=jsonObject.getString(RedisConsts.RedisListJsonKeys.SN.getKey());
				Integer type=jsonObject.getInt(RedisConsts.RedisListJsonKeys.TYPE.getKey());
				String log=jsonObject.getString(RedisConsts.RedisListJsonKeys.LOG.getKey());
				String time=jsonObject.getString(RedisConsts.RedisListJsonKeys.TIME.getKey());
				logger.debug("sn="+sn+" "+"log="+log);
				//获取设备ID和在线状态键值对，若设备ID重复，则用最新的覆盖
				onlineMap.put(sn, type);
				WasherLog washerLog=new WasherLog(sn, log, new SimpleDateFormat(RedisConsts.LOCAL_DATE_PATTEN).parse(time));
				washerLogs.add(washerLog);
			}
		} catch (Exception e) {
			// TODO: handle exception
		}
		return result;
	}

	@Override
	public Long pushWasherFaultList(RedisWasherFault redisWasherFault) {
		// TODO Auto-generated method stub
		Long result=(long) 0;
		try 
		{
			JSONObject jsonObject=JSONObject.fromObject(redisWasherFault);
			result=redisdao.rpush(RedisConsts.RedisListKeys.FAULTLIST.getKey(), jsonObject.toString());
			logger.info(jsonObject.toString());
		} catch (Exception e) {
			// TODO: handle exception
			logger.error("RedisMngImpl-pushWasherFaultList",e);
		}
		return result;
	}

	@Override
	public Boolean popWasherFaultList(ShardedJedis shardedJedis,
			List<WasherFault> washerFaults) {
		// TODO Auto-generated method stub
		Boolean result=true;
		try {
			//获取当前REDIS中未插入数据库报警事件列表的缓存长度， RedisConsts.RedisListDealSize.FAULTLIST.getSize()为默认配置最大缓存数据处理长度
			long catchSize=redisdao.llen(shardedJedis,RedisConsts.RedisListKeys.FAULTLIST.getKey());				
			if(catchSize > RedisConsts.RedisListDealSize.FAULTLISTSIZE.getSize())
					catchSize=RedisConsts.RedisListDealSize.FAULTLISTSIZE.getSize();

			//从REDIS中提取catchSize个未插入数据库的缓存数据，进行列表填充，后续插入到数据库
			while(catchSize-->0){
				String data=redisdao.lpop(RedisConsts.RedisListKeys.FAULTLIST.getKey());
				if(null==data)
					break;
				JSONObject jsonObject=JSONObject.fromObject(data);
				String sn=jsonObject.getString(RedisConsts.RedisListJsonKeys.SN.getKey());
				String fault=jsonObject.getString(RedisConsts.RedisListJsonKeys.FAULT.getKey());
				String time=jsonObject.getString(RedisConsts.RedisListJsonKeys.TIME.getKey());
				logger.debug("sn="+sn+" "+"fault="+fault);
				WasherFault washerFault=new WasherFault(sn, fault, new SimpleDateFormat(RedisConsts.LOCAL_DATE_PATTEN).parse(time));
				washerFaults.add(washerFault);
			}
		} catch (Exception e) {
			// TODO: handle exception
		}
		return result;
	}
	
}
