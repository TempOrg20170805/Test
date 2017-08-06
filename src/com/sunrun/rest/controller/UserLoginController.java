package com.sunrun.rest.controller;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.util.Map;
import java.util.Properties;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;

import net.sf.ehcache.util.FindBugsSuppressWarnings;

import org.apache.commons.lang.RandomStringUtils;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.jeecms.common.security.BadCredentialsException;
import com.jeecms.common.util.Num62;
import com.jeecms.common.web.RequestUtils;
import com.jeecms.core.entity.CmsGroup;
import com.jeecms.core.entity.CmsGroup.CmsGroupEnum;
import com.jeecms.core.entity.CmsUser;
import com.jeecms.core.entity.CmsUserExt;
import com.jeecms.core.entity.UnifiedUser;
import com.jeecms.core.manager.CmsUserMng;
import com.jeecms.core.manager.UnifiedUserMng;
import com.sunrun.common.util.HttpRequest;
import com.sunrun.common.util.LoadProperties;

import com.sunrun.rest.dto.BaseDTO;
import com.sunrun.rest.dto.BaseDTO.BaseDTOEnum;
import com.sunrun.rest.dto.LoginMsgDTO;
import com.sunrun.rest.dto.RegisterDTO;
import com.sunrun.washer.entity.JpushBinding;
import com.sunrun.washer.manager.JpushBindingMng;
/**
 * 
 * @author wangcy
 * @ClassName UserLoginController.java
 * @CreateDate  2017-6-13
 * @descrintion   用户登陆、注册
 * @editor 
 * @editDate
 */
@Controller
public class UserLoginController {
	@Autowired
	private CmsUserMng cmsUserMng;
	@Autowired
	private UnifiedUserMng unifiedUserMng;
	@Autowired
	private JpushBindingMng jpushBindingMng;

