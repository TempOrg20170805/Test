package com.sunrun.washer.dao;
import com.sunrun.washer.model.*;import com.sunrun.washer.entity.*;


import java.util.List;

import com.jeecms.common.page.Pagination;
import com.jeecms.common.hibernate4.Updater;
/**
 * 文 件 名 : WasherOrderDao.java
 * 创 建 人： 金明明
 * 日 期：2017-8-7
 * 修 改 人： 
 * 日 期： 
 * 描 述：订单 Dao层
 */
public interface WasherOrderDao {

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
	 * @param 订单管理
	 * @return
	 */
	public WasherOrder save(WasherOrder washerOrder);

	public WasherOrder updateByUpdater(Updater<WasherOrder> updater);

	/**
     * 删除订单管理
     * @param 订单管理Id
     * @return
     */
	public WasherOrder deleteById(Integer washerOrderId);

    /**
     * 根据Id获取实体
     * @param 订单管理Id
     * @return
     */
	public WasherOrder findById(Integer washerOrderId);

	/**
	 * 根据订单号查询
	 * @param outSn
	 * @return
	 */
	public WasherOrder findByOutSn(String outSn);

}

