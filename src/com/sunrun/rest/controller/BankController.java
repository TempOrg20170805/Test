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
 * 文 件 名 : BankController.java
 * 创 建 人： 金明明
 * 日 期：2017-8-15
 * 修 改 人： 
 * 日 期： 
 * 描 述：银行 Controller层
 */
@Controller
public class BankController extends BaseController{

	@Autowired
	private BankMng bankMng;
	@Autowired
	private CmsUserMng cmsUserMng;

	/**
	 * 查询银行管理列表（不分页）
	 * @param userId 用户Id
	 * @param bankModel 银行管理查询条件
	 * @return
	 */
	@RequestMapping("/bank/queryBankListByModel.json")
	@ResponseBody
	public BankQueryListDTO queryBankListByModel(BankModel bankModel, HttpServletRequest request) {
		BankQueryListDTO bankQueryListDTO = new BankQueryListDTO();
		if (validateQueryBankByModel(bankQueryListDTO, getUserId(), bankModel)) {
			// 代码：设置默认相关值
			List<Bank> banks = (List<Bank>) bankMng.queryBankByModel(bankModel);

			// 赋值银行管理必要信息信息
			List<BankDTO> bankDTOs = new ArrayList<BankDTO>(); 
			for (Bank bank : banks) {
				BankDTO bankDTO = new BankDTO(bank);
				bankDTOs.add(bankDTO);
			}
			bankQueryListDTO.setBankDTOs(bankDTOs);
			bankQueryListDTO.setState(BaseDTO.BaseDTOEnum.API_STATUS_SUCCESS);
			
		}
		return bankQueryListDTO;
	}


	/**
	 * 校验查询银行管理列表接口
	 * @param baseDTO
	 * @param userId 用户id
	 * @param bankModel 银行管理查询条件
	 * @return
	 */
	private Boolean validateQueryBankByModel(BaseDTO baseDTO, Integer userId, BankModel bankModel) {
		if (cmsUserMng.findById(userId)  == null) {
			baseDTO.setState(BaseDTO.BaseDTOEnum.API_MESSAGE_USER_NOT_FOUND);
			return false;			
		}
		return true;
	}

}