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
import com.sunrun.rest.dto.UserMachineAllCountDTO;
import com.sunrun.rest.dto.UserMachineDTO;
import com.sunrun.rest.dto.UserMachineDeleteDTO;
import com.sunrun.rest.dto.UserMachineDetailDTO;
import com.sunrun.rest.dto.UserMachineQueryDTO;
import com.sunrun.rest.dto.UserMachineSaveDTO;
import com.sunrun.rest.dto.UserMachineUpdateDTO;
import com.sunrun.washer.entity.FloorLayer;
import com.sunrun.washer.entity.Machine;
import com.sunrun.washer.entity.UserMachine;
import com.sunrun.washer.manager.FloorLayerMng;
import com.sunrun.washer.manager.MachineMng;
import com.sunrun.washer.manager.UserMachineMng;
import com.sunrun.washer.model.UserMachineModel;
import com.sunrun.washer.model.UserMachineModelUpdatePutIn;

/**
 * 文 件 名 : UserMachineController.java
 * 创 建 人： 金明明
 * 日 期：2017-7-30
 * 修 改 人： 
 * 日 期： 
 * 描 述：用户关联洗衣机 Controller层
 */
@Controller
public class UserMachineController extends BaseController{

	@Autowired
	private UserMachineMng userMachineMng;
	@Autowired
	private CmsUserMng cmsUserMng;
	@Autowired
	private FloorLayerMng floorLayerMng;
	@Autowired
	private MachineMng machineMng;
	
	
	/**
	 * 查询渠道商的洗衣机洗衣机数量
	 * @param userId 用户Id
	 * @param userMachineModel 用户关联洗衣机管理查询条件
	 * @param pageNo 当前页
	 * @param pageSize 每页数据量
	 * @return
	 */
	@RequestMapping("/userMachine/queryUserMachineAllCount.json")
	@ResponseBody
	public UserMachineAllCountDTO queryUserMachineAllCount(HttpServletRequest request) {
		UserMachineAllCountDTO userMachineAllCountDTO = new UserMachineAllCountDTO();
		if (validateQueryUserMachineAllCountByModel(userMachineAllCountDTO, getUserId())) {
			UserMachineModel userMachineModel = new UserMachineModel();
			userMachineModel.setUserId(getUserId());
			// 代码：设置默认相关值
			Pagination pagination = userMachineMng.queryUserMachineByModel(userMachineModel, 1, 1);
			// 赋值用户关联洗衣机管理分页信息
			userMachineAllCountDTO.setTotalCount(pagination.getTotalCount());
			userMachineAllCountDTO.setState(BaseDTO.BaseDTOEnum.API_STATUS_SUCCESS);
		}
		return userMachineAllCountDTO;
	}
	
	
	/**
	 * 查询渠道商的洗衣机（未查询未投放的洗衣机）
	 * @param userId 用户Id
	 * @param userMachineModel 用户关联洗衣机管理查询条件
	 * @param pageNo 当前页
	 * @param pageSize 每页数据量
	 * @return
	 */
	@RequestMapping("/userMachine/queryUserMachineByModel.json")
	@ResponseBody
	public UserMachineQueryDTO queryUserMachineByModel(UserMachineModel userMachineModel, Integer pageNo, Integer pageSize, HttpServletRequest request) {
		UserMachineQueryDTO userMachineQueryDTO = new UserMachineQueryDTO();
		if (validateQueryUserMachineByModel(userMachineQueryDTO, getUserId(), userMachineModel)) {
			// 代码：设置默认相关值
			Pagination pagination = userMachineMng.queryUserMachineByModel(userMachineModel, SimplePage.cpn(pageNo), pageSize);
			List<UserMachine> userMachines = (List<UserMachine>) pagination.getList();
					
			// 赋值用户关联洗衣机管理分页信息
			userMachineQueryDTO.setPageNo(pagination.getPageNo());
			userMachineQueryDTO.setPageSize(pagination.getPageSize());
			userMachineQueryDTO.setTotalCount(pagination.getTotalCount());

			// 赋值用户关联洗衣机管理必要信息信息
			List<UserMachineDTO> userMachineDTOs = new ArrayList<UserMachineDTO>(); 
			for (UserMachine userMachine : userMachines) {
				UserMachineDTO userMachineDTO = new UserMachineDTO(userMachine);
				// 赋值商品列表
				userMachineDTOs.add(userMachineDTO);
			}
			userMachineQueryDTO.setUserMachineDTOs(userMachineDTOs);
			userMachineQueryDTO.setState(BaseDTO.BaseDTOEnum.API_STATUS_SUCCESS);
		}
		return userMachineQueryDTO;
	}
	
