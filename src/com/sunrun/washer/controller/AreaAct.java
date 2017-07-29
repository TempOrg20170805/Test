package com.sunrun.washer.controller;

import static com.jeecms.common.page.SimplePage.cpn;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

import com.jeecms.common.page.Pagination;
import com.jeecms.common.web.CookieUtils;
import com.jeecms.core.manager.CmsLogMng;
import com.sunrun.washer.entity.Area;
import com.sunrun.washer.manager.AreaMng;
@Controller
public class AreaAct {
	private static final Logger log = LoggerFactory.getLogger(AreaAct.class);
	@Autowired
	private CmsLogMng cmsLogMng;
	@Autowired
	private AreaMng areaMng;
	
	
	@RequiresPermissions("area:v_list")
	@RequestMapping(value = "/area/v_list.do")
	public String list(String areaName,Integer areaId,HttpServletRequest request,Integer pageNo,ModelMap model){
		
		if(areaId == null){
			areaId =0;
		}
		
		Pagination pagination = areaMng.getPage(areaName,areaId,cpn(pageNo), CookieUtils.getPageSize(request));
		model.addAttribute("pagination", pagination);
		model.addAttribute("areaName", areaName);
		model.addAttribute("pageNo", pageNo);
		model.addAttribute("areaId", areaId);
		
		return "area/list";
	}
	
	@RequiresPermissions("area:v_add")
	@RequestMapping(value = "/area/v_add.do")
	public String add(String areaName,Integer areaId,HttpServletRequest request,Integer pageNo,ModelMap model){
		
		model.addAttribute("areaName", areaName);
		model.addAttribute("areaId", areaId);
		
		return "area/add";
	}
	
	@RequiresPermissions("area:o_save")
	@RequestMapping(value = "/area/o_save.do")
	public String save(Integer areaId,String areaName,Integer areaSort,Integer areaDeep,Boolean isDefault,
			HttpServletRequest request,Integer pageNo,ModelMap model){
		Area area = areaMng.findById(areaId);
		try {
			Area bean = areaMng.save(area, areaName, 0, areaDeep, isDefault);
			log.info("add Area id={}", bean.getAreaName());
			cmsLogMng.operating(request, "Area.log.add", "areaName="+ bean.getAreaName() );

		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return "redirect:v_list.do";
	}
	
	
	@RequiresPermissions("area:o_delete")
	@RequestMapping(value = "/area/o_delete.do")
	public String delete(Integer areaId,HttpServletRequest request,Integer pageNo,ModelMap model){
		try {
			Area bean = areaMng.delete(areaId);
			log.info("delete Area id={}", bean.getAreaName());
			cmsLogMng.operating(request, "Area.log.delete", "areaName="+ bean.getAreaName() );

		} catch (Exception e) {
			e.printStackTrace();
		}
		model.addAttribute("pageNo", pageNo);
		return "redirect:v_list.do";
	}
	
	//区域ajax联动查询
	@RequestMapping(value="/area/query_area_ajax.do")
	public void queryAreaList(Integer parentId, HttpServletRequest request, HttpServletResponse response, ModelMap model) {
		// 获取区域列表
		List<Area> areas = areaMng.findByPareantId(parentId);
		response.setContentType("text/json; charset=utf-8");
		PrintWriter out = null;
		try {
			out = response.getWriter();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		// 转换为json
		JSONArray array = new JSONArray();
		JSONObject member = null;
		try {
			for (Area area : areas) {
				member = new JSONObject();
				member.put("areaName", area.getAreaName());
				member.put("areaId", area.getAreaId());
				array.put(member);
			}
		} catch (JSONException e) {
			e.printStackTrace();
		}
		
		// 输出json
		out.print(array.toString());
	}
	
	
}