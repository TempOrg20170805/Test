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
 * 文 件 名 : TestPushController.java
 * 创 建 人： 金明明
 * 日 期：2017-8-26
 * 修 改 人： 
 * 日 期： 
 * 描 述：测试推送 Controller层
 */
@Controller
public class TestPushController {

	@Autowired
	private TestPushMng testPushMng;
	@Autowired
	private CmsUserMng cmsUserMng;
	@Autowired
	private WasherOrderMng washerOrderMng;

	@Autowired
	private JpushBindingMng jpushBindingMng;
	
	/**
	 * 添加测试推送管理
	 * @param machineNo 洗衣机序列号
	 * @param registrationId 推送编号
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "/testPush/saveTestPush.json")
	@ResponseBody
	public TestPushSaveDTO saveTestPush(String machineNo, String registrationId, HttpServletRequest request){
		TestPushSaveDTO testPushSaveDTO = new TestPushSaveDTO();
		if(validateSaveTestPush(testPushSaveDTO,machineNo,registrationId)){
			TestPushModelSave testPushModelSave = new TestPushModelSave();
			testPushModelSave.setMachineNo(machineNo);
			testPushModelSave.setRegistrationId(registrationId);
			TestPush testPush = testPushMng.saveTestPush(testPushModelSave);
			testPushSaveDTO.setTestPushId(testPush.getTestPushId());
			testPushSaveDTO.setState(BaseDTO.BaseDTOEnum.API_STATUS_SUCCESS);
		}
		return testPushSaveDTO;
	}
	
	/**
	 * 校验查询测试推送管理保存接口
	 * @param baseDTO
	 * @param userId 用户id
	 * @return
	 */
	private Boolean validateSaveTestPush(BaseDTO baseDTO, String machineNo, String registrationId) {
		if (StringUtils.isBlank(machineNo) || StringUtils.isBlank(registrationId)) {
			baseDTO.setState(BaseDTO.BaseDTOEnum.API_MESSAGE_PARAM_NOT_NULL);
		}
		return true;
	}

	/**
	 * 删除测试推送管理
	 * @param testPushId 
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "/testPush/deleteTestPush.json")
	@ResponseBody	
	public TestPushDeleteDTO deleteTestPush(Integer testPushId, HttpServletRequest request){
		TestPushDeleteDTO testPushDeleteDTO = new TestPushDeleteDTO();
		if(validateDeleteTestPush(testPushDeleteDTO,testPushId)){
			testPushMng.deleteById(testPushId);
			testPushDeleteDTO.setState(BaseDTO.BaseDTOEnum.API_STATUS_SUCCESS);
		}
		return testPushDeleteDTO;
	}
	
	/**
	 * 校验删除测试推送管理删除接口
	 * @param baseDTO
	 * @param userId 用户id
	 * @return
	 */
	private Boolean validateDeleteTestPush(BaseDTO baseDTO, Integer testPushId) {
		if (testPushMng.findById(testPushId) == null) {
			baseDTO.setState(BaseDTO.BaseDTOEnum.API_MESSAGE_VALIDATECODE_NOTEXIST);
		}
		return true;
	}
	
	
	/**
	 * 测试控制推送
	 * @param machineNo 洗衣机序列号
	 * @param modeNo 模式编号
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "/testPush/testPushControl.json")
	@ResponseBody	
	public TestPushDeleteDTO testPushControl(String machineNo, Integer modeNo, HttpServletRequest request){
		TestPushDeleteDTO testPushDeleteDTO = new TestPushDeleteDTO();
		if(validateTestPushControlh(testPushDeleteDTO,machineNo,modeNo)){
			washerOrderMng.pushControl(machineNo, modeNo);
			testPushDeleteDTO.setState(BaseDTO.BaseDTOEnum.API_STATUS_SUCCESS);
		}
		return testPushDeleteDTO;
	}
	/**
	 * 校验删除测试推送管理删除接口
	 * @param baseDTO
	 * @param userId 用户id
	 * @return
	 */
	private Boolean validateTestPushControlh(BaseDTO baseDTO, String machineNo, Integer modeNo) {
		if (StringUtils.isBlank(machineNo) || StringUtils.isBlank(machineNo)) {
			baseDTO.setState(BaseDTO.BaseDTOEnum.API_MESSAGE_PARAM_NOT_NULL);
		}
		return true;
	}
	

}

