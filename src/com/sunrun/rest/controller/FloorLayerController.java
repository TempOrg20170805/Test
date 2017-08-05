package com.sunrun.rest.controller;
import com.sunrun.rest.dto.*;import com.sunrun.washer.manager.*;import com.sunrun.washer.entity.*;import com.sunrun.washer.model.*;
import com.jeecms.core.manager.*;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import com.jeecms.common.page.Pagination;
import com.jeecms.common.page.SimplePage;

import java.util.ArrayList;
import java.util.List;

/**
 * 文 件 名 : FloorLayerController.java
 * 创 建 人： 金明明
 * 日 期：2017-7-30
 * 修 改 人： 
 * 日 期： 
 * 描 述：楼层 Controller层
 */
@Controller
public class FloorLayerController extends BaseController{

	@Autowired
	private FloorLayerMng floorLayerMng;
	@Autowired
	private CmsUserMng cmsUserMng;
	@Autowired
	private FloorMng floorMng;
	@Autowired
	private MachineMng machineMng;

	/**
	 * 查询楼层管理列表
	 * @param userId 用户Id
	 * @param floorLayerModel 楼层管理查询条件
	 * @param pageNo 当前页
	 * @param pageSize 每页数据量
	 * @return
	 */
	@RequestMapping("/floorLayer/queryFloorLayerByModel.json")
	@ResponseBody
	public FloorLayerQueryDTO queryFloorLayerByModel(FloorLayerModel floorLayerModel, Integer pageNo, Integer pageSize, HttpServletRequest request) {
		FloorLayerQueryDTO floorLayerQueryDTO = new FloorLayerQueryDTO();
		if (validateQueryFloorLayerByModel(floorLayerQueryDTO, getUserId(), floorLayerModel)) {
			// 代码：设置默认相关值
			Pagination pagination = floorLayerMng.queryFloorLayerByModel(floorLayerModel, SimplePage.cpn(pageNo), pageSize);
			List<FloorLayer> floorLayers = (List<FloorLayer>) pagination.getList();
					
			// 赋值楼层管理分页信息
			floorLayerQueryDTO.setPageNo(pagination.getPageNo());
			floorLayerQueryDTO.setPageSize(pagination.getPageSize());
			floorLayerQueryDTO.setTotalCount(pagination.getTotalCount());
			floorLayerQueryDTO.setFloorId(floorLayerModel.getFloorId());
			// 赋值楼层管理必要信息信息
			List<FloorLayerDTO> floorLayerDTOs = new ArrayList<FloorLayerDTO>(); 
			for (FloorLayer floorLayer : floorLayers) {
				FloorLayerDTO floorLayerDTO = new FloorLayerDTO(floorLayer);
				List<Machine> machines = machineMng.queryMachineByFloorLayer(floorLayer.getFloorLayerId());
				if (machines != null && machines.size() > 0) {
					floorLayerDTO.setMachineCount(machines.size());
				}
				// 赋值商品列表
				floorLayerDTOs.add(floorLayerDTO);
			}
			floorLayerQueryDTO.setFloorLayerDTOs(floorLayerDTOs);
			floorLayerQueryDTO.setState(BaseDTO.BaseDTOEnum.API_STATUS_SUCCESS);
			
		}
		return floorLayerQueryDTO;
	}

	/**
	 * 添加楼层管理
	 * @param name 楼层名
	 * @param layer 第几层
	 * @param layerX 楼层坐标x
	 * @param layerY 楼层坐标y
	 * @param floorId 楼Id
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "/floorLayer/saveFloorLayer.json")
	@ResponseBody
	public FloorLayerSaveDTO saveFloorLayer(String name,Integer layer,Integer layerX,Integer layerY,Integer floorId, HttpServletRequest request){
		FloorLayerSaveDTO floorLayerSaveDTO = new FloorLayerSaveDTO();
		if(validateSaveFloorLayer(floorLayerSaveDTO, getUserId(), name, layer, layerX, layerY, floorId)){
			FloorLayerModelSave floorLayerModelSave = new FloorLayerModelSave(name, layer, layerX, layerY, floorId);
			floorLayerMng.saveFloorLayer(floorLayerModelSave);
			floorLayerSaveDTO.setState(BaseDTO.BaseDTOEnum.API_STATUS_SUCCESS);
		}
		return floorLayerSaveDTO;
	}
	
	/**
	 * 校验查询楼层管理保存接口
	 * @param baseDTO
	 * @param userId 用户id
	 * @return
	 */
	private Boolean validateSaveFloorLayer(BaseDTO baseDTO, Integer userId, String name,Integer layer,Integer layerX,Integer layerY,Integer floorId) {
		if (cmsUserMng.findById(userId)  == null) {
			baseDTO.setState(BaseDTO.BaseDTOEnum.API_MESSAGE_USER_NOT_FOUND);
			return false;			
		}
		if (StringUtils.isBlank(name) || layer == null || layerX == null || layerY ==null || floorId == null) {
			baseDTO.setState(BaseDTO.BaseDTOEnum.API_MESSAGE_PARAM_REQUIRED);
			return false;
		}
		Floor floor = floorMng.findById(floorId);
		if (floor == null) {
			baseDTO.setState(BaseDTO.BaseDTOEnum.API_MESSAGE_VALIDATECODE_NOTEXIST);
			return false;
		}
		return true;
	}

