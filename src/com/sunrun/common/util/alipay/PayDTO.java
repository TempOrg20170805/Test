package com.sunrun.common.util.alipay;
public class PayDTO {
	private Boolean isSuccess;
	private String out_trade_no;
	
	public PayDTO() {
	}
	public Boolean getIsSuccess() {
		return isSuccess;
	}
	public void setIsSuccess(Boolean isSuccess) {
		this.isSuccess = isSuccess;
	}
	public String getOut_trade_no() {
		return out_trade_no;
	}
	public void setOut_trade_no(String out_trade_no) {
		this.out_trade_no = out_trade_no;
	}
}
