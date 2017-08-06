package com.sunrun.washer.manager;
import com.sunrun.washer.model.*;import com.sunrun.washer.entity.*;


import java.util.List;

import com.jeecms.common.page.Pagination;
/**
 * 文 件 名 : ModeMng.java
 * 创 建 人： 金明明
 * 日 期：2017-8-6
 * 修 改 人： 
 * 日 期： 
 * 描 述：洗衣模式 Mng层
 */
public interface ModeMng {

	/**
	 * 查询洗衣模式管理列表(不分页)
	 * @param modeModel 洗衣模式管理查询条件
	 * @return
	 */
	public List<Mode> queryModeListByModel(ModeModel modeModel);

	/**
	 * 保存洗衣模式管理
	 * @param modeModelSave 保存的信息对象
	 * @return
	 */
	public Mode saveMode(ModeModelSave modeModelSave);

	/**
	 * 更新洗衣模式管理基本信息
	 * @param modeModelUpdate 更新的信息
	 * @return
	 */
	public Mode updateMode(ModeModelUpdate modeModelUpdate);

	/**
     * 删除
     * @param id
     * @return
     */
	public Mode deleteById(Integer id);

	/**
	 * 根据id获取实体
	 * @param id
	 * @return
	 */
	public Mode findById(Integer id);


}

