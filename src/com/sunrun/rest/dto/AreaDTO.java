package com.sunrun.rest.dto;

import com.sunrun.red.entity.Area;

/**
 * 
 * @author zwy
 * @ClassName AreaDTO.java
 * @CreateDate  2017-6-22
 * @descrintion  地区或辖区DTO
 * @editor 
 * @editDate
 */
public class AreaDTO {

	private Integer id;
	private String name;
	
	public void initArea(Area area){
		this.id = area.getAreaId();
		this.name = area.getAreaName();
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
	
}
