package com.sunrun.washer.manager;
import com.sunrun.washer.model.*;import com.sunrun.washer.entity.*;


import java.util.List;

import com.jeecms.common.page.Pagination;
/**
 * 文 件 名 : FloorLayerMng.java
 * 创 建 人： 金明明
 * 日 期：2017-7-30
 * 修 改 人： 
 * 日 期： 
 * 描 述：楼层 Mng层
 */
public interface FloorLayerMng {

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
	 * @param floorLayerModelSave 保存的信息对象
	 * @return
	 */
	public FloorLayer saveFloorLayer(FloorLayerModelSave floorLayerModelSave);

	/**
	 * 更新楼层管理基本信息
	 * @param floorLayerModelUpdate 更新的信息
	 * @return
	 */
	public FloorLayer updateFloorLayer(FloorLayerModelUpdate floorLayerModelUpdate);

	/**
     * 删除
     * @param id
     * @return
     */
	public FloorLayer deleteById(Integer id);

	/**
	 * 根据id获取实体
	 * @param id
	 * @return
	 */
	public FloorLayer findById(Integer id);


}

