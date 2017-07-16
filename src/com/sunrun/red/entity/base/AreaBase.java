package com.sunrun.red.entity.base;

import com.sunrun.red.entity.Area;

/**
 * AbstractWasherArea entity provides the base persistence definition of the
 * WasherArea entity. @author MyEclipse Persistence Tools
 */

public abstract class AreaBase implements java.io.Serializable {

	// Fields

	private Integer areaId;
	private Area parent;
	private String areaName;
	private Integer areaSort;
	private Integer areaDeep;
	private Boolean isDefault;

	// Constructors

	/** default constructor */
	public AreaBase() {
	}

	/** minimal constructor */
	public AreaBase(String areaName, Integer areaSort, Integer areaDeep) {
		this.areaName = areaName;
		this.areaSort = areaSort;
		this.areaDeep = areaDeep;
	}

	/** full constructor */
	public AreaBase(String areaName, Integer areaSort, Integer areaDeep, Boolean isDefault) {
		this.areaName = areaName;
		this.areaSort = areaSort;
		this.areaDeep = areaDeep;
		this.isDefault = isDefault;
	}

	// Property accessors

	public Integer getAreaId() {
		return this.areaId;
	}

	public void setAreaId(Integer areaId) {
		this.areaId = areaId;
	}

	public String getAreaName() {
		return this.areaName;
	}

	public void setAreaName(String areaName) {
		this.areaName = areaName;
	}

	public Integer getAreaSort() {
		return this.areaSort;
	}

	public void setAreaSort(Integer areaSort) {
		this.areaSort = areaSort;
	}

	public Integer getAreaDeep() {
		return areaDeep;
	}

	public void setAreaDeep(Integer areaDeep) {
		this.areaDeep = areaDeep;
	}

	public Boolean getIsDefault() {
		return isDefault;
	}

	public void setIsDefault(Boolean isDefault) {
		this.isDefault = isDefault;
	}

	public Area getParent() {
		return parent;
	}

	public void setParent(Area parent) {
		this.parent = parent;
	}
	
}