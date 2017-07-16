package com.sunrun.red.dao;

import com.jeecms.common.hibernate4.Updater;
import com.jeecms.common.page.Pagination;
import com.sunrun.red.entity.Feedback;




public interface FeedbackDao {
	
	public Pagination getPage( Integer pageNo,Integer pageSize);
	
	public Feedback deleteById(Integer id);
	
	public Feedback save(Feedback bean);
	
	public Feedback findById(Integer id);
	
	public Feedback updateByUpdater(Updater<Feedback> updater);
}
