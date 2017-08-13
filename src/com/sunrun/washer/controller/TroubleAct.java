package com.sunrun.washer.controller;
import com.sunrun.washer.manager.*;import com.sunrun.washer.model.*;import com.sunrun.washer.entity.*;
import com.sunrun.washer.enums.TroubleStatusEnum;


import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import com.jeecms.common.page.Pagination;
import com.jeecms.common.page.SimplePage;
import com.jeecms.common.web.CookieUtils;
/**
 * 文 件 名 : TroubleAct.java
 * 创 建 人： 金明明
 * 日 期：2017-8-13
 * 修 改 人： 
 * 日 期： 
 * 描 述：故障 Act层
 */
@Controller
public class TroubleAct {

	@Autowired
	private TroubleMng troubleMng;
	
	/**
	 * 查询故障管理列表
	 * @param troubleModel 故障管理查询条件
	 * @param pageNo 当前页
	 * @return
	 */
	@RequestMapping("/trouble/queryTroubleByModel.do")
	public String queryTroubleByModel(TroubleModel troubleModel, Integer pageNo, HttpServletRequest request, ModelMap model) {
		Pagination pagination = troubleMng.queryTroubleByModel(troubleModel, SimplePage.cpn(pageNo), CookieUtils.getPageSize(request));
		model.addAttribute("troubleModel", troubleModel);
		model.addAttribute("pagination", pagination);
		model.addAttribute("pageNo", pageNo);
		// 编写代码
		return "trouble/list";
	}

	/**
	 * 添加故障管理
	 * @param model
	 * @return
	 */
	@RequestMapping(value="/trouble/addTrouble.do")
	public String addTrouble(ModelMap model) {
		return "trouble/add";
	}
	
	/**
	 * 保存故障管理
	 * @param troubleModelSave
	 * @param request
	 * @param model
	 * @return
	 */
	@RequestMapping(value="/trouble/saveTrouble.do")
	public String saveTrouble(TroubleModelSave troubleModelSave, HttpServletRequest request, ModelMap model) {
		// 保存
		troubleMng.saveTrouble(troubleModelSave);
		return queryTroubleByModel(new TroubleModel(), 1, request, model);
	}

	/**
	 * 跳转至故障管理修改页
	 * @param troubleId 故障管理Id
	 * @param model 
	 * @return
	 */
	@RequestMapping(value = "/trouble/editTrouble.do")
	public String editTrouble(Integer troubleId, ModelMap model, HttpServletRequest request) {
		Trouble trouble = troubleMng.findById(troubleId);
		model.put("bean", trouble);
		return "trouble/edit";
	}
	
	/**
	 * 更新故障管理
	 * @param troubleModelUpdate 修改内容
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "/trouble/updateTrouble.do")
	public String updateTrouble(TroubleModelUpdate troubleModelUpdate, ModelMap model, HttpServletRequest request) {
		Trouble trouble = null;
		if (TroubleStatusEnum.UNDER_REPAIR.getCode().equals(troubleModelUpdate.getStatus())) {
			trouble = troubleMng.startRepair(troubleModelUpdate.getTroubleId());
		}
		else if (TroubleStatusEnum.SUCCESS.getCode().equals(troubleModelUpdate.getStatus())) {
			trouble = troubleMng.endRepair(troubleModelUpdate.getTroubleId());
		}
		return queryTroubleByModel(new TroubleModel(), 1, request, model);
	}

	/**
	 * 删除故障管理
	 * @param troubleId 故障管理Id
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "/trouble/deleteTrouble.do")
	public String deleteTrouble(Integer troubleId, ModelMap model, HttpServletRequest request) {
		Trouble trouble = troubleMng.deleteById(troubleId);
		return queryTroubleByModel(new TroubleModel(), 1, request, model);
	}

	/**
	 * 查询故障管理详情
	 * @param id 故障管理id
	 * @return
	 */
	@RequestMapping("/trouble/viewTrouble.do")
	public String queryTroubleByModel(Integer troubleId, HttpServletRequest request, ModelMap model) {
		Trouble trouble = troubleMng.findById(troubleId);
		model.addAttribute("bean", trouble);
		return "trouble/view";
	}


}

