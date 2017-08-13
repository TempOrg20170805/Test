package com.sunrun.washer.manager;
import com.sunrun.washer.model.*;import com.sunrun.washer.entity.*;


import java.util.List;

import com.jeecms.common.page.Pagination;
/**
 * 文 件 名 : WasherOrderMng.java
 * 创 建 人： 金明明
 * 日 期：2017-8-7
 * 修 改 人： 
 * 日 期： 
 * 描 述：订单 Mng层
 */
public interface WasherOrderMng {
	
	/**
	 * 查询订单管理列表
	 * @param washerOrderModel 订单管理查询条件
	 * @param pageNo 当前页
	 * @param pageSize 每页数据量
	 * @return
	 */
	public Pagination queryWasherOrderByModel(WasherOrderModel washerOrderModel, Integer pageNo, Integer pageSize);

	/**
	 * 保存订单管理
	 * @param washerOrderModelSave 保存的信息对象
	 * @return
	 */
	public WasherOrder saveWasherOrder(WasherOrderModelSave washerOrderModelSave);

	/**
	 * 更新订单管理基本信息
	 * @param washerOrderModelUpdate 更新的信息
	 * @return
	 */
	public WasherOrder updateWasherOrder(WasherOrderModelUpdate washerOrderModelUpdate);

	/**
     * 删除
     * @param id
     * @return
     */
	public WasherOrder deleteById(Integer id);

	/**
	 * 根据id获取实体
	 * @param id
	 * @return
	 */
	public WasherOrder findById(Integer id);
	
	/**
	 * 更新支付信息
	 * @param wahserOrderId 订单ID
	 * @param payPlatform 支付平台
	 * @param payMessage 信息
	 * @return
	 */
	public WasherOrder updatePayMsg(Integer wahserOrderId, Integer payPlatform, String payMessage);
	
	/**
	 * 根据订单号查询
	 * @param outSn
	 * @return
	 */
	public WasherOrder findByOutSn(String outSn);

	/**
	 * 支付成功
	 * @param outSn 订单编号
	 * @return
	 */
	public WasherOrder paySuccess(String outSn);
}

