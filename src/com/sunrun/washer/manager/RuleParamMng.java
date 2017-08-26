package com.sunrun.washer.manager;
import com.sunrun.washer.model.*;import com.sunrun.washer.entity.*;


import java.util.List;

import com.jeecms.common.page.Pagination;
/**
 * 文 件 名 : RuleParamMng.java
 * 创 建 人： 金明明
 * 日 期：2017-8-26
 * 修 改 人： 
 * 日 期： 
 * 描 述：规则参数 Mng层
 */
public interface RuleParamMng {

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
	 * @param ruleParamModelSave 保存的信息对象
	 * @return
	 */
	public RuleParam saveRuleParam(RuleParamModelSave ruleParamModelSave);

	/**
	 * 更新规则参数管理基本信息
	 * @param ruleParamModelUpdate 更新的信息
	 * @return
	 */
	public RuleParam updateRuleParam(RuleParamModelUpdate ruleParamModelUpdate);

	/**
     * 删除
     * @param id
     * @return
     */
	public RuleParam deleteById(Integer id);

	/**
	 * 根据id获取实体
	 * @param id
	 * @return
	 */
	public RuleParam findById(Integer id);

	/**
	 * 参数编号
	 * @param ruleParamNo
	 * @return
	 */
	public RuleParam findByRuleParamNo(String ruleParamNo);

}

