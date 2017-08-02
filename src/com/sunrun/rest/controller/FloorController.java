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
 * 文 件 名 : FloorController.java
 * 创 建 人： 金明明
 * 日 期：2017-8-1
 * 修 改 人： 
 * 日 期： 
 * 描 述：楼 Controller层
 */
@Controller
public class FloorController extends BaseController{

	@Autowired
	private FloorMng floorMng;
	@Autowired
	private CmsUserMng cmsUserMng;

	/**
	 * 查询楼管理列表
	 * @param userId 用户Id
	 * @param floorModel 楼管理查询条件
	 * @param pageNo 当前页
	 * @param pageSize 每页数据量
	 * @return
	 */
	@RequestMapping("/floor/queryFloorByModel.json")
	@ResponseBody
	public FloorQueryDTO queryFloorByModel(FloorModel floorModel, Integer pageNo, Integer pageSize, HttpServletRequest request) {
		FloorQueryDTO floorQueryDTO = new FloorQueryDTO();
		if (validateQueryFloorByModel(floorQueryDTO, getUserId(), floorModel)) {
			// 代码：设置默认相关值
			Pagination pagination = floorMng.queryFloorByModel(floorModel, SimplePage.cpn(pageNo), pageSize);
			List<Floor> floors = (List<Floor>) pagination.getList();
					
			// 赋值楼管理分页信息
			floorQueryDTO.setPageNo(pagination.getPageNo());
			floorQueryDTO.setPageSize(pagination.getPageSize());
			floorQueryDTO.setTotalCount(pagination.getTotalCount());

			// 赋值楼管理必要信息信息
			List<FloorDTO> floorDTOs = new ArrayList<FloorDTO>(); 
			for (Floor floor : floors) {
				FloorDTO floorDTO = new FloorDTO();
				// 设置DTO 例如
				// floorDTO.setXX("XX");
				
				// 赋值商品列表
				floorDTOs.add(floorDTO);
			}
			floorQueryDTO.setFloorDTOs(floorDTOs);
			floorQueryDTO.setState(BaseDTO.BaseDTOEnum.API_STATUS_SUCCESS);
			
		}
		return floorQueryDTO;
	}

	/**
	 * 添加楼管理
	 * @param userId = customerId
	 * @param XX
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "/floor/saveFloor.json")
	@ResponseBody
	public FloorSaveDTO saveFloor(Integer xx, HttpServletRequest request){
		FloorSaveDTO floorSaveDTO = new FloorSaveDTO();
		if(validateSaveFloor(floorSaveDTO,xx)){
			// floorMng.save(bean, xx);
			floorSaveDTO.setState(BaseDTO.BaseDTOEnum.API_STATUS_SUCCESS);
		}
		return floorSaveDTO;
	}
	
	/**
	 * 校验查询楼管理保存接口
	 * @param baseDTO
	 * @param userId 用户id
	 * @return
	 */
	private Boolean validateSaveFloor(BaseDTO baseDTO, Integer userId) {
		if (cmsUserMng.findById(userId)  == null) {
			baseDTO.setState(BaseDTO.BaseDTOEnum.API_STATUS_SUCCESS);
			return false;			
		}
		return true;
	}

	/**
	 * 更新楼管理
	 * @param userId 
	 * @param xx 
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "/floor/updateFloor.json")
	@ResponseBody	
	public FloorUpdateDTO updateFloor(HttpServletRequest request){
		FloorUpdateDTO floorUpdateDTO = new FloorUpdateDTO();
		if(validateUpdateFloor(floorUpdateDTO,getUserId())){
			floorUpdateDTO.setState(BaseDTO.BaseDTOEnum.API_STATUS_SUCCESS);
		}
		return floorUpdateDTO;
	}
	
	/**
	 * 校验更新楼管理更新接口
	 * @param baseDTO
	 * @param userId 用户id
	 * @return
	 */
	private Boolean validateUpdateFloor(BaseDTO baseDTO, Integer userId) {
		if (cmsUserMng.findById(userId)  == null) {
			baseDTO.setState(BaseDTO.BaseDTOEnum.API_STATUS_SUCCESS);
			return false;			
		}
		return true;
	}

	/**
	 * 删除楼管理
	 * @param userId 
	 * @param xx 
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "/floor/deleteFloor.json")
	@ResponseBody	
	public FloorDeleteDTO deleteFloor(Integer xx, HttpServletRequest request){
		FloorDeleteDTO floorDeleteDTO = new FloorDeleteDTO();
		if(validateDeleteFloor(floorDeleteDTO,xx)){
			// floorMng.update(bean, xx);
			floorDeleteDTO.setState(BaseDTO.BaseDTOEnum.API_STATUS_SUCCESS);
		}
		return floorDeleteDTO;
	}
	
	/**
	 * 校验删除楼管理删除接口
	 * @param baseDTO
	 * @param userId 用户id
	 * @return
	 */
	private Boolean validateDeleteFloor(BaseDTO baseDTO, Integer userId) {
		if (cmsUserMng.findById(userId)  == null) {
			baseDTO.setState(BaseDTO.BaseDTOEnum.API_STATUS_SUCCESS);
			return false;			
		}
		return true;
	}

	/**
	 * 楼管理详情
	 * @param userId 
	 * @param floorId 楼管理Id
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "/floor/detailFloor.json")
	@ResponseBody
	public FloorDetailDTO detailFloor(Integer floorId, HttpServletRequest request){
		FloorDetailDTO floorDetailDTO = new FloorDetailDTO();
		if(validateDetailFloor(floorDetailDTO, getUserId(), floorId)){
			// floorMng.findById(floorId);
			floorDetailDTO.setState(BaseDTO.BaseDTOEnum.API_STATUS_SUCCESS);
		}
		return floorDetailDTO;
	}
		
	/**
	 * 校验楼管理详情接口
	 * @param baseDTO
	 * @param userId 用户id
	 * @param floorId 楼管理Id
	 * @return
	 */
	private Boolean validateDetailFloor(BaseDTO baseDTO, Integer userId, Integer floorId) {
		if (cmsUserMng.findById(userId)  == null) {
			baseDTO.setState(BaseDTO.BaseDTOEnum.API_STATUS_SUCCESS);
			return false;			
		}
		if (floorMng.findById(floorId) == null) {
			baseDTO.setState(FloorDetailDTO.FloorDetailDTOEnum.IS_NOT_EXIST);
			return false;
		}
		return true;
	}


	/**
	 * 校验查询楼管理列表接口
	 * @param baseDTO
	 * @param userId 用户id
	 * @param floorModel 楼管理查询条件
	 * @return
	 */
	private Boolean validateQueryFloorByModel(BaseDTO baseDTO, Integer userId, FloorModel floorModel) {
		if (cmsUserMng.findById(userId)  == null) {
			baseDTO.setState(BaseDTO.BaseDTOEnum.API_STATUS_SUCCESS);
			return false;			
		}
		return true;
	}

}

