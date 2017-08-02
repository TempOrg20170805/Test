package com.sunrun.washer.dao;
import com.sunrun.washer.model.*;import com.sunrun.washer.entity.*;


import java.util.List;

import com.jeecms.common.page.Pagination;
import com.jeecms.common.hibernate4.Updater;
/**
 * 文 件 名 : FloorLayerDao.java
 * 创 建 人： 金明明
 * 日 期：2017-7-30
 * 修 改 人： 
 * 日 期： 
 * 描 述：楼层 Dao层
 */
public interface FloorLayerDao {

	/**
	 * 查询楼层管理列表
	 * @param floorLayerModel 楼层管理查询条件
	 * @param pageNo 当前页
	 * @param pageSize 每页数据量
	 * @return
	 */
	public Pagination queryFloorLayerByModel(FloorLayerModel floorLayerModel, Integer pageNo, Integer pageSize);

	/**
	 * 保存楼层管理
	 * @param 楼层管理
	 * @return
	 */
	public FloorLayer save(FloorLayer floorLayer);

	public FloorLayer updateByUpdater(Updater<FloorLayer> updater);

	/**
     * 删除楼层管理
     * @param 楼层管理Id
     * @return
     */
	public FloorLayer deleteById(Integer floorLayerId);

    /**
     * 根据Id获取实体
     * @param 楼层管理Id
     * @return
     */
	public FloorLayer findById(Integer floorLayerId);


}

