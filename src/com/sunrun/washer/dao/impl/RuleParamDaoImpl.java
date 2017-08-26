package com.sunrun.washer.dao.impl;
import com.sunrun.washer.dao.*;import com.sunrun.washer.model.*;import com.sunrun.washer.entity.*;

import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Repository;

import com.jeecms.common.hibernate4.Finder;
import com.jeecms.common.hibernate4.HibernateBaseDao;
import com.jeecms.common.page.Pagination;
/**
 * 文 件 名 : RuleParamDaoImpl.java
 * 创 建 人： 金明明
 * 日 期：2017-8-26
 * 修 改 人： 
 * 日 期： 
 * 描 述：规则参数 DaoImpl
 */
@Repository
public class RuleParamDaoImpl extends HibernateBaseDao<RuleParam, Integer> implements RuleParamDao {

	@Override
	public List<RuleParam> queryRuleParamListByModel(RuleParamModel ruleParamModel) {
		Finder f = queryRuleParamBaseFinder("select bean from RuleParam bean where 1=1 ");

		return find(f);
	}

	@Override
	public Pagination queryRuleParamByModel(RuleParamModel ruleParamModel, Integer pageNo, Integer pageSize) {
		Finder f = queryRuleParamBaseFinder("select bean from RuleParam bean where 1=1 ");

		if (StringUtils.isNotBlank(ruleParamModel.getRuleParamNo())) {
			f.append(" and bean.ruleParamNo =:ruleParamNo");
			f.setParam("ruleParamNo", ruleParamModel.getRuleParamNo());
		}

		return find(f, pageNo, pageSize);
	}

	@Override
	public RuleParam save(RuleParam ruleParam) {
		getSession().save(ruleParam);
		return ruleParam;
	}


	@Override
	public RuleParam deleteById(Integer ruleParamId) {
		RuleParam entity = super.get(ruleParamId);
		if (entity != null) {
			getSession().delete(entity);
		}
		return entity;
	}

	@Override
	public RuleParam findById(Integer ruleParamId) {
		return get(ruleParamId);
	}


	@Override
	protected Class<RuleParam> getEntityClass() {
		return RuleParam.class;
	}

	/**
	 * 创建规则参数基本查询语句
	 * @param baseHQL 必须为RuleParam类查询，且临时命名为bean 例："from RuleParam bean where 1=1"
	 * @return
	 */
	private Finder queryRuleParamBaseFinder(String baseHQL) {
		Finder finder = Finder.create(baseHQL);
		// 此处增加公用的过滤条件，例如：每个规则参数要是有效的
		return finder;
	}

	@Override
	public RuleParam findByRuleParamNo(String ruleParamNo) {
		return findUniqueByProperty("ruleParamNo", ruleParamNo);
	}

}

