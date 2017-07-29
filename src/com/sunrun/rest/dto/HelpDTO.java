package com.sunrun.rest.dto;

import com.sunrun.washer.entity.Help;

public class HelpDTO {

	private Integer id;
	private String name;
	private String info;
	private String tell;
	
	public void initInfo(Help help){
		this.id = help.getId();
		this.name = help.getName();
		this.info = help.getInfo();
		this.tell = help.getTell();
	}
	
	public void initList(Help help){
		this.id = help.getId();
		this.name = help.getName();
	}
	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getInfo() {
		return info;
	}
	public void setInfo(String info) {
		this.info = info;
	}
	public String getTell() {
		return tell;
	}
	public void setTell(String tell) {
		this.tell = tell;
	}
	
}
