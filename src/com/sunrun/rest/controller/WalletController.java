package com.sunrun.rest.controller;
import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.jeecms.core.entity.CmsUser;
import com.jeecms.core.manager.CmsUserMng;
import com.sunrun.rest.dto.BaseDTO;
import com.sunrun.rest.dto.WalletDetailDTO;
import com.sunrun.washer.manager.WalletCardMng;
import com.sunrun.washer.manager.WalletLogMng;

/**
 * 文 件 名 : WalletController
 * 创 建 人： 金明明
 * 日 期：2017-8-6
 * 修 改 人： 
 * 日 期： 
 * 描 述：钱包管理 Controller层
 */
@Controller
public class WalletController extends BaseController {

	@Autowired
	private CmsUserMng cmsUserMng;
	@Autowired
	private WalletLogMng walletLogMng;

	@Autowired
	private WalletCardMng walletCardMng;
	
	/**
	 * 钱包详情
	 * @param userId 
	 * @param walletId 钱包Id
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "/wallet/detailWallet.json")
	@ResponseBody
	public WalletDetailDTO detailWallet(HttpServletRequest request){
		WalletDetailDTO walletDetailDTO = new WalletDetailDTO();
		if(validateDetailWallet(walletDetailDTO, getUserId())){
			CmsUser jcUser = cmsUserMng.findById(getUserId());
			walletDetailDTO.setMoney(jcUser.getMoney());
			walletDetailDTO.setState(BaseDTO.BaseDTOEnum.API_STATUS_SUCCESS);
		}
		return walletDetailDTO;
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
			baseDTO.setState(BaseDTO.BaseDTOEnum.API_MESSAGE_USER_NOT_FOUND);
			return false;			
		}
		return true;
	}
}

