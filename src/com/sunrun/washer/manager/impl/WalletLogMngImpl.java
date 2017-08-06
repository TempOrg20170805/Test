package com.sunrun.washer.manager.impl;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.jeecms.common.page.Pagination;
import com.jeecms.core.entity.CmsUser;
import com.jeecms.core.manager.CmsUserMng;
import com.sunrun.washer.dao.WalletLogDao;
import com.sunrun.washer.entity.WalletLog;
import com.sunrun.washer.manager.WalletLogMng;
import com.sunrun.washer.model.WalletLogModel;
import com.sunrun.washer.model.WalletLogModelSave;
/**
 * 文 件 名 : 消费日志管理
 * 创 建 人： 金明明
 * 日 期：2017-8-6
 * 修 改 人： 
 * 日 期： 
 * 描 述：消费日志管理 MngImpl
 */
@Service
@Transactional
public class WalletLogMngImpl implements WalletLogMng{

	@Autowired
	private WalletLogDao walletLogDao;
	@Autowired
	private CmsUserMng cmsUserMng;

	@Override
	public Pagination queryWalletLogByModel(WalletLogModel walletLogModel, Integer pageNo, Integer pageSize) {
		Pagination pagination = walletLogDao.queryWalletLogByModel(walletLogModel, pageNo, pageSize);
		// 编写代码
		return pagination;
	}
	
	@Override
	public List<WalletLog> queryWalletLogListByModel(WalletLogModel walletLogModel) {
		return walletLogDao.queryWalletLogListByModel(walletLogModel);
	}
	
	@Override
	public WalletLog saveWalletLog(WalletLogModelSave walletLogModelSave) {
		WalletLog bean = new WalletLog();
		
		CmsUser jcUser = cmsUserMng.findById(walletLogModelSave.getUserId());
		bean.setJcUser(jcUser);
		bean.setMoney(walletLogModelSave.getMoney());
		bean.setMsg(walletLogModelSave.getMsg());
		bean.setType(walletLogModelSave.getType());
		bean.setPayPlatform(walletLogModelSave.getPayPlatform());
		bean.setMoneyBefore(walletLogModelSave.getMoneyBefore());
		bean.setMoneyAfter(walletLogModelSave.getMoneyAfter());
		// 赋值保存的必要信息
		return walletLogDao.save(bean);
	}

	
	
	
	@Override
	public WalletLog findById(Integer id) {
		return walletLogDao.findById(id);
	}
	


}

