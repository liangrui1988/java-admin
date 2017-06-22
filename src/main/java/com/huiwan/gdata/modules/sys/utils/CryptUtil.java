package com.huiwan.gdata.modules.sys.utils;

import java.security.MessageDigest;
import java.security.SecureRandom;

import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.DESKeySpec;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 加密解密工具
 */
public class CryptUtil {
	private static Logger logger = LoggerFactory.getLogger(CryptUtil.class);
	private static final String PASSWORD_CRYPT_KEY = "Cn_Yayo";
	private final static String DES = "DES";

	/**
	 * MD5 摘要计算(byte[]).
	 * 
	 * @param  src 			byte[]
	 * @throws Exception
	 * @return byte[] 16 bit digest
	 */
	public static byte[] md5(byte[] src) throws Exception {
		MessageDigest alg = MessageDigest.getInstance("MD5");
		return alg.digest(src);
	}

	/**
	 * MD5 摘要计算(String).
	 * 
	 * @param  src 	
	 * @throws Exception
	 * @return {@link String}
	 */
	public static String md5(String src) throws Exception {
		return byte2hex(md5(src.getBytes()));
	}

	/**
	 * 加密
	 * 
	 * @param  src 			数据源
	 * @param  key 			密钥，长度必须是8的倍数
	 * @return byte[]		返回加密后的数据
	 * @throws Exception
	 */
	public static byte[] encrypt(byte[] src, byte[] key) throws Exception {
		// DES算法要求有一个可信任的随机数源
		SecureRandom sr = new SecureRandom();
		// 从原始密匙数据创建DESKeySpec对象
		DESKeySpec dks = new DESKeySpec(key);
		// 创建一个密匙工厂，然后用它把DESKeySpec转换成
		// 一个SecretKey对象
		SecretKeyFactory keyFactory = SecretKeyFactory.getInstance(DES);
		SecretKey securekey = keyFactory.generateSecret(dks);
		// Cipher对象实际完成加密操作
		Cipher cipher = Cipher.getInstance(DES);
		// 用密匙初始化Cipher对象
		cipher.init(Cipher.ENCRYPT_MODE, securekey, sr);
		// 现在，获取数据并加密
		// 正式执行加密操作
		return cipher.doFinal(src);
	}

	public static byte[] hex2byte(byte[] bytes) {
		if ((bytes.length % 2) != 0) {
			throw new IllegalArgumentException("长度不是偶数");
		}
		byte[] b2 = new byte[bytes.length / 2];
		for (int n = 0; n < bytes.length; n += 2) {
			String item = new String(bytes, n, 2);
			b2[n / 2] = (byte) Integer.parseInt(item, 16);
		}
		return b2;
	}

	/**
	 * 解密
	 * 
	 * @param src 		数据源
	 * @param key 		密钥，长度必须是8的倍数
	 * @return byte[]	返回解密后的原始数据
	 * @throws Exception
	 */
	public static byte[] decrypt(byte[] src, byte[] key) throws Exception {
		// DES算法要求有一个可信任的随机数源
		SecureRandom sr = new SecureRandom();
		// 从原始密匙数据创建一个DESKeySpec对象
		DESKeySpec dks = new DESKeySpec(key);
		// 创建一个密匙工厂，然后用它把DESKeySpec对象转换成
		// 一个SecretKey对象
		SecretKeyFactory keyFactory = SecretKeyFactory.getInstance(DES);
		SecretKey securekey = keyFactory.generateSecret(dks);
		// Cipher对象实际完成解密操作
		Cipher cipher = Cipher.getInstance(DES);
		// 用密匙初始化Cipher对象
		cipher.init(Cipher.DECRYPT_MODE, securekey, sr);
		// 现在，获取数据并解密
		// 正式执行解密操作
		return cipher.doFinal(src);
	}

	/**
	 * 密码解密
	 * 
	 * @param data
	 * @return
	 * @throws Exception
	 */
	public final static String decryptPassword(String data) {
		if (data != null)
			try {
				return new String(decrypt(hex2byte(data.getBytes()), PASSWORD_CRYPT_KEY.getBytes()));
			} catch (Exception e) {
				logger.error("", e);
			}
		return null;
	}

