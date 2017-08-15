package com.sunrun.washer.dao.impl;
import com.sunrun.washer.dao.*;import com.sunrun.washer.model.*;import com.sunrun.washer.entity.*;
import java.util.List;
import org.springframework.stereotype.Repository;

import com.jeecms.common.hibernate4.Finder;
import com.jeecms.common.hibernate4.HibernateBaseDao;
import com.jeecms.common.page.Pagination;
/**
 * 文 件 名 : BankDaoImpl.java
 * 创 建 人： 金明明
 * 日 期：2017-8-15
 * 修 改 人： 
 * 日 期： 
 * 描 述：银行 DaoImpl
 */
@Repository
public class BankDaoImpl extends HibernateBaseDao<Bank, Integer> implements BankDao {

	@Override
	public List<Bank> queryBankByModel(BankModel bankModel) {
		Finder f = queryBankBaseFinder("select bean from Bank bean where 1=1 ");

		return find(f);
	}

	@Override
	public Bank save(Bank bank) {
		getSession().save(bank);
		return bank;
	}


	@Override
	public Bank deleteById(Integer bankId) {
		Bank entity = super.get(bankId);
		if (entity != null) {
			getSession().delete(entity);
		}
		return entity;
	}

	@Override
	public Bank findById(Integer bankId) {
		return get(bankId);
	}


	@Override
	protected Class<Bank> getEntityClass() {
		return Bank.class;
	}

	/**
	 * 创建银行基本查询语句
	 * @param baseHQL 必须为Bank类查询，且临时命名为bean 例："from Bank bean where 1=1"
	 * @return
	 */
	private Finder queryBankBaseFinder(String baseHQL) {
		Finder finder = Finder.create(baseHQL);
		// 此处增加公用的过滤条件，例如：每个银行要是有效的
		finder.append(" and bean.status <> 0");
		return finder;
	}

}

