package com.sunrun.red.dao.impl;

import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Repository;

import com.jeecms.common.hibernate4.Finder;
import com.jeecms.common.hibernate4.HibernateBaseDao;
import com.jeecms.common.page.Pagination;
import com.sunrun.red.dao.HelpDao;
import com.sunrun.red.entity.Help;

@Repository
public class HelpDaoImpl extends HibernateBaseDao<Help, Integer> implements HelpDao{

	@Override
	public Pagination getPage(String name, Integer pageNo, Integer pageSize) {
		Finder f = Finder.create("select bean from Help bean where 1=1");
		if(!StringUtils.isBlank(name)){
			f.append(" and bean.name like:name");
	   		 f.setParam("name", "%" + name + "%" );
		}
		f.append(" order by bean.id desc");
		return find(f, pageNo, pageSize);
	}

	@Override
	public List<Help> getPageForApp(Integer id) {
		Finder f = Finder.create("select bean from Help bean where 1=1");
		
		if(id!=null){
			f.append(" and bean.id =:id");
	   		f.setParam("id", id );
		}
		f.append(" order by bean.id desc");
		return find(f);
	}

	@Override
	public Help save(Help bean) {
		getSession().save(bean);
		return bean;
	}

	@Override
	public Help findById(Integer id) {
		Help bean = super.get(id);
		return bean;
	}

	@Override
	public Help deleteById(Integer id) {
		Help bean  = super.get(id);
		if(bean!=null){
			getSession().delete(bean);
		}
		return bean;
	}

	@Override
	protected Class<Help> getEntityClass() {
		// TODO Auto-generated method stub
		return Help.class;
	}

}
