package com.sunrun.washer.manager;
import com.sunrun.washer.model.*;import com.sunrun.washer.entity.*;


import java.util.List;

import com.jeecms.common.page.Pagination;
/**
 * 文 件 名 : UserMachineMng.java
 * 创 建 人： 金明明
 * 日 期：2017-7-30
 * 修 改 人： 
 * 日 期： 
 * 描 述：用户关联洗衣机 Mng层
 */
public interface UserMachineMng {

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
	 * @param userMachineModelSave 保存的信息对象
	 * @return
	 */
	public Machine updateUserMachinePutIn(UserMachineModelUpdatePutIn userMachineModelUpdatePutIn);
	
	/**
	 * 渠道商删除投放的洗衣机
	 * @param machineId 洗衣机Id
	 * @return
	 */
	public Machine updateUserMachineFloorLayerDelete(Integer machineId);
	/**
	 * 保存用户关联洗衣机管理
	 * @param userMachineModelSave 保存的信息对象
	 * @return
	 */
	public UserMachine saveUserMachine(UserMachineModelSave userMachineModelSave);

	/**
	 * 更新用户关联洗衣机管理基本信息
	 * @param userMachineModelUpdate 更新的信息
	 * @return
	 */
	public UserMachine updateUserMachine(UserMachineModelUpdate userMachineModelUpdate);

	/**
     * 删除
     * @param id
     * @return
     */
	public UserMachine deleteById(Integer id);

	/**
	 * 根据id获取实体
	 * @param id
	 * @return
	 */
	public UserMachine findById(Integer id);


	/**
	 * 获取拥有该洗衣机的用户列表
	 * 注意：后续若有下级代理，这里需要加上权限限制
	 * @param machineId
	 * @return
	 */
	public List<UserMachine> findUserMachineListByMachine(Integer machineId);
}

