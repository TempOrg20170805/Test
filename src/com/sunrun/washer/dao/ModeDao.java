package com.sunrun.washer.dao;
import com.sunrun.washer.model.*;import com.sunrun.washer.entity.*;


import java.util.List;

import com.jeecms.common.page.Pagination;
import com.jeecms.common.hibernate4.Updater;
/**
 * 文 件 名 : ModeDao.java
 * 创 建 人： 金明明
 * 日 期：2017-8-6
 * 修 改 人： 
 * 日 期： 
 * 描 述：洗衣模式 Dao层
 */
public interface ModeDao {

	/**
	 * 查询洗衣模式管理列表(不分页)
	 * @param modeModel 洗衣模式管理查询条件
	 * @return
	 */
	public List<Mode> queryModeListByModel(ModeModel modeModel);

	/**
	 * 保存洗衣模式管理
	 * @param 洗衣模式管理
	 * @return
	 */
	public Mode save(Mode mode);

	public Mode updateByUpdater(Updater<Mode> updater);

	/**
     * 删除洗衣模式管理
     * @param 洗衣模式管理Id
     * @return
     */
	public Mode deleteById(Integer modeId);

    /**
     * 根据Id获取实体
     * @param 洗衣模式管理Id
     * @return
     */
	public Mode findById(Integer modeId);


}

