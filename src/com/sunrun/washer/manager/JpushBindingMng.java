package com.sunrun.washer.manager;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import com.sunrun.washer.entity.JpushBinding;



/**
 * 
 * @author wangcy
 * @ClassName JpushBindingMng.java
 * @CreateDate  2017-6-26
 * @descrintion  推送绑定业务表
 * @editor 
 * @editDate
 */
public interface JpushBindingMng {
	
	/**
	 * 根据ID查找设备实体
	 * @param id
	 * @return
	 */
	public JpushBinding findById(Integer id);
	
	/**
	 * 根据group和cityId查询绑定的警员列表
	 * @param id
	 * @return
	 */
	public List<JpushBinding> findPoliceUserList(Integer groupId,Integer cityId);
	
	/**
	 * 设备绑定添加
	 * @param bean
	 * @param request
	 * @return
	 */
	public JpushBinding saveJpushBinding(JpushBinding bean,Integer groupId,Integer cityId,HttpServletRequest request);
	
	/**
	 * 删除
	 * 
	 * @param id
	 * @return
	 */
	public JpushBinding deleteById(Integer id);

	/**
     * 更新
     * @param bean
     * @return
     */
	public JpushBinding updateJpushBinding(JpushBinding bean);
	
	/**
	 * 根据 手机Mac、设备Mac查询list
	 * 
	 * @param deviceMac
	 * @param mobileMac
	 * @return
	 */
	public List<JpushBinding> findByJpushBinding( String username);

	/**
	 * 根据手机Mac 设备Mac判断是否有相同的记录存入
	 * 
	 * @param deviceMac
	 * @param mobileMac
	 * @return
	 */
	public Boolean isDeviceJpush( String username);
	
	/**
	 * 根据 手机Mac、设备Mac查询对象
	 * @param deviceMac
	 * @param mobileMac
	 * @return
	 */
	public JpushBinding getDeviceJpush(String username);
	/**
	 * 消息推送方法
	 * @CreateDate  2017-5-5
	 * @author wangcy
	 * @param type
	 * @param mark
	 * @param alarmMsg
	 */
	public void JpushMsgSend(byte type,String mark);
}
