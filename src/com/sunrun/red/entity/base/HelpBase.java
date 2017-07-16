package com.sunrun.red.entity.base;

import java.util.Date;

/**
 * AbstractWasherHelp entity provides the base persistence definition of the
 * WasherHelp entity. @author MyEclipse Persistence Tools
 */

public abstract class HelpBase implements java.io.Serializable {

	// Fields

	private Integer id;
	private String name;
	private String info;
	private String tell;
	private Date createTime;

	// Constructors

	/** default constructor */
	public HelpBase() {
	}

	/** minimal constructor */
	public HelpBase(Integer id) {
		this.id = id;
	}

	/** full constructor */
	public HelpBase(Integer id, String name, String info, String tell,
			Date createTime) {
		this.id = id;
		this.name = name;
		this.info = info;
		this.tell = tell;
		this.createTime = createTime;
	}

	// Property accessors

	public Integer getId() {
		return this.id;
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
		return this.info;
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

	public Date getCreateTime() {
		return this.createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

}