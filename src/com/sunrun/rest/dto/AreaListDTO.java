package com.sunrun.rest.dto;

import java.util.List;

public class AreaListDTO extends BaseDTO{

	private List<AreaDTO> arealist;
	private Integer totalCount; // 所有数据量
	public List<AreaDTO> getArealist() {
		return arealist;
	}

	public void setArealist(List<AreaDTO> arealist) {
		this.arealist = arealist;
	}

	public Integer getTotalCount() {
		return totalCount;
	}

	public void setTotalCount(Integer totalCount) {
		this.totalCount = totalCount;
	}
	
	
}
