package com.sunrun.washer.manager.impl;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.jeecms.common.hibernate4.Updater;
import com.jeecms.common.page.Pagination;
import com.sunrun.washer.dao.HelpDao;
import com.sunrun.washer.entity.Help;
import com.sunrun.washer.manager.HelpMng;

@Service
@Transactional
public class HelpMngImpl implements HelpMng{

	@Autowired
	private HelpDao dao;
	
	@Override
	public Pagination getPage(String name, Integer pageNo, Integer pageSize) {
		// TODO Auto-generated method stub
		return dao.getPage(name, pageNo, pageSize);
	}

	@Override
	public List<Help> getPageForApp(Integer id) {
		// TODO Auto-generated method stub
		return dao.getPageForApp(id);
	}

	@Override
	public Help save(Help bean) {
		// TODO Auto-generated method stub
		bean.setCreateTime(new Date());
		return dao.save(bean);
	}

	@Override
	public Help findById(Integer id) {
		// TODO Auto-generated method stub
		return dao.findById(id);
	}

	@Override
	public Help update(Help bean) {
		// TODO Auto-generated method stub
		try{
			Updater<Help> updater =new Updater<Help>(bean);
			bean = dao.updateByUpdater(updater);
		}catch (Exception e) {
			e.printStackTrace();
		}
		return bean;
	}

	@Override
	public Help[] deleteByIds(Integer[] ids) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Help deleteById(Integer id) {
		// TODO Auto-generated method stub
		return dao.deleteById(id);
	}

}
