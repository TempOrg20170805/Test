package com.sunrun.washer.manager.impl;
import com.sunrun.washer.manager.*;import com.sunrun.washer.dao.*;import com.sunrun.washer.model.*;import com.sunrun.washer.entity.*;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.jeecms.common.hibernate4.Updater;
import com.jeecms.common.page.Pagination;
/**
 * 文 件 名 : RuleParamMngImpl.java
 * 创 建 人： 金明明
 * 日 期：2017-8-26
 * 修 改 人： 
 * 日 期： 
 * 描 述：规则参数 MngImpl
 */
@Service
@Transactional
public class RuleParamMngImpl implements RuleParamMng{

	@Autowired
	private RuleParamDao ruleParamDao;
	
	@Override
	public List<RuleParam> queryRuleParamListByModel(RuleParamModel ruleParamModel) {
		return ruleParamDao.queryRuleParamListByModel(ruleParamModel);
	}

	@Override
	public Pagination queryRuleParamByModel(RuleParamModel ruleParamModel, Integer pageNo, Integer pageSize) {
		Pagination pagination = ruleParamDao.queryRuleParamByModel(ruleParamModel, pageNo, pageSize);
		// 编写代码
		return pagination;
	}

	@Override
	public RuleParam saveRuleParam(RuleParamModelSave ruleParamModelSave) {
		RuleParam bean = new RuleParam();
		bean.setRuleParamNo(ruleParamModelSave.getRuleParamNo());
		bean.setDes(ruleParamModelSave.getDes());
		bean.setRuleParamValue(ruleParamModelSave.getRuleParamValue());
		// 赋值保存的必要信息
		return ruleParamDao.save(bean);
	}

	@Override
	public RuleParam updateRuleParam(RuleParamModelUpdate ruleParamModelUpdate) {
		RuleParam ruleParam = findById(ruleParamModelUpdate.getRuleParamId());
		ruleParam.setDes(ruleParamModelUpdate.getDes());
		ruleParam.setRuleParamValue(ruleParamModelUpdate.getRuleParamValue());
		return updateRuleParam(ruleParam);
	}
	/**
	 * 更新
	 * @param bean
	 * @return
	 */
	private RuleParam updateRuleParam(RuleParam bean) {
		Updater<RuleParam> updater = new Updater<RuleParam>(bean);
		return ruleParamDao.updateByUpdater(updater);
	}

	@Override
	public RuleParam deleteById(Integer id) {
		RuleParam bean = ruleParamDao.deleteById(id);
		return bean;
	}

	@Override
	public RuleParam findById(Integer id) {
		return ruleParamDao.findById(id);
	}

	@Override
	public RuleParam findByRuleParamNo(String ruleParamNo) {
		return ruleParamDao.findByRuleParamNo(ruleParamNo);
	}


}

