package com.sunrun.washer.dao;
import java.util.List;

import com.jeecms.common.hibernate4.Updater;
import com.sunrun.washer.entity.WalletCard;
import com.sunrun.washer.model.WalletCardModel;
/**
 * 文 件 名 : WalletCardDao.java
 * 创 建 人： 金明明
 * 日 期：2017-8-6
 * 修 改 人： 
 * 日 期： 
 * 描 述：银行卡/支付宝管理 Dao层
 */
public interface WalletCardDao {
	/**
	 * 查询银行卡/支付宝列表(不分页)
	 * @param walletCardModel 银行卡/支付宝查询条件
	 * @return
	 */
	public List<WalletCard> queryWalletCardListByModel(WalletCardModel walletCardModel);

	public WalletCard updateByUpdater(Updater<WalletCard> updater);
	
	/**
	 * 保存银行卡/支付宝
	 * @param 银行卡/支付宝
	 * @return
	 */
	public WalletCard save(WalletCard walletCard);
	
    /**
     * 根据Id获取实体
     * @param 银行卡/支付宝Id
     * @return
     */
	public WalletCard findById(Integer walletCardId);
	
	/**
     * 删除银行卡/支付宝
     * @param 银行卡/支付宝Id
     * @return
     */
	public WalletCard deleteById(Integer walletCardId);
}

