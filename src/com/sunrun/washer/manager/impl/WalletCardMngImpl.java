package com.sunrun.washer.manager.impl;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.jeecms.common.hibernate4.Updater;
import com.jeecms.core.manager.CmsUserMng;
import com.sunrun.washer.dao.WalletCardDao;
import com.sunrun.washer.entity.WalletCard;
import com.sunrun.washer.manager.WalletCardMng;
import com.sunrun.washer.model.WalletCardModel;
import com.sunrun.washer.model.WalletCardModelSave;
/**
 * 文 件 名 : WalletCardMngImpl.java
 * 创 建 人： 金明明
 * 日 期：2017-8-6
 * 修 改 人： 
 * 日 期： 
 * 描 述：银行卡/支付宝管理 MngImpl
 */
@Service
@Transactional
public class WalletCardMngImpl implements WalletCardMng{

	@Autowired
	private WalletCardDao walletCardDao;
	@Autowired
	private CmsUserMng jcUserMng;

	
	@Override
	public List<WalletCard> queryWalletCardListByModel(WalletCardModel walletCardModel) {
		return walletCardDao.queryWalletCardListByModel(walletCardModel);
	}
	
	@Override
	public WalletCard saveWalletCard(WalletCardModelSave walletCardModelSave) {
		WalletCard bean = new WalletCard();
		bean.setJcUser(jcUserMng.findById(walletCardModelSave.getUserId()));
		bean.setRealname(walletCardModelSave.getRealname());
		bean.setType(walletCardModelSave.getType());
		bean.setBankName(walletCardModelSave.getBankName());
		bean.setBankNum(walletCardModelSave.getBankNum());
		bean.setBankBranches(walletCardModelSave.getBankBranches());
		bean.setAlipayNum(walletCardModelSave.getAlipayNum());
		bean.setCollectionCode(walletCardModelSave.getCollectionCode());
		// 赋值保存的必要信息
		return walletCardDao.save(bean);
	}

	@Override
	public WalletCard deleteById(Integer id) {
		WalletCard bean = findById(id);
		bean.setStatus(WalletCard.WalletCardStatusEnum.DELETE.getValue());
		updateWalletCard(bean);
		return bean;
	}
	
	@Override
	public WalletCard findById(Integer id) {
		return walletCardDao.findById(id);
	}
	
	/**
	 * 更新
	 * @param bean
	 * @return
	 */
	private WalletCard updateWalletCard(WalletCard bean) {
		Updater<WalletCard> updater = new Updater<WalletCard>(bean);
		return walletCardDao.updateByUpdater(updater);
	}
	


}

