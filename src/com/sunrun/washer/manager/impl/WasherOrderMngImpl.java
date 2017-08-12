package com.sunrun.washer.manager.impl;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.jeecms.common.hibernate4.Updater;
import com.jeecms.common.page.Pagination;
import com.jeecms.core.entity.CmsUser;
import com.jeecms.core.manager.CmsUserMng;
import com.sunrun.common.util.OrderUtils;
import com.sunrun.washer.dao.WasherOrderDao;
import com.sunrun.washer.entity.Floor;
import com.sunrun.washer.entity.FloorLayer;
import com.sunrun.washer.entity.Machine;
import com.sunrun.washer.entity.Mode;
import com.sunrun.washer.entity.UserMachine;
import com.sunrun.washer.entity.WasherOrder;
import com.sunrun.washer.enums.WalletLogTypeEnum;
import com.sunrun.washer.enums.WasherOrderStatusEnum;
import com.sunrun.washer.manager.ModeMng;
import com.sunrun.washer.manager.UserMachineMng;
import com.sunrun.washer.manager.WasherOrderMng;
import com.sunrun.washer.model.WasherOrderModel;
import com.sunrun.washer.model.WasherOrderModelSave;
import com.sunrun.washer.model.WasherOrderModelUpdate;
/**
 * 文 件 名 : WasherOrderMngImpl.java
 * 创 建 人： 金明明
 * 日 期：2017-8-7
 * 修 改 人： 
 * 日 期： 
 * 描 述：订单 MngImpl
 */
@Service
@Transactional
public class WasherOrderMngImpl implements WasherOrderMng{

	@Autowired
	private WasherOrderDao washerOrderDao;
	@Autowired
	private UserMachineMng userMachineMng;
	@Autowired
	private CmsUserMng cmsUserMng;
	@Autowired
	private ModeMng modeMng;

	@Override
	public Pagination queryWasherOrderByModel(WasherOrderModel washerOrderModel, Integer pageNo, Integer pageSize) {
		Pagination pagination = washerOrderDao.queryWasherOrderByModel(washerOrderModel, pageNo, pageSize);
		// 编写代码
		return pagination;
	}

	@Override
	public WasherOrder saveWasherOrder(WasherOrderModelSave washerOrderModelSave) {
		WasherOrder bean = new WasherOrder();
		// 获取买家
		CmsUser buyer = cmsUserMng.findById(washerOrderModelSave.getUserId());
		List<UserMachine> usermachines = userMachineMng.findMachineListByUsers(washerOrderModelSave.getMechineId());
		UserMachine userMachine = usermachines.get(0);
		// 获取卖家
		CmsUser seller = userMachine.getCmsUser();
		// 获取洗衣机
		Machine machine = userMachine.getMachine();
		// 获取楼层
		FloorLayer floorLayer = machine.getFloorLayer();
		// 获取楼
		Floor floor = floorLayer.getFloor();
		// 获取洗衣模式
		Mode mode = modeMng.findById(washerOrderModelSave.getModeId());
		
		// 赋值买家卖家
		bean.setSeller(seller);
		bean.setSellerName(seller.getUsername());
		bean.setBuyer(buyer);
		bean.setBuyerName(buyer.getUsername());
		// 赋值订单基本信息
		bean.setAddTime(new Date());
		String outSn = OrderUtils.createOrder();
		bean.setOutSn(outSn);
		bean.setOrderAmount(mode.getModeMoney());
		// 赋值洗衣机信息
		bean.setMachine(machine);
		bean.setMachineNo(machine.getMachineNo());
		// 赋值楼信息
		bean.setAddressDetail(floor.getAddressDetail());
		// 赋值楼层信息
		String floorLayerLocate = floorLayer.getLayer() + "层" + ((machine.getFloorLayerY() - 1) * floorLayer.getLayerX() + machine.getFloorLayerX()) + "机位";
		bean.setFloorLayerLocate(floorLayerLocate);
		// 赋值洗衣机模式信息
		bean.setModeName(mode.getName());
		bean.setModeTime(mode.getModeTime());
		return washerOrderDao.save(bean);
	}

	@Override
	public WasherOrder updateWasherOrder(WasherOrderModelUpdate washerOrderModelUpdate) {
		return null;
	}
	/**
	 * 更新
	 * @param bean
	 * @return
	 */
	private WasherOrder updateWasherOrder(WasherOrder bean) {
		Updater<WasherOrder> updater = new Updater<WasherOrder>(bean);
		return washerOrderDao.updateByUpdater(updater);
	}

	@Override
	public WasherOrder deleteById(Integer id) {
		WasherOrder bean = washerOrderDao.deleteById(id);
		return bean;
	}

	@Override
	public WasherOrder findById(Integer id) {
		return washerOrderDao.findById(id);
	}

	@Override
	public WasherOrder paySuccess(Integer washerOrderId) {
		WasherOrder washerOrder = findById(washerOrderId);
		washerOrder.setOrderState(WasherOrderStatusEnum.PAY.getCode());
		washerOrder.setPaymentTime(new Date());
		washerOrder.setFinnshedTime(new Date());
		updateWasherOrder(washerOrder);
		cmsUserMng.updateMoney(washerOrder.getSeller().getId(), washerOrder.getOrderAmount(), WalletLogTypeEnum.INCOME.getCode(), washerOrder.getPayPlatform(), washerOrder.getMachineNo()+"洗衣机收入");
		// 调用洗衣机开始清洗
		return null;
	}


}

