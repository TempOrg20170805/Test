package com.sunrun.washer.manager.impl;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.jeecms.common.hibernate4.Updater;
import com.sunrun.washer.dao.BankDao;
import com.sunrun.washer.entity.Bank;
import com.sunrun.washer.manager.BankMng;
import com.sunrun.washer.model.BankModel;
import com.sunrun.washer.model.BankModelSave;
import com.sunrun.washer.model.BankModelUpdate;
/**
 * 文 件 名 : BankMngImpl.java
 * 创 建 人： 金明明
 * 日 期：2017-8-15
 * 修 改 人： 
 * 日 期： 
 * 描 述：银行 MngImpl
 */
@Service
@Transactional
public class BankMngImpl implements BankMng{

	@Autowired
	private BankDao bankDao;
	
	@Override
	public List<Bank> queryBankByModel(BankModel bankModel) {
		return bankDao.queryBankByModel(bankModel);
	}

	@Override
	public Bank saveBank(BankModelSave bankModelSave) {
		Bank bean = new Bank();
		// 赋值保存的必要信息
		return bankDao.save(bean);
	}

	@Override
	public Bank updateBank(BankModelUpdate bankModelUpdate) {
		return null;
	}
	/**
	 * 更新
	 * @param bean
	 * @return
	 */
	private Bank updateBank(Bank bean) {
		Updater<Bank> updater = new Updater<Bank>(bean);
		return bankDao.updateByUpdater(updater);
	}

	@Override
	public Bank deleteById(Integer id) {
		Bank bean = bankDao.deleteById(id);
		return bean;
	}

	@Override
	public Bank findById(Integer id) {
		return bankDao.findById(id);
	}


}

