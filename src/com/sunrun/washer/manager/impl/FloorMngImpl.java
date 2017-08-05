package com.sunrun.washer.manager.impl;
import java.util.Date;
import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.jeecms.common.hibernate4.Updater;
import com.jeecms.common.page.Pagination;
import com.sunrun.washer.dao.FloorDao;
import com.sunrun.washer.entity.Floor;
import com.sunrun.washer.enums.FloorStatus.FloorStatusEnum;
import com.sunrun.washer.manager.FloorMng;
import com.sunrun.washer.model.FloorModel;
import com.sunrun.washer.model.FloorModelSave;
import com.sunrun.washer.model.FloorModelUpdate;
/**
 * 文 件 名 : FloorMngImpl.java
 * 创 建 人： 金明明
 * 日 期：2017-8-1
 * 修 改 人： 
 * 日 期： 
 * 描 述：楼 MngImpl
 */
@Service
@Transactional
public class FloorMngImpl implements FloorMng{

	@Autowired
	private FloorDao floorDao;
	
	@Override
	public Pagination queryFloorByModel(FloorModel floorModel, Integer pageNo, Integer pageSize) {
		Pagination pagination = floorDao.queryFloorByModel(floorModel, pageNo, pageSize);
		// 编写代码
		return pagination;
	}

	@Override
	public Floor saveFloor(FloorModelSave floorModelSave) {
		Floor bean = new Floor();
		bean.setName(floorModelSave.getName());
		bean.setAddressDetail(floorModelSave.getAddressDetail());
		bean.setCreateTime(new Date());
		bean.setBaseUpdateTime(new Date());
		bean.setStatus(FloorStatusEnum.USE.getCode());
		// 赋值保存的必要信息
		return floorDao.save(bean);
	}

	@Override
	public Floor updateFloor(FloorModelUpdate floorModelUpdate) {
		Floor floor = findById(floorModelUpdate.getFloorId());
		if (StringUtils.isNotBlank(floorModelUpdate.getName())) {
			floor.setName(floorModelUpdate.getName());
		}
		if (StringUtils.isNotBlank(floorModelUpdate.getAddressDetail())) {
			floor.setAddressDetail(floorModelUpdate.getAddressDetail());
		}
		
		floor.setBaseUpdateTime(new Date());
		return null;
	}
	/**
	 * 更新
	 * @param bean
	 * @return
	 */
	private Floor updateFloor(Floor bean) {
		Updater<Floor> updater = new Updater<Floor>(bean);
		return floorDao.updateByUpdater(updater);
	}

	@Override
	public Floor deleteById(Integer id) {
		Floor bean = floorDao.deleteById(id);
		return bean;
	}

	@Override
	public Floor findById(Integer id) {
		return floorDao.findById(id);
	}

	@Override
	public boolean isAddressDetailExists(String addressDetail) {
		List<Floor> floors = floorDao.queryFloorByAddressDetail(addressDetail);
		if (floors != null && floors.size() > 0) {
			return true;
		} else {
			return false;
		}
	}

	@Override
	public Floor queryFloorByAddressDetail(String addressDetail) {
		List<Floor> floors = floorDao.queryFloorByAddressDetail(addressDetail);
		if (floors != null && floors.size() > 0) {
			return floors.get(0);
		} else {
			return null;
		}
	}


}

