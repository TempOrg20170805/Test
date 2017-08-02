package com.sunrun.washer.dao;
import com.sunrun.washer.model.*;import com.sunrun.washer.entity.*;


import java.util.List;

import com.jeecms.common.page.Pagination;
import com.jeecms.common.hibernate4.Updater;
/**
 * 文 件 名 : UserFloorDao.java
 * 创 建 人： 金明明
 * 日 期：2017-7-31
 * 修 改 人： 
 * 日 期： 
 * 描 述：用户关联楼 Dao层
 */
public interface UserFloorDao {

	/**
	 * 查询用户关联楼管理列表
	 * @param userFloorModel 用户关联楼管理查询条件
	 * @param pageNo 当前页
	 * @param pageSize 每页数据量
	 * @return
	 */
	public Pagination queryUserFloorByModel(UserFloorModel userFloorModel, Integer pageNo, Integer pageSize);

	/**
	 * 保存用户关联楼管理
	 * @param 用户关联楼管理
	 * @return
	 */
	public UserFloor save(UserFloor userFloor);

	public UserFloor updateByUpdater(Updater<UserFloor> updater);

	/**
     * 删除用户关联楼管理
     * @param 用户关联楼管理Id
     * @return
     */
	public UserFloor deleteById(Integer userFloorId);

    /**
     * 根据Id获取实体
     * @param 用户关联楼管理Id
     * @return
     */
	public UserFloor findById(Integer userFloorId);


}

