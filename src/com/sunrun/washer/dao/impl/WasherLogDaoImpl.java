package com.sunrun.washer.dao.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

import com.jeecms.common.hibernate4.HibernateBaseDao;
import com.sunrun.washer.dao.WasherLogDao;
import com.sunrun.washer.entity.WasherLog;


/** 
 * @Package: com.sunrun.washer.dao.impl
 * @ClassName: WasherLogDaoImpl.java 
 * @Description:洗衣机日志表dao层接口
 * @author: HL 
 * @date: 创建时间：2017年08月08日 下午2:25:02
 * @version: 1.0 
 */
@Repository
public class WasherLogDaoImpl extends HibernateBaseDao<WasherLog, Integer> implements WasherLogDao {

	/**
	 * logback打印日志，打印等级配置在logback.xml文件
	 */
	private static Logger logger = LoggerFactory.getLogger(WasherLogDaoImpl.class); 
	
	@Override
	protected Class<WasherLog> getEntityClass() {
		// TODO Auto-generated method stub
		return WasherLog.class;
	}
	
	@Override
	public Integer save(WasherLog washerLog) {
		// TODO Auto-generated method stub
		try {
			getSession().save(washerLog);
		} catch (Exception e) {
			// TODO: handle exception
			logger.error("WasherLogDaoImpl-save", e);
		}
		return washerLog.getId();
	}


}
