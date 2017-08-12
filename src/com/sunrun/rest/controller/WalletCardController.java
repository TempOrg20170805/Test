package com.sunrun.rest.controller;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.io.FilenameUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.jeecms.cms.Constants;
import com.jeecms.cms.manager.assist.CmsFileMng;
import com.jeecms.common.upload.FileRepository;
import com.jeecms.core.manager.CmsUserMng;
import com.sunrun.rest.dto.BaseDTO;
import com.sunrun.rest.dto.WalletCardAlipayDTO;
import com.sunrun.rest.dto.WalletCardBankDTO;
import com.sunrun.rest.dto.WalletCardDeleteDTO;
import com.sunrun.rest.dto.WalletCardDeleteDTO.WalletCardDeleteDTOEnum;
import com.sunrun.rest.dto.WalletCardQueryListDTO;
import com.sunrun.rest.dto.WalletCardSaveDTO;
import com.sunrun.rest.dto.WalletCardSaveDTO.WalletCardSaveDTOEnum;
import com.sunrun.rest.dto.WalletCardWeiXinDTO;
import com.sunrun.washer.entity.WalletCard;
import com.sunrun.washer.enums.WalletCardTypeEnum;
import com.sunrun.washer.manager.WalletCardMng;
import com.sunrun.washer.model.WalletCardModel;
import com.sunrun.washer.model.WalletCardModelSave;

/**
 * 文 件 名 : WalletCardController.java
 * 创 建 人： 金明明
 * 日 期：2017-8-6
 * 修 改 人： 
 * 日 期： 
 * 描 述：银行卡/支付宝管理 Controller层
 */
@Controller
public class WalletCardController extends BaseController {

	@Autowired
	private WalletCardMng walletCardMng;
	@Autowired
	private CmsUserMng cmsUserMng;
	
	@Autowired
	private FileRepository fileRepository;
	@Autowired
	private CmsFileMng fileMng;

