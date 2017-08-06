package com.sunrun.washer.manager;
import com.sunrun.washer.model.*;import com.sunrun.washer.entity.*;


import java.util.List;

import com.jeecms.common.page.Pagination;
/**
 * 文 件 名 : WalletCardMng.java
 * 创 建 人： 金明明
 * 日 期：2017-8-6
 * 修 改 人： 
 * 日 期： 
 * 描 述：银行卡/支付宝管理 Mng层
 */
public interface WalletCardMng {	
	/**
	 * 查询银行卡/支付宝列表(不分页)
	 * @param walletCardModel 银行卡/支付宝查询条件
	 * @return
	 */
	public List<WalletCard> queryWalletCardListByModel(WalletCardModel walletCardModel);
	

	/**
	 * 保存银行卡/支付宝
	 * @param walletCardModelSave 保存的信息对象
	 * @return
	 */
	public WalletCard saveWalletCard(WalletCardModelSave walletCardModelSave);

	/**
     * 删除
     * @param id
     * @return
     */
	public WalletCard deleteById(Integer id);
	
	/**
	 * 根据id获取实体
	 * @param id
	 * @return
	 */
	public WalletCard findById(Integer id);

}

