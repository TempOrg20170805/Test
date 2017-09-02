package com.sunrun.washer.manager;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import com.sunrun.washer.entity.JpushBinding;



/**
 * 文 件 名 : JpushBindingMng
 * 创 建 人： 金明明
 * 日 期：2017-8-13
 * 修 改 人： 
 * 日 期： 
 * 描 述：极光推送绑定
 */
public interface JpushBindingMng {
	
	/**
	 * 根据ID查找设备实体
	 * @param id
	 * @return
	 */
	public JpushBinding findById(Integer id);
		
	/**
	 * 设备绑定添加
	 * @param bean
	 * @param request
	 * @return
	 */
	public JpushBinding saveJpushBinding(JpushBinding bean,Integer groupId,Integer cityId,HttpServletRequest request);
	
	/**
	 * 删除
	 * 
	 * @param id
	 * @return
	 */
	public JpushBinding deleteById(Integer id);

	/**
     * 更新
     * @param bean
     * @return
     */
	public JpushBinding updateJpushBinding(JpushBinding bean);
	
	/**
	 * 根据 手机Mac、设备Mac查询list
	 * 
	 * @param deviceMac
	 * @param mobileMac
	 * @return
	 */
	public List<JpushBinding> findByJpushBinding( String username);

	/**
	 * 根据手机Mac 设备Mac判断是否有相同的记录存入
	 * 
	 * @param deviceMac
	 * @param mobileMac
	 * @return
	 */
	public Boolean isDeviceJpush( String username);

	/**
	 * 发送推送，开始洗涤
	 * 注意：这里查询该洗衣机最近的已完成订单当做用户进行推送。
	 * @param machineNo 洗衣机序列号
	 */
	public void JpushMsgSendStart(String machineNo);
	
	/**
	 * 发送推送，洗涤完成
	 * 注意：这里查询该洗衣机最近的已完成订单当做用户进行推送。
	 * @param machineNo 洗衣机序列号
	 */
	public void JpushMsgSendEnd(String machineNo);
	/**
	 * 推送-测试版
	 * @param registrationId 推送唯一编码
	 * @param msg 内容
	 */
	public void pushMsgTest(String registrationId, String msg);
}
