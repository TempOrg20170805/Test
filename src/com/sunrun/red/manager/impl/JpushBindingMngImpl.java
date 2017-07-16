package com.sunrun.red.manager.impl;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.jeecms.common.hibernate4.Updater;

import com.sunrun.common.util.jpush.JpushUtils;
import com.sunrun.red.dao.JpushBindingDao;
import com.sunrun.red.entity.JpushBinding;
import com.sunrun.red.manager.JpushBindingMng;



/**
 * 
 * @author wangcy
 * @ClassName JpushBindingMngImpl.java
 * @CreateDate  2016-7-6
 * @descrintion  推送绑定业务实现层
 * @editor 
 * @editDate
 */
@Service
@Transactional
public class JpushBindingMngImpl implements JpushBindingMng {
	@Autowired
	private JpushBindingDao dao;

	@Override
	public JpushBinding findById(Integer id) {
		// TODO Auto-generated method stub
		return dao.findById(id);
	}

	@Override
	public List<JpushBinding> findPoliceUserList(Integer groupId,Integer cityId) {
		// TODO Auto-generated method stub
		return dao.findPoliceUserList(groupId,cityId);
	}

	@Override
	public JpushBinding saveJpushBinding(JpushBinding bean,Integer groupId,Integer cityId,
			HttpServletRequest request) {
		if(StringUtils.isBlank(bean.getRegistrationId())||StringUtils.isBlank(bean.getPlatform())
				||bean.getKeyType()==null||bean.getPlatform()==null||bean.getRegistrationId()==null){
			System.out.println("绑定条件不符合，跳过绑定");
		}else{
			if(isDeviceJpush(bean.getUserName())){
				JpushBinding Jbean = dao.getDeviceJpush(bean.getUserName());
				Jbean.setUserName(bean.getUserName());
				Jbean.setRegistrationId(bean.getRegistrationId());
				Jbean.setKeyType(bean.getKeyType());
				Jbean.setPlatform(bean.getPlatform());
				Jbean.setAddTime(new Date());
				bean= updateJpushBinding(Jbean);
				System.out.println("推送绑定实现更新");
			}else{
				bean.setAddTime(new Date());
				bean.setGroupId(groupId);
				bean.setCityId(cityId);
				bean =dao.saveDeviceAdd(bean);
				System.out.println("推送绑定实现保存");
			}
		}
		
		return bean;
	}

	@Override
	public JpushBinding deleteById(Integer id) {
		JpushBinding bean = dao.deleteById(id);
		return bean;
	}

	@Override
	public JpushBinding updateJpushBinding(JpushBinding bean) {
		Updater<JpushBinding> updaterJpushBinding = new Updater<JpushBinding>(bean);
		bean=dao.updateByUpdater(updaterJpushBinding);
		return bean;
	}

	@Override
	public List<JpushBinding> findByJpushBinding(String username) {
		// TODO Auto-generated method stub
		return dao.getDeviceJpushList( username);
	}

	@Override
	public Boolean isDeviceJpush( String username) {
		// TODO Auto-generated method stub
		return dao.getDeviceJpushList(username).size()>0;
	}

	@Override
	public JpushBinding getDeviceJpush( String username) {
		// TODO Auto-generated method stub
		return dao.getDeviceJpush(username);
	}
    /**
     * type:0-低电量报警 1-断电报警 2-推动报警 3-震动报警
     *  报警类型：msgType
     */
	@Override
	public void JpushMsgSend(byte sendType, String mark) {
		// TODO Auto-generated method stub
//		Map<String, String> extras = new HashMap<String, String>();
//
//		List<DeviceUser> list = deviceUserMng.queryByMarkAOneMoreTypeList(null,mark);
//		if(list.size()>0){
//			for(int i=0;i<list.size();i++){
//				JpushBinding Jbean=dao.getDeviceJpush(list.get(i).getJcUser().getUsername());
//				Boolean apns_production;
//				if(Jbean.getKeyType()!=null&&Jbean.getKeyType()==1){
//					apns_production=false;
//				}else {
//					apns_production=true;
//				}
//				if(sendType==0){
//				       extras.put("msgType", "1");
//					   extras.put("deviceMark", mark);
//				   extras.put("deviceName", list.get(i).getDeviceName());
//					if(Jbean.getPlatform().equals("android")){
//						 JpushUtils.SendPush(list.get(i).getDeviceName()+"车辆电量过低，请及时查看", "低电量提示",Jbean.getRegistrationId(), extras);	
//					}else{
//						 JpushUtils.SendPushIOS(list.get(i).getDeviceName()+"车辆电量过低，请及时查看", "低电量提示",Jbean.getRegistrationId(), extras,apns_production);	 	 
//					}
//				}else if(sendType==1){
//					if(Jbean.getPlatform().equals("android")){
//						 JpushUtils.SendPush(list.get(i).getDeviceName()+"车辆已断电，请及时查看。", "断电提示",Jbean.getRegistrationId(), null);	
//					}else{
//						 JpushUtils.SendPushIOS(list.get(i).getDeviceName()+"车辆已断电，请及时查看。", "断电提示",Jbean.getRegistrationId(), null,apns_production);	 	 
//					}
//				}else if(sendType==2){
//					if(Jbean.getPlatform().equals("android")){
//						 JpushUtils.SendPush(list.get(i).getDeviceName()+"车辆发生可疑推动，请及时查看", "推动提示",Jbean.getRegistrationId(), extras);	
//					}else{
//						 JpushUtils.SendPushIOS(list.get(i).getDeviceName()+"车辆发生可疑推动，请及时查看", "推动提示",Jbean.getRegistrationId(), extras,apns_production);	 	 
//						 }				
//			  }else {
//				  if(Jbean.getPlatform().equals("android")){
//						 JpushUtils.SendPush(list.get(i).getDeviceName()+"车辆发生未知震动，请及时查看", "震动动提示",Jbean.getRegistrationId(), extras);	
//					}else{
//						 JpushUtils.SendPushIOS(list.get(i).getDeviceName()+"车辆发生未知震动，请及时查看", "震动动提示",Jbean.getRegistrationId(), extras,apns_production);	 	 
//						 }	
//			}
//		}
//		 
//	}
	}
}
