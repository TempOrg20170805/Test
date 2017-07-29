package com.sunrun.washer.entity;

import java.util.Set;

import com.sunrun.washer.entity.base.AreaBase;

/**
 * WasherArea entity. @author MyEclipse Persistence Tools
 */
public class Area extends AreaBase implements
		java.io.Serializable {

	// Constructors

	/** default constructor */
	public Area() {
	}

	/** minimal constructor */
	public Area(String areaName, Integer areaSort, Integer areaDeep) {
		super(areaName, areaSort, areaDeep);
	}

	/** full constructor */
	public Area(String areaName, Integer areaSort,
			Integer areaDeep, Boolean isDefault) {
		super(areaName, areaSort, areaDeep, isDefault);
	}

}
