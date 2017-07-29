package com.sunrun.washer.manager;

import java.util.List;

import com.jeecms.common.page.Pagination;
import com.sunrun.washer.entity.Dictionary;


public interface DictionaryMng {
	public Pagination getPage(String queryType,int pageNo, int pageSize);
	
	public List<Dictionary> getList(String queryType);
	
	public List<String> getTypeList();

	public Dictionary findById(Integer id);
	
	public Dictionary findByValue(String type,String value);

	public Dictionary save(Dictionary bean);

	public Dictionary update(Dictionary bean);

	public Dictionary deleteById(Integer id);
	
	public Dictionary[] deleteByIds(Integer[] ids);
	
	public boolean dicDeplicateValue(String value,String type);
}