	/**
	 * 更新楼层管理
	 * @param userId 
	 * @param xx 
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "/floorLayer/updateFloorLayer.json")
	@ResponseBody	
	public FloorLayerUpdateDTO updateFloorLayer(HttpServletRequest request){
		FloorLayerUpdateDTO floorLayerUpdateDTO = new FloorLayerUpdateDTO();
		if(validateUpdateFloorLayer(floorLayerUpdateDTO,getUserId())){
			floorLayerUpdateDTO.setState(BaseDTO.BaseDTOEnum.API_STATUS_SUCCESS);
		}
		return floorLayerUpdateDTO;
	}
	
	/**
	 * 校验更新楼层管理更新接口
	 * @param baseDTO
	 * @param userId 用户id
	 * @return
	 */
	private Boolean validateUpdateFloorLayer(BaseDTO baseDTO, Integer userId) {
		if (cmsUserMng.findById(userId)  == null) {
			baseDTO.setState(BaseDTO.BaseDTOEnum.API_MESSAGE_USER_NOT_FOUND);
			return false;			
		}
		return true;
	}

	/**
	 * 删除楼层
	 * @param userId 
	 * @param floorLayerId
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "/floorLayer/deleteFloorLayer.json")
	@ResponseBody	
	public FloorLayerDeleteDTO deleteFloorLayer(Integer floorLayerId, HttpServletRequest request){
		FloorLayerDeleteDTO floorLayerDeleteDTO = new FloorLayerDeleteDTO();
		if(validateDeleteFloorLayer(floorLayerDeleteDTO,getUserId(), floorLayerId)){
			// 删除楼层，同时删除该楼层的所有洗衣机楼层关联数据
			floorLayerMng.deleteById(floorLayerId);
			floorLayerDeleteDTO.setState(BaseDTO.BaseDTOEnum.API_STATUS_SUCCESS);
		}
		return floorLayerDeleteDTO;
	}
	
	/**
	 * 校验删除楼层管理删除接口
	 * @param baseDTO
	 * @param userId 用户id
	 * @return
	 */
	private Boolean validateDeleteFloorLayer(BaseDTO baseDTO, Integer userId, Integer floorLayerId) {
		if (cmsUserMng.findById(userId)  == null) {
			baseDTO.setState(BaseDTO.BaseDTOEnum.API_MESSAGE_USER_NOT_FOUND);
			return false;			
		}
		return true;
	}

	/**
	 * 楼层管理详情
	 * @param userId 
	 * @param floorLayerId 楼层管理Id
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "/floorLayer/detailFloorLayer.json")
	@ResponseBody
	public FloorLayerDetailDTO detailFloorLayer(Integer floorLayerId, HttpServletRequest request){
		FloorLayerDetailDTO floorLayerDetailDTO = new FloorLayerDetailDTO();
		if(validateDetailFloorLayer(floorLayerDetailDTO, getUserId(), floorLayerId)){
			// floorLayerMng.findById(floorLayerId);
			floorLayerDetailDTO.setState(BaseDTO.BaseDTOEnum.API_STATUS_SUCCESS);
		}
		return floorLayerDetailDTO;
	}
		
	/**
	 * 校验楼层管理详情接口
	 * @param baseDTO
	 * @param userId 用户id
	 * @param floorLayerId 楼层管理Id
	 * @return
	 */
	private Boolean validateDetailFloorLayer(BaseDTO baseDTO, Integer userId, Integer floorLayerId) {
		if (cmsUserMng.findById(userId)  == null) {
			baseDTO.setState(BaseDTO.BaseDTOEnum.API_MESSAGE_USER_NOT_FOUND);
			return false;			
		}
		if (floorLayerMng.findById(floorLayerId) == null) {
			baseDTO.setState(FloorLayerDetailDTO.FloorLayerDetailDTOEnum.IS_NOT_EXIST);
			return false;
		}
		return true;
	}


	/**
	 * 校验查询楼层管理列表接口
	 * @param baseDTO
	 * @param userId 用户id
	 * @param floorLayerModel 楼层管理查询条件
	 * @return
	 */
	private Boolean validateQueryFloorLayerByModel(BaseDTO baseDTO, Integer userId, FloorLayerModel floorLayerModel) {
		if (cmsUserMng.findById(userId)  == null) {
			baseDTO.setState(BaseDTO.BaseDTOEnum.API_MESSAGE_USER_NOT_FOUND);
			return false;			
		}
		return true;
	}

}

