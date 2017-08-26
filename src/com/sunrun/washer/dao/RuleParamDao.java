package com.sunrun.washer.dao;
import com.sunrun.washer.model.*;import com.sunrun.washer.entity.*;


import java.util.List;

import com.jeecms.common.page.Pagination;
import com.jeecms.common.hibernate4.Updater;
/**
 * 文 件 名 : RuleParamDao.java
 * 创 建 人： 金明明
 * 日 期：2017-8-26
 * 修 改 人： 
 * 日 期： 
 * 描 述：规则参数 Dao层
 */
public interface RuleParamDao {

	/**
	 * 查询规则参数管理列表(不分页)
	 * @param ruleParamModel 规则参数管理查询条件
	 * @return
	 */
	public List<RuleParam> queryRuleParamListByModel(RuleParamModel ruleParamModel);

	/**
	 * 查询规则参数管理列表
	 * @param ruleParamModel 规则参数管理查询条件
	 * @param pageNo 当前页
	 * @param pageSize 每页数据量
	 * @return
	 */
	public Pagination queryRuleParamByModel(RuleParamModel ruleParamModel, Integer pageNo, Integer pageSize);

	/**
	 * 保存规则参数管理
	 * @param 规则参数管理
	 * @return
	 */
	public RuleParam save(RuleParam ruleParam);

	public RuleParam updateByUpdater(Updater<RuleParam> updater);

	/**
     * 删除规则参数管理
     * @param 规则参数管理Id
     * @return
     */
	public RuleParam deleteById(Integer ruleParamId);

    /**
     * 根据Id获取实体
     * @param 规则参数管理Id
     * @return
     */
	public RuleParam findById(Integer ruleParamId);
	
	/**
	 * 参数编号
	 * @param ruleParamNo
	 * @return
	 */
	public RuleParam findByRuleParamNo(String ruleParamNo);


}

