package com.sunrun.washer.manager.impl;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.jeecms.common.hibernate4.Updater;
import com.jeecms.common.page.Pagination;
import com.jeecms.core.entity.CmsUser;
import com.jeecms.core.manager.CmsUserMng;
import com.sunrun.washer.dao.MachineDao;
import com.sunrun.washer.entity.FloorLayer;
import com.sunrun.washer.entity.Machine;
import com.sunrun.washer.entity.UserMachine;
import com.sunrun.washer.enums.MachineAuthLevelsEnum;
import com.sunrun.washer.enums.MachineStatusEnum;
import com.sunrun.washer.enums.UserMachineUseTypeEnum;
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
	
	/**
	 * logback打印日志，打印等级配置在logback.xml文件
	 */
	private static Logger logger = LoggerFactory.getLogger(MachineMngImpl.class); 
	
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
		// 更新洗衣机关联
		updateMachineConnect(machineModelSave.getUserName(),bean.getMachineId());
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
		
		// 更新洗衣机关联
		updateMachineConnect(machineModelUpdate.getUserName(),bean.getMachineId());
		
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
		// 删除所有关联
		updateMachineConnect(null, id);
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

	@Override
	@Transactional(value="transactionManager",propagation=Propagation.REQUIRED,rollbackFor=Exception.class)
	public Integer updateOnline(Map<String, Integer> onlineMap) {
		// TODO Auto-generated method stub
		int result=0;
		try {
			Iterator<Map.Entry<String,Integer>> ite_online = onlineMap.entrySet().iterator();
			while (ite_online.hasNext()) {
				Map.Entry<String,Integer> mapentry_online =ite_online.next();
				String sn =  mapentry_online.getKey().toString();
				Integer online = mapentry_online.getValue();
				result=machineDao.updateOnline(sn, online);
			}
		} catch (Exception e) {
			// TODO: handle exception
			logger.error("MachineMngImpl-updateOnline", e);
		}
		return result;
	}

	@Override
	@Transactional(value="transactionManager",propagation=Propagation.REQUIRED,rollbackFor=Exception.class)
	public Integer updateStatus(String sn, Integer status) {
		// TODO Auto-generated method stub
		int result=0;
		try 
		{
			result=machineDao.updateStatus(sn, status);
				
		} catch (Exception e) {
			// TODO: handle exception
			logger.error("MachineMngImpl-updateStatus", e);
		}
		return result;
	}

	/**
	 * 更新洗衣机关联
	 * @param userName 用户名
	 * @param machineId 洗衣机ID
	 */
	private void updateMachineConnect(String userName, Integer machineId) {
		// 若用户名为空,删除原有渠道商的权限
		if (StringUtils.isBlank(userName)) {
			deleteMachineWithConnect(machineId);
		}
		// 发放给渠道商
		else if (StringUtils.isNotBlank(userName)) {
			List<UserMachine> userMachines = userMachineMng.findUserMachineListByMachine(machineId);
			if (userMachines != null && userMachines.size() > 0) {
				if (!userMachines.get(0).getCmsUser().getUsername().equals(userName)) {
					// 若用户变更则更新删除原关联，并添加新关联
					deleteMachineWithConnect(machineId);
					saveMachineWithUser(userName, machineId);
				}
				// 若用户没变则不进行操作
			} else {
				// 原用户未关联，这里重新关联
				saveMachineWithUser(userName, machineId);
			}
		}
	}
	/**
	 * 删除洗衣机的所有关联，并不删除洗衣机
	 * @param machineId
	 */
	private void deleteMachineWithConnect(Integer machineId) {
		List<UserMachine>  userMachines = userMachineMng.findUserMachineListByMachine(machineId);
		if (userMachines != null && userMachines.size() > 0) {
			Machine machine = userMachines.get(0).getMachine();
			// 删除投放的洗衣机
			machineMng.updateUserMachineFloorLayerDelete(machine.getMachineId());
			userMachineMng.deleteById(userMachines.get(0).getUserMachineId());
		}
	}
	/**
	 * 洗衣机重新绑定用户
	 * @param userName 用户名
	 * @param machineId 洗衣机ID
	 */
	private void saveMachineWithUser(String userName, Integer machineId) {
		CmsUser user = cmsUserMng.findByUsername(userName);
		if (user != null) {
			UserMachineModelSave userMachineModelSave = new UserMachineModelSave();
			userMachineModelSave.setUserName(userName);
			userMachineModelSave.setAuthLevel(MachineAuthLevelsEnum.ONE.getCode());
			userMachineModelSave.setMachineId(machineId);
			userMachineModelSave.setUseType(UserMachineUseTypeEnum.USE.getCode());
			userMachineMng.saveUserMachine(userMachineModelSave);
		}
	}

	@Override
	public Machine updateTroubleStatus(Integer machineId, Integer isTrouble) {
		Machine machine = findById(machineId);
		if (isTrouble.equals(1)) {
			machine.setStatus(MachineStatusEnum.STOP.getCode());
		}
		// 注意：此处若故障解除，不需要更新洗衣机的状态，只要设备在线，状态会自动更新。
		
		machine.setIsTrouble(isTrouble);
		return updateMachine(machine);
	}


}

