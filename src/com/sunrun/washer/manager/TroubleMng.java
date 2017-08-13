package com.sunrun.washer.manager;
import com.sunrun.washer.model.*;import com.sunrun.washer.entity.*;


import java.util.List;

import com.jeecms.common.page.Pagination;
/**
 * 文 件 名 : TroubleMng.java
 * 创 建 人： 金明明
 * 日 期：2017-8-13
 * 修 改 人： 
 * 日 期： 
 * 描 述：故障 Mng层
 */
public interface TroubleMng {

	/**
	 * 查询故障管理列表
	 * @param troubleModel 故障管理查询条件
	 * @param pageNo 当前页
	 * @param pageSize 每页数据量
	 * @return
	 */
	public Pagination queryTroubleByModel(TroubleModel troubleModel, Integer pageNo, Integer pageSize);

	/**
	 * 保存故障管理
	 * @param troubleModelSave 保存的信息对象
	 * @return
	 */
	public Trouble saveTrouble(TroubleModelSave troubleModelSave);

	/**
     * 删除
     * @param id
     * @return
     */
	public Trouble deleteById(Integer id);

	/**
	 * 根据id获取实体
	 * @param id
	 * @return
	 */
	public Trouble findById(Integer id);

	/**
	 * 开始修理
	 * @param troubleId
	 * @return
	 */
	public Trouble startRepair(Integer troubleId);
	
	/**
	 * 完成修理
	 * @param troubleId
	 * @return
	 */
	public Trouble endRepair(Integer troubleId);
}

