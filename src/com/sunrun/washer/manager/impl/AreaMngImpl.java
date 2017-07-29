package com.sunrun.washer.manager.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.jeecms.common.hibernate4.Updater;
import com.jeecms.common.page.Pagination;
import com.sunrun.washer.dao.AreaDao;
import com.sunrun.washer.entity.Area;
import com.sunrun.washer.manager.AreaMng;

@Service
@Transactional
public class AreaMngImpl implements AreaMng {
	
	@Autowired
	private AreaDao dao;

	@Override
	public Pagination getPage(String area, Integer parentId,Integer pageNo, Integer pageSize) {
		return dao.getPage(area,parentId, pageNo, pageSize);
	}

	@Override
	public Pagination getPage(Integer parentId, Integer pageNo, Integer pageSize) {
		return dao.getPage(parentId, pageNo, pageSize);
	}

	@Override
	public Area save(Area parentId, String areaName, Integer areaSort,
			Integer areaDeep, Boolean isDefault) {
		Area bean = new Area();
		bean.setParent(parentId);
		bean.setAreaName(areaName);
		bean.setAreaSort(areaSort);
		bean.setAreaDeep(areaDeep);
		bean.setIsDefault(isDefault);
		bean = dao.save(bean);
		return bean;
	}

	@Override
	public Area update(Integer id, Area parentId, String areaName,
			Integer areaSort, Integer areaDeep, Boolean isDefault) {
		Area bean = dao.findById(id);
		bean.setParent(parentId);
		bean.setAreaName(areaName);
		bean.setAreaSort(areaSort);
		bean.setAreaDeep(areaDeep);
		bean.setIsDefault(isDefault);
		try {
			Updater<Area> updater = new Updater<Area>(bean );
			bean =	dao.updateByUpdater(updater);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return bean;
	}

	@Override
	public Area delete(Integer id) {
		return dao.delete(id);
	}

	@Override
	public Area findById(Integer id) {
		return dao.findById(id);
	}

	@Override
	public List<Area> findByPareantId(Integer pareneId) {
		return dao.findByPareantId(pareneId);
	}

	@Override
	public List<Area> findByAreaName(String areaName) {
		return dao.findByAreaName(areaName);
	}



	@Override
	public List<Area> ctiyList(Integer parentId) {
		// TODO Auto-generated method stub
		return dao.ctiyList(parentId);
	}

	@Override
	public List<Area> queryListByRoot(Integer parentId) {
		// TODO Auto-generated method stub
		return dao.queryListByRoot(parentId);
	}

}
