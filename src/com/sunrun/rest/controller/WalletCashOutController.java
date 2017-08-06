package com.sunrun.rest.controller;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.jeecms.common.page.Pagination;
import com.jeecms.common.page.SimplePage;
import com.jeecms.core.entity.CmsUser;
import com.jeecms.core.manager.CmsUserMng;
import com.sunrun.rest.dto.BaseDTO;
import com.sunrun.rest.dto.BaseDTO.BaseDTOEnum;
import com.sunrun.rest.dto.WalletCashOutDTO;
import com.sunrun.rest.dto.WalletCashOutPageDTO;
import com.sunrun.rest.dto.WalletCashOutPageDTO.WalletCashOutPageDTOEnum;
import com.sunrun.rest.dto.WalletCashOutQueryDTO;
import com.sunrun.rest.dto.WalletCashOutSaveDTO;
import com.sunrun.rest.dto.WalletCashOutSaveDTO.WalletCashOutSaveDTOEnum;
import com.sunrun.washer.entity.WalletCashOut;
import com.sunrun.washer.manager.WalletCardMng;
import com.sunrun.washer.manager.WalletCashOutMng;
import com.sunrun.washer.model.WalletCashOutModel;
import com.sunrun.washer.model.WalletCashOutModelSave;

/**
 * 文 件 名 : WalletCashOutController.java
 * 创 建 人： 金明明
 * 日 期：2017-8-6
 * 修 改 人： 
 * 日 期： 
 * 描 述：提现管理 Controller层
 */
@Controller
public class WalletCashOutController extends BaseController {

	@Autowired
	private WalletCashOutMng walletCashOutMng;
	@Autowired
	private CmsUserMng cmsUserMng;
	@Autowired
	private WalletCardMng walletCardMng;
	
	/**
	 * 查询提现记录
	 * @param userId 用户Id
	 * @param walletCashOutModel 钱包消费日志查询条件
	 * @param pageNo 当前页
	 * @param pageSize 每页数据量
	 * @return
	 */
	@RequestMapping("/walletCashOut/queryWalletCashOutByModel.json")
	@ResponseBody
	public WalletCashOutQueryDTO queryWalletCashOutByModel(WalletCashOutModel walletCashOutModel, Integer pageNo, Integer pageSize, HttpServletRequest request) {
		WalletCashOutQueryDTO walletCashOutQueryDTO = new WalletCashOutQueryDTO();
		if (validateQueryWalletCashOutByModel(walletCashOutQueryDTO, getUserId(), walletCashOutModel)) {
			walletCashOutModel.setUserId(getUserId());
			// 代码：设置莫得了相关值
			Pagination pagination = walletCashOutMng.queryWalletCashOutByModel(walletCashOutModel, SimplePage.cpn(pageNo), pageSize);
			List<WalletCashOut> walletCashOuts = (List<WalletCashOut>) pagination.getList();
					
			// 赋值钱包消费日志分页信息
			walletCashOutQueryDTO.setPageNo(pagination.getPageNo());
			walletCashOutQueryDTO.setPageSize(pagination.getPageSize());
			walletCashOutQueryDTO.setTotalCount(pagination.getTotalCount());

			// 赋值钱包消费日志必要信息信息
			List<WalletCashOutDTO> walletCashOutDTOs = new ArrayList<WalletCashOutDTO>(); 
			for (WalletCashOut walletCashOut : walletCashOuts) {
				WalletCashOutDTO walletCashOutDTO = new WalletCashOutDTO(walletCashOut);
				
				// 赋值商品列表
				walletCashOutDTOs.add(walletCashOutDTO);
			}
			walletCashOutQueryDTO.setWalletCashOutDTOs(walletCashOutDTOs);
			walletCashOutQueryDTO.setState(BaseDTO.BaseDTOEnum.API_STATUS_SUCCESS);
			
		}
		return walletCashOutQueryDTO;
	}
	
