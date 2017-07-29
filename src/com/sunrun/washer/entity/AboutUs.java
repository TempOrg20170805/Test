package com.sunrun.washer.entity;

import com.sunrun.washer.entity.base.AboutUsBase;

/**
 * WasherAboutUs entity. @author MyEclipse Persistence Tools
 */
public class AboutUs extends AboutUsBase implements
		java.io.Serializable {

	// Constructors

	/** default constructor */
	public AboutUs() {
	}

	/** minimal constructor */
	public AboutUs(Integer id) {
		super(id);
	}

	/** full constructor */
	public AboutUs(Integer id, String title, String info, String imgUrl) {
		super(id, title, info, imgUrl);
	}

}
