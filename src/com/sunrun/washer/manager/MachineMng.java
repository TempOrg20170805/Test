package com.sunrun.washer.manager;
import com.sunrun.washer.model.*;import com.sunrun.washer.entity.*;

import java.util.List;
import java.util.Map;

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
	 * 查询该楼有多少洗衣机
	 * @param floorId
	 * @return
	 */
	public List<Machine> queryMachineByFloor(Integer floorId);
	
	/**
	 * 查询该楼层有多少洗衣机
	 * @param floorId
	 * @return
	 */
	public List<Machine> queryMachineByFloorLayer(Integer floorLayerId);
	
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
	 * 渠道商删除投放的洗衣机
	 * @param machineId
	 * @return
	 */
	public Machine updateUserMachineFloorLayerDelete(Integer machineId);

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
	
	/**
	* @author: HL
	* @date: 2017年8月9日 下午2:23:48
	* @function: updateOnline  
	* @Description: 更新洗衣机在线离线状态 注意：故障洗衣机不更新状态
	* @param: @param onlineMap
	* @param: @return
	* @return: Integer
	* @throws
	*/
	public Integer updateOnline(Map<String, Integer> onlineMap);
	
	/**
	* @author: HL
	* @date: 2017年8月9日 下午3:05:23
	* @function: updateStatus  
	* @Description: 更新洗衣机工作状态  注意：故障洗衣机不更新状态
	* @param: @param status
	* @param: @return
	* @return: Integer
	* @throws
	 */
	public Integer updateStatus(String sn,Integer status);


}

