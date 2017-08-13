package com.sunrun.rest.controller;
import antlr.debug.TraceAdapter;

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
 * 文 件 名 : TroubleController.java
 * 创 建 人： 金明明
 * 日 期：2017-8-13
 * 修 改 人： 
 * 日 期： 
 * 描 述：故障 Controller层
 */
@Controller
public class TroubleController extends BaseController{

	@Autowired
	private TroubleMng troubleMng;
	@Autowired
	private CmsUserMng cmsUserMng;
	@Autowired
	private MachineMng machineMng;
	

	/**
	 * 查询故障列表
	 * @param userId 用户Id
	 * @param troubleModel 故障管理查询条件
	 * @param pageNo 当前页
	 * @param pageSize 每页数据量
	 * @return
	 */
	@RequestMapping("/trouble/queryTroubleByModel.json")
	@ResponseBody
	public TroubleQueryDTO queryTroubleByModel(TroubleModel troubleModel, Integer pageNo, Integer pageSize, HttpServletRequest request) {
		TroubleQueryDTO troubleQueryDTO = new TroubleQueryDTO();
		if (validateQueryTroubleByModel(troubleQueryDTO, getUserId(), troubleModel)) {
			// 代码：设置默认相关值
			Pagination pagination = troubleMng.queryTroubleByModel(troubleModel, SimplePage.cpn(pageNo), pageSize);
			List<Trouble> troubles = (List<Trouble>) pagination.getList();
					
			// 赋值故障管理分页信息
			troubleQueryDTO.setPageNo(pagination.getPageNo());
			troubleQueryDTO.setPageSize(pagination.getPageSize());
			troubleQueryDTO.setTotalCount(pagination.getTotalCount());

			// 赋值故障管理必要信息信息
			List<TroubleDTO> troubleDTOs = new ArrayList<TroubleDTO>(); 
			for (Trouble trouble : troubles) {
				TroubleDTO troubleDTO = new TroubleDTO(trouble);
				// 赋值商品列表
				troubleDTOs.add(troubleDTO);
			}
			troubleQueryDTO.setTroubleDTOs(troubleDTOs);
			troubleQueryDTO.setState(BaseDTO.BaseDTOEnum.API_STATUS_SUCCESS);
			
		}
		return troubleQueryDTO;
	}

	/**
	 * 添加故障
	 * @param machineId 洗衣机ID
	 * @param troubleReason 故障原因
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "/trouble/saveTrouble.json")
	@ResponseBody
	public TroubleSaveDTO saveTrouble(Integer machineId, String troubleReason, HttpServletRequest request){
		TroubleSaveDTO troubleSaveDTO = new TroubleSaveDTO();
		if(validateSaveTrouble(troubleSaveDTO,getUserId(),machineId,troubleReason)){
			TroubleModelSave troubleModelSave = new TroubleModelSave();
			troubleModelSave.setUserId(getUserId());
			troubleModelSave.setMachineId(machineId);
			troubleModelSave.setTroubleReason(troubleReason);
			troubleMng.saveTrouble(troubleModelSave);
			troubleSaveDTO.setState(BaseDTO.BaseDTOEnum.API_STATUS_SUCCESS);
		}
		return troubleSaveDTO;
	}
	
	/**
	 * 校验查询故障管理保存接口
	 * @param baseDTO
	 * @param userId 用户id
	 * @return
	 */
	private Boolean validateSaveTrouble(BaseDTO baseDTO, Integer userId, Integer machineId, String troubleReason) {
		if (cmsUserMng.findById(userId)  == null) {
			baseDTO.setState(BaseDTO.BaseDTOEnum.API_MESSAGE_USER_NOT_FOUND);
			return false;			
		}
		if (machineMng.findById(machineId) == null) {
			baseDTO.setState(BaseDTO.BaseDTOEnum.API_MESSAGE_VALIDATECODE_NOTEXIST);
			return false;
		}
		return true;
	}

	/**
	 * 校验查询故障管理列表接口
	 * @param baseDTO
	 * @param userId 用户id
	 * @param troubleModel 故障管理查询条件
	 * @return
	 */
	private Boolean validateQueryTroubleByModel(BaseDTO baseDTO, Integer userId, TroubleModel troubleModel) {
		if (cmsUserMng.findById(userId)  == null) {
			baseDTO.setState(BaseDTO.BaseDTOEnum.API_MESSAGE_USER_NOT_FOUND);
			return false;			
		}
		return true;
	}

}

