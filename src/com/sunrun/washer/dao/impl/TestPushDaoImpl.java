package com.sunrun.washer.dao.impl;
import com.sunrun.washer.dao.*;import com.sunrun.washer.model.*;import com.sunrun.washer.entity.*;

import java.util.List;
import org.springframework.stereotype.Repository;

import com.jeecms.common.hibernate4.Finder;
import com.jeecms.common.hibernate4.HibernateBaseDao;
import com.jeecms.common.page.Pagination;
/**
 * 文 件 名 : TestPushDaoImpl.java
 * 创 建 人： 金明明
 * 日 期：2017-8-26
 * 修 改 人： 
 * 日 期： 
 * 描 述：测试推送 DaoImpl
 */
@Repository
public class TestPushDaoImpl extends HibernateBaseDao<TestPush, Integer> implements TestPushDao {

	@Override
	public List<TestPush> queryTestPushListByModel(TestPushModel testPushModel) {
		Finder f = queryTestPushBaseFinder("select bean from TestPush bean where 1=1 ");

		/*if (判断是否有查询条件) {
			f.append(" and bean.** =:**");
			f.setParam("**", **);
		}*/

		return find(f);
	}

	@Override
	public TestPush save(TestPush testPush) {
		getSession().save(testPush);
		return testPush;
	}


	@Override
	public TestPush deleteById(Integer testPushId) {
		TestPush entity = super.get(testPushId);
		if (entity != null) {
			getSession().delete(entity);
		}
		return entity;
	}

	@Override
	public TestPush findById(Integer testPushId) {
		return get(testPushId);
	}


	@Override
	protected Class<TestPush> getEntityClass() {
		return TestPush.class;
	}

	/**
	 * 创建测试推送基本查询语句
	 * @param baseHQL 必须为TestPush类查询，且临时命名为bean 例："from TestPush bean where 1=1"
	 * @return
	 */
	private Finder queryTestPushBaseFinder(String baseHQL) {
		Finder finder = Finder.create(baseHQL);
		return finder;
	}

	@Override
	public TestPush findByMachineNo(String machineNo) {
		return findUniqueByProperty("machineNo", machineNo);
	}

}