	/**
	 * 渠道商投放洗衣机
	 * @param machineId 洗衣机Id
	 * @param floorLayerId 楼层Id
	 * @param floorLayerX 楼层位置x
	 * @param floorLayerY 楼层位置y
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "/userMachine/updateUserMachinePutIn.json")
	@ResponseBody
	public UserMachineSaveDTO updateUserMachinePutIn(Integer machineId, Integer floorLayerId,Integer floorLayerX,Integer floorLayerY, HttpServletRequest request){
		UserMachineSaveDTO userMachineSaveDTO = new UserMachineSaveDTO();
		if(validateUpdateUserMachinePutIn(userMachineSaveDTO,getUserId(), machineId, floorLayerId, floorLayerX, floorLayerY)){
			UserMachineModelUpdatePutIn userMachineModelSaveUpdatePutIn = new UserMachineModelUpdatePutIn(machineId, floorLayerId, floorLayerX, floorLayerY);
			userMachineMng.updateUserMachinePutIn(userMachineModelSaveUpdatePutIn);
			userMachineSaveDTO.setState(BaseDTO.BaseDTOEnum.API_STATUS_SUCCESS);
		}
		return userMachineSaveDTO;
	}
	
	/**
	 * 校验渠道商投放洗衣机接口
	 * @param baseDTO
	 * @param 
	 * @param userId 用户id
	 * @param floorLayerId 楼层Id
	 * @param floorLayerX 楼层位置x
	 * @param floorLayerY 楼层位置y
	 * @return
	 */
	private Boolean validateUpdateUserMachinePutIn(BaseDTO baseDTO, Integer userId, Integer machineId, Integer floorLayerId,Integer floorLayerX,Integer floorLayerY) {
		if (cmsUserMng.findById(userId)  == null) {
			baseDTO.setState(BaseDTO.BaseDTOEnum.API_STATUS_SUCCESS);
			return false;			
		}
		if (floorLayerId == null || floorLayerX == null || floorLayerY == null || machineId == null) {
			baseDTO.setState(BaseDTO.BaseDTOEnum.API_MESSAGE_PARAM_NOT_NULL);
			return false;
		}
		FloorLayer floorLayer = floorLayerMng.findById(floorLayerId);
		Machine machine = machineMng.findById(machineId);
		if (floorLayer == null || machine == null) {
			baseDTO.setState(BaseDTO.BaseDTOEnum.API_MESSAGE_VALIDATECODE_NOTEXIST);
		}
		return true;
	}
	
	/**
	 * 渠道商删除投放的洗衣机
	 * @param machineId 洗衣机Id
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "/userMachine/updateUserMachineFloorLayerDelete.json")
	@ResponseBody
	public UserMachineSaveDTO updateUserMachineFloorLayerDelete(Integer machineId, HttpServletRequest request){
		UserMachineSaveDTO userMachineSaveDTO = new UserMachineSaveDTO();
		if(validateUpdateUserMachineFloorLayerDelete(userMachineSaveDTO,getUserId(), machineId)){
			userMachineMng.updateUserMachineFloorLayerDelete(machineId);
			userMachineSaveDTO.setState(BaseDTO.BaseDTOEnum.API_STATUS_SUCCESS);
		}
		return userMachineSaveDTO;
	}
	
	/**
	 * 校验渠道商投放洗衣机接口
	 * @param baseDTO
	 * @param userId 用户id
	 * @param machineId
	 * @return
	 */
	private Boolean validateUpdateUserMachineFloorLayerDelete(BaseDTO baseDTO, Integer userId, Integer machineId) {
		if (cmsUserMng.findById(userId)  == null) {
			baseDTO.setState(BaseDTO.BaseDTOEnum.API_STATUS_SUCCESS);
			return false;			
		}
		if (machineId == null) {
			baseDTO.setState(BaseDTO.BaseDTOEnum.API_MESSAGE_PARAM_NOT_NULL);
			return false;
		}
		Machine machine = machineMng.findById(machineId);
		if (machine == null) {
			baseDTO.setState(BaseDTO.BaseDTOEnum.API_MESSAGE_VALIDATECODE_NOTEXIST);
		}
		return true;
	}

	/**
	 * 添加用户关联洗衣机管理
	 * @param userId = customerId
	 * @param XX
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "/userMachine/saveUserMachine.json")
	@ResponseBody
	public UserMachineSaveDTO saveUserMachine(Integer xx, HttpServletRequest request){
		UserMachineSaveDTO userMachineSaveDTO = new UserMachineSaveDTO();
		if(validateSaveUserMachine(userMachineSaveDTO,xx)){
			// userMachineMng.save(bean, xx);
			userMachineSaveDTO.setState(BaseDTO.BaseDTOEnum.API_STATUS_SUCCESS);
		}
		return userMachineSaveDTO;
	}
	
	/**
	 * 校验查询用户关联洗衣机管理保存接口
	 * @param baseDTO
	 * @param userId 用户id
	 * @return
	 */
	private Boolean validateSaveUserMachine(BaseDTO baseDTO, Integer userId) {
		if (cmsUserMng.findById(userId)  == null) {
			baseDTO.setState(BaseDTO.BaseDTOEnum.API_MESSAGE_USER_NOT_FOUND);
			return false;			
		}
		return true;
	}

