package com.sunrun.washer.dao;
import com.sunrun.washer.model.*;import com.sunrun.washer.entity.*;


import java.util.List;

import com.jeecms.common.page.Pagination;
import com.jeecms.common.hibernate4.Updater;
/**
 * 文 件 名 : MachineDao.java
 * 创 建 人： 金明明
 * 日 期：2017-7-31
 * 修 改 人： 
 * 日 期： 
 * 描 述：洗衣机 Dao层
 */
public interface MachineDao {

	/**
	 * 查询洗衣机管理列表
	 * @param machineModel 洗衣机管理查询条件
	 * @param pageNo 当前页
	 * @param pageSize 每页数据量
	 * @return
	 */
	public Pagination queryMachineByModel(MachineModel machineModel, Integer pageNo, Integer pageSize);

	/**
	 * 保存洗衣机管理
	 * @param 洗衣机管理
	 * @return
	 */
	public Machine save(Machine machine);

	public Machine updateByUpdater(Updater<Machine> updater);

	/**
     * 删除洗衣机管理
     * @param 洗衣机管理Id
     * @return
     */
	public Machine deleteById(Integer machineId);

    /**
     * 根据Id获取实体
     * @param 洗衣机管理Id
     * @return
     */
	public Machine findById(Integer machineId);


}

