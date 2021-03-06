package com.jeecms.cms.api;


import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;
import org.json.JSONArray;
import org.json.JSONException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.jeecms.cms.entity.assist.CmsMessage;
import com.jeecms.cms.entity.assist.CmsReceiverMessage;
import com.jeecms.cms.entity.main.ApiAccount;
import com.jeecms.cms.entity.main.ApiRecord;
import com.jeecms.cms.manager.assist.CmsMessageMng;
import com.jeecms.cms.manager.assist.CmsReceiverMessageMng;
import com.jeecms.cms.manager.main.ApiAccountMng;
import com.jeecms.cms.manager.main.ApiRecordMng;
import com.jeecms.cms.manager.main.ApiUserLoginMng;
import com.jeecms.common.util.ArrayUtils;
import com.jeecms.common.web.RequestUtils;
import com.jeecms.common.web.ResponseUtils;
import com.jeecms.core.entity.CmsSite;
import com.jeecms.core.entity.CmsUser;
import com.jeecms.core.manager.CmsSiteMng;
import com.jeecms.core.manager.CmsUserMng;
import com.jeecms.core.web.WebErrors;
import com.jeecms.core.web.util.CmsUtils;

@Controller
public class MessageApiAct{
	
	//保存
	private static final String OPERATE_SAVE="save";
	//修改
	private static final String OPERATE_UPDATE="update";
	//删除至回收站
	private static final String OPERATE_TRASH="trash";
	//还原
	private static final String OPERATE_REVERT="revert";
	//删除
	private static final String OPERATE_DELETE="delete";
	
	/**
	 * 我的站内信息API
	 * @param siteId 站点id 非必选 默认当前站
	 * @param box 信息类型 非必选  0收件箱 1发件箱 2草稿箱 3垃圾箱 默认0
	 * @param appId   appid 必选
	 * @param sessionKey 会话标识 必选
	 * @param first 开始 非必选 默认0
	 * @param count 数量 非必选 默认10 
	 */
	@RequestMapping(value = "/api/message/list.jspx")
	public void messageList(
			Integer siteId, Integer box,
			String appId,String sessionKey,Integer first,Integer count,
			HttpServletRequest request,HttpServletResponse response) 
					throws JSONException {
		String body="\"\"";
		String message="\"\"";
		String status=Constants.API_STATUS_FAIL;
		CmsUser user = null;
		if(first==null){
			first=0;
		}
		if(count==null){
			count=10;
		}
		if(siteId==null){
			siteId=CmsUtils.getSiteId(request);
		}
		if(box==null){
			box=0;
		}
		WebErrors errors=WebErrors.create(request);
		ApiAccount apiAccount = null;
		//验证公共非空参数
		errors=ApiValidate.validateRequiredParams(request,errors, appId,sessionKey);
		if(!errors.hasErrors()){
			apiAccount=apiAccountMng.findByAppId(appId);
			//验证appid
			errors=ApiValidate.validateApiAccount(request, errors,apiAccount);
			//apiAccount可能获取不到，需要再次判断
			if(!errors.hasErrors()){
				String aesKey=apiAccount.getAesKey();
				user=apiUserLoginMng.findUser(sessionKey, aesKey,apiAccount.getIvKey());
				//验证用户
				if(user==null){
					errors.addErrorString(Constants.API_MESSAGE_USER_NOT_LOGIN);
				}
			}
		}
		if(errors.hasErrors()){
			message="\""+errors.getErrors().get(0)+"\"";
		}else{
			JSONArray jsonArray=new JSONArray();
			if(box==0){
				//收件箱
				List<CmsReceiverMessage> list = receiverMessageMng.getList(
						siteId, null, user.getId(), null, null, 
						null, null, box, true,first,count);
				if(list!=null&&list.size()>0){
					for(int i=0;i<list.size();i++){
						jsonArray.put(i, list.get(i).convertToJson());
					}
				}
			}else if(box==3){
				//垃圾箱
				List<CmsReceiverMessage> list = receiverMessageMng.getList(
						siteId, user.getId(), user.getId(), null, null, 
						null, null, box, true,first,count);
				if(list!=null&&list.size()>0){
					for(int i=0;i<list.size();i++){
						jsonArray.put(i, list.get(i).convertToJson());
					}
				}
			}else if(box==2||box==1){
				//草稿箱或者发件箱
				List<CmsMessage>list=messageMng.getList(siteId, user.getId(), null,
						null, null, null,null, box, true,first,count);
				if(list!=null&&list.size()>0){
					for(int i=0;i<list.size();i++){
						jsonArray.put(i, list.get(i).convertToJson());
					}
				}
			}
			body=jsonArray.toString();
			message=Constants.API_MESSAGE_SUCCESS;
			status=Constants.API_STATUS_SUCCESS;
		}
		ApiResponse apiResponse=new ApiResponse(body, message, status);
		ResponseUtils.renderApiJson(response, request, apiResponse);
	}
	
