package com.sunrun.washer.manager;
import com.sunrun.washer.model.*;import com.sunrun.washer.entity.*;


import java.util.List;

import com.jeecms.common.page.Pagination;
/**
 * 文 件 名 : FloorMng.java
 * 创 建 人： 金明明
 * 日 期：2017-8-1
 * 修 改 人： 
 * 日 期： 
 * 描 述：楼 Mng层
 */
public interface FloorMng {

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
	 * @param floorModelSave 保存的信息对象
	 * @return
	 */
	public Floor saveFloor(FloorModelSave floorModelSave);

	/**
	 * 更新楼管理基本信息
	 * @param floorModelUpdate 更新的信息
	 * @return
	 */
	public Floor updateFloor(FloorModelUpdate floorModelUpdate);

	/**
     * 删除
     * @param id
     * @return
     */
	public Floor deleteById(Integer id);

	/**
	 * 根据id获取实体
	 * @param id
	 * @return
	 */
	public Floor findById(Integer id);


}

