package com.sunrun.washer.manager;
import com.sunrun.washer.model.*;import com.sunrun.washer.entity.*;


import java.util.List;

import com.jeecms.common.page.Pagination;
/**
 * 文 件 名 : WalletCashOutMng.java
 * 创 建 人： 金明明
 * 日 期：2017-8-6
 * 修 改 人： 
 * 日 期： 
 * 描 述：提现管理 Mng层
 */
public interface WalletCashOutMng {
	/**
	 * 查询提现列表
	 * @param walletCashOutModel 提现查询条件
	 * @param pageNo 当前页
	 * @param pageSize 每页数据量
	 * @return
	 */
	public Pagination queryWalletCashOutByModel(WalletCashOutModel walletCashOutModel, Integer pageNo, Integer pageSize);
	
	/**
	 * 保存提现
	 * @param walletCashOutModelSave 保存的信息对象
	 * @return
	 */
	public WalletCashOut saveWalletCashOut(WalletCashOutModelSave walletCashOutModelSave);

	/**
	 * 更新提现基本信息
	 * @param walletCashOutModelUpdate 更新的信息
	 * @return
	 */
	public WalletCashOut updateWalletCashOut(WalletCashOutModelUpdate walletCashOutModelUpdate);
	
	/**
	 * 根据id获取实体
	 * @param id
	 * @return
	 */
	public WalletCashOut findById(Integer id);

}

