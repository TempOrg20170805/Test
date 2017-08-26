package com.sunrun.washer.controller;
import com.sunrun.washer.manager.*;import com.sunrun.washer.model.*;import com.sunrun.washer.entity.*;


import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import com.jeecms.common.page.Pagination;
import com.jeecms.common.page.SimplePage;
import com.jeecms.common.web.CookieUtils;
/**
 * 文 件 名 : RuleParamAct.java
 * 创 建 人： 金明明
 * 日 期：2017-8-26
 * 修 改 人： 
 * 日 期： 
 * 描 述：规则参数 Act层
 */
@Controller
public class RuleParamAct {

	@Autowired
	private RuleParamMng ruleParamMng;
	

	/**
	 * 查询规则参数管理列表
	 * @param ruleParamModel 规则参数管理查询条件
	 * @param pageNo 当前页
	 * @return
	 */
	@RequestMapping("/ruleParam/queryRuleParamByModel.do")
	public String queryRuleParamByModel(RuleParamModel ruleParamModel, Integer pageNo, HttpServletRequest request, ModelMap model) {
		Pagination pagination = ruleParamMng.queryRuleParamByModel(ruleParamModel, SimplePage.cpn(pageNo), CookieUtils.getPageSize(request));
		model.addAttribute("ruleParamModel", ruleParamModel);
		model.addAttribute("pagination", pagination);
		model.addAttribute("pageNo", pageNo);
		// 编写代码
		return "ruleParam/list";
	}

	/**
	 * 添加规则参数管理
	 * @param model
	 * @return
	 */
	@RequestMapping(value="/ruleParam/addRuleParam.do")
	public String addRuleParam(ModelMap model) {
		return "ruleParam/add";
	}
	
	/**
	 * 保存规则参数管理
	 * @param ruleParamModelSave
	 * @param request
	 * @param model
	 * @return
	 */
	@RequestMapping(value="/ruleParam/saveRuleParam.do")
	public String saveRuleParam(RuleParamModelSave ruleParamModelSave, HttpServletRequest request, ModelMap model) {
		// 保存
		ruleParamMng.saveRuleParam(ruleParamModelSave);
		return queryRuleParamByModel(new RuleParamModel(), 1, request, model);
	}

	/**
	 * 跳转至规则参数管理修改页
	 * @param ruleParamId 规则参数管理Id
	 * @param model 
	 * @return
	 */
	@RequestMapping(value = "/ruleParam/editRuleParam.do")
	public String editRuleParam(Integer ruleParamId, ModelMap model, HttpServletRequest request) {
		RuleParam ruleParam = ruleParamMng.findById(ruleParamId);
		model.put("bean", ruleParam);
		return "ruleParam/edit";
	}
	
	/**
	 * 更新规则参数管理
	 * @param ruleParamModelUpdate 修改内容
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "/ruleParam/updateRuleParam.do")
	public String updateRuleParam(RuleParamModelUpdate ruleParamModelUpdate, ModelMap model, HttpServletRequest request) {
		RuleParam ruleParam = ruleParamMng.updateRuleParam(ruleParamModelUpdate);
		return queryRuleParamByModel(new RuleParamModel(), 1, request, model);
	}

	/**
	 * 删除规则参数管理
	 * @param ruleParamId 规则参数管理Id
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "/ruleParam/deleteRuleParam.do")
	public String deleteRuleParam(Integer ruleParamId, ModelMap model, HttpServletRequest request) {
		RuleParam ruleParam = ruleParamMng.deleteById(ruleParamId);
		return queryRuleParamByModel(new RuleParamModel(), 1, request, model);
	}

	/**
	 * 查询规则参数管理详情
	 * @param id 规则参数管理id
	 * @return
	 */
	@RequestMapping("/ruleParam/viewRuleParam.do")
	public String queryRuleParamByModel(Integer ruleParamId, HttpServletRequest request, ModelMap model) {
		RuleParam ruleParam = ruleParamMng.findById(ruleParamId);
		model.addAttribute("bean", ruleParam);
		return "ruleParam/view";
	}


}

