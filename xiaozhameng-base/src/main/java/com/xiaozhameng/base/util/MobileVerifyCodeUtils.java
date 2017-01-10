package com.xiaozhameng.base.util;

import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
/**
 * 手机短信验证码 生成 工具 
 * 
 * @author liuqc
 *
 */
public class MobileVerifyCodeUtils {
	private final static int OFFSET = 538309;

	/**
	 * 随机生成六位数验证码
	 */
	public static String generateMobileVfCode() {
		long seed = System.currentTimeMillis() + OFFSET;
		SecureRandom secureRandom = null; // 安全随机类
		try {
			secureRandom = SecureRandom.getInstance("SHA1PRNG");
			secureRandom.setSeed(seed);
		} catch (NoSuchAlgorithmException e) {
		}
		String codeList = "1234567890"; // 验证码数字取值范围
		StringBuilder sRand = new StringBuilder();
		for (int i = 0; i < 6; i++) {
			int code = secureRandom.nextInt(codeList.length() - 1);
			String rand = codeList.substring(code, code + 1);
			sRand.append(rand);
		}
		return sRand.toString();
	}
}
