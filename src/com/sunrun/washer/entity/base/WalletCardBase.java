package com.sunrun.washer.entity.base;
// default package

import java.util.Date;

import com.jeecms.core.entity.CmsUser;
import com.sunrun.washer.entity.WalletCard.WalletCardStatusEnum;
/**
 * 文 件 名 : WalletCardBase.java
 * 创 建 人： 金明明
 * 日 期：2017-8-6
 * 修 改 人： 
 * 日 期： 
 * 描 述：银行卡/支付宝
 */
public abstract class WalletCardBase  implements java.io.Serializable {


    // Fields    

    private Integer id;
    private CmsUser jcUser;
 	private String realname; // 真实姓名
 	private Integer type; // 类型 银行卡/支付宝
 	private String bankName; // 银行名称
 	private String bankNum; // 卡号
 	private String bankBranches; // 银行网点
 	private String alipayNum; // 支付宝账号
 	private String collectionCode; // 收款码
 	private Integer status = WalletCardStatusEnum.USE.getValue(); // 状态 0.删除 1.有效 默认有效
    private Date createTime = new Date(); // 创建时间


    // Constructors

    /** default constructor */
    public WalletCardBase() {
    }


   
    // Property accessors

    public Integer getId() {
        return this.id;
    }
    
    public void setId(Integer id) {
        this.id = id;
    }

    public String getRealname() {
        return this.realname;
    }
    
    public void setRealname(String realname) {
        this.realname = realname;
    }
    
    public Integer getType() {
		return type;
	}

	public void setType(Integer type) {
		this.type = type;
	}

	public String getBankName() {
        return this.bankName;
    }
    
    public void setBankName(String bankName) {
        this.bankName = bankName;
    }

    public String getBankNum() {
        return this.bankNum;
    }
    
    public void setBankNum(String bankNum) {
        this.bankNum = bankNum;
    }

    public String getBankBranches() {
        return this.bankBranches;
    }
    
    public void setBankBranches(String bankBranches) {
        this.bankBranches = bankBranches;
    }

    public String getAlipayNum() {
        return this.alipayNum;
    }
    
    public void setAlipayNum(String alipayNum) {
        this.alipayNum = alipayNum;
    }

	public CmsUser getJcUser() {
		return jcUser;
	}

	public void setJcUser(CmsUser jcUser) {
		this.jcUser = jcUser;
	}

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	public String getCollectionCode() {
		return collectionCode;
	}

	public void setCollectionCode(String collectionCode) {
		this.collectionCode = collectionCode;
	}

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}


}