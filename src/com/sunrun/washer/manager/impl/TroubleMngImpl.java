package com.sunrun.washer.manager.impl;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.jeecms.common.hibernate4.Updater;
import com.jeecms.common.page.Pagination;
import com.jeecms.core.entity.CmsUser;
import com.jeecms.core.manager.CmsUserMng;
import com.sunrun.washer.dao.TroubleDao;
import com.sunrun.washer.entity.Machine;
import com.sunrun.washer.entity.Trouble;
import com.sunrun.washer.enums.TroubleStatusEnum;
import com.sunrun.washer.manager.MachineMng;
import com.sunrun.washer.manager.TroubleMng;
import com.sunrun.washer.model.TroubleModel;
import com.sunrun.washer.model.TroubleModelSave;
/**
 * 文 件 名 : TroubleMngImpl.java
 * 创 建 人： 金明明
 * 日 期：2017-8-13
 * 修 改 人： 
 * 日 期： 
 * 描 述：故障 MngImpl
 */
@Service
@Transactional
public class TroubleMngImpl implements TroubleMng{

	@Autowired
	private TroubleDao troubleDao;
	@Autowired
	private MachineMng machineMng;
	@Autowired
	private CmsUserMng cmsUserMng;
	@Override
	public Pagination queryTroubleByModel(TroubleModel troubleModel, Integer pageNo, Integer pageSize) {
		Pagination pagination = troubleDao.queryTroubleByModel(troubleModel, pageNo, pageSize);
		// 编写代码
		return pagination;
	}

	@Override
	public Trouble saveTrouble(TroubleModelSave troubleModelSave) {
		Trouble bean = new Trouble();
		bean.setTroubleReason(troubleModelSave.getTroubleReason());
		CmsUser user = cmsUserMng.findById(troubleModelSave.getUserId());
		bean.setCmsUser(user);
		Machine machine = machineMng.findById(troubleModelSave.getMachineId());
		bean.setMachine(machine);
		bean.setStatus(TroubleStatusEnum.WAITING.getCode());
		bean.setCreateTime(new Date());
		// 将洗衣机设置为故障状态
		machineMng.updateTroubleStatus(machine.getMachineId(), 1);
		return troubleDao.save(bean);
	}

	/**
	 * 更新
	 * @param bean
	 * @return
	 */
	private Trouble updateTrouble(Trouble bean) {
		Updater<Trouble> updater = new Updater<Trouble>(bean);
		return troubleDao.updateByUpdater(updater);
	}

	@Override
	public Trouble deleteById(Integer id) {
		Trouble bean = findById(id);
		// 此处还原洗衣机故障状态
		machineMng.updateTroubleStatus(bean.getMachine().getMachineId(), 0);
		bean.setStatus(TroubleStatusEnum.DELETE.getCode());
		updateTrouble(bean);
		return bean;
	}

	@Override
	public Trouble findById(Integer id) {
		return troubleDao.findById(id);
	}

	@Override
	public Trouble startRepair(Integer troubleId) {
		Trouble trouble = findById(troubleId);
		trouble.setStatus(TroubleStatusEnum.UNDER_REPAIR.getCode());
		trouble.setStartTime(new Date());
		return updateTrouble(trouble);
	}

	@Override
	public Trouble endRepair(Integer troubleId) {
		Trouble trouble = findById(troubleId);
		trouble.setStatus(TroubleStatusEnum.SUCCESS.getCode());
		trouble.setSuccessTime(new Date());
		// 此处还原洗衣机故障状态
		machineMng.updateTroubleStatus(trouble.getMachine().getMachineId(), 0);
		
		return updateTrouble(trouble);
	}


}