	/**
	 * 信息读取API
	 * @param id 信息ID 必选
	 * @param appId   appid 必选
	 * @param sessionKey 会话标识 必选
	 */
	@RequestMapping(value = "/api/message/get.jspx")
	public void messageRead(
			Integer id,String appId,String sessionKey,
			HttpServletRequest request,HttpServletResponse response) 
					throws JSONException {
		String body="\"\"";
		String message="\"\"";
		String status=Constants.API_STATUS_FAIL;
		CmsUser user = null;
		WebErrors errors=WebErrors.create(request);
		ApiAccount apiAccount = null;
		//验证公共非空参数
		errors=ApiValidate.validateRequiredParams(request,errors, appId,sessionKey,id);
		if(!errors.hasErrors()){
			apiAccount=apiAccountMng.findByAppId(appId);
			//验证appid
			errors=ApiValidate.validateApiAccount(request, errors,apiAccount);
			//apiAccount可能获取不到，需要再次判断
			if(!errors.hasErrors()){
				String aesKey=apiAccount.getAesKey();
				user=apiUserLoginMng.findUser(sessionKey, aesKey,apiAccount.getIvKey());
				//验证用户
				if(user==null){
					errors.addErrorString(Constants.API_MESSAGE_USER_NOT_LOGIN);
				}
			}
		}
		if(errors.hasErrors()){
			message="\""+errors.getErrors().get(0)+"\"";
		}else{
			CmsReceiverMessage msg = receiverMessageMng.findById(id);
			if (msg != null) {
				// 阅读收信
				// 非收件人和发件人无权查看信件
				if (!msg.getMsgReceiverUser().equals(user)
						&& !msg.getMsgSendUser().equals(user)) {
					message="\"no Permissions View\"";
				}
				// 收件人查看更新已读状态
				if (msg.getMsgReceiverUser().equals(user)) {
					msg.setMsgStatus(true);
					receiverMessageMng.update(msg);
				}
				body=msg.convertToJson().toString();
			} else {
				// 阅读已发信
				CmsMessage sendmsg = messageMng.findById(id);
				if(sendmsg!=null){
					body=sendmsg.convertToJson().toString();
				}
			}
			message=Constants.API_MESSAGE_SUCCESS;
			status=Constants.API_STATUS_SUCCESS;
		}
		ApiResponse apiResponse=new ApiResponse(body, message, status);
		ResponseUtils.renderApiJson(response, request, apiResponse);
	}
	
	/**
	 * 信息保存发送API
	 * @param toUser 信息目的用户名  必选
	 * @param title  标题  必选
	 * @param content 内容 必选
	 * @param box 1发信  2存草稿   必选
	 * @param siteId 站点id 非必选  默认当前站id
	 * @param appId appid 必选
	 * @param nonce_str 随机字符串  必选
	 * @param sign 签名 必选
	 * @param sessionKey 会话标识 必选
	 */
	@RequestMapping(value = "/api/message/send.jspx")
	public void messageSave(String toUser,String title,String content,
			Integer box,Integer siteId,String appId,String sessionKey,
			String nonce_str,String sign,
			HttpServletRequest request,HttpServletResponse response)
					throws JSONException {
		if(siteId==null){
			siteId=CmsUtils.getSiteId(request);
		}
		if(box==null){
			box=1;
		}
		saveOrUpdate(OPERATE_SAVE, null, true,toUser, title, content, siteId,
				appId, sessionKey, nonce_str, sign, box, request, response);
	}
	
