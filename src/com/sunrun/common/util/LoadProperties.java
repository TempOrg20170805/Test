package com.sunrun.common.util;

import java.io.BufferedInputStream;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

import javax.servlet.http.HttpServletRequest;

public class LoadProperties {
	
	public static Properties getPropertiesObj(HttpServletRequest request){
		//加载属性文件
		Properties pro = new Properties();
		try {
			InputStream in = new BufferedInputStream (new FileInputStream(request.getSession().getServletContext().getRealPath("/WEB-INF/config")+"/sunrun_smsconfig.properties"));
			pro.load(in);
		} catch (IOException e) {
			System.out.println("未找到配置文件！！！");
			return null;
		}
		return pro;
	}

}
