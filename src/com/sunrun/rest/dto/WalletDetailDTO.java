package com.sunrun.rest.dto;

public class WalletDetailDTO extends BaseDTO{
	
	private Double money = new Double(0); // 金额

	public Double getMoney() {
		return money;
	}

	public void setMoney(Double money) {
		this.money = money;
	}
	
	
}
