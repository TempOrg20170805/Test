package com.sunrun.rest.controller;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.jeecms.common.page.Pagination;
import com.jeecms.common.page.SimplePage;
import com.jeecms.core.manager.CmsUserMng;
import com.sunrun.rest.dto.BaseDTO;
import com.sunrun.rest.dto.UserFloorDTO;
import com.sunrun.rest.dto.UserFloorDeleteDTO;
import com.sunrun.rest.dto.UserFloorDetailDTO;
import com.sunrun.rest.dto.UserFloorQueryDTO;
import com.sunrun.rest.dto.UserFloorSaveDTO;
import com.sunrun.rest.dto.UserFloorUpdateDTO;
import com.sunrun.washer.entity.Machine;
import com.sunrun.washer.entity.UserFloor;
import com.sunrun.washer.manager.FloorMng;
import com.sunrun.washer.manager.MachineMng;
import com.sunrun.washer.manager.UserFloorMng;
import com.sunrun.washer.model.UserFloorModel;
import com.sunrun.washer.model.UserFloorModelSave;

/**
 * 文 件 名 : UserFloorController.java
 * 创 建 人： 金明明
 * 日 期：2017-7-31
 * 修 改 人： 
 * 日 期： 
 * 描 述：用户关联楼 Controller层
 */
@Controller
public class UserFloorController extends BaseController{

	@Autowired
	private UserFloorMng userFloorMng;
	@Autowired
	private CmsUserMng cmsUserMng;
	@Autowired
	private MachineMng machineMng;
	@Autowired
	private FloorMng floorMng;

	/**
	 * 查询用户关联楼管理列表
	 * @param userId 用户Id
	 * @param userFloorModel 用户关联楼管理查询条件
	 * @param pageNo 当前页
	 * @param pageSize 每页数据量
	 * @return
	 */
	@RequestMapping("/userFloor/queryUserFloorByModel.json")
	@ResponseBody
	public UserFloorQueryDTO queryUserFloorByModel(UserFloorModel userFloorModel, Integer pageNo, Integer pageSize, HttpServletRequest request) {
		UserFloorQueryDTO userFloorQueryDTO = new UserFloorQueryDTO();
		if (validateQueryUserFloorByModel(userFloorQueryDTO, getUserId(), userFloorModel)) {
			// 代码：设置默认相关值
			Pagination pagination = userFloorMng.queryUserFloorByModel(userFloorModel, SimplePage.cpn(pageNo), pageSize);
			List<UserFloor> userFloors = (List<UserFloor>) pagination.getList();
					
			// 赋值用户关联楼管理分页信息
			userFloorQueryDTO.setPageNo(pagination.getPageNo());
			userFloorQueryDTO.setPageSize(pagination.getPageSize());
			userFloorQueryDTO.setTotalCount(pagination.getTotalCount());

			// 赋值用户关联楼管理必要信息信息
			List<UserFloorDTO> userFloorDTOs = new ArrayList<UserFloorDTO>(); 
			for (UserFloor userFloor : userFloors) {
				
				List<Machine> machines = machineMng.queryMachineByFloor(userFloor.getFloor().getFloorId());
				UserFloorDTO userFloorDTO = new UserFloorDTO(userFloor, machines.size());
				// 赋值商品列表
				userFloorDTOs.add(userFloorDTO);
			}
			userFloorQueryDTO.setUserFloorDTOs(userFloorDTOs);
			userFloorQueryDTO.setState(BaseDTO.BaseDTOEnum.API_STATUS_SUCCESS);
			
		}
		return userFloorQueryDTO;
	}

	/**
	 * 添加用户关联楼
	 * @param addressDetail
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "/userFloor/saveUserFloor.json")
	@ResponseBody
	public UserFloorSaveDTO saveUserFloor(String addressDetail, HttpServletRequest request){
		UserFloorSaveDTO userFloorSaveDTO = new UserFloorSaveDTO();
		if(validateSaveUserFloor(userFloorSaveDTO,getUserId(),addressDetail)){
			UserFloorModelSave userFloorModelSave = new UserFloorModelSave();
			userFloorModelSave.setUserId(getUserId());
			userFloorModelSave.setAddressDetail(addressDetail);
			userFloorMng.saveUserFloor(userFloorModelSave);
			userFloorSaveDTO.setState(BaseDTO.BaseDTOEnum.API_STATUS_SUCCESS);
		}
		return userFloorSaveDTO;
	}
	
	/**
	 * 校验查询用户关联楼管理保存接口
	 * @param baseDTO
	 * @param userId 用户id
	 * @return
	 */
	private Boolean validateSaveUserFloor(BaseDTO baseDTO, Integer userId, String addressDetail) {
		if (cmsUserMng.findById(userId)  == null) {
			baseDTO.setState(BaseDTO.BaseDTOEnum.API_MESSAGE_USER_NOT_FOUND);
			return false;			
		}
		if (StringUtils.isBlank(addressDetail)) {
			baseDTO.setState(BaseDTO.BaseDTOEnum.API_MESSAGE_PARAM_REQUIRED);
			return false;
		}
		
		if (floorMng.isAddressDetailExists(addressDetail)) {
			baseDTO.setState(UserFloorSaveDTO.UserFloorSaveDTOEnum.FLOOR_NAME_EXIST);
			return false;
		}
		return true;
	}

