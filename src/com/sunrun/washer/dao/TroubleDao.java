package com.sunrun.washer.dao;
import com.sunrun.washer.model.*;import com.sunrun.washer.entity.*;


import java.util.List;

import com.jeecms.common.page.Pagination;
import com.jeecms.common.hibernate4.Updater;
/**
 * 文 件 名 : TroubleDao.java
 * 创 建 人： 金明明
 * 日 期：2017-8-13
 * 修 改 人： 
 * 日 期： 
 * 描 述：故障 Dao层
 */
public interface TroubleDao {

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
	 * @param 故障管理
	 * @return
	 */
	public Trouble save(Trouble trouble);

	public Trouble updateByUpdater(Updater<Trouble> updater);

	/**
     * 删除故障管理
     * @param 故障管理Id
     * @return
     */
	public Trouble deleteById(Integer troubleId);

    /**
     * 根据Id获取实体
     * @param 故障管理Id
     * @return
     */
	public Trouble findById(Integer troubleId);


}

