package com.sunrun.washer.dao;

import com.jeecms.common.hibernate4.Updater;
import com.sunrun.washer.entity.AboutUs;

public interface AboutUsDao {

	public AboutUs save(AboutUs bean);

	public AboutUs findById(Integer id);

	public AboutUs updateByUpdater(Updater<AboutUs> updater);
	
	public AboutUs getList();
}
