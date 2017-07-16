package com.sunrun.rest.dto;

import com.sunrun.red.entity.Dictionary;

/**
 * 
 * @author wangcy
 * @ClassName DictionaryInfoDTO.java
 * @CreateDate  2017-6-18
 * @descrintion  字典内容DTO
 * @editor 
 * @editDate
 */
public class DictionaryInfoDTO {
	
	private Integer dictionaryId;
	private java.lang.String name;
	private java.lang.String value;
	private java.lang.String type;

	private java.lang.String remark;
	private  Boolean isRequired;
	
	public DictionaryInfoDTO (Dictionary entity){
		this.dictionaryId = entity.getId();
		this.name = entity.getName();
		this.value = entity.getValue();
		this.type = entity.getType();
		if(entity.getRemark()==null){
			this.remark ="";
		}else{
			this.remark =entity.getRemark();
		}
		if(entity.getIsRequired()==null){
			this.isRequired =false;
		}else{
			this.isRequired =entity.getIsRequired();
		}
		
	}

	
	/**
	 * 
	 */
	public DictionaryInfoDTO() {
		super();
	}
	public java.lang.String getName() {
		return name;
	}
	public void setName(java.lang.String name) {
		this.name = name;
	}
	public java.lang.String getValue() {
		return value;
	}
	public void setValue(java.lang.String value) {
		this.value = value;
	}
	public java.lang.String getType() {
		return type;
	}
	public void setType(java.lang.String type) {
		this.type = type;
	}


	public java.lang.String getRemark() {
		return remark;
	}


	public void setRemark(java.lang.String remark) {
		this.remark = remark;
	}


	public Boolean getIsRequired() {
		return isRequired;
	}


	public void setIsRequired(Boolean isRequired) {
		this.isRequired = isRequired;
	}


	public Integer getDictionaryId() {
		return dictionaryId;
	}


	public void setDictionaryId(Integer dictionaryId) {
		this.dictionaryId = dictionaryId;
	}
	
	
	
	
	
	
}