	/**
	 * 密码加密
	 * 
	 * @param password
	 * @return
	 * @throws Exception
	 */
	public final static String encryptPassword(String password) {
		if (password != null)
			try {
				return byte2hex(encrypt(password.getBytes(), PASSWORD_CRYPT_KEY.getBytes()));
			} catch (Exception e) {
				logger.error("", e);
			}
		return null;
	}

	/**
	 * 二行制转字符串
	 * 
	 * @param bytes
	 * @return
	 */
	public static String byte2hex(byte[] bytes) {
		StringBuilder builder = new StringBuilder();
		String stmp = "";
		for (int n = 0; bytes != null && n < bytes.length; n++) {
			stmp = (java.lang.Integer.toHexString(bytes[n] & 0XFF));
			if (stmp.length() == 1) {
				builder.append("0").append(stmp);
			} else {
				builder.append(stmp);
			}
		}
		return builder.toString().toUpperCase();
	}

	/**
	 * 二行制转页面字符串
	 * 
	 * @param b
	 * @return
	 */
	public static String byte2webhex(byte[] b) {
		return byte2hex(b, "%");
	}

	/**
	 * 二行制转字符串
	 * 
	 * @param bytes
	 * @param elide 		分隔符
	 * @return
	 */
	public static String byte2hex(byte[] bytes, String elide) {
		StringBuilder sb = new StringBuilder();
		String stmp = "";
		elide = elide == null ? "" : elide;
		for (int n = 0; bytes != null && n < bytes.length; n++) {
			stmp = (java.lang.Integer.toHexString(bytes[n] & 0XFF));
			if (stmp.length() == 1)
				sb.append(elide).append("0").append(stmp);
			else
				sb.append(elide).append(stmp);
		}
		return sb.toString().toUpperCase();
	}

	public static String getMD5(byte[] source) {
		String src = null;
		// 用来将字节转换成 16 进制表示的字符
		char hexDigits[] = { '0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'a', 'b', 'c', 'd', 'e', 'f' };
		try {
			java.security.MessageDigest md = java.security.MessageDigest.getInstance("MD5");
			md.update(source);
			byte tmp[] = md.digest(); 		// MD5 的计算结果是一个 128 位的长整数，用字节表示就是 16 个字节
			char str[] = new char[16 * 2]; 	// 每个字节用 16 进制表示的话，使用两个字符，所以表示成 16 进制需要 32 个字符
			int k = 0; 						// 表示转换结果中对应的字符位置
			for (int i = 0; i < 16; i++) { 	// 从第一个字节开始，对 MD5 的每一个字节转换成 16 进制字符的转换
				byte byte0 = tmp[i];	 	// 取第 i 个字节
				str[k++] = hexDigits[byte0 >>> 4 & 0xf]; 
				str[k++] = hexDigits[byte0 & 0xf]; // 取字节中低 4 位的数字转换
			}
			src = new String(str); // 换后的结果转换为字符串

		} catch (Exception e) {
			logger.error("", e);
		}
		return src;
	}
	
	
	/**
	 * 随机数据来源
	 */
	@SuppressWarnings("unused")
	private final static String[] UPPERCASE = { "1", "2", "3", "4", "5", "6",
			"7", "8", "9", "0", "Q", "W", "E", "R", "T", "Y", "U", "I", "O",
			"P", "A", "S", "D", "F", "G", "H", "J", "K", "L", "Z", "X", "C",
			"V", "B", "N", "M" };
	
	/**
	 * 随机数据来源
	 */
	@SuppressWarnings("unused")
	private final static String[] LOWERCASE = { "1", "2", "3", "4", "5", "6",
		"7", "8", "9", "0", "q", "w", "e", "r", "t", "y", "u", "i", "o",
		"p", "a", "s", "d", "f", "g", "h", "j", "k", "l", "z", "x", "c",
		"v", "b", "n", "m" };
}
