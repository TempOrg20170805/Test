package com.sunrun.red.entity.base;

import java.sql.Timestamp;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import com.jeecms.core.entity.CmsUser;

/**
 * AbstractWasherInsurance entity provides the base persistence definition of the
 * WasherInsurance entity. @author MyEclipse Persistence Tools
 */

public abstract class InsuranceBase implements java.io.Serializable {

	// Fields

	private Integer id;
	private CmsUser admin;
	private String name;
	private Double amount;
	private Integer life;
	private String info;
	private Date createTime;
	private Date modifyTime;

	// Constructors

	/** default constructor */
	public InsuranceBase() {
	}

	/** full constructor */
	public InsuranceBase(String name, Double amount,
			Integer life, String info, Date createTime, Date modifyTime) {
		this.name = name;
		this.amount = amount;
		this.life = life;
		this.info = info;
		this.createTime = createTime;
		this.modifyTime = modifyTime;
	}

	// Property accessors

	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Double getAmount() {
		return this.amount;
	}

	public void setAmount(Double amount) {
		this.amount = amount;
	}

	public Integer getLife() {
		return this.life;
	}

	public void setLife(Integer life) {
		this.life = life;
	}

	public String getInfo() {
		return this.info;
	}

	public void setInfo(String info) {
		this.info = info;
	}

	public Date getCreateTime() {
		return this.createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	public Date getModifyTime() {
		return modifyTime;
	}

	public void setModifyTime(Date modifyTime) {
		this.modifyTime = modifyTime;
	}

	public CmsUser getAdmin() {
		return admin;
	}

	public void setAdmin(CmsUser admin) {
		this.admin = admin;
	}

}