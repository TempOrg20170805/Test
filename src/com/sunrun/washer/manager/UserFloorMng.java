package com.sunrun.washer.manager;
import com.sunrun.washer.model.*;import com.sunrun.washer.entity.*;


import java.util.List;

import com.jeecms.common.page.Pagination;
/**
 * 文 件 名 : UserFloorMng.java
 * 创 建 人： 金明明
 * 日 期：2017-7-31
 * 修 改 人： 
 * 日 期： 
 * 描 述：用户关联楼 Mng层
 */
public interface UserFloorMng {

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
	 * @param userFloorModelSave 保存的信息对象
	 * @return
	 */
	public UserFloor saveUserFloor(UserFloorModelSave userFloorModelSave);

	/**
     * 删除
     * @param id
     * @return
     */
	public UserFloor deleteById(Integer id);

	/**
	 * 根据id获取实体
	 * @param id
	 * @return
	 */
	public UserFloor findById(Integer id);


}

