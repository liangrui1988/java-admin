package com.rui.pro1.modules.sys.utils;

import org.apache.shiro.crypto.hash.SimpleHash;

/**
 * 
 * @author ruiliang
 *
 */
public class PassUtil {
	public static final String salt = "$#@^&*2016";

	public static String encryptPassword(String password, String name) {

		// SHA-1 md5
		String newPassword = new SimpleHash("MD5", password, salt + name, 2)
				.toHex();

		return newPassword;
	}


	public static void main(String[] args) {
		System.out.println(encryptPassword("123456", "admin"));

	}
}
