package com.sunrun.washer.dao;
import com.sunrun.washer.model.*;import com.sunrun.washer.entity.*;


import java.util.List;

import com.jeecms.common.page.Pagination;
import com.jeecms.common.hibernate4.Updater;
/**
 * 文 件 名 : BankDao.java
 * 创 建 人： 金明明
 * 日 期：2017-8-15
 * 修 改 人： 
 * 日 期： 
 * 描 述：银行 Dao层
 */
public interface BankDao {

	/**
	 * 查询银行管理列表
	 * @param bankModel 银行管理查询条件
	 * @return
	 */
	public List<Bank> queryBankByModel(BankModel bankModel);

	/**
	 * 保存银行管理
	 * @param 银行管理
	 * @return
	 */
	public Bank save(Bank bank);

	public Bank updateByUpdater(Updater<Bank> updater);

	/**
     * 删除银行管理
     * @param 银行管理Id
     * @return
     */
	public Bank deleteById(Integer bankId);

    /**
     * 根据Id获取实体
     * @param 银行管理Id
     * @return
     */
	public Bank findById(Integer bankId);


}

