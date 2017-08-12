package com.sunrun.washer.dao.impl;
import java.util.List;

import org.springframework.stereotype.Repository;

import com.jeecms.common.hibernate4.Finder;
import com.jeecms.common.hibernate4.HibernateBaseDao;
import com.sunrun.washer.dao.WalletCardDao;
import com.sunrun.washer.entity.WalletCard;
import com.sunrun.washer.enums.WalletCardStatusEnum;
import com.sunrun.washer.model.WalletCardModel;
/**
 * 文 件 名 : WalletCardDaoImpl.java
 * 创 建 人： 金明明
 * 日 期：2017-8-6
 * 修 改 人： 
 * 日 期： 
 * 描 述：银行卡/支付宝管理 DaoImpl
 */
@Repository
public class WalletCardDaoImpl extends HibernateBaseDao<WalletCard, Integer> implements WalletCardDao {
	
	@Override
	public List<WalletCard> queryWalletCardListByModel(WalletCardModel walletCardModel) {
		Finder f = queryWalletCardBaseFinder("select bean from WalletCard bean where 1=1 ");

		if (walletCardModel.getType() != null && walletCardModel.getType() > 0) {
			f.append(" and bean.type = :type").setParam("type", walletCardModel.getType());
		}
		if (walletCardModel.getUserId() != null) {
			f.append(" and bean.jcUser.id = :userId").setParam("userId", walletCardModel.getUserId());
		}
		
		f.append(" order by bean.type asc");

		return find(f);
	}

	@Override
	protected Class<WalletCard> getEntityClass() {
		return WalletCard.class;
	}
	
	@Override
	public WalletCard save(WalletCard walletCard) {
		getSession().save(walletCard);
		return walletCard;
	}
	
	@Override
	public WalletCard findById(Integer walletCardId) {
		return get(walletCardId);
	}
	
	@Override
	public WalletCard deleteById(Integer walletCardId) {
		WalletCard entity = super.get(walletCardId);
		if (entity != null) {
			getSession().delete(entity);
		}
		return entity;
	}
	
	
	/**
	 * 创建银行卡/支付宝管理基本查询语句
	 * @param baseHQL 必须为WalletCard类查询，且临时命名为bean 例："from WalletCard bean where 1=1"
	 * @return
	 */
	private Finder queryWalletCardBaseFinder(String baseHQL) {
		Finder finder = Finder.create(baseHQL);
		finder.append(" and bean.status <> :deleteStatus");
		finder.setParam("deleteStatus", WalletCardStatusEnum.DELETE.getValue());
		return finder;
	}
	
	
}

