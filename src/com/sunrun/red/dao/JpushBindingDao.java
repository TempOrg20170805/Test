package com.sunrun.red.dao;

import java.util.List;

import com.jeecms.common.hibernate4.Updater;
import com.sunrun.red.entity.JpushBinding;




/**
 * 
 * @author wangcy
 * @ClassName JpushBindingDao.java
 * @CreateDate  2017-4-26
 * @descrintion  推送绑定DAO层 
 * @editor 
 * @editDate
 */
public interface JpushBindingDao {
	
   public List<JpushBinding> getList(String username);
	
	public JpushBinding saveDeviceAdd(JpushBinding bean);
	
	public JpushBinding findById(Integer id);
	
	public List<JpushBinding> findPoliceUserList(Integer groupId,Integer cityId);
	
	public JpushBinding deleteById(Integer id);

	public JpushBinding updateByUpdater(Updater<JpushBinding> updater);
	
	/**
	 * 根据 手机Mac、设备Mac查询list
	 * @param deviceMac
	 * @param mobileMac
	 * @return
	 */
	public List<JpushBinding> getDeviceJpushList(String username);
	/**
	 * 根据 手机Mac、设备Mac查询对象
	 * @param deviceMac
	 * @param mobileMac
	 * @return
	 */
	public JpushBinding getDeviceJpush(String username);

}
