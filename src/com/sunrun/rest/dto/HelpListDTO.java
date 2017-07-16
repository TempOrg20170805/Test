package com.sunrun.rest.dto;

import java.util.List;

public class HelpListDTO extends BaseDTO{

	private List<HelpDTO> list;

	public List<HelpDTO> getList() {
		return list;
	}

	public void setList(List<HelpDTO> list) {
		this.list = list;
	}
	
}
