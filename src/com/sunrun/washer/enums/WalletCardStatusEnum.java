package com.sunrun.washer.enums;


public enum WalletCardStatusEnum {
	/**
	 * 0.删除
	 */
	DELETE(0,"删除"),
	/**
	 * 1.有效
	 */
	USE(1, "有效");
	private Integer value;
	private String name;
	
	public Integer getValue() {
		return value;
	}

	public void setValue(Integer value) {
		this.value = value;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	private WalletCardStatusEnum(Integer value, String name) {
		this.value = value;
		this.name = name;
	}
	
	public static String getNameByValue(Integer value) {
		for (WalletCardStatusEnum walletCardStatusEnum : WalletCardStatusEnum.values()) {
			if (walletCardStatusEnum.getValue().equals(value)) {
				return walletCardStatusEnum.getName();
			}
		}
		return "";
	}
}
