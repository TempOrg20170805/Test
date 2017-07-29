package com.sunrun.washer.entity.base;

/**
 * AbstractWasherAboutUs entity provides the base persistence definition of the
 * WasherAboutUs entity. @author MyEclipse Persistence Tools
 */

public abstract class AboutUsBase implements java.io.Serializable {

	// Fields

	private Integer id;
	private String title;
	private String info;
	private String imgUrl;

	// Constructors

	/** default constructor */
	public AboutUsBase() {
	}

	/** minimal constructor */
	public AboutUsBase(Integer id) {
		this.id = id;
	}

	/** full constructor */
	public AboutUsBase(Integer id, String title, String info,
			String imgUrl) {
		this.id = id;
		this.title = title;
		this.info = info;
		this.imgUrl = imgUrl;
	}

	// Property accessors

	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getTitle() {
		return this.title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getInfo() {
		return this.info;
	}

	public void setInfo(String info) {
		this.info = info;
	}

	public String getImgUrl() {
		return this.imgUrl;
	}

	public void setImgUrl(String imgUrl) {
		this.imgUrl = imgUrl;
	}

}