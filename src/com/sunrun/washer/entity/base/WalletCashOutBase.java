package com.sunrun.washer.entity.base;
// default package

import java.math.BigDecimal;
import java.util.Date;

import com.jeecms.core.entity.CmsUser;
import com.sunrun.washer.entity.WalletCard;
import com.sunrun.washer.entity.WalletCashOut;

/**
 * 文 件 名 : WalletCashOutBase.java
 * 创 建 人： 金明明
 * 日 期：2017-8-6
 * 修 改 人： 
 * 日 期： 
 * 描 述：钱包提现
 */
public abstract class WalletCashOutBase  implements java.io.Serializable {


    // Fields    

    private Integer cashOutId;
 	private CmsUser jcUser; // 用户Id
 	private BigDecimal money; // 提现金额
 	private Integer state; // 提现状态 1.提现申请中 2.提现成功 3.提现失败
 	private Integer cashOutType; // 提现类型：1.银行卡 2.支付宝
 	private String cardNo; // 卡号
 	private String cardName; // 卡用户姓名
 	private String cardCompany; // 卡所对应银行
 	private String cardNetwork; // 卡网点
 	private Date createTime; // 发起时间
 	private Date handleTime; // 处理时间
 	private String cashOutFailReason; // 提现失败原因
 	private String collectionCode; // 收款码

    // Constructors

    /** default constructor */
    public WalletCashOutBase() {
    }


	public Integer getCashOutId() {
		return cashOutId;
	}


	public void setCashOutId(Integer cashOutId) {
		this.cashOutId = cashOutId;
	}


	public CmsUser getJcUser() {
		return jcUser;
	}


	public void setJcUser(CmsUser jcUser) {
		this.jcUser = jcUser;
	}


	public BigDecimal getMoney() {
		return money;
	}


	public void setMoney(BigDecimal money) {
		this.money = money;
	}


	public Integer getState() {
		return state;
	}


	public void setState(Integer state) {
		this.state = state;
	}

	public String getStateStr() {
		return WalletCashOut.WalletCashOutStateEnum.getNameByValue(state);
	}

	public Integer getCashOutType() {
		return cashOutType;
	}


	public void setCashOutType(Integer cashOutType) {
		this.cashOutType = cashOutType;
	}
	
	public String getCashOutTypeStr() {
		return WalletCard.WalletCardTypeEnum.getNameByValue(cashOutType);
	}


	public String getCardNo() {
		return cardNo;
	}


	public void setCardNo(String cardNo) {
		this.cardNo = cardNo;
	}


	public String getCardName() {
		return cardName;
	}


	public void setCardName(String cardName) {
		this.cardName = cardName;
	}


	public String getCardCompany() {
		return cardCompany;
	}


	public void setCardCompany(String cardCompany) {
		this.cardCompany = cardCompany;
	}


	public String getCardNetwork() {
		return cardNetwork;
	}


	public void setCardNetwork(String cardNetwork) {
		this.cardNetwork = cardNetwork;
	}


	public Date getCreateTime() {
		return createTime;
	}


	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}


	public Date getHandleTime() {
		return handleTime;
	}


	public void setHandleTime(Date handleTime) {
		this.handleTime = handleTime;
	}


	public String getCashOutFailReason() {
		return cashOutFailReason;
	}


	public void setCashOutFailReason(String cashOutFailReason) {
		this.cashOutFailReason = cashOutFailReason;
	}


	public String getCollectionCode() {
		return collectionCode;
	}


	public void setCollectionCode(String collectionCode) {
		this.collectionCode = collectionCode;
	}
	

	
}