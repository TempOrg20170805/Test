package com.sunrun.red.entity;

import com.sunrun.red.entity.base.JpushBindingBase;




/**
 * 
 * @author wangcy
 * @ClassName JpushBinding.java
 * @CreateDate  2017-5-16
 * @descrintion   推送绑定
 * @editor 
 * @editDate
 */
public class JpushBinding extends JpushBindingBase implements java.io.Serializable {

    // Constructors

    /**  
	* @Fields serialVersionUID : TODO(用一句话描述这个变量表示什么)  
	*/  
	private static final long serialVersionUID = 1L;


	/** default constructor */
    public JpushBinding() {
    }

    
    /** full constructor */
    public JpushBinding(String platform, String tag, String alias, String registrationId, String userName,
    		Integer groupId,Integer cityId) {
        super(platform, tag, alias, registrationId, userName, groupId,cityId);        
    }
   
}
