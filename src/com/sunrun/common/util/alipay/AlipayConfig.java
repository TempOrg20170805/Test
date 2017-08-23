package com.sunrun.common.util.alipay;
public class AlipayConfig {
	// 商户appid
	public static String APPID = "2088421557546027";//APPID
	// 私钥 pkcs8格式的
	public static String RSA_PRIVATE_KEY = "MIICdgIBADANBgkqhkiG9w0BAQEFAASCAmAwggJcAgEAAoGBAKxfJriAeM5xUzMlepTAsPgPVHjVvYYLQhVWAhphn0HhlyB0aUKcjtOWBPiLmB5TZ416Aw54ZGJ5duBGSdv3HjiNM2dVueXQEPOOKo3H5OCxfmuXsldzsazTbFvOjJreYkpsqCORqV2A6CsK7C5Bfo2cA4YkLi9fYoR0Ay1QBGMNAgMBAAECgYBYg8wodqCSspMc8qQN+x1lZMnl+yyFxwY6Wp5d2b+kXynyGpVrBWY6tRD8aQLGn+HPPtaO+bfi8ajf5nhLIo07eUfpWZM9ehLj2kZco+N4u8L+L1zRca0HjSPGTY7BKyZZFQobGWZ6bzHqdrQZqKAFyqQAIWUX0rnnx6ryqjXLqQJBANc/w+/061seov4+/roqVJGOJao2CfeJKkDsfJb7rQ5HLzJr47rpRfGAK4iPTCKEyx0xT4RVnow4QjiuJFJikGcCQQDNAUuaftXfi7blQHlyxuGCcvGM010+GSKK3gxzyfWaX/3yJOFR++21vfFLg2qsU/k2vZAGPVywZ2nphdppuLhrAkAEvUewKeFgwTzvgi2Tpcj0539WzxfLFn8SrOJVZLc7wD9+ybda7zX2Ck9rE1Sm0euy+dn5XPU/uyFiJBtyoO7zAkEAnFg5jXjfqV4HPS1/sd4lS/RXbXwg52QJ6PhRbNkAWxyMMmiYAotsjadvZ5Sw/P4J2QqW1XtkvhAQQQ1ZNjmvQwJAe0sJGVa9F6fbh6amApDB5wvU9oCb74i5Tqp5/uzZIgZ3Kbej2QNu8b2eLcZlLr97CYGu3sHZaTSFa1d3kqYVYA==";
	// 服务器异步通知页面路径 需http://或者https://格式的完整路径，不能加?id=123这类自定义参数，必须外网可以正常访问
	public static String notify_url = "http://javawangcy.imwork.net:30650/SmartEv/rest";
	// 页面跳转同步通知页面路径 需http://或者https://格式的完整路径，不能加?id=123这类自定义参数，必须外网可以正常访问 商户可以自定义同步跳转地址
	//public static String return_url = "http://商户网关地址/alipay.trade.wap.pay-JAVA-UTF-8/return_url.jsp";
	// 请求网关地址
	public static String URL = "https://openapi.alipay.com/gateway.do";
	// 编码
	public static String CHARSET = "UTF-8";
	// 返回格式
	public static String FORMAT = "json";
	// 支付宝公钥
	public static String ALIPAY_PUBLIC_KEY = "MIIBIjANBgkqhkiG9w0BAQEFAAOCAQ8AMIIBCgKCAQEAx9bIQfJBeH9MIozUhE9LBQtDhoOpChEpLyhSNc5vl5Jtuu+tdHlmDDrIU5BTEpf2ZrPRyxVZklQ3cTiM4zteCq1lz81PRQJmpiUGO5esXC4nA1b2jmYc29cUtlBbD/o+TCmxFkkV8b9ug9nt//V4ai3rx0Cm7pB3Uza/NaEobRB5Tmg4aTUQf+qvNf6ir9HR8pKykrYL6uI69jx33OPdkaysRS/eZmhydc3DkwWoBSA0hHyztUQaVbtJ8aVxuN+RXWFNSO+W7lJM41uQk+yZED4rMZw1ZYB9wX00eWhbXt+8zfSfEUKWIgU+ycb5jbDx5U9Gxu506+s29T9q4zjMtwIDAQAB";
	// 日志记录目录
	public static String log_path = "/log";
	// RSA2
	public static String SIGNTYPE = "RSA2";
}
