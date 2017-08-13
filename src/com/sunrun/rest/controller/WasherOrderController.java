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
import com.sunrun.rest.dto.BaseDTO;
import com.sunrun.rest.dto.WasherOrderDTO;
import com.sunrun.rest.dto.WasherOrderDetailDTO;
import com.sunrun.rest.dto.WasherOrderQueryDTO;
import com.sunrun.rest.dto.WasherOrderSaveDTO;
import com.sunrun.rest.dto.WasherOrderUpdateDTO;
import com.sunrun.washer.entity.WasherOrder;
import com.sunrun.washer.enums.ModeNoEnum;
import com.sunrun.washer.manager.MachineMng;
import com.sunrun.washer.manager.ModeMng;
import com.sunrun.washer.manager.WasherOrderMng;
import com.sunrun.washer.model.WasherOrderModel;
import com.sunrun.washer.model.WasherOrderModelSave;

/**
 * 文 件 名 : WasherOrderController.java
 * 创 建 人： 金明明
 * 日 期：2017-8-7
 * 修 改 人： 
 * 日 期： 
 * 描 述：订单 Controller层
 */
@Controller
public class WasherOrderController extends BaseController{

	@Autowired
	private WasherOrderMng washerOrderMng;
	@Autowired
	private CmsUserMng cmsUserMng;
	@Autowired
	private ModeMng modeMng;
	@Autowired
	private MachineMng machineMng;

	/**
	 * 查询订单管理列表
	 * @param userId 用户Id
	 * @param washerOrderModel 订单管理查询条件
	 * @param pageNo 当前页
	 * @param pageSize 每页数据量
	 * @return
	 */
	@RequestMapping("/washerOrder/queryWasherOrderByModel.json")
	@ResponseBody
	public WasherOrderQueryDTO queryWasherOrderByModel(WasherOrderModel washerOrderModel, Integer pageNo, Integer pageSize, HttpServletRequest request) {
		WasherOrderQueryDTO washerOrderQueryDTO = new WasherOrderQueryDTO();
		if (validateQueryWasherOrderByModel(washerOrderQueryDTO, getUserId(), washerOrderModel)) {
			washerOrderModel.setBuyerId(getUserId());
			// 代码：设置默认相关值
			Pagination pagination = washerOrderMng.queryWasherOrderByModel(washerOrderModel, SimplePage.cpn(pageNo), pageSize);
			List<WasherOrder> washerOrders = (List<WasherOrder>) pagination.getList();
					
			// 赋值订单管理分页信息
			washerOrderQueryDTO.setPageNo(pagination.getPageNo());
			washerOrderQueryDTO.setPageSize(pagination.getPageSize());
			washerOrderQueryDTO.setTotalCount(pagination.getTotalCount());

			// 赋值订单管理必要信息信息
			List<WasherOrderDTO> washerOrderDTOs = new ArrayList<WasherOrderDTO>(); 
			for (WasherOrder washerOrder : washerOrders) {
				WasherOrderDTO washerOrderDTO = new WasherOrderDTO(washerOrder);
				// 赋值商品列表
				washerOrderDTOs.add(washerOrderDTO);
			}
			washerOrderQueryDTO.setWasherOrderDTOs(washerOrderDTOs);
			washerOrderQueryDTO.setState(BaseDTO.BaseDTOEnum.API_STATUS_SUCCESS);
			
		}
		return washerOrderQueryDTO;
	}