	/**
	 * 草稿信息修改API
	 * @param id 信息ID 必选 
	 * @param title  标题  非必选
	 * @param content 内容  非必选
	 * @param siteId 站点id 非必选  默认当前站id
	 * @param appId appid 必选
	 * @param nonce_str 随机字符串  必选
	 * @param sign 签名 必选
	 * @param sessionKey 会话标识 必选
	 */
	@RequestMapping(value = "/api/message/draftUpdate.jspx")
	public void messageUpdate(
			Integer id,String title,String content,
			Integer siteId,String appId,String sessionKey,
			String nonce_str,String sign,
			HttpServletRequest request,HttpServletResponse response)
					throws JSONException {
		if(siteId==null){
			siteId=CmsUtils.getSiteId(request);
		}
		saveOrUpdate(OPERATE_UPDATE, id, false,null, title, content, siteId,
				appId, sessionKey, nonce_str, sign, 2, request, response);
	}
	
	
	/**
	 * 草稿信息发送API
	 * @param id 信息id 必选
	 * @param appId appid 必选
	 * @param nonce_str 随机字符串  必选
	 * @param sign 签名 必选
	 * @param sessionKey 会话标识 必选
	 */
	@RequestMapping(value = "/api/message/draftToSend.jspx")
	public void messageDraftSend(
			Integer id,String appId,String sessionKey,String nonce_str,String sign,
			HttpServletRequest request,HttpServletResponse response)throws JSONException {
		saveOrUpdate(OPERATE_UPDATE, id, true, null,
				null, null, null, appId, sessionKey,
				nonce_str, sign, 1, request, response);
	}
	
	/**
	 * 删除信息到回收站API
	 * @param ids 信息id 逗号,分隔 
	 * @param appId appid 必选
	 * @param sessionKey 会话标识 必选 
	 */
	@RequestMapping(value = "/api/message/trash.jspx")
	public void messageTrash(
			String  ids,String appId,String sessionKey,
			HttpServletRequest request,HttpServletResponse response)throws JSONException {
		messageOperate(OPERATE_TRASH, ids, appId, sessionKey, request, response);
	}
	
	/**
	 * 回收站还原API
	 * @param ids 信息id 逗号,分隔 
	 * @param appId appid 必选
	 * @param sessionKey 会话标识 必选 
	 */
	@RequestMapping(value = "/api/message/revert.jspx")
	public void messageRevert(
			String  ids,String appId,String sessionKey,
			HttpServletRequest request,HttpServletResponse response)throws JSONException {
		messageOperate(OPERATE_REVERT, ids, appId, sessionKey, request, response);
	}
	
	/**
	 * 删除站内信息API
	 * @param ids 信息id 逗号,分隔 
	 * @param appId appid 必选
	 * @param sessionKey 会话标识 必选 
	 */
	@RequestMapping(value = "/api/message/delete.jspx")
	public void messageDelete(
			String  ids,String appId,String sessionKey,
			HttpServletRequest request,HttpServletResponse response)throws JSONException {
		messageOperate(OPERATE_DELETE, ids, appId, sessionKey, request, response);
	}
	
