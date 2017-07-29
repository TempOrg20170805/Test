package com.sunrun.washer.manager;

import com.jeecms.common.hibernate4.Updater;
import com.jeecms.common.page.Pagination;
import com.sunrun.washer.entity.Feedback;

public interface FeedbackMng {
	/**
	 * 获取列表
	 * 
	 * @param pageNo
	 * @param pageSize
	 * @return
	 */
	public Pagination getPage(Integer pageNo, Integer pageSize);

	/**
	 * 删除反馈信息
	 * 
	 * @param id
	 * @return
	 */
	public Feedback deleteById(Integer id);
	/**
	 * 批量删除
	 * @param ids
	 * @return
	 */
	
	public Feedback[] deleteByIds(Integer[] ids);
	/**
	 * 添加反馈信息
	 * 
	 * @param bean
	 * @return
	 */
	public Feedback save(Feedback bean);

	/**
	 * 通过ID查询信息记录
	 * 
	 * @param id
	 * @return
	 */
	public Feedback findById(Integer id);

	/**
	 * 更新
	 * 
	 * @param bean
	 * @return
	 */
	public Feedback update(Feedback bean);
}
