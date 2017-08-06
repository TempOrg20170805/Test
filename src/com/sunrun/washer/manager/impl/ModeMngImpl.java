package com.sunrun.washer.manager.impl;
import com.sunrun.rest.dto.BaseDTO.BaseDTOEnum;
import com.sunrun.washer.manager.*;import com.sunrun.washer.dao.*;import com.sunrun.washer.model.*;import com.sunrun.washer.entity.*;
import com.sunrun.washer.enums.BaseStatusEnum;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.jeecms.common.hibernate4.Updater;
import com.jeecms.common.page.Pagination;
/**
 * 文 件 名 : ModeMngImpl.java
 * 创 建 人： 金明明
 * 日 期：2017-8-6
 * 修 改 人： 
 * 日 期： 
 * 描 述：洗衣模式 MngImpl
 */
@Service
@Transactional
public class ModeMngImpl implements ModeMng{

	@Autowired
	private ModeDao modeDao;
	
	@Override
	public List<Mode> queryModeListByModel(ModeModel modeModel) {
		return modeDao.queryModeListByModel(modeModel);
	}

	@Override
	public Mode saveMode(ModeModelSave modeModelSave) {
		Mode bean = new Mode();
		bean.setName(modeModelSave.getName());
		bean.setModeMoney(modeModelSave.getModeMoney());
		bean.setModeTime(modeModelSave.getModeTime());
		bean.setStatus(BaseStatusEnum.USE.getCode());
		// 赋值保存的必要信息
		return modeDao.save(bean);
	}

	@Override
	public Mode updateMode(ModeModelUpdate modeModelUpdate) {
		Mode bean = findById(modeModelUpdate.getModeId());
		bean.setName(modeModelUpdate.getName());
		bean.setModeMoney(modeModelUpdate.getModeMoney());
		bean.setModeTime(modeModelUpdate.getModeTime());
		bean.setStatus(BaseStatusEnum.USE.getCode());
		updateMode(bean);
		return bean;
	}
	/**
	 * 更新
	 * @param bean
	 * @return
	 */
	private Mode updateMode(Mode bean) {
		Updater<Mode> updater = new Updater<Mode>(bean);
		return modeDao.updateByUpdater(updater);
	}

	@Override
	public Mode deleteById(Integer id) {
		Mode bean = findById(id);
		bean.setStatus(BaseStatusEnum.DELETE.getCode());
		updateMode(bean);
		return bean;
	}

	@Override
	public Mode findById(Integer id) {
		return modeDao.findById(id);
	}


}

