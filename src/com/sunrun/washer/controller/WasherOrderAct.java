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
 * 文 件 名 : WasherOrderAct.java
 * 创 建 人： 金明明
 * 日 期：2017-8-7
 * 修 改 人： 
 * 日 期： 
 * 描 述：订单 Act层
 */
@Controller
public class WasherOrderAct {

	@Autowired
	private WasherOrderMng washerOrderMng;
	

	/**
	 * 查询订单管理列表
	 * @param washerOrderModel 订单管理查询条件
	 * @param pageNo 当前页
	 * @return
	 */
	@RequestMapping("/washerOrder/queryWasherOrderByModel.do")
	public String queryWasherOrderByModel(WasherOrderModel washerOrderModel, Integer pageNo, HttpServletRequest request, ModelMap model) {
		Pagination pagination = washerOrderMng.queryWasherOrderByModel(washerOrderModel, SimplePage.cpn(pageNo), CookieUtils.getPageSize(request));
		model.addAttribute("washerOrderModel", washerOrderModel);
		model.addAttribute("pagination", pagination);
		model.addAttribute("pageNo", pageNo);
		// 编写代码
		return "washerOrder/list";
	}

	/**
	 * 添加订单管理
	 * @param model
	 * @return
	 */
	@RequestMapping(value="/washerOrder/addWasherOrder.do")
	public String addWasherOrder(ModelMap model) {
		return "washerOrder/add";
	}
	
	/**
	 * 保存订单管理
	 * @param washerOrderModelSave
	 * @param request
	 * @param model
	 * @return
	 */
	@RequestMapping(value="/washerOrder/saveWasherOrder.do")
	public String saveWasherOrder(WasherOrderModelSave washerOrderModelSave, HttpServletRequest request, ModelMap model) {
		// 保存
		washerOrderMng.saveWasherOrder(washerOrderModelSave);
		return queryWasherOrderByModel(new WasherOrderModel(), 1, request, model);
	}

	/**
	 * 跳转至订单管理修改页
	 * @param washerOrderId 订单管理Id
	 * @param model 
	 * @return
	 */
	@RequestMapping(value = "/washerOrder/editWasherOrder.do")
	public String editWasherOrder(Integer washerOrderId, ModelMap model, HttpServletRequest request) {
		WasherOrder washerOrder = washerOrderMng.findById(washerOrderId);
		model.put("bean", washerOrder);
		return "washerOrder/edit";
	}
	
	/**
	 * 更新订单管理
	 * @param washerOrderModelUpdate 修改内容
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "/washerOrder/updateWasherOrder.do")
	public String updateWasherOrder(WasherOrderModelUpdate washerOrderModelUpdate, ModelMap model, HttpServletRequest request) {
		WasherOrder washerOrder = washerOrderMng.updateWasherOrder(washerOrderModelUpdate);
		return queryWasherOrderByModel(new WasherOrderModel(), 1, request, model);
	}

	/**
	 * 删除订单管理
	 * @param washerOrderId 订单管理Id
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "/washerOrder/deleteWasherOrder.do")
	public String deleteWasherOrder(Integer washerOrderId, ModelMap model, HttpServletRequest request) {
		WasherOrder washerOrder = washerOrderMng.deleteById(washerOrderId);
		return queryWasherOrderByModel(new WasherOrderModel(), 1, request, model);
	}

	/**
	 * 查询订单管理详情
	 * @param id 订单管理id
	 * @return
	 */
	@RequestMapping("/washerOrder/viewWasherOrder.do")
	public String queryWasherOrderByModel(Integer washerOrderId, HttpServletRequest request, ModelMap model) {
		WasherOrder washerOrder = washerOrderMng.findById(washerOrderId);
		model.addAttribute("bean", washerOrder);
		return "washerOrder/view";
	}


}

