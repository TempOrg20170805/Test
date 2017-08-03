package com.sunrun.washer.manager;
import com.sunrun.washer.model.*;import com.sunrun.washer.entity.*;


import java.util.List;

import com.jeecms.common.page.Pagination;
/**
 * 文 件 名 : MachineMng.java
 * 创 建 人： 金明明
 * 日 期：2017-7-31
 * 修 改 人： 
 * 日 期： 
 * 描 述：洗衣机 Mng层
 */
public interface MachineMng {

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
	 * @param machineModelSave 保存的信息对象
	 * @return
	 */
	public Machine saveMachine(MachineModelSave machineModelSave);

	/**
	 * 更新洗衣机管理基本信息
	 * @param machineModelUpdate 更新的信息
	 * @return
	 */
	public Machine updateMachine(MachineModelUpdate machineModelUpdate);
	
	/**
	 * 渠道商投放洗衣机
	 * @param machineModelUpdate 更新的信息
	 * @return
	 */
	public Machine updateMachine(UserMachineModelUpdatePutIn userMachineModelSaveUpdatePutIn);

	/**
     * 删除
     * @param id
     * @return
     */
	public Machine deleteById(Integer id);

	/**
	 * 根据id获取实体
	 * @param id
	 * @return
	 */
	public Machine findById(Integer id);


}

