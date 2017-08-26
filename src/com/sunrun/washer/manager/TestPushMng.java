package com.sunrun.washer.manager;
import com.sunrun.washer.model.*;import com.sunrun.washer.entity.*;


import java.util.List;

import com.jeecms.common.page.Pagination;
/**
 * 文 件 名 : TestPushMng.java
 * 创 建 人： 金明明
 * 日 期：2017-8-26
 * 修 改 人： 
 * 日 期： 
 * 描 述：测试推送 Mng层
 */
public interface TestPushMng {

	/**
	 * 查询测试推送管理列表(不分页)
	 * @param testPushModel 测试推送管理查询条件
	 * @return
	 */
	public List<TestPush> queryTestPushListByModel(TestPushModel testPushModel);

	/**
	 * 保存测试推送管理
	 * @param testPushModelSave 保存的信息对象
	 * @return
	 */
	public TestPush saveTestPush(TestPushModelSave testPushModelSave);

	/**
	 * 更新测试推送管理基本信息
	 * @param testPushModelUpdate 更新的信息
	 * @return
	 */
	public TestPush updateTestPush(TestPushModelUpdate testPushModelUpdate);

	/**
     * 删除
     * @param id
     * @return
     */
	public TestPush deleteById(Integer id);

	/**
	 * 根据id获取实体
	 * @param id
	 * @return
	 */
	public TestPush findById(Integer id);

	/**
	 * 根据洗衣机编号查询
	 * @param machineNo
	 * @return
	 */
	public TestPush findByMachineNo(String machineNo);
	
	/**
	 * 获取tcp服务器的数据，直接推送
	 * @param str
	 */
	public void testGetMsgToPush(String machineNo, String str);
}

