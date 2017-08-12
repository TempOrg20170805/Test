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
 * 文 件 名 : MachineController.java
 * 创 建 人： 金明明
 * 日 期：2017-7-31
 * 修 改 人： 
 * 日 期： 
 * 描 述：洗衣机 Controller层
 */
@Controller
public class MachineController extends BaseController{

	@Autowired
	private MachineMng machineMng;
	@Autowired
	private CmsUserMng cmsUserMng;
	@Autowired
	private FloorLayerMng floorLayerMng;
	/**
	 * 查询洗衣机管理列表
	 * @param userId 用户Id
	 * @param machineModel 洗衣机管理查询条件
	 * @param pageNo 当前页
	 * @param pageSize 每页数据量
	 * @return
	 */
	@RequestMapping("/machine/queryMachineByModel.json")
	@ResponseBody
	public MachineQueryDTO queryMachineByModel(MachineModel machineModel, Integer pageNo, Integer pageSize, HttpServletRequest request) {
		MachineQueryDTO machineQueryDTO = new MachineQueryDTO();
		if (validateQueryMachineByModel(machineQueryDTO, getUserId(), machineModel)) {
			// 代码：设置默认相关值
			Pagination pagination = machineMng.queryMachineByModel(machineModel, SimplePage.cpn(pageNo), pageSize);
			List<Machine> machines = (List<Machine>) pagination.getList();
					
			// 赋值洗衣机管理分页信息
			machineQueryDTO.setPageNo(pagination.getPageNo());
			machineQueryDTO.setPageSize(pagination.getPageSize());
			machineQueryDTO.setTotalCount(pagination.getTotalCount());

			// 赋值洗衣机管理必要信息信息
			List<MachineDTO> machineDTOs = new ArrayList<MachineDTO>(); 
			for (Machine machine : machines) {
				MachineDTO machineDTO = new MachineDTO();
				// 设置DTO 例如
				// machineDTO.setXX("XX");
				
				// 赋值商品列表
				machineDTOs.add(machineDTO);
			}
			machineQueryDTO.setMachineDTOs(machineDTOs);
			machineQueryDTO.setState(BaseDTO.BaseDTOEnum.API_STATUS_SUCCESS);
			
		}
		return machineQueryDTO;
	}
	
	/**
	 * 查询该楼层洗衣机列表
	 * @param userId 用户Id
	 * @param floorLayerId 楼层ID
	 * @param pageNo 当前页
	 * @param pageSize 每页数据量
	 * @return
	 */
	@RequestMapping("/machine/queryMachineByFloorId.json")
	@ResponseBody
	public MachineByFloorLayerQueryDTO queryMachineByFloorId(Integer floorLayerId, HttpServletRequest request) {
		MachineByFloorLayerQueryDTO machineQueryDTO = new MachineByFloorLayerQueryDTO();
		if (validateQueryMachineByFloorLayer(machineQueryDTO, getUserId(), floorLayerId)) {
			// 初始化查询条件
			MachineModel machineModel = new MachineModel();
			machineModel.setFloorLayerId(floorLayerId);
			// 代码：设置默认相关值
			Pagination pagination = machineMng.queryMachineByModel(machineModel, 1, Integer.MAX_VALUE);
			List<Machine> machines = (List<Machine>) pagination.getList();
					
			// 赋值洗衣机管理分页信息;
			machineQueryDTO.setTotalCount(pagination.getTotalCount());
			// 查询楼层
			FloorLayer floorLayer = floorLayerMng.findById(floorLayerId);
			machineQueryDTO.setFloorLayer(floorLayer);
			
			// 赋值洗衣机管理必要信息信息
			List<MachineByFloorLayerDTO> machineDTOs = new ArrayList<MachineByFloorLayerDTO>(); 
			for (Machine machine : machines) {
				MachineByFloorLayerDTO machineDTO = new MachineByFloorLayerDTO(machine);
				// 赋值商品列表
				machineDTOs.add(machineDTO);
			}
			machineQueryDTO.setMachineDTOs(machineDTOs);
			machineQueryDTO.setState(BaseDTO.BaseDTOEnum.API_STATUS_SUCCESS);
			
		}
		return machineQueryDTO;
	}

