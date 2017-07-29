package com.sunrun.washer.entity.base;

import java.util.Date;

/**
 * 
 * @author wangcy
 * @ClassName JpushBindingBase.java
 * @CreateDate  2017-6-26
 * @descrintion   推送绑定实体
 * @editor 
 * @editDate
 */
public abstract class JpushBindingBase  implements java.io.Serializable {


    // Fields    
     /**  
	* @Fields serialVersionUID : TODO(用一句话描述这个变量表示什么)  
	*/  
	 private static final long serialVersionUID = 1L;
	
	 private Integer id;
     private String platform;
     private String tag;
     private String alias;
     private String registrationId;
     private String userName;//注册手机号
     private Integer keyType;//秘钥使用类型:1、iOS开发版 2、iOS生产版 3、iOS正式版4、安卓版
     private Date addTime;
     private Integer groupId;
     private Integer cityId;
      
    // Constructors
    /** default constructor */
    public JpushBindingBase() {
    	
    }
    
    /** full constructor */
    public JpushBindingBase(String platform, String tag, String alias, String registrationId, String userName,
    		Integer groupId,Integer cityId) {
        this.platform = platform;
        this.tag = tag;
        this.alias = alias;
        this.registrationId = registrationId;
        this.userName = userName;
        this.groupId = groupId;
        this.cityId = cityId;
    }
   
    // Property accessors
    public Integer getId() {
        return this.id;
    }
    
    public void setId(Integer id) {
        this.id = id;
    }

    public String getPlatform() {
        return this.platform;
    }
    
    public void setPlatform(String platform) {
        this.platform = platform;
    }

    public String getTag() {
        return this.tag;
    }
    
    public void setTag(String tag) {
        this.tag = tag;
    }

    public String getAlias() {
        return this.alias;
    }
    
    public void setAlias(String alias) {
        this.alias = alias;
    }

    public String getRegistrationId() {
        return this.registrationId;
    }
    
    public void setRegistrationId(String registrationId) {
        this.registrationId = registrationId;
    }
    
	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public Integer getKeyType() {
		return keyType;
	}

	public void setKeyType(Integer keyType) {
		this.keyType = keyType;
	}

	public Date getAddTime() {
		return addTime;
	}

	public void setAddTime(Date addTime) {
		this.addTime = addTime;
	}

	public Integer getGroupId() {
		return groupId;
	}

	public void setGroupId(Integer groupId) {
		this.groupId = groupId;
	}

	public Integer getCityId() {
		return cityId;
	}

	public void setCityId(Integer cityId) {
		this.cityId = cityId;
	}
   
}