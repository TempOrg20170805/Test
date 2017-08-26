package com.sunrun.washer.dao;
import com.sunrun.washer.model.*;import com.sunrun.washer.entity.*;


import java.util.List;

import com.jeecms.common.page.Pagination;
import com.jeecms.common.hibernate4.Updater;
/**
 * 文 件 名 : TestPushDao.java
 * 创 建 人： 金明明
 * 日 期：2017-8-26
 * 修 改 人： 
 * 日 期： 
 * 描 述：测试推送 Dao层
 */
public interface TestPushDao {

	/**
	 * 查询测试推送管理列表(不分页)
	 * @param testPushModel 测试推送管理查询条件
	 * @return
	 */
	public List<TestPush> queryTestPushListByModel(TestPushModel testPushModel);

	/**
	 * 保存测试推送管理
	 * @param 测试推送管理
	 * @return
	 */
	public TestPush save(TestPush testPush);

	public TestPush updateByUpdater(Updater<TestPush> updater);

	/**
     * 删除测试推送管理
     * @param 测试推送管理Id
     * @return
     */
	public TestPush deleteById(Integer testPushId);

    /**
     * 根据Id获取实体
     * @param 测试推送管理Id
     * @return
     */
	public TestPush findById(Integer testPushId);

	/**
	 * 根据洗衣机编号查询
	 * @param machineNo
	 * @return
	 */
	public TestPush findByMachineNo(String machineNo);

}

