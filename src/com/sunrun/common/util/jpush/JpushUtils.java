package com.sunrun.common.util.jpush;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import net.sf.json.JSONObject;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import cn.jiguang.common.resp.APIConnectionException;
import cn.jiguang.common.resp.APIRequestException;
import cn.jpush.api.JPushClient;

import cn.jpush.api.push.PushResult;
import cn.jpush.api.push.model.Options;
import cn.jpush.api.push.model.Platform;
import cn.jpush.api.push.model.PushPayload;
import cn.jpush.api.push.model.audience.Audience;
import cn.jpush.api.push.model.notification.IosNotification;
import cn.jpush.api.push.model.notification.Notification;
/**
 * 
 * @author wangcy
 * @ClassName JpushUtils.java
 * @CreateDate  2017-6-7
 * @descrintion   极光推送
 * @editor 
 * @editDate
 */
public class JpushUtils {
	protected static final Logger LOG = LoggerFactory
			.getLogger(JpushUtils.class);
	
	//警用端--keyType=2的时候 iOS
	private static final String appKey1 = "da239790136fe512a6507711";//猎狐
	private static final String masterSecret1 = "bd62c2c750be72134d0bc274";
	//警用端--安卓和iOS
	private static final String appKey2 = "9bbe8b2110d9e76d9984ef3a";//猎狐
	private static final String masterSecret2 = "d2bb9c4cb215b2e924d66cff";
	//民用端--安卓和iOS
	private static final String appKey3 = "31b967490130d60cb9316796";//猎狐
	private static final String masterSecret3 = "71755f61bc51e656c506349d";
	//民用端--keyType=2的时候 iOS
	private static final String appKey4 = "e58c2d945b011b17b2865ba6";//猎狐
	private static final String masterSecret4 = "357a545901786b9f9d7e5547";
	
	public static void main(String[] args) {
		 	Map<String, String> extras = new HashMap<String, String>();
		    extras.put("seriaNumber", "2");
		    extras.put("rfidList", "928sbqw,dedwdew");
		    Long t = new Date().getTime();
		    System.out.println("毫秒"+t);
		    //extras.put("时间", t.toString());//t.toString()
		    String ALERT = "您的车发生震动报警";
	        String TITLE ="易行保";
	        String registrationId  ="1104a897929ec6b0bb1"; 
	        //SendPushIOS(ALERT,TITLE,registrationId ,extras,false);


	        SendPush(ALERT,TITLE,registrationId ,extras,1,4);

	        System.out.println(extras);
	        System.out.println(extras.get("deviceMark"));
	}
	
	/**
	 * 
	 * @param ALERT 内容
	 * @param TITLE 标题
	 * @param registrationId
	 * @param extras
	 */
	public static void SendPush(String ALERT,String  TITLE ,String registrationId ,Map extras,Integer groupId,Integer keyType) {
		// HttpProxy proxy = new HttpProxy("localhost", 3128);
		// Can use this https proxy: https://github.com/Exa-Networks/exaproxy
		// 1、创建JPushClient
		/*
		 * 参数说明： masterSecret：注册应用的主密码,即API 主密码 appKey:注册应用的应用Key
		 * maxRetryTime:最大的尝试次数，设为3表示：跟服务器进行建立连接若失败会尝试再进行两次尝试
		 */
//        System.out.println("调用了com.sunrun.common.util.BoxJpushUtils中SendPush方法");
        JPushClient jpushClient = null;
        //1：民用 2：安装 3：警用
        if(groupId==1){

        	jpushClient = new JPushClient(masterSecret3, appKey3,3);


        }else if(groupId==3){

        	jpushClient = new JPushClient(masterSecret2, appKey2,3);

        }
		
		// 对于推送,所有你需要做的是建立PushPayload对象。
		PushPayload payload = buildPushObject_android_tag_alertWithTitle(ALERT,TITLE,registrationId ,extras);

		try {
			PushResult result = jpushClient.sendPush(payload);
			LOG.info("Got result - " + result);

		} catch (APIConnectionException e) {
			LOG.error("Connection error. Should retry later. ", e);

		} catch (APIRequestException e) {
			LOG.error(
					"Error response from JPush server. Should review and fix it. ",
					e);
			LOG.info("HTTP Status: " + e.getStatus());
			LOG.info("Error Code: " + e.getErrorCode());
			LOG.info("Error Message: " + e.getErrorMessage());
			LOG.info("Msg ID: " + e.getMsgId());
		}
	}
	
	
	/**
	 * 消息发送iOS端
	 * @param Alert
	 * @param title
	 * @param registrationId
	 * @param extras
	 * @param apns_production  True 表示推送生产环境，False 表示要推送开发环境；
	 */
	public static void SendPushIOS(String Alert, String title,
			String registrationId, Map extras,Boolean apns_production,Integer groupId,Integer keyType) {
		
		System.out.println("调用了com.sunrun.common.util.BoxJpushUtils中SendPushIOS方法");
		JPushClient jpushClient = null;
	    if(groupId==1){
	    	if(keyType==2){
	    		jpushClient = new JPushClient(masterSecret4, appKey4, 3);
	    	}else{
	    		jpushClient = new JPushClient(masterSecret3, appKey3, 3);
	    	}
        	
        }else if(groupId==3){
        	if(keyType==2){
        		jpushClient = new JPushClient(masterSecret1, appKey1, 3);
        	}else{
        		jpushClient = new JPushClient(masterSecret2, appKey2, 3);
        	}
        }
		
		// 建立PushPayload对象。平台是iOS
		PushPayload payload = anyi_iOS(Alert,title, registrationId, extras,apns_production);
		try {	
			PushResult result = jpushClient.sendPush(payload);					
			LOG.info("Got result - " + result);
		} catch (APIConnectionException e) {
			LOG.error("Connection error. Should retry later. ", e);
		} catch (APIRequestException e) {
			LOG.error("Error response from JPush server. Should review and fix it. ",e);
			LOG.info("HTTP Status: " + e.getStatus());
			LOG.info("Error Code: " + e.getErrorCode());
			LOG.info("Error Message: " + e.getErrorMessage());
			LOG.info("Msg ID: " + e.getMsgId());
		}
	}
	
	
		// 构建推送对象：平台是 Android，目标是 tag 为 "tag1" 的设备，内容是 Android 通知 ALERT，并且标题为 TITLE。
		public static PushPayload buildPushObject_android_tag_alertWithTitle(String ALERT,String  TITLE ,String registrationId, Map extras ) {
			return PushPayload.newBuilder().setPlatform(Platform.android())
					.setAudience(Audience.registrationId(registrationId))
					.setNotification(Notification.android(ALERT, TITLE, extras))
					.build();
		}
	
		

		//构建推送平台：平台-iOS,设备-registrationId，内容Alert的通知
		public static PushPayload anyi_iOS(String Alert,String title ,String registrationId,Map extras,Boolean apns_production){
			 return PushPayload.newBuilder()
		                .setPlatform(Platform.ios())
		                .setAudience(Audience.registrationId(registrationId))
		                .setNotification(Notification.newBuilder()
		                        .addPlatformNotification(IosNotification.newBuilder()
		                                .setAlert(Alert)
		                                .setBadge(0)
		                                .setSound("happy")
		                                .addExtras(extras)
		                                .build())
		                        .build())		               
		                 .setOptions(Options.newBuilder()
		                         .setApnsProduction(apns_production)
		                         .build())
		                 .build();
			
		}
		

}
