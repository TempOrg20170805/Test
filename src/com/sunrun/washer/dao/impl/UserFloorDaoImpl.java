package com.sunrun.washer.dao.impl;
import com.sunrun.washer.dao.*;import com.sunrun.washer.model.*;import com.sunrun.washer.entity.*;
import java.util.List;
import org.springframework.stereotype.Repository;

import com.jeecms.common.hibernate4.Finder;
import com.jeecms.common.hibernate4.HibernateBaseDao;
import com.jeecms.common.page.Pagination;
/**
 * 文 件 名 : UserFloorDaoImpl.java
 * 创 建 人： 金明明
 * 日 期：2017-7-31
 * 修 改 人： 
 * 日 期： 
 * 描 述：用户关联楼 DaoImpl
 */
@Repository
public class UserFloorDaoImpl extends HibernateBaseDao<UserFloor, Integer> implements UserFloorDao {

	@Override
	public Pagination queryUserFloorByModel(UserFloorModel userFloorModel, Integer pageNo, Integer pageSize) {
		Finder f = queryUserFloorBaseFinder("select bean from UserFloor bean where 1=1 ");

		if (userFloorModel.getUserId() != null) {
			f.append(" and bean.cmsUser.id =:userId");
			f.setParam("userId", userFloorModel.getUserId());
		}

		return find(f, pageNo, pageSize);
	}

	@Override
	public UserFloor save(UserFloor userFloor) {
		getSession().save(userFloor);
		return userFloor;
	}


	@Override
	public UserFloor deleteById(Integer userFloorId) {
		UserFloor entity = super.get(userFloorId);
		if (entity != null) {
			getSession().delete(entity);
		}
		return entity;
	}

	@Override
	public UserFloor findById(Integer userFloorId) {
		return get(userFloorId);
	}


	@Override
	protected Class<UserFloor> getEntityClass() {
		return UserFloor.class;
	}

	/**
	 * 创建用户关联楼基本查询语句
	 * @param baseHQL 必须为UserFloor类查询，且临时命名为bean 例："from UserFloor bean where 1=1"
	 * @return
	 */
	private Finder queryUserFloorBaseFinder(String baseHQL) {
		Finder finder = Finder.create(baseHQL);
		// 此处增加公用的过滤条件，例如：每个用户关联楼要是有效的
		finder.append(" and bean.floor.status <> 0");
		return finder;
	}

}

