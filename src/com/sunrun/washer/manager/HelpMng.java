package com.sunrun.washer.manager;

import java.util.List;

import com.jeecms.common.page.Pagination;
import com.sunrun.washer.entity.Help;

public interface HelpMng {

	public Pagination getPage(String name, Integer pageNo,Integer pageSize);
	
	public List<Help> getPageForApp(Integer id);
	
	public Help save(Help bean);
	
	public Help findById(Integer id);
	
	public Help update(Help bean);
	
	public Help[] deleteByIds(Integer[] ids);
	
	public Help deleteById(Integer id);
}
