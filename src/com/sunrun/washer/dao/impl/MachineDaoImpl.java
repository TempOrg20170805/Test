package com.sunrun.washer.dao.impl;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.hibernate.Query;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

import com.jeecms.common.hibernate4.Finder;
import com.jeecms.common.hibernate4.HibernateBaseDao;
import com.jeecms.common.page.Pagination;
import com.sunrun.washer.dao.MachineDao;
import com.sunrun.washer.entity.Machine;
import com.sunrun.washer.model.MachineModel;
import com.sunrun.washer.model.MachineWithUserModel;
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

	/**
	 * logback打印日志，打印等级配置在logback.xml文件
	 */
	private static Logger logger = LoggerFactory.getLogger(MachineDaoImpl.class); 
	
	
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

	@Override
	public Integer updateOnline(String sn, Integer online) {
		// TODO Auto-generated method stub
		try {
			if(!StringUtils.isBlank(sn))
			{
				// 注意：故障洗衣机不更新状态
				String hql="update Machine set online=:online where machineNo=:sn and isTrouble <> 1";
				Query query= getSession().createQuery(hql);
				query.setParameter("online", online);
				query.setParameter("sn", sn);
				return query.executeUpdate();
			}
		
		} catch (Exception e) {
			// TODO: handle exception
			logger.error("MachineDaoImpl-updateOnline", e);
		}
		return 0;
	}

	@Override
	public Integer updateStatus(String sn, Integer status) {
		// TODO Auto-generated method stub
		try {
			if(!StringUtils.isBlank(sn))
			{
				// 注意：故障洗衣机不更新状态
				String hql="update Machine set status=:status where machineNo=:sn and isTrouble <> 1";
				Query query= getSession().createQuery(hql);
				query.setParameter("status", status);
				query.setParameter("sn", sn);
				return query.executeUpdate();
			}
		
		} catch (Exception e) {
			// TODO: handle exception
			logger.error("MachineDaoImpl-updateStatus", e);
		}
		return 0;
	}

	@Override
	public Integer updateAllMachineDeleteFloorLayerByFloorLayerIds(
			Integer[] floorLayerIds) {
		String hql = "update Machine set floorLayer=null,floorLayerX=0,floorLayerY=0 where floorLayer.floorLayerId in (:ids)";
		return (Integer) getSession().createQuery(hql)
				.setParameterList("ids", floorLayerIds).executeUpdate();
	}

	@Override
	public Pagination queryMachineWithUserByModel(
			MachineWithUserModel machineWithUserModel, Integer pageNo,
			Integer pageSize) {
		
		// 初始化分页
		Pagination page=new Pagination();
		page.setPageNo(pageNo);
		page.setPageSize(pageSize);
		if(pageNo<=0)
			pageNo=1;
		int bsize= (pageNo - 1) * pageSize;
		
		
		String sqlFrom = "from washer_machine wm LEFT JOIN washer_user_machine wum on wm.machine_id=wum.machine_id LEFT JOIN jc_user ju on wum.user_id=ju.user_id and wum.use_type=1 and wum.auth_level=1 where wm.status <> 0 ";
		// 统计总数的sql
		String countSql = "select count(wm.machine_id) as count ";
		// 查询数据的Sql
		String sql = "select wm.machine_id as machine_id, wm.`name` AS `name`, wm.type as type, wm.machine_no as machineNo, ju.username as userName ";
		
		String endSql = "";
		if (machineWithUserModel.getType().equals(1)) {
			endSql = endSql + " and userName is null ";
		}
		else if (machineWithUserModel.getType().equals(2)) {
			endSql = endSql + " and userName is not null ";
		}
		
		if (StringUtils.isNotBlank(machineWithUserModel.getMachineNo())) {
			endSql = endSql + " and wm.machine_no like :machineNo ";
		}
		
		String pageSql = " LIMIT :bsize,:pageSize";
		
		// 查询总数量
		Query queryCount = getSession().createSQLQuery(countSql+sqlFrom+endSql);
		if (StringUtils.isNotBlank(machineWithUserModel.getMachineNo())) {
			queryCount = queryCount.setString("machineNo", "%"+machineWithUserModel.getMachineNo()+"%");
		}
		List queryCountList = queryCount.list();
		if (queryCountList != null && queryCountList.size() > 0) {
			Object result = queryCountList.get(0);
			if (result != null) {
				page.setTotalCount(((BigInteger)result).intValue());
			} else {
				page.setTotalCount(0);
			}
		}
		
		
		Query query = getSession().createSQLQuery(sql+sqlFrom+endSql+pageSql);
		if (StringUtils.isNotBlank(machineWithUserModel.getMachineNo())) {
			query = query.setString("machineNo", "%"+machineWithUserModel.getMachineNo()+"%");
		}
		query = query.setInteger("bsize", bsize);
		query = query.setInteger("pageSize", pageSize);
		List<Object[]> pusList = (List<Object[]>)query.list();
		List<Machine> machines = new ArrayList<>();
		for (Object[] results : pusList) {
			Machine machine = new Machine((Integer)results[0],(String) results[1], (String) results[2], (String) results[3], (String) results[4]);
			machines.add(machine);
		}
		page.setList(machines);
		
		return page;
	}

}

