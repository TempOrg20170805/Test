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
import com.jeecms.core.entity.CmsUser;
import com.sunrun.common.util.jpush.JpushUtils;
import com.sunrun.washer.dao.JpushBindingDao;
import com.sunrun.washer.entity.JpushBinding;
import com.sunrun.washer.entity.WasherOrder;
import com.sunrun.washer.manager.JpushBindingMng;
import com.sunrun.washer.manager.WasherOrderMng;

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
	@Autowired
	private WasherOrderMng washerOrderMng;

	@Override
	public JpushBinding findById(Integer id) {
		// TODO Auto-generated method stub
		return dao.findById(id);
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

	/**
	 * 工具洗衣机序列号发送推送
	 * 注意：这里查询该洗衣机最近的已完成订单当做用户进行推送。
	 * @param outSn 订单号
	 */
	@Override
	public void JpushMsgSendStart(String machineNo) {
		// 查询该洗衣机最近已完成的订单
		WasherOrder washerOrder = washerOrderMng.queryWasherOrderByMachineNo(machineNo);
		if (washerOrder != null) {
			System.out.println("订单号："+washerOrder.getOutSn()+"，开始推送任务，开始洗涤");
			CmsUser user = washerOrder.getBuyer();
			List<JpushBinding> jpushBindings = findByJpushBinding(user.getUsername());
			for (JpushBinding jpushBinding : jpushBindings) {
				pushMsg(jpushBinding.getRegistrationId(), "智能洗衣机", "您的洗衣机已经开始洗涤", jpushBinding.getKeyType(), jpushBinding.getPlatform());
			}
		}
	}
	
	/**
	 * 发送推送，洗涤完成
	 * 注意：这里查询该洗衣机最近的已完成订单当做用户进行推送。
	 * @param machineNo 洗衣机序列号
	 */
	@Override
	public void JpushMsgSendEnd(String machineNo) {
		// 查询该洗衣机最近已完成的订单
		WasherOrder washerOrder = washerOrderMng.queryWasherOrderByMachineNo(machineNo);
		if (washerOrder != null) {
			System.out.println("订单号："+washerOrder.getOutSn()+"，开始推送任务，结束洗涤");
			CmsUser user = washerOrder.getBuyer();
			List<JpushBinding> jpushBindings = findByJpushBinding(user.getUsername());
			for (JpushBinding jpushBinding : jpushBindings) {
				pushMsg(jpushBinding.getRegistrationId(), "智能洗衣机", "您的洗衣机已经完成洗涤任务", jpushBinding.getKeyType(), jpushBinding.getPlatform());
			}
		}
	}
	
	/**
	 * 推送
	 * @param registrationId 推送唯一编码
	 * @param title 标题
	 * @param alert 内容
	 * @param keyType 秘钥使用类型:1、iOS开发版 2、iOS生产版 3、iOS正式版4、安卓版
	 * @param platform 推送平台设置其关键字分别为："android", "ios", "winphone"
	 */
	private void pushMsg(String registrationId, String title, String alert, Integer keyType, String platform) {
		Map<String, String> extras = new HashMap<String, String>();
		boolean apns_production = false;
		if (keyType != null && keyType > 1) {
			apns_production = true;
		}

        if ("ios".equals(platform)) {
        	JpushUtils.SendPushIOS(alert,title,registrationId ,extras,apns_production);
        }
        else if ("android".equals(platform)) {
        	JpushUtils.SendPush(alert,title,registrationId ,extras);
        }
        else {
        	System.out.println("推送平台"+platform+"不存在");
        }
	}
	
	
	/**
	 * 推送-测试版
	 * @param registrationId 推送唯一编码
	 * @param title 标题
	 * @param alert 内容
	 */
	@Override
	public void pushMsgTest(String registrationId, String title, String alert) {
		Map<String, String> extras = new HashMap<String, String>();
		extras.put("msg", alert);
        JpushUtils.SendPushTest(alert,title,registrationId ,extras);
	}
}
