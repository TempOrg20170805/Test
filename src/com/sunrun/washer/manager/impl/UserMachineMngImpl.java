package com.sunrun.washer.manager.impl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.jeecms.common.hibernate4.Updater;
import com.jeecms.common.page.Pagination;
import com.jeecms.core.entity.CmsUser;
import com.jeecms.core.manager.CmsUserMng;
import com.sunrun.washer.dao.UserMachineDao;
import com.sunrun.washer.entity.Machine;
import com.sunrun.washer.entity.UserMachine;
import com.sunrun.washer.manager.MachineMng;
import com.sunrun.washer.manager.UserMachineMng;
import com.sunrun.washer.model.UserMachineModel;
import com.sunrun.washer.model.UserMachineModelSave;
import com.sunrun.washer.model.UserMachineModelUpdate;
import com.sunrun.washer.model.UserMachineModelUpdatePutIn;
/**
 * 文 件 名 : UserMachineMngImpl.java
 * 创 建 人： 金明明
 * 日 期：2017-7-30
 * 修 改 人： 
 * 日 期： 
 * 描 述：用户关联洗衣机 MngImpl
 */
@Service
@Transactional
public class UserMachineMngImpl implements UserMachineMng{

	@Autowired
	private UserMachineDao userMachineDao;
	@Autowired
	private CmsUserMng cmsUserMng;
	@Autowired
	private MachineMng machineMng;
	
	@Override
	public Pagination queryUserMachineByModel(UserMachineModel userMachineModel, Integer pageNo, Integer pageSize) {
		Pagination pagination = userMachineDao.queryUserMachineByModel(userMachineModel, pageNo, pageSize);
		// 编写代码
		return pagination;
	}

	@Override
	public UserMachine saveUserMachine(UserMachineModelSave userMachineModelSave) {
		UserMachine bean = new UserMachine();
		CmsUser cmsUser = cmsUserMng.findByUsername(userMachineModelSave.getUserName());
		bean.setCmsUser(cmsUser);
		Machine machine = machineMng.findById(userMachineModelSave.getMachineId());
		bean.setMachine(machine);
		bean.setUseType(userMachineModelSave.getUseType());
		bean.setAuthLevel(userMachineModelSave.getAuthLevel());
		// 赋值保存的必要信息
		return userMachineDao.save(bean);
	}

	@Override
	public UserMachine updateUserMachine(UserMachineModelUpdate userMachineModelUpdate) {
		return null;
	}
	/**
	 * 更新
	 * @param bean
	 * @return
	 */
	private UserMachine updateUserMachine(UserMachine bean) {
		Updater<UserMachine> updater = new Updater<UserMachine>(bean);
		return userMachineDao.updateByUpdater(updater);
	}

	@Override
	public UserMachine deleteById(Integer id) {
		UserMachine bean = userMachineDao.deleteById(id);
		return bean;
	}

	@Override
	public UserMachine findById(Integer id) {
		return userMachineDao.findById(id);
	}

	@Override
	public Machine updateUserMachinePutIn(
			UserMachineModelUpdatePutIn userMachineModelSaveUpdatePutIn) {
		return machineMng.updateMachine(userMachineModelSaveUpdatePutIn);
	}

	@Override
	public Machine updateUserMachineFloorLayerDelete(Integer machineId) {
		return machineMng.updateUserMachineFloorLayerDelete(machineId);
	}


}

