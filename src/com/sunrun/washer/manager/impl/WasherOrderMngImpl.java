package com.sunrun.washer.manager.impl;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.apache.mina.core.session.IoSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.jeecms.common.hibernate4.Updater;
import com.jeecms.common.page.Pagination;
import com.jeecms.core.entity.CmsUser;
import com.jeecms.core.manager.CmsUserMng;
import com.sunrun.common.util.OrderUtils;
import com.sunrun.tcp.common.DataUtils;
import com.sunrun.tcp.common.ProtocolConsts;
import com.sunrun.tcp.mina.entity.WashOrder;
import com.sunrun.tcp.mina.handler.ServerHandler;
import com.sunrun.washer.dao.WasherOrderDao;
import com.sunrun.washer.entity.Floor;
import com.sunrun.washer.entity.FloorLayer;
import com.sunrun.washer.entity.Machine;
import com.sunrun.washer.entity.Mode;
import com.sunrun.washer.entity.UserMachine;
import com.sunrun.washer.entity.WasherOrder;
import com.sunrun.washer.enums.ModeNoEnum;
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
		List<UserMachine> usermachines = userMachineMng.findUserMachineListByMachine(washerOrderModelSave.getMechineId());
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
		bean.setFloorLayerX(machine.getFloorLayerX());
		bean.setFloorLayerY(machine.getFloorLayerY());
		// 赋值楼信息
		bean.setAddressDetail(floor.getAddressDetail());
		// 赋值楼层信息
		String floorLayerLocate = floorLayer.getLayer() + "层" + ((machine.getFloorLayerY() - 1) * floorLayer.getLayerX() + machine.getFloorLayerX()) + "机位";
		bean.setFloorLayerLocate(floorLayerLocate);
		bean.setLayer(floorLayer.getLayer());
		bean.setLayerX(floorLayer.getLayerX());
		bean.setLayerY(floorLayer.getLayerY());
		// 赋值洗衣机模式信息
		bean.setModeNo(ModeNoEnum.getContains(mode.getModeId()).getModeNo());
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
	public WasherOrder findByOutSn(String outSn) {
		return washerOrderDao.findByOutSn(outSn);
	}
	
	@Override
	public WasherOrder paySuccess(String outSn) {
		WasherOrder washerOrder = washerOrderDao.findByOutSn(outSn);
		washerOrder.setOrderState(WasherOrderStatusEnum.FINISH.getCode());
		washerOrder.setPaymentTime(new Date());
		washerOrder.setFinnshedTime(new Date());
		updateWasherOrder(washerOrder);
		// 渠道商增加收入
		cmsUserMng.updateMoney(washerOrder.getSeller().getId(), washerOrder.getOrderAmount(), WalletLogTypeEnum.INCOME.getCode(), washerOrder.getPayPlatform(), washerOrder.getMachineNo()+"洗衣机收入");
		// 调用洗衣机开始清洗
		// 洗衣机洗涤模式编号 washerOrder.getModeNo();
		// 洗衣机序列号 washerOrder.getMachineNo();
		pushControl(washerOrder.getMachineNo(), washerOrder.getModeNo());
		
		return washerOrder;
	}
	
	/**
	 * 推送控制
	 * @param machineNo
	 * @param modeNo
	 */
	public void pushControl(String machineNo, Integer modeNo) {
		Iterator<Map.Entry<String,IoSession>> ite_deviceiomap = ServerHandler.getDeviceIoMap().entrySet().iterator();
		String sn= machineNo;
		while (ite_deviceiomap.hasNext()) 
		{
			Map.Entry<String,IoSession> mapentry_deviceiomap =ite_deviceiomap.next();
			String devicemark =  mapentry_deviceiomap.getKey().toString();
			IoSession session = mapentry_deviceiomap.getValue();
			if(sn.equals(devicemark))
			{
				if(session!=null&&session.isConnected())
				{
					byte[] data=new byte[ProtocolConsts.PACKAGE_WASHANSWER_LEN];
					System.arraycopy(data, ProtocolConsts.ProtocolField.HEADER.getPos(), ProtocolConsts.PACKET_HEADER, 0,ProtocolConsts.ProtocolField.HEADER.getLen());
					System.arraycopy(data, ProtocolConsts.ProtocolField.PACKAGE_LEN.getPos(), ProtocolConsts.PACKAGE_WASHORDER_LEN, 0,1);
					System.arraycopy(data, ProtocolConsts.ProtocolField.FACTORY_ID.getPos(), ProtocolConsts.FACTORY_ID, 0,1);
					System.arraycopy(data, ProtocolConsts.ProtocolField.DEVICEID.getPos(), DataUtils.getDevMarkByteArray(sn), 0,ProtocolConsts.ProtocolField.DEVICEID.getLen());
					System.arraycopy(data, ProtocolConsts.ProtocolField.MSGTYPE.getPos(), modeNo, 0,1);
					WashOrder washOrder=new WashOrder(ProtocolConsts.PACKET_HEADER, ProtocolConsts.PACKAGE_WASHORDER_LEN,  ProtocolConsts.FACTORY_ID,DataUtils.getDevMarkByteArray(sn), (byte)(int)modeNo, DataUtils.XOR(data));
					session.write(washOrder);
				}
				break;
			}
		}
	}

	@Override
	public WasherOrder updatePayMsg(Integer wahserOrderId, Integer payPlatform,
			String payMessage) {
		WasherOrder washerOrder = findById(wahserOrderId);
		washerOrder.setPayPlatform(payPlatform);
		washerOrder.setPayMessage(payMessage);
		return updateWasherOrder(washerOrder);
	}

	@Override
	public WasherOrder queryWasherOrderByMachineNo(String machineNo) {
		WasherOrderModel washerOrderModel = new WasherOrderModel();
		washerOrderModel.setMachineNo(machineNo);
		washerOrderModel.setOrderState(WasherOrderStatusEnum.FINISH.getCode());
		washerOrderModel.setOrderByType(2);
		Pagination pagination = queryWasherOrderByModel(washerOrderModel, 1, 1);
		if (pagination != null && pagination.getTotalCount() > 0) {
			return (WasherOrder) pagination.getList().get(0);
		} else {
			return null;
		}
	}



}

