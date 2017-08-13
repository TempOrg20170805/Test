package com.sunrun.washer.manager.impl;

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
import com.sunrun.washer.dao.JpushBindingDao;
import com.sunrun.washer.entity.JpushBinding;
import com.sunrun.washer.manager.JpushBindingMng;

/**
 * 文 件 名 : JpushBindingMngImpl
 * 创 建 人： 金明明
 * 日 期：2017-8-13
 * 修 改 人： 
 * 日 期： 
 * 描 述：极光推送绑定
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

	}
}