	/**
	 * 登录
	 * @param longType 登陆类型：1.渠道端 2.普通端
	 * @param username 用户名
	 * @param password 密码
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "/user/login.json")
	@ResponseBody
	public BaseDTO userLogin(Integer longType,String username, JpushBinding jpushBean,String password, HttpServletRequest request) {
		LoginMsgDTO loginMsgDTO = new LoginMsgDTO();
		if (validateLogin(loginMsgDTO, username, password,longType)) {
			try {
				// 基本信息设置
				String ip = RequestUtils.getIpAddr(request);	
				// 登录
				unifiedUserMng.login(username, password, ip);
				// 登录结果传递
				CmsUser cmsUser = cmsUserMng.findByUsername(username);
				// 校验登录者是否有效
				if (validateLoginIsUsed(loginMsgDTO, cmsUser,longType)) {
					loginMsgDTO.initCmsUserDTO(cmsUser);
					//设备添加后进行推送绑定
					jpushBean.setUserName(username);//获取注册名
					JpushBinding push = jpushBindingMng.saveJpushBinding(jpushBean, cmsUser.getGroup().getId(), null, request);
					loginMsgDTO.setState(BaseDTOEnum.API_STATUS_SUCCESS);
				}
			} catch (BadCredentialsException e) {
				if (e.getMessage().equals("password invalid")) {
					loginMsgDTO.setState(BaseDTOEnum.API_MESSAGE_USERNAME_PASSWORD_ERROR);
				}
				else if (e.getMessage().equals("account not activated")) {
					loginMsgDTO.setState(BaseDTOEnum.API_MESSAGE_USER_NOT_ACTIVATED);
				}
				else {
					e.printStackTrace();
				}	
			}
		}
		return loginMsgDTO;
	}
	
	/**
	 * 注册
	 * @param username 用户名
	 * @param password 密码
	 * @param validateCode 验证码
	 * @return
	 */
	@RequestMapping(value = "/user/register.json")
	@ResponseBody
	public RegisterDTO userRegister(String username, String password, String validateCode, HttpServletRequest request) {
		RegisterDTO registerDTO = new RegisterDTO();
		
		if (validateRegister(registerDTO, username, password, validateCode, request)) {
			// 设置注册初始值
			String ip = RequestUtils.getIpAddr(request);
			CmsUserExt cmsUserExt = new CmsUserExt();
			cmsUserExt.setMobile(username);
			Map<String,String> attrs = RequestUtils.getRequestMap(request, "attr_");
			cmsUserMng.registerAppMember(username, password, CmsGroupEnum.NORMAL.getValue(), ip, cmsUserExt, attrs);
			registerDTO.setState(BaseDTOEnum.API_STATUS_SUCCESS);
			CmsUser cmsUser = cmsUserMng.findByUsername(username);
			if(cmsUser!=null){
				registerDTO.initRegisterCmsUserDTO(cmsUser);
			}
		}
		
		return registerDTO;
	}
	/**
	 * 验证码验证接口
	 * @param username 用户名（手机号）
	 * @param validateCode 验证码
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "/user/codeValidate.json")
	@ResponseBody
	public BaseDTO codeValidate(String username, String validateCode, HttpServletRequest request) {
		BaseDTO codeValidateMsgDTO = new BaseDTO();
		if (validateCodeValidate(codeValidateMsgDTO, username, validateCode, request)) {
			codeValidateMsgDTO.setState(BaseDTOEnum.API_STATUS_SUCCESS);
		}
		return codeValidateMsgDTO;
	}
	
	/**
	 * 获取注册短信
	 * @param phone 电话号码
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "/user/getRegisterSMS.json")
	@ResponseBody
	public BaseDTO getRegisterSMS(String phone, HttpServletRequest request) {
		BaseDTO baseDTO = new BaseDTO();
		if (validateRegisterSMS(baseDTO, phone)) {
			String validateCode = RandomStringUtils.random(4, Num62.N10_CHARS);
			Properties pro = LoadProperties.getPropertiesObj(request);
			ServletContext application = request.getSession().getServletContext();
			application.setAttribute(phone, validateCode);
			String content = "【智能洗衣机】提交了手机注册请求"+"。"+"4位验证码为" + validateCode + "。" +"如没有提交请求请忽略"+"。";
			System.out.println(validateCode);
			HttpRequest.sendPost(pro.getProperty("sms.gateway"),"userid="+pro.getProperty("sms.userid")+"&account="+pro.getProperty("sms.account")+"&password="+pro.getProperty("sms.password")+"&mobile="+phone+"&content="+content+"&action="+pro.getProperty("sms.action")+"&sendTime="+pro.getProperty("sms.sendTime")+"&extno="+pro.getProperty("sms.extno"));
			baseDTO.setState(BaseDTOEnum.API_STATUS_SUCCESS);
		}
		return baseDTO;
	}
	
	/**
	 * 忘记密码-修改
	 * @param username 用户名
	 * @param newpassword 密码
	 * @param validateCode 验证码
	 * @return
	 */
	@RequestMapping(value = "/user/forget.json")
	@ResponseBody
	public BaseDTO forget(String username, String newpassword,String validateCode, HttpServletRequest request) {
		BaseDTO forgetDTO = new BaseDTO();	
		if (validateForget(forgetDTO, username, newpassword, validateCode, request)) {
			UnifiedUser unifiedUser = unifiedUserMng.getByUsername(username);
			Integer id=unifiedUser.getId();
			unifiedUserMng.update(id, newpassword, null);			
			forgetDTO.setState(BaseDTOEnum.API_STATUS_SUCCESS);
		}
		
		return forgetDTO;
	}
	
