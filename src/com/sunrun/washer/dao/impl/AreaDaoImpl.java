package com.sunrun.washer.dao.impl;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.jeecms.common.hibernate4.Finder;
import com.jeecms.common.hibernate4.HibernateBaseDao;
import com.jeecms.common.page.Pagination;
import com.sunrun.washer.dao.AreaDao;
import com.sunrun.washer.entity.Area;

@Repository
public class AreaDaoImpl extends HibernateBaseDao<Area, Integer> implements AreaDao{

	@Override
	protected Class<Area> getEntityClass() {
		return Area.class;
	}

	@Override
	public Pagination getPage(String area, Integer parentId,Integer pageNo, Integer pageSize) {
		Finder f = Finder.create("select bean from Area bean where 1=1 ");
		if(area == null){
			area = "";
		}
		if(area != ""){
			f.append(" and bean.areaName like :areaName");
			f.setParam("areaName", "%"+area+"%");
		}
		if(parentId != 0){
			f.append(" and bean.area.areaId =:areaId");
			f.setParam("areaId", parentId);
		}
		f.append(" order by bean.areaId asc");
		return find(f, pageNo, pageSize);
	}

	@Override
	public Pagination getPage(Integer parentId, Integer pageNo, Integer pageSize) {
		Finder f = Finder.create("select bean from Area bean where 1=1 ");
		if(parentId != 0){
			f.append(" and bean.area.areaId =:areaId");
			f.setParam("areaId", parentId);
		}
		f.append(" order by bean.areaId asc");
		return find(f, pageNo, pageSize);
	}

	@Override
	public Area save(Area bean) {
		getSession().save(bean);
		return bean;
	}

	@Override
	public Area delete(Integer id) {
		Area bean = super.get(id);
		if(bean != null){
			getSession().delete(bean);
		}
		return bean;
	}

	@Override
	public Area findById(Integer id) {
		Area bean = super.get(id);
		return bean;
	}

	@Override
	public List<Area> findByPareantId(Integer parentId) {
		Finder f = Finder.create("select bean from Area bean where 1=1 ");
		if(parentId != null){
			f.append(" and bean.parent.areaId =:areaId");
			f.setParam("areaId", parentId);
		}
		f.append(" order by bean.areaId asc");
		return find(f);
	}

	@Override
	public List<Area> findByAreaName(String areaName) {
		Finder f = Finder.create("select bean from Area bean where 1=1 ");
		if(areaName != null){
			f.append(" and bean.areaName like :areaName");
			f.setParam("areaName", "%"+areaName+"%");
		}
		return find(f);
	}


	@Override
	@SuppressWarnings("unchecked")
	public List<Area> areaList() {
		Finder f = Finder.create("select bean from Area bean where 1=1 ");
		f.append(" order by bean.id asc");
		return find(f);
	}
	
	@Override
	public List<Area> ctiyList(Integer parentId) {
		Finder f = Finder.create("select bean from Area bean where 1=1 ");
		if(parentId != 0){
			f.append(" and bean.area.areaId =:areaId");
			f.setParam("areaId", parentId);
		}
		f.append(" order by bean.areaId asc");
		return find(f);
	}

	@Override
	public List<Area> queryListByRoot(Integer parentId) {
		Finder finder = Finder.create("from Area bean where 1=1");
		if (parentId >= 0) {
			finder.append(" and bean.parent.areaId = :parentId");
			finder.setParam("parentId", parentId);
		}
		List<Area> areas = find(finder);
		return areas;
	}

}
