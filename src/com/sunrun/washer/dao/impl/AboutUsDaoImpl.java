package com.sunrun.washer.dao.impl;

import com.jeecms.common.hibernate4.Finder;
import com.jeecms.common.hibernate4.HibernateBaseDao;
import com.sunrun.washer.dao.AboutUsDao;
import com.sunrun.washer.entity.AboutUs;

public class AboutUsDaoImpl extends HibernateBaseDao<AboutUs, Integer> implements AboutUsDao{

	@Override
	public AboutUs save(AboutUs bean) {
		// TODO Auto-generated method stub
		getSession().save(bean);
		return bean;
	}

	@Override
	public AboutUs findById(Integer id) {
		// TODO Auto-generated method stub
		AboutUs bean = super.get(id);
		return bean;
	}
	
	@Override
	protected Class<AboutUs> getEntityClass() {
		// TODO Auto-generated method stub
		return AboutUs.class;
	}

	@Override
	public AboutUs getList() {
		// TODO Auto-generated method stub
		Finder f = Finder.create("select bean from AboutUs bean");
		if(!find(f).isEmpty()){
			return (AboutUs) find(f).get(0);
		}else{
			return null;
		}
	}

}
