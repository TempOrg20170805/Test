package com.sunrun.rest.dto;


import java.util.List;

/**
 * 文 件 名 : WalletLogQueryDTO
 * 创 建 人： 金明明
 * 日 期：2017-8-6
 * 修 改 人： 
 * 日 期： 
 * 描 述：消费日志管理DTO 查询结果
 */
public class WalletLogQueryDTO extends BaseDTO {
	public enum WalletLogQueryDTOEnum implements BaseStateDTOEnum{
		;
		
		private Integer stateCode;
		private String msg;
		private WalletLogQueryDTOEnum(Integer stateCode, String msg) {
			this.stateCode = stateCode;
			this.msg = msg;
		}
		@Override
		public String getMsg() {
			return this.msg;
		}

		@Override
		public Integer getStateCode() {
			return this.stateCode;
		}
		
	}
	private Integer pageNo; // 当前页
	private Integer pageSize; // 每页数据量
	private Integer totalCount; // 所有数据量

	private List<WalletLogDTO> walletLogDTOs;
	
	public Integer getPageNo() {
		return pageNo;
	}
	public void setPageNo(Integer pageNo) {
		this.pageNo = pageNo;
	}
	public Integer getPageSize() {
		return pageSize;
	}
	public void setPageSize(Integer pageSize) {
		this.pageSize = pageSize;
	}
	public Integer getTotalCount() {
		return totalCount;
	}
	public void setTotalCount(Integer totalCount) {
		this.totalCount = totalCount;
	}
	public List<WalletLogDTO> getWalletLogDTOs() {
		return walletLogDTOs;
	}
	public void setWalletLogDTOs(
			List<WalletLogDTO> walletLogDTOs) {
		this.walletLogDTOs = walletLogDTOs;
	}
}

