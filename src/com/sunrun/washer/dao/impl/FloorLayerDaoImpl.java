package com.sunrun.washer.dao.impl;
import com.sunrun.washer.dao.*;import com.sunrun.washer.model.*;import com.sunrun.washer.entity.*;
import java.util.List;
import org.springframework.stereotype.Repository;

import com.jeecms.common.hibernate4.Finder;
import com.jeecms.common.hibernate4.HibernateBaseDao;
import com.jeecms.common.page.Pagination;
/**
 * 文 件 名 : FloorLayerDaoImpl.java
 * 创 建 人： 金明明
 * 日 期：2017-7-30
 * 修 改 人： 
 * 日 期： 
 * 描 述：楼层 DaoImpl
 */
@Repository
public class FloorLayerDaoImpl extends HibernateBaseDao<FloorLayer, Integer> implements FloorLayerDao {

	@Override
	public Pagination queryFloorLayerByModel(FloorLayerModel floorLayerModel, Integer pageNo, Integer pageSize) {
		Finder f = queryFloorLayerBaseFinder("select bean from FloorLayer bean where 1=1 ");

		if (floorLayerModel.getFloorId() != null) {
			f.append(" and bean.floor.floorId =:floorId");
			f.setParam("floorId", floorLayerModel.getFloorId());
		}

		f.append(" order by bean.layer asc");
		return find(f, pageNo, pageSize);
	}

	@Override
	public FloorLayer save(FloorLayer floorLayer) {
		getSession().save(floorLayer);
		return floorLayer;
	}


	@Override
	public FloorLayer deleteById(Integer floorLayerId) {
		FloorLayer entity = super.get(floorLayerId);
		if (entity != null) {
			getSession().delete(entity);
		}
		return entity;
	}

	@Override
	public FloorLayer findById(Integer floorLayerId) {
		return get(floorLayerId);
	}


	@Override
	protected Class<FloorLayer> getEntityClass() {
		return FloorLayer.class;
	}

	/**
	 * 创建楼层基本查询语句
	 * @param baseHQL 必须为FloorLayer类查询，且临时命名为bean 例："from FloorLayer bean where 1=1"
	 * @return
	 */
	private Finder queryFloorLayerBaseFinder(String baseHQL) {
		Finder finder = Finder.create(baseHQL);
		// 此处增加公用的过滤条件，例如：每个楼层要是有效的
		// finder.append(" and bean.status <> 0");
		return finder;
	}

}

