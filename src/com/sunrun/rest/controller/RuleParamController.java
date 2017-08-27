package com.sunrun.rest.controller;
import com.sunrun.rest.dto.*;import com.sunrun.washer.manager.*;import com.sunrun.washer.entity.*;import com.sunrun.washer.enums.RuleParamNoEnum;
import com.sunrun.washer.model.*;
import com.jeecms.core.manager.*;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import com.jeecms.common.page.Pagination;
import com.jeecms.common.page.SimplePage;

import java.util.ArrayList;
import java.util.List;

/**
 * 文 件 名 : RuleParamController.java
 * 创 建 人： 金明明
 * 日 期：2017-8-26
 * 修 改 人： 
 * 日 期： 
 * 描 述：规则参数 Controller层
 */
@Controller
public class RuleParamController extends BaseController{

	@Autowired
	private RuleParamMng ruleParamMng;
	@Autowired
	private CmsUserMng cmsUserMng;

	/**
	 * 规则参数管理详情
	 * @param userId 
	 * @param ruleParamNo 规则编号
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "/ruleParam/detailRuleParam.json")
	@ResponseBody
	public RuleParamDetailDTO detailRuleParam(String ruleParamNo, HttpServletRequest request){
		RuleParamDetailDTO ruleParamDetailDTO = new RuleParamDetailDTO();
		if(validateDetailRuleParam(ruleParamDetailDTO, getUserId())){
			RuleParam ruleParam = null;
			if (StringUtils.isNotBlank(ruleParamNo)) {
				ruleParam = ruleParamMng.findByRuleParamNo(ruleParamNo);
			}
			
			if (ruleParam != null) {
				ruleParamDetailDTO.setRuleParamValue(ruleParam.getRuleParamValue());
				ruleParamDetailDTO.setDes(ruleParam.getDes());
			} else {
				ruleParamDetailDTO.setRuleParamValue(0);
			}
			
			ruleParamDetailDTO.setState(BaseDTO.BaseDTOEnum.API_STATUS_SUCCESS);
		}
		return ruleParamDetailDTO;
	}
		
	/**
	 * 校验规则参数管理详情接口
	 * @param baseDTO
	 * @param userId 用户id
	 * @param ruleParamId 规则参数管理Id
	 * @return
	 */
	private Boolean validateDetailRuleParam(BaseDTO baseDTO, Integer userId) {
		if (cmsUserMng.findById(userId)  == null) {
			baseDTO.setState(BaseDTO.BaseDTOEnum.API_MESSAGE_USER_NOT_FOUND);
			return false;			
		}
		return true;
	}


}

