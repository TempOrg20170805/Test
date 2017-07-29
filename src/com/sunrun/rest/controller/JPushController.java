package com.sunrun.rest.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.jeecms.common.page.Pagination;
import com.jeecms.core.entity.CmsUser;
import com.jeecms.core.manager.CmsUserMng;
import com.sunrun.common.util.jpush.JpushUtils;
import com.sunrun.rest.dto.BaseDTO;
import com.sunrun.rest.dto.WalletDetailDTO;
import com.sunrun.rest.dto.BaseDTO.BaseDTOEnum;

/**
 * 文 件 名 : JPushController
 * 创 建 人： 金明明
 * 日 期：2017-7-26
 * 修 改 人： 
 * 日 期： 
 * 描 述：极光推送 Controller
 */
@Controller
public class JPushController extends BaseController{
	
	@Autowired
	private CmsUserMng cmsUserMng;
	
	/**
	 * 推送
	 * @param jsonStr json字符串数据
	 * @param type 类型
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "/pushTest.json")
	@ResponseBody
	public WalletDetailDTO pushTest(String jsonStr, Integer type, String extrasMsg, HttpServletRequest request){
		WalletDetailDTO dto = new WalletDetailDTO();
		if(validatePushTest(dto, super.getUserId())){
			CmsUser jcUser = cmsUserMng.findById(super.getUserId());
			/*Map<String, String> extras = new HashMap<String, String>();
		    extras.put("extrasMsg", extrasMsg);
		    String registrationId  ="1104a897929ec6b0bb1";
			JpushUtils.SendPush(jsonStr, "标题", registrationId, extras);*/
			
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
	private Boolean validatePushTest(BaseDTO baseDTO, Integer userId) {
		if (cmsUserMng.findById(userId)  == null) {
			baseDTO.setState(BaseDTOEnum.API_MESSAGE_USER_NOT_FOUND);
			return false;			
		}
		return true;
	}

}