	/**
	 * 修改密码
	 * @param username 用户名
	 * @param newpassword 密码
	 * @param validateCode 验证码
	 * @return
	 */
	@RequestMapping(value = "/user/editPassword.json")
	@ResponseBody
	public BaseDTO editPassword(String username, String password,String newpassword,HttpServletRequest request) {
		BaseDTO forgetDTO = new BaseDTO();	
		if (validateAdminForget(forgetDTO, username, password,newpassword, request)) {
			try {
				UnifiedUser unifiedUser = unifiedUserMng.getByUsername(username);
				Integer id=unifiedUser.getId();
				unifiedUserMng.updateAPP(id, password, newpassword);			
				forgetDTO.setState(BaseDTOEnum.API_STATUS_SUCCESS);
			} catch (BadCredentialsException e) {
				if (e.getMessage().equals("password invalid")) {
					forgetDTO.setState(BaseDTOEnum.API_MESSAGE_PASSWORD_ERROR);
				}
				else if (e.getMessage().equals("account not activated")) {
					forgetDTO.setState(BaseDTOEnum.API_MESSAGE_USER_NOT_ACTIVATED);
				}
				else {
					e.printStackTrace();
				}	
			}
			
		}
		
		return forgetDTO;
	}
	/**
	 * 获取忘记密码短信
	 * @param phone 电话号码
	 * @param request
	 * @return
	 * @throws UnsupportedEncodingException 
	 */
	@RequestMapping(value = "/user/getForgetSMS.json")
	@ResponseBody
	public BaseDTO getForgetSMS(String phone,HttpServletRequest request) throws UnsupportedEncodingException {
		BaseDTO baseDTO = new BaseDTO();
		if (validateForgetSMS(baseDTO, phone)) {
			String validateCode = RandomStringUtils.random(4, Num62.N10_CHARS);
			Properties pro = LoadProperties.getPropertiesObj(request);
			ServletContext application = request.getSession().getServletContext();
			application.setAttribute(phone, validateCode);
			String content = "【智能洗衣机】提交了手机忘记密码请求"+"。"+"4位验证码为" + validateCode + "。" +"如没有提交请求请忽略"+"。";
			String newContent = URLDecoder.decode(content, "UTF-8");
			System.out.println(validateCode);
			HttpRequest.sendPost(pro.getProperty("sms.gateway"),"userid="+pro.getProperty("sms.userid")+"&account="+pro.getProperty("sms.account")+"&password="+pro.getProperty("sms.password")+"&mobile="+phone+"&content="+newContent+"&action="+pro.getProperty("sms.action")+"&sendTime="+pro.getProperty("sms.sendTime")+"&extno="+pro.getProperty("sms.extno"));
			baseDTO.setState(BaseDTOEnum.API_STATUS_SUCCESS);
		}
		return baseDTO;
	}
	/**
	 * 校验注册短信
	 * @param baseDTO
	 * @param phone
	 * @return
	 */
	private Boolean validateRegisterSMS(BaseDTO baseDTO, String phone) {
		if (StringUtils.isBlank(phone)) {
			baseDTO.setState(BaseDTOEnum.API_MESSAGE_PARAM_NOT_NULL);
			return false;
		}
		else if (!cmsUserMng.usernameNotExist(phone)) {
			baseDTO.setState(BaseDTOEnum.API_MESSAGE_USERNAME_EXIST);
			return false;
		}
		return true;
	}
	
	/**
	 * 验证码校验
	 * @param baseDTO
	 * @param username 用户名（手机号）
	 * @param validateCode 验证码
	 * @param request
	 * @return
	 */
	private Boolean validateCodeValidate(BaseDTO baseDTO, String username, String validateCode, HttpServletRequest request) {
		ServletContext application = request.getSession().getServletContext();
		if (StringUtils.isBlank(username) || StringUtils.isBlank(validateCode)) {
			baseDTO.setState(BaseDTOEnum.API_MESSAGE_PARAM_NOT_NULL);
			return false;
		}
		else if(application.getAttribute(username) == null) {
			baseDTO.setState(BaseDTOEnum.API_MESSAGE_VALIDATECODE_NOTEXIST);
			return false;
		}
		else if(!application.getAttribute(username).toString().equals(validateCode)) {
			baseDTO.setState(BaseDTOEnum.API_MESSAGE_VALIDATECODE_ERROR);
			return false;
		}
		return true;
	}
	
	/**
	 * 校验注册
	 * @param baseDTO
	 * @param username 用户名
	 * @param password 密码
	 * @param validateCode 校验码
	 * @param request
	 * @return
	 */
	private Boolean validateRegister(BaseDTO baseDTO, String username, String password, String validateCode, HttpServletRequest request) {
		ServletContext application = request.getSession().getServletContext();
		if (StringUtils.isBlank(username) || StringUtils.isBlank(password)|| StringUtils.isBlank(validateCode)) {
			baseDTO.setState(BaseDTOEnum.API_MESSAGE_PARAM_NOT_NULL);
			return false;
		}
		else if (!cmsUserMng.usernameNotExist(username)) {
			baseDTO.setState(BaseDTOEnum.API_MESSAGE_USERNAME_EXIST);
			return false;
		}
		else if(application.getAttribute(username) == null) {
			baseDTO.setState(BaseDTOEnum.API_MESSAGE_VALIDATECODE_NOTEXIST);
			return false;
		}
		else if(!application.getAttribute(username).toString().equals(validateCode)) {
			baseDTO.setState(BaseDTOEnum.API_MESSAGE_VALIDATECODE_ERROR);
			return false;
		}
		application.removeAttribute(username);
		return true;
	}
	