	/**
	 * 下单
	 * @param modeId 模式ID
	 * @param mechineId 洗衣机ID
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "/washerOrder/saveWasherOrder.json")
	@ResponseBody
	public WasherOrderSaveDTO saveWasherOrder(Integer modeId, Integer mechineId, HttpServletRequest request){
		WasherOrderSaveDTO washerOrderSaveDTO = new WasherOrderSaveDTO();
		if(validateSaveWasherOrder(washerOrderSaveDTO, getUserId(), modeId, mechineId)){
			WasherOrderModelSave washerOrderModelSave = new WasherOrderModelSave();
			washerOrderModelSave.setUserId(getUserId());
			washerOrderModelSave.setModeId(modeId);
			washerOrderModelSave.setMechineId(mechineId);
			WasherOrder washerOrder = washerOrderMng.saveWasherOrder(washerOrderModelSave);
			washerOrderSaveDTO.init(washerOrder);
			washerOrderSaveDTO.setState(BaseDTO.BaseDTOEnum.API_STATUS_SUCCESS);
		}
		return washerOrderSaveDTO;
	}
	
	/**
	 * 校验下单接口接口
	 * @param baseDTO
	 * @param userId 用户id
	 * @return
	 */
	private Boolean validateSaveWasherOrder(BaseDTO baseDTO, Integer userId, Integer modeId, Integer mechineId) {
		if (cmsUserMng.findById(userId)  == null) {
			baseDTO.setState(BaseDTO.BaseDTOEnum.API_MESSAGE_USER_NOT_FOUND);
			return false;			
		}
		if (modeMng.findById(modeId) == null || machineMng.findById(mechineId) == null || ModeNoEnum.getContains(modeId) == null) {
			baseDTO.setState(BaseDTO.BaseDTOEnum.API_MESSAGE_VALIDATECODE_NOTEXIST);
			return false;
		}
		
		return true;
	}

	/**
	 * 更新订单管理
	 * @param userId 
	 * @param xx 
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "/washerOrder/updateWasherOrder.json")
	@ResponseBody	
	public WasherOrderUpdateDTO updateWasherOrder(HttpServletRequest request){
		WasherOrderUpdateDTO washerOrderUpdateDTO = new WasherOrderUpdateDTO();
		if(validateUpdateWasherOrder(washerOrderUpdateDTO,getUserId())){
			washerOrderUpdateDTO.setState(BaseDTO.BaseDTOEnum.API_STATUS_SUCCESS);
		}
		return washerOrderUpdateDTO;
	}
	
	/**
	 * 校验更新订单管理更新接口
	 * @param baseDTO
	 * @param userId 用户id
	 * @return
	 */
	private Boolean validateUpdateWasherOrder(BaseDTO baseDTO, Integer userId) {
		if (cmsUserMng.findById(userId)  == null) {
			baseDTO.setState(BaseDTO.BaseDTOEnum.API_MESSAGE_USER_NOT_FOUND);
			return false;			
		}
		return true;
	}

	/**
	 * 订单管理详情
	 * @param userId 
	 * @param washerOrderId 订单管理Id
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "/washerOrder/detailWasherOrder.json")
	@ResponseBody
	public WasherOrderDetailDTO detailWasherOrder(Integer washerOrderId, HttpServletRequest request){
		WasherOrderDetailDTO washerOrderDetailDTO = new WasherOrderDetailDTO();
		if(validateDetailWasherOrder(washerOrderDetailDTO, getUserId(), washerOrderId)){
			WasherOrder washerOrder = washerOrderMng.findById(washerOrderId);
			washerOrderDetailDTO.init(washerOrder);
			washerOrderDetailDTO.setState(BaseDTO.BaseDTOEnum.API_STATUS_SUCCESS);
		}
		return washerOrderDetailDTO;
	}
		
	/**
	 * 校验订单管理详情接口
	 * @param baseDTO
	 * @param userId 用户id
	 * @param washerOrderId 订单管理Id
	 * @return
	 */
	private Boolean validateDetailWasherOrder(BaseDTO baseDTO, Integer userId, Integer washerOrderId) {
		if (cmsUserMng.findById(userId)  == null) {
			baseDTO.setState(BaseDTO.BaseDTOEnum.API_MESSAGE_USER_NOT_FOUND);
			return false;			
		}
		if (washerOrderMng.findById(washerOrderId) == null) {
			baseDTO.setState(WasherOrderDetailDTO.WasherOrderDetailDTOEnum.IS_NOT_EXIST);
			return false;
		}
		return true;
	}


	/**
	 * 校验查询订单管理列表接口
	 * @param baseDTO
	 * @param userId 用户id
	 * @param washerOrderModel 订单管理查询条件
	 * @return
	 */
	private Boolean validateQueryWasherOrderByModel(BaseDTO baseDTO, Integer userId, WasherOrderModel washerOrderModel) {
		if (cmsUserMng.findById(userId)  == null) {
			baseDTO.setState(BaseDTO.BaseDTOEnum.API_MESSAGE_USER_NOT_FOUND);
			return false;			
		}
		return true;
	}

}

