package com.sunrun.rest.controller;
import com.sunrun.rest.dto.*;import com.sunrun.washer.manager.*;import com.sunrun.washer.entity.*;import com.sunrun.washer.model.*;
import com.jeecms.core.manager.*;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import com.jeecms.common.page.Pagination;
import com.jeecms.common.page.SimplePage;

import java.util.ArrayList;
import java.util.List;

/**
 * 文 件 名 : ModeController.java
 * 创 建 人： 金明明
 * 日 期：2017-8-6
 * 修 改 人： 
 * 日 期： 
 * 描 述：洗衣模式 Controller层
 */
@Controller
public class ModeController extends BaseController{

	@Autowired
	private ModeMng modeMng;
	@Autowired
	private CmsUserMng cmsUserMng;

	/**
	 * 查询洗衣模式管理列表（不分页）
	 * @param userId 用户Id
	 * @param modeModel 洗衣模式管理查询条件
	 * @return
	 */
	@RequestMapping("/mode/queryModeListByModel.json")
	@ResponseBody
	public ModeQueryListDTO queryModeListByModel(ModeModel modeModel, HttpServletRequest request) {
		ModeQueryListDTO modeQueryListDTO = new ModeQueryListDTO();
		if (validateQueryModeByModel(modeQueryListDTO, getUserId(), modeModel)) {
			// 代码：设置默认相关值
			List<Mode> modes = (List<Mode>) modeMng.queryModeListByModel(modeModel);

			// 赋值洗衣模式管理必要信息信息
			List<ModeDTO> modeDTOs = new ArrayList<ModeDTO>(); 
			for (Mode mode : modes) {
				ModeDTO modeDTO = new ModeDTO(mode);
				modeDTOs.add(modeDTO);
			}
			modeQueryListDTO.setModeDTOs(modeDTOs);
			modeQueryListDTO.setState(BaseDTO.BaseDTOEnum.API_STATUS_SUCCESS);
			
		}
		return modeQueryListDTO;
	}

	/**
	 * 校验查询洗衣模式管理列表接口
	 * @param baseDTO
	 * @param userId 用户id
	 * @param modeModel 洗衣模式管理查询条件
	 * @return
	 */
	private Boolean validateQueryModeByModel(BaseDTO baseDTO, Integer userId, ModeModel modeModel) {
		if (cmsUserMng.findById(userId)  == null) {
			baseDTO.setState(BaseDTO.BaseDTOEnum.API_MESSAGE_USER_NOT_FOUND);
			return false;			
		}
		return true;
	}

}

