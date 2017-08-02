package com.sunrun.washer.entity;

import java.util.Date;

import com.jeecms.core.entity.CmsUser;
import com.sunrun.washer.entity.base.FeedbackBase;


public class Feedback extends FeedbackBase implements java.io.Serializable {
	
	public Feedback() {
	}

	
	public Feedback(Integer id) {
		super(id);
	}

	public Feedback(Integer id,String info,CmsUser user,Date createTime){
		super(id,info,user,createTime);
	}
}
