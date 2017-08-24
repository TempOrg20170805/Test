package com.sunrun.washer.manager.impl;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.sunrun.washer.dao.WasherLogDao;
import com.sunrun.washer.entity.WasherFault;
import com.sunrun.washer.entity.WasherLog;
import com.sunrun.washer.manager.WasherLogMng;

/** 
 * @Package: com.sunrun.washer.manager.impl
 * @ClassName: WasherLogMngImpl.java 
 * @Description:洗衣机日志业务层接口实现
 * @author: HL 
 * @date: 创建时间：2017年08月08日 下午2:28:29
 * @version: 1.0 
 */
@Service
public class WasherLogMngImpl implements WasherLogMng{

	/**
	 * logback打印日志，打印等级配置在logback.xml文件
	 */
	private static Logger logger = LoggerFactory.getLogger(WasherLogMngImpl.class); 
	/**
	 * 网关表dao层
	 */
	@Autowired
	private WasherLogDao dao;
	
	@Override
	@Transactional(value="transactionManager",propagation=Propagation.REQUIRED,rollbackFor=Exception.class)
	public Integer saveWasherLogList(List<WasherLog> washerLogs) {
		// TODO Auto-generated method stub
		Integer result=0;
		try {
			for(int i=0;i<washerLogs.size();i++)
			{
				result=dao.saveWasherLog(washerLogs.get(i));
			}
		} catch (Exception e) {
			// TODO: handle exception
			logger.error("WasherLogMngImpl-saveWasherLogList", e);
			result=-1;
		}
		
		return result;
	}

	@Override
	@Transactional(value="transactionManager",propagation=Propagation.REQUIRED,rollbackFor=Exception.class)
	public Integer saveWasherFaultList(List<WasherFault> washerFaults) {
		// TODO Auto-generated method stub
		Integer result=0;
		try {
			for(int i=0;i<washerFaults.size();i++)
			{
				result=dao.saveWasherFault(washerFaults.get(i));
			}
		} catch (Exception e) {
			// TODO: handle exception
			logger.error("WasherLogMngImpl-saveWasherFaultList", e);
			result=-1;
		}
		
		return result;
	}

}
