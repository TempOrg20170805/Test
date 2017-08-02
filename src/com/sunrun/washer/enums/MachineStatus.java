package com.sunrun.washer.enums;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MachineStatus {
	public enum MachineStatusEnum{
		DELETE(0, "删除"),
		NOT_USE(1,"空闲"),
		USE(2,"使用中"),
		STOP(3,"停用")
		;
		
		private Integer code;
	    private String describe;

	    private MachineStatusEnum(Integer code, String describe) {
	        this.code = code;
	        this.describe = describe;
	    }
	    
	    public static Map<Integer, String> getMap() {
	        Map<Integer,String> map = new HashMap<Integer,String>();
	        for (MachineStatusEnum item : MachineStatusEnum.values()) {
	            map.put(item.getCode(), item.getDescribe());
	        }
	        return map;
	    }

		public static List<MachineStatusEnum> getList() {
	        List<MachineStatusEnum> stateList = new ArrayList<MachineStatusEnum>();
	        for (MachineStatusEnum item : MachineStatusEnum.values()) {
	            stateList.add(item);
	        }
	        return stateList;
	    }
		
		public static MachineStatusEnum getContains(Integer code) {
	        for (MachineStatusEnum item : MachineStatusEnum.values()) {
	        	if(code.equals(item.getCode())){
	        		return item;
	        	}
	        }
	        return null;
	    }

		public Integer getCode() {
			return code;
		}

		public void setCode(Integer code) {
			this.code = code;
		}

		public String getDescribe() {
			return describe;
		}

		public void setDescribe(String describe) {
			this.describe = describe;
		}
		

	}

}
