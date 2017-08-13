package com.sunrun.common.util.weixinpay;

import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;
/**
 * XML提交
 * @author jmm
 *
 */
public class XMLPost {
	/**
	* 微信接口基础post请求
	* @param path 请求路径
	* @param xml 请求参数
	* @return xml
	*/
	public static final String postpay(String path,String xml){
		try{
		       HttpPost post = new HttpPost(path);   
		       StringEntity entity = new StringEntity(xml,"iso8859-1");
		       entity.setContentEncoding("utf-8");
		       entity.setContentType("text/xml");
		       post.setEntity(entity);
		       return EntityUtils.toString(new DefaultHttpClient().execute(post).getEntity(),"utf-8");
		}catch(Exception e){
			e.printStackTrace();
		}
		return "";
	} 
}
