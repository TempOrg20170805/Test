package com.sunrun.washer.dao;

import java.util.List;

import com.jeecms.common.hibernate4.Updater;
import com.jeecms.common.page.Pagination;
import com.sunrun.washer.entity.Help;

public interface HelpDao {

	public Pagination getPage(String name, Integer pageNo,Integer pageSize);
	
	public List<Help> getPageForApp(Integer id);
	
	public Help save(Help bean);
	
	public Help findById(Integer id);
	
	public Help updateByUpdater(Updater<Help> updater);;
	
	public Help deleteById(Integer id);
}
