package com.sunrun.common.util.alipay.config;

/* *
 *类名：AlipayConfig
 *功能：基础配置类
 *详细：设置帐户有关信息及返回路径
 *版本：3.3
 *日期：2012-08-10
 *说明：
 *以下代码只是为了方便商户测试而提供的样例代码，商户可以根据自己网站的需要，按照技术文档编写,并非一定要使用该代码。
 *该代码仅供学习和研究支付宝接口使用，只是提供一个参考。
	
 *提示：如何获取安全校验码和合作身份者ID
 *1.用您的签约支付宝账号登录支付宝网站(www.alipay.com)
 *2.点击“商家服务”(https://b.alipay.com/order/myOrder.htm)
 *3.点击“查询合作者身份(PID)”、“查询安全校验码(Key)”

 *安全校验码查看时，输入支付密码后，页面呈灰色的现象，怎么办？
 *解决方法：
 *1、检查浏览器配置，不让浏览器做弹框屏蔽设置
 *2、更换浏览器或电脑，重新登录查询。
 */

public class AlipayConfig {
	
	//↓↓↓↓↓↓↓↓↓↓请在这里配置您的基本信息↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓
	// 合作身份者ID，以2088开头由16位纯数字组成的字符串
	public static String partner = "2088421557546027";
	
	// 收款支付宝账号，以2088开头由16位纯数字组成的字符串，一般情况下收款账号就是签约账号
	public static String seller_id = partner;
	// 商户的私钥
	public static String private_key = "MIICdgIBADANBgkqhkiG9w0BAQEFAASCAmAwggJcAgEAAoGBAKxfJriAeM5xUzMlepTAsPgPVHjVvYYLQhVWAhphn0HhlyB0aUKcjtOWBPiLmB5TZ416Aw54ZGJ5duBGSdv3HjiNM2dVueXQEPOOKo3H5OCxfmuXsldzsazTbFvOjJreYkpsqCORqV2A6CsK7C5Bfo2cA4YkLi9fYoR0Ay1QBGMNAgMBAAECgYBYg8wodqCSspMc8qQN+x1lZMnl+yyFxwY6Wp5d2b+kXynyGpVrBWY6tRD8aQLGn+HPPtaO+bfi8ajf5nhLIo07eUfpWZM9ehLj2kZco+N4u8L+L1zRca0HjSPGTY7BKyZZFQobGWZ6bzHqdrQZqKAFyqQAIWUX0rnnx6ryqjXLqQJBANc/w+/061seov4+/roqVJGOJao2CfeJKkDsfJb7rQ5HLzJr47rpRfGAK4iPTCKEyx0xT4RVnow4QjiuJFJikGcCQQDNAUuaftXfi7blQHlyxuGCcvGM010+GSKK3gxzyfWaX/3yJOFR++21vfFLg2qsU/k2vZAGPVywZ2nphdppuLhrAkAEvUewKeFgwTzvgi2Tpcj0539WzxfLFn8SrOJVZLc7wD9+ybda7zX2Ck9rE1Sm0euy+dn5XPU/uyFiJBtyoO7zAkEAnFg5jXjfqV4HPS1/sd4lS/RXbXwg52QJ6PhRbNkAWxyMMmiYAotsjadvZ5Sw/P4J2QqW1XtkvhAQQQ1ZNjmvQwJAe0sJGVa9F6fbh6amApDB5wvU9oCb74i5Tqp5/uzZIgZ3Kbej2QNu8b2eLcZlLr97CYGu3sHZaTSFa1d3kqYVYA==";
	
	// 支付宝的公钥，无需修改该值
	public static String ali_public_key  = "MIGfMA0GCSqGSIb3DQEBAQUAA4GNADCBiQKBgQCnxj/9qwVfgoUh/y2W89L6BkRAFljhNhgPdyPuBV64bfQNN1PjbCzkIM6qRdKBoLPXmKKMiFYnkd6rAoprih3/PrQEB/VsW8OoM8fxn67UDYuyBTqA23MML9q1+ilIZwBC2AQ2UBVOrFXfFl75p6/B5KsiNG9zpgmLCUYuLkxpLQIDAQAB";

	//↑↑↑↑↑↑↑↑↑↑请在这里配置您的基本信息↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑
	

	// 调试用，创建TXT日志文件夹路径
	public static String log_path = "D:\\";

	// 字符编码格式 目前支持 gbk 或 utf-8
	public static String input_charset = "utf-8";
	
	// 签名方式 不需修改
	public static String sign_type = "RSA";

}
