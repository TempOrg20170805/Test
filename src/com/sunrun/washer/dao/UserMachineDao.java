package com.sunrun.washer.dao;
import com.sunrun.washer.model.*;import com.sunrun.washer.entity.*;


import java.util.List;

import com.jeecms.common.page.Pagination;
import com.jeecms.common.hibernate4.Updater;
/**
 * 文 件 名 : UserMachineDao.java
 * 创 建 人： 金明明
 * 日 期：2017-7-30
 * 修 改 人： 
 * 日 期： 
 * 描 述：用户关联洗衣机 Dao层
 */
public interface UserMachineDao {

	/**
	 * 查询用户关联洗衣机管理列表
	 * @param userMachineModel 用户关联洗衣机管理查询条件
	 * @param pageNo 当前页
	 * @param pageSize 每页数据量
	 * @return
	 */
	public Pagination queryUserMachineByModel(UserMachineModel userMachineModel, Integer pageNo, Integer pageSize);

	/**
	 * 保存用户关联洗衣机管理
	 * @param 用户关联洗衣机管理
	 * @return
	 */
	public UserMachine save(UserMachine userMachine);

	public UserMachine updateByUpdater(Updater<UserMachine> updater);

	/**
     * 删除用户关联洗衣机管理
     * @param 用户关联洗衣机管理Id
     * @return
     */
	public UserMachine deleteById(Integer userMachineId);

    /**
     * 根据Id获取实体
     * @param 用户关联洗衣机管理Id
     * @return
     */
	public UserMachine findById(Integer userMachineId);

	/**
	 * 获取使用类型的用户列表
	 * @param machineId
	 * @return
	 */
	public List<UserMachine> findUserMachineListByMachine(Integer machineId, Integer useType);
}

