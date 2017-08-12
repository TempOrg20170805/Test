package com.sunrun.washer.dao.impl;
import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Repository;

import com.jeecms.common.hibernate4.Finder;
import com.jeecms.common.hibernate4.HibernateBaseDao;
import com.jeecms.common.page.Pagination;
import com.sunrun.washer.dao.UserMachineDao;
import com.sunrun.washer.entity.UserMachine;
import com.sunrun.washer.model.UserMachineModel;
/**
 * 文 件 名 : UserMachineDaoImpl.java
 * 创 建 人： 金明明
 * 日 期：2017-7-30
 * 修 改 人： 
 * 日 期： 
 * 描 述：用户关联洗衣机 DaoImpl
 */
@Repository
public class UserMachineDaoImpl extends HibernateBaseDao<UserMachine, Integer> implements UserMachineDao {

	@Override
	public Pagination queryUserMachineByModel(UserMachineModel userMachineModel, Integer pageNo, Integer pageSize) {
		Finder f = queryUserMachineBaseFinder("select bean from UserMachine bean where 1=1 ");

		if (userMachineModel.getUserId() != null) {
			f.append(" and bean.cmsUser.id =:userId");
			f.setParam("userId", userMachineModel.getUserId());
		}
		
		if (StringUtils.isNotBlank(userMachineModel.getMachineNo())) {
			f.append(" and bean.machine.machineNo like :machineNo");
			f.setParam("machineNo", "%"+userMachineModel.getMachineNo()+"%");
		}
		
		// 1.未投放的洗衣机过滤条件
		if (userMachineModel.getQueryType().equals(1)) {
			f.append(" and (bean.machine.floorLayerX is null or bean.machine.floorLayerX <= 0)");
			f.append(" and (bean.machine.floorLayerY is null or bean.machine.floorLayerY <= 0)");
		}

		f.append(" order by bean.machine.createTime desc");
		return find(f, pageNo, pageSize);
	}

	@Override
	public UserMachine save(UserMachine userMachine) {
		getSession().save(userMachine);
		return userMachine;
	}


	@Override
	public UserMachine deleteById(Integer userMachineId) {
		UserMachine entity = super.get(userMachineId);
		if (entity != null) {
			getSession().delete(entity);
		}
		return entity;
	}

	@Override
	public UserMachine findById(Integer userMachineId) {
		return get(userMachineId);
	}


	@Override
	protected Class<UserMachine> getEntityClass() {
		return UserMachine.class;
	}

	/**
	 * 创建用户关联洗衣机基本查询语句
	 * @param baseHQL 必须为UserMachine类查询，且临时命名为bean 例："from UserMachine bean where 1=1"
	 * @return
	 */
	private Finder queryUserMachineBaseFinder(String baseHQL) {
		Finder finder = Finder.create(baseHQL);
		// 此处增加公用的过滤条件，例如：每个用户关联洗衣机要是有效的
		// finder.append(" and bean.status <> 0");
		return finder;
	}
	
	@Override
	public List<UserMachine> findUserMachineListByMachine(Integer machineId,
			Integer useType) {
		Finder f = queryUserMachineBaseFinder("select bean from UserMachine bean where 1=1 ");

		if (machineId != null) {
			f.append(" and bean.machine.machineId =:machineId");
			f.setParam("machineId", machineId);
		}
		
		if (useType != null) {
			f.append(" and bean.useType = :useType");
			f.setParam("useType", useType);
		}

		f.append(" order by bean.machine.createTime desc");
		return find(f);
	}
}

