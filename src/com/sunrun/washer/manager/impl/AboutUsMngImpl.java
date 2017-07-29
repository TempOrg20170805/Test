package com.sunrun.washer.manager.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.jeecms.common.hibernate4.Updater;
import com.sunrun.washer.dao.AboutUsDao;
import com.sunrun.washer.entity.AboutUs;
import com.sunrun.washer.manager.AboutUsMng;

@Service
@Transactional
public class AboutUsMngImpl implements AboutUsMng{

	@Autowired
	private AboutUsDao dao;
	
	@Override
	public AboutUs save(AboutUs bean) {
		// TODO Auto-generated method stub
		return dao.save(bean);
	}

	@Override
	public AboutUs findById(Integer id) {
		// TODO Auto-generated method stub
		return dao.findById(id);
	}

	@Override
	public AboutUs update(AboutUs bean) {
		// TODO Auto-generated method stub
		try{
			Updater<AboutUs> updater =new Updater<AboutUs>(bean);
			bean = dao.updateByUpdater(updater);
		}catch (Exception e) {
			e.printStackTrace();
		}
		return bean;
	}

	@Override
	public AboutUs getList() {
		// TODO Auto-generated method stub
		return dao.getList();
	}

}
