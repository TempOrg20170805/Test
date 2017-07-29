package com.sunrun.washer.dao.impl;

import org.springframework.stereotype.Repository;

import com.jeecms.common.hibernate4.Finder;
import com.jeecms.common.hibernate4.HibernateBaseDao;
import com.jeecms.common.page.Pagination;
import com.sunrun.washer.dao.FeedbackDao;
import com.sunrun.washer.entity.Feedback;
@Repository
public class FeedbackDaoImpl extends HibernateBaseDao<Feedback, Integer> implements FeedbackDao {

	@Override
	public Pagination getPage(Integer pageNo, Integer pageSize) {
		// TODO Auto-generated method stub
		Finder f = Finder.create("select bean from Feedback bean where 1=1 ");
		f.append(" order by bean.id asc");
		return find(f,pageNo,pageSize);
	}

	@Override
	public Feedback deleteById(Integer id) {
		// TODO Auto-generated method stub
		Feedback bean  = super.get(id);
		if(bean!=null){
			getSession().delete(bean);
		}
		return bean;
	}

	@Override
	public Feedback save(Feedback bean) {
		// TODO Auto-generated method stub
		getSession().save(bean);
		return bean;
	}

	@Override
	public Feedback findById(Integer id) {
		// TODO Auto-generated method stub
		Feedback  bean=super.get(id);
		return bean;
	}

	@Override
	protected Class<Feedback> getEntityClass() {
		// TODO Auto-generated method stub
		return Feedback.class;
	}

}
