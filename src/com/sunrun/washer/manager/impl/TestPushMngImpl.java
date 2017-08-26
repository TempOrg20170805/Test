package com.sunrun.washer.manager.impl;
import com.sunrun.washer.manager.*;import com.sunrun.washer.dao.*;import com.sunrun.washer.model.*;import com.sunrun.washer.entity.*;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.jeecms.common.hibernate4.Updater;
import com.jeecms.common.page.Pagination;
/**
 * 文 件 名 : TestPushMngImpl.java
 * 创 建 人： 金明明
 * 日 期：2017-8-26
 * 修 改 人： 
 * 日 期： 
 * 描 述：测试推送 MngImpl
 */
@Service
@Transactional
public class TestPushMngImpl implements TestPushMng{

	@Autowired
	private TestPushDao testPushDao;
	@Autowired
	private JpushBindingMng jpushBindingMng;
	
	@Override
	public List<TestPush> queryTestPushListByModel(TestPushModel testPushModel) {
		return testPushDao.queryTestPushListByModel(testPushModel);
	}

	@Override
	public TestPush saveTestPush(TestPushModelSave testPushModelSave) {
		TestPush oldBean = findByMachineNo(testPushModelSave.getMachineNo());
		if (oldBean != null) {
			// 向原用户推送,machineNo的推送编号已变更
			oldBean.setRegistrationId(testPushModelSave.getRegistrationId());
			updateTestPush(oldBean);
			return oldBean;
		} else {
			TestPush bean = new TestPush();
			bean.setMachineNo(testPushModelSave.getMachineNo());
			bean.setRegistrationId(testPushModelSave.getRegistrationId());
			// 赋值保存的必要信息
			return testPushDao.save(bean);
		}
	}

	@Override
	public TestPush updateTestPush(TestPushModelUpdate testPushModelUpdate) {
		TestPush testPush = findById(testPushModelUpdate.getTestPushId());
		testPush.setRegistrationId(testPushModelUpdate.getRegistrationId());
		return updateTestPush(testPush);
	}
	/**
	 * 更新
	 * @param bean
	 * @return
	 */
	private TestPush updateTestPush(TestPush bean) {
		Updater<TestPush> updater = new Updater<TestPush>(bean);
		return testPushDao.updateByUpdater(updater);
	}

	@Override
	public TestPush deleteById(Integer id) {
		TestPush bean = testPushDao.deleteById(id);
		return bean;
	}

	@Override
	public TestPush findById(Integer id) {
		return testPushDao.findById(id);
	}

	@Override
	public TestPush findByMachineNo(String machineNo) {
		return testPushDao.findByMachineNo(machineNo);
	}

	@Override
	public void testGetMsgToPush(String machineNo, String str) {
		TestPush testPush = findByMachineNo(machineNo);
		if (testPush != null) {
			jpushBindingMng.pushMsgTest(testPush.getRegistrationId(), "测试推送", str);
		} else {
			System.out.println(machineNo+"无推送Id");
		}
	}


}

