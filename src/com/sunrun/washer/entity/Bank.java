package com.sunrun.washer.entity;
import com.sunrun.washer.entity.base.BankBase;

public class Bank extends BankBase implements java.io.Serializable {
	
	public Bank() {
	}

	
	public Bank(Integer bankId) {
		super(bankId);
	}

	public Bank(Integer bankId, String name, Integer status, String bankStr, String bankIconUrl){
		super(bankId, name, status, bankStr, bankIconUrl);
	}
}

