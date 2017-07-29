package com.sunrun.washer.dao.impl;

import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import com.jeecms.common.hibernate4.Finder;
import com.jeecms.common.hibernate4.HibernateBaseDao;
import com.jeecms.common.page.Pagination;
import com.sunrun.washer.dao.DictionaryDao;
import com.sunrun.washer.entity.Dictionary;


@Repository
public class DictionaryDaoImpl extends HibernateBaseDao<Dictionary, Integer> implements DictionaryDao {
	public Pagination getPage(String queryType,int pageNo, int pageSize) {
		Criteria crit = createCriteria();
		if(StringUtils.isNotBlank(queryType)){
			Criterion cron = Restrictions.like("type",queryType);
			crit.add(cron);
		}
		Pagination page = findByCriteria(crit, pageNo, pageSize);
		return page;
	}
	
	@SuppressWarnings("unchecked")
	public List<Dictionary> getList(String type){
//		Criterion cron = Restrictions.like("type",type); 
//		return findByCriteria(cron);
		Finder f = Finder.create("select bean from Dictionary bean where 1=1 ");
		if(StringUtils.isNotBlank(type) ){
			 f.append(" and bean.type like:type");
	   		 f.setParam("type", "%"+type+"%" );
		}
		f.append(" order by bean.id desc");
		return find(f);
		
	}
	
	@SuppressWarnings("unchecked")
	public List<String> getTypeList(){
		Finder f=Finder.create("select  type from Dictionary dic group by type ");
		return find(f);
	}

	public Dictionary findById(Integer id) {
		Dictionary entity = get(id);
		return entity;
	}
	
	@SuppressWarnings("unchecked")
	public Dictionary findByValue(String type,String value){
		Criterion cron_type=null,cron_value=null;
		if(StringUtils.isNotBlank(type)){
			cron_type = Restrictions.like("type",type);
		}
		if(StringUtils.isNotBlank(value)){
			cron_value = Restrictions.like("value",value);
		}
		List<Dictionary>li=findByCriteria(cron_type,cron_value);
		if(li!=null&&li.size()>0){
			return li.get(0);
		}else{
			return null;
		}
	}

	public Dictionary save(Dictionary bean) {
		getSession().save(bean);
		return bean;
	}

	public Dictionary deleteById(Integer id) {
		Dictionary entity = super.get(id);
		if (entity != null) {
			getSession().delete(entity);
		}
		return entity;
	}
	
	public int countByValue(String value, String type) {
		String hql = "select count(*) from Dictionary bean where bean.value=:value and bean.type=:type";
		Query query = getSession().createQuery(hql);
		query.setParameter("value", value);
		query.setParameter("type", type);
		return ((Number) query.iterate().next()).intValue();
	}
	
	@Override
	protected Class<Dictionary> getEntityClass() {
		return Dictionary.class;
	}
}