	/**
	 * 更新用户关联楼管理
	 * @param userId 
	 * @param xx 
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "/userFloor/updateUserFloor.json")
	@ResponseBody	
	public UserFloorUpdateDTO updateUserFloor(HttpServletRequest request){
		UserFloorUpdateDTO userFloorUpdateDTO = new UserFloorUpdateDTO();
		if(validateUpdateUserFloor(userFloorUpdateDTO,getUserId())){
			userFloorUpdateDTO.setState(BaseDTO.BaseDTOEnum.API_STATUS_SUCCESS);
		}
		return userFloorUpdateDTO;
	}
	
	/**
	 * 校验更新用户关联楼管理更新接口
	 * @param baseDTO
	 * @param userId 用户id
	 * @return
	 */
	private Boolean validateUpdateUserFloor(BaseDTO baseDTO, Integer userId) {
		if (cmsUserMng.findById(userId)  == null) {
			baseDTO.setState(BaseDTO.BaseDTOEnum.API_MESSAGE_USER_NOT_FOUND);
			return false;			
		}
		return true;
	}

	/**
	 * 删除用户关联楼管理
	 * @param userId 
	 * @param xx 
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "/userFloor/deleteUserFloor.json")
	@ResponseBody	
	public UserFloorDeleteDTO deleteUserFloor(Integer xx, HttpServletRequest request){
		UserFloorDeleteDTO userFloorDeleteDTO = new UserFloorDeleteDTO();
		if(validateDeleteUserFloor(userFloorDeleteDTO,xx)){
			// userFloorMng.update(bean, xx);
			userFloorDeleteDTO.setState(BaseDTO.BaseDTOEnum.API_STATUS_SUCCESS);
		}
		return userFloorDeleteDTO;
	}
	
	/**
	 * 校验删除用户关联楼管理删除接口
	 * @param baseDTO
	 * @param userId 用户id
	 * @return
	 */
	private Boolean validateDeleteUserFloor(BaseDTO baseDTO, Integer userId) {
		if (cmsUserMng.findById(userId)  == null) {
			baseDTO.setState(BaseDTO.BaseDTOEnum.API_MESSAGE_USER_NOT_FOUND);
			return false;			
		}
		return true;
	}

	/**
	 * 用户关联楼管理详情
	 * @param userId 
	 * @param userFloorId 用户关联楼管理Id
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "/userFloor/detailUserFloor.json")
	@ResponseBody
	public UserFloorDetailDTO detailUserFloor(Integer userFloorId, HttpServletRequest request){
		UserFloorDetailDTO userFloorDetailDTO = new UserFloorDetailDTO();
		if(validateDetailUserFloor(userFloorDetailDTO, getUserId(), userFloorId)){
			// userFloorMng.findById(userFloorId);
			userFloorDetailDTO.setState(BaseDTO.BaseDTOEnum.API_STATUS_SUCCESS);
		}
		return userFloorDetailDTO;
	}
		
	/**
	 * 校验用户关联楼管理详情接口
	 * @param baseDTO
	 * @param userId 用户id
	 * @param userFloorId 用户关联楼管理Id
	 * @return
	 */
	private Boolean validateDetailUserFloor(BaseDTO baseDTO, Integer userId, Integer userFloorId) {
		if (cmsUserMng.findById(userId)  == null) {
			baseDTO.setState(BaseDTO.BaseDTOEnum.API_MESSAGE_USER_NOT_FOUND);
			return false;			
		}
		if (userFloorMng.findById(userFloorId) == null) {
			baseDTO.setState(UserFloorDetailDTO.UserFloorDetailDTOEnum.IS_NOT_EXIST);
			return false;
		}
		return true;
	}


	/**
	 * 校验查询用户关联楼管理列表接口
	 * @param baseDTO
	 * @param userId 用户id
	 * @param userFloorModel 用户关联楼管理查询条件
	 * @return
	 */
	private Boolean validateQueryUserFloorByModel(BaseDTO baseDTO, Integer userId, UserFloorModel userFloorModel) {
		if (cmsUserMng.findById(userId)  == null) {
			baseDTO.setState(BaseDTO.BaseDTOEnum.API_MESSAGE_USER_NOT_FOUND);
			return false;			
		}
		return true;
	}

}

