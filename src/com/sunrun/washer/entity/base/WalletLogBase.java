package com.sunrun.washer.entity.base;
// default package

import java.math.BigDecimal;
import java.util.Date;

import com.jeecms.core.entity.CmsUser;
/**
 * 文 件 名 : WalletLogBase.java
 * 创 建 人： 金明明
 * 日 期：2017-8-6
 * 修 改 人： 
 * 日 期： 
 * 描 述：消费日志
 */
public abstract class WalletLogBase  implements java.io.Serializable {
    // Fields    
	private java.lang.Integer id;
	private com.jeecms.core.entity.CmsUser jcUser; // 用户ID
	private java.lang.Integer type; // 交易类型 1.运费付款
	private Integer payPlatform; // 交易平台 1.钱包平台 2.支付宝平台 3.微信平台 4.银行卡平台
	private BigDecimal money; // 交易金额
	private java.util.Date time = new Date(); // 交易时间
	private java.lang.String msg; // 交易信息
	private BigDecimal moneyBefore; // 变更前钱包金额（不包含第三方支付）
	private BigDecimal moneyAfter; // 变更后钱包金额（不包含第三方支付）


    // Constructors

    /** default constructor */
    public WalletLogBase() {
    }

    
    
   
    // Property accessors

    public Integer getId() {
        return this.id;
    }
    
    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getType() {
        return this.type;
    }
    
    public void setType(Integer type) {
        this.type = type;
    }


    public BigDecimal getMoney() {
		return money;
	}

	public void setMoney(BigDecimal money) {
		this.money = money;
	}

	public java.util.Date getTime() {
		return time;
	}

	public void setTime(java.util.Date time) {
		this.time = time;
	}

	public String getMsg() {
        return this.msg;
    }
    
    public void setMsg(String msg) {
        this.msg = msg;
    }

	public CmsUser getJcUser() {
		return jcUser;
	}

	public void setJcUser(CmsUser jcUser) {
		this.jcUser = jcUser;
	}

	public Integer getPayPlatform() {
		return payPlatform;
	}

	public void setPayPlatform(Integer payPlatform) {
		this.payPlatform = payPlatform;
	}

	public BigDecimal getMoneyBefore() {
		return moneyBefore;
	}

	public void setMoneyBefore(BigDecimal moneyBefore) {
		this.moneyBefore = moneyBefore;
	}


	public BigDecimal getMoneyAfter() {
		return moneyAfter;
	}

	public void setMoneyAfter(BigDecimal moneyAfter) {
		this.moneyAfter = moneyAfter;
	}

	
}