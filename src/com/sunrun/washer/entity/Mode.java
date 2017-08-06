package com.sunrun.washer.entity;
import java.math.BigDecimal;

import com.sunrun.washer.entity.base.ModeBase;

public class Mode extends ModeBase implements java.io.Serializable {
	
	public Mode() {
	}

	
	public Mode(Integer modeId) {
		super(modeId);
	}

	public Mode(Integer modeId, String name, BigDecimal modeMoney, Integer modeTime, Integer status){
		super(modeId, name, modeMoney, modeTime, status);
	}
}