	/**
	 * 更新用户关联洗衣机管理
	 * @param userId 
	 * @param xx 
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "/userMachine/updateUserMachine.json")
	@ResponseBody	
	public UserMachineUpdateDTO updateUserMachine(HttpServletRequest request){
		UserMachineUpdateDTO userMachineUpdateDTO = new UserMachineUpdateDTO();
		if(validateUpdateUserMachine(userMachineUpdateDTO,getUserId())){
			userMachineUpdateDTO.setState(BaseDTO.BaseDTOEnum.API_STATUS_SUCCESS);
		}
		return userMachineUpdateDTO;
	}
	
	/**
	 * 校验更新用户关联洗衣机管理更新接口
	 * @param baseDTO
	 * @param userId 用户id
	 * @return
	 */
	private Boolean validateUpdateUserMachine(BaseDTO baseDTO, Integer userId) {
		if (cmsUserMng.findById(userId)  == null) {
			baseDTO.setState(BaseDTO.BaseDTOEnum.API_MESSAGE_USER_NOT_FOUND);
			return false;			
		}
		return true;
	}

	/**
	 * 删除用户删除洗衣机
	 * @param userId 
	 * @param machineId 洗衣机ID
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "/userMachine/deleteUserMachine.json")
	@ResponseBody	
	public UserMachineDeleteDTO deleteUserMachine(Integer machineId, HttpServletRequest request){
		UserMachineDeleteDTO userMachineDeleteDTO = new UserMachineDeleteDTO();
		if(validateDeleteUserMachine(userMachineDeleteDTO, getUserId(), machineId)){
			// userMachineMng.update(bean, xx);
			userMachineDeleteDTO.setState(BaseDTO.BaseDTOEnum.API_STATUS_SUCCESS);
		}
		return userMachineDeleteDTO;
	}
	
	/**
	 * 校验删除用户关联洗衣机管理删除接口
	 * @param baseDTO
	 * @param userId 用户id
	 * @return
	 */
	private Boolean validateDeleteUserMachine(BaseDTO baseDTO, Integer userId ,Integer machineId) {
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
	 * 用户关联洗衣机管理详情
	 * @param userId 
	 * @param userMachineId 用户关联洗衣机管理Id
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "/userMachine/detailUserMachine.json")
	@ResponseBody
	public UserMachineDetailDTO detailUserMachine(Integer userMachineId, HttpServletRequest request){
		UserMachineDetailDTO userMachineDetailDTO = new UserMachineDetailDTO();
		if(validateDetailUserMachine(userMachineDetailDTO, getUserId(), userMachineId)){
			// userMachineMng.findById(userMachineId);
			userMachineDetailDTO.setState(BaseDTO.BaseDTOEnum.API_STATUS_SUCCESS);
		}
		return userMachineDetailDTO;
	}
		
	/**
	 * 校验用户关联洗衣机管理详情接口
	 * @param baseDTO
	 * @param userId 用户id
	 * @param userMachineId 用户关联洗衣机管理Id
	 * @return
	 */
	private Boolean validateDetailUserMachine(BaseDTO baseDTO, Integer userId, Integer userMachineId) {
		if (cmsUserMng.findById(userId)  == null) {
			baseDTO.setState(BaseDTO.BaseDTOEnum.API_MESSAGE_USER_NOT_FOUND);
			return false;			
		}
		if (userMachineMng.findById(userMachineId) == null) {
			baseDTO.setState(UserMachineDetailDTO.UserMachineDetailDTOEnum.IS_NOT_EXIST);
			return false;
		}
		return true;
	}


	/**
	 * 校验查询用户关联洗衣机管理列表接口
	 * @param baseDTO
	 * @param userId 用户id
	 * @param userMachineModel 用户关联洗衣机管理查询条件
	 * @return
	 */
	private Boolean validateQueryUserMachineByModel(BaseDTO baseDTO, Integer userId, UserMachineModel userMachineModel) {
		if (cmsUserMng.findById(userId)  == null) {
			baseDTO.setState(BaseDTO.BaseDTOEnum.API_MESSAGE_USER_NOT_FOUND);
			return false;			
		}
		return true;
	}
	
	/**
	 * 校验查询用户洗衣机数量接口
	 * @param baseDTO
	 * @param userId 用户id
	 * @return
	 */
	private Boolean validateQueryUserMachineAllCountByModel(BaseDTO baseDTO, Integer userId) {
		if (cmsUserMng.findById(userId)  == null) {
			baseDTO.setState(BaseDTO.BaseDTOEnum.API_MESSAGE_USER_NOT_FOUND);
			return false;			
		}
		return true;
	}

}