	private void saveOrUpdate(
			String operate,Integer id,boolean toSend,
			String toUser,String title,String content,
			Integer siteId,String appId,String sessionKey,
			String nonce_str,String sign,Integer box,
			HttpServletRequest request,HttpServletResponse response){
		String body="\"\"";
		String message="\"\"";
		String status=Constants.API_STATUS_FAIL;
		CmsUser user = null;
		WebErrors errors=WebErrors.create(request);
		ApiAccount apiAccount = null;
		//验证公共非空参数
		errors=ApiValidate.validateRequiredParams(request,errors, appId,
				sessionKey,nonce_str,sign);
		if(!errors.hasErrors()){
			apiAccount=apiAccountMng.findByAppId(appId);
			//验证签名
			errors=ApiValidate.validateSign(request, errors, apiAccount, sign);
			//apiAccount可能获取不到，需要再次判断
			if(!errors.hasErrors()){
				String aesKey=apiAccount.getAesKey();
				user=apiUserLoginMng.findUser(sessionKey, aesKey,apiAccount.getIvKey());
				//验证用户
				if(user==null){
					errors.addErrorString(Constants.API_MESSAGE_USER_NOT_LOGIN);
				}
			}
		}
		if(errors.hasErrors()){
			message="\""+errors.getErrors().get(0)+"\"";
		}else{
			ApiRecord record=apiRecordMng.findBySign(sign, appId);
			if(record!=null){
				message=Constants.API_MESSAGE_REQUEST_REPEAT;
			}else{
				//发送信息
				if(operate==OPERATE_SAVE){
					if(StringUtils.isNotBlank(toUser)
							  &&StringUtils.isNotBlank(content)
							  &&StringUtils.isNotBlank(title)){
						CmsSite site=siteMng.findById(siteId);
						CmsUser msgReceiverUser = userMng.findByUsername(toUser);
						if(msgReceiverUser!=null){
							// 发送端
							CmsMessage msg=new CmsMessage();
							msg.setMsgBox(box);
							msg.setMsgSendUser(user);
							msg.setMsgTitle(title);
							msg.setMsgContent(content);
							msg.setMsgSendUser(user);
							msg.setMsgReceiverUser(msgReceiverUser);
							msg.setMsgStatus(false);
							if(toSend){
								msg.setSendTime(new Date());
							}else{
								msg.setSendTime(null);
							}
							msg.setSite(site);
							msg=messageMng.save(msg);
							CmsReceiverMessage receiverMessage = new CmsReceiverMessage(msg);
							receiverMessage.setMsgBox(box);
							receiverMessage.setMessage(msg);
							// 接收端（有一定冗余）
							receiverMessageMng.save(receiverMessage);
							apiRecordMng.callApiRecord(RequestUtils.getIpAddr(request),
									appId, "/api/message/save.jspx",sign);
							body="{\"id\":"+"\""+msg.getId()+"\"}";
							message=Constants.API_MESSAGE_SUCCESS;
						}else{
							message=Constants.API_MESSAGE_USER_NOT_FOUND;
						}
					}else{
						message=Constants.API_MESSAGE_PARAM_ERROR;
					}
				}else if(operate==OPERATE_UPDATE){
					//草稿信息修改
					if(id!=null){
						CmsMessage msg=messageMng.findById(id);
						if(msg!=null){
							if(toSend){
								msg.setMsgBox(1);
								msg.setSendTime(new Date());
							}
							if(StringUtils.isNotBlank(content)){
								msg.setMsgContent(content);
							}
							if(StringUtils.isNotBlank(title)){
								msg.setMsgTitle(title);
							}
							msg = messageMng.update(msg);
							// 更新发送表的信息，收件表的信息同步更新
							Set<CmsReceiverMessage> receiverMessageSet = msg.getReceiverMsgs();
							Iterator<CmsReceiverMessage> it = receiverMessageSet.iterator();
							CmsReceiverMessage receiverMessage;
							while (it.hasNext()) {
								receiverMessage = it.next();
								receiverMessage.setMsgContent(msg.getContentHtml());
								receiverMessage.setMsgReceiverUser(msg.getMsgReceiverUser());
								receiverMessage.setMsgTitle(msg.getMsgTitle());
								receiverMessage.setMessage(msg);
								if(toSend){
									receiverMessage.setMsgBox(0);
									receiverMessage.setSendTime(new Date());
								}
								// 接收端（有一定冗余）
								receiverMessageMng.update(receiverMessage);
							}
							apiRecordMng.callApiRecord(RequestUtils.getIpAddr(request),
									appId, "/api/message/update.jspx",sign);
							message=Constants.API_MESSAGE_SUCCESS;
						}else{
							message="\"msg not found\"";
						}
					}else{
						message=Constants.API_MESSAGE_PARAM_ERROR;
					}
				}
				status=Constants.API_STATUS_SUCCESS;
			}
		}	
		ApiResponse apiResponse=new ApiResponse(body, message, status);
		//js跨域请求
		String callback = request.getParameter("callback");
		if(StringUtils.isNotBlank(callback)){
			ResponseUtils.renderJson(response,callback+"(" + apiResponse.toString() + ")" );
		}else{
			ResponseUtils.renderJson(response, apiResponse.toString());
		}
	}
	
