package com.sunrun.rest.controller;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.jeecms.common.page.Pagination;
import com.jeecms.common.page.SimplePage;
import com.jeecms.core.manager.CmsUserMng;
import com.sunrun.washer.entity.WalletLog;
import com.sunrun.washer.manager.WalletLogMng;
import com.sunrun.washer.model.WalletLogModel;
import com.sunrun.rest.dto.BaseDTO;
import com.sunrun.rest.dto.WalletLogDTO;
import com.sunrun.rest.dto.WalletLogQueryDTO;
import com.sunrun.rest.dto.BaseDTO.BaseDTOEnum;

/**
 * 文 件 名 : WalletLogController
 * 创 建 人： 金明明
 * 日 期：2017-8-6
 * 修 改 人： 
 * 日 期： 
 * 描 述：消费日志管理 Controller层
 */
@Controller
public class WalletLogController extends BaseController {

	@Autowired
	private WalletLogMng walletLogMng;
	@Autowired
	private CmsUserMng cmsUserMng;

	/**
	 * 查询钱包消费日志列表
	 * @param userId 用户Id
	 * @param walletLogModel 钱包消费日志查询条件
	 * @param pageNo 当前页
	 * @param pageSize 每页数据量
	 * @return
	 */
	@RequestMapping("/walletLog/queryWalletLogByModel.json")
	@ResponseBody
	public WalletLogQueryDTO queryWalletLogByModel(WalletLogModel walletLogModel, Integer pageNo, Integer pageSize, HttpServletRequest request) {
		WalletLogQueryDTO walletLogQueryDTO = new WalletLogQueryDTO();
		if (validateQueryWalletLogByModel(walletLogQueryDTO, getUserId(), walletLogModel)) {
			walletLogModel.setUserId(getUserId());
			// 代码：设置莫得了相关值
			Pagination pagination = walletLogMng.queryWalletLogByModel(walletLogModel, SimplePage.cpn(pageNo), pageSize);
			List<WalletLog> walletLogs = (List<WalletLog>) pagination.getList();
					
			// 赋值钱包消费日志分页信息
			walletLogQueryDTO.setPageNo(pagination.getPageNo());
			walletLogQueryDTO.setPageSize(pagination.getPageSize());
			walletLogQueryDTO.setTotalCount(pagination.getTotalCount());

			// 赋值钱包消费日志必要信息信息
			List<WalletLogDTO> walletLogDTOs = new ArrayList<WalletLogDTO>(); 
			for (WalletLog walletLog : walletLogs) {
				WalletLogDTO walletLogDTO = new WalletLogDTO(walletLog);
				// 设置DTO 例如
				// walletLogDTO.setXX("XX");
				
				// 赋值商品列表
				walletLogDTOs.add(walletLogDTO);
			}
			walletLogQueryDTO.setWalletLogDTOs(walletLogDTOs);
			walletLogQueryDTO.setState(BaseDTO.BaseDTOEnum.API_STATUS_SUCCESS);
			
		}
		return walletLogQueryDTO;
	}

	/**
	 * 校验查询钱包消费日志列表接口
	 * @param baseDTO
	 * @param userId 用户id
	 * @param walletLogModel 钱包消费日志查询条件
	 * @return
	 */
	private Boolean validateQueryWalletLogByModel(BaseDTO baseDTO, Integer userId, WalletLogModel walletLogModel) {
		if (cmsUserMng.findById(userId)  == null) {
			baseDTO.setState(BaseDTO.BaseDTOEnum.API_MESSAGE_USER_NOT_FOUND);
			return false;			
		}
		return true;
	}
}

