package com.sunrun.washer.manager.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.jeecms.common.hibernate4.Updater;
import com.jeecms.common.page.Pagination;
import com.sunrun.washer.dao.FeedbackDao;
import com.sunrun.washer.entity.Feedback;
import com.sunrun.washer.manager.FeedbackMng;

@Service
@Transactional
public class FeedbackMngImpl implements FeedbackMng {
	@Autowired
	private FeedbackDao dao;

	@Override
	public Pagination getPage(Integer pageNo, Integer pageSize) {
		// TODO Auto-generated method stub
		return dao.getPage(pageNo, pageSize);
	}

	@Override
	public Feedback[] deleteByIds(Integer[] ids) {
		// TODO Auto-generated method stub
		Feedback[] beans = new Feedback[ids.length];
		for (int i = 0, len = ids.length; i < len; i++) {
			beans[i] = deleteById(ids[i]);
		}
		return beans;
	}

	@Override
	public Feedback save(Feedback bean) {
		// TODO Auto-generated method stub
		return dao.save(bean);
	}

	@Override
	public Feedback findById(Integer id) {
		// TODO Auto-generated method stub
		return dao.findById(id);
	}

	@Override
	public Feedback update(Feedback bean) {
		// TODO Auto-generated method stub
		try {
			Updater<Feedback> updater = new Updater<Feedback>(bean);
			bean = dao.updateByUpdater(updater);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return bean;
	}

	@Override
	public Feedback deleteById(Integer id) {
		// TODO Auto-generated method stub
		return dao.deleteById(id);
	}

}