	/**
	 * 更新洗衣机管理
	 * @param userId 
	 * @param xx 
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "/machine/updateMachine.json")
	@ResponseBody	
	public MachineUpdateDTO updateMachine(HttpServletRequest request){
		MachineUpdateDTO machineUpdateDTO = new MachineUpdateDTO();
		if(validateUpdateMachine(machineUpdateDTO,getUserId())){
			machineUpdateDTO.setState(BaseDTO.BaseDTOEnum.API_STATUS_SUCCESS);
		}
		return machineUpdateDTO;
	}
	
	/**
	 * 校验更新洗衣机管理更新接口
	 * @param baseDTO
	 * @param userId 用户id
	 * @return
	 */
	private Boolean validateUpdateMachine(BaseDTO baseDTO, Integer userId) {
		if (cmsUserMng.findById(userId)  == null) {
			baseDTO.setState(BaseDTO.BaseDTOEnum.API_MESSAGE_USER_NOT_FOUND);
			return false;			
		}
		return true;
	}

	/**
	 * 删除洗衣机管理
	 * @param userId 
	 * @param xx 
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "/machine/deleteMachine.json")
	@ResponseBody	
	public MachineDeleteDTO deleteMachine(Integer machineId, HttpServletRequest request){
		MachineDeleteDTO machineDeleteDTO = new MachineDeleteDTO();
		if(validateDeleteMachine(machineDeleteDTO,machineId)){
			// machineMng.update(bean, xx);
			machineDeleteDTO.setState(BaseDTO.BaseDTOEnum.API_STATUS_SUCCESS);
		}
		return machineDeleteDTO;
	}
	
	/**
	 * 校验删除洗衣机管理删除接口
	 * @param baseDTO
	 * @param userId 用户id
	 * @return
	 */
	private Boolean validateDeleteMachine(BaseDTO baseDTO, Integer userId) {
		if (cmsUserMng.findById(userId)  == null) {
			baseDTO.setState(BaseDTO.BaseDTOEnum.API_MESSAGE_USER_NOT_FOUND);
			return false;			
		}
		return true;
	}

	/**
	 * 洗衣机管理详情
	 * @param userId 
	 * @param machineId 洗衣机管理Id
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "/machine/detailMachine.json")
	@ResponseBody
	public MachineDetailDTO detailMachine(Integer machineId, HttpServletRequest request){
		MachineDetailDTO machineDetailDTO = new MachineDetailDTO();
		if(validateDetailMachine(machineDetailDTO, getUserId(), machineId)){
			// machineMng.findById(machineId);
			machineDetailDTO.setState(BaseDTO.BaseDTOEnum.API_STATUS_SUCCESS);
		}
		return machineDetailDTO;
	}
		
	/**
	 * 校验洗衣机管理详情接口
	 * @param baseDTO
	 * @param userId 用户id
	 * @param machineId 洗衣机管理Id
	 * @return
	 */
	private Boolean validateDetailMachine(BaseDTO baseDTO, Integer userId, Integer machineId) {
		if (cmsUserMng.findById(userId)  == null) {
			baseDTO.setState(BaseDTO.BaseDTOEnum.API_MESSAGE_USER_NOT_FOUND);
			return false;			
		}
		if (machineMng.findById(machineId) == null) {
			baseDTO.setState(MachineDetailDTO.MachineDetailDTOEnum.IS_NOT_EXIST);
			return false;
		}
		return true;
	}


	/**
	 * 校验查询洗衣机管理列表接口
	 * @param baseDTO
	 * @param userId 用户id
	 * @param machineModel 洗衣机管理查询条件
	 * @return
	 */
	private Boolean validateQueryMachineByModel(BaseDTO baseDTO, Integer userId, MachineModel machineModel) {
		if (cmsUserMng.findById(userId)  == null) {
			baseDTO.setState(BaseDTO.BaseDTOEnum.API_MESSAGE_USER_NOT_FOUND);
			return false;			
		}
		return true;
	}
	
	/**
	 * 校验根据楼层ID查询洗衣机列表接口
	 * @param baseDTO
	 * @param userId 用户id
	 * @param floorLayer 楼层Id
	 * @return
	 */
	private Boolean validateQueryMachineByFloorLayer(BaseDTO baseDTO, Integer userId, Integer floorLayerId) {
		if (cmsUserMng.findById(userId)  == null) {
			baseDTO.setState(BaseDTO.BaseDTOEnum.API_MESSAGE_USER_NOT_FOUND);
			return false;			
		}
		if (floorLayerId == null) {
			baseDTO.setState(BaseDTO.BaseDTOEnum.API_MESSAGE_PARAM_NOT_NULL);
			return false;
		}
		FloorLayer floorLayer = floorLayerMng.findById(floorLayerId);
		if (floorLayer == null) {
			baseDTO.setState(BaseDTO.BaseDTOEnum.API_MESSAGE_VALIDATECODE_NOTEXIST);
			return false;
		}
		return true;
	}


}

