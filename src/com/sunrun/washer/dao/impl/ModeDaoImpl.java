package com.sunrun.washer.dao.impl;
import com.sunrun.washer.dao.*;import com.sunrun.washer.model.*;import com.sunrun.washer.entity.*;
import java.util.List;
import org.springframework.stereotype.Repository;

import com.jeecms.common.hibernate4.Finder;
import com.jeecms.common.hibernate4.HibernateBaseDao;
import com.jeecms.common.page.Pagination;
/**
 * 文 件 名 : ModeDaoImpl.java
 * 创 建 人： 金明明
 * 日 期：2017-8-6
 * 修 改 人： 
 * 日 期： 
 * 描 述：洗衣模式 DaoImpl
 */
@Repository
public class ModeDaoImpl extends HibernateBaseDao<Mode, Integer> implements ModeDao {

	@Override
	public List<Mode> queryModeListByModel(ModeModel modeModel) {
		Finder f = queryModeBaseFinder("select bean from Mode bean where 1=1 ");

		/*if (判断是否有查询条件) {
			f.append(" and bean.** =:**");
			f.setParam("**", **);
		}*/

		return find(f);
	}

	@Override
	public Mode save(Mode mode) {
		getSession().save(mode);
		return mode;
	}


	@Override
	public Mode deleteById(Integer modeId) {
		Mode entity = super.get(modeId);
		if (entity != null) {
			getSession().delete(entity);
		}
		return entity;
	}

	@Override
	public Mode findById(Integer modeId) {
		return get(modeId);
	}


	@Override
	protected Class<Mode> getEntityClass() {
		return Mode.class;
	}

	/**
	 * 创建洗衣模式基本查询语句
	 * @param baseHQL 必须为Mode类查询，且临时命名为bean 例："from Mode bean where 1=1"
	 * @return
	 */
	private Finder queryModeBaseFinder(String baseHQL) {
		Finder finder = Finder.create(baseHQL);
		// 此处增加公用的过滤条件，例如：每个洗衣模式要是有效的
		finder.append(" and bean.status <> 0");
		return finder;
	}

}

