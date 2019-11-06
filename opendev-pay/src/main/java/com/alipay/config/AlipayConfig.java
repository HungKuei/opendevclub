package com.alipay.config;

import java.io.FileWriter;
import java.io.IOException;

/**
 * 类名：AlipayConfig
 * 功能：基础配置类
 * 详细：设置帐户有关信息及返回路径
 * 修改日期：2017-04-05
 * 说明：
 * 以下代码只是为了方便商户测试而提供的样例代码，商户可以根据自己网站的需要，按照技术文档编写,并非一定要使用该代码。
 * 该代码仅供学习和研究支付宝接口使用，只是提供一个参考。
 */

public class AlipayConfig {

	// ↓↓↓↓↓↓↓↓↓↓请在这里配置您的基本信息↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓

	// 应用ID,您的APPID，收款账号既是您的APPID对应支付宝账号
	public static String app_id = "2016101700708474";

	// 商户私钥，您的PKCS8格式RSA2私钥
	public static String merchant_private_key = "MIIEvgIBADANBgkqhkiG9w0BAQEFAASCBKgwggSkAgEAAoIBAQCMwuYmyKoxQeHF9kkYU1kGUlunFeNKoI+M5ljZH5/Vsd5eGXm6hgNRKiy2+UHsUqHTUEOOJ1RPx3bonYAklsiGHZHgPtEJPzAmGm9RBbvkyCvnJI64jTVUKSrGSgsZucvgoEjXFCaMnWy+J/NmL0uulTmjFkil1d9pIDtH1oQdfOpPrIHzaEW0AM84MESkIaKkuT35taHq7S3X1CeNs6H8lLtELNbD6NwIcLXk3A0Qd2xqTl/mQv/P27tcjCthrzQzoJDXcQiFg1MLqNdxyjoln/QaRglfh1Q6VcGLR3GyUmVBEFnKvVT26wzWXyEO33CiCZ/zX3IGxpkTNUuvfr03AgMBAAECggEADbdIGetfuiEDUPjosngcqqMHz0Ywuarfkgk4CyArwPTuCBlPZT99v0Duzn2HUPHUVvTxQD7Q1l1HVu9R9z7CF192QXWdhNslY/Ebe7elUWu363QCfDU2Rxcj1eKNJlqWZTL5JgwOnZERcY4PB5wAMejUjvGy7oXimSnM3PkoMn89/e9tJbKRSp4yYp49d7DXYFqgfTBq71pATjwXP0KtWbOiiImtYruXgcM010B1zK7qWstoXDIpojWSHUdINRabdfkYe+x6stevqLvxG5cqIlPnKPKgHi4HyUsukKwRz1+0zwD1Pul9mYgW2w1MOu9qteeMk7kaCp4xexMWPnp0cQKBgQDiGJ1ZYshfiBN+R+5B78zvoxn47iR5R2eq4SBqcKZiNXLwCTK57P+Z+iKc4UqIo5ECu8l9A5+IbKOyITAuR3uyqVmGs9ubjWoFhUQyNC3JLZooSYQSGGM3x5MTd84GCsJNg/XUrQqwfbaBAMfFTs9sYgn/2t4SIkDm++nMPbAywwKBgQCfYO8gBXOaRzUIbWL1jvTtU3soMH3U3VxQrJqqI0737O9vLp8d3YbLBXUJ7C9rr39EAsr+UjjCfztj9NRFufVElWZKKIQVNEuNfPLNgejcBfi/g7e9rgAIl4WsE9mIDxazK5g5mJPBIqDY/1tBcYE9CpdgyU4SX9KUdJinY6r8fQKBgAbbwEop2PIs6op2HCAb6heK1wraDnkvVVG1qooQCLwJ6walAMjrC4XcNJiVrPEgJRH0KFSGe5xjz0NkBlYhDPuKb5DKe+roC0NHm536PzlPDNIUCNGTs87JWwjIJZPBV8uKKTbeWOipp5OaNnUhwuECyFLejMX4DAf1tE3hwSqJAoGBAI424clR9ACTT4gwi2AQaw8mMbv/4RhDWe+GxQ/SJ9HksxYjpMnydSfNu8zZ2sqdM9ONJnNfDNTI0Ub1HUEGmQvUwAymMI33Nv+Ood2s8Qr7rSW/NAJmNBN2mUmeALp+tEO2P8QNGLL64kofOd+gl7DPNZqgpjCRHodnrxuWPOnFAoGBAMD/p7FLc5VfN9pKQIwM3mZXVvbGTP79oNIer6FqZ0T0Rl7/P9moZ5Mub/dzMGmwW527UUloVViZCzY0Hchg/SzL7gEOSv2D8v6rtTQnx8xIJr1fPCuA92yfPYt5CYBzRhOUU+F2IpFUMvGwWFeeW8ZaXKjzd2wf7ahFeJUKr243";

	// 支付宝公钥,查看地址：https://openhome.alipay.com/platform/keyManage.htm
	// 对应APPID下的支付宝公钥。
	public static String alipay_public_key = "MIIBIjANBgkqhkiG9w0BAQEFAAOCAQ8AMIIBCgKCAQEAo8jtP8x70P1vcsfPWv4YsNlLHPZaafYZUv4Z2T2jjc4szsQJA1O3XNuJwmsvyEhLeEENaHoZkGqdMht3Al2MD89JJLNZBljymdbqyXXoGdThBYrUcpyvi1HvgieVVuOHoIECUoQZpCaz6jBcn1uoECDJxUoU45Au749MP7OrFU3rl8eOoYrAgqZG1Wh2uoxnhPqasij/oi1ZBbdxY0KDRYuhuGu4FqXNjcJ0Vx1hsmk6kM7AK6xc4W2ZxSyVrrTgAdyO5CrkFhUR1Hw/mSCJjOz1JPR5xnVUZskNOpoGHD7XN+Da25rUadcQNqc2T6/ES95O1gaQUnLcXqdTIdVyCQIDAQAB";

	// 服务器异步通知页面路径 需http://格式的完整路径，不能加?id=123这类自定义参数，必须外网可以正常访问
	public static String notify_url = "http://openpay.free.idcfengye.com/pay/norok?username=hungkuei";

	// 页面跳转同步通知页面路径 需http://格式的完整路径，不能加?id=123这类自定义参数，必须外网可以正常访问
	public static String return_url = "http://openpay.free.idcfengye.com/pay/norok?username=hungkuei";

	// 签名方式
	public static String sign_type = "RSA2";

	// 字符编码格式
	public static String charset = "utf-8";

	// 支付宝网关
	public static String gatewayUrl = "https://openapi.alipaydev.com/gateway.do";

	// 支付宝网关
	public static String log_path = "C:\\";

	// ↑↑↑↑↑↑↑↑↑↑请在这里配置您的基本信息↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑

	/**
	 * 写日志，方便测试（看网站需求，也可以改成把记录存入数据库）
	 * 
	 * @param sWord
	 *            要写入日志里的文本内容
	 */
	public static void logResult(String sWord) {
		FileWriter writer = null;
		try {
			writer = new FileWriter(log_path + "alipay_log_" + System.currentTimeMillis() + ".txt");
			writer.write(sWord);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (writer != null) {
				try {
					writer.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
	}
}
