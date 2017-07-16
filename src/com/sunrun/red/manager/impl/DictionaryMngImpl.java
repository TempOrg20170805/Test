package com.sunrun.red.manager.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.jeecms.common.hibernate4.Updater;
import com.jeecms.common.page.Pagination;
import com.sunrun.red.dao.DictionaryDao;
import com.sunrun.red.entity.Dictionary;
import com.sunrun.red.manager.DictionaryMng;


@Service
@Transactional
public class DictionaryMngImpl implements DictionaryMng {
	
	public void init(){
	}
	@Transactional(readOnly = true)
	public Pagination getPage(String queryType,int pageNo, int pageSize) {
		Pagination page = dao.getPage(queryType,pageNo, pageSize);
		return page;
	}
	
	@Transactional(readOnly = true)
	public List<Dictionary> getList(String queryType){
		return dao.getList(queryType);
	}
	
	@Transactional(readOnly = true)
	public List<String> getTypeList(){
		return dao.getTypeList();
	}

	@Transactional(readOnly = true)
	public Dictionary findById(Integer id) {
		Dictionary entity = dao.findById(id);
		return entity;
	}
	
	@Transactional(readOnly = true)
	public Dictionary findByValue(String type,String value){
		Dictionary entity = dao.findByValue(type,value);
		return entity;
	}

	public Dictionary save(Dictionary bean) {
		dao.save(bean);
		return bean;
	}

	public Dictionary update(Dictionary bean) {
		Updater<Dictionary> updater = new Updater<Dictionary>(bean);
		bean = dao.updateByUpdater(updater);
		return bean;
	}

	public Dictionary deleteById(Integer id) {
		Dictionary bean = dao.deleteById(id);
		return bean;
	}
	
	public Dictionary[] deleteByIds(Integer[] ids) {
		Dictionary[] beans = new Dictionary[ids.length];
		for (int i = 0,len = ids.length; i < len; i++) {
			beans[i] = deleteById(ids[i]);
		}
		return beans;
	}
	
	public boolean dicDeplicateValue(String value, String type) {
		return dao.countByValue(value,type) > 0;
	}

	private DictionaryDao dao;

	@Autowired
	public void setDao(DictionaryDao dao) {
		this.dao = dao;
	}
}