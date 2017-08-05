package com.sunrun.washer.dao.impl;
import com.sunrun.washer.dao.*;import com.sunrun.washer.model.*;import com.sunrun.washer.entity.*;

import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Repository;

import com.jeecms.common.hibernate4.Finder;
import com.jeecms.common.hibernate4.HibernateBaseDao;
import com.jeecms.common.page.Pagination;
/**
 * 文 件 名 : MachineDaoImpl.java
 * 创 建 人： 金明明
 * 日 期：2017-7-31
 * 修 改 人： 
 * 日 期： 
 * 描 述：洗衣机 DaoImpl
 */
@Repository
public class MachineDaoImpl extends HibernateBaseDao<Machine, Integer> implements MachineDao {

	@Override
	public Pagination queryMachineByModel(MachineModel machineModel, Integer pageNo, Integer pageSize) {
		Finder f = queryMachineBaseFinder("select bean from Machine bean where 1=1 ");

		
		
		if (StringUtils.isNotBlank(machineModel.getMachineNo())) {
			f.append(" and bean.machineNo like :machineNo");
			f.setParam("machineNo", "%"+machineModel.getMachineNo()+"%");
		}
		
		if (machineModel.getFloorLayerId() != null && machineModel.getFloorLayerId() > 0) {
			f.append(" and bean.floorLayer.floorLayerId =:floorLayerId");
			f.setParam("floorLayerId", machineModel.getFloorLayerId());
		}
		
		f.append(" order by bean.createTime desc");

		return find(f, pageNo, pageSize);
	}

	@Override
	public Machine save(Machine machine) {
		getSession().save(machine);
		return machine;
	}


	@Override
	public Machine deleteById(Integer machineId) {
		Machine entity = super.get(machineId);
		if (entity != null) {
			getSession().delete(entity);
		}
		return entity;
	}

	@Override
	public Machine findById(Integer machineId) {
		return get(machineId);
	}


	@Override
	protected Class<Machine> getEntityClass() {
		return Machine.class;
	}

	/**
	 * 创建洗衣机基本查询语句
	 * @param baseHQL 必须为Machine类查询，且临时命名为bean 例："from Machine bean where 1=1"
	 * @return
	 */
	private Finder queryMachineBaseFinder(String baseHQL) {
		Finder finder = Finder.create(baseHQL);
		// 此处增加公用的过滤条件，例如：每个洗衣机要是有效的
		finder.append(" and bean.status <> 0");
		return finder;
	}

	@Override
	public List<Machine> queryMachineByFloor(Integer floorId) {
		Finder f = queryMachineBaseFinder("select bean from Machine bean where 1=1 ");
		f.append(" and bean.floorLayer.floor.floorId =:floorId");
		f.setParam("floorId", floorId);
		f.append(" order by bean.createTime desc");
		return find(f);
	}

	@Override
	public List<Machine> queryMachineByFloorLayer(Integer floorLayerId) {
		Finder f = queryMachineBaseFinder("select bean from Machine bean where 1=1 ");
		f.append(" and bean.floorLayer.floorLayerId =:floorLayerId");
		f.setParam("floorLayerId", floorLayerId);
		f.append(" order by bean.createTime desc");
		return find(f);
	}

}

