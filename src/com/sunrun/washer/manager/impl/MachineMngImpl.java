package com.sunrun.washer.manager.impl;
import java.util.Date;
import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.jeecms.common.hibernate4.Updater;
import com.jeecms.common.page.Pagination;
import com.jeecms.core.entity.CmsUser;
import com.jeecms.core.manager.CmsUserMng;
import com.sunrun.washer.dao.MachineDao;
import com.sunrun.washer.entity.FloorLayer;
import com.sunrun.washer.entity.Machine;
import com.sunrun.washer.enums.MachineAuthLevels.MachineAuthLevelsEnum;
import com.sunrun.washer.enums.MachineStatus.MachineStatusEnum;
import com.sunrun.washer.manager.FloorLayerMng;
import com.sunrun.washer.manager.MachineMng;
import com.sunrun.washer.manager.UserMachineMng;
import com.sunrun.washer.model.MachineModel;
import com.sunrun.washer.model.MachineModelSave;
import com.sunrun.washer.model.MachineModelUpdate;
import com.sunrun.washer.model.UserMachineModelSave;
import com.sunrun.washer.model.UserMachineModelUpdatePutIn;
/**
 * 文 件 名 : MachineMngImpl.java
 * 创 建 人： 金明明
 * 日 期：2017-7-31
 * 修 改 人： 
 * 日 期： 
 * 描 述：洗衣机 MngImpl
 */
@Service
@Transactional
public class MachineMngImpl implements MachineMng{

	@Autowired
	private MachineDao machineDao;
	@Autowired
	private UserMachineMng userMachineMng;
	@Autowired
	private CmsUserMng cmsUserMng;
	@Autowired
	private MachineMng machineMng;
	@Autowired
	private FloorLayerMng floorLayerMng;
	
	@Override
	public Pagination queryMachineByModel(MachineModel machineModel, Integer pageNo, Integer pageSize) {
		Pagination pagination = machineDao.queryMachineByModel(machineModel, pageNo, pageSize);
		// 编写代码
		return pagination;
	}

	@Override
	public Machine saveMachine(MachineModelSave machineModelSave) {
		Machine bean = new Machine();
		// 赋值保存的必要信息
		bean.setName(machineModelSave.getName());
		bean.setType(machineModelSave.getType());
		bean.setMachineNo(machineModelSave.getMachineNo());
		
		bean =  machineDao.save(bean);
		// 发放给渠道商
		if (StringUtils.isNotBlank(machineModelSave.getUserName())) {
			CmsUser user = cmsUserMng.findByUsername(machineModelSave.getUserName());
			if (user != null) {
				UserMachineModelSave userMachineModelSave = new UserMachineModelSave();
				userMachineModelSave.setUserName(machineModelSave.getUserName());
				userMachineModelSave.setAuthLevel(MachineAuthLevelsEnum.ONE.getCode());
				userMachineModelSave.setMachineId(bean.getMachineId());
				userMachineModelSave.setUseType(1);
				userMachineMng.saveUserMachine(userMachineModelSave);
			}
		}
		return bean;
	}

	@Override
	public Machine updateMachine(MachineModelUpdate machineModelUpdate) {
		Machine bean = findById(machineModelUpdate.getMachineId());
		if (machineModelUpdate.getName() !=null) {
			bean.setName(machineModelUpdate.getName());
		}
		if (machineModelUpdate.getType() !=null) {
			bean.setType(machineModelUpdate.getType());
		}
		if (machineModelUpdate.getMachineNo() != null) {
			bean.setMachineNo(machineModelUpdate.getMachineNo());
		}
		bean.setBaseUpdateTime(new Date());
		return updateMachine(bean);
	}
	/**
	 * 更新
	 * @param bean
	 * @return
	 */
	private Machine updateMachine(Machine bean) {
		Updater<Machine> updater = new Updater<Machine>(bean);
		return machineDao.updateByUpdater(updater);
	}

	@Override
	public Machine deleteById(Integer id) {
		Machine bean = findById(id);
		bean.setStatus(MachineStatusEnum.DELETE.getCode());
		bean = updateMachine(bean);
		return bean;
	}

	@Override
	public Machine findById(Integer id) {
		return machineDao.findById(id);
	}

	@Override
	public Machine updateMachine(
			UserMachineModelUpdatePutIn userMachineModelSaveUpdatePutIn) {
		Machine machine = machineMng.findById(userMachineModelSaveUpdatePutIn.getMachineId());
		FloorLayer floorLayer = floorLayerMng.findById(userMachineModelSaveUpdatePutIn.getFloorLayerId());
		machine.setFloorLayer(floorLayer);
		machine.setFloorLayerX(userMachineModelSaveUpdatePutIn.getFloorLayerX());
		machine.setFloorLayerY(userMachineModelSaveUpdatePutIn.getFloorLayerY());
		machine.setUseTime(new Date());
		return updateMachine(machine);
	}

	@Override
	public List<Machine> queryMachineByFloor(Integer floorId) {
		return machineDao.queryMachineByFloor(floorId);
	}

	@Override
	public Machine updateUserMachineFloorLayerDelete(Integer machineId) {
		Machine machine = machineMng.findById(machineId);
		machine.setFloorLayer(null);
		machine.setFloorLayerX(0);
		machine.setFloorLayerY(0);
		machine.setUseTime(new Date());
		return updateMachine(machine);
	}

	@Override
	public List<Machine> queryMachineByFloorLayer(Integer floorLayerId) {
		return machineDao.queryMachineByFloorLayer(floorLayerId);
	}


}

