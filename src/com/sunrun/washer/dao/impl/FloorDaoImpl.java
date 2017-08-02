package com.sunrun.washer.dao.impl;
import com.sunrun.washer.dao.*;import com.sunrun.washer.model.*;import com.sunrun.washer.entity.*;
import java.util.List;
import org.springframework.stereotype.Repository;

import com.jeecms.common.hibernate4.Finder;
import com.jeecms.common.hibernate4.HibernateBaseDao;
import com.jeecms.common.page.Pagination;
/**
 * 文 件 名 : FloorDaoImpl.java
 * 创 建 人： 金明明
 * 日 期：2017-8-1
 * 修 改 人： 
 * 日 期： 
 * 描 述：楼 DaoImpl
 */
@Repository
public class FloorDaoImpl extends HibernateBaseDao<Floor, Integer> implements FloorDao {

	@Override
	public Pagination queryFloorByModel(FloorModel floorModel, Integer pageNo, Integer pageSize) {
		Finder f = queryFloorBaseFinder("select bean from Floor bean where 1=1 ");

		/*if (判断是否有查询条件) {
			f.append(" and bean.** =:**");
			f.setParam("**", **);
		}*/

		return find(f, pageNo, pageSize);
	}

	@Override
	public Floor save(Floor floor) {
		getSession().save(floor);
		return floor;
	}


	@Override
	public Floor deleteById(Integer floorId) {
		Floor entity = super.get(floorId);
		if (entity != null) {
			getSession().delete(entity);
		}
		return entity;
	}

	@Override
	public Floor findById(Integer floorId) {
		return get(floorId);
	}


	@Override
	protected Class<Floor> getEntityClass() {
		return Floor.class;
	}

	/**
	 * 创建楼基本查询语句
	 * @param baseHQL 必须为Floor类查询，且临时命名为bean 例："from Floor bean where 1=1"
	 * @return
	 */
	private Finder queryFloorBaseFinder(String baseHQL) {
		Finder finder = Finder.create(baseHQL);
		// 此处增加公用的过滤条件，例如：每个楼要是有效的
		// finder.append(" and bean.status <> 0");
		return finder;
	}

}

