package com.sunrun.washer.manager.impl;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.jeecms.common.hibernate4.Updater;
import com.jeecms.common.page.Pagination;
import com.sunrun.washer.dao.FloorLayerDao;
import com.sunrun.washer.entity.Floor;
import com.sunrun.washer.entity.FloorLayer;
import com.sunrun.washer.manager.FloorLayerMng;
import com.sunrun.washer.manager.FloorMng;
import com.sunrun.washer.model.FloorLayerModel;
import com.sunrun.washer.model.FloorLayerModelSave;
import com.sunrun.washer.model.FloorLayerModelUpdate;
/**
 * 文 件 名 : FloorLayerMngImpl.java
 * 创 建 人： 金明明
 * 日 期：2017-7-30
 * 修 改 人： 
 * 日 期： 
 * 描 述：楼层 MngImpl
 */
@Service
@Transactional
public class FloorLayerMngImpl implements FloorLayerMng{

	@Autowired
	private FloorLayerDao floorLayerDao;
	@Autowired
	private FloorMng floorMng;
	
	@Override
	public Pagination queryFloorLayerByModel(FloorLayerModel floorLayerModel, Integer pageNo, Integer pageSize) {
		Pagination pagination = floorLayerDao.queryFloorLayerByModel(floorLayerModel, pageNo, pageSize);
		// 编写代码
		return pagination;
	}

	@Override
	public FloorLayer saveFloorLayer(FloorLayerModelSave floorLayerModelSave) {
		FloorLayer bean = new FloorLayer();
		
		Floor floor = floorMng.findById(floorLayerModelSave.getFloorId());
		bean.setFloor(floor);
		bean.setName(floorLayerModelSave.getName());
		bean.setLayer(floorLayerModelSave.getLayer());
		bean.setLayerX(floorLayerModelSave.getLayerX());
		bean.setLayerY(floorLayerModelSave.getLayerY());
		bean.setCreateTime(new Date());
		bean.setBaseUpdateTime(new Date());
		return floorLayerDao.save(bean);
	}

	@Override
	public FloorLayer updateFloorLayer(FloorLayerModelUpdate floorLayerModelUpdate) {
		return null;
	}
	/**
	 * 更新
	 * @param bean
	 * @return
	 */
	private FloorLayer updateFloorLayer(FloorLayer bean) {
		Updater<FloorLayer> updater = new Updater<FloorLayer>(bean);
		return floorLayerDao.updateByUpdater(updater);
	}

	@Override
	public FloorLayer deleteById(Integer id) {
		FloorLayer bean = floorLayerDao.deleteById(id);
		return bean;
	}

	@Override
	public FloorLayer findById(Integer id) {
		return floorLayerDao.findById(id);
	}


}

