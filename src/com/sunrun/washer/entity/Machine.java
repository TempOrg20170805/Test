package com.sunrun.washer.entity;
import java.util.Date;

import com.sunrun.washer.entity.base.MachineBase;

public class Machine extends MachineBase implements java.io.Serializable {
	
	public Machine() {
	}

	
	public Machine(Integer machineId) {
		super(machineId);
	}

	public Machine(Integer machineId, String name, String type, String machineNo, Integer status, Integer floorLayerId, Integer floorLayerX, Integer floorLayerY, Date createTime, Date useTime, Date baseUpdateTime){
		super(machineId, name, type, machineNo, status, floorLayerId, floorLayerX, floorLayerY, createTime, useTime, baseUpdateTime);
	}
}

