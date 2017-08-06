package com.sunrun.washer.dao.impl;
import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Repository;

import com.jeecms.common.hibernate4.Finder;
import com.jeecms.common.hibernate4.HibernateBaseDao;
import com.jeecms.common.page.Pagination;
import com.sunrun.washer.dao.WalletCashOutDao;
import com.sunrun.washer.entity.WalletCashOut;
import com.sunrun.washer.model.WalletCashOutModel;
/**
 * 文 件 名 : WalletCashOutDaoImpl.java
 * 创 建 人： 金明明
 * 日 期：2017-8-6
 * 修 改 人： 
 * 日 期： 
 * 描 述：提现管理 DaoImpl
 */
@Repository
public class WalletCashOutDaoImpl extends HibernateBaseDao<WalletCashOut, Integer> implements WalletCashOutDao {

	@Override
	public Pagination queryWalletCashOutByModel(WalletCashOutModel walletCashOutModel, Integer pageNo, Integer pageSize) {
		Finder f = queryWalletCashOutBaseFinder("select bean from WalletCashOut bean where 1=1 ");
		if (walletCashOutModel.getUserId() != null) {
			f.append(" and bean.jcUser.id = :userId").setParam("userId", walletCashOutModel.getUserId());
		}
		if (StringUtils.isNotBlank(walletCashOutModel.getUsername())) {
			f.append(" and bean.jcUser.username like :username").setParam("username", "%"+walletCashOutModel.getUsername()+"%");
		}
		if (walletCashOutModel.getState() > 0) {
			f.append(" and bean.state = :state").setParam("state", walletCashOutModel.getState());
		}

		return find(f, pageNo, pageSize);
	}
	
	@Override
	protected Class<WalletCashOut> getEntityClass() {
		return WalletCashOut.class;
	}
	
	@Override
	public WalletCashOut save(WalletCashOut walletCashOut) {
		getSession().save(walletCashOut);
		return walletCashOut;
	}
	
	@Override
	public WalletCashOut findById(Integer walletCashOutId) {
		return get(walletCashOutId);
	}
	
	/**
	 * 创建提现管理基本查询语句
	 * @param baseHQL 必须为WalletCashOut类查询，且临时命名为bean 例："from WalletCashOut bean where 1=1"
	 * @return
	 */
	private Finder queryWalletCashOutBaseFinder(String baseHQL) {
		Finder finder = Finder.create(baseHQL);
		// 此处增加公用的过滤条件，例如：每个提现管理要是有效的
		return finder;
	}
	
	
}

