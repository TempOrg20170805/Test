package com.sunrun.washer.dao.impl;
import com.sunrun.washer.dao.*;import com.sunrun.washer.model.*;import com.sunrun.washer.entity.*;
import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Repository;

import com.jeecms.common.hibernate4.Finder;
import com.jeecms.common.hibernate4.HibernateBaseDao;
import com.jeecms.common.page.Pagination;
/**
 * 文 件 名 : TroubleDaoImpl.java
 * 创 建 人： 金明明
 * 日 期：2017-8-13
 * 修 改 人： 
 * 日 期： 
 * 描 述：故障 DaoImpl
 */
@Repository
public class TroubleDaoImpl extends HibernateBaseDao<Trouble, Integer> implements TroubleDao {

	@Override
	public Pagination queryTroubleByModel(TroubleModel troubleModel, Integer pageNo, Integer pageSize) {
		Finder f = queryTroubleBaseFinder("select bean from Trouble bean where 1=1 ");

		if (troubleModel.getStatus() != null && troubleModel.getStatus() > 0) {
			f.append(" and bean.status =:status");
			f.setParam("status", troubleModel.getStatus());
		}
		
		if (troubleModel.getUserId() != null) {
			f.append(" and bean.cmsUser.id =:userId");
			f.setParam("userId", troubleModel.getUserId());
		}
		
		if (StringUtils.isNotBlank(troubleModel.getMachineNo())) {
			f.append(" and bean.machine.machineNo like :machineNo");
			f.setParam("machineNo", "%"+troubleModel.getMachineNo()+"%");
		}
		
		if (StringUtils.isNotBlank(troubleModel.getUserName())) {
			f.append(" and bean.cmsUser.username like :username");
			f.setParam("username", "%"+troubleModel.getUserName()+"%");
		}

		f.append(" order by bean.createTime desc");
		return find(f, pageNo, pageSize);
	}

	@Override
	public Trouble save(Trouble trouble) {
		getSession().save(trouble);
		return trouble;
	}


	@Override
	public Trouble deleteById(Integer troubleId) {
		Trouble entity = super.get(troubleId);
		if (entity != null) {
			getSession().delete(entity);
		}
		return entity;
	}

	@Override
	public Trouble findById(Integer troubleId) {
		return get(troubleId);
	}


	@Override
	protected Class<Trouble> getEntityClass() {
		return Trouble.class;
	}

	/**
	 * 创建故障基本查询语句
	 * @param baseHQL 必须为Trouble类查询，且临时命名为bean 例："from Trouble bean where 1=1"
	 * @return
	 */
	private Finder queryTroubleBaseFinder(String baseHQL) {
		Finder finder = Finder.create(baseHQL);
		// 此处增加公用的过滤条件，例如：每个故障要是有效的
		finder.append(" and bean.status <> 0");
		return finder;
	}

}

