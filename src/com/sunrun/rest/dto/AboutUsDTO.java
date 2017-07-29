package com.sunrun.rest.dto;

import com.sunrun.washer.entity.AboutUs;

public class AboutUsDTO extends BaseDTO{
	
	private String title;
	private String info;
	private String imgUrl;
	
	public void init(AboutUs about){
		this.title = about.getTitle();
		this.info = about.getInfo();
		this.imgUrl = about.getImgUrl();
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getInfo() {
		return info;
	}
	public void setInfo(String info) {
		this.info = info;
	}
	public String getImgUrl() {
		return imgUrl;
	}
	public void setImgUrl(String imgUrl) {
		this.imgUrl = imgUrl;
	}
	
}
