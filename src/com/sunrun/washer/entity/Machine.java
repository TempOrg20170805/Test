package com.sunrun.washer.entity;
import java.util.Date;

import com.sunrun.washer.entity.base.MachineBase;

public class Machine extends MachineBase implements java.io.Serializable {
	
	public Machine() {
	}

	
	public Machine(Integer machineId) {
		super(machineId);
	}
	
	public Machine(Integer machineId, String name, String type, String machineNo, String userName) {
		super(machineId, name, type, machineNo, userName);
	}

}

