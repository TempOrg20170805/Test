package com.sunrun.red.dao.impl;

import java.util.List;
import org.apache.commons.lang.StringUtils;
import org.hibernate.Criteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;
import com.jeecms.common.hibernate4.Finder;
import com.jeecms.common.hibernate4.HibernateBaseDao;
import com.sunrun.red.dao.JpushBindingDao;
import com.sunrun.red.entity.JpushBinding;



/**
 * 
 * @author wangcy
 * @ClassName JpushBindingDaoImpl.java
 * @CreateDate  2016-7-6
 * @descrintion  推送绑定DAO实现层
 * @editor 
 * @editDate
 */
@Repository
public class JpushBindingDaoImpl extends HibernateBaseDao<JpushBinding, Integer> implements JpushBindingDao {

	@Override
	protected Class<JpushBinding> getEntityClass() {
		// TODO Auto-generated method stub
		return JpushBinding.class;
	}
	
	@Override
	public List<JpushBinding> findPoliceUserList(Integer groupId, Integer cityId) {
		Finder f= Finder.create(" select bean from JpushBinding bean where 1=1 ");
		if(groupId!=null){
			f.append(" and bean.groupId =:groupId");
	   		f.setParam("groupId", groupId);
		}
		if(cityId!=null){
			f.append(" and bean.cityId =:cityId");
	   		f.setParam("cityId", cityId);
		}
		return find(f);
	}

	@Override
	public List<JpushBinding> getList(String username) {
		Finder f= Finder.create(" select bean from JpushBinding bean where 1=1 ");
		if(!StringUtils.isBlank(username)){
			f.append(" and bean.username like:username");
	   		 f.setParam("username", "%" + username + "%" );
		}
		return find(f);
	}

	@Override
	public JpushBinding saveDeviceAdd(JpushBinding bean) {
		getSession().save(bean);
		return bean;
	}

	@Override
	public JpushBinding findById(Integer id) {
		JpushBinding entity = super.get(id);
		
		return entity;
	}

	@Override
	public JpushBinding deleteById(Integer id) {
		JpushBinding entity =super.get(id);
		if(entity!=null){
			getSession().delete(entity);
		}
		return entity;
	}

	@Override
	public List<JpushBinding> getDeviceJpushList(String username) {
		Finder finder =Finder.create(" select bean from JpushBinding bean where 1=1");

		if(!StringUtils.isBlank(username)){
			finder.append(" and bean.userName =:userName");
			finder.setParam("userName", username);
		}
		return find(finder);
	}

	@Override
	public JpushBinding getDeviceJpush( String username) {
		return findUniqueByProperty("userName", username);
	}


}
