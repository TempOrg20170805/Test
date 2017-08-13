package com.sunrun.washer.dao.impl;
import com.sunrun.washer.dao.*;import com.sunrun.washer.model.*;import com.sunrun.washer.entity.*;
import com.sunrun.washer.enums.WasherOrderStatusEnum;

import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Repository;

import com.jeecms.common.hibernate4.Finder;
import com.jeecms.common.hibernate4.HibernateBaseDao;
import com.jeecms.common.page.Pagination;
/**
 * 文 件 名 : WasherOrderDaoImpl.java
 * 创 建 人： 金明明
 * 日 期：2017-8-7
 * 修 改 人： 
 * 日 期： 
 * 描 述：订单 DaoImpl
 */
@Repository
public class WasherOrderDaoImpl extends HibernateBaseDao<WasherOrder, Integer> implements WasherOrderDao {

	@Override
	public Pagination queryWasherOrderByModel(WasherOrderModel washerOrderModel, Integer pageNo, Integer pageSize) {
		Finder f = queryWasherOrderBaseFinder("select bean from WasherOrder bean where 1=1 ");

		if (StringUtils.isNotBlank(washerOrderModel.getOutSn())) {
			f.append(" and bean.outSn like :outSn");
			f.setParam("outSn", "%"+washerOrderModel.getOutSn()+"%");
		}
		
		if (StringUtils.isNotBlank(washerOrderModel.getMachineNo())) {
			f.append(" and bean.machineNo like :machineNo");
			f.setParam("machineNo", "%"+washerOrderModel.getMachineNo()+"%");
		}
		
		if (StringUtils.isNotBlank(washerOrderModel.getBuyerName())) {
			f.append(" and bean.buyer.username like :buyerName");
			f.setParam("buyerName", "%"+washerOrderModel.getBuyerName()+"%");
		}
		
		if (washerOrderModel.getOrderState() !=null && washerOrderModel.getOrderState() >= 10) {
			f.append(" and bean.orderState = :orderState");
			f.setParam("orderState", washerOrderModel.getOrderState());
		}
		
		if (washerOrderModel.getBuyerId() !=null) {
			f.append(" and bean.buyer.id = :buyerId");
			f.setParam("buyerId", washerOrderModel.getBuyerId());
		}

		f.append(" order by bean.addTime desc");
		return find(f, pageNo, pageSize);
	}

	@Override
	public WasherOrder save(WasherOrder washerOrder) {
		getSession().save(washerOrder);
		return washerOrder;
	}


	@Override
	public WasherOrder deleteById(Integer washerOrderId) {
		WasherOrder entity = super.get(washerOrderId);
		if (entity != null) {
			getSession().delete(entity);
		}
		return entity;
	}

	@Override
	public WasherOrder findById(Integer washerOrderId) {
		return get(washerOrderId);
	}


	@Override
	protected Class<WasherOrder> getEntityClass() {
		return WasherOrder.class;
	}

	/**
	 * 创建订单基本查询语句
	 * @param baseHQL 必须为WasherOrder类查询，且临时命名为bean 例："from WasherOrder bean where 1=1"
	 * @return
	 */
	private Finder queryWasherOrderBaseFinder(String baseHQL) {
		Finder finder = Finder.create(baseHQL);
		// 此处增加公用的过滤条件，例如：每个订单要是有效的
		finder.append(" and bean.orderState <> :deleteStatus");
		finder.setParam("deleteStatus", WasherOrderStatusEnum.DELETE.getCode());
		return finder;
	}

	@Override
	public WasherOrder findByOutSn(String outSn) {
		return findUniqueByProperty("outSn", outSn);
	}

}

