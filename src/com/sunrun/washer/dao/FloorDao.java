package com.sunrun.washer.dao;
import com.sunrun.washer.model.*;import com.sunrun.washer.entity.*;


import java.util.List;

import com.jeecms.common.page.Pagination;
import com.jeecms.common.hibernate4.Updater;
/**
 * 文 件 名 : FloorDao.java
 * 创 建 人： 金明明
 * 日 期：2017-8-1
 * 修 改 人： 
 * 日 期： 
 * 描 述：楼 Dao层
 */
public interface FloorDao {

	/**
	 * 查询楼管理列表
	 * @param floorModel 楼管理查询条件
	 * @param pageNo 当前页
	 * @param pageSize 每页数据量
	 * @return
	 */
	public Pagination queryFloorByModel(FloorModel floorModel, Integer pageNo, Integer pageSize);

	/**
	 * 保存楼管理
	 * @param 楼管理
	 * @return
	 */
	public Floor save(Floor floor);

	public Floor updateByUpdater(Updater<Floor> updater);

	/**
     * 删除楼管理
     * @param 楼管理Id
     * @return
     */
	public Floor deleteById(Integer floorId);

    /**
     * 根据Id获取实体
     * @param 楼管理Id
     * @return
     */
	public Floor findById(Integer floorId);


}

