package com.sunrun.washer.entity;

import java.sql.Timestamp;
import java.util.Date;

import com.sunrun.washer.entity.base.HelpBase;

/**
 * WasherHelp entity. @author MyEclipse Persistence Tools
 */
public class Help extends HelpBase implements
		java.io.Serializable {

	// Constructors

	/** default constructor */
	public Help() {
	}

	/** minimal constructor */
	public Help(Integer id) {
		super(id);
	}

	/** full constructor */
	public Help(Integer id, String name, String info, String tell,Date createTime) {
		super(id, name, info, tell, createTime);
	}

}
