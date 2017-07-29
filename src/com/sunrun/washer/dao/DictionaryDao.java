package com.sunrun.washer.dao;

import java.util.List;

import com.jeecms.common.hibernate4.Updater;
import com.jeecms.common.page.Pagination;
import com.sunrun.washer.entity.Dictionary;


public interface DictionaryDao {
	public Pagination getPage(String queryType,int pageNo, int pageSize);
	
	public List<Dictionary> getList(String type);
	
	public List<String> getTypeList();

	public Dictionary findById(Integer id);
	
	public Dictionary findByValue(String type,String value);

	public Dictionary save(Dictionary bean);

	public Dictionary updateByUpdater(Updater<Dictionary> updater);

	public Dictionary deleteById(Integer id);

	public int countByValue(String value, String type);
}