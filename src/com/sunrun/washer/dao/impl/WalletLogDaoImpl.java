package com.sunrun.washer.dao.impl;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.springframework.stereotype.Repository;

import com.jeecms.common.hibernate4.Finder;
import com.jeecms.common.hibernate4.HibernateBaseDao;
import com.jeecms.common.page.Pagination;
import com.sunrun.washer.dao.WalletLogDao;
import com.sunrun.washer.entity.WalletLog;
import com.sunrun.washer.model.WalletLogModel;
/**
 * 文 件 名 : 消费日志管理
 * 创 建 人： 金明明
 * 日 期：2017-8-6
 * 修 改 人： 
 * 日 期： 
 * 描 述：消费日志管理 DaoImpl
 */
@Repository
public class WalletLogDaoImpl extends HibernateBaseDao<WalletLog, Integer> implements WalletLogDao {

	@Override
	public Pagination queryWalletLogByModel(WalletLogModel walletLogModel, Integer pageNo, Integer pageSize) {
		Finder f = queryWalletLogBaseFinder("select bean from WalletLog bean where 1=1 ");

		if (walletLogModel.getUserId() != null && walletLogModel.getUserId() >0 ){
			f.append(" and bean.jcUser.id = :userId");
			f.setParam("userId", walletLogModel.getUserId());
		}
		
		if (walletLogModel.getType() != null && walletLogModel.getType() >0 ){
			f.append(" and bean.type = :type");
			f.setParam("type", walletLogModel.getType());
		}
		
		if (walletLogModel.getStartTime() != null) {
			f.append(" and bean.time > :startTime").setParam("startTime", walletLogModel.getStartTime());
		}
		if (walletLogModel.getEndTime() != null) {
			f.append(" and bean.time <= :endTime").setParam("endTime", walletLogModel.getEndTime());
		}
		
		f.append(" order by bean.time desc");

		return find(f, pageNo, pageSize);
	}
	
	@Override
	public List<WalletLog> queryWalletLogListByModel(WalletLogModel walletLogModel) {
		Finder f = queryWalletLogBaseFinder("select bean from WalletLog bean where 1=1 ");

		if (walletLogModel.getUserId() != null && walletLogModel.getUserId() >0 ){
			f.append(" and bean.jcUser.id = :userId");
			f.setParam("userId", walletLogModel.getUserId());
		}
		
		f.append(" order by bean.time desc");

		return find(f);
	}

	@Override
	protected Class<WalletLog> getEntityClass() {
		return WalletLog.class;
	}
	
	@Override
	public WalletLog save(WalletLog walletLog) {
		getSession().save(walletLog);
		return walletLog;
	}
	
	@Override
	public WalletLog findById(Integer walletLogId) {
		return get(walletLogId);
	}
	
	
	/**
	 * 创建消费日志管理基本查询语句
	 * @param baseHQL 必须为WalletLog类查询，且临时命名为bean 例："from WalletLog bean where 1=1"
	 * @return
	 */
	private Finder queryWalletLogBaseFinder(String baseHQL) {
		Finder finder = Finder.create(baseHQL);
		// 此处增加公用的过滤条件，例如：每个消费日志管理要是有效的
		return finder;
	}
	
	
}