	private void messageOperate(String operate,String  ids,String appId,String sessionKey
			,HttpServletRequest request,HttpServletResponse response){
		String body="\"\"";
		String message="\"\"";
		String status=Constants.API_STATUS_FAIL;
		CmsUser user = null;
		WebErrors errors=WebErrors.create(request);
		ApiAccount apiAccount = null;
		//验证公共非空参数
		errors=ApiValidate.validateRequiredParams(request,errors, appId,sessionKey);
		if(!errors.hasErrors()){
			apiAccount=apiAccountMng.findByAppId(appId);
			//验证appid
			errors=ApiValidate.validateApiAccount(request, errors,apiAccount);
			//apiAccount可能获取不到，需要再次判断
			if(!errors.hasErrors()){
				String aesKey=apiAccount.getAesKey();
				user=apiUserLoginMng.findUser(sessionKey, aesKey,apiAccount.getIvKey());
				//验证用户
				if(user==null){
					errors.addErrorString(Constants.API_MESSAGE_USER_NOT_LOGIN);
				}
			}
		}
		if(errors.hasErrors()){
			message="\""+errors.getErrors().get(0)+"\"";
		}else{
			Integer[] intIds=ArrayUtils.parseStringToArray(ids);
			CmsMessage msg;
			CmsReceiverMessage receiverMessage;
			if(operate==OPERATE_TRASH){
				Iterator<CmsReceiverMessage> it;
				for (Integer i = 0; i < intIds.length; i++) {
					msg = messageMng.findById(intIds[i]);
					receiverMessage = receiverMessageMng.findById(intIds[i]);
					if (msg != null && msg.getMsgSendUser().equals(user)) {
						msg.setMsgBox(3);
						receiverMessage = new CmsReceiverMessage();
						receiverMessage.setMsgBox(3);
						receiverMessage.setMsgContent(msg.getMsgContent());
						receiverMessage.setMsgSendUser(msg.getMsgSendUser());
						receiverMessage.setMsgReceiverUser(user);
						receiverMessage.setMsgStatus(msg.getMsgStatus());
						receiverMessage.setMsgTitle(msg.getMsgTitle());
						receiverMessage.setSendTime(msg.getSendTime());
						receiverMessage.setSite(msg.getSite());
						receiverMessage.setMessage(null);
						// 接收端（有一定冗余）
						receiverMessageMng.save(receiverMessage);
						// 清空该发件对应的收件关联关系
						Set<CmsReceiverMessage> receiverMessages = msg
								.getReceiverMsgs();
						if (receiverMessages != null && receiverMessages.size() > 0) {
							it = receiverMessages.iterator();
							CmsReceiverMessage tempReceiverMessage;
							while (it.hasNext()) {
								tempReceiverMessage = it.next();
								tempReceiverMessage.setMessage(null);
								receiverMessageMng.update(tempReceiverMessage);
							}
						}
						messageMng.deleteById(intIds[i]);
					}
					if (receiverMessage != null
							&& receiverMessage.getMsgReceiverUser().equals(user)) {
						receiverMessage.setMsgBox(3);
						receiverMessageMng.update(receiverMessage);
					}
				}
			}else if(operate==OPERATE_REVERT){
				for (Integer i = 0; i < intIds.length; i++) {
					receiverMessage = receiverMessageMng.findById(intIds[i]);
					// 收件箱
					if (receiverMessage != null
							&& receiverMessage.getMsgReceiverUser().equals(user)) {
						receiverMessage.setMsgBox(0);
						receiverMessageMng.update(receiverMessage);
					}
				}
			}else if(operate==OPERATE_DELETE){
				for (Integer i = 0; i < intIds.length; i++) {
					// 清空收到的站内信
					receiverMessage = receiverMessageMng.findById(intIds[i]);
					if (receiverMessage != null
							&& receiverMessage.getMsgReceiverUser().equals(user)) {
						receiverMessageMng.deleteById(intIds[i]);
					} else {
						// 清空发送的站内信
						msg = messageMng.findById(intIds[i]);
						if (msg != null
								&& msg.getMsgSendUser().equals(user)) {
							for(CmsReceiverMessage receiverMsg:msg.getReceiverMsgs()){
								receiverMsg.setMessage(null);
								receiverMessageMng.update(receiverMsg);
							}
							messageMng.deleteById(msg.getId());
						}
					}
				}
			}
			message=Constants.API_STATUS_SUCCESS;
			status=Constants.API_STATUS_SUCCESS;
		}
		ApiResponse apiResponse=new ApiResponse(body, message, status);
		ResponseUtils.renderApiJson(response, request, apiResponse);
	}

	@Autowired
	private CmsUserMng userMng;
	@Autowired
	private ApiAccountMng apiAccountMng;
	@Autowired
	private ApiUserLoginMng apiUserLoginMng;
	@Autowired
	private ApiRecordMng apiRecordMng;
	@Autowired
	private CmsMessageMng messageMng;
	@Autowired
	private CmsReceiverMessageMng receiverMessageMng;
	@Autowired
	private CmsSiteMng siteMng;
}

