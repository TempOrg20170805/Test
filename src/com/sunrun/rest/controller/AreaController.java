package com.sunrun.rest.controller;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.jeecms.core.manager.CmsUserMng;
import com.sunrun.rest.dto.AreaDTO;
import com.sunrun.rest.dto.AreaListDTO;
import com.sunrun.rest.dto.BaseDTO.BaseDTOEnum;
import com.sunrun.washer.entity.Area;
import com.sunrun.washer.manager.AreaMng;

/**
 * 
 * @author zwy
 * @ClassName AreaController.java
 * @CreateDate 2017-6-22
 * @descrintion 地区和辖区相关的接口
 * @editor
 * @editDate
 */
@Controller
public class AreaController {
	@Autowired
	private CmsUserMng userMng;
	@Autowired
	private  AreaMng areaMng;
	
	

	
	
	
	
	//http://localhost:8080/Washer/rest/queryWasherArea.json
		/**
		 * 
		 * @CreateDate  2017-5-20
		 * @author wangcy
		 * @param id
		 * @param request查询省市区
		 * @param model
		 * @return
		 */
		@RequestMapping(value = "/queryWasherArea.json")
		@ResponseBody
		public AreaListDTO querySmartEvArea(Integer areaId,  HttpServletRequest request, ModelMap model) {
			AreaListDTO queryAreaDTO = new AreaListDTO();
			List<Area> areaList = areaMng.queryListByRoot(areaId==null?45062:areaId);

			List<AreaDTO> queryAreaInfoDTO = new ArrayList<AreaDTO>();
			for (Area area : areaList) {
				AreaDTO sa= new AreaDTO();
				sa.initArea(area);
				queryAreaInfoDTO.add(sa);
			}
			queryAreaDTO.setArealist(queryAreaInfoDTO);	
			queryAreaDTO.setTotalCount(areaList.size());
			queryAreaDTO.setState(BaseDTOEnum.API_STATUS_SUCCESS);
			return queryAreaDTO; 
		}
	
}
