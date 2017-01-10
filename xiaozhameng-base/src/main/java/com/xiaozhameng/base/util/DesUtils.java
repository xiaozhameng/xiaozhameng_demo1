package com.xiaozhameng.base.util;

import java.security.Key;
import java.security.Security;

import javax.crypto.Cipher;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * DES加密解密工具类
 * 
 * @author zl
 * 
 */
public class DesUtils {
	private static Logger logger = LoggerFactory.getLogger(DesUtils.class);
	
	private static String strDefaultKey = "ADSFGFG-";
	private Cipher encryptCipher = null;
	private Cipher decryptCipher = null;

	public static String byteArr2HexStr(byte[] arrB) {
		int iLen = arrB.length;
		// 每个byte用两个字符才能表示，所以字符串的长度是数组长度的两倍
		StringBuffer sb = new StringBuffer(iLen * 2);
		for (int i = 0; i < iLen; i++) {
			int intTmp = arrB[i];
			// 把负数转换为正数
			while (intTmp < 0) {
				intTmp = intTmp + 256;
			}
			// 小于0F的数需要在前面补0
			if (intTmp < 16) {
				sb.append("0");
			}
			sb.append(Integer.toString(intTmp, 16));
		}
		return sb.toString();
	}

	public static byte[] hexStr2ByteArr(String strIn){
		byte[] arrB = strIn.getBytes();
		int iLen = arrB.length;
		// 两个字符表示一个字节，所以字节数组长度是字符串长度除以2
		byte[] arrOut = new byte[iLen / 2];
		for (int i = 0; i < iLen; i = i + 2) {
			String strTmp = new String(arrB, i, 2);
			arrOut[i / 2] = (byte) Integer.parseInt(strTmp, 16);
		}
		return arrOut;
	}
	public DesUtils(){
		this(strDefaultKey);
	}
	private DesUtils(String strKey){
		try {
			Security.addProvider(new com.sun.crypto.provider.SunJCE());
			Key key = getKey(strKey.getBytes());
			encryptCipher = Cipher.getInstance("DES");
			encryptCipher.init(Cipher.ENCRYPT_MODE, key);
			decryptCipher = Cipher.getInstance("DES");
			decryptCipher.init(Cipher.DECRYPT_MODE, key);
		} catch (Exception e) {
			logger.error("init is error",e);
		}
	}

	/**
	 * 加密字节数组
	 * 
	 * @param arrB
	 *            需加密的字节数组
	 * @return 加密后的字节数组
	 * @throws Exception
	 */
	private byte[] encrypt(byte[] arrB) {
		try {
			return encryptCipher.doFinal(arrB);
		} catch (Exception e) {
			logger.error("encrypt is error",e);
		}
		return null;
	}

	/**
	 * 加密字符串
	 * 
	 * @param strIn
	 *            需加密的字符串
	 * @return 加密后的字符串
	 * @throws Exception
	 */
	private String encrypt(String strIn) {
		return byteArr2HexStr(encrypt(strIn.getBytes()));
	}

	/**
	 * 解密字节数组
	 * 
	 * @param arrB
	 *            需解密的字节数组
	 * @return 解密后的字节数组
	 * @throws Exception
	 */
	private byte[] decrypt(byte[] arrB){
		try {
			return decryptCipher.doFinal(arrB);
		} catch (Exception e) {
			logger.info("decrypt is error");
		}
		return null;
	}

	/**
	 * 解密字符串
	 * 
	 * @param strIn
	 *            需解密的字符串
	 * @return 解密后的字符串
	 * @throws Exception
	 */
	private String decrypt(String strIn) {
		return new String(decrypt(hexStr2ByteArr(strIn)));
	}

	/**
	 * 从指定字符串生成密钥，密钥所需的字节数组长度为8位 不足8位时后面补0，超出8位只取前8位
	 * 
	 * @param arrBTmp
	 *            构成该字符串的字节数组
	 * @return 生成的密钥
	 * @throws Exception
	 */
	private Key getKey(byte[] arrBTmp) throws Exception {
		// 创建一个空的8位字节数组（默认值为0）
		byte[] arrB = new byte[8];
		// 将原始字节数组转换为8位
		for (int i = 0; i < arrBTmp.length && i < arrB.length; i++) {
			arrB[i] = arrBTmp[i];
		}
		// 生成密钥
		Key key = new javax.crypto.spec.SecretKeySpec(arrB, "DES");

		return key;
	}

	public static String encryptByDES(String str, String key) {
		DesUtils des = null;
		String encrStr = null;
		try{
			if (key == null) {
				des = new DesUtils();
				encrStr = des.encrypt(str);
			} else {
				des = new DesUtils(MD5.encodeByMd5AndSalt(key,strDefaultKey));
				encrStr = des.encrypt(str);
			}
		}catch(Throwable t){
			logger.error("encryptByDES is error str:" + str +",key:" + key,t);
		}
		return encrStr == null ? "" : encrStr;
	}

	/**
	 * 解密方法，用于读取签名校验通过后的卡号
	 * 
	 * @param str
	 *            密文
	 * @param key
	 *            密钥
	 * @return 明文
	 * @throws Exception
	 */
	public static String decryptByDES(String str, String key){
		DesUtils des = null;
		String dencrStr = null;
		try{
			if (key == null) {
				des = new DesUtils();
				dencrStr = des.decrypt(str);
			} else {
				des = new DesUtils(MD5.encodeByMd5AndSalt(key,strDefaultKey));
				dencrStr = des.decrypt(str);
			}
		}catch(Throwable e){
			//logger.error("decryptByDES by md5 is error by str:" + str +",key:" + key,e);
		}
		if(dencrStr == null || "".equals(dencrStr)){
			try{
				if (key == null) {
					des = new DesUtils();
					dencrStr = des.decrypt(str);
				} else {
					des = new DesUtils(key);
					dencrStr = des.decrypt(str);
				}
			}catch(Throwable e){
				logger.error("decryptByDES is error by str:" + str +",key:" + key,e);
			}
		}
		return dencrStr == null ? "" : dencrStr;
	}
	public static void main(String[] args) {
		String value = DesUtils.encryptByDES("4367420014970862530","6000002");
		System.out.println(value);
		String data = DesUtils.decryptByDES(value,"6000002");
		System.out.println(data);
	}
}