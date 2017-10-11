package com.sunrun.rest.controller;

import java.util.Iterator;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.mina.core.session.IoSession;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.sunrun.rest.dto.BaseDTO;
import com.sunrun.rest.dto.TestPushDeleteDTO;
import com.sunrun.tcp.common.DataUtils;
import com.sunrun.tcp.mina.entity.Transparent;
import com.sunrun.tcp.mina.handler.ServerHandler;

/** 
 * @Package: com.sunrun.rest.controller 
 * @ClassName: TestController.java 
 * @Description:按摩椅透传数据测试控制层
 * @author: HL 
 * @date: 创建时间：2017年10月11日 上午9:39:25
 * @version: 1.0 
 */
@Controller
public class TestController {

	//logback打印日志，打印等级配置在logback.xml文件
	private static Logger logger = LoggerFactory.getLogger(TestController.class); 
	
	@RequestMapping(value ="/transparent.json",method=RequestMethod.POST)
	@ResponseBody
	public TestPushDeleteDTO transparent(HttpServletRequest request,HttpServletResponse response)
	{	
		TestPushDeleteDTO testPushDeleteDTO = new TestPushDeleteDTO();
		testPushDeleteDTO.setState(BaseDTO.BaseDTOEnum.API_STATUS_FAIL);
		try {			
			// 编码格式
	    	request.setCharacterEncoding("UTF-8");
			String sn = request.getParameter("sn");
			String data = request.getParameter("data");
			Iterator<Map.Entry<String,IoSession>> ite_deviceiomap = ServerHandler.getDeviceIoMap().entrySet().iterator();
			while (ite_deviceiomap.hasNext()) 
			{
				Map.Entry<String,IoSession> mapentry_deviceiomap =ite_deviceiomap.next();
				String devicemark =  mapentry_deviceiomap.getKey().toString();
				IoSession session = mapentry_deviceiomap.getValue();
				if(sn.equals(devicemark))
				{
					if(session!=null&&session.isConnected())
					{
						byte[] dataArray=data.getBytes();
						Transparent transparent=new Transparent(dataArray, DataUtils.XOR(dataArray, dataArray.length));
						session.write(transparent);
						testPushDeleteDTO.setState(BaseDTO.BaseDTOEnum.API_STATUS_SUCCESS);
					}
					break;
				}
			}
		} catch (Exception e) {
			// TODO: handle exception
			logger.error("TestController-transparent", e);
		}
		return testPushDeleteDTO;
	}
}
