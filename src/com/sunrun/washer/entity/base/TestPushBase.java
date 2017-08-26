package com.sunrun.washer.entity.base;



public class TestPushBase implements java.io.Serializable {
	
	private Integer testPushId; // id
	private String machineNo; // 洗衣机编号
	private String registrationId; // 推送id

	
	public TestPushBase() {
	}

	public TestPushBase(Integer testPushId) {
		this.testPushId = testPushId;
	}
	

	public Integer getTestPushId() {
		return this.testPushId;
	}
 
	public void setTestPushId(Integer testPushId) {
		this.testPushId = testPushId;
	}
	public String getMachineNo() {
		return this.machineNo;
	}
 
	public void setMachineNo(String machineNo) {
		this.machineNo = machineNo;
	}
	public String getRegistrationId() {
		return this.registrationId;
	}
 
	public void setRegistrationId(String registrationId) {
		this.registrationId = registrationId;
	}
}

