package com.sunrun.washer.manager;
import com.sunrun.washer.model.*;import com.sunrun.washer.entity.*;


import java.util.List;

import com.jeecms.common.page.Pagination;
/**
 * 文 件 名 : BankMng.java
 * 创 建 人： 金明明
 * 日 期：2017-8-15
 * 修 改 人： 
 * 日 期： 
 * 描 述：银行 Mng层
 */
public interface BankMng {

	/**
	 * 查询银行管理列表
	 * @param bankModel 银行管理查询条件
	 * @return
	 */
	public List<Bank> queryBankByModel(BankModel bankModel);

	/**
	 * 保存银行管理
	 * @param bankModelSave 保存的信息对象
	 * @return
	 */
	public Bank saveBank(BankModelSave bankModelSave);

	/**
	 * 更新银行管理基本信息
	 * @param bankModelUpdate 更新的信息
	 * @return
	 */
	public Bank updateBank(BankModelUpdate bankModelUpdate);

	/**
     * 删除
     * @param id
     * @return
     */
	public Bank deleteById(Integer id);

	/**
	 * 根据id获取实体
	 * @param id
	 * @return
	 */
	public Bank findById(Integer id);


}

