package com.sunrun.common.util.unionpay.util;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import com.unionpay.acp.sdk.SDKConfig;

public class UnionpayInit extends HttpServlet{

	/**
	 * 
	 */
	private static final long serialVersionUID = -4987902611205780336L;
	@Override
	public void init() throws ServletException {
		/**
		 * 参数初始化
		 */
		SDKConfig.getConfig().loadPropertiesFromSrc();// 从classpath加载acp_sdk.properties文件
		super.init();
	}
}
