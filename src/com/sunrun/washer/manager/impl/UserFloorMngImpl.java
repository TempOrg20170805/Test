package com.sunrun.washer.manager.impl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.jeecms.common.page.Pagination;
import com.jeecms.core.entity.CmsUser;
import com.jeecms.core.manager.CmsUserMng;
import com.sunrun.washer.dao.UserFloorDao;
import com.sunrun.washer.entity.Floor;
import com.sunrun.washer.entity.UserFloor;
import com.sunrun.washer.manager.FloorMng;
import com.sunrun.washer.manager.UserFloorMng;
import com.sunrun.washer.model.FloorModelSave;
import com.sunrun.washer.model.UserFloorModel;
import com.sunrun.washer.model.UserFloorModelSave;
/**
 * 文 件 名 : UserFloorMngImpl.java
 * 创 建 人： 金明明
 * 日 期：2017-7-31
 * 修 改 人： 
 * 日 期： 
 * 描 述：用户关联楼 MngImpl
 */
@Service
@Transactional
public class UserFloorMngImpl implements UserFloorMng{

	@Autowired
	private UserFloorDao userFloorDao;
	@Autowired
	private CmsUserMng cmsUserMng;
	@Autowired
	private FloorMng floorMng;
	@Override
	public Pagination queryUserFloorByModel(UserFloorModel userFloorModel, Integer pageNo, Integer pageSize) {
		Pagination pagination = userFloorDao.queryUserFloorByModel(userFloorModel, pageNo, pageSize);
		return pagination;
	}

	@Override
	public UserFloor saveUserFloor(UserFloorModelSave userFloorModelSave) {
		UserFloor bean = new UserFloor();
		// 查询用户
		CmsUser cmsUser = cmsUserMng.findById(userFloorModelSave.getUserId());
		bean.setCmsUser(cmsUser);
		// 保存楼
		FloorModelSave floorModelSave = new FloorModelSave();
		floorModelSave.setAddressDetail(userFloorModelSave.getAddressDetail());
		floorModelSave.setName("");
		Floor floor = floorMng.saveFloor(floorModelSave);
		bean.setFloor(floor);
		// 赋值保存的必要信息
		return userFloorDao.save(bean);
	}

	@Override
	public UserFloor deleteById(Integer id) {
		UserFloor bean = userFloorDao.deleteById(id);
		return bean;
	}

	@Override
	public UserFloor findById(Integer id) {
		return userFloorDao.findById(id);
	}


}