	/**
	 * 查询银行卡列表（不分页）
	 * @param userId 用户Id
	 * @param walletCardModel 银行卡/支付宝查询条件
	 * @return
	 */
	@RequestMapping("/walletCard/queryWalletCardListByModel.json")
	@ResponseBody
	public WalletCardQueryListDTO queryWalletCardListByModel(WalletCardModel walletCardModel, HttpServletRequest request) {
		WalletCardQueryListDTO walletCardQueryListDTO = new WalletCardQueryListDTO();
		if (validateQueryWalletCardByModel(walletCardQueryListDTO, getUserId(), walletCardModel)) {
			walletCardModel.setUserId(getUserId());
			// 代码：默认搜索所有类型银行卡
			walletCardModel.setType(WalletCardTypeEnum.ALL.getValue());
			List<WalletCard> walletCards = (List<WalletCard>) walletCardMng.queryWalletCardListByModel(walletCardModel);

			// 赋值支付宝
			List<WalletCardAlipayDTO> walletCardAlipayDTOs = new ArrayList<WalletCardAlipayDTO>();
			// 赋值微信
			List<WalletCardWeiXinDTO> walletCardWeiXinDTOs = new ArrayList<WalletCardWeiXinDTO>(); 
			// 赋值银行
			List<WalletCardBankDTO> walletCardBankDTOs = new ArrayList<WalletCardBankDTO>();
			for (WalletCard walletCard : walletCards) {
				// 支付宝
				if (WalletCardTypeEnum.ALIPAY_CARD.getValue().equals(walletCard.getType())) {
					WalletCardAlipayDTO walletCardAlipayDTO = new WalletCardAlipayDTO(walletCard);
					walletCardAlipayDTOs.add(walletCardAlipayDTO);
				} 
				// 微信
				else if (WalletCardTypeEnum.WEIXIN.getValue().equals(walletCard.getType())) {
					WalletCardWeiXinDTO walletCardWeiXinDTO = new WalletCardWeiXinDTO(walletCard);
					walletCardWeiXinDTOs.add(walletCardWeiXinDTO);
				}
				// 银行
				else if (WalletCardTypeEnum.BANK_CARD.getValue().equals(walletCard.getType())) {
					WalletCardBankDTO walletCardBankDTO = new WalletCardBankDTO(walletCard);
					walletCardBankDTOs.add(walletCardBankDTO);
				}
			}
			walletCardQueryListDTO.setWalletCardAlipayDTOs(walletCardAlipayDTOs);
			walletCardQueryListDTO.setWalletCardWeiXinDTOs(walletCardWeiXinDTOs);
			walletCardQueryListDTO.setWalletCardBankDTOs(walletCardBankDTOs);
			walletCardQueryListDTO.setState(BaseDTO.BaseDTOEnum.API_STATUS_SUCCESS);
			
		}
		return walletCardQueryListDTO;
	}
	
	
	/**
	 * 添加银行卡/支付宝/微信
	 * @param userId 用户id
	 * @param realname 真实姓名
	 * @param type 类型 1.银行卡 2.支付宝 3.微信
	 * @param bankName 银行名称
	 * @param bankNum 银行卡号
	 * @param bankBranches 银行网点
	 * @param alipayNum 支付宝账号
	 * @Param collectionCodeImg 收款码图片
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "/walletCard/saveWalletCard.json")
	@ResponseBody
	public WalletCardSaveDTO saveWalletCard(String realname,Integer type,String bankName,String bankNum,String bankBranches,String alipayNum, @RequestParam(required=false)MultipartFile collectionCodeImg,HttpServletRequest request){
		WalletCardSaveDTO walletCardSaveDTO = new WalletCardSaveDTO();
		if(validateSaveWalletCard(walletCardSaveDTO, getUserId(), realname, type, bankName, bankNum, bankBranches, alipayNum)){
			String fileUrl = "";
			String upPath = Constants.COLLECTION_CODE_IMG_PATH + "Picture";
			if(collectionCodeImg!=null && collectionCodeImg.getSize()!=0){
				try {
					String fileName = collectionCodeImg.getOriginalFilename();
					String ext = FilenameUtils.getExtension(fileName).toLowerCase(
							Locale.ENGLISH);
					String ctx = request.getContextPath();
					fileUrl = fileRepository.storeByExt(upPath, ext,
							collectionCodeImg);
					// 加上部署路径
					fileUrl = ctx + fileUrl;
					fileMng.saveFileByPath(fileUrl, fileName, false);	
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
			
			walletCardMng.saveWalletCard(new WalletCardModelSave(getUserId(), realname, type, bankName, bankNum, bankBranches, alipayNum, fileUrl));
			walletCardSaveDTO.setState(BaseDTO.BaseDTOEnum.API_STATUS_SUCCESS);
		}
		return walletCardSaveDTO;
	}
	
	/**
	 * 删除银行卡/支付宝/微信
	 * @param userId 
	 * @param cardId 卡Id 
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "/walletCard/deleteWalletCard.json")
	@ResponseBody	
	public WalletCardDeleteDTO deleteWalletCard(Integer cardId, HttpServletRequest request){
		WalletCardDeleteDTO walletCardDeleteDTO = new WalletCardDeleteDTO();
		if(validateDeleteWalletCard(walletCardDeleteDTO,getUserId(),cardId)){
			walletCardMng.deleteById(cardId);
			walletCardDeleteDTO.setState(BaseDTO.BaseDTOEnum.API_STATUS_SUCCESS);
		}
		return walletCardDeleteDTO;
	}
	
	
	/**
	 * 校验查询银行卡/支付宝列表接口
	 * @param baseDTO
	 * @param userId 用户id
	 * @param walletCardModel 银行卡/支付宝查询条件
	 * @return
	 */
	private Boolean validateQueryWalletCardByModel(BaseDTO baseDTO, Integer userId, WalletCardModel walletCardModel) {
		if (cmsUserMng.findById(userId)  == null) {
			baseDTO.setState(BaseDTO.BaseDTOEnum.API_MESSAGE_USER_NOT_FOUND);
			return false;			
		}
		return true;
	}
	
	/**
	 * 校验查询银行卡/支付宝添加接口
	 * @param baseDTO
	 * @param userId 用户id
	 * @return
	 */
	private Boolean validateSaveWalletCard(BaseDTO baseDTO, Integer userId,String realname,Integer type,String bankName,String bankNum,String bankBranches,String alipayNum) {
		if (cmsUserMng.findById(userId)  == null) {
			baseDTO.setState(BaseDTO.BaseDTOEnum.API_MESSAGE_USER_NOT_FOUND);
			return false;			
		}
		if (!WalletCardTypeEnum.ALIPAY_CARD.getValue().equals(type) && !WalletCardTypeEnum.BANK_CARD.getValue().equals(type) && !WalletCardTypeEnum.WEIXIN.getValue().equals(type)){
			baseDTO.setState(WalletCardSaveDTOEnum.CARD_TYPE_ERROR);
			return false;
		}
		return true;
	}
	
	/**
	 * 校验删除银行卡/支付宝删除接口
	 * @param baseDTO
	 * @param userId 用户id
	 * @return
	 */
	private Boolean validateDeleteWalletCard(BaseDTO baseDTO, Integer userId, Integer cardId) {
		if (cmsUserMng.findById(userId)  == null) {
			baseDTO.setState(BaseDTO.BaseDTOEnum.API_MESSAGE_USER_NOT_FOUND);
			return false;
		}
		if (walletCardMng.findById(cardId) == null) {
			baseDTO.setState(WalletCardDeleteDTOEnum.CARD_IS_NOT_EXIST);
			return false;
		}
		return true;
	}

}

