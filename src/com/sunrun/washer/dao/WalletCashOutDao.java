package com.sunrun.washer.dao;
import com.jeecms.common.hibernate4.Updater;
import com.jeecms.common.page.Pagination;
import com.sunrun.washer.entity.WalletCashOut;
import com.sunrun.washer.model.WalletCashOutModel;
/**
 * 文 件 名 : WalletCashOutDao.java
 * 创 建 人： 金明明
 * 日 期：2017-8-6
 * 修 改 人： 
 * 日 期： 
 * 描 述：提现管理 Dao层
 */
public interface WalletCashOutDao {
	/**
	 * 查询提现列表
	 * @param walletCashOutModel 提现查询条件
	 * @param pageNo 当前页
	 * @param pageSize 每页数据量
	 * @return
	 */
	public Pagination queryWalletCashOutByModel(WalletCashOutModel walletCashOutModel, Integer pageNo, Integer pageSize);

	public WalletCashOut updateByUpdater(Updater<WalletCashOut> updater);
	
	/**
	 * 保存提现
	 * @param 提现
	 * @return
	 */
	public WalletCashOut save(WalletCashOut walletCashOut);
	
    /**
     * 根据Id获取实体
     * @param 提现Id
     * @return
     */
	public WalletCashOut findById(Integer walletCashOutId);
}

