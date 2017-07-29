package com.sunrun.washer.manager;

import com.sunrun.washer.entity.AboutUs;

public interface AboutUsMng {

	public AboutUs save(AboutUs bean);

	public AboutUs findById(Integer id);

	public AboutUs update(AboutUs bean);
	
	public AboutUs getList();
	
}
