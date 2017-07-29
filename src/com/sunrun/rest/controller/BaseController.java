package com.sunrun.rest.controller;

import javax.persistence.Access;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.ModelAttribute;

import com.jeecms.core.manager.CmsUserMng;


/**
 * 
 * @author jinmm
 * @ClassName BaseController.java
 * @CreateDate 2017-7-16
 * @descrintion 接口基础类
 * @editor
 * @editDate
 */
public class BaseController{
	
	protected HttpServletRequest request;
	protected HttpServletResponse response;
	protected HttpSession session;
	
	@Autowired
	private CmsUserMng cmsUserMng;

	@ModelAttribute
	public void setReqAndRes(HttpServletRequest request, HttpServletResponse response){
		this.request = request;
		this.response = response;
		this.session = request.getSession();
		try {
			if (request.getParameter("userId") != null && StringUtils.isNotBlank(request.getParameter("userId").toString())) {
				this.setUserId(Integer.parseInt(request.getParameter("userId").toString()));
			} else {
				this.setUserId(0);
			}
		}catch(Exception e) {
			this.setUserId(0);
		}

	}
	
	private Integer userId;

	public Integer getUserId() {
		return userId;
	}

	public void setUserId(Integer userId) {
		this.userId = userId;
	}

	public HttpServletRequest getRequest() {
		return request;
	}

	public void setRequest(HttpServletRequest request) {
		this.request = request;
	}

	public HttpServletResponse getResponse() {
		return response;
	}

	public void setResponse(HttpServletResponse response) {
		this.response = response;
	}

	public HttpSession getSession() {
		return session;
	}

	public void setSession(HttpSession session) {
		this.session = session;
	}

}
