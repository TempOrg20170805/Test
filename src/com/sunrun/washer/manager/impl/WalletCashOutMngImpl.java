package com.sunrun.washer.manager.impl;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.jeecms.common.hibernate4.Updater;
import com.jeecms.common.page.Pagination;
import com.jeecms.core.entity.CmsUser;
import com.jeecms.core.manager.CmsUserMng;
import com.sunrun.washer.dao.WalletCashOutDao;
import com.sunrun.washer.entity.WalletCard;
import com.sunrun.washer.entity.WalletCashOut;
import com.sunrun.washer.enums.WalletCardTypeEnum;
import com.sunrun.washer.enums.WalletCashOutStateEnum;
import com.sunrun.washer.enums.WalletLogPayPlatformEnum;
import com.sunrun.washer.enums.WalletLogTypeEnum;
import com.sunrun.washer.manager.WalletCardMng;
import com.sunrun.washer.manager.WalletCashOutMng;
import com.sunrun.washer.model.WalletCashOutModel;
import com.sunrun.washer.model.WalletCashOutModelSave;
import com.sunrun.washer.model.WalletCashOutModelUpdate;
/**
 * 文 件 名 : WalletCashOutMngImpl.java
 * 创 建 人： 金明明
 * 日 期：2017-8-6
 * 修 改 人： 
 * 日 期： 
 * 描 述：提现管理 MngImpl
 */
@Service
@Transactional
public class WalletCashOutMngImpl implements WalletCashOutMng{

	@Autowired
	private WalletCashOutDao walletCashOutDao;
	@Autowired
	private CmsUserMng cmsUserMng;
	@Autowired
	private WalletCardMng walletCardMng;

	@Override
	public Pagination queryWalletCashOutByModel(WalletCashOutModel walletCashOutModel, Integer pageNo, Integer pageSize) {
		Pagination pagination = walletCashOutDao.queryWalletCashOutByModel(walletCashOutModel, pageNo, pageSize);
		// 编写代码
		return pagination;
	}
	
	@Override
	public WalletCashOut saveWalletCashOut(WalletCashOutModelSave walletCashOutModelSave) {
		WalletCashOut bean = new WalletCashOut();
		CmsUser jcUser = cmsUserMng.findById(walletCashOutModelSave.getUserId());
		WalletCard walletCard = walletCardMng.findById(walletCashOutModelSave.getCardId());
		bean.setJcUser(jcUser);
		bean.setMoney(walletCashOutModelSave.getMoney());
		bean.setState(WalletCashOutStateEnum.CASH_OUT_DO.getValue());
		bean.setCashOutType(walletCard.getType());
		if (WalletCardTypeEnum.ALIPAY_CARD.getValue().equals(walletCard.getType())) {
			bean.setCardNo(walletCard.getAlipayNum());
		} else if(WalletCardTypeEnum.BANK_CARD.getValue().equals(walletCard.getType())) {
			bean.setCardNo(walletCard.getBankNum());
		} else {
			bean.setCardNo("");
		}
		bean.setCardName(walletCard.getRealname());
		bean.setCardCompany(walletCard.getBankName());
		bean.setCardNetwork(walletCard.getBankBranches());
		bean.setCreateTime(new Date());
		bean.setCollectionCode(walletCard.getCollectionCode());
		if (walletCard.getBank() != null) {
			bean.setBank(walletCard.getBank());
		}
		// 赋值保存的必要信息
		return walletCashOutDao.save(bean);
	}

	@Override
	public WalletCashOut updateWalletCashOut(WalletCashOutModelUpdate walletCashOutModelUpdate) {
		WalletCashOut walletCashOut = findById(walletCashOutModelUpdate.getWalletCashOutId());
		if (WalletCashOutStateEnum.SUCCESS.getValue().equals(walletCashOutModelUpdate.getState())) {
			cmsUserMng.updateMoney(walletCashOut.getJcUser().getId(), walletCashOut.getMoney().negate(), WalletLogTypeEnum.CASHOUT.getCode(), WalletLogPayPlatformEnum.WALLET.getCode(),WalletLogTypeEnum.CASHOUT.getDescribe());
		}
		walletCashOut.setState(walletCashOutModelUpdate.getState());
		walletCashOut.setHandleTime(new Date());
		return updateWalletCashOut(walletCashOut);
	}
	
	@Override
	public WalletCashOut findById(Integer id) {
		return walletCashOutDao.findById(id);
	}
	
	/**
	 * 更新
	 * @param bean
	 * @return
	 */
	private WalletCashOut updateWalletCashOut(WalletCashOut bean) {
		Updater<WalletCashOut> updater = new Updater<WalletCashOut>(bean);
		return walletCashOutDao.updateByUpdater(updater);
	}
	


}

