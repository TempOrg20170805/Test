package com.sunrun.rest.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.jeecms.common.page.Pagination;
import com.jeecms.core.entity.CmsUser;
import com.jeecms.core.manager.CmsUserMng;
import com.sunrun.rest.dto.BaseDTO;
import com.sunrun.rest.dto.WalletDetailDTO;
import com.sunrun.rest.dto.BaseDTO.BaseDTOEnum;

/**
 * 
 * @author zwy
 * @ClassName UserWalletController.java
 * @CreateDate  2017-7-14
 * @descrintion  我的——我的钱包
 * @editor 
 * @editDate
 */
@Controller
public class UserWalletController {
	
	@Autowired
	private CmsUserMng cmsUserMng;
	
	/**
	 * 钱包详情
	 * @param userId 
	 * @param walletId 钱包Id
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "/user/wallet/detail.json")
	@ResponseBody
	public WalletDetailDTO detailWallet(Integer userId, HttpServletRequest request){
		WalletDetailDTO dto = new WalletDetailDTO();
		if(validateDetailWallet(dto, userId)){
			CmsUser jcUser = cmsUserMng.findById(userId);
			dto.setMoney(jcUser.getMoney());
			dto.setState(BaseDTOEnum.API_STATUS_SUCCESS);
		}
		return dto;
	}
	
	/**
	 * 校验钱包详情接口
	 * @param baseDTO
	 * @param userId 用户id
	 * @param walletId 钱包Id
	 * @return
	 */
	private Boolean validateDetailWallet(BaseDTO baseDTO, Integer userId) {
		if (cmsUserMng.findById(userId)  == null) {
			baseDTO.setState(BaseDTOEnum.API_MESSAGE_USER_NOT_FOUND);
			return false;			
		}
		return true;
	}

}
