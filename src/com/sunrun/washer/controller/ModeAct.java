package com.sunrun.washer.controller;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

import com.sunrun.washer.entity.Mode;
import com.sunrun.washer.manager.ModeMng;
import com.sunrun.washer.model.ModeModel;
import com.sunrun.washer.model.ModeModelSave;
import com.sunrun.washer.model.ModeModelUpdate;
/**
 * 文 件 名 : ModeAct.java
 * 创 建 人： 金明明
 * 日 期：2017-8-6
 * 修 改 人： 
 * 日 期： 
 * 描 述：洗衣模式 Act层
 */
@Controller
public class ModeAct {

	@Autowired
	private ModeMng modeMng;
	

	/**
	 * 查询洗衣模式管理列表
	 * @param modeModel 洗衣模式管理查询条件
	 * @param pageNo 当前页
	 * @return
	 */
	@RequestMapping("/mode/queryModeByModel.do")
	public String queryModeByModel(ModeModel modeModel, Integer pageNo, HttpServletRequest request, ModelMap model) {
		List<Mode> modes = modeMng.queryModeListByModel(modeModel);
		model.addAttribute("modeModel", modeModel);
		model.addAttribute("list", modes);
		// 编写代码
		return "mode/list";
	}

	/**
	 * 添加洗衣模式管理
	 * @param model
	 * @return
	 */
	@RequestMapping(value="/mode/addMode.do")
	public String addMode(ModelMap model) {
		return "mode/add";
	}
	
	/**
	 * 保存洗衣模式管理
	 * @param modeModelSave
	 * @param request
	 * @param model
	 * @return
	 */
	@RequestMapping(value="/mode/saveMode.do")
	public String saveMode(ModeModelSave modeModelSave, HttpServletRequest request, ModelMap model) {
		// 保存
		modeMng.saveMode(modeModelSave);
		return queryModeByModel(new ModeModel(), 1, request, model);
	}

	/**
	 * 跳转至洗衣模式管理修改页
	 * @param modeId 洗衣模式管理Id
	 * @param model 
	 * @return
	 */
	@RequestMapping(value = "/mode/editMode.do")
	public String editMode(Integer modeId, ModelMap model, HttpServletRequest request) {
		Mode mode = modeMng.findById(modeId);
		model.put("bean", mode);
		return "mode/edit";
	}
	
	/**
	 * 更新洗衣模式管理
	 * @param modeModelUpdate 修改内容
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "/mode/updateMode.do")
	public String updateMode(ModeModelUpdate modeModelUpdate, ModelMap model, HttpServletRequest request) {
		Mode mode = modeMng.updateMode(modeModelUpdate);
		return queryModeByModel(new ModeModel(), 1, request, model);
	}

	/**
	 * 删除洗衣模式管理
	 * @param modeId 洗衣模式管理Id
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "/mode/deleteMode.do")
	public String deleteMode(Integer modeId, ModelMap model, HttpServletRequest request) {
		Mode mode = modeMng.deleteById(modeId);
		return queryModeByModel(new ModeModel(), 1, request, model);
	}

	/**
	 * 查询洗衣模式管理详情
	 * @param id 洗衣模式管理id
	 * @return
	 */
	@RequestMapping("/mode/viewMode.do")
	public String queryModeByModel(Integer modeId, HttpServletRequest request, ModelMap model) {
		Mode mode = modeMng.findById(modeId);
		model.addAttribute("bean", mode);
		return "mode/view";
	}


}

