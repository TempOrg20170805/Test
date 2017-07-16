package com.sunrun.rest.dto;

import java.util.List;
/**
 * 
 * @author wangcy
 * @ClassName DictionaryListDTO.java
 * @CreateDate  2017-6-18
 * @descrintion  字典列表DTO
 * @editor 
 * @editDate
 */
public class DictionaryListDTO extends BaseDTO{

	
	private List<DictionaryInfoDTO> dictionaryList;

	public List<DictionaryInfoDTO> getDictionaryList() {
		return dictionaryList;
	}

	public void setDictionaryList(List<DictionaryInfoDTO> dictionaryList) {
		this.dictionaryList = dictionaryList;
	}
	
	
	
}
