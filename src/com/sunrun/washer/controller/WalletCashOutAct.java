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
 * 文 件 名 : WalletCashOutAct.java
 * 创 建 人： 金明明
 * 日 期：2017-8-6
 * 修 改 人： 
 * 日 期： 
 * 描 述：提现管理 Act层
 */
@Controller
public class WalletCashOutAct {

	@Autowired
	private WalletCashOutMng walletCashOutMng;

	/**
	 * 查询提现列表
	 * @param walletCashOutModel 提现查询条件
	 * @param pageNo 当前页
	 * @return
	 */
	@RequestMapping("/walletCashOut/queryWalletCashOutByModel.do")
	public String queryWalletCashOutByModel(WalletCashOutModel walletCashOutModel, Integer pageNo, HttpServletRequest request, ModelMap model) {
		Pagination pagination = walletCashOutMng.queryWalletCashOutByModel(walletCashOutModel, SimplePage.cpn(pageNo), CookieUtils.getPageSize(request));
		model.addAttribute("walletCashOutModel", walletCashOutModel);
		model.addAttribute("pagination", pagination);
		model.addAttribute("pageNo", pageNo);
		// 编写代码
		return "walletCashOut/list";
	}
	
	/**
	 * 查询提现详情
	 * @param id 提现id
	 * @return
	 */
	@RequestMapping("/walletCashOut/viewWalletCashOut.do")
	public String queryWalletCashOutByModel(Integer walletCashOutId, HttpServletRequest request, ModelMap model) {
		WalletCashOut walletCashOut = walletCashOutMng.findById(walletCashOutId);
		model.addAttribute("bean", walletCashOut);
		return "walletCashOut/view";
	}
	
	
	/**
	 * 跳转至提现修改页
	 * @param walletCashOutId 提现Id
	 * @param model 
	 * @return
	 */
	@RequestMapping(value = "/walletCashOut/editWalletCashOut.do")
	public String editWalletCashOut(Integer walletCashOutId, ModelMap model, HttpServletRequest request) {
		WalletCashOut walletCashOut = walletCashOutMng.findById(walletCashOutId);
		model.put("bean", walletCashOut);
		return "walletCashOut/edit";
	}
	
	/**
	 * 更新提现
	 * @param walletCashOutModelUpdate 修改内容
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "/walletCashOut/updateWalletCashOut.do")
	public String updateWalletCashOut(WalletCashOutModelUpdate walletCashOutModelUpdate, ModelMap model, HttpServletRequest request) {
		WalletCashOut walletCashOut = walletCashOutMng.updateWalletCashOut(walletCashOutModelUpdate);
		return queryWalletCashOutByModel(new WalletCashOutModel(), 1, request, model);
	}

}