	/**
	 * 校验查询提现记录列表接口
	 * @param baseDTO
	 * @param userId 用户id
	 * @param walletCashOutModel 钱包消费日志查询条件
	 * @return
	 */
	private Boolean validateQueryWalletCashOutByModel(BaseDTO baseDTO, Integer userId, WalletCashOutModel walletCashOutModel) {
		if (cmsUserMng.findById(userId)  == null) {
			baseDTO.setState(BaseDTO.BaseDTOEnum.API_MESSAGE_USER_NOT_FOUND);
			return false;			
		}
		return true;
	}
	
	/**
	 * 提现界面
	 * @param userId 用户id
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "/walletCashOut/addWalletCashOut.json")
	@ResponseBody
	public WalletCashOutPageDTO addWalletCashOut(HttpServletRequest request) {
		WalletCashOutPageDTO walletCashOutPageDTO = new WalletCashOutPageDTO();
		if (validateAddWalletCashOut(walletCashOutPageDTO, getUserId())) {
			CmsUser cmsUser = cmsUserMng.findById(getUserId());
			walletCashOutPageDTO.setMoney(cmsUser.getMoney());
			walletCashOutPageDTO.setState(BaseDTOEnum.API_STATUS_SUCCESS);
		}
		
		return walletCashOutPageDTO;
	}
	/**
	 * 添加提现
	 * @param userId 用户id
	 * @param money 提现金额
	 * @param cardId 提现卡id
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "/walletCashOut/saveWalletCashOut.json")
	@ResponseBody
	public WalletCashOutSaveDTO saveWalletCashOut(BigDecimal money, Integer cardId, HttpServletRequest request){
		WalletCashOutSaveDTO walletCashOutSaveDTO = new WalletCashOutSaveDTO();
		if(validateSaveWalletCashOut(walletCashOutSaveDTO,getUserId(),cardId, money)){
			WalletCashOutModelSave walletCashOutModelSave = new WalletCashOutModelSave(getUserId(), money, cardId);
			walletCashOutMng.saveWalletCashOut(walletCashOutModelSave);
			walletCashOutSaveDTO.setState(BaseDTO.BaseDTOEnum.API_STATUS_SUCCESS);
		}
		return walletCashOutSaveDTO;
	}
	
	/**
	 * 校验查询提现保存接口
	 * @param baseDTO
	 * @param userId 用户id
	 * @return
	 */
	private Boolean validateSaveWalletCashOut(BaseDTO baseDTO, Integer userId, Integer cardId, BigDecimal money) {
		if (cmsUserMng.findById(userId)  == null) {
			baseDTO.setState(BaseDTO.BaseDTOEnum.API_MESSAGE_USER_NOT_FOUND);
			return false;			
		}
		if (walletCardMng.findById(cardId) == null) {
			baseDTO.setState(WalletCashOutSaveDTOEnum.CARD_IS_NOT_EXIST);
			return false;
		}
		if (money.compareTo(new BigDecimal(0)) <= 0) {
			baseDTO.setState(WalletCashOutSaveDTOEnum.CASHOUT_CAN_NOT_ZERO);
			return false;
		}
		CmsUser jcUser = cmsUserMng.findById(userId);
		if (jcUser.getMoney().compareTo(money) < 0) {
			baseDTO.setState(WalletCashOutSaveDTOEnum.MONEY_IS_NOT_ENGOUH);
			return false;
		}
		return true;
	}
	
	/**
	 * 校验提现添加界面接口
	 * @param baseDTO
	 * @param userId
	 * @return
	 */
	private Boolean validateAddWalletCashOut(BaseDTO baseDTO, Integer userId) {
		if (cmsUserMng.findById(userId)  == null) {
			baseDTO.setState(WalletCashOutPageDTOEnum.USER_IS_NOT_EXIST);
			return false;			
		}
		return true;
	}

}