	/**
	 * 登录数据校验
	 * @param baseDTO 
	 * @param username 用户名
	 * @param password 密码
	 * @return
	 */
	private Boolean validateLogin(BaseDTO baseDTO, String username, String password,Integer longType) {
		if (StringUtils.isBlank(username) || StringUtils.isBlank(password)||longType==null) {
			baseDTO.setState(BaseDTOEnum.API_MESSAGE_PARAM_NOT_NULL);
			return false;
		}else if(cmsUserMng.findByUsername(username)==null){
			baseDTO.setState(BaseDTOEnum.API_MESSAGE_USER_NOT_FOUND);
			return false;
		}
		CmsUser cmsUser = cmsUserMng.findByUsername(username);
		if (cmsUser.getGroup() == null) {
			baseDTO.setState(LoginMsgDTO.LoginMsgDTOEnum.GROUP_NULL);
			return false;
		} else if (CmsGroupEnum.NORMAL.getValue().equals(cmsUser.getGroup().getId()) &&!cmsUser.getGroup().getId().equals(longType)){
			// 普通端用户只能登录普通端， 渠道端可登录普通端与渠道端
			baseDTO.setState(LoginMsgDTO.LoginMsgDTOEnum.GROUP_ERROR);
			return false;
		}
		return true;
	}
	/**
	 * 校验登录者信息
	 * @param baseDTO
	 * @param cmsUser
	 * @return
	 */
	private Boolean validateLoginIsUsed(BaseDTO baseDTO, CmsUser cmsUser,Integer longType) {
		CmsGroup cmsGroup = cmsUser.getGroup();
		
		if (longType!=cmsGroup.getId()) {
			baseDTO.setState(BaseDTOEnum.API_MESSAGE_NOT_ROLE);//  205 无权限访问
			return false;
		}
		if (cmsUser.getDisabled().booleanValue()) {
			baseDTO.setState(BaseDTOEnum.API_MESSAGE_USER_NOT_ACTIVATED);
			return false;
		}
		return true;
	}

	
	/**
	 * 校验忘记密码短信
	 * @param baseDTO
	 * @param phone
	 * @return
	 */
	private Boolean validateForgetSMS(BaseDTO baseDTO, String phone) {
		if (StringUtils.isBlank(phone)) {
			baseDTO.setState(BaseDTOEnum.API_MESSAGE_PARAM_NOT_NULL);
			return false;
		}
		else if (cmsUserMng.usernameNotExist(phone)) {
			baseDTO.setState(BaseDTOEnum.API_MESSAGE_USER_NOT_FOUND);
			return false;
		}
		return true;
	}
	
	/**
	 * 校验忘记密码
	 * @param baseDTO
	 * @param username 用户名
	 * @param password 密码
	 * @param validateCode 校验码
	 * @param request
	 * @return
	 */
	private Boolean validateForget(BaseDTO baseDTO, String username, String password, String validateCode, HttpServletRequest request) {
		ServletContext application = request.getSession().getServletContext();
		if (StringUtils.isBlank(username) || StringUtils.isBlank(password)|| StringUtils.isBlank(validateCode)) {
			baseDTO.setState(BaseDTOEnum.API_MESSAGE_PARAM_NOT_NULL);
			return false;
		}
		else if(application.getAttribute(username) == null) {
			baseDTO.setState(BaseDTOEnum.API_MESSAGE_VALIDATECODE_NOTEXIST);
			return false;
		}
		else if(!application.getAttribute(username).toString().equals(validateCode)) {
			baseDTO.setState(BaseDTOEnum.API_MESSAGE_VALIDATECODE_ERROR);
			return false;
		}
		application.removeAttribute(username);
		return true;
	}
	
	/**
	 * 校验修改密码
	 * @param baseDTO
	 * @param username 用户名
	 * @param password 密码
	 * @param validateCode 校验码
	 * @param request
	 * @return
	 */
	private Boolean validateAdminForget(BaseDTO baseDTO, String username, String password, String newpassword,HttpServletRequest request) {
		ServletContext application = request.getSession().getServletContext();
		if (StringUtils.isBlank(username) || StringUtils.isBlank(password)||StringUtils.isBlank(newpassword)) {
			baseDTO.setState(BaseDTOEnum.API_MESSAGE_PARAM_NOT_NULL);//208·参数不能为NULL或空
			return false;
		}
		return true;
	}
	

}
